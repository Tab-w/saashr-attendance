package com.fesco.saashr.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author WangXingYu
 * @date 2018-01-10
 */
public class DateEditor extends PropertiesEditor {

    /**
     * 日期是否允许为空
     */
    private boolean isAllowEmpty;

    /**
     * 日期格式
     */
    private String pattern;

    public DateEditor() {
        super();
        this.isAllowEmpty = true;
        this.pattern = "yyyy-MM-dd";
    }

    public DateEditor(String pattern, boolean isAllowEmpty) {
        super();
        this.isAllowEmpty = isAllowEmpty;
        this.pattern = pattern;
    }

    public boolean isAllowEmpty() {
        return isAllowEmpty;
    }

    public void setAllowEmpty(boolean isAllowEmpty) {
        this.isAllowEmpty = isAllowEmpty;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            if (!StringUtils.isEmpty(text)) {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                setValue(format.parse(text));
            } else {
                if (isAllowEmpty) {
                    setValue(null);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}