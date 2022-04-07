package BookManager.operation;

import java.util.Scanner;

import BookManager.book.Book;
import BookManager.book.BookList;

public class FindOperation implements IOperation{

    @SuppressWarnings({"all"})
    @Override
    public void work(BookList bookList) {
        System.out.println("查找图书");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要查找的书籍");

        String name = scanner.nextLine();
        for (int i = 0; i < bookList.getUseSize(); i++) {
            Book book = bookList.getBook(i);

            if(book.getName().equals(name)) {
                System.out.println(book);
                System.out.println("找到了");
                return; 
            }
        }
        System.out.println("没有找到哦");
    }
}
