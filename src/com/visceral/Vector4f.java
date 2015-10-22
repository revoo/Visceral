package com.visceral;

/** Vector math class with single precision floating point x, y, z, w components */
public class Vector4f {
    private float x;
    private float y;
    private float z;
    private float w;

    public Vector4f(float cx, float cy, float cz, float cw) {
        this.x = cx;
        this.y = cy;
        this.z = cz;
        this.w = cw;
    }

    public Vector4f(float cx, float cy, float cz) {
        this(cx, cy, cz, 1.0f);
    }

    /** Computes magnitude of vector via pythagorean theorem. */
    public double mag(float x, float y, float z, float w) {
        return Math.sqrt( (x * x) + (y * y) + (z * z) + (w * w) );
    }

    /** Computes vector dot product between two vectors and returns a float. */
    public float dot(Vecto4f v2) {
        return ( x * v2.x() + y * v2.y() + z * v2.z() + w * v2.w() );
    }

    /** Computes vector cross product and returns a vector */
    public Vector4f cross(vector4f v2) {
        //Vector4f product
    }

    public float x() { return x; }
    public float y() { return y; }
    public float z() { return z; }
    public float w() { return w; }
}
