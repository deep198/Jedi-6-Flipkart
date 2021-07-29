package com.flipkart.helper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface CloseConnection {

    default public void closeConnection( Connection conn,PreparedStatement stmt){
        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se2){
        }
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
}