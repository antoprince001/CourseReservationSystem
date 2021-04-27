/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awtchecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
class Course{
    String courseID;
    String instructorID;
    String name;
    String aboutCourse;
    String startDate;
    String endDate;

    public Course(String courseID,String instructorID,String name,String aboutCourse,String startDate,String endDate) {
    this.courseID = courseID;
    this.instructorID= instructorID;
    this.name = name;
    this.aboutCourse = aboutCourse;
    this.startDate= startDate;
    this.endDate = endDate;  
    }
    
    public static boolean createCourse(String courseID,String instructorID,String name,String aboutCourse,String startDate,String endDate){
        try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
            PreparedStatement ps=(PreparedStatement) con.prepareCall(" INSERT INTO Courses VALUES(?,?,?,?,?,?)");
            ps.setString(1, courseID);
            ps.setString(2, instructorID);
            ps.setString(3, name);
            ps.setString(4, aboutCourse);
            ps.setString(5, startDate);
            ps.setString(6, endDate);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(Exception e){
         return false;   
        }
        
    }
    
    public static boolean updateCourse(String courseID,String instructorID,String name,String aboutCourse,String startDate,String endDate){
        
        try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
            PreparedStatement ps=(PreparedStatement) con.prepareCall(" UPDATE courses SET courseID = ?, instructorID = ?, name=?,aboutCourse=?,startDate=?,endDate=?");
            ps.setString(1, courseID);
            ps.setString(2, instructorID);
            ps.setString(3, name);
            ps.setString(4, aboutCourse);
            ps.setString(5, startDate);
            ps.setString(6, endDate);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(Exception e){
         return false;   
        }
    }
}
public class CatalogController {
    
    public static ArrayList<Course> viewCatalog(){
        ArrayList<Course> courses = new ArrayList<Course>();
         try {
                ResultSet rs;
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
                PreparedStatement ps = (PreparedStatement) con.prepareCall(" SELECT * FROM courses");
                rs = ps.executeQuery();
                while(rs.next()) {
                    courses.add(new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                }
                return courses;
         }catch(Exception e){
             return null;
         }
    }
    
    public boolean updateCatalog(){
        return true;
    }
    
    public Course searchCatalog(String courseID){
        try {
                ResultSet rs;
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
                PreparedStatement ps = (PreparedStatement) con.prepareCall(" SELECT * FROM courses where courseid = ?");
                ps.setString(1, courseID);
                rs = ps.executeQuery();
                if(rs.next()) {
                    return new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                }
                else{
                    return null;
                }

         }catch(Exception e){
             return null;
         }
    }
}
