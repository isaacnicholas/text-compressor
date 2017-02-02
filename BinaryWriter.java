import java.io.FileOutputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Arrays;

public class BinaryWriter {
	/*
	 * A map provide quick access to each binary position in a byte. Note, here
	 * used a kind of 'Little-Endian' (least significant position in the byte
	 * corresponds to smallest stream address) method to organize bits in the
	 * byte.
	 */
	private static byte[] binaryMap = { (byte) 0x01, (byte) 0x02, (byte) 0x04,
			(byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80 };

	private final int bufferSize = 100;
	private byte[] buffer = new byte[bufferSize];
	private int pos = 0;

	private FileOutputStream fos = null;

	// Constructor for initialization file handles
	public BinaryWriter(String fileName) throws FileNotFoundException {
		this.fos = new FileOutputStream(fileName);
	}

	// Close method as normal IO Classes
	public void close() throws IOException {
		this.flush();
		this.fos.close();
		this.fos = null;
	}

	// Write boolean array into buffer
	public void writeBinaryArray(boolean[] boolArray) throws IOException {
		this.write(boolArray, 0, boolArray.length);
	}

	// Concrete implementation of above method
	private void write(boolean[] boolArray, int iStart, int iEnd)
			throws IOException {
		if (this.pos + iEnd - iStart <= this.bufferSize * 8) {
			for (int i = iStart; i < iEnd; i++, this.pos++) {
				if (boolArray[i])
					this.buffer[this.pos / 8] |= BinaryWriter.binaryMap[this.pos % 8];
			}
		} else {
			this.write(boolArray, iStart, iStart + this.bufferSize * 8
					- this.pos);
			this
					.write(boolArray, iStart + this.bufferSize * 8 - this.pos,
							iEnd);
		}
		if (this.pos == this.bufferSize * 8) {
			// Write buffer into file
			this.fos.write(buffer);
			this.fos.flush();
			// Clean buffer and reset position marker
			Arrays.fill(this.buffer, (byte) 0x00);
			this.pos = 0;
		}
	}

	// Write current effective data in buffer into file
	private void flush() throws IOException {
		if (this.pos != 0) {
			// Write this part of buffer into output file
			byte[] tmp = new byte[this.pos / 8 + 1];
			System.arraycopy(this.buffer, 0, tmp, 0, tmp.length);
			this.fos.write(tmp);
			this.fos.flush();
			// Clean buffer and reset position marker
			Arrays.fill(this.buffer, (byte) 0x00);
			this.pos = 0;
		}
	}

	// Finalizer method ensure all bits in the buffer have been written to the
	// file
	// before destructing BinaryWriter object
	protected void finalize() throws IOException {
		if (this.pos != 0)
			this.flush();
		if (this.fos != null)
			this.fos.close();
	}
}