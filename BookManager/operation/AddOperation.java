package BookManager.operation;

import java.util.Scanner;
import BookManager.book.Book;
import BookManager.book.BookList;

public class AddOperation implements IOperation{
    @SuppressWarnings({"all"})
    @Override
    public void work(BookList bookList) {
        System.out.println("新增图书");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入书名");
        String name = scanner.nextLine();
        System.out.println("请输入图书的作者");
        String author = scanner.nextLine();
        System.out.println("请输入图书的价格");
        int price = scanner.nextInt();
        System.out.println("请输入图书的类型");
        String type = scanner.next();

        Book book = new Book(name, author, price, type);

        int curSize = bookList.getUseSize();
        bookList.setBook(curSize,book);

        bookList.setUseSize(curSize+1);

        System.out.println("新增成功");

        
    }
    
}
