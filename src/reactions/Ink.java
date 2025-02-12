package reactions;

import graphics.G;
import music.I;
import music.UC;

import java.awt.*;
import java.util.ArrayList;

public class Ink implements I.Show {

    public static final Buffer BUFFER = new Buffer();
    public static final int K = UC.normSampleSize;

    public Norm norm;
    public G.VS vs;

    public Ink() {
        norm = new Norm();
        vs = BUFFER.bBox.getNewVS();
    }

    @Override
    public void show(Graphics g) {
        g.setColor(UC.inkColor);
        norm.drawAt(g, vs);
    }

    public int dist(Norm n) {
        int res = 0;
        for (int i = 0; i < Norm.N; i++) {
            if (points[i] != null && n.points[i] != null) { // ✅ 避免 NullPointerException
                int dx = points[i].x - n.points[i].x;
                int dy = points[i].y - n.points[i].y;
                res += dx * dx + dy * dy;
            }
        }
        return res;
    }

    // ------------------- Norm (标准化笔画) ----------------------
    public static class Norm extends G.PL {
        public static final int N = UC.normCoordMax;
        public static final int MAX = UC.normCoordMax;
        public static final G.VS NCS = new G.VS(0, 0, MAX, MAX); // Normalized coordinate system

        public Norm() {
            super(N);
            BUFFER.subSample(this);
            G.V.T.set(BUFFER.bBox, NCS);
            transform();
        }

        public void drawAt(Graphics g, G.VS vs) {
            G.V.T.set(NCS, vs);
            if (N > 1) { // ✅ 避免 i-1 变成 -1
                for (int i = 1; i < N; i++) {
                    g.drawLine(points[i - 1].tx(), points[i - 1].ty(), points[i].tx(), points[i].ty());
                }
            }
        }

        public void blend(Norm norm, int nBlend) {
            for (int i = 0; i < N; i++) {
                if (points[i] != null && norm.points[i] != null) { // ✅ 避免 NullPointerException
                    points[i].blend(norm.points[i], nBlend);
                }
            }
        }
    }

    // ------------------- Buffer (临时存储一系列的点) -----------------------
    public static class Buffer extends G.PL implements I.Show, I.Area {
        public static final int MAX = UC.inkBufferMAX;
        public int n; // 当前缓冲区的点数
        public G.BBox bBox = new G.BBox();

        // 单例模式，私有构造方法
        private Buffer() {
            super(MAX);
        }

        public void add(int x, int y) {
            if (n < MAX) {
                points[n].set(x, y);
                n++;
                bBox.add(x, y);
            }
        }

        public void clear() {
            n = 0;
        }

        public void dn(int x, int y) {
            clear();
            add(x, y);
            bBox.set(x, y);
        }

        public void up(int x, int y) {
            add(x, y);
        }

        public void drag(int x, int y) {
            add(x, y);
        }

        public boolean hit(int x, int y) {
            return true;
        }

        public void show(Graphics g) {
            drawN(g, n);
            bBox.draw(g);
        }

        public void subSample(G.PL pl) { // ✅ 避免 IndexOutOfBoundsException
            int k = pl.size();
            if (k <= 1) return;

            for (int i = 0; i < k; i++) {
                int index = i * (n - 1) / (k - 1);
                if (index < this.points.length) {
                    pl.points[i].set(this.points[index]);
                }
            }
        }
    }

    // ------------------- List (用于存储多个 Ink) -----------------------
    public static class List extends ArrayList<Ink> implements I.Show {
        @Override
        public void show(Graphics g) {
            for (Ink i : this) {
                i.show(g);
            }
        }
    }
}
