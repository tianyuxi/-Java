package BookManager.operation;

import java.util.Scanner;
import BookManager.book.Book;
import BookManager.book.BookList;

public class BorrowOperation implements IOperation{

    @SuppressWarnings({"all"})
    @Override
    public void work(BookList bookList) {
        System.out.println("借阅图书");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要借阅的书籍");

        String name = scanner.nextLine();
        for (int i = 0; i < bookList.getUseSize(); i++) {
            Book book = bookList.getBook(i);

            if(book.getName().equals(name)) {
                book.setBorrowed(true);
                System.out.println("借阅成功");
                return;     
            }
        }
        System.out.println("没有你要借阅的书籍");
    }
}
