import javax.swing.*;
import java.awt.*;

/**
 * Created by egor on 30.09.2014.
 */
public class Enemy {
    int x;
    int y;
    int v;
    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/Enemy.png")).getImage();
    Road road;
    public Rectangle getRect(){
        return new Rectangle(x, y, 138, 62);
    }
    public Enemy(int x, int y, int v, Road road) {
        this.v = v;
        this.y = y;
        this.x = x;
        this.road = road;
    }

    public void move() {
        x = x - road.p.v + v;
    }
}
