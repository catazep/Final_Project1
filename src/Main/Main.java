/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Interface.HomePage;
import DataBase.DB_Connection;
import Interface.*;
import Library.Book;
import Library.Reader;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;



/**
 *
 * @author Catalin
 */
public class Main 
{
    public static void main(String[] args) throws SQLException 
    {
        //Connection connection = DB_Connection.InitializeConnection();
        
        //HomePage homePage = new HomePage();
        //homePage.setVisible(true);
        //Reader reader1=new Reader(3,"292038430018","Popescu Cristian",150);
        //Reader.DeletReaderByCNP("292038430018");
        //Reader.DeletReaderByID(1);
        
        
        //Book book1=new Book(1,"Test","Test","Test");
        //Book.DeletBook(97);
        
        //Reader reader1=new Reader(1,"292038430018","Test Reader",1);
        //Book book1=new Book(1,"Test","Test","Test");
        
        
        
        
        //Reader reader=Reader.ExtractReaderDatas(3);
        //reader.PrintReader();
        //Book.DeletBook(1);
        //Book book=Book.ExtractBookDatas(1);
        //book.PrintBook();
        
        //Book.UpdateBookID(100, 1);
        //Book.UpdateBookName(1, " Testing Name");
        //Book.UpdateBookAuthor(1, "Testing Author");
        //Book.UpdateBookType(1, "Testing Type");
        
        //Reader.UpdateReaderID(12, 1);
        //Reader.UpdateReaderCNP(1, "1450428430016");
        //Reader.UpdateReaderName(1, "Testing");
        //Reader.UpdateReaderGroup(1, 232);
        
        
        //Reader.UpdateReaderID("1450428430016", 1);
        //Reader.UpdateReaderCNP(1, "1450428430016");
        //Reader.UpdateReaderName(1, "Testing");
        //Reader.UpdateReaderGroup(1, 232);
        
        /*int bookID;
        int bookReaderID;
        String bookName;
        String bookAuthor;
        String bookType;
        Date borrowDate;
        Date exceedDate;
        
        
        ResultSet resultSet=Reader.SearchReaderByName("Georgescu Catalina");
        while(resultSet.next())
        {
                    bookID = resultSet.getInt("BookID");
                    bookReaderID = resultSet.getInt("FK_ReaderID");
                    bookName = resultSet.getString("BookName");
                    bookAuthor = resultSet.getString("BookAuthor");
                    bookType = resultSet.getString("BookType");
                    borrowDate = resultSet.getDate("BorrowDate");
                    exceedDate = resultSet.getDate("ExceedDate");
                    System.out.println(bookID+" "+bookReaderID+bookName+bookAuthor+bookType+borrowDate+exceedDate);
                    
                    
        }
        
        while(resultSet.next())
                {
                    int id = resultSet.getInt("readerID");
                    String cnp = resultSet.getString("readerCNP");
                    String name = resultSet.getString("readerName");
                    int group = resultSet.getInt("readerGroup");
                    System.out.println("ID : "+id);
                    System.out.println("CNP : "+cnp);
                    System.out.println("Name : "+name);
                    System.out.println("Group : "+group);
                    
                }
        */
        
        //Reader.BorrowBook(3,42);
        
        //Difference between w dates
        /*Date today=new Date();
        Date tommorow=new Date(today.getTime()+(1000 * 60 * 60 * 24));
        System.out.println(today);
        System.out.println(tommorow);
        
        System.out.println((tommorow.getTime()-today.getTime())/1000/60/60/24);
        */
        
        
        //Reader.ReturnBook(2);
        //Reader.BorrowBook(1, 4);
        //Book book1=new Book(1,"Test","Test","Test");
        
        Book.ListOfLateBooks();
        
    }
}
