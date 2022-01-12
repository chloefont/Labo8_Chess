package chess;

public enum PlayerColor {
  WHITE, BLACK;

  // TODO avons nous le droit de faire ceci sachant que cette classe nous appartient pas ?
  /**
   * Retourne la couleur opposée.
   * @param color une Couleur.
   * @return Couleur opposée.
   */
  public static PlayerColor getOpposite(PlayerColor color){
    return color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
  }
}
