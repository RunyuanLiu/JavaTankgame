package com.hspedu.tankgame06;

public class shot implements Runnable{
   int x;
   int y;
   int direct = 0;
   boolean isLive = true;
   int speed = 2;

    public shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
       while (true){
//           线程休眠50ms
           try {
               Thread.sleep(50);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           switch (direct){
               case 0:
                   y -= speed;
                   break;
               case 1:
                   x += speed;
                   break;
               case 2:
                   y += speed;
                   break;
               case 3:
                   x -= speed;
                   break;
           }
           System.out.println("子弹 x="+x+"y="+y);
//           当子弹碰到敌人塔克时，也应该结束
           if( !(x>=0&&x<=1000&& y>=0 && y<=750 && isLive) ){
               isLive = false;
               break;
           }
       }
    }

}
