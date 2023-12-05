package com.hspedu.tankgame06;

import com.hspedu.tankgame06.MyPanel;
import com.hspedu.tankgame06.Recorder;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class HspTankGame06 extends JFrame {
    static Scanner scanner = new Scanner(System.in);
//   定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        HspTankGame06 hspTankGame01 = new HspTankGame06();
    }
    public HspTankGame06(){
        System.out.println("请输入选择，1：新游戏，2：继续上局");
        String key = scanner.next();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
     this.setSize(1300,950);
     this.addKeyListener(mp);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setVisible(true);
     this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Recorder.keepRecorder();
             System.exit(0);
//             super.windowClosing(e);
         }
     });
    }
}
