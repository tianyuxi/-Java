package BookManager.user;

import BookManager.book.BookList;
import BookManager.operation.IOperation;

public abstract class User {
    protected String name;

    protected IOperation[] operations;

    public User(String name) {
        this.name = name;
    }

    public abstract int menu();
    
    public void DoOperation(BookList bookList,int choice) {
        this.operations[choice].work(bookList);
    }
}
