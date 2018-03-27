import java.util.ArrayList;

public class page {
	private int Length;
    private String Content;
    private ArrayList<record> recordList;
 
    public page(){
    		Length=0;
		this.recordList = new ArrayList<>();
    }
    
    public void add(record record){
    		this.recordList.add(record);
    		Length+=record.getLength();
    }
    public page(int Length, String Content) {
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
