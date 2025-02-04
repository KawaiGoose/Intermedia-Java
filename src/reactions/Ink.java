package reactions;

import graphics.G;
import music.I;
import music.UC;

import java.awt.*;
import java.util.ArrayList;

public class Ink implements I.Show{

    public static final Buffer buffer = new Buffer();

    public Ink(){
        super(Buffer.n);
        for (int i = 0; i < Buffer.n; i++) {
            points[i].set(BUFFER.point[i]);
        }
    }

    @Override
    public void show(Graphics g) {
        // stub (相当于TODO)
//        g.setColor(Color.BLACK);
//        g.fillRect(100, 100, 100, 100);
//        g.drawString("Stub Ink.Show", 300, 100);
        g.setColor(UC.intColor);
        draw(g);
    }

    // -------------------Buffer-----------------------
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

        public void dn(int x, int y) {clear();add(x,y);}
        public void up(int x, int y) {clear();add(x,y);}
        public void drag(int x, int y) {add(x,y);}
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
