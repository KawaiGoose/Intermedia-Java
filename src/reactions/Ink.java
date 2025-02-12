package reactions;

import graphics.G;
import music.I;
import music.UC;

import java.awt.*;
import java.util.ArrayList;

public class Ink /** extends G.PL */ implements I.Show{

    public static final Buffer BUFFER = new Buffer();

    public static final int K = UC.normSampleSize;

//    public static G.VS TEMP = new G.VS(100,100,100,100);
    public Norm norm;
    public G.VS vs;

    public Ink(){
        norm = new Norm();
        vs = BUFFER.bBox.getNewVS();

        //super(BUFFER.n);
//        for (int i = 0; i < BUFFER.n; i++) {
//            points[i].set(BUFFER.points[i]);
//        }
//        super(K); //(二)
//        BUFFER.subSample(this);
//        G.V.T.set(BUFFER.bBox, TEMP);
//        transform();
//        G.V.T.set(TEMP, BUFFER.bBox.getNewVS());
//        transform();
    }

    @Override
    public void show(Graphics g) {
        // stub (相当于TODO)
//        g.setColor(Color.BLACK);
//        g.fillRect(100, 100, 100, 100);
//        g.drawString("Stub Ink.Show", 300, 100);
//        g.setColor(UC.inkColor);
//        draw(g);
        g.setColor(UC.inkColor);
        norm.drawAt(g,vs);
    }

    public int dist(Norm n){
        int res = 0;
        for (int i = 0; i < N; i++) {
            int dx = points[i].x - n.points[i];
            int dy = points[i].x - n.points[i].y;
            res += dx*dx + dy*dy;
        }
        return res;
    }
    // -------------------Norm----------------------
    public static class Norm extends G.PL{
        public static final int N = UC.normCoordMax, MAX = UC.normCoordMax;
        public static final G.VS NCS = new G.VS(0,0,MAX,MAX); //Normalized coordinate system

        public Norm() {
            super(N);
            BUFFER.subSample(this);
            G.V.T.set(BUFFER.bBox, NCS);
            transform();
        }
        public void drawAt(Graphics g, G.VS vs){
            G.V.T.set(NCS, vs);
            for (int i = 0; i < N; i++) {
                g.drawLine(points[i - 1].tx(), points[i - 1].ty(), points[i].tx(), points[i].ty());
            }
        }

        public void blend(Norm norm, int nBlend){
            for (int i = 0; i < N; i++) {
                points[i].blend(norm.points[i],nBlend);

            }
        }
    }

    // -------------------Buffer-----------------------临时存储一系列的点
    public static class Buffer extends G.PL implements I.Show, I.Area{
        public static final int MAX = UC.inkBufferMAX;
        public int n; //how many points actually in buffer
        public G.BBox bBox = new G.BBox();


        // private constructor : singleton or factory
        private Buffer(){
            super(MAX);
        }

        public void add(int x, int y){
            if(n < MAX){
                points[n].set(x,y);
                n++;
                bBox.add(x, y);
            }
        }

        public void clear(){
            n = 0;
        }

        public void dn(int x, int y) {clear();add(x,y);bBox.set(x, y);} //当鼠标按下时调用。它首先清空缓冲区，然后在鼠标按下的位置添加一个新点。+ bbox
        public void up(int x, int y) {add(x, y);}//当鼠标释放时调用。添加一个点到缓冲区，这通常标志着一次绘制操作的结束。
        public void drag(int x, int y) {add(x,y);}//当鼠标拖动时调用。继续在新位置添加点。
        public boolean hit(int x, int y) {return true;}
        public void show(Graphics g) {drawN(g, n);bBox.draw(g);} // + bbox
        public void subSample(G.PL pl){ // + subsample 功能
            int k = pl.size();
            for (int i = 0; i < k; i++) {
                pl.points[i].set(this.points[i * (n - 1) / (k - 1)]);
            }
        }

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
