package sandbox;

import graphics.G;
import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Ovals extends WinApp {
    public static G.VS theOval = new G.VS(100,100,100,100); //（一）调用G.VS对象 里面的所有方法从这里取
    public static Color color = G.rndColor(); //（一）调用G.rnd方法 随机改变颜色

    public static Oval.List ovals = new Oval.List(); //（二）调用Oval.List对象

    public Oval lastOval; //（三）成员变量，用于记录当前操作的椭圆

    private boolean dragging = false; // (四) 拖拽标志

    private static G.V mouseDelta = new G.V(0,0);// (四) 用于存储鼠标偏移量


    public Ovals() {
        super("Ovals", 1000, 700);
    }

    public void paintComponent(Graphics g){
        g.setColor(color); //（一）
        //（一）固定绘制椭圆
        // g.fillOval(theOval.loc.x, theOval.loc.y, theOval.size.x, theOval.size.y);
        //（二） 遍历ovals列表打印椭圆，这里调用ovals自己的draw函数就好
        ovals.draw(g);
    }

    @Override
    public void mousePressed(MouseEvent me){
        //（一）点击椭圆会变色
//        if(theOval.hit(me.getX(),me.getY())){
//            color = G.rndColor();
//            repaint();
//        }
        //（二）需要确保每次点击时创建一个新的椭圆并将其添加到 ovals 列表中
//        Oval oval = new Oval(me.getX() - 50, me.getY() - 50);
//        ovals.add(oval);
//        repaint();
        //（三）拖拽改变大小
//        lastOval = new Oval(me.getX(), me.getY());
//        ovals.add(lastOval);
//        repaint();
        //（四）拖拽形状到要去的地方
        int x = me.getX(), y = me.getY();
        lastOval = ovals.hit(x, y);
        if (lastOval == null){ //没点击到形状
            dragging = false; //说明不进行拖拽动作
            lastOval = new Oval(x, y); //创造新形状，并加入列表
            ovals.add(lastOval);
        }
        else{ //点击到形状
            dragging = true; // 进行拖拽动作
            mouseDelta.set(lastOval.loc.x - x, lastOval.loc.y - y); // 计算点击位置与椭圆中心的偏移量
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me){
        //（三）拖拽改变大小
//        lastOval.resize(me.getX(), me.getY());
//        repaint();
        //（四）拖拽形状到要去的地方
        if (dragging){ //如果是拖拽
            lastOval.moveTo(me.getX() + mouseDelta.x, me.getY() + mouseDelta.y);
        }
        else{ //不是拖拽
            lastOval.resize(me.getX(), me.getY());
        }
        repaint();
    }

    public static void main(String[] args) {
        PANEL = new Ovals();
        WinApp.launch();
    }

    //----------------------Oval-------------------
    public static class Oval extends G.VS{
        public Color c = G.rndColor();

        public Oval(int x, int y) {
            super(x, y, 100, 100);
        }

        @Override
        public void fill(Graphics g, Color c){
            g.setColor(c);
            g.fillOval(loc.x, loc.y, size.x, size.y);
        }

        public void resize(int x, int y){ //（三）拖拽改变大小
            size.set(x - loc.x, y - loc.y);
        }

        public void moveTo(int x, int y){loc.set(x,y);} //（四）拖拽形状到要去的地方

        //----------------------List-------------------
        public static class List extends ArrayList<Oval> {
            public void draw(Graphics g){ //（二）遍历 ovals 列表
                for(Oval o : this){
                    o.fill(g, o.c);
                }
            }

            public Oval hit(int x, int y){ //（四）拖拽形状到要去的地方
                Oval res = null;
                for(Oval s: this){if(s.hit(x,y)){res = s;}}// res 用于保存点击的 Square 对象。如果点击命中了正方形，则更新 res 为该正方形。
                return res; //返回最后一个命中的正方形是故意设计的行为，为了确保用户点击的顶部正方形被选中
            }

        }
    }

}
