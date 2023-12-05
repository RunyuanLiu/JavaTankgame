package com.hspedu.tankgame06;

import com.hspedu.tankgame06.Tank;
import com.hspedu.tankgame06.shot;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Vector<shot> shots = new Vector<>();
//    增加成员，EnemyTank可以得到敌人坦克的Vector
   Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
//添加一个方法可以将Mypanel成员的Vector<EnemyTank> = new Vector<>();
//    设置到EnemyTank 的成员 enemyTanks
//    编写方法，判断当前的坦克是否和enemyTanks中的其他坦克发生重叠或碰撞
   public boolean isTouchEnemyTank(){
//        判断当前敌人坦克的方向
       switch (this.getDirect()){
           case 0:
//               让当前敌人坦克和其他所有的敌人坦克比较
               for (int i = 0; i < enemyTanks.size(); i++) {
//                   取出一个坦克
                   EnemyTank enemyTank = enemyTanks.get(i);
//                   不和自己比较
                   if (enemyTank != this){
//                       敌人坦克上下
                       if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                        if (this.getX() >= enemyTank.getX()
                                &&this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY()
                                &&this.getY() <= enemyTank.getY() + 60){
                            return true;
                        }
                           if (this.getX() + 40 >= enemyTank.getX()
                                   &&this.getX() + 40 <= enemyTank.getX() + 40
                                   && this.getY() >= enemyTank.getY()
                                   &&this.getY() <= enemyTank.getY() + 60){
                               return true;
                           }
                       }
                       if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                           if (this.getX() >= enemyTank.getX()
                                   &&this.getX() <= enemyTank.getX()+60
                                   && this.getY() >= enemyTank.getY()
                                   &&this.getY() <= enemyTank.getY()+40){
                               return true;
                           }
                           if (this.getX()+40 >= enemyTank.getX()
                                   &&this.getX()+40 <= enemyTank.getX()+60
                                   && this.getY() >= enemyTank.getY()
                                   &&this.getY() <= enemyTank.getY()+40){
                               return true;
                           }

                       }

                   }
               }
               break;
           case 1:
               for (int i = 0; i < enemyTanks.size(); i++) {
//                   取出一个坦克
                   EnemyTank enemyTank = enemyTanks.get(i);
//                   不和自己比较
                   if (enemyTank != this){
//                       敌人坦克上下
                       if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                           if (this.getX() + 60 >= enemyTank.getX()
                                   &&this.getX() + 60 <= enemyTank.getX() + 40
                                   && this.getY() >= enemyTank.getY()
                                   &&this.getY()<= enemyTank.getY() + 60){
                               return true;
                           }
                           if (this.getX() + 60 >= enemyTank.getX()
                                   &&this.getX() + 60 <= enemyTank.getX() + 40
                                   && this.getY() + 40 >= enemyTank.getY()
                                   &&this.getY() + 40 <= enemyTank.getY() + 60){
                               return true;
                           }
                       }
                       if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                           if (this.getX() + 60 >= enemyTank.getX()
                                   &&this.getX() + 60 <= enemyTank.getX() + 60
                                   && this.getY() >= enemyTank.getY()
                                   &&this.getY() <= enemyTank.getY() + 40){
                               return true;
                           }
                           if (this.getX() + 60 >= enemyTank.getX()
                                   &&this.getX() + 60 <= enemyTank.getX() + 60
                                   &&this.getY() + 40 >= enemyTank.getY()
                                   &&this.getY() + 40 <= enemyTank.getY() + 40){
                               return true;
                           }

                       }

                   }
               }

               break;
           case 2:
               for (int i = 0; i < enemyTanks.size(); i++) {
//                   取出一个坦克
                   EnemyTank enemyTank = enemyTanks.get(i);
//                   不和自己比较
                   if (enemyTank != this){
//                       敌人坦克上下
                       if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                           if (this.getX() >= enemyTank.getX()
                                   &&this.getX()  <= enemyTank.getX() + 40
                                   &&this.getY() + 60 >= enemyTank.getY()
                                   &&this.getY() + 60 <= enemyTank.getY() + 60){
                               return true;
                           }
                           if (this.getX() + 40 >= enemyTank.getX()
                                   &&this.getX() + 40 <= enemyTank.getX() + 40
                                   &&this.getY() + 60 >= enemyTank.getY()
                                   &&this.getY() + 60 <= enemyTank.getY() + 60){
                               return true;
                           }
                       }
                       if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                           if (this.getX() >= enemyTank.getX()
                                   &&this.getX() <= enemyTank.getX() + 60
                                   &&this.getY() + 60 >= enemyTank.getY()
                                   &&this.getY() + 60 <= enemyTank.getY() + 40){
                               return true;
                           }
                           if (this.getX() + 40 >= enemyTank.getX()
                                   &&this.getX() + 40 <= enemyTank.getX() + 60
                                   &&this.getY() + 60 >= enemyTank.getY()
                                   &&this.getY() + 60 <= enemyTank.getY() + 40){
                               return true;
                           }

                       }

                   }
               }
               break;
           case 3:
               for (int i = 0; i < enemyTanks.size(); i++) {
//                   取出一个坦克
                   EnemyTank enemyTank = enemyTanks.get(i);
//                   不和自己比较
                   if (enemyTank != this){
//                       敌人坦克上下
                       if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                           if (this.getX() >= enemyTank.getX()
                                   &&this.getX() <= enemyTank.getX() + 40
                                   &&this.getY() >= enemyTank.getY()
                                   &&this.getY() <= enemyTank.getY() + 60){
                               return true;
                           }
                           if (this.getX() >= enemyTank.getX()
                                   &&this.getX() <= enemyTank.getX() + 40
                                   &&this.getY() + 40 >= enemyTank.getY()
                                   &&this.getY() + 40 <= enemyTank.getY() + 60){
                               return true;
                           }
                       }
                       if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                           if (this.getX() >= enemyTank.getX()
                                   &&this.getX() <= enemyTank.getX() + 60
                                   &&this.getY() >= enemyTank.getY()
                                   &&this.getY() <= enemyTank.getY() + 40){
                               return true;
                           }
                           if (this.getX()  >= enemyTank.getX()
                                   &&this.getX()  <= enemyTank.getX() + 60
                                   &&this.getY() + 40 >= enemyTank.getY()
                                   &&this.getY() + 40 <= enemyTank.getY() + 40){
                               return true;
                           }

                       }

                   }
               }
               break;
       }
       return false;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    @Override
    public void run() {
        while(true){
//            如果
            if(isLive&&shots.size() <1){
                shot s = null;
                switch (getDirect()){
                    case 0:
                        s = new shot(getX()+20,getY(),0);
                    case 1:
                        s = new shot(getX()+60,getY()+20,1);
                    case 2:
                        s = new shot(getX()+20,getY()+60,2);
                    case 3:
                        s = new shot(getX(),getY()+20,3);
                }
                shots.add(s);
                new Thread(s).start();
            }
            switch (getDirect()){
                case 0://向上
                    for (int i = 0; i < 30; i++) {
                        if(getY()>0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1://向右
                    for (int i = 0; i < 30; i++) {
                        if (getX()+60<1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2://向下
                    for (int i = 0; i < 30; i++) {
                        if (getY()+60<750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3://向左
                    for (int i = 0; i < 30; i++) {
                        if (getX()>0 && !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
//            返回0到4但取不到4
            setDirect((int)(Math.random()*4));
            if(!isLive){
                break;
            }
        }
    }
}
