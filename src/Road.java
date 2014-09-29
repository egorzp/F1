import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by egor on 9/29/14.
 */
public class Road extends JPanel implements ActionListener {
    //каждые двадцать милиссекунд выполняем функцию actionPerformed
    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon("res/bg_road.png").getImage();
    //создаем экземпляр игрока
    Player p = new Player();

    public Road(){
        mainTimer.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    //создаем слушатель клавиатуры
    private class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            p.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            p.keyReleased(e);
        }
    }

    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        //рисуем машину
        g.drawImage(p.img, p.x, p.y, null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();

    }
}