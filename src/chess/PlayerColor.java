package chess;

public enum PlayerColor {
  WHITE, BLACK;

  /**
   * Retourne la couleur opposée.
   * @param color une Couleur.
   * @return Couleur opposée.
   */
  public static PlayerColor getOpposite(PlayerColor color){
    return color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
  }
}
