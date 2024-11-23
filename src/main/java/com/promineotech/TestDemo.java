package com.promineotech;
import java.util.Arrays;
import java.util.Random;
import com.google.common.annotations.VisibleForTesting;

public class TestDemo {
  /**
   * adds two ints together if they are both > 0, otherwise throws exception
   * @param a
   * @param b
   * @return sum of a and b
   */
  @VisibleForTesting
  public int addPositive(int a, int b) {
    if(a > 0 && b > 0) {
      return a + b;
    } else {
      throw new IllegalArgumentException("Both parameters must be positive!");
    }
  }
  /**
   * alphabetizes the letters of a given word
   * @param String word
   * @return String word rearranged
   */
  @VisibleForTesting
  public String alphabetizeLettersOfWord(String word) {
    char[] charArray = word.toCharArray();
    Arrays.sort(charArray);
    return charArray.toString();
  }
  /**
   * squares a random number
   * @return number squared
   */
  @VisibleForTesting
  public int randomNumberSquared() {
    int random = getRandomInt();
    return random * random;
  }
  /**
   * gets a random integer from 1 to 10
   * @return random int
   */
  @VisibleForTesting
  int getRandomInt() {
    Random random = new Random();
    return random.nextInt(10) + 1;
  }
}
