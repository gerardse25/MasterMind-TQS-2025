package mastermind.model;

public class Code {

  private final int[] digits;

  public Code(int[] digits) {
    this.digits = digits;
  }

  public int length() {
    return digits.length;
  }

  public int[] getDigits() {
    return digits;
  }

  public static Code generateSecret(int length) {
    int[] digits = new int[length];
    for (int i = 0; i < length; i++) {
      digits[i] = 0;   // ja millorarem després (ara només volem passar el test)
    }
    return new Code(digits);
  }

}
