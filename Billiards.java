/*************************************************************************
 *  Compilation:  javac Billiards.java
 *  Execution:    java Billiards
 *  Dependencies: StdDraw.java StdAudio.java
 *                laser.wav pop.wav
 *                fourBall.png sixBall.png eightBall.png
 *
 *  GOAL: Upgrade this simulation to run 3 balls at once 
 *  NOTE: It's okay for now to assume that the images do not interact with one another!
 * 
 *************************************************************************/

public class Billiards { 

    // TO DO: declare global variables as arrays
    // public static double[] rx;          // x position (m)
    // public static double[] ry;          // y position (m)
    // public static double[] vx;          // x velocity (m/s)
    // public static double[] vy;          // y velocity (m/s)
    // public static String[] image;       // name of each ball, including file type
    
    // global variables
    public static double rx = .480, ry = .880;
    public static double vx = .015, vy = .023;
    public static String image = "sixBall.png";

    public static void main(String[] args) {    
        // TO DO:   Declare global array size. 
        // NOTE:    Replace N with the appropriate number.

        // vx = new double[N];
        // vy = new double[N];
        // rx = new double[N];
        // ry = new double[N];
        // image = new String[N];

        // TO DO: initialize global array parameters with a single Loop
        // NOTE:    Set rx components to -.4, 0, .4
        //          Set ry components to -1, 0, 1
        //          Set all vx, vy components to .015, .023 

        // set Canvas parameters
        StdDraw.setCanvasSize(300, 600);
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-2.0, 2.0);

        // main animation loop
        while (true) { 

            // TO DO: do not break until all 3 balls have been pocketed
            if (pocket())
                break; 

            // TO DO: Upgrade simulation to run all balls that have not been pocketed
            if (Math.abs(rx + vx) > .83) { vx = -vx; StdAudio.play("pop.wav"); }
            if (Math.abs(ry + vy) > 1.9) { vy = -vy; StdAudio.play("pop.wav");   }
            rx = rx + vx; 
            ry = ry + vy; 
            StdDraw.picture(0, 0, "billiardsTable.png", 2.25, 4.5);
            StdDraw.picture(rx, ry, image);

            StdDraw.show(10); 
        } 
    } 

    // play runout() simulation if ball is near enough to a pocket
    public static boolean pocket() {
        if (Math.abs(rx + vx) > .8) {
            if (Math.abs(ry + vy)> 1.87) {
                runOut();
                return true;
            }
            if (Math.abs(ry + vy) < 0.03 ) {
                runOut();
                return true;
            }  
        }
        return false;
    }

    // simulate a ball exiting
    // TO DO:   update input paramaters such that runOut() simulation only plays for one ball
    public static void runOut() {
        for (int i = 0; i < 9; i++) {
            rx = rx + vx; 
            ry = ry + vy;
            StdDraw.picture(0, 0, "billiardsTable.png", 2.25, 4.5);
            StdDraw.picture(rx, ry, image);
            StdDraw.show(1); 
        } 
        StdAudio.play("laser.wav");
        StdDraw.picture(0, 0, "billiardsTable.png", 2.25, 4.5);
        StdDraw.show(1); 
        return;
    }
} 
