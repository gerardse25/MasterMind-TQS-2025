package mastermind.controller;

import mastermind.model.Code;
import mastermind.model.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test inicial del controlador utilitzant una vista falsa (mock manual).
 */
public class MastermindControllerTest {

  private static class FakeView implements MastermindView {
    boolean welcomeShown = false;
    int askForGuessCount = 0; // comptador de vegades que es demana un intent
    int showResultCount = 0;
    int showWinCount = 0;


    @Override
    public void showWelcome() {
      welcomeShown = true;
    }

    @Override
    public void askForGuess() {
      askForGuessCount++;
    }

    @Override
    public void showResult(int b, int w, int a) {
      showResultCount++;
    }

    @Override
    public void showWin(int attempts) {
      showWinCount++;
    }

    @Override public void showGameOver() {}
  }


  @Test
  void startGame_showsWelcomeMessage() {
    FakeView view = new FakeView();
    Game game = new Game(new Code(new int[]{1,2,3,4}));
    MastermindController controller = new MastermindController(game, view);

    controller.startGame();

    assertTrue(view.welcomeShown);
  }

  /**
   * En iniciar la partida s'ha de mostrar la benvinguda
   * i demanar el primer intent al jugador.
   */
  @Test
  void startGame_asksForFirstGuess() {
    FakeView view = new FakeView();
    Game game = new Game(new Code(new int[]{1,2,3,4}));
    MastermindController controller = new MastermindController(game, view);

    controller.startGame();

    assertTrue(view.welcomeShown);
    assertEquals(1, view.askForGuessCount);
  }


  @Test
  void handleGuess_showsResultAndAsksAgain_ifNotOver() {
    FakeView view = new FakeView();
    Game game = new Game(new Code(new int[]{1,2,3,4}));
    MastermindController controller = new MastermindController(game, view);

    // Comencem en 0 intents
    assertEquals(0, game.getAttempts());

    // Fem un intent incorrecte
    controller.handleGuess(new int[]{0,0,0,0});

    // S'ha d'haver incrementat el nombre d'intents
    assertEquals(1, game.getAttempts());

    // La vista ha de mostrar el resultat exactament 1 vegada
    assertEquals(1, view.showResultCount);

    // Després ha de tornar a demanar un altre intent
    assertEquals(1, view.askForGuessCount);
  }


  @Test
  void handleGuess_showsWinAndDoesNotAskAgain_whenPlayerWins() {
    FakeView view = new FakeView();
    Game game = new Game(new Code(new int[]{1,2,3,4}));
    MastermindController controller = new MastermindController(game, view);

    // Fem un intent correcte
    controller.handleGuess(new int[]{1,2,3,4});

    // La vista ha de mostrar un WIN exactament 1 cop
    assertEquals(1, view.showWinCount);

    // No ha de demanar un altre intent
    assertEquals(0, view.askForGuessCount);

    // Ha d'haver mostrat el resultat també
    assertEquals(1, view.showResultCount);
  }


}
