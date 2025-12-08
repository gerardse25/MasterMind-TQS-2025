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
    int[] secretDigits = secret.getDigits();
    int[] guessDigits  = guess.getDigits();

    // Marquem quines posicions ja hem “gastat” (per no comptar dues vegades)
    boolean[] secretUsed = new boolean[secretDigits.length];
    boolean[] guessUsed  = new boolean[guessDigits.length];

    int black = 0;
    int white = 0;

    // PRIMER PAS: comptar negres (encerts exactes)
    for (int i = 0; i < secretDigits.length; i++) {
      if (secretDigits[i] == guessDigits[i]) {
        black++;
        secretUsed[i] = true; // aquesta posició del secret ja està gastada
        guessUsed[i] = true;  // aquesta posició del guess també
      }
    }

    // SEGON PAS: comptar blanques (encerts però en posició diferent)
    for (int i = 0; i < guessDigits.length; i++) {
      // Només mirem els dígits del guess que no són negres
      if (!guessUsed[i]) {
        // Busquem si aquest dígit existeix en alguna posició lliure del secret
        for (int j = 0; j < secretDigits.length; j++) {
          // Només mirem posicions del secret que encara no hem gastat
          if (!secretUsed[j] && guessDigits[i] == secretDigits[j]) {
            white++;
            secretUsed[j] = true; // gastem aquesta posició del secret
            break;                 // sortim del for j, aquest dígit del guess ja està comptat
          }
        }
      }
    }

    return new EvaluationResult(black, white);
  }




}
