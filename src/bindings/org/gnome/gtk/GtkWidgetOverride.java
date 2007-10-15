/*
 * GtkWidgetOverride.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Hand crafted to get at window field. This is a FIXME until we add
 * properties to the GObject case in the code generator (assuming we ever do).
 * 
 * @author Andrew Cowie
 */
final class GtkWidgetOverride extends Plumbing
{
    private GtkWidgetOverride() {}

    static final org.gnome.gdk.Window getWindow(Widget self) {
        long result;

        synchronized (lock) {
            result = gtk_widget_get_window(pointerOf(self));

            return (org.gnome.gdk.Window) objectFor(result);
        }
    }

    private static native final long gtk_widget_get_window(long self);
}