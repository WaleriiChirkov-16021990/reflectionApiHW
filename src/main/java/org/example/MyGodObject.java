package org.example;

import org.example.myAnnotations.AfterEach;
import org.example.myAnnotations.BeforeEach;
import org.example.myAnnotations.Test;

import java.lang.reflect.Field;

public class MyGodObject {
    private String name;
    public MyGodObject(String name) {
        this.name = name;
    }

    public MyGodObject() {
    }

    @BeforeEach
    public void before(){
        System.out.println("MyGodObject before");
    }

    @Test
    public void testOut(){
//        getLightning();
        System.out.println("MyGodObject testOut successful");
    }

    @AfterEach
    public void after() throws Exception {
        System.out.println("After");
    }

    public String getLightning() {
        System.out.println("this Lightning lit up the sky");
        return "☁ Туча ☁\n" +
                "☂ Зонтик ☂\n" +
                "ϟ Молния; ϟ\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyGodObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
