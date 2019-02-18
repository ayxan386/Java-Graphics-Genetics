import java.util.Random;

public class Population {
    rocket[] rockets;
    static int generation = -1;
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
    }
    public Population nextGen()
    {
        rocket best = rockets[0];
        for(rocket r : rockets) {
            if(r.fitness > best.fitness)
                best = r;
        }
        System.out.println("Current generation :" + generation);
        System.out.printf("Current best fitness :%.3f\n",best.fitness);
//        System.out.println("last id :" + best.lasti);
        Population temp = new Population(rockets.length,false);
        normalize();
        for(int i=0;i<temp.rockets.length;i++)
        {
            rocket a = random();
            rocket b = random();
            temp.rockets[i] = a.cross(b);
            temp.rockets[i].mutate();
        }
        return temp;
    }
}
