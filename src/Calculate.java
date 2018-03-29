
public class Calculate {
	int pageNumber;
	int recordNumber;
	public Calculate(int pageNumber,int recordNumber){
		this.pageNumber=pageNumber;
		this.recordNumber=recordNumber;
	}
	public void CalculatePage(){
		pageNumber++;
	}
	public int getPageNumber(){
		return pageNumber;
		
	}
	public void CalculateRecord(){
		recordNumber++;
	}
	public int getRecordNumber(){
		return recordNumber;
		
	}
}
