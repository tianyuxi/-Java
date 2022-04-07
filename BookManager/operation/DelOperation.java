package BookManager.operation;

import java.util.Scanner;
import BookManager.book.Book;
import BookManager.book.BookList;

public class DelOperation implements IOperation{

    @SuppressWarnings({"all"})
    @Override
    public void work(BookList bookList) {
        System.out.println("删除图书");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的书籍");
        String name = scanner.nextLine();

        int i = 0;
        for (; i < bookList.getUseSize(); i++) {
            Book book = bookList.getBook(i);
            if(book.getName().equals(name)) {
                break;
            }
            if(i == bookList.getUseSize()) {
                System.out.println("未能查询到您要删除的书籍");
                return;
            }

            for(int pos = i;pos < bookList.getUseSize()-1;pos++) {
                // book = bookList.getBook(pos+1);
                // bookList.setBook(pos, book);
                bookList.setBook(pos, bookList.getBook(pos+1));
            }
            bookList.setUseSize(bookList.getUseSize()-1);
            System.out.println("删除成功");
        }
    }
}
