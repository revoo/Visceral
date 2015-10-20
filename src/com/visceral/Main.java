package com.visceral;

public class Main {
    public static void main(String[] args) {
	    Display display = new Display(800, 600, "Visceral");
        display.visible(true);
        //Triangle rainy_mood = new Triangle(1);
        Rasterizer triangle = display.getFrameBuffer();
        Vertex p1 = new Vertex(100, 100);
        Vertex p2 = new Vertex(0, 200);
        Vertex p3 = new Vertex(80, 300);
        Bitmap screen = display.getFrameBuffer();


        long prevTime = System.nanoTime();
        while(true) {
            long curTime = System.nanoTime();
            float dTime = (float) ((curTime - prevTime) / 100000000.0);
            prevTime = curTime;

            //triangle.drawLine(200, 100, 500, 500);

            //triangle.scanlineEdge(p1, p2, 1);
           //triangle.ScanConvertTriangle(p1, p2, p3, 0);
            //for(int i = 100; i < 200; i++) {
            //    triangle.initScanBuffer(i, 300 - i, 300 + i);
            //}
            //triangle.drawLine(200, 200, 500, 400);
            //triangle.drawLine(500, 500, 200, 200);
            triangle.rasterize(p1, p2, p3);
            //screen.drawPixel(100, 100, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x00);
            display.swapBuffers(true, 0);
        }
    }
}
