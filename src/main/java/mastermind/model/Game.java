package mastermind.model;

/**
 * Representa una partida de Mastermind.
 * De moment només guarda el codi secret.
 */
public class Game {

  private final Code secret;

  /** Crea una nova partida amb un codi secret aleatori de longitud 4. */
  public Game() {
    this.secret = Code.generateSecret(4);
  }

  /** Constructor alternatiu per a tests. */
  public Game(Code secret) {
    this.secret = secret;
  }

  /**
   * Encara no implementem la lògica real.
   * De moment retornem un resultat buit.
   */
  public EvaluationResult makeGuess(int[] guessDigits) {
    return new EvaluationResult(0, 0);
  }

  public Code getSecret() {
    return secret;
  }
}
