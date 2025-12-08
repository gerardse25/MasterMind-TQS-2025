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
    int showGameOverCount = 0;


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

    @Override
    public void showGameOver() {
      showGameOverCount++;
    }
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

  /**
   * Cas de prova: intent incorrecte amb la partida encara en curs.
   * Comprovem que:
   *  - s'incrementa el nombre d'intents,
   *  - es mostra el resultat una vegada,
   *  - i es torna a demanar un nou intent.
   * Prova de caixa negra del controlador.
   */
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

  /**
   * Cas de prova: el jugador encerta el codi.
   * Comprovem que:
   *  - es mostra el resultat,
   *  - es mostra el missatge de victòria,
   *  - i NO es demanen més intents.
   * Prova de caixa negra del controlador.
   */
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


  /**
   * Cas de prova: quan el jugador arriba al màxim d'intents
   * sense encertar el codi, el controlador ha de mostrar GAME OVER
   * i no ha de demanar més intents.
   *
   * Es tracta d'una prova de caixa negra del controlador.
   */
  @Test
  void handleGuess_showsGameOver_whenMaxAttemptsReached() {
    FakeView view = new FakeView();
    Game game = new Game(new Code(new int[]{1,2,3,4}));
    MastermindController controller = new MastermindController(game, view);

    // Forcem que la partida arribi al màxim d'intents (10)
    for (int i = 0; i < 10; i++) {
      controller.handleGuess(new int[]{0,0,0,0});  // mai encertem
    }

    // Comprovem que la vista ha mostrat Game Over exactament 1 vegada
    assertEquals(1, view.showGameOverCount);

    // No s'ha de demanar cap altre intent després de la fi de la partida
    assertEquals(0, view.askForGuessCount - 10); // només les 10 primeres
  }


}
