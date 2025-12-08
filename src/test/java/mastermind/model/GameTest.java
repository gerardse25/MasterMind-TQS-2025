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

  /**
   * Inicialment, una partida NO està guanyada.
   */
  @Test
  void isWon_initiallyFalse() {
    Code secret = new Code(new int[]{1,2,3,4});
    Game game = new Game(secret);

    assertFalse(game.isWon());
  }
  /**
   * Si el jugador encerta el codi, la partida queda guanyada.
   */
  @Test
  void isWon_becomesTrueAfterCorrectGuess() {
    Code secret = new Code(new int[]{1,2,3,4});
    Game game = new Game(secret);

    // Encerta el codi
    game.makeGuess(new int[]{1,2,3,4});

    assertTrue(game.isWon());
  }

  /**
   * Inicialment, el nombre d'intents ha de ser 0.
   */
  @Test
  void attempts_initiallyZero() {
    Code secret = new Code(new int[]{1,2,3,4});
    Game game = new Game(secret);

    assertEquals(0, game.getAttempts());
  }

}
