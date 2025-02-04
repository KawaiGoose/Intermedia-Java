
package graphics;

import java.awt.*;
import java.util.Random;

public class G {
    public static void spline(Graphics g, int ax, int ay, int bx, int by, int cx, int cy, int n) {
        if (n == 0) {g.drawLine(ax, ay, cx, cy);return;}
        int abx = (ax + bx) / 2, aby = (ay + by) / 2;
        int bcx = (bx + cx) / 2, bcy = (by + cy) / 2;
        int abcx = (abx + bcx) / 2, abcy = (aby + bcy) / 2;
        spline(g, ax, ay, abx, aby, abcx, abcy, n-1);
        spline(g, abcx, abcy, bcx, bcy, cx, cy, n-1);

    }

    //生成一个随机颜色
    public static Random RDN = new Random(); //初始化Random对象RDN

    public static int rnd(int max) {
        return RDN.nextInt(max);
    } //rnd函数：调用RDN生成随机变量

    public static Color rndColor() {
        return new Color(rnd(256), rnd(256), rnd(256)); //给Color赋值这些随机变量，生成一个随机颜色
    }

    //
    public static void clearScreen(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 5000, 5000);
    }

    // ----------- V ------------------
    public static class V {
        public int x, y;

        public V(int x, int y) {
            this.set(x, y);
        }

        public void set(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void add(V v) {
            x += v.x;
            y += v.y;
        } // vector addition

        public void set(V v) {set(v.x, v.y);}
    }

    // ----------- VS ----------------
    public static class VS {
        public V loc, size;

        public VS(int x, int y, int w, int h) {
            loc = new V(x, y);
            size = new V(w, h);
        }

        public void fill(Graphics g, Color c) {
            g.setColor(c);
            g.fillRect(loc.x, loc.y, size.x, size.y);
        }

        public boolean hit(int x, int y) {
            return loc.x <= x && loc.y <= y && x <= (loc.x + size.x) && y <= (loc.y + size.y);
        }

        public int xL(){return loc.x;}
        public int xH(){return loc.x + size.x;}
        public int xM(){return loc.x + size.x / 2;}
        public int yL(){return loc.y;}
        public int yH(){return loc.y + size.y;}
        public int yM(){return loc.y + size.y / 2;}

    }

    //-----------------------LoHi---------------------
    public static class LoHi {
    }

    //-----------------------BBox---------------------
    public static class BBox {
    }

    //-----------------------PL-----------------------
    public static class PL {
        public V[] points;
        public PL(int count) {
            points = new V[count];
            for (int i = 0; i < count; i++) {
                points[i] = new V(0,0);
            }
        }
        public int size(){
            return points.length;
        }

        public void drawN(Graphics g, int n){ //画一个initial的line
            for (int i = 0; i < n; i++) {
                g.drawLine(points[i - 1].x, points[i - 1].y, points[i].x, points[i].y);
            }
        }
        public void draw(Graphics g){
            drawN(g, points.length);
        }
    }
}

