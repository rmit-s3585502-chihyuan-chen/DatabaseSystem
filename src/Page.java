import java.util.ArrayList;





public class Page {
	private int Length;
    private String Content;
    private ArrayList<Record> recordList;
 
    public Page(){
    		Length=2;
		this.recordList = new ArrayList<>();
    }
    
    public ArrayList<Record> getRecord(){
    	return recordList;
    }
    
    public void add(Record record){
    		this.recordList.add(record);
    		Length+=record.getLength();
    }
    public Page(int Length, String Content) {
        this.Length = Length;
        this.Content = Content;
    }

    public int getLength() {
        return Length;
    }
    
    public void setLength(int Length) {
    		this.Length = Length;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}

