import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by egor on 9/29/14.
 */
//машина нашего игрока


public class Player {
    public static final int MAX_V = 100; //максимальная скорость
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM = 550;

    Image img = new ImageIcon("res/Player.png").getImage();

    int v = 5; // скорость
    int dv = 0; // ускорение
    int s = 0; // путь
    //координаты машины
    int x = 30;
    int y = 280;
    int dy = 0;
    //первый слой дороги
    int layer1 = 0;
    int layer2 = 1200;


    //метод отвечающий за движение
    public void move(){
        s += v;
        v += dv;
        if(v <= 0) v = 0;
        if (v >= MAX_V) v = MAX_V;
        y -= dy;
        if(y <= MAX_TOP)y = MAX_TOP;
        if(y >= MAX_BOTTOM)y = MAX_BOTTOM;
        if(layer2 -v <= 0){
            layer1 = 0;
            layer2 = 1200;
        }else {
            layer1 -=v;
            layer2 -=v;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT){
            dv = 5;
        }
        if (key == KeyEvent.VK_LEFT){
            dv = -5;
        }
        if (key == KeyEvent.VK_UP){
            dy = 10;
        }
        if (key == KeyEvent.VK_DOWN){
            dy = -10;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT||key == KeyEvent.VK_LEFT){
            dv = 0;
        }
        if (key == KeyEvent.VK_UP||key == KeyEvent.VK_DOWN){
            dy = 0;
        }

    }
}