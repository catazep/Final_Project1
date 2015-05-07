/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import DataBase.DB_Connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Catalin
 */
public class Book
{
    
    private int bookID;
    private int readerOfBookID;
    private String bookName;
    private String bookAuthor;
    private String bookType;
    Date borrowDate;
    Date exceedDate;
    
    
    //Create new book
    public Book(int newID,String newName,String newAuthor,String newType) throws SQLException 
    {
        bookID=newID;
        bookName=newName;
        bookAuthor=newAuthor;
        bookType=newType;
        
        
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        
        
        //Insert into data base 
        try
        {
            String querry="INSERT INTO `library`.`books` (`BookID`, `FK_ReaderID`, `BookName`, `BookAuthor`, `BookType`, `BorrowDate`, `ExceedDate`) "
                    + "VALUES ('"+bookID+"', NULL, '"+bookName+"', '"+bookAuthor+"', '"+bookType+"', NULL, NULL);";
            statement.executeUpdate(querry);
            //Book has been inserted
        }
        catch(Exception error)
        {
            System.out.println(error);
        }
        
    }
    
    //Delete book by bookID
    public static void DeletBook(int bookID) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        try
        {
            String querry;
            ResultSet resultSet;
            querry="SELECT * FROM books WHERE bookID="+bookID;
            resultSet=statement.executeQuery(querry);
            
            if(resultSet.next())
            {
                querry = "DELETE FROM `library`.`books` WHERE `books`.`bookID` = "+bookID+";";
                statement.executeUpdate(querry);
            //Reader has been created
            } 
            else
            {
                System.out.println("This book doesnt exist !");
            }
        }
        catch(Exception err)
        {
            System.out.println(err);
        }
        
    }
    
    
    
    //Get/Set bookID
    public void SetBookId(int setID)
    {
        bookID=setID;
    }
    public int GetBookID()
    {
        return bookID;
    }
    
    
    
    //Get/Set readerOfBookIDGroup
    public void SetReaderOfBookID(int newReader)
    {
        readerOfBookID=newReader;
    }
    public int GetReaderOfBook()
    {
        return readerOfBookID;
    }
    
    
    
    //Get/Set BookName
        public void SetBookName(String newName)
    {
        bookName=newName;
    }
    public String GetBookName()
    {
        return bookName;
    }
    
    
    
    //Get/Set bookAuthor
    public void SetBookAuthor(String setAuthor)
    {
        bookAuthor=setAuthor;
    }
        
    public String GetBookAuthor()
    {
        return bookAuthor;
    }
    
    
    //Get/Set bookType
    public void SetBookType(String setType)
    {
        bookType=setType;
    }
        
    public String GetBookType()
    {
        return bookType;
    }
    
    
    
    //Get/Set borrow date
    public void SetBorrowDate(Date newDate)
    {
        borrowDate=newDate;
    }
        
    public Date GetBorrowDate()
    {
        return borrowDate;
    }
    
    
    
    //Get/Set exceed date
    public void SetExceedDate(Date newDate)
    {
        borrowDate=newDate;
    }
        
    public Date GetExceedwDate()
    {
        return exceedDate;
    }
}
