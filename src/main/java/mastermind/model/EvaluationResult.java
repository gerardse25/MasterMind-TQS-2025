package mastermind.model;

/**
 * Guarda el resultat d'una jugada:
 * - blackPegs = encerts exactes
 * - whitePegs = encerts fora de lloc
 */
public class EvaluationResult {

  private final int blackPegs;
  private final int whitePegs;

  public EvaluationResult(int blackPegs, int whitePegs) {
    this.blackPegs = blackPegs;
    this.whitePegs = whitePegs;
  }

  public int getBlackPegs() {
    return blackPegs;
  }

  public int getWhitePegs() {
    return whitePegs;
  }

}
