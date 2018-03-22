import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class dbload {
	BufferedReader input;
	public void ReadFile() {
		try {
			input = new BufferedReader(new FileReader("BUSINESS_NAMES_201803.csv"));
			String[]output;
			while(input.ready()){
				output=input.readLine().split("/t");
				for(int i=0;i<=output.length-1;i++){
					System.out.println(output[i]);
				}
			    }
				
			
		} catch (Exception e) {
			System.err.println("Could not find info.txt");
		}
	}
    
}
