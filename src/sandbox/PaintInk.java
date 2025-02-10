
package sandbox;

import graphics.WinApp;
import music.UC;
import reactions.Ink;

import java.awt.*;
import java.awt.event.MouseEvent;

// Testing - gesture识别系统
public class PaintInk extends WinApp {
    public static Ink.List inkList= new Ink.List();

    static{ //和constructor不同，能够初始化任何东西，不一定是对象
//        inkList.add(new Ink()); // 初始化的时候加入一个Ink
    }

    public PaintInk() {
        super("PaintInk", UC.mainWindowWidth, UC.mainWindowHeight);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        Ink.BUFFER.show(g);
        inkList.show(g);
        g.drawString("point" + Ink.BUFFER.n, 600, 30);
    }

    public void mousePressed(MouseEvent me){
        Ink.BUFFER.dn(me.getX(),me.getY());
        repaint();
    }

    public void mouseDragged(MouseEvent me){
        Ink.BUFFER.drag(me.getX(),me.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent me){
        Ink.BUFFER.up(me.getX(),me.getY());
        inkList.add(new Ink());
        repaint();
    }


    public static void main(String[] args) {
        PANEL = new PaintInk();
        WinApp.launch();
    }
}
