package bit;

class UserError extends Exception {

    public UserError(String message) {
    super(message);
    }
}

class PasswordError extends Exception {

    public PasswordError(String message) {
    super(message);
    }
} 

public class TestDemo9 {
    public static void login(String userName, String password) throws UserError,PasswordError {
    if (!TestDemo9.userName.equals(userName)) {
        throw new UserError("用户名错误");
    }
    if (!TestDemo9.password.equals(password)) {
        throw new PasswordError("密码错误");
    }
    System.out.println("登陆成功");
    } 

    private static String userName = "admin";
    private static String password = "tianyuxi";
  
       

    public static void main(String[] args) {
        try {
            login("admin", "tianyux");
            } catch (UserError userError) {
            userError.printStackTrace();
            } catch (PasswordError passwordError) {
            passwordError.printStackTrace();
            }

    }
}
