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
    private Book borrowedBooks[]=new Book[4];
    
    
    //Create new reader
    public Reader(int newID,String newCNP,String newName,int newGroup) throws SQLException
    {
        readerID=newID;
        readerName=newName;
        readerCNP=newCNP;
        readerGroup=newGroup;
        numberOfBooks=0;
        for(int i=1;i<4;i++)
        {
            borrowedBooks[i]=null;
        }
        
        
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
            System.out.println(err);
        }
        
        
    }
    
    //Delete reader by ID
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
    
    //Delete reader by CNP
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
    
    //Get readers books
    public Book[] GetBooks()
    {
        return borrowedBooks;
    }
    
    
    //Borrow/Return a book
    public void BorrowBook(Book newBook)
    {
        if(numberOfBooks<4)
        {
            borrowedBooks[numberOfBooks]=newBook;
            numberOfBooks++;
            
        }
        else
        {
            
            //You have exceed the maximum number of borrowed books
        }
            
    }

    public void ReturnBook(Book newBook)
    {
        boolean check=false;
        for(int i=1;i<=4;i++)
        {
            if(newBook.equals(borrowedBooks[i]))
            {
                check=true;
                
                while(borrowedBooks[i+1]!=null)
                {
                    borrowedBooks[i]=borrowedBooks[i+1];
                }
                borrowedBooks[i]=null;
                if(check==true)
                {
                    numberOfBooks--;
                }
                else
                {
                    //Something is wrong,this guy shouldnt has this book
                }
                
                break;
            }
           
        }
            
    }
    
    
}
