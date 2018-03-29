import java.util.*;
public class Calculate {
	int pageNumber;
	int recordNumber;
	long startTime ;
	long endTime;
	public Calculate(int pageNumber,int recordNumber,long startTime,long endTime){
		this.pageNumber=pageNumber;
		this.recordNumber=recordNumber;
		this.startTime=startTime;
		this.endTime=endTime;
		
	}
	public void setStatrTime(){
		startTime+=System.currentTimeMillis();
	}
	public void SetEndTime(){
		endTime+=System.currentTimeMillis();
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
	public void CalculateTime(){
		System.out.println("The number of milliseconds to create the heap file:"+(endTime-startTime));
	}
	public void printPageNumbers(){
		System.out.println("The number of pages used:"+pageNumber);
	}
	public void printRecordNumbers(){
		System.out.println("The number of records loaded:"+recordNumber);
	}
}
