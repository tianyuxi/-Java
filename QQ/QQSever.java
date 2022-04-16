package QQ;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1";// 登陆成功
    String MESSAGE_LOGIN_FAIL = "2";// 登陆失败
    String MESSAGE_COMM_MES = "3";// 普通的信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";// 要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";// 返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";// 客户端请求退出
    String MESSAGE_TO_ALL_MES = "7";//群发
    String MESSAGE_FILE_MES = "8";
}
class User implements Serializable{//表示一个用户信息
    private static final long serialVersionUID = 1L;//增加兼容性
    private String userId;
    private String password;
    
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
class Message implements Serializable{
    private static final long serialVersionUID = 1L;//增加兼容性
    private String sender;//发送者
    private String getter;//接收者
    private String content;//消息内容
    private String sendTime;//发送时间
    private String messageType;//消息类型

    private byte[] fileBytes;
    private int fileLen = 0;
    private String dest;//将文件传输到哪里
    private String src;//源文件


    public String getSender() {return sender;}
    public void setSender(String sender) {this.sender = sender;}
    public String getGetter() {return getter;}
    public void setGetter(String getter) {this.getter = getter;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public String getSendTime() {return sendTime;}
    public void setSendTime(String sendTime) {this.sendTime = sendTime;}
    public String getMessageType() {return messageType;}
    public void setMessageType(String messageType) {this.messageType = messageType;}

    public byte[] getFileBytes() {return fileBytes;}
    public void setFileBytes(byte[] fileBytes) {this.fileBytes = fileBytes;}
    public int getFileLen() {return fileLen;}
    public void setFileLen(int fileLen) {this.fileLen = fileLen;}
    public String getDest() {return dest;}
    public void setDest(String dest) {this.dest = dest;}
    public String getSrc() {return src;}
    public void setSrc(String src) {this.src = src;}
}

class ManageClientThreads {// 用于管理客户端通信的线程
    private static HashMap<String, ServerConnectClientThread> hashMap = new HashMap<>();

    public static HashMap<String,ServerConnectClientThread> getHashMap() {
        return hashMap;
    }

    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hashMap.put(userId, serverConnectClientThread);
    }

    public static ServerConnectClientThread getServerConnectThread(String userId) {
        return hashMap.get(userId);
    }

    public static void removeServerConnectThread(String userId) {
        hashMap.remove(userId);
    }

    public static String getOnlineUser() {
        Iterator<String> iterator = hashMap.keySet().iterator();
        String onlineUserList = "";
        while (iterator.hasNext()) {
            onlineUserList += iterator.next().toString() + " ";
        }
        return onlineUserList;
    }
}

class ServerConnectClientThread extends Thread {
    // 该类的一个对象和某个客户端保持通信
    private Socket socket;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("服务器的客户端" + userId + "保持通信...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                if (message.getMessageType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    System.out.print(message.getSender() + "要在线");
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    Message message2 = new Message();
                    message2.setMessageType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
                    message2.setContent(onlineUser);
                    message2.setGetter(message.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                } else if (message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)) {
                    ServerConnectClientThread serverConnectClientThread = ManageClientThreads
                            .getServerConnectThread(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(
                            serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);//转发

                } else if (message.getMessageType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    // 需要遍历管理线程的集合,把所有的线程的socket都得到,然后把message进行转发
                    HashMap<String, ServerConnectClientThread> hashMap = ManageClientThreads.getHashMap();
                    Iterator<String> iterator = hashMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        // 取出在线用户的id
                        String onlineUserId = iterator.next().toString();
                        if (!onlineUserId.equals(message.getSender())) {// 排除自己
                            ObjectOutputStream oos = new ObjectOutputStream(
                                    hashMap.get(onlineUserId).getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_FILE_MES)) {
                    ServerConnectClientThread serverConnectClientThread = ManageClientThreads.getServerConnectThread(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);
                }else if (message.getMessageType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println(message.getSender() + "退出系统");
                    ManageClientThreads.removeServerConnectThread(message.getSender());
                    socket.close();
                    break;
                } else {
                    System.out.println("暂时不处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class QQServer {
    private ServerSocket ss = null;
    // ConcurrentHashMap处理的线程安全,即线程同步处理,在多线程情况下是安全的
    private static ConcurrentHashMap<String, User> validUser = new ConcurrentHashMap<>();
    static {
        validUser.put("100", new User("100", "123456"));
        validUser.put("200", new User("200", "123456"));
        validUser.put("300", new User("300", "123456"));
        validUser.put("400", new User("400", "123456"));
        validUser.put("500", new User("500", "123456"));
        validUser.put("600", new User("600", "123456"));
    }

    public boolean checkUser(String userId, String password) {
        User user = validUser.get(userId);
        if (userId == null) {
            return false;
        }
        if (password == null) {
            return false;
        }
        if (!user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    public QQServer() {
        try {
            System.out.println("服务器在9999端口监听...");
            new Thread(new PushNews()).start();
            ss = new ServerSocket(9999);
            while (true) {// 监听是
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                User u = (User) ois.readObject();// 读取客户端发送的User对象
                Message message = new Message();

                if (checkUser(u.getUserId(), u.getPassword())) {
                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    // 将message对象回复
                    oos.writeObject(message);
                    // 创建一个线程,和客户端保持通信,该线程需要保持socket对象
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket,
                            u.getUserId());
                    serverConnectClientThread.start();
                    ManageClientThreads.addClientThread(u.getUserId(), serverConnectClientThread);

                } else {// 登陆失败
                    System.out.println("用户" + u.getUserId() + "登陆失败");
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 如果服务器退出了while,说明服务器不在监听,因此关闭ServerSocket
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class PushNews implements Runnable{
    @Override
    public void run() {
        while(true) {
            System.out.println("请输入服务器要推送的新闻/消息[输入exit退出]");
            String news = Utility.readString(100);
            if("exit".equals(news)) {
                break;
            }
            Message message = new Message();
            message.setSender("服务器");
            message.setMessageType(MessageType.MESSAGE_TO_ALL_MES);
            message.setContent(news);
            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人说： "+news);
            
            HashMap<String,ServerConnectClientThread> hashMap = ManageClientThreads.getHashMap();
            Iterator<String> iterator = hashMap.keySet().iterator();
            while(iterator.hasNext()) {
                String onlineUserId = iterator.next();
                ServerConnectClientThread serverConnectClientThread = hashMap.get(onlineUserId);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class QQSever {
    public static void main(String[] args) {
        new QQServer();
    }
}
