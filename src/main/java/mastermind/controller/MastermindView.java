package mastermind.controller;

/**
 * Interfície de la Vista del joc Mastermind.
 * El controlador farà crides a aquests mètodes per mostrar informació.
 * La implementació concreta (consola, GUI, etc.) vindrà després.
 */
public interface MastermindView {

  /**
   * Mostra un missatge inicial quan comença la partida.
   */
  void showWelcome();

  /**
   * Demana a l'usuari que introdueixi un nou intent.
   * La implementació concreta (Scanner, GUI...) farà la lectura real.
   */
  void askForGuess();

  /**
   * Mostra el resultat d'un intent.
   *
   * @param black nombre de fitxes negres
   * @param white nombre de fitxes blanques
   * @param attempts intents acumulats
   */
  void showResult(int black, int white, int attempts);

  /**
   * Mostra un missatge quan el jugador ha guanyat.
   *
   * @param attempts intents necessaris per guanyar
   */
  void showWin(int attempts);

  /**
   * Mostra un missatge quan la partida s'ha acabat sense encertar el codi.
   */
  void showGameOver();
}
