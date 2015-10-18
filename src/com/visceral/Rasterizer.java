package com.visceral;

/** Uses scan-line rasterization algorithm to render convex polygons. Extends Bitmap class. */

public class Rasterizer extends Bitmap {
    private final int[] scanBuffer;

    public Rasterizer(int width, int height) {
        super(width, height);
        scanBuffer = new int[height * 2];
    }

    public void initScanBuffer(int yCoord, int xMin, int xMax) {
            // Multiply index by 2 to not wipe previous xMax value on every new iteration
            scanBuffer[yCoord * 2]     = xMin;
            scanBuffer[yCoord * 2 + 1] = xMax;
    }

    /** Draw a line using Bresenham's line algorithm (modified DDA). */
    public void drawLine(int x1, int y1, int x2, int y2) {
        // Calculate delta for x and y for slope calculation
        float dx = x2 - x1;
        float dy = y2 - y1;
        float slope = dy / dx;
        float y = slope;

        for(int i = x1; i < x2; i++) {
            drawPixel(i,(int) y + y1, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x00);
            // Anti-aliasing attempt
            drawPixel(i + 1,(int) y + y1 + 1, (byte) 0x01, (byte) 0xFF, (byte) 0x00, (byte) 0x00);
            drawPixel(i - 1,(int) y + y1 -1, (byte) 0x01, (byte) 0xFF, (byte) 0x00, (byte) 0x00);
            y += slope;
        }
    }

    /** Assembles triangle from 3 given vertices. Sorts vertices from min y-value to max y-value. */
    public void rasterize(Vertex min_y, Vertex mid_y, Vertex max_y) {
        Vertex[] vertices = {min_y, mid_y, max_y};
        // Checking for identical vertices
        if ((vertices[0].equal(vertices[1]) || (vertices[0].equal(vertices[2])) || (vertices[1].equal(vertices[2])))) {
            System.out.println("Error assembling triangle: two or more of the vertices are equal.");
            System.exit(99);
        }

        // Sort vectors y-values from min to max with insertion sort
        for(int i = 0; i < 2; i++) {
            for(int j = 1; j < vertices.length; j++) {
                if (vertices[i].getY() > vertices[j].getY()) {
                    int temp_y = vertices[i].getY();
                    int temp_x = vertices[i].getX();
                    vertices[i].setY(vertices[j].getY());
                    vertices[i].setX(vertices[j].getX());
                    vertices[j].setY(temp_y);
                    vertices[j].setX(temp_x);
                }
            }
        }

        // Compute cross product to check coordinate space handedness to see which side to draw triangle
        // TODO: Understand handedness operation
        float area = vertices[0].crossProduct(vertices[2], vertices[1]);
        int handedness = area >= 0 ? 1 : 0;

        scanlineEdge(min_y, max_y, handedness);
        scanlineEdge(min_y, mid_y, 1 - handedness);
        scanlineEdge(mid_y, max_y, 1 - handedness);
        // Output to scan buffer to eventually draw into the framebuffer
        drawScanBuffer(vertices[0].getY(), vertices[2].getY());
    }

    public void drawScanBuffer(int yMin, int yMax) {
        for(int i = yMin; i < yMax; i++) {
            int xMin = scanBuffer[i * 2];
            int xMax = scanBuffer[i * 2 + 1];
            for(int j = xMin ; j < xMax; j++) {
                drawPixel(j, i, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x00);
            }
        }
    }

    public void scanlineEdge(Vertex min_y, Vertex max_y, int scanIndex) {
            int y1 = min_y.getY();
            int y2 = max_y.getY();
            int x1 = min_y.getX();
            int x2 = max_y.getX();

            float dx = x2 - x1;
            float dy = y2 - y1;

            if (dy <= 0)
                return;

            float xslope = dx / dy;
            float x     = (float) x1;

            for(int i = y1; i < y2; i++) {
                scanBuffer[i * 2 + scanIndex] = (int) x;
                x += xslope;
            }
    }

 }
