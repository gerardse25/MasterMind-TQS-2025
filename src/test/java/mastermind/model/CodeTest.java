package mastermind.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CodeTest {

  @Test
  void generateSecret_hasRequestedLength() {
    Code secret = Code.generateSecret(4);

    assertNotNull(secret);           // El codi generat no pot ser null
    assertEquals(4, secret.length()); // Ha de tenir longitud 4
  }

  @Test
  void generateSecret_lengthMustBePositive() {
    assertThrows(IllegalArgumentException.class,
        () -> Code.generateSecret(0));
  }

  @Test
  void generateSecret_digitsMustBeBetween0And5() {
    Code secret = Code.generateSecret(4);

    for (int d : secret.getDigits()) {
      assertTrue(d >= 0 && d <= 5,
          "Digit out of bounds: " + d);
    }
  }



}
