//Stuff To Import
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ByteCounter {
	// The two arays that I use to store the byte value and how many times I've
	// used
	byte data[];
	int count[];

	public ByteCounter(byte stuff[]) {
		// The first thing I do is count the number of unique charachers
		int uniquecount = 0;
		for (int cnt = 0; cnt < stuff.length; cnt++) {
			// Take each character, assume it's unique until proven otherwise
			boolean isunique = true;
			/*
			 * Note I go through the rest of the list to check for Duplicates.
			 * This way, the last time a charcter is used it marks a unique spot
			 */
			for (int place = cnt + 1; place < stuff.length; place++) {
				if (stuff[cnt] == stuff[place]) {
					isunique = false;
					break;
				}
			}
			if (isunique == true)
				++uniquecount;
		}
		// Initalize the size
		data = new byte[uniquecount];
		count = new int[uniquecount];
		/*
		 * Create another array the size of the original string. Once a
		 * character is used, the software then marks all like charcters as used
		 * and counts the occurance
		 */
		boolean isused[];
		isused = new boolean[stuff.length];
		int uniquecounter = 0;
		for (int cnt = 0; cnt < stuff.length; cnt++) {
			if (isused[cnt] == false) {
				data[uniquecounter] = stuff[cnt];
				count[uniquecounter] = 1;
				isused[cnt] = true;
				for (int place = cnt + 1; place < stuff.length; place++) {
					if (stuff[cnt] == stuff[place]) {
						isused[place] = true;
						++count[uniquecounter];
					}
				}
				++uniquecounter;
			}
		}
		// Sets from byte smallest to largest
		setOrder("byte");
	}

	public ByteCounter(String s) throws IOException {
		// Note that this is the only unique line from the last constructor.
		byte[] stuff = (Files.readAllBytes(Paths.get(s)));
		int uniquecount = 0;
		for (int cnt = 0; cnt < stuff.length; cnt++) {
			boolean isunique = true;
			for (int place = cnt + 1; place < stuff.length; place++) {
				if (stuff[cnt] == stuff[place]) {
					isunique = false;
					break;
				}
			}
			if (isunique == true)
				++uniquecount;
		}
		data = new byte[uniquecount];
		count = new int[uniquecount];
		boolean isused[];
		isused = new boolean[stuff.length];
		int uniquecounter = 0;
		for (int cnt = 0; cnt < stuff.length; cnt++) {
			if (isused[cnt] == false) {
				data[uniquecounter] = stuff[cnt];
				count[uniquecounter] = 1;
				isused[cnt] = true;
				for (int place = cnt + 1; place < stuff.length; place++) {
					if (stuff[cnt] == stuff[place]) {
						isused[place] = true;
						++count[uniquecounter];
					}
				}
				++uniquecounter;
			}
		}
		setOrder("byte");
	}

	public int getCount(byte b) {
		// tries to find that byte, then tells the value
		for (int i = 0; i < data.length; i++) {
			if (data[i] == b) {
				return count[i];
			}
		}
		// If it can't find that byte, then it appears 0 times
		return 0;
	}

	public int[] getCount(byte b[]) {
		// Just a simple run through the array and gets the byte count from
		// earlier in each one.
		int[] array;
		array = new int[b.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = getCount(b[i]);
		}
		return array;
	}

	public int[] getCount() {
		return getCount(data);
	}

	public void setOrder(String order) {
		if (data.length > 1) {
			if (order.equals("byte")) {
				// Creates the new data and count array
				byte newdata[];
				int newcount[];
				// This marks when a byte vaule has been used in the original
				// array
				boolean thisisused[];
				newdata = new byte[data.length];
				thisisused = new boolean[data.length];
				newcount = new int[data.length];
				// false until proven true
				boolean allused = false;
				// This counter is to keep track of which value on the new
				// arrays we are on
				int place = 0;
				while (allused == false) {
					int i = 0;
					// find the first byte on the old array we aren't using
					while (thisisused[i] == true) {
						++i;
					}
					byte value = data[i];
					int itslocation = i;
					// Checks all the other bytes that's left
					for (int cnt = i; cnt < data.length; cnt++) {
						// has it been uesd yet?
						if (thisisused[cnt] == false) {
							// Is it's value smaller
							if (data[cnt] < value) {
								// This is the new smallest value, save it.
								itslocation = cnt;
								value = data[cnt];
							}
						}
					}
					// Write that value to the new arrays
					newdata[place] = value;
					newcount[place] = count[itslocation];
					// mark its location as being used
					thisisused[itslocation] = true;
					// assume we've used them all until we find one that hasn't
					// been
					// used
					allused = true;
					for (int cnt3 = 0; cnt3 < data.length; cnt3++) {
						if (thisisused[cnt3] == false) {
							allused = false;
							break;
						}
					}
					++place;
				}
				data = newdata;
				count = newcount;
			} else if (order.equals("countInc")) {
				// The next line is for the extra credit
				// Since it runs in order, if I sort now, it will stay in order
				// with the same size
				setOrder("byte");
				byte newdata[];
				int newcount[];
				boolean thisisused[];
				newdata = new byte[data.length];
				thisisused = new boolean[data.length];
				newcount = new int[data.length];
				boolean allused = false;
				int place = 0;
				while (allused == false) {
					int i = 0;
					while (thisisused[i] == true) {
						++i;
					}
					int value = count[i];
					int itslocation = i;
					for (int cnt = i; cnt < data.length; cnt++) {
						if (thisisused[cnt] == false) {
							// This is the only major change between Byte and
							// CountIn; what a smaller value is
							if (count[cnt] < value) {
								itslocation = cnt;
								value = count[cnt];
							}
						}
					}
					newdata[place] = data[itslocation];
					newcount[place] = value;
					thisisused[itslocation] = true;
					++place;
					allused = true;
					for (int cnt3 = 0; cnt3 < data.length; cnt3++) {
						if (thisisused[cnt3] == false) {
							allused = false;
							break;
						}
					}
				}
				data = newdata;
				count = newcount;
			} else if (order.equals("countDec")) {
				setOrder("byte");
				byte newdata[];
				int newcount[];
				boolean thisisused[];
				newdata = new byte[data.length];
				thisisused = new boolean[data.length];
				newcount = new int[data.length];
				boolean allused = false;
				int place = 0;
				while (allused == false) {
					int i = 0;
					while (thisisused[i] == true) {
						++i;
					}
					int value = count[i];
					int itslocation = i;
					for (int cnt = i; cnt < data.length; cnt++) {
						if (thisisused[cnt] == false) {
							// The only change between countInc and count Dec
							if (count[cnt] > value) {
								itslocation = cnt;
								value = count[cnt];
							}
						}
					}
					newdata[place] = data[itslocation];
					newcount[place] = value;
					thisisused[itslocation] = true;
					++place;
					allused = true;
					for (int cnt3 = 0; cnt3 < data.length; cnt3++) {
						if (thisisused[cnt3] == false) {
							allused = false;
							break;
						}
					}
				}
				data = newdata;
				count = newcount;
			} else
				throw new IllegalArgumentException("Not a valid option");
		}
	}

	public byte[] getElements() {
		return data;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (data.length > 0) {
			for (int cnt = 0; cnt + 1 < data.length; cnt++) {
				s.append(data[cnt]);
				s.append(':');
				s.append(count[cnt]);
				s.append(' ');
			}
			s.append(data[data.length - 1]);
			s.append(':');
			s.append(count[data.length - 1]);
		}
		return s.toString();
	}

	public String toString(String format) {
		if (format.equals("char")) {
			StringBuilder s = new StringBuilder();
			if (data.length > 0) {
				for (int cnt = 0; cnt + 1 < data.length; cnt++) {
					s.append((char) data[cnt]);
					s.append(':');
					s.append(count[cnt]);
					s.append(' ');
				}
				s.append((char) data[data.length - 1]);
				s.append(':');
				s.append(count[data.length - 1]);
			}
			return s.toString();
		} else
			// the insturctions didn't say what to do if it didn't say char, so
			// I assumed just return the string normally
			return toString();
	}
}