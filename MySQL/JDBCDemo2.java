import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Account {
    private int id;
    private String name;
    private String adress;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAdress() {return adress;}
    public void setAdress(String adress) {this.adress = adress;}

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
public class JDBCDemo2 {
    @Test
    //执行DML语句--对数据的增删改
    public void testDML() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);
        //定义sql
        String sql = "UPDATE emp set salary = 10000 WHERE id = 7";

        //获取执行sql的对象statement
        Statement statement = conn.createStatement();

        int count = statement.executeUpdate(sql);

        //处理结果
        //System.out.println(count1);
        if(count > 0) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        //释放资源
        statement.close();
        conn.close();
    }

    @Test
    //执行DDL语句--对数据的增删改
    public void testDDL() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);
        //定义sql
        String sql = "create database db02";
        //获取执行sql的对象statement
        Statement statement = conn.createStatement();
        int count = statement.executeUpdate(sql);
        //处理结果
        System.out.println(count);

        //释放资源
        statement.close();
        conn.close();
    }

    @Test
    //执行DQL语句--对数据的增删改
    public void testResultSet() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);
        //定义sql
        String sql = "select * from users";
        //获取执行sql的对象statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            String adress = resultSet.getString(3);

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String adress = resultSet.getString("adress");

            System.out.println(id);
            System.out.println(name);
            System.out.println(adress);
            System.out.println("----------");
        }
        resultSet.close();
        statement.close();
        conn.close();
    }
    @Test
    public void testResultSet2() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);
        //定义sql
        String sql = "select * from users";
        //获取执行sql的对象statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Account> list = new ArrayList<>();

        while (resultSet.next()) {
            Account account = new Account();


//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            String adress = resultSet.getString(3);

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String adress = resultSet.getString("adress");

            account.setId(id);
            account.setName(name);
            account.setAdress(adress);
            list.add(account);
        }
        System.out.println(list);

        resultSet.close();
        statement.close();
        conn.close();
    }

    @Test
    //执行DML语句--对数据的增删改
    public void testResultSet3() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);

        String name = "张三";
        String pwd = "123";

        String sql = "select * from tb_user where username= '"+name+"'and password='"+pwd+"'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登陆失败");
        }
        resultSet.close();
        statement.close();
        conn.close();
    }

    @Test//SQL注入
    public void testLogin_Inject() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);

        String name = "随便写";
        String pwd = "' or '1' = '1";

        String sql = "select * from tb_user where username= '"+name+"'and password='"+pwd+"'";
        System.out.println(sql);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登陆失败");
        }
        resultSet.close();
        statement.close();
        conn.close();
    }

    @Test//防止SQL注入
    public void testPreparedStatement() throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false&useSeverPrepStmts";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);

        String name = "随便写";
        String pwd = "' or '1' = '1";

        //String sql = "select * from tb_user where username= '"+name+"'and password='"+pwd+"'";
        String sql = "select * from tb_user where username= ? and password= ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,pwd);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登陆失败");
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
}
