package org.example;

import org.example.myAnnotations.AfterEach;
import org.example.myAnnotations.BeforeEach;
import org.example.myAnnotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

        for (Method method : object.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                checkTestMethod(method);
                method.setAccessible(true);
                runTest(method, objectByReflection);
            }
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
        if (!method.getReturnType().isAssignableFrom(void.class) && method.getParameterCount() != 0) {
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
