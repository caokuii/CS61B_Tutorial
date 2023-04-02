
public class Test {
    public static void main(String[] args){
        In in = new In("./data/planets.txt");
        int num = in.readInt();
        double radius = in.readDouble();
        String s = in.readString();
        System.out.println(s);
        String imageToDraw = "./images/starfield.jpg";
        /** Sets up the universe so it goes from
         * -100, -100 up to 100, 100 */
        StdDraw.setScale(-100, 100);

        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(-100, -100, imageToDraw);
        StdDraw.picture(0, 100, imageToDraw);
        StdDraw.picture(-100, 0, imageToDraw);
        StdDraw.picture(0, -100, imageToDraw);

        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
        StdDraw.show();
        StdDraw.pause(5);

        String str1 = "Hello";
        String str2 = "world!";
        String result = str1 + " " + str2;
        System.out.println(result);
    }
}
