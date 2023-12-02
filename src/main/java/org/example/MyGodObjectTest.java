package org.example;

import org.example.myAnnotations.AfterEach;
import org.example.myAnnotations.BeforeEach;
import org.example.myAnnotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MyGodObjectTest {
    private Object myObject;

    public MyGodObjectTest() {
    }

    public Object getMyObject() {
        return myObject;
    }

    public void setMyObject(MyGodObject myObject) {
        this.myObject = myObject;
    }

    @BeforeEach
    public void before(){
//        MyGodObjectTest myObjectTEst;
//
//        try {
//                myObjectTEst = this.getClass().getConstructor().newInstance();
//            this.myObject.getClass().getConstructor(String.class).setAccessible(true);
//
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(this.myObject.toString());
        System.out.println("MyGodObjectTest created with constructor BeforeEach");
    }

    @Test
    public void testOut(){
        System.out.println("MyGodObjectTest testOut successful order default");
    }

    @Test(order = 1)
    public void testOut1(){
        System.out.println("MyGodObjectTest testOut successful order 1");
    }
    @Test(order = 2)
    public void testOut2(){
        System.out.println("MyGodObjectTest testOut successful order 2");
    }
    @Test(order = -5)
    public void testOut_5(){
        System.out.println("MyGodObjectTest testOut successful order -5");
    }
    @AfterEach
    public void after(){
//        Field declaredField;
//        try {
//            declaredField = Class.class.getDeclaredField("name");
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//        declaredField.setAccessible(true);
//        try {
//            declaredField.set(this.myObject, "Goddess Guerra");
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("AfterEach for myGodObjectTest");

    }

    public void setMyObject(Object myObject) {
        this.myObject = myObject;
    }
}
