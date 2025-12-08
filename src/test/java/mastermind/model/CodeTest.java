package mastermind.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves de caixa negra del mètode generateSecret().
 * S’avaluen particions equivalents, casos límit i propietats del resultat.
 */
public class CodeTest {

  /**
   * Cas de prova 1: Partició equivalent vàlida.
   * Verifica que la longitud del codi generat coincideix amb la demanada.
   */
  @Test
  void generateSecret_hasRequestedLength() {
    Code secret = Code.generateSecret(4);

    assertNotNull(secret);            // No pot ser null → propietat bàsica
    assertEquals(4, secret.length()); // Partició vàlida: length = 4
  }

  /**
   * Cas de prova 2: Cas límit i partició invàlida.
   * Longitud = 0 ha de provocar una excepció.
   */
  @Test
  void generateSecret_lengthMustBePositive() {
    assertThrows(IllegalArgumentException.class,
        () -> Code.generateSecret(0));
  }

  /**
   * Cas de prova 3: Partició equivalent del rang de valors vàlids.
   * Tots els dígits han d’estar dins del rang [0..5].
   */
  @Test
  void generateSecret_digitsMustBeBetween0And5() {
    Code secret = Code.generateSecret(4);

    for (int d : secret.getDigits()) {
      assertTrue(d >= 0 && d <= 5,
          "Digit out of bounds: " + d);
    }
  }

  /**
   * Cas de prova 4: Propietat d'aleatorietat.
   * Dos codis generats consecutivament haurien de ser diferents.
   * (Caixa negra – comprovació de propietat observable)
   */
  @Test
  void generateSecret_twoConsecutiveGenerationsShouldDiffer() {
    Code secret1 = Code.generateSecret(4);
    Code secret2 = Code.generateSecret(4);

    //No sempre serà cert, però estadísticament molt improbable
    assertNotEquals(
        java.util.Arrays.toString(secret1.getDigits()),
        java.util.Arrays.toString(secret2.getDigits())
    );
  }

}
