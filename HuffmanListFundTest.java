import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Test;

public class HuffmanListFundTest {
  
  /* JUnit tests of fundamental functionality
   *
   * Use these JUnit tests to ensure that your code correctly
   * implements the fundamental functionality.
   */
  
  @Test
  public void testByteArrayArgumentConstructor() {
    HuffmanList hList = new HuffmanList(new byte[] {(byte)'a', (byte)'b', (byte)'a'});
    Iterator<HuffmanNode> iter = hList.iterator();
    
    assertTrue(
               "HuffmanList constructed from byte array ['a', 'b', 'a'] should not be empty",
               iter.hasNext());
    
    HuffmanNode hNode = iter.next();
    assertEquals(
                 "The first element of HuffmanList should contain byte 'b'",
                 (byte)'b', hNode.b);
    assertEquals(
                 "The first element of HuffmanList should be with count 1",
                 1, hNode.count);
    assertTrue(
               "HuffmanList constructed from byte array ['a', 'b', 'a'] should have 2 elements",
               iter.hasNext());
    
    hNode = iter.next();
    assertEquals(
                 "The second element of HuffmanList should contain byte 'a'",
                 (byte)'a', hNode.b);
    assertEquals(
                 "The second element of HuffmanList should be with count 2",
                 2, hNode.count);
    assertFalse(
                "HuffmanList constructed from byte array ['a', 'b', 'a'] should only have 2 elements",
                iter.hasNext());
  }
  
  @Test
  public void testStringArgumentConstructor()throws IOException {
    char[] charList = new char[]{'\n', 'c', 'b', 'a'};
    int[] countList = new  int[]{  1,   2,   3,   4};
    
    HuffmanList list = new HuffmanList("file.txt"); // aaaabbbcc\n
    HuffmanNode hNode;
    
    Iterator<HuffmanNode> iter = list.iterator();
    for (int i = 0; i < 4; i++) {
      assertTrue(
                 "HuffmanList constructed from file 'file.txt' should create a list length 4.  However, your list only has " + i + " elements",
                 iter.hasNext());
      
      hNode = iter.next();
      assertEquals(
                   "The #" + (i+1) + " element in the HuffmanList, constructed from 'file.txt' should be character '" + charList[i] + "'.",
                   (byte)charList[i], hNode.b);
      assertEquals(
                   "The count of #" + (i+1) + " element, '" + (byte)charList[i] + "', in the HuffmanList, constructed from file 'file.txt', should be " + countList[i] + ".",
                   countList[i], hNode.count);
    }
    assertFalse(
                "A HuffmanList constructed from 'file.txt' should create a list of length 4. However, your list has a 5-th element.",
                iter.hasNext());
  }
  
  @Test
  public void testIterator() throws IOException {
    HuffmanList list = new HuffmanList("file.txt"); // aaaabbbcc (and a new line '\n')
    Iterator<HuffmanNode> it = list.iterator();
    assertEquals(
                 "This method provides an iterator of the list. Check that the iterator has values.",
                 true, it.hasNext());
    
    HuffmanNode hn = it.next();
    assertEquals(
                 "This method provides an iterator of the list. Check the byte value of the first node in the list ",
                 (byte)'\n', hn.b);
    
    it.next();
    it.next();
    hn = it.next();
    assertEquals(
                 "This method provides an iterator of the list. Check the count of the last node. ",
                 4, hn.count);
    assertEquals(
                 "This method provides an iterator of the list.  Check that hasNext is false.  There should be no more elements in iterator. ",
                 false, it.hasNext());
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
  public void testByteAndIntegerArrayConstructor() {
    HuffmanList list = new HuffmanList(new byte[]{(byte)'a', (byte)'b'}, new int[] {1, 2});
    Iterator<HuffmanNode> it = list.iterator();
    assertEquals("the first byte isn't correct",(byte)'a',it.next().b);
    assertEquals("the second byte isn't correct",(byte)'b',it.next().b);
    HuffmanList list2 = new HuffmanList(new byte[]{(byte)'a', (byte)'b'}, new int[] {2, 1});
    Iterator<HuffmanNode> it2 = list2.iterator();
    assertEquals("the first byte isn't correct",(byte)'b',it2.next().b);
    assertEquals("the second byte isn't correct",(byte)'a',it2.next().b);
  }
  @Test
  public void testExtraCredit(){
    HuffmanList list=new HuffmanList(new byte[]{(byte)'b', (byte)'c', (byte)'a', (byte)'a', (byte)'c'});
    byte[] correct=new byte[]{ (byte)'b',(byte)'a', (byte)'c'};
    Iterator<HuffmanNode> it = list.iterator();
    for(int i=0;i<3;i++){
      assertEquals("Extra Credit Is Failing at node "+(i+1),correct[i],it.next().b);
    }
  }
}