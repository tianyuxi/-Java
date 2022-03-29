package game;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel2 extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20); 
    }

    @Override//有字符输出时,该方法就会触发
    public void keyTyped(KeyEvent e) {

    }

    @Override//当某个键按下,该方法会触发
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode()+"被按下");
        if(e.getKeyCode()==KeyEvent.VK_DOWN) {
            //System.out.print("移动");
            y++;
        }else if(e.getKeyCode()==KeyEvent.VK_UP) {
            y--;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            x--;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            x++;
        }
        this.repaint();//让面板重绘
    }

    @Override//当某个键释放,该方法会触发
    public void keyReleased(KeyEvent e) {
        
    }
}

public class BallMove extends JFrame {
    MyPanel2 mp = null;

    //构造器
    public BallMove() {
        mp = new MyPanel2();
        this.add(mp);
        this.setSize(400,300);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
}
