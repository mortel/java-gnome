/*
 * Pixbuf.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gdk;

import java.io.File;
import java.io.FileNotFoundException;

import org.gnome.glib.GlibException;

/**
 * An image in memory.
 * 
 * <p>
 * <i> Pixbuf is just here to be efficicent at handling images that are going
 * to placed in your GTK user interfaces. If you want to draw on an image, use
 * Cairo's ImageSurgace. If you want to otherwise manipualte the image, use a
 * dedicated image processing library to load the data as it will doubtless
 * provide for more efficient storage anticipating the processing tasks it
 * will facilitate.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public class Pixbuf extends org.gnome.glib.Object
{
    protected Pixbuf(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Pixbuf object from the image found in
     * <code>filename</code>.
     * 
     * @since 4.0.5
     */
    /*
     * Trapping an exception in a constructor is tricky; you have to use an
     * auxiliary helper method.
     */
    public Pixbuf(String filename) throws FileNotFoundException {
        super(checkPixbufFromFile(filename));
    }

    /*
     * First check the file exists first, allowing us to isolate the GError
     * representing image format problems. We'll need a more efficient GError
     */
    private static long checkPixbufFromFile(String filename) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + "not found");
        }
        try {
            return GdkPixbuf.createPixbufFromFile(filename);
        } catch (GlibException ge) {
            // FIXME change to something more image related.
            throw new RuntimeException(ge.getMessage());
        }
    }

    /**
     * Construct a new Pixbuf object by loading an image from a given
     * filename, but scaling it. The image will be scaled to fit the supplied
     * width and height, preserving the aspect ratio or not based on the value
     * of the fourth parameter.
     * 
     * <p>
     * If preserving the aspect ratio,
     * <ul>
     * <li>width of <code>-1</code> will cause the image to be scaled to
     * the exact given height
     * <li>height of <code>-1</code> will cause the image to be scaled to
     * the exact given width.
     * </ul>
     * When not preserving aspect ratio,
     * <ul>
     * <li>a width or height of <code>-1</code> means to not scale the
     * image at all in that dimension.
     * </ul>
     * 
     * @since 4.0.5
     */
    public Pixbuf(String filename, int width, int height, boolean preserveAspectRatio)
            throws FileNotFoundException {
        super(checkPixbufFromFileAtScale(filename, width, height, preserveAspectRatio));
    }

    private static long checkPixbufFromFileAtScale(String filename, int width, int height,
            boolean preserveAspectRatio) throws FileNotFoundException {
        final File target;

        target = new File(filename);
        if (!target.exists()) {
            throw new FileNotFoundException(target + "not found");
        }
        try {
            return GdkPixbuf.createPixbufFromFileAtScale(filename, width, height, preserveAspectRatio);
        } catch (GlibException ge) {
            // FIXME change to something more image related.
            throw new RuntimeException(ge.getMessage());
        }
    }

    /**
     * Write this Pixbuf to a file.
     * 
     * <p>
     * The various file formats that GDK is actually capable of writing to are
     * specified by the constants on PixbufFormat. For example, you can save a
     * screenshot as a PNG using:
     * 
     * <pre>
     * Pixbuf p;
     * ...
     * 
     * p.save(&quot;Screenshot.png&quot;, PixbufFormat.PNG);
     * </pre>
     * 
     * @since 4.0.5
     */
    /*
     * TODO there are a wide range of GError states that emanate from this
     * call; we need some appropriate code to interpret the more "common" ones
     * and turn them into more strongly typed Exceptions.
     */
    public void save(String filename, PixbufFormat type) {
        try {
            GdkPixbuf.savev(this, filename, type.getName(), null, null);
        } catch (GlibException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    /**
     * Get the width of this Pixbuf, in pixels
     * 
     * @since 4.0.8
     */
    public int getWidth() {
        return GdkPixbuf.getWidth(this);
    }

    /**
     * Get the height of this Pixbuf, in pixels
     * 
     * @since 4.0.8
     */
    public int getHeight() {
        return GdkPixbuf.getHeight(this);
    }

    /**
     * Get a the individual pixel values comprising this Pixbuf.
     * 
     * <p>
     * Image data in a Pixbuf is stored in memory in an uncompressed but
     * packed format. Rows in the image are stored top to bottom, and in each
     * row pixels are stored from left to right. The returned array is, as you
     * would expect, the bytes comprising each single pixel in a sequential
     * series. There will be either three (RGB) or four (RGBA) bytes per
     * pixel, see {@link #getNumChannels() getNumChannels()}.
     * 
     * <p>
     * The return array is of type of is <code>byte</code> but you can
     * expect unsigned values in the range <code>0</code> to
     * <code>255</code>. If you need the actual values you'll have to
     * <code>&</code> with <code>0xFF</code> to get yourself to the
     * correct unsigned integer.
     * 
     * <p>
     * You should not need to call this. See the caveats at the top of this
     * class; changing the values of this array will <b>not</b> change the
     * underlying image.
     * 
     * <p>
     * <i>The underlying library stores Pixbufs in a format which is efficient
     * for internal representation and memory operations, though not
     * necessarily efficient for size and frequently extra bits are added as
     * alignment padding. We do some minor copying so that the array returned
     * is one byte per channel and cartesian. </i>
     * 
     * <p>
     * <i>In the current GDK Pixbuf library implementation, the red, green,
     * blue and optional alpha are fixed at 8 bits per channel.</i>
     * 
     * @since 4.0.8
     */
    public byte[] getPixels() {
        final int width, height;
        final int numChannels, rowstride, size;
        final byte[] data;
        int p, k;
        final byte[] result;

        width = GdkPixbuf.getWidth(this);
        height = GdkPixbuf.getHeight(this);
        numChannels = GdkPixbuf.getNChannels(this);
        rowstride = GdkPixbuf.getRowstride(this);

        data = GdkPixbufOverride.getPixels(this);

        size = width * numChannels;
        result = new byte[height * size];

        k = 0;
        p = 0;

        for (int j = 0; j < height; j++) {
            System.arraycopy(data, p, result, k, size);

            p += rowstride;
            k += size;
        }

        return result;
    }

    /**
     * Get the number of colour channels in this Pixbuf. This will be either
     * <code>3</code> for RGB or <code>4</code> for an RGBA image.
     * 
     * <p>
     * You don't actually need this; if you're working with the pixel data
     * from {@link #getPixels() getPixels()} just divide the length of the
     * returned array by (<var>width</var> <code>*</code> <var>height</var>).
     * 
     * @since 4.0.8
     */
    public int getNumChannels() {
        return GdkPixbuf.getNChannels(this);
    }
}
