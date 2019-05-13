package cc.mrbird.web.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Table(name = "t_score")
public class Score implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "ID")
    @ExportConfig(value = "编号")
    private Long id;

    @Column(name = "SCORE")
    @ExportConfig(value = "分数")
    private Integer score;

    @Column(name = "STU_ID")
    @ExportConfig(value = "学生")
    private Long stuId;

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

    @Column(name = "SUB_ID")
    @ExportConfig(value = "课程")
    private Long subId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
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

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }
    @Transient
    private String username;
    @Transient
    private String subname;
    @Transient
    private Long deptId;
    @Transient
    private String creator;
    @Transient
    private String updator;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

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
