package com.example.BookShopApp;
import com.example.BookShopApp.Book;
import com.example.BookShopApp.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookService 
{
    @Autowired
    private BookRepository bookRepository;
    public Book getBookById(int id) 
    {
        return bookRepository.findById(id).orElse(null);
    }
    public void updateBookQuantity(int bookId, int quantityToBuy)
    {
        Book book = getBookById(bookId);
        if (book != null && book.getQuantity() >= quantityToBuy) 
        {
            book.setQuantity(book.getQuantity() - quantityToBuy);
            bookRepository.save(book);
        } 
        else 
        {
            throw new RuntimeException("Insufficient quantity or book not found");
        }
        
    }
}
	
