package ru.scol.gb.j3.lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    private static boolean isAnnotationOnce(Method[] methods, Class<? extends Annotation> annotationClass) {
        int count = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                count++;
            }
        }
        return (count == 1);
    }

    private static Method getMethodAnnotation(Method[] methods, Class<? extends Annotation> annotationClass) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        return null;
    }

    private static LinkedList<Method> getTestsList(Method[] methods) {
        LinkedList<Method> methodLinkedList = new LinkedList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(TestSuite.class)) {
                methodLinkedList.add(method);
            }
        }
        return methodLinkedList;
    }

    private static void sortTestsByPriority(LinkedList<Method> methodsList) {
        Collections.sort(methodsList, Comparator.comparingInt(o -> o.getAnnotation(TestSuite.class).priority())
        );
    }

    public static void start(Class c) throws RuntimeException {
        try {
            Method[] methods = c.getDeclaredMethods();
            if (!isAnnotationOnce(methods, BeforeSuite.class) || !isAnnotationOnce(methods, AfterSuite.class)) {
                throw new RuntimeException("Methods \"BeforeSuite\" and \"AfterSuite\" should be one");
            }

            Constructor constructor = c.getDeclaredConstructor();
            Object classObject = constructor.newInstance();

            Method method = getMethodAnnotation(methods, BeforeSuite.class);
            method.invoke(classObject);

            LinkedList<Method> tests = getTestsList(methods);
            sortTestsByPriority(tests);
            for (Method test : tests) {
                test.invoke(classObject);
            }

            method = getMethodAnnotation(methods, AfterSuite.class);
            method.invoke(classObject);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void start(String className) throws RuntimeException {
        try {
            Class c = Class.forName(className);
            start(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
