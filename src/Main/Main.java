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
import java.sql.SQLException;

/**
 *
 * @author Catalin
 */
public class Main 
{
    public static void main(String[] args) throws SQLException 
    {
        //Connection conn =DB_Connection.InitializeConnection();
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
        Reader.UpdateReaderName(1, "Testing");
        //Reader.UpdateReaderGroup(1, 232);
        
        
        
    }
}
