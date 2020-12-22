package org.fig.reflection.util;

import org.fig.reflection.annotation.Column;
import org.fig.reflection.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Metamodel<T> {
    private Class<T> clss;

    public static <T> Metamodel<T> of(Class<T> clss) {
        return new Metamodel<>(clss);
    }

    public Metamodel(Class<T> clss) {
        this.clss = clss;
    }

    public PrimaryKeyField getPrimaryKey() {
        Field[] fields = clss.getDeclaredFields();
        for(Field field: fields){
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if(primaryKey != null){
                PrimaryKeyField pkField = new PrimaryKeyField(field);
                return pkField;
            }
        }

        throw new IllegalArgumentException("No PK found in class " + clss.getSimpleName());
    }

    public List<ColumnField> getColumns() {
        List<ColumnField> columnFields = new ArrayList<>();
        Field[] fields = clss.getDeclaredFields();
        for(Field field: fields){
            Column column = field.getAnnotation(Column.class);
            if(column != null){
                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
            }
        }

        return columnFields;
    }
}
