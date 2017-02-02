import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Paths;

public class HuffmanCoder {
	public String out;
	public String in;
	public String data;

	public HuffmanCoder(String in, String out) throws IOException {
		// The first step is to create a string from the data
		byte[] stuff = (Files.readAllBytes(Paths.get(in)));
		data = new String(stuff);
		this.in = in;
		this.out = out;
	}

	public void compress() throws IOException {
		BinaryWriter writer = new BinaryWriter(out);
		// I now want to run the Huffman Code to create the tree
		HuffmanCode h = new HuffmanCode(in);
		// run through the string, find the correct character and write it to
		// the file
		for (int i = 0; i < data.length(); i++) {
			if ((byte) data.charAt(i) > 0) {
				writer.writeBinaryArray(h.code((byte) data.charAt(i)));
			}
		}
		writer.close();
	}
}