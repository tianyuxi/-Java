package BookManager.operation;

import BookManager.book.Book;
import BookManager.book.BookList;

public class DisplayOperation implements IOperation{

    @Override
    public void work(BookList bookList) {
        System.out.println("显示图书");

        for (int i = 0; i < bookList.getUseSize(); i++) {
            Book book = bookList.getBook(i);
            System.out.println(book);
        }
    }
}
