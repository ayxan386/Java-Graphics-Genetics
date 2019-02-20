import java.awt.*;

public class obstacle {
    mvector pos;
    double width;
    double height;

    public obstacle()
    {
        pos = new mvector(400,400);
        width = 50;
        height = 50;
    }
    public obstacle(mvector pos, double width, double height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public void draw()
    {
        myPanel.drawRect(pos.x,pos.y,width,height, Color.darkGray,true);
    }
    public void update(rocket[] rockets)
    {
        draw();
        for(rocket r : rockets)
        {
            if(r.pos.x - r.w <= this.pos.x + width )//|| r.pos.x + r.w >= this.pos.x)
            {
                if((r.pos.y >= this.pos.y && r.pos.y <= this.pos.y + height))
                    r.stop = true;
            }
            //if(r.pos.y + r.w >= this.pos.y ||)
                if(r.pos.y + r.w <= this.pos.y + height) {
                if ((r.pos.x <= this.pos.x + width && r.pos.x >= this.pos.x))
                    r.stop = true;
                 }
        }
    }
}
