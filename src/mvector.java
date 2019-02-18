import java.util.Random;

public class mvector {
    public double x;
    public double y;
    public double angle;

    public mvector()
    {

    }
    public mvector(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public static mvector randomVector()
    {
        Random rd = new Random();
        mvector res = new mvector();
        res.angle = rd.nextDouble() * Math.PI * 2;
        res.x = Math.cos(res.angle);
        res.y = Math.sin(res.angle);
        return  res;
    }

    public void mult(double l)
    {
        this.x = l * Math.cos(this.angle);
        this.y = l * Math.sin(this.angle);
    }
    public static double dist(mvector a,mvector b)
    {
        return (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);
    }
}
