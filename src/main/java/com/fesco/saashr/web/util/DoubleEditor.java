package com.fesco.saashr.web.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

public class DoubleEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            text = "0";
        }
        setValue(Double.parseDouble(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}