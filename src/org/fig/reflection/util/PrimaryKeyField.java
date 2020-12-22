package org.fig.reflection.util;

import java.lang.reflect.Field;

public class PrimaryKeyField {
    private Field field;

    public PrimaryKeyField(Field field) {
        this.field = field;
    }

    public String getName() {
        return field.getName();
    }

    public Class<?> getType() {
        return field.getType();
    }
}
