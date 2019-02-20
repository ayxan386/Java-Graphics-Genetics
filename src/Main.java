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
    public rocket best;
//    public rocket best1;
    public obstacle obs1;
    public void init()
    {
        best = null;
//
//        best1 = new rocket();
//        best1.pos = new mvector(width/2,height-50);
//        best1.c = Color.green;
//        for(int i=0;i<best1.vel.length;i++)
//        {
//            best1.vel[i] = mvector.vectorFromAngle(-Math.PI/2);
//            best1.vel[i].mult(rocket.maxv);
//        }
        obs1 = new obstacle();
        obs1.pos = new mvector(170,600);
        obs1.width = 150;
        obs1.height = 20;
        target = new mvector(width/2,50);
        pop = new Population(1000,true);
    }
    public void draw()
    {
        frames++;
        if(frames % 400 == 0) {
            best = pop.getBest();
            pop = pop.nextGen();
            return;
        }
        pop.update();
        obs1.update(pop.rockets);
  //      best1.update();
        if(best != null)
            myPanel.drawLine(0,best.pos.y,width,best.pos.y,Color.MAGENTA);
        myPanel.drawCircle(target.x - 40,target.y - 40,40,40,Color.red,true);
        mframe.repaint();
    }

}