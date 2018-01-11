package com.fesco.saashr.web;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-05
 */
public class TestPerson {
    private int id;
    private String name;
    private int age;
    private int gender;
    private List<TestPerson> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<TestPerson> getChildren() {
        return children;
    }

    public void setChildren(List<TestPerson> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" {");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", children=").append(children);
        sb.append("}");
        return sb.toString();
    }
}
