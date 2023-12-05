package com.hspedu.tankgame06;

import com.hspedu.tankgame06.*;
import com.sun.jdi.ShortType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;
//为了让Panel不停的重绘子弹，需要将MyPanel 实现 Runnable，当做一个线程使用
public class MyPanel extends JPanel implements KeyListener ,Runnable {
//    定义我的坦克
//    定义一个Node对象的Vector，用于恢复敌人坦克的坐标
    Vector<Node> nodes = new Vector<>();
    Hero hero = null;

    Vector<EnemyTank> enemyTanks = new Vector<>();
//   定义一个vector，用于存放炸弹
    Vector<Bomb> bombs = new Vector<>();
//    当子弹击中坦克是，就加入一个Bomb对象到bombs中
    int enemyTankSize = 3;
//    定义三张炸弹图片用于显示爆炸
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
//    因为是多线程问题所以放在集合中
    public MyPanel(String key){
        File file = new File(Recorder.getRecordFile());
        if (file.exists()){
            nodes = Recorder.getNodesAndEnemyTankRec();
        }else {
            System.out.println("文件不存在只能开启新的游戏");
            key = "1";
        }

        //    将MyPanel对象的enemyTanks 设置给 Recorder 的 enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(500,100);
        switch (Integer.parseInt(key)){
            case 1:
                Recorder.setAllEnemyTankNum(0);
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank1 = new EnemyTank(100 * (i + 1), 0);
                    enemyTank1.setEnemyTanks(enemyTanks);
                    enemyTank1.setDirect(2);
//            启动敌人tank线程，让其动起来
                    new Thread(enemyTank1).start();
//            给该enemyTank 加入一颗子弹
                    shot shot =  new shot(enemyTank1.getX()+20,enemyTank1.getY()+60,enemyTank1.getDirect());
                    enemyTank1.shots.add(shot);
                    new Thread(shot).start();

                    enemyTanks.add(enemyTank1);
                }
                break;
            case 2:
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank1 = new EnemyTank(node.getX(),node.getY());
                    enemyTank1.setEnemyTanks(enemyTanks);
                    enemyTank1.setDirect(node.getDirect());
//            启动敌人tank线程，让其动起来
                    new Thread(enemyTank1).start();
//            给该enemyTank 加入一颗子弹
                    shot shot =  new shot(enemyTank1.getX()+20,enemyTank1.getY()+60,enemyTank1.getDirect());
                    enemyTank1.shots.add(shot);
                    new Thread(shot).start();

                    enemyTanks.add(enemyTank1);
                }
            default:
                System.out.println("输入有误");
        }
//        enemyTank = new EnemyTank(500,500);//初始化自己的tank

//        初始化图片对象
//        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(""));
//        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(""));
//        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(""));
        hero.setSpeed(50);
    }
