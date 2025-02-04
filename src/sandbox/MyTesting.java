package sandbox;

import graphics.WinApp;

import java.awt.*;

public class MyTesting extends WinApp {

    public MyTesting() {super("MyTesting", 1000, 700);}

    @Override //重写Jpanel的方法paintComponent，用于绘制组件的内容
    public void paintComponent(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.drawRect(100,100,100,100);
    }

    public static void main(String[] args){
        PANEL = new MyTesting();
        WinApp.launch();
    }
}
