package com.visceral;

/** This class simulates a 3D rain effect using perspectives */

public class Rain {
    private final int cdrop_count;
    private final float cspeed;
    private final float cspread;

    private float[] drop_x;
    private float[] drop_y;
    private float[] drop_z;

    public Rain(int drop_count, float speed, float spread) {
        cdrop_count  = drop_count;
        cspeed       = speed;
        cspread      = spread;
        drop_x       = new float[drop_count];
        drop_y       = new float[drop_count];
        drop_z       = new float[drop_count];

        for(int i = 0; i < drop_count; i++)
            spawn_drop(i);
    }

    public void spawn_drop(int index) {
            //The random values have 0.5 subtracted from them and are multiplied
            //by 2 to remap them from the range (0, 1) to (-1, 1).
            drop_x[index] = (float) (((Math.random() - 0.5) * cspread));
            drop_y[index] = (float) (((Math.random() - 0.5) * cspread));
            drop_z[index] = (float) ((Math.random() + 0.001f) * cspread);
    }

    public void render(Bitmap screen, float delta_time) {
            // FOV
            //float ztan = (float) Math.tan(Math.toRadians(80.0));
        float ztan =1;
            screen.paint((byte) 0x00, (byte) 0x00, (byte) 0x00);
            float halfWidth  = screen.getWidth()  / 2.0f;
            float halfHeight = screen.getHeight() / 2.0f;

            for(int i = 0; i < drop_z.length; i++) {
                drop_z[i] -= delta_time * cspeed;

                if (drop_z[i] <= 0)
                    spawn_drop(i);

                //Multiplying the position by (size/2) and then adding (size/2)
                //remaps the positions from range (-1, 1) to (0, size)
                int x = (int) ((drop_x[i]/(drop_z[i] * ztan)) * halfWidth + halfWidth);
                int y = (int) ((drop_y[i]/(drop_z[i] * ztan)) * halfHeight * halfHeight);

                if (x < 0 || x > screen.getWidth() || y < 0 || y > screen.getHeight()) {
                    spawn_drop(i);
                }
                else {
                    screen.drawPixel(x, y, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
                }
            }
    }
}
