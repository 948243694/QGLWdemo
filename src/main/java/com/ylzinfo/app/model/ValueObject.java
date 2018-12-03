package com.ylzinfo.app.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class ValueObject {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
