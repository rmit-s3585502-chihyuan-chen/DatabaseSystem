import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputHeap {
	DataOutputStream os;

	public OutputHeap(String fileName) {
		try {
			os = new DataOutputStream(new FileOutputStream(fileName));
		} catch (IOException e) {
			System.err.println("Cannot output file");
		}
	}

	public void writeField(Field field) {
		try {
			if (field.getType().equals("String")) {
				writeBinary(field.getContent().getBytes("UTF-8"));
			} else {
				os.writeLong(Long.valueOf(field.getContent()));
			}
		} catch (IOException e) {
			System.err.println("Cannot write String into file");
		}
	}
	public void writeInt(int index) {
		try {
			os.writeInt(index);
		} catch (IOException e) {
			System.err.println("Cannot write Int into file");
		}

	}

	public void writeShort(Short shortValue) {
		try {
			os.writeShort(shortValue);
		} catch (IOException e) {
			System.err.println("Cannot write Short into file");
		}
	}

	public void writeBinary(byte[] bytes) {
		try {
			os.write(bytes);
		} catch (IOException e) {
			System.err.println("Cannot write Short into file");
		}
	}
}