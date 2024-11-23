package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.stream;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.*;

class TestDemoJUnitTest {
  private TestDemo testDemo;

  @BeforeEach
  void setUp() throws Exception {
    testDemo = new TestDemo();
  }
  
  // a set of arguments to use in the parameterized test assertTwoPositiveNumbersAddedCorrectly
  static Stream<Arguments> argumentsForAddPositive() {
    return Stream.of(
        arguments(2, 4, 6, false),
        arguments(765, 123, 888, false),
        arguments(-5, -3, -8, true),     // turns out the third argument here can be anything,
        arguments(-1, 77, 1000, true),     // because the method doesn't add the numbers unless
        arguments(500, -500, 0, true),   // they are both positive
        arguments(0, 10, 10, true)
        
        );
  }
  
  // performs the test on the addPositive method. results in 6 test runs, one for each set of arguments.
  @ParameterizedTest
  @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
  void assertTwoPositiveNumbersAddedCorrectly(int a, int b, int expected, boolean expectException) {
    // Given two positive integers (stream)
    // When method addPositive is called
    // Then either add or throw exception
    if(!expectException) {
      assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
    }
  }
  
  // performs the test on addPositive(). results in one test run, no matter how many sets of arguments are presented.
  @Test
  void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
    assertThat(testDemo.addPositive(3, 4)).isEqualTo(7);
    assertThat(testDemo.addPositive(88888, 11111)).isEqualTo(99999);
    assertThatThrownBy(() -> testDemo.addPositive(0, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> testDemo.addPositive(50, 0)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> testDemo.addPositive(0, -65)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> testDemo.addPositive(27, -7438)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> testDemo.addPositive(-1, -1)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> testDemo.addPositive(-5, 99)).isInstanceOf(IllegalArgumentException.class);

  }
  
  // tests alphabetizeLettersOfWord().
  @ParameterizedTest
  @MethodSource("com.promineotech.TestDemoJUnitTest#argsForWordAlphabetization")
  void assertThatLettersOfWordAreAlphabetizedCorrectly(String word, String expected) {
    assertThat(testDemo.alphabetizeLettersOfWord(word).equals(expected));
  }
  // arguments used to test alphabetizeLettersOfWord()
  static Stream<Arguments> argsForWordAlphabetization() {
    return Stream.of(
        arguments("hello", "ehllo"),
        arguments("world", "dlorw"),
        arguments("parallelogram", "aaaeglllmoprr")
        );
  }
  
  // uses mockito to provide a number for the method instead of getting a random one, 
  // so that we can tell the test what the expected value should be.
  @Test
  void assertThatNumberSquaredIsCorrect() {
    TestDemo mockDemo = spy(testDemo);
    doReturn(5).when(mockDemo).getRandomInt();
    int fiveSquared = mockDemo.randomNumberSquared();
    assertThat(fiveSquared).isEqualTo(25);
  }
  
  
  
}
