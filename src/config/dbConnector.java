/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author BOSS
 */
public class dbConnector {
    
    private Connection connect;
    public dbConnector(){
        try{
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/clester_db","root","");
        }catch(SQLException ex){
            System.out.println("Can't connect to database:"+ex.getMessage());
        }
    }
    
    public ResultSet getData(String sql) throws SQLException{
        Statement stmt = connect.createStatement();
        ResultSet rst = stmt.executeQuery(sql);
        return rst;
  
    }

    public int insertData(String sql){
        int result;
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Inserted Succesfully!");
            pst.close();
          result=1;   
        } catch(SQLException ex){
            System.out.println("Connection Error:"+ex);
            result=0;
             }
        return result;
    }   
    
    public void updateData(String sql){
        
     try{
      PreparedStatement pst = connect.prepareStatement(sql);
        int rowsUpdated = pst.executeUpdate();
            if(rowsUpdated>0){
                JOptionPane.showMessageDialog(null,"Data Updated Succesfully!");
            }else{
                System.out.println("Data Updated Failed!");
            }
            pst.close();
     }catch(SQLException ex){
         System.out.println("Connection error"+ex);
     
     }
        
    }
    
    public void deleteData(int id,String table,String table_id){
        try{
        PreparedStatement pst = connect.prepareStatement("DELETE FROM "+table+" WHERE "+table_id+"=?");
        pst.setInt(1,id);
        int rowsDeleted = pst.executeUpdate();
            if(rowsDeleted > 0){
            JOptionPane.showMessageDialog(null,"Deleted Succesfully!");
            }else{
                System.out.println("Deletion Failed");
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Connection Error"+ex);
        }
    
    }
    
  }
    

