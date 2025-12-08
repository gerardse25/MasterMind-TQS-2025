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
  }
}
