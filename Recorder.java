package com.hspedu.tankgame06;
import com.hspedu.tankgame06.EnemyTank;
import com.hspedu.tankgame06.Node;

import java.io.*;
import java.util.Vector;
public class Recorder {
//    设置变量，记录我方击毁敌人坦克数
      private static int allEnemyTankNum = 0;
//      定义IO对象
      private static FileWriter fw = null;
      private static BufferedWriter bw = null;
      private static BufferedReader br = null;
      private static String recordFile = "d:\\myRecord.txt";
//      定义一个vector，指向Mypanel对象的敌人坦克向量
      private static Vector<EnemyTank> enemyTanks = null;
      private static Vector<Node> nodes = new Vector<>();

      public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
//      定义一个Node的Vector，用于保存敌人的信息
      public static Vector<Node> getNodesAndEnemyTankRec(){
          try {
              br = new BufferedReader(new FileReader(recordFile));
              allEnemyTankNum = Integer.parseInt(br.readLine());
              String line = "";
              while ((line = br.readLine()) != null){
//                  生成信息数组
                  String[] xyz = line.split(" ");
                  Node node = new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt( xyz[2]));
                  nodes.add(node);
              }
          } catch (IOException e) {
              throw new RuntimeException(e);
          } finally {
              try {
                  if (br != null){
                      br.close();
                  }
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
          }
          return nodes;
      }

    public static String getRecordFile() {
        return recordFile;
    }

    //增加一个方法，当游戏退出时，将allEnemyTankNum保存到文件
    public  static void keepRecorder() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum +"\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive){
                    String record = enemyTank.getX() + " "
                            + enemyTank.getY() + " "
                            + enemyTank.getDirect();
                    bw.write(record+"\r\n");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null){
                    bw.close();
            }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }
    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
//当我方坦克击毁一个敌人坦克，就应当allEnemyTankNum++
    public static void addallEnemyTankNum(){
        Recorder.allEnemyTankNum = Recorder.allEnemyTankNum + 1;
    }
}
