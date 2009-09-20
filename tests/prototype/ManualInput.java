/*
 * ManualInput.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventExpose;
import org.gnome.gdk.EventKey;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.InputMethod;
import org.gnome.gtk.SimpleInputMethod;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;

/**
 * Experiment with GTK's input method system.
 * 
 * @author AndrewCowie
 */
public class ManualInput
{
    public static void main(String[] args) {
        final Window w;
        final DrawingArea d;
        final InputMethod im;
        final StringBuilder buf;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Text editor");
        w.setDefaultSize(200, 100);

        d = new DrawingArea();
        d.setCanFocus(true);

        w.add(d);

        im = new SimpleInputMethod();
        im.setUsePreedit(true);

        buf = new StringBuilder("Hello");

        d.connect(new Widget.ExposeEvent() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;
                final Layout layout;
                final FontDescription desc;

                cr = new Context(source.getWindow());

                layout = new Layout(cr);

                desc = new FontDescription("DejaVu Serif, Book 12");
                layout.setFontDescription(desc);

                layout.setText(buf.toString());

                source.setSizeRequest(layout.getPixelWidth(), layout.getPixelHeight());

                cr.showLayout(layout);
                return false;
            }
        });

        d.connect(new Widget.KeyPressEvent() {
            public boolean onKeyPressEvent(Widget source, EventKey event) {
                if (im.filterKeypress(event)) {
                    return true;
                }
                return false;
            }
        });

        d.connect(new Widget.KeyReleaseEvent() {
            public boolean onKeyReleaseEvent(Widget source, EventKey event) {
                if (im.filterKeypress(event)) {
                    return true;
                }
                return false;
            }
        });

        im.connect(new InputMethod.Commit() {
            public void onCommit(InputMethod source, String str) {
                buf.append(str);
                d.queueDraw();
            }
        });

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        w.showAll();

        Gtk.main();
    }
}