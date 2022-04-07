package BookManager;

import java.util.Scanner;

import BookManager.book.BookList;
import BookManager.user.Administrator;
import BookManager.user.NormalUser;
import BookManager.user.User;
@SuppressWarnings({"all"})

public class TestBook {
    public static User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名");
        String name = scanner.nextLine();
        System.out.println("请输入你的身份:1--管理员,2--普通用户");
        int choice = scanner.nextInt();

        if(choice == 1) {
            return new Administrator(name);
        }else {
            return new NormalUser(name);
        }  
    }
    public static void main(String[] args) {
        BookList bookList = new BookList();//准备书籍
        //登录
        User user = login();
        while(true) {
            int choice = user.menu();

            user.DoOperation(bookList, choice);
        }
    }
}