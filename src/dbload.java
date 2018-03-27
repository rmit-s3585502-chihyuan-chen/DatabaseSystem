import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class dbload {
	
	public static void main(String[] args) throws IOException {
	int pagesize=4096;
	String csvFile = "BUSINESS_NAMES_201803.csv";
    String cvsSplitBy = "\t";
    ArrayList<filed> fieldList = new ArrayList<>();
    ArrayList<record> recordList = new ArrayList<>();
    ArrayList<page> pagelist = new ArrayList<>();
	int filedNumber=0;
	int length;
	String type;
	String content; 
	BufferedReader br = null;
     String line = "";
		try {
			page page = new page();
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            		fieldList =  new ArrayList<>();
            		String []elements = line.split(cvsSplitBy);
            		
            		if(elements.length<9){
            			continue;
            		}
            		
            		for(int i=0;i<elements.length;i++){
            			if(elements[i]==null){
            				elements[i]="";
            			}
            		}
            		
            		fieldList.add(new filed("String",elements[0]));
            		fieldList.add(new filed("String",elements[1]));
            		fieldList.add(new filed("String",elements[2]));
            		fieldList.add(new filed("String",elements[3]));
            		fieldList.add(new filed("String",elements[4]));
            		fieldList.add(new filed("String",elements[5]));
            		fieldList.add(new filed("String",elements[6]));
            		fieldList.add(new filed("String",elements[7]));
            		fieldList.add(new filed("long",elements[8]));
            		record record = new record(fieldList);
            		if(pagesize - page.getLength()>record.getLength()){
            			page.add(record);
            	}       
            	else{
            			pagelist.add(page);
            			page = new page();
            			page.add(record);
            	}
            		
            }
		
		
      } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
	}
	
