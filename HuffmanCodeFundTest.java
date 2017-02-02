import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanCodeFundTest {
  
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
  public void testByteArrayArgumentConstructorAndToString() {
    HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'});
    assertEquals("Testing an array with byte input failed","98: 1\n97: 0",hc.toString());
  }
  
  @Test
  public void testStringArgumentConstructor() throws IOException {
    HuffmanCode hc = new HuffmanCode("file.txt");
    assertEquals("Testing an array with string input failed","97: 0\n98: 11\n99: 101\n10: 100",hc.toString());
  }
  
  @Test
  public void testByteAndCountArraysConstructor() {
    HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    assertEquals("Testing an array with string input failed","98: 1\n97: 0",hc.toString());
    HuffmanCode hc2 = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'}, new int [] {3, 2});
    assertEquals("Testing an array with string input failed","97: 1\n98: 0",hc2.toString());;
  }
  
  @Test
  public void testCodeMethod() {
    HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    boolean[] code = hc.code((byte)'a');
    assertFalse("Code method dosen't work.",code[0]);
    boolean[] code2=hc.code((byte)'b');
    assertTrue("Code method doesn't work.",code2[0]);
  }
  
  @Test
  public void testCodeString(){
    HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    assertEquals("first CodeString doesn't work", "0",hc.codeString((byte)'a'));
    assertEquals("second CodeString doesn't work", "1",hc.codeString((byte)'b'));
  }
}