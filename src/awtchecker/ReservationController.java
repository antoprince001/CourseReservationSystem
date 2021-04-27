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
 class Reserve {
    String courseID;
    String reservationID;
    String studentID;
    String reservedDate;
  

    public Reserve(String reservationID,String courseID,String studentID,String reservedDate) {
    this.courseID = courseID;
    this.reservationID = reservationID;
    this.studentID = studentID;
    this.reservedDate= reservedDate;
    }
    
    public static boolean createReserve(String courseID,String reservationID,String studentID,String reservedDate){
        try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
            PreparedStatement ps=(PreparedStatement) con.prepareCall(" INSERT INTO reservation VALUES(?,?,?,?)");
            ps.setString(1, reservationID);
            ps.setString(2, courseID);
            ps.setString(3, studentID);
            ps.setString(4, reservedDate);
           
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(Exception e){
          
            System.out.println(e.toString());
         return false;   
        }
        
    }
    
    public static boolean DeleteReserve(String courseID,String instructorID,String name,String aboutCourse,String startDate,String endDate){
        
//        try{
//            
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//            Connection con=(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
//            PreparedStatement ps=(PreparedStatement) con.prepareCall(" UPDATE courses SET courseID = ?, instructorID = ?, name=?,aboutCourse=?,startDate=?,endDate=?");
//            ps.setString(1, courseID);
//            ps.setString(2, instructorID);
//            ps.setString(3, name);
//            ps.setString(4, aboutCourse);
//            ps.setString(5, startDate);
//            ps.setString(6, endDate);
//            ps.executeUpdate();
//            ps.close();
//            return true;
//        }catch(Exception e){
//         return false;   
//        }
        return false;
    }
}
public class ReservationController {
    
    public static ArrayList<Reserve> myReservations(String studentID){
        ArrayList<Reserve> reserves = new ArrayList<Reserve>();
         try {
                ResultSet rs;
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
                PreparedStatement ps = (PreparedStatement) con.prepareCall(" SELECT * FROM reservation where studentID = ?");
                ps.setString(1, studentID);
                rs = ps.executeQuery();
                while(rs.next()) {
                    reserves.add(new Reserve(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                }
                return reserves;
         }catch(Exception e){
             return null;
         }
    }
    
   
    
    public static Reserve searchReservationByID(String reservationID){
        try {
                ResultSet rs;
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
                PreparedStatement ps = (PreparedStatement) con.prepareCall(" SELECT * FROM reservation where reservationID = ?");
                ps.setString(1, reservationID);
                rs = ps.executeQuery();
                if(rs.next()) {
                    return new Reserve(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                }
                else{
                    return null;
                }

         }catch(Exception e){
             return null;
         }
    }
    
    
    public ArrayList<Reserve> searchReservationByCourse(String courseID){
        
        ArrayList<Reserve> reserves = new ArrayList<Reserve>();

        try {
                ResultSet rs;
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/OOAD", "anton", "password");
                PreparedStatement ps = (PreparedStatement) con.prepareCall(" SELECT * FROM reservation where courseID = ?");
                ps.setString(1, courseID);
                rs = ps.executeQuery();
                while(rs.next()) {
                    reserves.add(new Reserve(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                }
                return reserves;

         }catch(Exception e){
             return null;
         }
    }
    
    

}
