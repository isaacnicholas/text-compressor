import java.util.LinkedList;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.Queue;

class HuffmanCode {
	// This is the intinal node, the top of the tree
	public HuffmanNode start = new HuffmanNode((byte) 0, 0);
	// These are for use to create the code
	private boolean codes[];
	private boolean newcodes[];
	private boolean finalanswer[];

	public HuffmanCode(byte[] stuff){
    //This piece creates the list which I create the tree from
    LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
    ByteCounter b=new ByteCounter(stuff);
    b.setOrder("countInc");
    byte[] data=b.getElements();
    int[] count=b.getCount();
    for(int i=0;i<data.length;i++){
      list.add(new HuffmanNode(data[i],count[i]));
    }
    //And for for the creating of the tree
    //While there is more than one node in the tree
    while(list.size()>1){
      //Take the first two smallest values and create a node with a left and right
      HuffmanNode h=new HuffmanNode((byte)0,list.get(0).count+list.get(1).count);
      h.setLeft(list.get(0));
      h.setRight(list.get(1));
      //If its's the largest node, add it to the back
      if(h.count>list.getLast().count){
        list.addLast(h);
      }
      //else, find the first node that it is smaller than or equal to and add it in front
      else{
        for(int i=0;i<list.size();i++){
          if(h.count<=list.get(i).count){
            list.add(i,h);
            break;
          }
        }
      }
      //Remove the first two nodes (the ones that were replaced)
      list.removeFirst();
      list.removeFirst();
    }
    //There's only one node left, make it the top of the tree variable
    start=list.getFirst();
    //Now to save the binary encoding
    createCode(start);
  }

	public HuffmanCode(String s) throws IOException{
    LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
    //This is the only line that changed between constructors
    ByteCounter b=new ByteCounter(s);
    b.setOrder("countInc");
    byte[] data=b.getElements();
    int[] count=b.getCount();
    for(int i=0;i<data.length;i++){
      list.add(new HuffmanNode(data[i],count[i]));
    }
    while(list.size()>1){
      HuffmanNode h=new HuffmanNode((byte)0,list.get(0).count+list.get(1).count);
      h.setLeft(list.get(0));
      h.setRight(list.get(1));
      if(h.count>list.getLast().count){
        list.addLast(h);
      }
      else{
        for(int i=0;i<list.size();i++){
          if(h.count<=list.get(i).count){
            list.add(i,h);
            break;
          }
        }
      }
      list.removeFirst();
      list.removeFirst();
    }
    start=list.getFirst();
    createCode(start);
  }

	public HuffmanCode(byte[] data, int[] count){
    LinkedList<HuffmanNode> list = new LinkedList<HuffmanNode>();
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
        throw new IllegalArgumentException("Negative Count Found");
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
    while(list.size()>1){
      HuffmanNode h=new HuffmanNode((byte)0,list.get(0).count+list.get(1).count);
      h.setLeft(list.get(0));
      h.setRight(list.get(1));
      if(h.count>list.getLast().count){
        list.addLast(h);
      }
      else{
        for(int i=0;i<list.size();i++){
          if(h.count<=list.get(i).count){
            list.add(i,h);
            break;
          }
        }
      }
      list.removeFirst();
      list.removeFirst();
    }
    start=list.getFirst();
    createCode(start);
  }

	private void createCode(HuffmanNode h) {
		// If it has a left or right, then it's not at the end, and not a node
		// with valuable data
		if (h.left == null && h.right == null) {
			h.setCode(codes);
		}
		// it has nodes below it, calculate everything around it
		else {
			// this code adds a false (or 0) to the boolean array, signifying
			// going left
			// if it doesn't have anything, create a array of one
			if (codes == null) {
				codes = new boolean[1];
			}
			// if it does, then create a new array, copy over all the old array
			// and add a 0 to the end
			else {
				boolean[] newcodes;
				newcodes = new boolean[codes.length + 1];
				for (int i = 0; i < codes.length; i++) {
					newcodes[i] = codes[i];
				}
				codes = newcodes;
			}
			// now go to the left
			createCode(h.left);
			// once i hit here, I've gone as far left as I can, so I'm taking
			// off the last left
			boolean[] newcodes;
			newcodes = new boolean[codes.length - 1];
			if (newcodes != null) {
				for (int i = 0; i < newcodes.length; i++) {
					newcodes[i] = codes[i];
				}
			}
			codes = newcodes;
			// now I'm doing the same thing to the right
			if (codes == null) {
				codes = new boolean[1];
				// I have to set it to true to signify going right
				codes[0] = true;
			} else {
				newcodes = new boolean[codes.length + 1];
				for (int i = 0; i < codes.length; i++) {
					newcodes[i] = codes[i];
				}
				newcodes[codes.length] = true;
				codes = newcodes;
			}
			createCode(h.right);
			newcodes = new boolean[codes.length - 1];
			if (newcodes != null) {
				for (int i = 0; i < newcodes.length; i++) {
					newcodes[i] = codes[i];
				}
			}
			codes = newcodes;
		}
	}

	public boolean[] code(byte b) {
		// sets all the variables that are used to null to reset whatever else
		// happened to them
		finalanswer = null;
		// these are used to save the boolean array
		codes = null;
		newcodes = null;
		// the first recursion, starting from the beginning
		code(b, start);
		if (finalanswer != null) {
			return finalanswer;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void code(byte b, HuffmanNode h) {
		// tranverses the same way as createCode
		if (h != null) {
			if (h.b == b) {
				finalanswer = h.code;
				return;
			} else {
				code(b, h.left);
				code(b, h.right);
			}
		}
	}

	public String codeString(byte b) {
		// this is really simple, it creates the boolean array from above and
		// turns that booelan
		// into a string
		boolean[] bits = code(b);
		StringBuilder s = new StringBuilder();
		for (int cnt = 0; cnt < bits.length; cnt++) {
			if (bits[cnt] == true)
				s.append('1');
			else
				s.append('0');
		}
		return s.toString();
	}

	public String toString(){
    //this code is for a level order transversal, though reading from right to left
    //the code that is used the most will be at the top right, so that is why the code works as such
    StringBuilder sb=new StringBuilder();
    Queue<HuffmanNode> q=new LinkedList<HuffmanNode>();
    q.add(start);
    while(!q.isEmpty()){
      HuffmanNode h=q.poll();
      if(h.b!=(byte)0){
        sb.append(h.b);
        sb.append(": ");
        sb.append(codeString(h.b));
        sb.append('\n');
      }
      if(h.right!=null)
        q.add(h.right);
      if(h.left!=null)
        q.add(h.left);
    }
    sb.setLength(sb.length()-1);
    return sb.toString();
  }
}