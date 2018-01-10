package com.fesco.saashr.web.common;

/**
 * @author WangXingYu
 * @date 2018-01-10
 */
public enum SessionAttr {
    Current_USER("currentUser", "当前用户");

    SessionAttr(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
