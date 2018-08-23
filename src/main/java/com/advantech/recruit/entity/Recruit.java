package com.advantech.recruit.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Recruit extends IdEntity{

    private String name;

    private String grander;

    private String phone;

    private String email;

    private String school;

    private String major;

    private String background;

    private String description;

    private String createTime;

    private String url;


    private String credit;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrander() {
        return grander;
    }

    public void setGrander(String grander) {
        this.grander = grander;
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

    public void setDescription(String des) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
