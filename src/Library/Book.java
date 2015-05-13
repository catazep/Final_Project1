/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import DataBase.DB_Connection;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Catalin
 */
public class Book
{
    //To update 
    private int bookID;
    private int readerOfBookID;
    private String bookName;
    private String bookAuthor;
    private String bookType;
    private Date borrowDate;
    private Date exceedDate;
    
    public void PrintBook()
    {
            if(bookID>0)
            {
                System.out.println(bookID+" "+readerOfBookID+" "+bookName+" "+bookAuthor+" "+bookType+" "+borrowDate+" "+exceedDate);
            }
            
            else
            {
                System.out.println("This book doesnt exist book!");
                
            }
           
        
        
        
        
        //De finisat!!!
    }
    
    
    private Book()
    {
        
    }
    
    
    //Create new book
    public  Book(int newID,String newName,String newAuthor,String newType) throws SQLException 
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
            System.out.println("The book has been added to the library !");
            //Book has been inserted
        }
        catch(Exception err)
        {
            System.out.println("This book already exist !");//Based on duplicate foreign key SQLException
            //could occur other errors than book exist
            //System.out.println(err);
        }
        
    }
    
    
    
   
    
    
    
    
    
    
    
    //Deletation of books
    
    //By ID
    public static void DeletBook(int bookID) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        
        try
        {
            String querry;
            ResultSet resultSet;
            
            
            querry="SELECT FK_ReaderID FROM books WHERE BookID="+bookID;
            resultSet=statement.executeQuery(querry);
            int cheker=0;
            while(resultSet.next())
            {
                cheker=resultSet.getInt("FK_ReaderID");
            }
            if(cheker!=0)
            {
                System.out.println("Unable to remove a borrowed book !");
            }
            else
            {
                querry="SELECT * FROM books WHERE bookID="+bookID;
            resultSet=statement.executeQuery(querry);
            
            if(resultSet.next())
            {
                querry = "DELETE FROM `library`.`books` WHERE `books`.`bookID` = "+bookID+";";
                statement.executeUpdate(querry);
                System.out.println("The book has been removed !");
            
            } 
            else
            {
                System.out.println("This book doesn`t exist !");
            }
            }
               
            
        }
        catch(Exception err)
        {
            System.out.println(err);
        }
        
    }
    
    
    
    //Extracting book datas by ID
    public static Book  ExtractBookDatas(int bookID) throws SQLException
    {
        Book extractedBook=new Book();
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        querry="SELECT * FROM books WHERE bookID="+bookID;
        resultSet=statement.executeQuery(querry); 
        if(!resultSet.next())
        {
            System.out.println("No bookID !");
        }
        else
        {
            try
        {
        
        querry="SELECT * FROM books WHERE bookID = "+bookID;
        resultSet=statement.executeQuery(querry);    
                    
            
            
            while(resultSet.next())
            {
                    
            
                    int extractedBookID = resultSet.getInt("bookID");
                    int extractedReaderOfBookID = resultSet.getInt("FK_ReaderID");
                    String extractedBookName = resultSet.getString("BookName");
                    String extractedBookAuthor = resultSet.getString("BookAuthor");
                    String extractedBookType = resultSet.getString("BookType");
                    Date extractedBorrowDate = resultSet.getDate("BorrowDate");
                    Date extractedExceedDate = resultSet.getDate("ExceedDate");
                    
                    //System.out.println(extractedBookID+" "+extractedReaderOfBookID+" "+extractedBookName+" "+extractedBookAuthor+
                            //" "+extractedBookType+" "+extractedBorrowDate+" "+extractedExceedDate);
                    
                    extractedBook.SetBookId(extractedBookID);
                    extractedBook.SetReaderOfBookID(extractedReaderOfBookID);
                    extractedBook.SetBookName(extractedBookName);
                    extractedBook.SetBookAuthor(extractedBookAuthor);
                    extractedBook.SetBookType(extractedBookType);
                    extractedBook.SetBorrowDate(extractedBorrowDate);
                    extractedBook.SetExceedDate(extractedExceedDate);
                    
                    return extractedBook;
                    //other exception for this
                  
                
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Error : "+ex);
        }
        }
        
        
            
            
               
                return extractedBook;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Book Update (finding by bookID)
    
    //Update bookID 
    public static void UpdateBookID(int bookID,int newBookID) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        try
        {
            
            querry="SELECT BookID,FK_ReaderID FROM books where BookID="+bookID;
            resultSet=statement.executeQuery(querry);
            
            int readerOfTheBookID=0;
            int theBookID=0;
            
            if(resultSet.next())
            {
                theBookID=resultSet.getInt("BookID");
                readerOfTheBookID=resultSet.getInt("FK_ReaderID");
                
            }
            
            
            if(readerOfTheBookID==0&&theBookID!=0)
            {
                    
                    querry="UPDATE `library`.`books` SET `BookID` = "+newBookID+" WHERE `books`.`BookID` ="+bookID+" ;";
                    statement.executeUpdate(querry);
                    System.out.println("BookID has been changed !");
                    
                
            }
            else
            {
                
                if(theBookID==0)
                {
                    System.out.println("This ID of book is invalid !");
                }
                else if(readerOfTheBookID!=0)
                {
                    System.out.println("Unable to update book ID while it is lent !");
                    System.out.println("The book is lent to reader with ID = "+readerOfTheBookID);
                }
                    
                    
                
            }
            
                
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            
        }
        
        
    }
    
    //Update bookName
    public static void UpdateBookName(int bookID,String newBookName) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        try
        {
            
            querry="SELECT BookID FROM books where BookID="+bookID;
            resultSet=statement.executeQuery(querry);
            
            
            int theBookID=0;
            
            if(resultSet.next())
            {
                theBookID=resultSet.getInt("BookID");
                
                
            }
            
            if(theBookID!=0)
            {
                    
                    querry="UPDATE `library`.`books` SET `BookName` = '"+newBookName+"' WHERE `books`.`BookID` = "+bookID+";";
                    statement.executeUpdate(querry);
                    System.out.println("BookName has been changed !");
                    
                
            }
            else
            {
                
                if(theBookID==0)
                {
                    System.out.println("The book ID has been not found !");
                }
                
                    
                    
                
            }
            
                
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            
        }
    }
    
    //Update bookAuthor
    public static void UpdateBookAuthor(int bookID,String newBookAuthor) throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        try
        {
            
            querry="SELECT BookID FROM books where BookID="+bookID;
            resultSet=statement.executeQuery(querry);
            
            
            int theBookID=0;
            
            if(resultSet.next())
            {
                theBookID=resultSet.getInt("BookID");
                
                
            }
            
            if(theBookID!=0)
            {
                    
                    querry="UPDATE `library`.`books` SET `BookAuthor` = '"+newBookAuthor+"' WHERE `books`.`BookID` = "+bookID+";";
                    statement.executeUpdate(querry);
                    System.out.println("BookAuthor has been changed !");
                    
                
            }
            else
            {
                
                if(theBookID==0)
                {
                    System.out.println("The book ID has been not found !");
                }
                
                    
                    
                
            }
            
                
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            
        }
    }
    
    //update bookType
    public static void UpdateBookType(int bookID,String newBookType) throws SQLException
    {
       Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        String querry;
        ResultSet resultSet;
        
        try
        {
            
            querry="SELECT BookID FROM books where BookID="+bookID;
            resultSet=statement.executeQuery(querry);
            
            
            int theBookID=0;
            
            if(resultSet.next())
            {
                theBookID=resultSet.getInt("BookID");
                
                
            }
            
            if(theBookID!=0)
            {
                    
                    querry="UPDATE `library`.`books` SET `BookType` = '"+newBookType+"' WHERE `books`.`BookID` = "+bookID+";";
                    statement.executeUpdate(querry);
                    System.out.println("BookType has been changed !");
                    
                
            }
            else
            {
                
                if(theBookID==0)
                {
                    System.out.println("The book ID has been not found !");
                }
                
                    
                    
                
            }
            
                
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            
        } 
    }
    
    
    
    
    
    
    //Searching books
    
    //By bookID
       public static ResultSet SearchBookByID(int bookID) throws SQLException 
    {
        String querry="SELECT * FROM books WHERE bookID = "+bookID+" ;";
        
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(querry);
        
        return resultSet;
            
        
        
    }
       
    //By bookName
    public static ResultSet SearchBookByName(String bookName) throws SQLException
    {
        String querry="SELECT * FROM books WHERE bookName =\""+bookName+"\" ;";
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(querry);
        return resultSet;
        
        
        
        
    }
    
    //By bookAuthor
    public static ResultSet SearchBookByAuthor(String bookAuthor) throws SQLException
    {
        String querry="SELECT * FROM `library`.`books` WHERE bookAuthor = \""+bookAuthor+"\" ;";
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(querry);
       
            return resultSet;
        
    }
    
    
    
    //Return a ResultSet of book which have exceeded the limit time
    public static ResultSet ListOfLateBooks() throws SQLException
    {
        Connection connection=DB_Connection.InitializeConnection();
        Statement statement=connection.createStatement();
        String querry="SELECT * FROM `books` WHERE DATEDIFF(SYSDATE(),BorrowDate) > 0";
        //Optional varable
        
        
        
        ResultSet resultSet = statement.executeQuery(querry);
        
        /*
        while(resultSet.next())
        {
            System.out.print(resultSet.getInt("BookID")+"               ");
            System.out.print(resultSet.getString("BookName")+"               ");
            System.out.print(resultSet.getString("BookAuthor")+"               ");
            System.out.print(resultSet.getString("BookType")+"               ");
            System.out.println();
        }
                */
        //System.out.println(resultSet);
        return resultSet;
    }
    
    
    
    
    
    
    //Get/Set
    //Get/Set bookID
    public void SetBookId(int setID)
    {
        //System.out.println(setID);
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
        exceedDate=newDate;
    }
        
    public Date GetExceedDate()
    {
        return exceedDate;
    }
}
