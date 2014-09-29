import javax.swing.*;


/**
 * Created by egor on 9/29/14.
 */
public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Java F1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.add(new Road()); //добавили панель дороги на фрейм
        frame.setVisible(true);


    }

}