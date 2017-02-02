import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class  ByteCounterFundTest {
  
  /* JUnit tests of fundamental functionality
   *
   * Use these JUnit tests to ensure that your code correctly
   * implements the fundamental functionality.
   */
  
  @Test
  public void testArrayArgumentConstructorAndToString() {
    byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    ByteCounter byteCount = new ByteCounter(test);
    assertEquals(
                 "A constructor with an byte array argument should store the unique bytes with their occurrences values. (This could also indicate a problem the the to String method.)",
                 (byte)'a'+":3 "+ (byte)'b'+":2 "+(byte)'c'+":1", byteCount.toString());
  }
  
  @Test
  public void testStringArgumentConstructorAndGetCount()throws IOException {
    ByteCounter byteCount = new ByteCounter("file.txt");
    byte b = (byte)'a';
    assertEquals(
                 "A constructor with a String arguemnt should compute the byte counts from the specified file. (This could also indicate a problem the the to String method.)",
                 4, byteCount.getCount(b));
  }
  
  @Test
  public void testByteArgumentGetCount() {
    byte[] test = {(byte)'y', (byte)'y', (byte)'z'};
    byte b = (byte)'y';
    ByteCounter byteCount = new ByteCounter(test);
    assertEquals(
                 "The getCount method should take a byte value and return the number of occurrences.",
                 2, byteCount.getCount(b));
  }
  
  @Test
  public void testArrayArgumentGetCount() {
    byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    byte[]    b = {(byte)'c', (byte)'b', (byte)'a'};
    ByteCounter byteCount = new ByteCounter(test);
    int[] count = byteCount.getCount(b);
    assertArrayEquals(
                      "The getCount method should take a byte array and return an array of the number of occurrences of each byte.",
                      new int[] {1, 2, 3}, count);
  }
  
  @Test
  public void testGetElements() {
    byte[] test = {(byte)'a', (byte)'a', (byte)'a', (byte)'b', (byte)'b', (byte)'c'};
    ByteCounter byteCount = new ByteCounter(test);
    byte[] elements = byteCount.getElements();
    assertArrayEquals(
                      "The getElements method should return an array of bytes that have a non-zero count.", new byte[] {(byte)'a', (byte)'b',
      (byte)'c'}, elements);
    assertEquals(
                 "The getElements method should return an array of bytes that have a non-zero count", 3, elements.length);
  }
  
  /* Other JUnit tests.
   *
   * Write your own JUnit tests below to check the correctness of your implementation.
   * Leave the above methods intact and add your own tests below this comment so it's
   * easier for the graders to see what you've added.
   *
   * When you turn in your drafts we will run our own test suite on your code and provide
   * you with the feedback to help you debug an issues.
   *
   * Your draft code needs to contain a complete set of methods as specified in the assignment.
   * Otherwise, it won't compile with our test suite.  To avoid this, you should first write
   * a complete set of "skeleton" methods, i.e. methods with the correct names and
   * the correct return and argument types.
   *
   * To help ensure your code will compile when you submit it for testing, below we have
   * included a set of skeleton tests. You should replace these with more meaningful tests
   * as you write your methods.
   */
  
  @Test
  public void testSetOrder() throws IOException{
    ByteCounter byteCount = new ByteCounter("file.txt");
    byteCount.setOrder("byte");
    assertEquals("SetOrder byte is not working correctly",byteCount.toString(),"10:1 97:4 98:3 99:2");
    byteCount.setOrder("countInc");
    assertEquals("SetOrder Increasing is not working correctly",byteCount.toString(),"10:1 99:2 98:3 97:4");
    byteCount.setOrder("countDec");
    assertEquals("SetOrder Decreasing is not working correctly",byteCount.toString(),"97:4 98:3 99:2 10:1");
    //Now to test the extra credit
    //this also tests for counting when like characters aren't beside eachother
    ByteCounter byteCount2=new ByteCounter(new byte[] {(byte)'b',(byte)'d',(byte)'b',(byte)'a',(byte)'a',(byte)'c',(byte)'c',(byte)'c',(byte)'d'});
    byteCount2.setOrder("countInc");
    assertEquals("the extra credit isn't working for increasing",byteCount2.toString(),"97:2 98:2 100:2 99:3");
    byteCount2.setOrder("countDec");
    assertEquals("the extra credit isn't working for decreasing",byteCount2.toString(),"99:3 97:2 98:2 100:2");
    
  }
  
  @Test
  public void testFormatToString() {
    byte[] test = {(byte)'a', (byte)'b'};
    ByteCounter byteCount = new ByteCounter(test);
    byteCount.toString("char");
    assertEquals("toString isn't working with characters",byteCount.toString("char"),"a:1 b:1");
  }
}