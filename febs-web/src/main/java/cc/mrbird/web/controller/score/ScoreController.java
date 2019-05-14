package cc.mrbird.web.controller.score;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.utils.FileUtils;
import cc.mrbird.system.domain.MyUser;
import cc.mrbird.system.domain.UserWithRole;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.controller.base.BaseController;
import cc.mrbird.web.domain.Score;
import cc.mrbird.web.service.score.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ScoreController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;
    @Log("获取分数信息")
    @RequestMapping("score")
    @PreAuthorize("hasAuthority('score:list')")
    public String index(Model model) {
        return "score/score";
    }

    @RequestMapping("score/getScore")
    @ResponseBody
    public ResponseBo getScore(Long id) {
        try {
            Score score = this.scoreService.findById(id);
            return ResponseBo.ok(score);
        } catch (Exception e) {
            log.error("获取分数信息失败", e);
            return ResponseBo.error("获取分数信息失败，请联系网站管理员！");
        }
    }
    @Log("获取分数信息")
    @RequestMapping("score/list")
    @PreAuthorize("hasAuthority('score:list')")
    @ResponseBody
    public Map<String, Object> scoreList(QueryRequest request, Score score) {
        MyUser currentUser=this.getCurrentUser();
        List<UserWithRole> roles= userService.findUserWithRole(currentUser.getUserId());
        if(roles.get(0).getRoleName().equalsIgnoreCase("教师角色")){
            score.setDeptId(roles.get(0).getDeptId());
        }
        if(roles.get(0).getRoleName().equalsIgnoreCase("学生角色")){
            score.setStuId(roles.get(0).getUserId());
        }
        return super.selectByPageNumSize(request, () -> this.scoreService.findScores(score));
    }

    /**@RequestMapping("score/checkScoreName")
    @ResponseBody
    public boolean checkScoreName(String name, String oldName) {
        if (StringUtils.isNotBlank(oldName) && StringUtils.equalsIgnoreCase(name, oldName)) {
            return true;
        }
        Score result = this.scoreService.findByName(name);
        return result == null;
    }**/

    @Log("新增分数 ")
    @PreAuthorize("hasAuthority('score:add')")
    @RequestMapping("score/add")
    @ResponseBody
    public ResponseBo addScore(Score score) {
        try {
            score.setCreateBy(this.getCurrentUser().getUserId());
            this.scoreService.addScore(score);
            return ResponseBo.ok("新增分数成功！");
        } catch (Exception e) {
            log.error("新增分数失败", e);
            return ResponseBo.error("新增分数失败，请联系网站管理员！");
        }
    }

    @Log("删除分数")
    @PreAuthorize("hasAuthority('score:delete')")
    @RequestMapping("score/delete")
    @ResponseBody
    public ResponseBo deleteScores(String ids) {
        try {
            this.scoreService.deleteScores(ids);
            return ResponseBo.ok("删除分数成功！");
        } catch (Exception e) {
            log.error("删除分数失败", e);
            return ResponseBo.error("删除分数失败，请联系网站管理员！");
        }
    }

    @Log("修改分数 ")
    @PreAuthorize("hasAuthority('score:update')")
    @RequestMapping("score/update")
    @ResponseBody
    public ResponseBo updateScore(Score score) {
        try {
            score.setUpdateBy(this.getCurrentUser().getUserId());
            score.setUpdateDate(new Date());
            this.scoreService.updateScore(score);
            return ResponseBo.ok("修改分数成功！");
        } catch (Exception e) {
            log.error("修改部门失败", e);
            return ResponseBo.error("修改分数失败，请联系网站管理员！");
        }
    }

    @RequestMapping("subject/checkScore")
    @ResponseBody
    public boolean checkScore(Long stuId, Long subject) {
        if (stuId==null && subject==null) {
            return true;
        }
        Score result = this.scoreService.checkScore(stuId,subject);
        return result == null;
    }
    @RequestMapping("score/excel")
    @ResponseBody
    public ResponseBo scoreExcel(Score score) {
        try {
            List<Score> list = this.scoreService.findAllScores(score);
            return FileUtils.createExcelByPOIKit("分数表", list, Class.class);
        } catch (Exception e) {
            log.error("导出分数信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("score/csv")
    @ResponseBody
    public ResponseBo scoreCsv(Score score) {
        try {
            List<Score> list = this.scoreService.findAllScores(score);
            return FileUtils.createCsv("分数表", list, Score.class);
        } catch (Exception e) {
            log.error("获取分数信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
}
