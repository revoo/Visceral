package com.visceral;

/** Frames per second counter class to benchmark and monitor performance of graphical elements as they are processing each frame. */

public class FPS {
    private static long previous_frame_time;
    private static int frame_accumulator;

    /** Prints the current calculated frames per second to the standard output stream */
    public static void calculateFPS() {
            // Nano -> Micro -> Milli -> Centi
            long current_frame_time = (long) (Math.floor(System.nanoTime() / 1000000000));
            // When one second will pass this if-block will execute to reset the frame counter
            if (previous_frame_time < current_frame_time) {
                int frames_per_second = frame_accumulator;
                previous_frame_time = current_frame_time;
                frame_accumulator = 0;
                System.out.println("FPS: " + frames_per_second);
            }
            // Accumulate frames while one second is not up
            frame_accumulator++;
   }
}
