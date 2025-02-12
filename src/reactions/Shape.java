package reactions;

import graphics.G;
import music.UC;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shape {
    public Prototype.List prototypes = new Prototype.List();
    public String name;

    public Shape(String name) {
        this.name = name;
    }

    //-------------------Prototype---------------
    public static class Prototype extends Ink.Norm{
        int nBlend = 1;
        public void blend(Ink.Norm norm){blend(norm, nBlend);nBlend++;}//C的写法，用++一行的写法取代两行

        //-------------------PrototypeList---------------
        public static class List extends ArrayList<Prototype>{
            public static Prototype bestMatch; //set as side effect of bestDist. python会返回一个tuple，一个显示一个存在global变量
            public int bestDist(Ink.Norm norm){
                bestMatch = null; // assume you would fail
                int bestSoFar = UC.noMatchDist;
                for(Prototype p : this){
                    int d = p.dist(norm);
                    if(d < bestSoFar){
                        bestMatch = p;
                        bestSoFar = d;
                    }
                }
                return bestSoFar;
            }
            private static int m = 10, w = 60;
            private static G.VS showBox = new G.VS(m,m,w,w); // debug routine for the prototype shape --> repeated show prototype
            public void show(Graphics g){
                g.setColor(Color.ORANGE);
                for (int i = 0; i < size(); i++) {
                    Prototype p = get(i);
                    int x = m + i*(m+w);
                    showBox.loc.set(x, m);
                    p.drawAt(g, showBox);
                    g.drawString("" + p.nBlend, x, 20);

                }
            }
        }
    }
}
