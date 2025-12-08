package mastermind.model;

/**
 * Representa una partida de Mastermind.
 * De moment només guarda el codi secret.
 */
public class Game {

  private final Code secret;
  private boolean won = false;
  private int attempts = 0;  // intents fets fins ara


  /** Crea una nova partida amb un codi secret aleatori de longitud 4. */
  public Game() {
    this.secret = Code.generateSecret(4);
  }

  /** Constructor alternatiu per a tests. */
  public Game(Code secret) {
    this.secret = secret;
  }

  public int getAttempts() {
    return attempts;
  }


  /**
   * Avalua un intent del jugador respecte del codi secret.
   *
   * @param guessDigits dígits de l'intent del jugador
   * @return resultat amb fitxes negres i blanques
   */
  public EvaluationResult makeGuess(int[] guessDigits) {
    // Creem el codi de l'intent a partir dels dígits
    Code guess = new Code(guessDigits);

    // Avaluem l'intent comparant-lo amb el codi secret
    EvaluationResult result = Code.evaluateGuess(secret, guess);

    // Si tots els dígits són correctes, la partida queda guanyada
    if (result.getBlackPegs() == secret.length()) {
      won = true;
    }

    // Retornem el resultat per poder-lo mostrar o tractar des de fora
    return result;
  }



  public Code getSecret() {
    return secret;
  }

  /**
   * Indica si la partida ja s'ha guanyat.
   */
  public boolean isWon() {
    return won;
  }
}
