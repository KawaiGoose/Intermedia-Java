package sandbox;

import graphics.WinApp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Paint extends WinApp {
    public static int click = 0; //储存多个path
    public static Path thePath = new Path(); //track当前的path
    public static Pic pic = new Pic();

    public Paint() {
        super("Paint", 1000, 700);
    } //调用父类的构造函数

    @Override
    public void paintComponent(Graphics graphics){
        /*
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(100,50,200,500);

        graphics.setColor(Color.GREEN);
        graphics.fillOval(100,600,200,300);

        int x = 400, y = 200;
        String msg = "Clicks" + click;

        // 绘制文本
        graphics.setColor(Color.BLUE);
        graphics.drawString(msg, x, y);

        // 绘制基线标记点
        graphics.setColor(Color.RED);
        graphics.drawOval(x, y, 3, 3); // 基线标记点

        // 获取字体度量信息
        FontMetrics fm = graphics.getFontMetrics();
        int a = fm.getAscent();       // 上升高度
        int d = fm.getDescent();      // 下降高度
        int w = fm.stringWidth(msg);  // 字符串宽度

        // 绘制文本边界框
        graphics.setColor(Color.GREEN);
        graphics.drawRect(x, y - a, w, a + d);
        */
        graphics.setColor(Color.BLACK);
        pic.draw(graphics);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        click++;
        thePath = new Path(); // ✅ 创建新 Path，防止路径混在一起
        thePath.add(me.getPoint()); // 将鼠标按下的位置添加到路径中，记录起始点
        pic.add(thePath); //添加到Pic
        repaint(); // 请求重绘
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        thePath.add(me.getPoint()); // 将鼠标拖动的位置添加到路径中
        repaint(); // 请求重绘
    }

    public static void main(String[] args){
        PANEL = new Paint();
        WinApp.launch();
    }

    //--------------------PATH----------------------------
    public static class Path extends ArrayList<Point> {
        public void draw(Graphics graphics){
            for(int i = 1; i<size(); i++){
                Point p = get(i-1);
                Point n = get(i); // the previous and the next point
                graphics.drawLine(p.x,p.y,n.x,n.y);
            }
        }
    }
    //--------------------PIC----------------------------
    public static class Pic extends ArrayList<Path>{
        public void draw(Graphics graphics){
            for(Path p:this){
                p.draw(graphics);
            }
        }
    }
}
