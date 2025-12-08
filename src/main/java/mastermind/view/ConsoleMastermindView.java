package mastermind.view;

import mastermind.controller.MastermindView;

import java.util.Scanner;

/**
 * Implementació de la vista de Mastermind per consola.
 * Mostra missatges per pantalla.
 */
public class ConsoleMastermindView implements MastermindView {

  private final Scanner scanner;

  public ConsoleMastermindView(Scanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public void showWelcome() {
    System.out.println("Benvingut al Mastermind!");
    System.out.println("Intenta endevinar el codi de 4 dígits (de 0 a 5).");
  }

  @Override
  public void askForGuess() {
    System.out.println("Introdueix el teu intent (4 dígits sense espais, per ex. 0123):");
    // La lectura real la farem des de la classe Main.
  }

  @Override
  public void showResult(int black, int white, int attempts) {
    System.out.println("Resultat: " + black + " negres, " + white + " blanques. "
        + "Intents: " + attempts);
  }

  @Override
  public void showWin(int attempts) {
    System.out.println("Has encertat el codi! 🎉");
    System.out.println("Intents totals: " + attempts);
  }

  @Override
  public void showGameOver() {
    System.out.println("Has exhaurit el nombre màxim d'intents. GAME OVER.");
  }
}
