
package sandbox;

import graphics.WinApp;
import music.UC;
import reactions.Ink;

import java.awt.*;
import java.awt.event.MouseEvent;

// Tetsing - gesture识别系统
public class PaintInk extends WinApp {
    public static Ink.List inkList= new Ink.List();

    static{ //和constructor不同，能够初始化任何东西，不一定是对象
        inkList.add(new Ink()); // 初始化的时候加入一个Ink
    }

    public PaintInk() {
        super("PaintInk", UC.mainWindowWidth, UC.mainWindowHeight);
    }

    public void paintComponent(Graphics g){
        //g.clearscreen(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
        Ink.Buffer.show(g);
        inkList.show(Graphics g);
    }

    public void mousePressed(MouseEvent me){
        Ink.Buffer.dn(me.getX(),me.getY());
        repaint();
    }

    public void mouseDragged(MouseEvent me){
        Ink.Buffer.drag(me.getX(),me.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent me){
        Ink.Buffer.up(me.getX(),me.getY());
        intList.add(new Ink());
        repaint();
    }


    public static void main(String[] args) {
        PANEL = new PaintInk();
        WinApp.launch();
    }
}
