import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

/**
 * Created by egor on 9/29/14.
 */
public class Road extends JPanel implements ActionListener, Runnable {
    //каждые двадцать милиссекунд выполняем функцию actionPerformed
    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/bg_road.png")).getImage();
    //создаем экземпляр игрока
    Player p = new Player();

    //Создаем врагов в отдельном потоке, через коллекциюю
    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies = new ArrayList<Enemy>();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    //создаем слушатель клавиатуры
    private class MyKeyAdapter extends KeyAdapter {
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

    // все рисуем здесь
    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        //рисуем машину
        g.drawImage(p.img, p.x, p.y, null);

        //рисуем спидометр
        double v = (200/Player.MAX_V) * p.v;
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Speed: " + v + "MPH", 100, 30);


        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();
                g.drawImage(e.img, e.x, e.y, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();

    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())){
               JOptionPane.showMessageDialog(null, "YOU LOSE!!!");
                System.exit(1);
            }
        }
    }

    @Override
    public void run() {
        //создаем врагов со случайной задержкой через rand
        //добавляем врагов в арайЛист
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200, rand.nextInt(600), rand.nextInt(60), this));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}