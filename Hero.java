package com.hspedu.tankgame06;

import com.hspedu.tankgame06.Tank;
import com.hspedu.tankgame06.shot;

import java.util.Vector;

public class Hero extends Tank {
//    定义一个shot对象，表示一个射击线程
    shot shot = null;
    Vector<shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }
    public void shotEnemyTank(){
        if (shots.size() == 5){
            return;
        }
//        创建shot对象，根据当前Hero对象的位置和方向创建Shot
        switch (getDirect()){
            case 0:
                shot = new shot(getX()+20,getY(),0);
                break;
            case 1:
                shot = new shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new shot(getX(),getY()+20,3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }

}
