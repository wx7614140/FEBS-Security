package cc.mrbird.web.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "t_subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "ID")
    @ExportConfig(value = "编号")
    private Long id;

    @Column(name = "GRADE_ID")
    @ExportConfig(value = "年级")
    private Long gradeId;

    @Column(name = "NAME")
    @ExportConfig(value = "班级名称")
    private String name;
    @Column(name = "REMARKS")
    @ExportConfig(value = "备注")
    private String remarks;
    @Column(name = "DEL_FLAG")
    @ExportConfig(value = "备注")
    private Integer delFlag;
    @Column(name = "CREATE_DATE")
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    private Date createDate;
    @Column(name = "CREATE_BY")
    @ExportConfig(value = "创建人")
    private Long createBy;
    @Column(name = "UPDATE_DATE")
    @ExportConfig(value = "修改时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    private Date updateDate;
    @Column(name = "UPDATE_BY")
    @ExportConfig(value = "修改人")
    private Long updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
