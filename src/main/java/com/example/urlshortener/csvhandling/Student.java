package com.example.urlshortener.csvhandling;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String dob;

    public Student(int id, String name, String dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }
}
