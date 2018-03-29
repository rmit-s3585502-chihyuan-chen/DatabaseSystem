import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class dbload {

	public static void main(String[] args) throws IOException {
		long startTime = 0;
		long endTime=0;
		int pageNumber=0;
		int recordNumber=0;
		int pagesize = 4096;
		String csvFile = "BUSINESS_NAMES_201803.csv";
		String cvsSplitBy = "\t";
		ArrayList<Field> fieldList = new ArrayList<>();
		ArrayList<Record> recordList = new ArrayList<>();
		ArrayList<Page> pagelist = new ArrayList<>();
		Calculate calculator= new Calculate(pageNumber,recordNumber,startTime,endTime);
		int filedNumber = 0;
		int length;
		int index;
		String type;
		String content;
		int disk;
		BufferedReader br = null;
		String line = "";
		try {
			Page page = new Page();
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				fieldList = new ArrayList<>();
				String[] elements = line.split(cvsSplitBy);

				if (elements.length < 9) {
					continue;
				}

				for (int i = 0; i < elements.length; i++) {
					if (elements[i] == null) {
						elements[i] = "";
					}
				}

				fieldList.add(new Field("String", elements[0]));
				fieldList.add(new Field("String", elements[1]));
				fieldList.add(new Field("String", elements[2]));
				fieldList.add(new Field("String", elements[3]));
				fieldList.add(new Field("String", elements[4]));
				fieldList.add(new Field("String", elements[5]));
				fieldList.add(new Field("String", elements[6]));
				fieldList.add(new Field("String", elements[7]));
				fieldList.add(new Field("long", elements[8]));
				Record record = new Record(fieldList);
				calculator.CalculateRecord();
				if (pagesize - page.getLength() -4> record.getLength()) {
					page.add(record);
				} else {
					if (pagelist.size() == 100)
						break;
                
					pagelist.add(page);
					page = new Page();
					calculator.CalculatePage();
					page.add(record);
				}

				// for(Page p:pagelist){testing to printout data
				// for(Record r:p.getRecord()){
				// for(Field f:r.getField()){
				// System.out.println(f.getContent());

				// }
				// }
				// } testing to printout data
			}
			calculator.setStatrTime();
			OutputHeap out = new OutputHeap("heap." + pagesize);
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
				disk=(pagesize-page.getLength());
				if (disk < 0){
					System.out.println("");
				}
				byte availableSpace[] = new byte[disk];
				out.writeBinary(availableSpace);
			}
			calculator.SetEndTime();
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
	calculator.printRecordNumbers();
    calculator.printPageNumbers();
    calculator.CalculateTime();
	}

}
