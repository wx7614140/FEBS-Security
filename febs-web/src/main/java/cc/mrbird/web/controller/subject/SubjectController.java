package cc.mrbird.web.controller.subject;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.utils.FileUtils;
import cc.mrbird.web.controller.base.BaseController;
import cc.mrbird.web.domain.Subject;
import cc.mrbird.web.service.subject.SubjectService;
import org.apache.commons.lang3.StringUtils;
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
public class SubjectController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubjectService subjectService;
    @Log("获取课程信息")
    @RequestMapping("subject")
    @PreAuthorize("hasAuthority('subject:list')")
    public String index(Model model) {
        return "subject/subject";
    }

    @RequestMapping("subject/getSubject")
    @ResponseBody
    public ResponseBo getSubject(Long id) {
        try {
            Subject subject = this.subjectService.findById(id);
            return ResponseBo.ok(subject);
        } catch (Exception e) {
            log.error("获取课程信息失败", e);
            return ResponseBo.error("获取课程信息失败，请联系网站管理员！");
        }
    }
    @Log("获取课程信息")
    @RequestMapping("subject/list")
    @PreAuthorize("hasAuthority('subject:list')")
    @ResponseBody
    public Map<String, Object> subjectList(QueryRequest request, Subject subject) {
            return super.selectByPageNumSize(request, () -> this.subjectService.findSubjects(subject));
    }

    @RequestMapping("subject/checkSubjectName")
    @ResponseBody
    public boolean checkSubjectName(String name, String oldName) {
        if (StringUtils.isNotBlank(oldName) && StringUtils.equalsIgnoreCase(name, oldName)) {
            return true;
        }
        Subject result = this.subjectService.findByName(name);
        return result == null;
    }

    @Log("新增课程 ")
    @PreAuthorize("hasAuthority('subject:add')")
    @RequestMapping("subject/add")
    @ResponseBody
    public ResponseBo addSubject(Subject subject) {
        try {
            subject.setCreateBy(this.getCurrentUser().getUserId());
            this.subjectService.addSubject(subject);
            return ResponseBo.ok("新增课程成功！");
        } catch (Exception e) {
            log.error("新增课程失败", e);
            return ResponseBo.error("新增课程失败，请联系网站管理员！");
        }
    }

    @Log("删除课程")
    @PreAuthorize("hasAuthority('subject:delete')")
    @RequestMapping("subject/delete")
    @ResponseBody
    public ResponseBo deleteSubjects(String ids) {
        try {
            this.subjectService.deleteSubjects(ids);
            return ResponseBo.ok("删除课程成功！");
        } catch (Exception e) {
            log.error("删除课程失败", e);
            return ResponseBo.error("删除课程失败，请联系网站管理员！");
        }
    }

    @Log("修改课程 ")
    @PreAuthorize("hasAuthority('subject:update')")
    @RequestMapping("subject/update")
    @ResponseBody
    public ResponseBo updateSubject(Subject subject) {
        try {
            subject.setUpdateBy(this.getCurrentUser().getUserId());
            subject.setUpdateDate(new Date());
            this.subjectService.updateSubject(subject);
            return ResponseBo.ok("修改课程成功！");
        } catch (Exception e) {
            log.error("修改部门失败", e);
            return ResponseBo.error("修改课程失败，请联系网站管理员！");
        }
    }

    @RequestMapping("subject/excel")
    @ResponseBody
    public ResponseBo subjectExcel(Subject subject) {
        try {
            List<Subject> list = this.subjectService.findAllSubjects(subject);
            return FileUtils.createExcelByPOIKit("课程表", list, Class.class);
        } catch (Exception e) {
            log.error("导出课程信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("subject/csv")
    @ResponseBody
    public ResponseBo subjectCsv(Subject subject) {
        try {
            List<Subject> list = this.subjectService.findAllSubjects(subject);
            return FileUtils.createCsv("课程表", list, Subject.class);
        } catch (Exception e) {
            log.error("获取课程信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
}
