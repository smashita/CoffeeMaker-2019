package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestJUnit {
  @Test
  public void testSetup() {
    String str = "I am done with Junit setup";
    assertEquals("I am done with Junit setup",str);
  }
}
