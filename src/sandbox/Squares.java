/*
package sandbox;

import graphics.WinApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import graphics.G;
import music.I;
import music.UC;

import javax.swing.*;

public class Squares extends WinApp implements ActionListener {

    public static boolean showSpline = false;

    public static Square.List squares = new Square.List(); //创建对象

    public static Square lastSquare;

    public static boolean dragging = false;

    public static G.V mouseDelta = new G.V(0,0);

    public static Timer timer;

    public static G.V pressedLoc = new G.V(0,0);

    public Squares() {
        super("Squares",1000,800); //初始化面板
        timer = new Timer(30,this);
        timer.setInitialDelay(5000);
        timer.start();

    }

    public static G.VS theVS = new G.VS(100,100,200,300); //创造矩形
    public static Color color = G.rndColor(); //随机颜色

    @Override
    public void paintComponent(Graphics g){
        G.clearScreen(g);
        //theVS.fill(g, color); //填充绘制矩形
        squares.draw(g);
        if (showSpline && squares.size() > 2){
            g.setColor(Color.BLACK);
            G.V a = squares.get(0).loc, b = squares.get(1).loc, c = squares.get(2).loc;
            G.spline(g, a.x, a.y, b.x, b.y, c.x, c.y, 4);

        }
    }

//    @Override
//    public void mousePressed(MouseEvent me){
//        //if(theVS.hit(me.getX(), me.getY())){color = G.rndColor();} //过程一：如果点击矩形，颜色更变
//        //squares.add(new Square(me.getX(),me.getY())); //过程二：免去直接创建对象
//        //squares.addNew(me.getX(), me.getY()); //过程三：点击鼠标有一个方格
//        int x = me.getX(), y = me.getY();
//        lastSquare = squares.hit(x,y);
//        //lastSquare = new Square(me.getX(), me.getY()); //Process 4:Dragging Box
//        if(lastSquare == null){
//            dragging = false;
//            lastSquare = new Square(x,y);
//            squares.add(lastSquare);
//        }
//        else{
//            mouseDelta.set(lastSquare.loc.x - x,lastSquare.loc.y - y);
//            dragging = true;
//            lastSquare.dv.set(0,0);
//            pressedLoc.set(x,y);
//
//        }
//        repaint(); // don't forget to repaint when you change something.
//    }

//    @Override
//    public void mouseDragged(MouseEvent me){
//        int x = me.getX(), y = me.getY();
//        if (dragging) {
//            lastSquare.moveTo(x + mouseDelta.x,y + mouseDelta.y);
//        }
//        else{
//            lastSquare.resize(x,y);
//        }
//        repaint();
//    }

    public static I.Area curArea; //点击方格还是白板，两个选项
    @Override
    public void mousePressed(MouseEvent me){
        int x = me.getX(), y = me.getY();
        curArea = squares.hit(x,y);
        curArea.dn(x,y);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me){
        curArea.drag(me.getX(),me.getY());
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me){
        curArea.up(me.getX(),me.getY());
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

//    @Override
//    public void mouseReleased(MouseEvent me){
//        if (dragging){
//            lastSquare.dv.set(me.getX() - pressedLoc.x,me.getY() - pressedLoc.y);
//        }
//    }

    //-----------------Square------------------------------
    public static class Square extends G.VS implements I.Area{
        public static Square BACKGROUND = new Square(){
            //anonymous class --> 简单的不值得新建一个类的功能直接加进去

            public void dn(int x, int y){ //background create a new block and resize
                lastSquare = new Square(x,y);
                squares.add(lastSquare);
            }

            public void drag(int x, int y){
                lastSquare.resize(x,y);
            }

            public void up(int x, int y){
                lastSquare.moveTo(x,y);
            }
        };



        //overloading
        public Square(){
            super(0,0,UC.largePossibleCoorinate,UC.largePossibleCoorinate);
            c = Color.WHITE;
        }

        public Color c = G.rndColor();

//        public G.V dv = new G.V(G.rnd(20) - 10,G.rnd(20) - 10);
        public G.V dv = new G.V(0,0);

        public Square(int x, int y){super(x,y,100,100);}

        public void draw(Graphics g){fill(g,c); moveandBounce();}

        //rubberband rectangles
        public void resize(int x, int y){if(x>loc.x && y>loc.y){size.set(x - loc.x, y - loc.y);}}

        //Dragging Boxes
        public void moveTo(int x, int y){loc.set(x,y);}

        public void moveandBounce(){
            loc.add(dv);
            if (xL() < 0 && dv.x < 0){dv.x = - dv.x;}
            if (yL() < 0 && dv.y < 0){dv.y = - dv.y;}
            if (xH() > 1000 && dv.x > 0){dv.x = - dv.x;}
            if (yH() > 800 && dv.y > 0){dv.y = - dv.y;}
        }

        @Override
        public void dn(int x, int y) {
            mouseDelta.set(loc.x - x, loc.y - y);

        }

        @Override
        public void up(int x, int y) {

        }

        @Override
        public void drag(int x, int y) {
            loc.set(mouseDelta.x + x, mouseDelta.y + y );
        }


        //------------------List----------------------------
        public static class List extends ArrayList<Square> {
            public List(){
                super();
                this.add(Square.BACKGROUND);
            }
            public void draw(Graphics g){for(Square s : this){s.draw(g);}}
            public void addNew(int x, int y){add(new Square(x,y));}
            public Square hit(int x, int y){ // （一）can return null （二）不止return null
                Square res = null;
                for(Square s : this){
                    if(s.hit(x,y)){
                        res = s;
                    }
                }
                return res;
            }
        }
    }

    public static void main(String[] args){PANEL=new Squares();WinApp.launch();}
}
*/
