import java.util.Random;

public class Population {
    rocket[] rockets;
    private static int generation = -1;
    public static double avg = 0;
    private static rocket best = null;
    public Population(int size,boolean flag)
    {
        generation ++;
        rockets = new rocket[size];
        if(flag) {
            for (int i = 0; i < size; i++) {
                rockets[i] = new rocket();
                rockets[i].pos = new mvector(Main.width / 2, Main.height - 50);
            }
        }
    }

    public void update()
    {
        for(rocket r : rockets)
           if(r != null) r.update();
           myPanel.addText(String.format("Current Generation: %d",generation),5,15);
           myPanel.addText(String.format("Current Best fitness: %.3f",best != null ? best.fitness : 0),5,35);
           myPanel.addText(String.format("Current Generation: %.3f",avg),5,55);
    }
    public rocket random()
    {
        Random t = new Random();
        double a = t.nextDouble();
        for(rocket r : rockets)
        {
            if(a - r.normScore < 0)
                return r;
            else a-=r.normScore;
        }
        return rockets[0];
    }
    public void normalize()
    {
        double sum = 0;
        for(rocket r : rockets)
        {
            sum+=r.fitness;
        }
        for(rocket r : rockets)
        {
            r.normScore = r.fitness / sum;
        }
       this.avg = sum / rockets.length;
    }
    public Population nextGen()
    {
        this.best = this.getBest();
        normalize();
        Population temp = new Population(rockets.length,false);
        for(int i=0;i<temp.rockets.length;i++)
        {
            rocket a = random();
            rocket b = random();
            temp.rockets[i] = a.cross(b);
            temp.rockets[i].mutate();
        }
        return temp;
    }
    public rocket getBest()
    {
        rocket b = rockets[0];
        for(rocket r : rockets) {
            if(r.fitness > b.fitness)
                b = r;
        }
        return b;
    }
}
