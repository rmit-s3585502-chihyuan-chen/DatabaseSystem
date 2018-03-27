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
    ArrayList<Field> fieldList = new ArrayList<>();
    ArrayList<Record> recordList = new ArrayList<>();
    ArrayList<Page> pagelist = new ArrayList<>();
	int filedNumber=0;
	int length;
	int index ;
	String type;
	String content; 
	BufferedReader br = null;
     String line = "";
		try {
			Page page = new Page();
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
            		
            		fieldList.add(new Field("String",elements[0]));
            		fieldList.add(new Field("String",elements[1]));
            		fieldList.add(new Field("String",elements[2]));
            		fieldList.add(new Field("String",elements[3]));
            		fieldList.add(new Field("String",elements[4]));
            		fieldList.add(new Field("String",elements[5]));
            		fieldList.add(new Field("String",elements[6]));
            		fieldList.add(new Field("String",elements[7]));
            		fieldList.add(new Field("long",elements[8]));
            		Record record = new Record(fieldList);
            		if(pagesize - page.getLength()>record.getLength()){
            			page.add(record);
            	}       
            	else{
            			pagelist.add(page);
            			page = new Page();
            			page.add(record);
            	}
            		
            		
            		//for(Page p:pagelist){
            		//	for(Record r:p.getRecord()){
            		//		for(Field f:r.getField()){
            		//			System.out.println(f.getContent());
            				
            		//			}
            		//		}
            		//	}
            		}
            		
            OutputHeap out=new OutputHeap("heap."+pagesize);
            for(int i=0;i<pagelist.size();i++){
            out.writeInt(page.getRecord().size());
            index=2+(pagelist.size()*2);
            System.out.print(index);
            for(int j=0;j<pagelist.get(i).getRecord().size();j++){
            	out.writeInt(index);
            	index+=pagelist.get(i).getRecord().get(j).getLength();
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
	
