/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Catalin
 */
public class DB_Connection
{
    public static Connection InitializeConnection()
    {
        //Could demand username and password
        String username="root";
        String password="root";
        
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library",username,password);
            return connection; 
        }
        catch(ClassNotFoundException | SQLException error)
        {
            //System.out.println("Unable to connect to the databse!");
            return null;
        }
    }
    
}
