package com.github.pwittchen.kirai.library.html;

import com.github.pwittchen.kirai.library.Piece;

public final class HtmlPiece extends Piece {
  private HtmlPiece(String key, Object value) {
    super(key, value, new HtmlSyntax());
  }

  public static Piece put(String key, Object value) {
    return new HtmlPiece(key, value);
  }
}