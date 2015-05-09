/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import DataBase.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Catalin
 */
public class Reader
{
    private String readerCNP;
    private String readerName;
    private int readerID;
    private int readerGroup;
    private int numberOfBooks;
    
    //Cannot use since the object dies after ending the application
    //private Book borrowedBooks[]=new Book[4];
    
    public void PrintReader()
    {
            if(readerID>0)
            {
                System.out.println(readerID+" "+readerCNP+" "+readerName+" "+readerGroup);
            }
            
            else
            {
                System.out.println("This reader doesnt exist book!");
                
            }
           
        
        
        
        
        //De finisat!!!
    }
    
    
    private Reader()
    {
        //Internal use
    }
    
    //Create new reader
    public Reader(int newID,String newCNP,String newName,int newGroup) throws SQLException
    {
        readerID=newID;
        readerName=newName;
        readerCNP=newCNP;
        readerGroup=newGroup;
        numberOfBooks=0;
        
        
        
        
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        
        
        //Insert into data base is next step
        try
        {
            String querry="INSERT INTO `library`.`readers` (`ReaderID`, `ReaderCNP`, `ReaderName`, `ReaderGroup`)"
                    + "VALUES ('"+readerID+"', '"+readerCNP+"', '"+readerName+"', '"+readerGroup+"');";
            statement.executeUpdate(querry);
            //Reader has been created
        }
        catch(Exception err)
        {
            System.out.println("The readerCNP, readerID or Both are used !");
            //Reader already exist !
            //System.out.println(err);
        }
        
        
    }
    
    
    
    
    //Deleation of reader
    //By ID
    public static void DeletReaderByID(int readerID) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        try
        {
            String querry;
            ResultSet resultSet;
            querry="SELECT * FROM readers WHERE readerID="+readerID;
            resultSet=statement.executeQuery(querry);
            
            if(resultSet.next())
            {
                querry = "DELETE FROM `library`.`readers` WHERE `readers`.`ReaderID` = "+readerID+";";
                statement.executeUpdate(querry);
            //Reader has been created
            } 
            else
            {
                System.out.println("This reader doesnt exist !");
            }
        }
        catch(Exception err)
        {
            System.out.println(err);
        }
        
    }
    
    //ByCNP
    public static void DeletReaderByCNP(String readerCNP) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        try
        {
            String querry;
            ResultSet resultSet;
            querry="SELECT * FROM readers WHERE readerCNP="+readerCNP;
            resultSet=statement.executeQuery(querry);
            
            if(resultSet.next())
            {
                querry = "DELETE FROM `library`.`readers` WHERE `readers`.`ReaderCNP` = "+readerCNP+";";
                statement.executeUpdate(querry);
            //Reader has been created
            } 
            else
            {
                System.out.println("This reader doesnt exist !");
            }
        }
        catch(Exception err)
        {
            System.out.println(err);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //Reader data extraction
    
      public static Reader ExtractReaderDatas(int readerID) throws SQLException
    {
        Reader extractedReader=new Reader();
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        
        
        
        try
        {
        String querry;
        ResultSet resultSet;
        querry="SELECT * FROM readers WHERE ReaderID = "+readerID;
        resultSet=statement.executeQuery(querry);    
                    
            
            
            while(resultSet.next())
            {
                    
            
                    int extractedReaderID=resultSet.getInt("ReaderID");
                    String extractedReaderCNP=resultSet.getString("ReaderCNP");
                    String extractedReaderName=resultSet.getString("ReaderName");
                    int extractedReaderGroup=resultSet.getInt("ReaderGroup");
                    extractedReader.SetReaderId(extractedReaderID);
                    extractedReader.SetReaderCNP(extractedReaderCNP);
                    extractedReader.SetReaderName(extractedReaderName);
                    extractedReader.SetReaderGroup(extractedReaderGroup);
                    
                   
                    
                    return extractedReader;
                    //other exception for this
                  
                
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Error : "+ex);
        }
        
            
            
               
                return extractedReader;
    }
    
    
    //Unfinished-------------TO FINISH AFTER ALL UPDATES ARE DONE-----------------------------------
    public static void BorrowBook(int readerID,int bookID)
    {
       
    }
    
    
    
    
    
    
    
    
    
    //Reader Update (Find by readerID)
    
    public static void UpdateReaderID(int readerID,int newReaderID) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        
        
        try
        {
            
            querry="SELECT ReaderID FROM readers where ReaderID="+readerID;
            resultSet=statement.executeQuery(querry);
            
            if(!resultSet.next())
            {
                System.out.println("This readerID doesn't exist !");
            }
             else  
            {
                querry="SELECT BookID FROM books where FK_ReaderID="+readerID;
                resultSet=statement.executeQuery(querry);
            
            
            
                if(!resultSet.next())
                {
                
                    
                    querry="UPDATE `library`.`readers` SET `ReaderID` = "+newReaderID+" WHERE `readers`.`ReaderID` ="+readerID+" ;";
                    statement.executeUpdate(querry);
                    System.out.println("ReaderID has been changed !");
                    
                    
              
                
                }
                else
                {
                System.out.println("Unable to cahnage the readerID while he has at leat one book !");
                }
            }
                    
          
            
        }
        catch(Exception ex)
        {
            System.out.println("This ID is already used !");
            
        }
    }
    
    public static void UpdateReaderCNP(int readerID,String newReaderCNP) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        
        
        try
        {
            
            querry="SELECT ReaderID FROM readers where ReaderID="+readerID;
            resultSet=statement.executeQuery(querry);
            
            if(!resultSet.next())
            {
                System.out.println("This readerID doesn't exist !");
            }
             else  
            {
                
                    
                    querry="UPDATE `library`.`readers` SET `ReaderCNP` = "+newReaderCNP+" WHERE `readers`.`ReaderID` ="+readerID+" ;";
                    statement.executeUpdate(querry);
                    System.out.println("ReaderID has been changed !");
                    
                
            }
                    
          
            
        }
        catch(Exception ex)
        {
            System.out.println("This CNP is already used !");
            
        }
    }
    
    public static void UpdateReaderName(int readerID,String newReaderName) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        
        
        try
        {
            
            querry="SELECT ReaderID FROM readers where ReaderID="+readerID;
            resultSet=statement.executeQuery(querry);
            
            if(!resultSet.next())
            {
                System.out.println("This readerID doesn't exist !");
            }
             else  
            {
                
                    
                    querry="UPDATE `library`.`readers` SET `ReaderName` = '"+newReaderName+"' WHERE `readers`.`ReaderID` = "+readerID+";";
                    statement.executeUpdate(querry);
                    System.out.println("ReaderID has been changed !");
                    
                
            }
                    
          
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            
        }
    }
    
    
    public static void UpdateReaderGroup(int readerID,int newReaderGroup) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        
        
        try
        {
            
            querry="SELECT ReaderID FROM readers where ReaderID="+readerID;
            resultSet=statement.executeQuery(querry);
            
            if(!resultSet.next())
            {
                System.out.println("This readerID doesn't exist !");
            }
             else  
            {
                
                    
                    querry="UPDATE `library`.`readers` SET `ReaderGroup` = "+newReaderGroup+" WHERE `readers`.`ReaderID` ="+readerID+" ;";
                    statement.executeUpdate(querry);
                    System.out.println("ReaderID has been changed !");
                    
                
            }
                    
          
            
        }
        catch(Exception ex)
        {
            System.out.println("Other errors !");
            
        }
    }
    
    
    
    //Reader Update (Find by readerCNP)
    
    public static void UpdateReaderID(String readerCNP,int newReaderId)
    {
        
    }
    
    public static void UpdateReaderCNP(String readerCNP,String newReaderCNP)
    {
        
    }
    
    public static void UpdateReaderName(String readerCNP,String newReaderName)
    {
        
    }
    
    
    public static void UpdateReaderGroup(String readerCNP,int newReaderGroup)
    {
        
    }
    
    
    
    
    
    
    
     //Get/Set
    
    
    //Get/Set readerID
    public void SetReaderId(int newID)
    {
        readerID=newID;
    }
    public int GetReaderID()
    {
        return readerID;
    }
    
    
    //Get/Set readerGroup
    public void SetReaderGroup(int newGroup)
    {
        readerGroup=newGroup;
    }
    public int GetReaderGroup()
    {
        return readerGroup;
    }
    
    
    
    //Get/Set readerCNP
    public void SetReaderCNP(String newCNP)
    {
        readerCNP=newCNP;
    }
    public String GetReaderCNP()
    {
        return readerCNP;
    }
    
    
    
    //Get/Set readerName
    public void SetReaderName(String newName)
    {
        readerName=newName;
    }
        
    public String GetReaderName()
    {
        return readerName;
    }
    
    public void SetReaderNumberOfBooks(int newNumberOfBooks)
    {
        numberOfBooks=newNumberOfBooks;
    }
    public int GetReaderNumberOfBooks()
    {
        return numberOfBooks;
    }
    
    
  
    
    
    
    
    
    
}
