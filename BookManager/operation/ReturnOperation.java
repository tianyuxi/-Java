package BookManager.operation;

import java.util.Scanner;
import BookManager.book.Book;
import BookManager.book.BookList;

public class ReturnOperation implements IOperation{

    @SuppressWarnings({"all"})
    @Override
    public void work(BookList bookList) {
        System.out.println("归还图书");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要归还的图书");

        String name = scanner.nextLine();
        for (int i = 0; i < bookList.getUseSize(); i++) {
            Book book = bookList.getBook(i);

            if(book.getName().equals(name)) {
                System.out.println("归还成功");
                book.setBorrowed(false);
                return;
            }
        }
        System.out.println("未能查询找您要归还的书籍");
    }
}
