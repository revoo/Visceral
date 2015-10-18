package com.visceral;

/** PURPOSE: This class contains the implementation of the display logic for the rendering engine. Functions including
    displaying windows, swapping buffers, drawing to the display buffer are altered here.
 */

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

public class Display extends Canvas {
    private final JFrame         frame;
    private final Rasterizer     frameBuffer;
    private final byte[]         displayPixels;
    private final Graphics       graphics;
    private final BufferStrategy bufStrategy;
    private final BufferedImage  image;

    public Display(int width, int height, String title) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);

        // Pixel buffer setup. Retrieve an image buffer with the form of Blue-Green-Red for the graphics object to draw to the screen */
        frameBuffer = new Rasterizer(width, height);
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        displayPixels = ( (DataBufferByte) image.getRaster().getDataBuffer()).getData();
        frameBuffer.paint((byte) 0x00, (byte) 0x00, (byte) 0x00);

        // Window context
        frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(title);

        // Double-buffering technique?
        createBufferStrategy(1);
        bufStrategy = getBufferStrategy();
        graphics = bufStrategy.getDrawGraphics();
    }

    public void visible(boolean condition) {
        frame.setVisible(condition);
    }

    public void close() {
        frame.dispose();
    }

    // TODO: FPS-Lock
    public void swapBuffers(boolean show_fps, int FPS_lock) {
        // FPS-lock logic
        if (show_fps)
            FPS.calculateFPS();
        // Copy bitmap to display buffer in the form of BGR discarding the alpha
        frameBuffer.toBGR(displayPixels);
        graphics.drawImage(image, 0, 0, frameBuffer.getWidth(), frameBuffer.getHeight(), null);
        // Actually show the buffer
        bufStrategy.show();
    }

    public void paint(byte r, byte g, byte b)  { frameBuffer.paint(b, r, g); }

    public Rasterizer getFrameBuffer() {
        return frameBuffer;
    }
}


