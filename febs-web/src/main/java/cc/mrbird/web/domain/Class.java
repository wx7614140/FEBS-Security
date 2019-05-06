package cc.mrbird.web.domain;

import cc.mrbird.common.annotation.ExportConfig;
import cc.mrbird.web.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_class")
public class Class extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "CLASS_ID")
    @ExportConfig(value = "编号")
    private Long classId;

    @Column(name = "GRADE_ID")
    @ExportConfig(value = "年级")
    private Long gradeId;

    @Column(name = "CLASS_NAME")
    @ExportConfig(value = "班级名称")
    private String className;

    //@Column(name = "ORDER_NUM",nullable=true,updatable=false)
    private Integer state;

    @Column(name = "CREATE_TIME")
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    private Date createTime;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
