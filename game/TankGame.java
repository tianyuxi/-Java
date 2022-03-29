package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed = 1;

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}

    public int getDirection() {return direction;}
    public void setDirection(int direction) {this.direction = direction; }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
 
    public void moveUp()    {y-=this.speed;}
    public void moveRight() {x+=this.speed;}
    public void moveDown()  {y+=this.speed;}
    public void moveLeft()  {x-=this.speed;}
}
class Hero extends Tank {//自己的坦克
    public Hero(int x,int y) {
        super(x, y);
    }
}
class Peace extends Tank {
    public Peace(int x, int y) {
        super(x, y);
    }
}
class MyPanel extends JPanel implements KeyListener {//坦克大战的绘图区域
    Hero hero = null;
    Peace peace = null;
    public MyPanel() {
        hero = new Hero(100,100);//初始化自己的坦克
        hero.setSpeed(20);

        peace = new Peace(100, 100);
        peace.setSpeed(20);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形,默认为黑色

        drawTank(hero.getX(),hero.getY(),g,hero.getDirection(),0);//青色的坦克
        drawTank(peace.getX()+60, peace.getY(), g, peace.getDirection(), 1);
        // drawTank(hero.getX()+180, hero.getY(), g, 2, 1);
        // drawTank(hero.getX()+240, hero.getY(), g, 3, 1);
    }

    public void drawTank(int x,int y,Graphics g,int direction,int type) {
        switch(type) {
            case 0:
            g.setColor(Color.cyan);
            break;
            case 1:
            g.setColor(Color.yellow);
            break;
        }
        switch(direction) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//坦克左边的轮子
                g.fill3DRect(x+30, y, 10, 60, false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);//坦克左边的轮子
                g.fill3DRect(x, y+30, 60, 10, false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//坦克左边的轮子
                g.fill3DRect(x+30, y, 10, 60, false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);//坦克左边的轮子
                g.fill3DRect(x, y+30, 60, 10, false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W) {
            hero.setDirection(0);
            hero.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_D) {
            hero.setDirection(1);
            hero.moveRight();
        }else if(e.getKeyCode()==KeyEvent.VK_S) {
            hero.setDirection(2);
            hero.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A) {
            hero.setDirection(3);
            hero.moveLeft();
        }
        //第二辆坦克
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            peace.setDirection(0);
            peace.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            peace.setDirection(1);
            peace.moveRight();
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
            peace.setDirection(2);
            peace.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            peace.setDirection(3);
            peace.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

public class TankGame  extends JFrame {

    MyPanel mp = null;
    
    public TankGame() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
}
