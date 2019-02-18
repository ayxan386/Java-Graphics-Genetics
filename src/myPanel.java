import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class myPanel extends JPanel {
    static ArrayList<myShape> shapes;
        public myPanel()
        {
            shapes = new ArrayList<>();
        }
        public void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.white);
            g2.fillRect(0,0,Main.width,Main.height);
            for(myShape sp : shapes)
            {
                g2.setColor(sp.color);
                if(sp.fill)
                {
                    g2.fill(sp.shape);
                }
                else
                {
                    g2.draw(sp.shape);
                }
            }
            shapes.clear();
        }
    public static void drawRect(double x,double y,double h,double w,Color c,boolean fill)
    {
        Shape s = new Rectangle2D.Double(x,y,w,h);
        myShape temp = new myShape(s,c,fill);
        shapes.add(temp);
    }
    public static void drawRect(double x,double y,double h,double w,Color c,boolean fill,double angle)
    {
        Shape s = new Rectangle2D.Double(x,y,w,h);
        myShape temp = new myShape(s,c,fill);
        temp.angle = angle;
        shapes.add(temp);
    }
    public static void drawCircle(double x,double y,double h,double w,Color c,boolean fill)
    {
        Shape s = new Arc2D.Double(x,y,w,h,0,360,Arc2D.OPEN);
        myShape temp = new myShape(s,c,fill);
        shapes.add(temp);
    }
    public static void drawLine(double x0,double y0,double x,double y,Color c)
    {
        Shape s = new Line2D.Double(x0,y0,x,y);
        myShape temp = new myShape(s,c,false);
        shapes.add(temp);
    }
}