// 编写方法，显示我方击毁tank的信息
    public void showInfo(Graphics g){
//     画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1024,30);
        drawTank(1020,60,g,0,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形默认黑色
//        画出坦克
        showInfo(g);
        if (hero!=null&& hero.isLive){
            drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        }
//        画出hero射击的子弹
        for (int i = 0; i < hero.shots.size(); i++) {
              shot shot = hero.shots.get(i);
            if (shot !=null && shot.isLive != false){
//            g.fill3DRect(hero.shot.x,hero.shot.y,1,1,false);
                g.draw3DRect(shot.x,shot.y,6,6,false);;
            }else {//如果该shot对象已经无效，就从shots集合中拿掉
                hero.shots.remove(shot);
            }
        }
//        如果集合中有炸弹的就要画出来
//        for(int i = 0; i<bombs.size();i++){
//            Bomb bomb = bombs.get(i);
//            if (bomb.life > 6){
//                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
//            }else if (bomb.life > 3){
//                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
//            }else {
//                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
//            }
//        }
//        System.out.println("第一个坦克");
//        drawTank(hero.getX()+70,hero.getY(),g,hero.getDirect(),1);
////        System.out.println("第二个坦克");
//        drawTank(hero.getX()+120,hero.getY(),g,hero.getDirect(),1);
//        drawTank(hero.getX()+150,hero.getY()+150,g,hero.getDirect(),1);
//        System.out.println("“”“”“");
//        画出敌人的坦克
//    编写方法画出坦克,遍历几何
//        System.out.println(enemyTankSize);
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank1 = enemyTanks.get(i);
            if (enemyTank1.isLive) {
                drawTank(enemyTank1.getX(), enemyTank1.getY(), g, enemyTank1.getDirect(), 1);
// 画出enemyTank所有子弹
                for (int j = 0; j < enemyTank1.shots.size(); j++) {
                    shot shot = enemyTank1.shots.get(j);
                    if (shot.isLive) {
//                    取出当前shot的x，y
                        g.draw3DRect(shot.x, shot.y, 6, 6, false);
                    } else {
//                    从vetor中移除
                        enemyTank1.shots.remove(shot);
                    }

                }

            }
        }
    }



    /**
 * **/
    public void drawTank(int x,int y,Graphics g ,int direct,int type){
//        根据不同类型的坦克，设置不同颜色
        switch (type){
            case 0://我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
//        根据坦克的方向来绘制坦克
        switch (direct) {
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y,x+20,y+30);
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println();
        }


    }
    public void hitEnemyTank(){
        for (int j = 0; j < hero.shots.size(); j++) {
             shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive){
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot,enemyTank);
                }
            }
        }
    }
    public void hitHeroTank(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                shot shot = enemyTank.shots.get(j);
                if (hero.isLive&&shot.isLive){
                    hitTank(shot,hero);
                }

            }

        }
    }
    public  void hitTank(shot shot ,Tank Tank){
        switch (Tank.getDirect()){
            case 0:
            case 2:
                if (shot.x>Tank.getX()&&shot.x<Tank.getX()+40 && shot.y>Tank.getY()
                        &&shot.y<Tank.getY()+60){
                    shot.isLive = false;
                    Tank.isLive = false;
//                    当自己的子弹击中敌人对象后，将enemytank从Vector中拿掉
                    enemyTanks.remove(Tank);
//                    判断删除的坦克是否是敌人坦克，若是加一
                    if(Tank instanceof EnemyTank){
                       Recorder.addallEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(Tank.getX(),Tank.getY());
                    bombs.add(bomb);
                }
            case 1:
            case 3:
                if (shot.x>Tank.getX()&&shot.x<Tank.getX()+60 && shot.y>Tank.getY()
                        &&shot.y<Tank.getY()+40){
                    shot.isLive = false;
                    Tank.isLive = false;
                    enemyTanks.remove(Tank);
                    if(Tank instanceof EnemyTank){
                        Recorder.addallEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(Tank.getX(),Tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //处理WDSA键按下的情况
        if (e.getKeyCode()==KeyEvent.VK_W){//上
            hero.setDirect(0);
            if (hero.getY()>0) {
                hero.moveUp();
            }
//            drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),1);
        }else if (e.getKeyCode()==KeyEvent.VK_D){//右
            hero.setDirect(1);
           if (hero.getX()+90<=1000){
               hero.moveRight();
           }
        }else if (e.getKeyCode()==KeyEvent.VK_S){//左移动
            hero.setDirect(2);
            if (hero.getY()+90<=750){hero.moveDown();}
        }else if (e.getKeyCode()==KeyEvent.VK_A){//下移动
            hero.setDirect(3);
           if(hero.getX()>0){
               hero.moveLeft();
           }
        }
   if (e.getKeyCode()==KeyEvent.VK_J){
//       弹壳子弹打中敌人坦克
//       if (hero.shot==null || !hero.shot.isLive) {
//           hero.shotEnemyTank();
//       }
//       多颗子弹打中敌人坦克
       hero.shotEnemyTank();
        }
        this.repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void run() {
        while (true) {
//            判断是否集中了敌人坦克
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemyTank();
            hitHeroTank();
//            if (hero.shot.isLive){
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//
//
//                }
//            }

            this.repaint();
        }
    }
}
