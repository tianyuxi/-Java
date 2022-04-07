package BookManager.user;

import java.util.Scanner;
import BookManager.operation.AddOperation;
import BookManager.operation.DelOperation;
import BookManager.operation.DisplayOperation;
import BookManager.operation.ExitOperation;
import BookManager.operation.FindOperation;
import BookManager.operation.IOperation;

public class Administrator extends User{
    public Administrator(String name) {
        super(name);
        this.operations = new IOperation[] {
            new ExitOperation(),
            new FindOperation(),
            new AddOperation(),
            new DelOperation(),
            new DisplayOperation(),
        };
    }
    @SuppressWarnings({"all"})
    @Override
    public int menu() {
        System.out.println("====================================");
        System.out.println("Holle"+this.name+"欢迎来到图书系统");
        System.out.println("1.查找图书");
        System.out.println("2.借阅图书");
        System.out.println("3.删除图书");
        System.out.println("4.显示所有图书");
        System.out.println("0.退出系统");
        System.out.println("====================================");
        Scanner scanner = new Scanner(System.in);
        int choice= scanner.nextInt();

        return choice;
        
    }
}
