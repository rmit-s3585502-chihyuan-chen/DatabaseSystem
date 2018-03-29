import java.io.File;
import java.io.FileWriter;
import java.util.*;
public class Calculate {
	int pageNumber;
	int recordNumber;
	long startTime ;
	long endTime;
	String[] data= new String[3];
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
		long finishedTime=(endTime-startTime);
		data[0]="The number of records loaded:"+Integer.toString(recordNumber);
		data[1]="The number of pages used:"+Integer.toString(pageNumber);
		data[2]="The number of milliseconds to create the heap file:"+Long.toString(finishedTime);
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
		}
		}
	
	public void printPageNumbers(){
		System.out.println("The number of pages used:"+pageNumber);
	}
	public void printRecordNumbers(){
		System.out.println("The number of records loaded:"+recordNumber);
	}
	public void outputStdout(){
		 File saveFile=new File("stdout");
		    try
		    {
		      FileWriter fwriter=new FileWriter(saveFile);
		     for(int i=0;i<data.length;i++){
             fwriter.write(data[i]+"\n");
		     }
		      fwriter.close();
		    }
		    catch(Exception e)
		    {
		     System.err.println("Cannot output stdout");;
		    }
		  }
	}

