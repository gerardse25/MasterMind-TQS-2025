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

  /**
   * Gestiona un intent del jugador:
   * - mostra el resultat,
   * - comprova si s'ha guanyat,
   * - comprova si s'ha acabat la partida per intents,
   * - i si no, demana un altre intent.
   */
  public void handleGuess(int[] digits) {

    // Avaluem l'intent
    var result = game.makeGuess(digits);

    // Mostrem negres/blanques i intents
    view.showResult(result.getBlackPegs(), result.getWhitePegs(), game.getAttempts());

    // Si el jugador ha encertat, partida guanyada
    if (game.isWon()) {
      view.showWin(game.getAttempts());
      return;
    }

    // Si la partida s'ha acabat per massa intents, mostrem Game Over
    if (game.isOver()) {
      view.showGameOver();
      return;
    }

    // Si la partida continua, demanem un altre intent
    view.askForGuess();
  }





}
