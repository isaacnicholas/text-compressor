import java.util.LinkedList;
import java.io.IOException;
import java.util.Iterator;
public class HuffmanList{
  private LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
  public HuffmanList(byte[] stuff){
    //Creates a ByteCounter and adds each element to that list
    ByteCounter b=new ByteCounter(stuff);
    //Note that the extra credit is handled because of the other extra credit in ByteCounter
    b.setOrder("countInc");
    byte[] data=b.getElements();
    int[] counts=b.getCount();
    for(int i=0;i<data.length;i++){
      list.add(new HuffmanNode(data[i],counts[i]));
    }
  }
  public HuffmanList(String s) throws IOException{
    ByteCounter b=new ByteCounter(s);
    b.setOrder("countInc");
    byte[] data=b.getElements();
    int[] counts=b.getCount();
    for(int i=0;i<data.length;i++){
      list.add(new HuffmanNode(data[i],counts[i]));
    }
  }
  public HuffmanList(byte[] data, int[] count){
    //Tests for non-unique characters
    for(int cnt=0; cnt<data.length;cnt++){
      for(int cnt2=cnt+1;cnt2<data.length;cnt2++){
        if(data[cnt]==data[cnt2]){
          throw new IllegalArgumentException("Non-Unique Character Found");
        }
      }
    }
    //tests for negative values
    for(int cnt=0;cnt<count.length;cnt++){
      if(count[cnt]<0){
        throw new IllegalArgumentException("Negative Character Found");
      }
    }
    //now it's time to sort
    //first by character (copied and modified from bytecouter
    byte newdata[];
    int newcount[];
    //This marks when a byte vaule has been used in the original array
    boolean thisisused[];
    newdata=new byte[data.length];
    thisisused=new boolean[data.length];
    newcount=new int[data.length];
    //false until proven true
    boolean allused=false;
    //This counter is to keep track of which value on the new arrays we are on
    int cnt2=0;
    while(allused==false){
      int i=0;
      //find the first byte on the old array we aren't using
      while(thisisused[i]==true){
        ++i;}
      byte value=data[i];
      int itslocation=i;
      //Checks all the other bytes that's left
      for(int cnt=i;cnt<data.length;cnt++){
        //has it been uesd yet?
        if(thisisused[cnt]==false){
          //Is it's value smaller
          if(data[cnt]<value){
            //This is the new smallest value, save it.
            itslocation=cnt;
            value=data[cnt];
          }
        }
      }
      //Write that value to the new arrays
      newdata[cnt2]=value;
      newcount[cnt2]=count[itslocation];
      //mark its location as being used
      thisisused[itslocation]=true;
      //assume we've used them all until we find one that hasn't been used
      allused=true;
      for(int cnt3=0;cnt3<data.length;cnt3++){
        if(thisisused[cnt3]==false){
          allused=false;
          break;
        }
      }
      ++cnt2;
    }
    data=newdata;
    count=newcount;
    //now to sort by count
    newdata=new byte[data.length];
    thisisused=new boolean[data.length];
    newcount=new int[data.length];
    allused=false;
    cnt2=0;
    while(allused==false){
      int i=0;
      while(thisisused[i]==true){
        ++i;}
      int value=count[i];
      int itslocation=i;
      for(int cnt=i;cnt<data.length;cnt++){
        if(thisisused[cnt]==false){
          //This is the only major change between Byte and CountInc, what a smaller value is
          if(count[cnt]<value){
            itslocation=cnt;
            value=count[cnt];
          }
        }
      }
      newdata[cnt2]=data[itslocation];
      newcount[cnt2]=value;
      thisisused[itslocation]=true;
      ++cnt2;
      allused=true;
      for(int cnt3=0;cnt3<data.length;cnt3++){
        if(thisisused[cnt3]==false){
          allused=false;
          break;
        }
      }
    }
    data=newdata;
    count=newcount; 
    for(int i=0;i<data.length;i++){
      list.add(new HuffmanNode(data[i],count[i]));
    }
  }
  public Iterator<HuffmanNode> iterator(){
    return list.iterator();
  }
}