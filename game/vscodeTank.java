package game.vscodeTank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed = 1;
    boolean isLive = true;

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
class EnemyTank extends Tank implements Runnable{//敌人坦克
    public EnemyTank(int x, int y) {super(x, y);}
    Vector<Shoot> shoots = new Vector<>();
    boolean isLive = true;//坦克默认是活得
    @Override
    public void run() {
        if(isLive && shoots.size() == 5) {//敌人坦克子弹的数量
            Shoot s = null;
            switch(getDirection()) {
                case 0: s = new Shoot(getX()+20,getY(),0);
                case 1: s = new Shoot(getX()+60,getY()+20,1);
                case 2: s = new Shoot(getX()+20,getY()+60,2);
                case 3: s = new Shoot(getX(),getY()+20,3);
            }
            shoots.add(s);
            new Thread(s).start();
        }
        while(true) {
            switch(getDirection()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if(getY() > 0) {moveUp();}
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if(getX()+60<1000) {moveRight();}
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if(getY()+60<750) {moveDown();}                    
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if(getX()>0) {moveLeft();}                 
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            setDirection((int)(Math.random()*4));//得到0~3的整数
            if(!isLive) {break;}//退出线程
        }
    }
}
class Hero extends Tank {//自己的坦克
    public Hero(int x,int y) {
        super(x, y);
    }
    Shoot shoot = null;
    Vector<Shoot> shoots = new Vector<>();

    public void shootEnemyTank() {//射击
        if(shoots.size()==5) {
            return;//面板最多出现5颗子弹
        }
        switch(getDirection()) {
            case 0:
                shoot = new Shoot(getX()+20, getY(), 0);
                break;
            case 1:
                shoot = new Shoot(getX()+60, getY()+20, 1);
                break;
            case 2:
                shoot = new Shoot(getX()+20, getY()+60, 2);
                break;
            case 3:
                shoot = new Shoot(getX(), getY()+20, 3);
                break;
        }
        shoots.add(shoot);
        new Thread(shoot).start();
    }
}
class Peace extends Tank {
    public Peace(int x, int y) {
        super(x, y);
    }
    // Shoot shoot = null;
    // Vector<Shoot> shoots = new Vector<>();

    // public void shootEnemyTank() {//射击
    //     if(shoots.size()==5) {
    //         return;//面板最多出现5颗子弹
    //     }
    //     switch(getDirection()) {
    //         case 0:
    //             shoot = new Shoot(getX()+20, getY(), 0);
    //             break;
    //         case 1:
    //             shoot = new Shoot(getX()+60, getY()+20, 1);
    //             break;
    //         case 2:
    //             shoot = new Shoot(getX()+20, getY()+60, 2);
    //             break;
    //         case 3:
    //             shoot = new Shoot(getX(), getY()+20, 3);
    //             break;
    //     }
    //     shoots.add(shoot);
    //     new Thread(shoot).start();
    // }
}
class MyPanel extends JPanel implements KeyListener,Runnable{//坦克大战的绘图区域
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();//创建动态数组
    int enemyTankSize = 3;
    Peace peace = null;


