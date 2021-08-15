package com.whpowernode.entity;

import java.util.Date;

/**
 * @项目 code
 * @描述 TODO
 * @时间 2021-08-10 14:58
 **/
public class Student {
    private Integer id;
    private String name;
    private String address;
    private Integer age;
    private String sex;
    private Date  birth;

    public Student() {
    }

    public Student(Integer id, String name, String address, Integer age, String sex, Date birth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                '}';
    }
}
