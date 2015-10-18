package com.visceral;

/** This class simulates a 3D rain effect using perspectives */

public class Triangle {
    private final float cspeed;

    private float drop_x;
    private float drop_y;
    private float drop_z;

    public Triangle(float speed) {
        cspeed       = speed;
    }

    public void spawn_drop(int index) {
            //The random values have 0.5 subtracted from them and are multiplied
            //by 2 to remap them from the range (0, 1) to (-1, 1).
            drop_x = (float) (((Math.random() - 0.5) ));
            drop_y = (float) (((Math.random() - 0.5) ));
            drop_z = (float) ((Math.random() + 0.001f));
    }

    public void render(Bitmap screen, float delta_time, Rasterizer triangle) {
            // FOV
            //float ztan = (float) Math.tan(Math.toRadians(80.0));
            float ztan =1;
            screen.paint((byte) 0x00, (byte) 0x00, (byte) 0x00);
            float halfWidth  = screen.getWidth()  / 2.0f;
            float halfHeight = screen.getHeight() / 2.0f;

            for(int i = 0; i < 1; i++) {
                drop_z -= delta_time * cspeed;

                if (drop_z <= 0)
                    spawn_drop(2);

                //Multiplying the position by (size/2) and then adding (size/2)
                //remaps the positions from range (-1, 1) to (0, size)
                int x = (int) ((drop_x/(drop_z * ztan)) * halfWidth + halfWidth);
                int y = (int) ((drop_y/(drop_z * ztan)) * halfHeight * halfHeight);

                if (x < 0 || x > screen.getWidth() || y < 0 || y > screen.getHeight()) {
                    spawn_drop(i);
                }
                else {
                    Vertex v1 = new Vertex(x, y);
                    spawn_drop(i);
                    x = (int) ((drop_x /(drop_z * ztan)) * halfWidth + halfWidth);
                    y = (int) ((drop_y /(drop_z * ztan)) * halfHeight * halfHeight);
                    Vertex v2 = new Vertex(x, y);
                    spawn_drop(i);
                    x = (int) ((drop_x/(drop_z * ztan)) * halfWidth + halfWidth);
                    y = (int) ((drop_y/(drop_z * ztan)) * halfHeight * halfHeight);
                    Vertex v3 = new Vertex(x, y);
                    triangle.rasterize(v1, v2 ,v3);

                }
            }
    }
}
