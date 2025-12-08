package mastermind.model;

import java.util.Random;

/**
 * Representa un codi secret del joc Mastermind.
 */
public class Code {

  private final int[] digits;

  /**
   * Constructor del codi.
   * @param digits l'array d'enters que formen el codi secret
   */
  public Code(int[] digits) {
    this.digits = digits;
  }

  /** Retorna la longitud del codi. */
  public int length() {
    return digits.length;
  }

  /** Retorna els dígits del codi. */
  public int[] getDigits() {
    return digits;
  }

  /**
   * Genera un codi secret amb longitud donada i dígits dins el rang [0..5].
   * Prova de caixa negra: determinista pel contracte, però implementació interna oculta.
   *
   * @param length longitud del codi
   * @return instància de Code amb dígits vàlids
   * @throws IllegalArgumentException si length <= 0 (cas límit)
   */
  public static Code generateSecret(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be positive");
    }

    Random random = new Random();               // Generador d'aleatorietat
    int[] digits = new int[length];

    // Loop simple (més endavant servirà per loop testing)
    for (int i = 0; i < length; i++) {
      digits[i] = random.nextInt(6); // Genera un dígit dins [0..5]
    }

    return new Code(digits);
  }

  /**
   * Avalua un intent comparat amb el codi secret.
   *
   * @param secret el codi secret
   * @param guess l'intent del jugador
   * @return objecte EvaluationResult amb black i white pegs
   */
  public static EvaluationResult evaluateGuess(Code secret, Code guess) {
    int black = 0;

    for (int i = 0; i < secret.length(); i++) {
      if (secret.getDigits()[i] == guess.getDigits()[i]) {
        black++;
      }
    }

    return new EvaluationResult(black, 0); // white de moment 0
  }



}
