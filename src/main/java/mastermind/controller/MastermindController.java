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

    // Mostrem resultat normalment
    view.showResult(result.getBlackPegs(), result.getWhitePegs(), game.getAttempts());

    // Si la partida s'ha guanyat → MOSTRAR WIN i no demanar més intents
    if (game.isWon()) {
      view.showWin(game.getAttempts());
      return;
    }

    // Si la partida s'ha acabat per intents → mostrar Game Over
    if (game.isOver()) {
      view.showGameOver();
      return;
    }

    // Si no s'ha acabat → demanar un altre intent
    view.askForGuess();
  }




}
