public class NBody
{

	public static double readRadius(String fileName)
	{
        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
	}

    public static Body[] readBodies(String fileName)
    {
        
        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble(); 
        Body[] allBodys = new  Body[number];
        for(int i=0;i<number;i++)
        {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allBodys[i] = new Body(xP,yP,xV,yV,mass,img);
        } 
        return allBodys;
    }

    public static void drawBackground(double radius)
    {
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        String imageToDraw = "images/starfield.jpg";
        StdDraw.picture(0,0,imageToDraw);
        //StdDraw.show();

    }

    public static void drawBody(Body[] allBodies)
    {
        int len = allBodies.length;
        for(int i=0;i<len;i++)
        {
            allBodies[i].draw();
        }
    }

    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);

        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        while(t<=T)
        {
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            int i = 0;
            while(i<allBodies.length)
            {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
                i++;
            }

            i = 0;
            while(i<allBodies.length)
            {
                allBodies[i].update(dt,xForces[i],yForces[i]);
                i++;
            }

            drawBackground(radius);
            drawBody(allBodies);
            StdDraw.show();
            StdDraw.pause(10);

            

            t = t+dt;
        }
        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++)
         {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
            allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);   
        }


    }



}