    public MyPanel() {
        hero = new Hero(500,100);//初始化自己的坦克
        hero.setSpeed(20);

        peace = new Peace(500, 100);
        peace.setSpeed(20);
        for (int i = 0; i < enemyTankSize; i++) {//初始化敌人坦克
            EnemyTank enemyTank = new EnemyTank(100*(i+1),0);//创建一个敌人坦克
            enemyTank.setDirection(2);//默认反向
            new Thread(enemyTank).start();//启动敌人坦克线程
            Shoot shoot = new Shoot(enemyTank.getX()+20, enemyTank.getY()+60, enemyTank.getDirection());
            enemyTank.shoots.add(shoot);//加入一颗子弹
            new Thread(shoot).start();//子弹线程
            enemyTanks.add(enemyTank);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形,默认为黑色
        if(hero != null && hero.isLive) {
            drawTank(hero.getX(),hero.getY(),g,hero.getDirection(),0);//青色的坦克
        }
        if(peace != null && peace.isLive) {
            drawTank(peace.getX()+60, peace.getY(), g, peace.getDirection(), 1);
        }

        //画出hero射击的坦克
        // if(hero.shoot != null && hero.shoot.isLive == true) {//画出hero射出的子弹
        //     g.draw3DRect(hero.shoot.x, hero.shoot.y, 5, 5, false);
        // }
        for (int i = 0; i < hero.shoots.size(); i++) {
            Shoot shoot = hero.shoots.get(i);
            if (shoot != null && shoot.isLive == true) {
                g.draw3DRect(shoot.x, shoot.y, 1, 1, false);
            } else {//如果该shoot对象已经无效,就从shoots集合中拿掉
                hero.shoots.remove(shoot);
            }
        }

        for (int i = 0; i < enemyTanks.size(); i++) {//画敌人坦克,遍历Vector
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isLive) {//当敌人坦克是存活的,才会画
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                for (int j = 0; j < enemyTank.shoots.size(); j++) {
                    Shoot shoot = enemyTank.shoots.get(j);//取出子弹
                    if(shoot.isLive) {
                        g.draw3DRect(shoot.x, shoot.y, 1, 1, false);
                    }else {
                        enemyTank.shoots.remove(shoot);
                    }
                }
            }
        }
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

    public void hitEnemyTank() {
        // for (int j = 0; j < hero.shoots.size(); j++) {
        //     Shoot shoot = hero.shoots.get(j);
        // }

        if(hero.shoot != null && hero.shoot.isLive) {//判断子弹是否击中了敌人的坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(hero.shoot, enemyTank);
            }
        }
    }

    public void hitHero() {//判断敌人坦克是否击中我的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {//遍历所有敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shoots.size(); j++) {//遍历enemyTank的所有子弹
                Shoot shoot = enemyTank.shoots.get(j);
                if(hero.isLive && shoot.isLive) {//判断是否击中我的坦克
                    hitTank(shoot, hero);
                }
            }
        }
    }
    public void hitTank(Shoot s,Tank enemyTank) {//判断我方子弹是否击中敌人坦克
        switch(enemyTank.getDirection()) {
            case 0://坦克向上
            case 1://坦克向右
            case 2://坦克向下
                if(s.x>enemyTank.getX() && s.x<enemyTank.getX()+40
                && s.y>enemyTank.getY() && s.y<enemyTank.getY()+60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);//当坦克被击中时,删除该坦克
                }
            case 3://坦克向左
                if(s.x>enemyTank.getX() && s.x<enemyTank.getX()+60
                && s.y>enemyTank.getY() && s.y<enemyTank.getY()+40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                }
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W) {//上
            hero.setDirection(0);
            if(hero.getY()>0) {
                hero.moveUp();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_D) {//右
            hero.setDirection(1);
            if(hero.getX()+60<1000) {
                hero.moveRight();
            } 
        }else if(e.getKeyCode()==KeyEvent.VK_S) {//下
            hero.setDirection(2);
            if(hero.getY()+60<750) {
                hero.moveDown();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_A) {//左
            hero.setDirection(3);
            if(hero.getX()>0) {
                hero.moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_J) {
            // if(hero.shoot==null || !hero.shoot.isLive) {
            //     hero.shootEnemyTank();
            // }
            hero.shootEnemyTank();
        }
        //第二辆坦克
        if(e.getKeyCode()==KeyEvent.VK_UP) {//上
            peace.setDirection(0);
            if(peace.getY()>0) {
                peace.moveUp();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {//右
            peace.setDirection(1);
            if(peace.getX()+60<1000) {
                peace.moveRight();
            } 
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN) {//下
            peace.setDirection(2);
            if(peace.getY()+60<750) {
                peace.moveDown();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT) {//左
            peace.setDirection(3);
            if(peace.getX()>0) {
                peace.moveLeft();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
class Shoot implements Runnable {
    int x;
    int y;
    int direction = 0;
    int speed = 3;//子弹速度
    boolean isLive = true;//子弹是否存活
    

    public Shoot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch(direction) {
                case 0:
                    y -=speed;
                    break;
                case 1:
                    x +=speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            System.out.println("子弹 x="+x+"y="+y);

            if(!(x >= 0&&x <= 1000&&y >= 0&&y <=750 && isLive)) {
                this.isLive = false;
                break;
            }
        }
    }
}
public class vscodeTank  extends JFrame {
    MyPanel mp = null;
    
    public vscodeTank() {
        mp = new MyPanel();
        Thread thread = new Thread(mp);//将mp放到Thread,并启动
        thread.start();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        vscodeTank tankGame = new vscodeTank();
    }
}
