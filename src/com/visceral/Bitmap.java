package com.visceral;

/** Class holding the raster logic dealing with the framebuffer and pixel data. */

public class Bitmap {
    private final int width;
    private final int height;
    private final byte pixels[];

    public Bitmap(int c_width, int c_height) {
        width  = c_width;
        height = c_height;
        pixels = new byte[c_width * c_width * 4];
    }

    /** Set the buffer pixels colors to a certain value to be displayed across the entire screen space. */
    public void paint(byte b, byte g, byte r) {
        //Arrays.fill(pixels, fillColor);
        int pixel_size = pixels.length - 3;
        for(int index = 0; index < pixel_size; index += 4) {
            pixels[index + 1] = b;
            pixels[index + 2] = g;
            pixels[index + 3] = r;
        }
    }

    /** Draws a pixel to the bitmap buffer at location (x,y) with colors byte BGR. Move to actual display buffer to output. */
    public void drawPixel(int x, int y, byte a, byte b, byte r, byte g) {
        // locate the pixel on the screen space using x + y * width times number of pixel components: RGBA.
        int index = (x + y * width) * 4;
        pixels[index    ] = a;
        pixels[index + 1] = r;
        pixels[index + 2] = g;
        pixels[index + 3] = b;
    }
    /** Copy bitmap pixels to the screen space buffer to be outputted to screen space using the Graphics object drawImage method. */
    public void toBGR(byte[] BGR) {
        // Multiply render_area by 3 because each pixel has 3 color components: red, green, blue.
        int render_area = width * height * 3;
        for(int BGR_index = 0, p_index = 1; BGR_index < render_area; BGR_index += 3, p_index += 4) {
              //BGR[i * 3] = pixels[i * 4];
              BGR[BGR_index]     = pixels[p_index];
              BGR[BGR_index + 1] = pixels[p_index + 1];
              BGR[BGR_index + 2] = pixels[p_index + 2];
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}