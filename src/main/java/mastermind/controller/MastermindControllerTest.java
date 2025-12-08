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

    @Override
    public void showWelcome() {
      welcomeShown = true;
    }

    @Override public void askForGuess() {}
    @Override public void showResult(int b, int w, int a) {}
    @Override public void showWin(int attempts) {}
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
}
