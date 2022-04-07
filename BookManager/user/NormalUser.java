package BookManager.user;

import java.util.Scanner;

import BookManager.operation.BorrowOperation;
import BookManager.operation.ExitOperation;
import BookManager.operation.FindOperation;
import BookManager.operation.IOperation;
import BookManager.operation.ReturnOperation;

public class NormalUser extends User{
    public NormalUser(String name) {
        super(name);

        this.operations = new IOperation[] {
            new ExitOperation(),
            new FindOperation(),
            new BorrowOperation(),
            new ReturnOperation(),
        };
    }
    @SuppressWarnings({"all"})
    @Override
    public int menu() {
        System.out.println("====================================");
        System.out.println("Holle"+this.name+"欢迎来到图书系统");
        System.out.println("1.查找图书");
        System.out.println("2.借阅图书");
        System.out.println("3.归还图书");
        System.out.println("0.退出系统");
        System.out.println("====================================");
        Scanner scanner = new Scanner(System.in);
        int choice= scanner.nextInt();

        return choice; 
    }
}
