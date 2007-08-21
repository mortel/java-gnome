<?php
/*
 * index.php
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * This file comprises part of the infrastructure and content of the
 * java-gnome project website. As such, it is conveyed alongside the source
 * code and is made available to you by its authors under the terms of the
 * "GNU General Public Licence, version 2". See the LICENCE file for the terms
 * governing usage, copying and redistribution.
 */

	require "template.inc";
?>
<html>
<head>
<?
	template_header();
?>
<title>Get java-gnome!</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Get java-gnome!</h1>
<div class="bluepanel">
	<a class="nav-white" href="#source">Source will always be better than
	binary</a>
</div>

<p><i>You can help by ensuring java-gnome is properly packaged for your 
operating system!</i></p>

<h1>Download binaries</h1>

<p>Proper binary packages of the java-gnome <i>will be</i> available in many
fine Linux and Unix distributions, allowing you to quickly and easily install
the library on your system.</p>

<p>Distro specific instructions are available for each of:

<ul>
<li><p><a class="subject" href="gentoo.php">Gentoo Linux</a><br></p>
<li><p><a class="subject" href="solaris.php">Open Solaris</a><br></p>
<li><p><a class="subject" href="ubuntu.php">Ubuntu Linux</a><br></p>
<li><p><a class="subject" href="debian.php">Debian Linux</a><br></p>
<li><p><a class="subject" href="fedora.php">Fedora Core Linux</a><br></p>
</ul>

If yours isn't on that list, or if they don't have the latest release, then
it is certainly time to file a bug report!</p>

<p><i>Binaries from by third parties are necessarily used at your own risk. We
encourage the developers in the various downstream distributions to actively
participate in our project; if you're using packages provided by groups that
do so you will find that the level of community interest and support will be
quite high. Those on other platforms will necessarily meet with a less
enthusiastic response, but Open Source is about choice, so try java-gnome
where ever you like.</i></p>

<a name="source" title="Source will always be better than binary"></a>
<h1>Download source</h1>

<p>Installing binary packages provided by your distribution is certainly
convenient, but java-gnome is easily built from source and you are welcome to
do so.

<h2>Grab the tarball with latest release...</h2>

<p>Like most GNOME projects, official releases of java-gnome in tarball form
are hosted on the <code>ftp.gnome.org</code> servers. You can download the
latest release from <a class="nav-black"
href="http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.0/"><pre>http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.0/</pre></a>

<h2>Or check out the sources from the repository...</h2>

<p>For those who like to live on the bleeding edge, you can checkout the
code from our version control repository. You'll need to install Bazaar,
available in most Linux and Unix distributions (or you can likewise build it
from source; it is written in Python and installs most anywhere).</p>

<p>You can checkout the source code as follows:

<pre>
$ bzr checkout http://research.operationaldynamics.com/bzr/java-gnome/mainline/
</pre>

<p>We try our best to keep <code>mainline</code> in a buildable state, and
certainly the latest bugfixes and improvements will be present there, but we
can't guarantee that it'll be problem free. As ever when building pre-release
code, you would do well to hang with us on `IRC` or mailing list so you're up
to date with the state of the project in the current cycle.

<h2>... and build!</h2>

<p>Regardless of which sources you get, the next step is to build them. Doing
so is quite straight forward, but since java-gnome is a native library (and
not just architecture independent Java bytecode), building it is a wee touch
more involved than just compiling <code>.java</code> files. See <a
href="../README.html"><code>README</code></a> for more details of how to
configure and compile the bindings.</p>

<h1>Browse source</h1>

<p>One of the interesting things about Bazaar (and many of the other modern
Distributed Version Control Systems) is that when a repository is put online,
you can usually have the source code directly available via <code>HTTP</code>.
This means that if you want to quickly point someone at a particular file,
it's as easy as directly linking to it at the URL you checked out from + the
path to the file.</p>

<p>If you would like to browse the source code, here is the top of the
mainline (aka <code>HEAD</code>) branch:

<a class="download" href="http://research.operationaldynamics.com/bzr/java-gnome/mainline/">
<code>http://research.operationaldynamics.com/bzr/java-gnome/mainline/</code>
</a>
</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
