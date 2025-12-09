package mastermind.mocks;

import java.util.Random;

/**
 * Mock de Random per poder controlar els valors generats als tests.
 * En lloc de generar nombres aleatoris, retorna la seqüència de valors
 * que li passem al constructor.
 */
public class FakeRandom extends Random {

  private final int[] values;
  private int index = 0;

  /**
   * Crea un FakeRandom amb una seqüència de valors prefixats.
   *
   * @param values valors que es retornaran successivament a nextInt(...)
   */
  public FakeRandom(int... values) {
    this.values = values;
  }

  /**
   * Sobreescrivim nextInt per retornar el següent valor de la llista.
   * El paràmetre bound no s'utilitza, assumim que els valors ja són vàlids.
   */
  @Override
  public int nextInt(int bound) {
    int value = values[index % values.length];
    index++;
    return value;
  }
}
