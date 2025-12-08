package mastermind;

import mastermind.controller.MastermindController;
import mastermind.model.Game;
import mastermind.view.ConsoleMastermindView;

import java.util.Scanner;

/**
 * Classe d'entrada del programa.
 * Permet jugar a Mastermind per consola.
 */
public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Game game = new Game();  // genera un codi secret aleatori de longitud 4
    ConsoleMastermindView view = new ConsoleMastermindView(scanner);
    MastermindController controller = new MastermindController(game, view);

    // Iniciem la partida (mostra benvinguda i demana primer intent)
    controller.startGame();

    // Bucle principal de joc: mentre la partida no estigui acabada
    while (!game.isOver()) {
      // Llegim un intent de l'usuari com a string
      String line = scanner.nextLine().trim();

      // Validació molt senzilla: ha de tenir longitud 4 i ser tot dígits
      if (line.length() != 4 || !line.chars().allMatch(Character::isDigit)) {
        System.out.println("Format invàlid. Escriu 4 dígits (per ex. 0123).");
        continue;
      }

      int[] digits = new int[4];
      boolean valid = true;

      for (int i = 0; i < 4; i++) {
        int d = Character.getNumericValue(line.charAt(i));
        if (d < 0 || d > 5) {
          valid = false;
          break;
        }
        digits[i] = d;
      }

      if (!valid) {
        System.out.println("Cada dígit ha d'estar entre 0 i 5.");
        continue;
      }

      // Passem l'intent al controlador
      controller.handleGuess(digits);
    }

    scanner.close();
  }
}
