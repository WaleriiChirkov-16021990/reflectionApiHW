package org.example;

import org.example.myAnnotations.AfterEach;
import org.example.myAnnotations.BeforeEach;
import org.example.myAnnotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyAbstractProcessor {
    public static void processObject(Class<?> object) {
        final Constructor<?> declaredConstructor;
        final Object objectByReflection;
        try {
            declaredConstructor = object.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            objectByReflection = declaredConstructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса", e);
        }


        for (Method method : object.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                checkVoidMethod(method);
                method.setAccessible(true);
                runTest(method, objectByReflection);
            }
        }
        List<Method> methodsTestList = new ArrayList<Method>();
        for (Method method : object.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                methodsTestList.add(method);
//                checkTestMethod(method);
//                method.setAccessible(true);
//                runTest(method, objectByReflection);
            }
        }
        methodsTestList = methodsTestList.stream().sorted((a, b) -> a.getDeclaredAnnotation(Test.class).order() - b.getDeclaredAnnotation(Test.class).order()).collect(Collectors.toList());

        for (Method method :
                methodsTestList) {
            checkTestMethod(method);
            method.setAccessible(true);
            runTest(method, objectByReflection);
        }


        for (Method method : object.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterEach.class)) {
                checkVoidMethod(method);
                method.setAccessible(true);
                runTest(method, objectByReflection);
            }
        }
    }

    private static void checkVoidMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(Void.class) && method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Method " + method.getName() + " should be a valid Void method and not have argument");
        }
    }


    private static void checkTestMethod(Method method) {
        if (method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Method " + method.getName() + " should be a valid Void method and not have argument");
        }
    }

    private static void runTest(Method method, Object object) {
        try {
            method.setAccessible(true);
            method.invoke(object);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось запустить тестовый метод", e);
        }
    }
}
