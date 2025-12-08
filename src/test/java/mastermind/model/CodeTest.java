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



}
