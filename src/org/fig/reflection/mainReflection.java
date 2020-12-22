package org.fig.reflection;

import org.fig.reflection.model.Person;
import org.fig.reflection.util.ColumnField;
import org.fig.reflection.util.Metamodel;
import org.fig.reflection.util.PrimaryKeyField;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class mainReflection {

    public static void main(String[] args) throws ClassNotFoundException {
        Metamodel metamodel = Metamodel.of(Person.class);

        PrimaryKeyField pkField = metamodel.getPrimaryKey();
        List<ColumnField> columnFields = metamodel.getColumns();

        System.out.println("PK name = " + pkField.getName() +
                ", type = " + pkField.getType().getSimpleName());

        for(ColumnField columnField: columnFields){
            System.out.println("ColumnField name = " + columnField.getName() +
                    ", type = " + columnField.getType().getSimpleName());
        }
    }

    public static void printMethods() throws ClassNotFoundException {
        String personClassName = "org.fig.reflection.model.Person";
        Class<?> personClass = Class.forName(personClassName);

        System.out.println("Person class = " + personClass);

        Field[] fields = personClass.getFields();
        System.out.println("Fields: ");
        System.out.println(Arrays.toString(fields));

        Field[] declaredFields = personClass.getDeclaredFields();
        System.out.println("Fields: ");
        System.out.println(Arrays.toString(declaredFields));

        System.out.println("Methods: ");
        Method[] methods = personClass.getMethods();
        for(Method method: methods){
            System.out.println(method);
        }

        System.out.println("Declared Methods: ");
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for(Method method: declaredMethods){
            System.out.println(method);
        }

        System.out.println("Static Declared Methods: ");
        Arrays.stream(declaredMethods)
                .filter(m -> Modifier.isStatic(m.getModifiers()))
                .forEach(System.out::println);
    }
}
