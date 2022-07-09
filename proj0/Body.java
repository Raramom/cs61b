public class Body
 {
 	public double xxPos;//x position
 	public double yyPos;//y position
 	public double xxVel;//velocity in the x direction
 	public double yyVel;//velocity in the y direction
 	public double mass;
 	public String imgFileName;
 	public static final double G = 6.67e-11;

 	public Body(double xP, double yP, double xV,
              double yV, double m, String img)
 	{
 		this.xxPos = xP;
 		this.yyPos = yP;
 		this.xxVel = xV;
 		this.yyVel = yV;
 		this.mass = m;
 		this.imgFileName = img;
 	}

 	public Body(Body b)
 	{
 		this.xxPos = b.xxPos;
 		this.yyPos = b.yyPos;
 		this.xxVel = b.xxVel;
 		this.yyVel = b.yyVel;
 		this.mass = b.mass;
 		this.imgFileName = b.imgFileName;
 	}

 	public double calcDistance(Body b)
 	{
 		double x_distance = this.xxPos - b.xxPos;
 		double y_distance = this.yyPos - b.yyPos;
 		double x2_y2 = x_distance*x_distance + y_distance*y_distance;
 		double dis = Math.sqrt(x2_y2);
 		return dis;
 	}

 	public double calcForceExertedBy(Body b)
 	{
 		double dis = this.calcDistance(b);
 		double dis_2 = dis*dis;
 		double force = G*(this.mass)*(b.mass)/dis_2;
 		return force;
 	}

 	public double calcForceExertedByX(Body b)
 	{
 		double force = this.calcForceExertedBy(b);
 		double dis = this.calcDistance(b);
 		double dx = b.xxPos - this.xxPos;
 		double force_x = force*dx/dis;
 		return force_x;
 	}

 	public double calcForceExertedByY(Body b)
 	{
 		double force = this.calcForceExertedBy(b);
 		double dis = this.calcDistance(b);
 		double dy = b.yyPos - this.yyPos;
 		double force_y = force*dy/dis;
 		return force_y;
 	}

 	public double calcNetForceExertedByX(Body[] allBodys)
 	{
 		int len = allBodys.length;
 		double net_force = 0;
 		for(int i=0;i<len;i++)
 		{
 			if(this.equals(allBodys[i]))
 				continue;
 			else
 				net_force = net_force + this.calcForceExertedByX(allBodys[i]);
 		}
 		return net_force;
 	}

 	public double calcNetForceExertedByY(Body[] allBodys)
 	{
 		int len = allBodys.length;
 		double net_force = 0;
 		for(int i=0;i<len;i++)
 		{
 			if(this.equals(allBodys[i]))
 				continue;
 			else
 				net_force = net_force + this.calcForceExertedByY(allBodys[i]);
 		}
 		return net_force;
 	}

 	public void update(double dt,double f_net_x,double f_net_y)
 	{
 		double a_x = f_net_x/this.mass;
 		double a_y = f_net_y/this.mass;
 		this.xxVel = this.xxVel + dt*a_x;
 		this.yyVel = this.yyVel + dt*a_y;
 		this.xxPos = this.xxPos + dt*this.xxVel;
 		this.yyPos = this.yyPos + dt*this.yyVel;
  	}

	public void draw()
	{
		String address = "images//" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,address);
		//StdDraw.show();
	}
  }
