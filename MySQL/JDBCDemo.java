import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db01?useSSL=false";
        String username = "root";
        String password = "2000";
        Connection conn = DriverManager.getConnection(url,username,password);
        //定义sql
        String sql1 = "UPDATE emp set salary = 10000 WHERE id = 7";
        String sql2 = "UPDATE emp set salary = 10000 WHERE id = 8";
        //获取执行sql的对象statement
        Statement statement = conn.createStatement();
        try {
            conn.setAutoCommit(false);//开启事务
            //执行sql
            int count1 = statement.executeUpdate(sql1);
            int count2 = statement.executeUpdate(sql2);

            //处理结果
            System.out.println(count1);
            System.out.println(count2);
            conn.commit();//提交事务
        }catch (Exception throwables) {
            conn.rollback();//回滚事务
            throwables.printStackTrace();
        }
        //释放资源
        statement.close();
        conn.close();
    }
}
