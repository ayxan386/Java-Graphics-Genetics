import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    JFrame mframe = new JFrame("Rockets move");
    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        m.init();

        double fps = 60;
        long per = (long)(1e+9/fps);
        long curr = System.nanoTime();

        while(true)
        {
            if(curr + per < System.nanoTime())
            {
                curr +=per;
                m.draw();
            }
            Thread.sleep(30);
        }
    }
    public static int width = 800;
    public static int height = 800;
    public int frames = 0;
    public Main()
    {
        mframe.setSize(width,height);
        mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mframe.setLocationRelativeTo(null);
        mframe.setResizable(false);
        myPanel panel = new myPanel();
        mframe.add(panel);
        mframe.setVisible(true);
    }
    public Random r = new Random();
    public static mvector target;
    public Population pop;

    public void init()
    {
        target = new mvector(width/2,50);
        pop = new Population(100,true);
    }
    public void draw()
    {
        frames++;
        if(frames % 400 == 0) {
            pop = pop.nextGen();
            return;
        }
        pop.update();
        myPanel.drawCircle(target.x - 40,target.y - 40,40,40,Color.red,true);
        mframe.repaint();
    }

}