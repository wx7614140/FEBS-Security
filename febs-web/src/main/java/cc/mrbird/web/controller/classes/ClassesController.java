package cc.mrbird.web.controller.classes;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.utils.FileUtils;
import cc.mrbird.web.controller.base.BaseController;
import cc.mrbird.web.domain.Class;
import cc.mrbird.web.service.classes.ClassesService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ClassesController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassesService classesService;
    @Log("获取班级信息")
    @RequestMapping("class")
    @PreAuthorize("hasAuthority('class:list')")
    public String index(Model model) {
        return "class/class";
    }

    @RequestMapping("class/getClass")
    @ResponseBody
    public ResponseBo getClass(Long classId) {
        try {
            Class clazz = this.classesService.findById(classId);
            return ResponseBo.ok(clazz);
        } catch (Exception e) {
            log.error("获取班级信息失败", e);
            return ResponseBo.error("获取班级信息失败，请联系网站管理员！");
        }
    }
    @Log("获取班级信息")
    @RequestMapping("class/list")
    @PreAuthorize("hasAuthority('class:list')")
    @ResponseBody
    public Map<String, Object> classList(QueryRequest request, cc.mrbird.web.domain.Class clazz) {
            return super.selectByPageNumSize(request, () -> this.classesService.findClasses(clazz));
    }

    @RequestMapping("class/checkClassName")
    @ResponseBody
    public boolean checkDeptName(String className, String oldClassName) {
        if (StringUtils.isNotBlank(oldClassName) && StringUtils.equalsIgnoreCase(className, oldClassName)) {
            return true;
        }
        Class result = this.classesService.findByName(className);
        return result == null;
    }

    @Log("新增班级 ")
    @PreAuthorize("hasAuthority('class:add')")
    @RequestMapping("class/add")
    @ResponseBody
    public ResponseBo addRole(Class clazz) {
        try {
            this.classesService.addClass(clazz);
            return ResponseBo.ok("新增班级成功！");
        } catch (Exception e) {
            log.error("新增班级失败", e);
            return ResponseBo.error("新增班级失败，请联系网站管理员！");
        }
    }

    @Log("删除班级")
    @PreAuthorize("hasAuthority('class:delete')")
    @RequestMapping("class/delete")
    @ResponseBody
    public ResponseBo deleteClasses(String ids) {
        try {
            this.classesService.deleteClasses(ids);
            return ResponseBo.ok("删除班级成功！");
        } catch (Exception e) {
            log.error("删除班级失败", e);
            return ResponseBo.error("删除班级失败，请联系网站管理员！");
        }
    }

    @Log("修改班级 ")
    @PreAuthorize("hasAuthority('class:update')")
    @RequestMapping("class/update")
    @ResponseBody
    public ResponseBo updateClass(Class clazz) {
        try {
            this.classesService.updateClass(clazz);
            return ResponseBo.ok("修改班级成功！");
        } catch (Exception e) {
            log.error("修改部门失败", e);
            return ResponseBo.error("修改班级失败，请联系网站管理员！");
        }
    }

    @RequestMapping("class/excel")
    @ResponseBody
    public ResponseBo classExcel(Class clazz) {
        try {
            List<Class> list = this.classesService.findAllClasses(clazz);
            return FileUtils.createExcelByPOIKit("班级表", list, Class.class);
        } catch (Exception e) {
            log.error("导出班级信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("class/csv")
    @ResponseBody
    public ResponseBo classCsv(Class clazz) {
        try {
            List<Class> list = this.classesService.findAllClasses(clazz);
            return FileUtils.createCsv("班级表", list, Class.class);
        } catch (Exception e) {
            log.error("获取班级信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
}
