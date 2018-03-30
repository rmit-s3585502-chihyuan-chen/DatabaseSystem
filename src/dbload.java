import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: dbload main class launches the process of loading ,creating and timing
 * @Date: Created on 21/03/2018
 * @Version 1.0
 */
public class dbload {

	public static void main(String[] args) throws IOException {
		long startTime = 0;//set the strat time
		long endTime=0;//set the end time of creating heap file
		int pageNumber=0;//set the default page number
		int recordNumber=0;//set the default record number
		int pagesize; //set the page size
		String fileName; //set the fileName
		String cvsSplitBy = "\t"; //set the symbol of splitting 
		ArrayList<Field> fieldList = new ArrayList<>(); //set the ArrayList to store field
		ArrayList<Record> recordList = new ArrayList<>(); //set the ArrayList to store record
		ArrayList<Page> pagelist = new ArrayList<>(); //set the ArrayList to store page
		Calculate calculator= new Calculate(pageNumber,recordNumber,startTime,endTime); //import particular attributes to run the calculate's process 
		int length; //set length
		int index; //set index
		String type; //set type
		String content; //set content;
		int disk;// set disk to be used to calculate the page space
		BufferedReader br = null; //set the BufferReader to read data
		String line = ""; //set the line to store the content 
		try {
		    if(args[0].equals("-p")){  //use args to avoid hardcode path and run the particular command to launch
		    	pagesize=Integer.parseInt(args[1]);
		    	fileName=args[2];
		    }else{//use args to avoid hardcode path and run the particular command to launch
		    	pagesize=Integer.parseInt(args[1]);
		    	fileName=args[0];
		    }
			Page page = new Page(); //create the new page
			br = new BufferedReader(new FileReader(fileName));//load file
			br.readLine();//read data's line by line
			while ((line = br.readLine()) != null) {
				fieldList = new ArrayList<>(); //create the fieldList to store elements which contains each field's information
				String[] elements = line.split(cvsSplitBy);//store each field's information

				if (elements.length < 9) { //sometimes some fields in the data may not have the value then it will skip it to keep loading
					continue;
				}

				for (int i = 0; i < elements.length; i++) { //replace the null value of field
					if (elements[i] == null) {
						elements[i] = "";
					}
				}
                //add each elements which contains different types of fields into fieldList
				fieldList.add(new Field("String", elements[0]));
				fieldList.add(new Field("String", elements[1]));
				fieldList.add(new Field("String", elements[2]));
				fieldList.add(new Field("String", elements[3]));
				fieldList.add(new Field("String", elements[4]));
				fieldList.add(new Field("String", elements[5]));
				fieldList.add(new Field("String", elements[6]));
				fieldList.add(new Field("String", elements[7]));
				fieldList.add(new Field("long", elements[8]));
				//add each elements which contains different types of fields into fieldList
				Record record = new Record(fieldList);//create the new record to store field
				calculator.CalculateRecord();//calculate record number
				if (pagesize - page.getLength() -4> record.getLength()) { //if the page has the space, the page will store this record
					page.add(record);
				} else { //if the page does not have the space to add new record, it will create new page to store record
					//if (pagelist.size() == 100)   use the small page to test
					//	break;                      use the small page to test
                
					pagelist.add(page);
					page = new Page();
					calculator.CalculatePage();//calculate page nubmer
					page.add(record);
				}
                // testing to printout data
				// for(Page p:pagelist){testing to printout data
				// for(Record r:p.getRecord()){
				// for(Field f:r.getField()){
				// System.out.println(f.getContent());

				// }
				// }
				// } testing to printout data
			}
			calculator.setStatrTime();//set the start timing mark for recroding the time
			OutputHeap out = new OutputHeap("heap." + pagesize); //output the heap file
			for (int i = 0; i < pagelist.size(); i++) {
				page=pagelist.get(i);
				out.writeInt(page.getRecord().size());
				index = 2 + (pagelist.size() * 2);
				//write record index
				for (int j = 0; j < page.getRecord().size(); j++) {
					Record record=page.getRecord().get(j);
					out.writeInt(index);
					index += record.getLength();
				}
				
				for(int q=0;q<page.getRecord().size();q++){
					Record record=page.getRecord().get(q);
					index = 18;
					//write field index
					for (int x = 0; x < record.getField().size(); x++) {
						out.writeInt(index);
						index+=record.getField().get(x).getLength();
					}
					//write field
					for(int z=0;z<record.getField().size();z++){
						out.writeField(record.getField().get(z));
					}			
				}
				disk=(pagesize-page.getLength());//get the available capacity
				if (disk < 0){ //if disk has a little space, it will use"" to fill in the available space to make each page has the same size
					System.out.println("");
				}
				byte availableSpace[] = new byte[disk];//set the capacity for available space
				out.writeBinary(availableSpace); //writ in the heap file
			}
			calculator.SetEndTime();// set the end of timing
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
    calculator.CalculateTime();//calculate how much time did it create the heap file
    calculator.outputStdout();//output the time of creating,page numbers and  record numbers
	}

}
