import java.awt.*;
import java.util.Random;

public class rocket {

        public static double maxv = 5;
        public static double w = 20,h = 40;
        public static double mutationRate = 0.09;
        public static Random r = new Random();

        int lasti = 0;
        double normScore;
        mvector pos;
        mvector[] vel;
        double fitness;
        Color c;
        public rocket()
        {
            fitness = 0;
            normScore = 0;
            c = Color.black;
            vel = new mvector[400];
            for(int i=0;i<vel.length;i++) {
                vel[i] = mvector.randomVector();
                vel[i].mult(r.nextDouble() * maxv);
            }
        }
        public void update()
        {
            if(pos.x - w < 0 || pos.x + w > Main.width
            || pos.y - w < 0 || pos.y + w > Main.height)
            {
                c = Color.BLUE;
                draw();
                return;
            }
            if(mvector.dist(pos,Main.target) < (w + 40) * (w + 40))
            {
                fitness = 1.5;
                c = Color.green;
                draw();
                return;
            }
            pos.x += vel[lasti].x;
            pos.y += vel[lasti].y;
            lasti++;
            if(lasti == vel.length)lasti = 0;
            calcFitness();
            draw();
        }
        public void draw()
        {
            myPanel.drawCircle(pos.x - w,pos.y - w,w,w, c,true);
        }
        public rocket cross(rocket b)
        {
            rocket temp = new rocket();

            temp.pos = new mvector(Main.width/2,Main.height-50);

            for(int i=0;i<vel.length/2;i++)
                temp.vel[i] = this.vel[i];
            for(int i=vel.length/2;i<vel.length;i++)
                temp.vel[i] = b.vel[i];
            return  temp;
        }
        public void mutate()
        {
            for(int i=0;i<vel.length;i++)
            {
                if(r.nextDouble() < mutationRate) {
                    vel[i] = mvector.randomVector();
                    vel[i].mult((r.nextDouble()*maxv));
                    return;
                }
            }
        }
        public void calcFitness()
        {
            fitness = (100000./(1 + mvector.dist(pos,Main.target)));
        }
}
