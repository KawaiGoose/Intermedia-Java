package music;

import java.awt.*;

public interface I {
    //nesting Interface: control for your Classes recall
    //public interface Draw { public void draw(); }
    public interface Show { public void show(Graphics g); }
    public interface Hit { public void hit(int x, int y); }
    public interface Area extends Hit{ //控制所有mouse的行为
        public void dn(int x, int y);
        public void up(int x, int y);
        public void drag(int x, int y);
    }

}
