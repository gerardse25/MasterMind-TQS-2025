package mastermind.controller;

import mastermind.model.Game;

/**
 * Controlador del joc Mastermind.
 * De moment només crida la Vista amb el missatge inicial.
 */
public class MastermindController {

  private final Game game;
  private final MastermindView view;

  public MastermindController(Game game, MastermindView view) {
    this.game = game;
    this.view = view;
  }

  public void startGame() {
    view.showWelcome();
    view.askForGuess();  // demanem el primer intent
  }

  public void handleGuess(int[] digits) {
    var result = game.makeGuess(digits);

    // Mostrem negres, blanques i intents acumulats
    view.showResult(result.getBlackPegs(), result.getWhitePegs(), game.getAttempts());

    // Si la partida no ha acabat, demanem un altre intent
    if (!game.isOver()) {
      view.askForGuess();
    }
  }


}
