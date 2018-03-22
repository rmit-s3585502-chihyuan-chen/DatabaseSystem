import java.io.BufferedReader;
import java.io.FileReader;

public class dbload {
	BufferedReader input;
	public void ReadFile() { //import txt file
		try {
			input = new BufferedReader(new FileReader("BUSINESS_NAMES_201803.csv"));
		} catch (Exception e) {
			System.out.println("Could not find info.txt");
		}
	}
}
