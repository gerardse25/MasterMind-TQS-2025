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
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be positive");
    }

    int[] digits = new int[length];
    for (int i = 0; i < length; i++) {
      digits[i] = 0;  // després ja el farem real i aleatori
    }
    return new Code(digits);
  }


}
