package com.visceral;

/** Vertex data class. */
public class Vertex {
    private int x;
    private int y;
    public Vertex(int xCoord, int yCoord) {
        x= xCoord;
        y= yCoord;
    }
    /** Getter. */
    public int getX() {
        return x;
    }
    /** Getter. */
    public int getY() {
        return y;
    }
    /** Setter. */
    public void setX(int setter) {
        x = setter;
    }
    /** Setter. */
    public void setY(int setter) {
        y = setter;
    }

    /** Checks for equality between any of the vertex coordinates with another vertex v2 */
    public boolean equal(Vertex v2) {
        return ((this.x == v2.x) && (this.y == v2.y));
    }

    /** Computes cross product of two vectors using 3 vertices to mark end points. */
    public float crossProduct(Vertex v2, Vertex v3) {
        // Calculate lengths of vectors
        float x1 = v2.getX() - x;
        float y1 = v2.getY() - y;
        float x2 = v3.getX() - x;
        float y2 = v3.getX() - y;

        return (x1 * y2 - x2 * y1);
    }

}
