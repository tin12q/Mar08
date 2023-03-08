package com.example.client;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DBHelper {
    private final static String url = "jdbc:sqlserver://localhost;databaseName=diemdanh;encrypt=true;trustServerCertificate=true";
    private static final String usr = "sa";
    private static final String pss = "Abcd1234";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conn;
    public static LinkedList<Student> list = new LinkedList<>();
    public static Map<String, Integer> iDmap = new HashMap<>();

    private static void connection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usr, pss);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }
    public static void updateMap(){
        for (Student student : list) {
            iDmap.put(student.getMASV(), 2);
        }
    }
    public static void getData(){
        try {
            connection();
            CallableStatement cs = conn.prepareCall("{CALL getdata()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getSv(){
        try {
            connection();
            CallableStatement cs = conn.prepareCall("{CALL getsv()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Student student = new Student(rs.getString(1), rs.getString(2));
                list.add(student);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int[] getTime(String id){
        int[] time = new int[2];
        time[0] = 0;
        time[1] = 0;
        try {
            connection();
            CallableStatement cs = conn.prepareCall("{CALL getTime(?)}");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                time[0] = rs.getInt("phep");
                time[1] = rs.getInt("khong");
                return time;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return time;
    }
    public static void ghi(){
        try {
            connection();

            CallableStatement cs = conn.prepareCall("{CALL ghi(?,?,?)}");
            for (Student student : list) {
                if(iDmap.get(student.getMASV())==2) continue;
                cs.setString(1, student.getMASV());
                cs.setDate(2,new Date(System.currentTimeMillis()));
                cs.setInt(3, iDmap.get(student.getMASV()));
                cs.execute();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
