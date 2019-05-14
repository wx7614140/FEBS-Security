package cc.mrbird.web.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
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
    @ExportConfig(value = "年级",convert = "s:1=一年级,2=二年级,3=三年级,4=四年级,5=五年级,6=六年级,7=初一,8=初二,9=初三,10=高一,11=高二,12=高三")
    private Long gradeId;

    @Column(name = "NAME")
    @ExportConfig(value = "名称")
    private String name;
    @Column(name = "REMARKS")
    @ExportConfig(value = "备注")
    private String remarks;
    @Column(name = "DEL_FLAG")
    private Integer delFlag;
    @Column(name = "CREATE_DATE")
    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    private Date createDate;
    @Column(name = "CREATE_BY")

    private Long createBy;
    @Column(name = "UPDATE_DATE")
    @ExportConfig(value = "修改时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    private Date updateDate;
    @Column(name = "UPDATE_BY")
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
    @Transient
    @ExportConfig(value = "创建人")
    private String creator;
    @Transient
    @ExportConfig(value = "修改人")
    private String updator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
}
