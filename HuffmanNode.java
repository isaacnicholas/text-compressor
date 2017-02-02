public class HuffmanNode {
	// the pieces to a node
	public byte b;
	public int count;
	public boolean[] code;
	public HuffmanNode left;
	public HuffmanNode right;

	// the constructor
	public HuffmanNode(byte data, int c) {
		b = data;
		count = c;
		left = null;
		right = null;
	}

	public void setLeft(HuffmanNode newleft) {
		left = newleft;
	}

	public void setRight(HuffmanNode newright) {
		right = newright;
	}

	public void setCode(boolean[] newcode) {
		code = newcode;
	}
}