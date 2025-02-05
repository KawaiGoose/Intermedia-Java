package reactions;

import graphics.G;
import music.I;
import music.UC;

import java.awt.*;
import java.util.ArrayList;

import static jdk.internal.jshell.tool.StopDetectingInputStream.State.BUFFER;

public class Ink extends G.PL implements I.Show{

    public static final Buffer BUFFER = new Buffer();

    public Ink(){
        super(Buffer.n);
        for (int i = 0; i < Buffer.n; i++) {
            points[i].set(BUFFER.points[i]);
        }
    }

    @Override
    public void show(Graphics g) {
        // stub (相当于TODO)
//        g.setColor(Color.BLACK);
//        g.fillRect(100, 100, 100, 100);
//        g.drawString("Stub Ink.Show", 300, 100);
        g.setColor(UC.inkColor);
        draw(g);
    }

    // -------------------Buffer-----------------------临时存储一系列的点
    public static class Buffer extends G.PL implements I.Show, I.Area{
        public static final int MAX = UC.inkBufferMAX;
        public static int n; //how many points actually in buffer

        // private constructor : singleton or factory
        private Buffer(){
            super(MAX);
        }

        public void add(int x, int y){
            if(n < MAX){
                points[n].set(x,y);
                n++;
            }
        }

        public void clear(){
            n = 0;
        }

        public void dn(int x, int y) {clear();add(x,y);} //当鼠标按下时调用。它首先清空缓冲区，然后在鼠标按下的位置添加一个新点。
        public void up(int x, int y) {add(x, y);}//当鼠标释放时调用。添加一个点到缓冲区，这通常标志着一次绘制操作的结束。
        public void drag(int x, int y) {add(x,y);}//当鼠标拖动时调用。继续在新位置添加点。
        public boolean hit(int x, int y) {return true;}
        public void show(Graphics g) {drawN(g, n);}
    }

    // -------------------List-----------------------
    public static class List extends ArrayList<Ink> implements I.Show{
        @Override
        public void show(Graphics g) {
            for (Ink i: this) {
                i.show(g);
            }
        }
    }
}
