
package sandbox;

import graphics.WinApp;
import music.UC;
import reactions.Ink;

import java.awt.*;
import java.awt.event.MouseEvent;

// Testing - gesture识别系统
public class PaintInk extends WinApp {
    public static Shape.Prototype.List pList = new Shape.Prototype.List();
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
//        g.drawString("point" + Ink.BUFFER.n, 600, 30);
        if(inkList.size > 1){
            int last = inkList.size() - 1；
            int dist = inkList.get(last).norm.dist(inkList.get(last - 1).norm);
            g.setColor(dist > UC.noMatchDist ? Color.RED : Color.BLACK);
            g.drawString("Dist:" + dist, 600, 60);
            pList.show(g);
        }
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
//        Ink.BUFFER.up(me.getX(),me.getY());
//        inkList.add(new Ink());
        Ink int = new Ink();
        Shape.Prototype proto;

        inkList.add(ink);
        if(p.List.bestDist(ink.norm) < UC.noMatchDist){
            //find match, so blend
//            Shape.Prototype.List.bestMatch.blend(ink.norm);
            proto = Shape.ProtoType.List.bestMatch;
            proto.blend(ink.norm);
        }
        else{
//            pList.add(new Shape.Prototype());
            proto = new Shape.Prototype();
            pList.add(proto);
        }
        ink.norm = proto;
        repaint();
    }


    public static void main(String[] args) {
        PANEL = new PaintInk();
        WinApp.launch();
    }
}
