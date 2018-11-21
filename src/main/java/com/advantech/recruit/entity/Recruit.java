package com.advantech.recruit.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Recruit extends IdEntity{
    /**
     * 姓名
     */
    private String name;
    /**
     * 提交时间
     */
    private String createTime;
    /**
     * 性别
     */
    private String gender;
    /**
     * 电话
     */
    @Column(unique = true)
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 学校
     */
    private String school;
    /**
     * 专业
     */
    private String major;
    /**
     * 学历
     */
    private String background;
    /**
     * 描述
     */
    private String description;

    /**
     * 简历url
     */
    private String resumeUrl;
    /**
     * 身份证号
     */
    private String credit;
    /**
     * 投递岗位
     * 0：前端开发  web app
     * 1：java开发  java development
     * 2：云平台开发工程师 cloud development
     * 3：软件测试   software test
     * 4：软件产品经理 pm
     * 5: 云平台应用工程师 cloud application
     */
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Column(name = "description",length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", background='" + background + '\'' +
                ", description='" + description + '\'' +
                ", resumeUrl='" + resumeUrl + '\'' +
                ", credit='" + credit + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
