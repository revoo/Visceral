package com.visceral;

/** Matrix class representing a 4D matrix for linear and affine transformations. */
public class Matrix4f {
    // Internal matrix
    private float[][] matrix;

    public Matrix4f() {
        matrix = new float[4][4];
    }

    /** Sets the matrix to be an identity matrix. */
    public void identity() {
        matrix[0][0] = 1;   matrix[0][1] = 0;   matrix[0][2] = 0;    matrix[0][3] = 0;
        matrix[1][0] = 0;   matrix[1][1] = 1;   matrix[1][2] = 0;    matrix[1][3] = 0;
        matrix[2][0] = 0;   matrix[2][1] = 0;   matrix[2][2] = 1;    matrix[2][3] = 0;
        matrix[2][0] = 0;   matrix[2][1] = 0;   matrix[2][2] = 0;    matrix[3][3] = 1;
    }

    /** Sets a value at x, y location of the matrix. */
    public void set(int x, int y, float value) {
        matrix[x][y] = value;
    }

    /** Equates a matrix the parameter. */
    public void equate(Matrix4f m2) {

    }

}
