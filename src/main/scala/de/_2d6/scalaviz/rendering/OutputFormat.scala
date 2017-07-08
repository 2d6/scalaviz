package de._2d6.scalaviz.rendering

/**
  * The output format of the file into which a [[de._2d6.scalaviz.graph.Graph]] will be rendered
  */
sealed trait OutputFormat {
  def extension: String

  override def toString: String = extension
}

case object OutputFormat {

  /**
    * Bitmap format
    */
  case object bmp extends OutputFormat {
    val extension = "bmp"
  }

  /**
    * Dot format
    */
  case object canon extends OutputFormat {
    val extension = "canon"
  }

  /**
    * Dot format
    */
  case object dot extends OutputFormat {
    val extension = "dot"
  }

  /**
    * Dot format
    */
  case object gv extends OutputFormat {
    val extension = "gv"
  }

  /**
    * Dot format
    */
  case object xdot extends OutputFormat {
    val extension = "xdot"
  }

  /**
    * Dot format
    */
  case object xdot1_2 extends OutputFormat {
    val extension = "xdot1.2"
  }

  /**
    * Dot format
    */
  case object xdot1_4 extends OutputFormat {
    val extension = "xdot1.4"
  }

  /**
    * CGImage format
    */
  case object cgimage extends OutputFormat {
    val extension = "cgimage"
  }

  /**
    * Client-side image map format
    */
  case object cmap extends OutputFormat {
    val extension = "cmap"
  }

  /**
    * Encapsulated PostScript format
    */
  case object eps extends OutputFormat {
    val extension = "eps"
  }

  /**
    * OpenEXR format
    */
  case object exr extends OutputFormat {
    val extension = "exr"
  }

  /**
    * FIG graphics language format
    */
  case object fig extends OutputFormat {
    val extension = "fig"
  }

  /**
    * GD language format
    */
  case object gd extends OutputFormat {
    val extension = "gd"
  }

  /**
    * GD2 language format
    */
  case object gd2 extends OutputFormat {
    val extension = "gd2"
  }

  /**
    * GIF bitmap format
    */
  case object gif extends OutputFormat {
    val extension = "gif"
  }

  /**
    * ICO format
    */
  case object ico extends OutputFormat {
    val extension = "ico"
  }

  /**
    * Server-side image map format
    */
  case object imap extends OutputFormat {
    val extension = "imap"
  }

  /**
    * Client-side image map format
    */
  case object cmapx extends OutputFormat {
    val extension = "cmapx"
  }

  /**
    * Server-side image map format. Identical to [[imap]], except only using rectangles as active areas.
    */
  case object imap_np extends OutputFormat {
    val extension = "imap_np"
  }

  /**
    * Client-side image map format. Identical to [[cmapx]], except only using rectangles as active areas.
    */
  case object cmapx_np extends OutputFormat {
    val extension = "cmapx_np"
  }

  /**
    * HTML image map format
    */
  case object ismap extends OutputFormat {
    val extension = "ismap"
  }

  /**
    * JPEG2000 format
    */
  case object jp2 extends OutputFormat {
    val extension = "jp2"
  }

  /**
    * JPEG format
    */
  case object jpg extends OutputFormat {
    val extension = "jpg"
  }

  /**
    * JPEG format
    */
  case object pict extends OutputFormat {
    val extension = "pct"
  }

  /**
    * PDF format
    */
  case object pdf extends OutputFormat {
    val extension = "pdf"
  }

  /**
    * Pic format
    */
  case object pic extends OutputFormat {
    val extension = "pic"
  }

  /**
    * Native line-based format for Graphviz graphs
    */
  case object plain extends OutputFormat {
    val extension = "plain"
  }

  /**
    * Native line-based format for Graphviz graphs. Identical to [[plain]] apart from providing port names on head and
    * tail nodes when applicable
    */
  case object plain_ext extends OutputFormat {
    val extension = "plain-ext"
  }

  /**
    * PNG format
    */
  case object png extends OutputFormat {
    val extension = "png"
  }

  /**
    * Scene-description format for 3D modelling for the Persistence of Vision Raytracer.
    */
  case object pov extends OutputFormat {
    val extension = "pov"
  }

  /**
    * PostScript format
    */
  case object ps extends OutputFormat {
    val extension = "ps"
  }

  /**
    * PostScript format with PDF notations
    */
  case object ps2 extends OutputFormat {
    val extension = "ps2"
  }

  /**
    * Adobe PhotoShop PSD format
    */
  case object psd extends OutputFormat {
    val extension = "psd"
  }

  /**
    * SGI format
    */
  case object sgi extends OutputFormat {
    val extension = "sgi"
  }

  /**
    * SVG format
    */
  case object svg extends OutputFormat {
    val extension = "svg"
  }

  /**
    * SVG format, compressed
    */
  case object svgz extends OutputFormat {
    val extension = "svgz"
  }

  /**
    * TARGA/TGA format
    */
  case object tga extends OutputFormat {
    val extension = "tga"
  }

  /**
    * TIF format
    */
  case object tif extends OutputFormat {
    val extension = "tif"
  }

  /**
    * TK format
    */
  case object tk extends OutputFormat {
    val extension = "tk"
  }

  /**
    * VML format
    */
  case object vml extends OutputFormat {
    val extension = "vml"
  }

  /**
    * VML format, compressed
    */
  case object vmlz extends OutputFormat {
    val extension = "vmlz"
  }

  /**
    * VRML format
    */
  case object vrml extends OutputFormat {
    val extension = "vrml"
  }

  /**
    * WBMP format
    */
  case object wbmp extends OutputFormat {
    val extension = "wbmp"
  }

  /**
    * WEBP format
    */
  case object webp extends OutputFormat {
    val extension = "webp"
  }

}
