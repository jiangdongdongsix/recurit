package com.advantech.recruit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Recruit extends IdEntity{

    private String name;

    private String grander;

    private String tel;

    private String email;

    private String school;

    private String professional;

    private String background;

    private String des;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Column(name = "des",length = 2000)
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
