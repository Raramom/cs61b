public class NBody
{

	public static double readRadius(String fileName)
	{
        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
	}

    public static Planet[] readPlanets(String fileName)
    {
        
        In in = new In(fileName);
        int number = in.readInt();
        double radius = in.readDouble(); 
        Planet[] allPlanets = new  Planet[number];
        for(int i=0;i<number;i++)
        {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP,yP,xV,yV,mass,img);
        } 
        return allPlanets;
    }

    private static void drawBackground(double radius)
    {
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        String imageToDraw = "images/starfield.jpg";
        StdDraw.picture(0,0,imageToDraw);
        //StdDraw.show();

    }

    private static void drawPlanet(Planet[] allPlanets)
    {
        int len = allPlanets.length;
        for(int i=0;i<len;i++)
        {
            allPlanets[i].draw();
        }
    }

    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);

        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        while(t<=T)
        {
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            int i = 0;
            while(i<allPlanets.length)
            {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
                i++;
            }

            i = 0;
            while(i<allPlanets.length)
            {
                allPlanets[i].update(dt,xForces[i],yForces[i]);
                i++;
            }

            drawBackground(radius);
            drawPlanet(allPlanets);
            StdDraw.show();
            StdDraw.pause(10);

            

            t = t+dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++)
         {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
            allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }


    }



}