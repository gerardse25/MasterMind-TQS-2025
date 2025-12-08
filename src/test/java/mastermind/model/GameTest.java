package mastermind.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves de la classe Game.
 */
public class GameTest {

  /**
   * Cas de prova: si el jugador encerta exactament el codi,
   * el resultat ha de ser 4 negres i 0 blanques.
   * (Test de caixa negra sobre makeGuess).
   */
  @Test
  void makeGuess_allCorrect() {
    Code secret = new Code(new int[]{1,2,3,4});
    Game game = new Game(secret);

    EvaluationResult result = game.makeGuess(new int[]{1,2,3,4});

    assertEquals(4, result.getBlackPegs());
    assertEquals(0, result.getWhitePegs());
  }
}
