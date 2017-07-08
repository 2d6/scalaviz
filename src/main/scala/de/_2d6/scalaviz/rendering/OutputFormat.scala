package de._2d6.scalaviz.rendering

sealed trait OutputFormat {
  def extension: String

  override def toString: String = extension
}

case object OutputFormat {

  case object bmp extends OutputFormat {
    val extension = "bmp"
  }

  case object canon extends OutputFormat {
    val extension = "canon"
  }

  case object dot extends OutputFormat {
    val extension = "dot"
  }

  case object gv extends OutputFormat {
    val extension = "gv"
  }

  case object xdot extends OutputFormat {
    val extension = "xdot"
  }

  case object xdot1_2 extends OutputFormat {
    val extension = "xdot1.2"
  }

  case object xdot1_4 extends OutputFormat {
    val extension = "xdot1.4"
  }

  case object cgimage extends OutputFormat {
    val extension = "cgimage"
  }

  case object cmap extends OutputFormat {
    val extension = "cmap"
  }

  case object eps extends OutputFormat {
    val extension = "eps"
  }

  case object exr extends OutputFormat {
    val extension = "exr"
  }

  case object fig extends OutputFormat {
    val extension = "fig"
  }

  case object gd extends OutputFormat {
    val extension = "gd"
  }

  case object gd2 extends OutputFormat {
    val extension = "gd2"
  }

  case object gif extends OutputFormat {
    val extension = "gif"
  }

  case object gtk extends OutputFormat {
    val extension = "gtk"
  }

  case object ico extends OutputFormat {
    val extension = "ico"
  }

  case object imap extends OutputFormat {
    val extension = "imap"
  }

  case object cmapx extends OutputFormat {
    val extension = "cmapx"
  }

  case object imap_np extends OutputFormat {
    val extension = "imap_np"
  }

  case object cmapx_np extends OutputFormat {
    val extension = "cmapx_np"
  }

  case object ismap extends OutputFormat {
    val extension = "ismap"
  }

  case object jp2 extends OutputFormat {
    val extension = "jp2"
  }

  case object jpg extends OutputFormat {
    val extension = "jpg"
  }

  case object pict extends OutputFormat {
    val extension = "pct"
  }

  case object pdf extends OutputFormat {
    val extension = "pdf"
  }

  case object pic extends OutputFormat {
    val extension = "pic"
  }

  case object plain extends OutputFormat {
    val extension = "plain"
  }

  case object plain_ext extends OutputFormat {
    val extension = "plain-ext"
  }

  case object png extends OutputFormat {
    val extension = "png"
  }

  case object pov extends OutputFormat {
    val extension = "pov"
  }

  case object ps extends OutputFormat {
    val extension = "ps"
  }

  case object ps2 extends OutputFormat {
    val extension = "ps2"
  }

  case object psd extends OutputFormat {
    val extension = "psd"
  }

  case object sgi extends OutputFormat {
    val extension = "sgi"
  }

  case object svg extends OutputFormat {
    val extension = "svg"
  }

  case object svgz extends OutputFormat {
    val extension = "svgz"
  }

  case object tga extends OutputFormat {
    val extension = "tga"
  }

  case object tif extends OutputFormat {
    val extension = "tif"
  }

  case object tk extends OutputFormat {
    val extension = "tk"
  }

  case object vml extends OutputFormat {
    val extension = "vml"
  }

  case object vmlz extends OutputFormat {
    val extension = "vmlz"
  }

  case object vrml extends OutputFormat {
    val extension = "vrml"
  }

  case object wbmp extends OutputFormat {
    val extension = "wbmp"
  }

  case object webp extends OutputFormat {
    val extension = "webp"
  }

  case object xlib extends OutputFormat {
    val extension = "xlib"
  }

  case object x11 extends OutputFormat {
    val extension = "x11"
  }

}
