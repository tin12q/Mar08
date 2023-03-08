package com.example.client;

public class Student {
    private String MASV;
    private String name;
    private int lan = 2;
    public String getMASV() {
        return MASV;
    }

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Student(String MASV, String name) {
        this.MASV = MASV;
        this.name = name;
    }


    public int getLan() {
        return lan;
    }

    public void setLan(int lan) {
        this.lan = lan;
    }
}
