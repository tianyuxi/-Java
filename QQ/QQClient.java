package QQ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1";//登陆成功
    String MESSAGE_LOGIN_FAIL = "2";//登陆失败
    String MESSAGE_COMM_MES = "3";//普通的信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";//要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";//客户端请求退出
    String MESSAGE_TO_ALL_MES = "7";//群发
    String MESSAGE_FILE_MES = "8";
}
class User implements Serializable{//表示一个用户信息
    private static final long serialVersionUID = 1L;//增加兼容性
    private String userId;
    private String password;

    public User() {}
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
    private String sender;
    private String getter;
    private String content;
    private String sendTime;
    private String messageType;

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
class Chat {
    public void sendMessageToOne(String content,String senderId,String getterId) {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_COMM_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId+" 对 "+getterId+" 说 "+content);
        try {
            ObjectOutputStream oos=new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessageToAll(String content,String senderId) {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId+" 对大家说 "+content);
        try {
            ObjectOutputStream oos=new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ManageClientConnectServerThread {
    //把多个线程放入一个HashMap集合,key就是用户id,value就是线程
    private static HashMap<String,ClientConnectServerThread> hashMap = new HashMap<>();
    //将某个线程加入到集合
    public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread ) {
        hashMap.put(userId, clientConnectServerThread); 
    }
    //通过userId可以得到对应线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hashMap.get(userId);
    }
}
class ClientConnectServerThread extends Thread{
    //创建一个和服务区保持通信的线程,持有socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("客户端线程,等待从服务器发送的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();// 如果服务器没有发送Message对象,线程会阻塞在这里
                if (message.getMessageType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\n===当前在线用户列表===");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户:" + onlineUsers[i]);
                    }
                } else if (message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n"+new Date().toString()+"--"+message.getGetter() + "说" + message.getContent());
                } else if(message.getMessageType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    System.out.println("\n"+new Date().toString()+"--"+message.getSender()+"对大家说: "+message.getContent());
                }else if(message.getMessageType().equals(MessageType.MESSAGE_FILE_MES)) {
                    System.out.println("\n"+new Date().toString()+"--"+message.getSender()+"给"+message.getGetter()+"发送文件"+
                    message.getSrc()+"到我的电脑的目录"+message.getDest());
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("\n保存文件成功");
                }else {
                    System.out.println("是其他类型的message");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class UserClientServer {
    private User u = new User();//在其他地方可能使用User信息
    private Socket socket;
    public boolean checkUser(String userId,String password) {
        boolean b = false;
        u.setUserId(userId);//创建User对象
        u.setPassword(password);
        try {
            //连接到服务器
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message)ois.readObject();
            if(ms.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                b = true;
            } else {
                //登录失败,线程没有启动,关闭socket
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return b;
    }
    public void onlineFriendList() { 
        
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());
        try {
            //从管理线程的集合中,通过userId,得到这个线程
            ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId());
            Socket socket = clientConnectServerThread.getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);//发送message对象,先服务端要求在线用户列表
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logout() {
        //退出客户端,并给服务器发送一个退出系统的message对象
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());//指定哪一个客户端ID
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserId()+"退出了系统");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class FileClientServer {
    public void sendFileToOne(String src,String dest,String senderId,String getterId) {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);
            message.setFileBytes(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //提示信息
        System.out.println("\n"+senderId+" 给 "+getterId+"发送文件"+src+"到对方电脑"+dest);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
class QQView {
    private boolean loop = true;
    private String key = "";//接收用户的键盘输入
    private UserClientServer userClientServer = new UserClientServer();//用于登录服务器
    private Chat chat = new Chat();//对象用户私聊
    private FileClientServer fileClientServer = new FileClientServer();

    public void mainMenu() {
        while(loop) {
            System.out.println("==========欢迎登陆网络通信系统==========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择:");
            key = Utility.readString(1);
            switch(key) {
                case "1":
                    System.out.println("请输入你的账号");
                    String userId = Utility.readString(50);
                    System.out.println("请输入密码:");
                    String password = Utility.readString(50);
                    if (userClientServer.checkUser(userId, password)) {
                        System.out.println("==========欢迎"+userId+"==========");
                        while(loop) {
                            System.out.println("\n====网络通信系统二级菜单(用户"+userId+")===");
                            System.out.println("\t\t 1显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.println("请输入你的选择");
                            key = Utility.readString(1);
                            switch(key) {
                                case "1":
                                    userClientServer.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.print("输入群聊内容: ");
                                    String s = Utility.readString(100);
                                    chat.sendMessageToAll(s, userId);
                                    break;
                                case "3":
                                    System.out.print("请输入要私聊的用户名称(在线)");
                                    String getterId = Utility.readString(50);
                                    System.out.print("输入发送内容: ");
                                    String content = Utility.readString(100);
                                    chat.sendMessageToOne(content, userId, getterId);
                                    break;
                                case "4":
                                    System.out.println("请输入你想把文件发送给的用户");
                                    getterId = Utility.readString(50);
                                    System.out.println("请输入发送文件的目录");
                                    String src = Utility.readString(100);
                                    System.out.println("请输入对方的路径");
                                    String dest = Utility.readString(100);
                                    fileClientServer.sendFileToOne(src, dest, userId, getterId);
                                    break;
                                case "9":
                                    //调用方法给服务器发送一个退出系统的message
                                    userClientServer.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登陆失败");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}

public class QQClient {
    public static void main(String[] args) {
        new QQView().mainMenu();
    }
}

class Utility {
    private static Scanner scanner = new Scanner(System.in);
    private static String readKeyBoard(int limit, boolean blankReturn) {
		//定义了字符串
		String line = "";
		//scanner.hasNextLine() 判断有没有下一行
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();//读取这一行
			//如果line.length=0, 即用户没有输入任何内容，直接回车
			if (line.length() == 0) {
                if (blankReturn) return line;//如果blankReturn=true,可以返回空串
                else continue; //如果blankReturn=false,不接受空串，必须输入内容
            }
			//如果用户输入的内容大于了 limit，就提示重写输入  
			//如果用户如的内容 >0 <= limit ,我就接受
            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不能大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }
        return line;
    }
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }
}