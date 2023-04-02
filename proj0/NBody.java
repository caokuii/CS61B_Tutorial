

public class NBody {
    private static int num;//系统中Planet的个数
    private static double radius;//系统的半径

    public static double readRadius(String PlanetsTxtPath){
        In in = new In(PlanetsTxtPath);
        num = in.readInt();
        radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String PlanetsTxtPath){
        In in = new In(PlanetsTxtPath);
        num = in.readInt();
        radius = in.readDouble();
        Planet[] planets = new Planet[num];
        String[] s = new String[6];
        for(int i = 0; i < num; i++){
            for(int j = 0; j < 6; j++){
                s[j] = in.readString();
                //System.out.println(s[j]);
            }
            planets[i] = new Planet(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3]), Double.parseDouble(s[4]), s[5]);
        }
        return planets;
    }

    public static void main(String[] args){
        double T, dt;
        String fileName = args[2];
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        readRadius(fileName);
        Planet[] planets = readPlanets(fileName);

        String imageToDraw = "./images/starfield.jpg";
        StdDraw.pause(2000);

        for(int i = 0; i < NBody.num; i++){
            planets[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        double time;
        StdAudio.play("./audio/2001.mid");
        for(time = 0; time < T; time += dt){
            double[] xForces = new double[NBody.num];
            double[] yForces = new double[NBody.num];
            for(int i = 0; i < NBody.num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < NBody.num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            /** Sets up the universe so it goes from
             * -100, -100 up to 100, 100 */
            StdDraw.setScale(-radius, radius);
            /* Clears the drawing window. */
            StdDraw.clear();
            StdDraw.picture(-radius, radius, imageToDraw);
            StdDraw.picture(0, radius, imageToDraw);
            StdDraw.picture(-radius, 0, imageToDraw);
            StdDraw.picture(0, -radius, imageToDraw);
            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            for(int i = 0; i < NBody.num; i++){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }
        StdAudio.close();
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", NBody.radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}
