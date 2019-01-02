package com.example.orders_api.entity;

import com.example.orders_api.utils.Constants;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.StandardToStringStyle;

public abstract class AbstractEntity {
    @Override
    public String toString() {
        StandardToStringStyle toStringStyle = new StandardToStringStyle();
        toStringStyle.setContentStart(Constants.TO_STRING_CONTENT_START);
        toStringStyle.setFieldSeparator(Constants.TO_STRING_SEPARATOR);
        toStringStyle.setFieldSeparatorAtStart(false);
        toStringStyle.setContentEnd(Constants.TO_STRING_CONTENT_END);
        toStringStyle.setUseShortClassName(true);
        toStringStyle.setUseIdentityHashCode(false);

        return new ReflectionToStringBuilder(this, toStringStyle).toString();
    }
}
