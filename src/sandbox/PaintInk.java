package sandbox;

import graphics.WinApp;
import music.UC;
import reactions.Ink;

import java.awt.*;
import java.awt.event.MouseEvent;

// Testing - gesture识别系统
public class PaintInk extends WinApp {
    public static Shape.Prototype.List pList = new Shape.Prototype.List();
    public static Ink.List inkList = new Ink.List();

    static { // 和constructor不同，能够初始化任何东西，不一定是对象
        // inkList.add(new Ink()); // 初始化时加入一个 Ink
    }

    public PaintInk() {
        super("PaintInk", UC.mainWindowWidth, UC.mainWindowHeight);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        Ink.BUFFER.show(g);
        inkList.show(g);

        if (inkList.size() > 1) {
            int last = inkList.size() - 1;
            int dist = inkList.get(last).norm.dist(inkList.get(last - 1).norm);
            g.setColor(dist > UC.noMatchDist ? Color.RED : Color.BLACK);
            g.drawString("Dist:" + dist, 600, 60);
            pList.show(g);
        }
    }

    public void mousePressed(MouseEvent me) {
        Ink.BUFFER.dn(me.getX(), me.getY());
        repaint();
    }

    public void mouseDragged(MouseEvent me) {
        Ink.BUFFER.drag(me.getX(), me.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent me) {
        Ink ink = new Ink();  // ✅ 修正 `int` 为 `ink`
        Shape.Prototype proto;

        inkList.add(ink);

        if (pList.bestDist(ink.norm) < UC.noMatchDist) {
            // Find match, so blend
            proto = Shape.Prototype.List.bestMatch;
            proto.blend(ink.norm);
        } else {
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
