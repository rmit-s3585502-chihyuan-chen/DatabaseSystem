import java.util.ArrayList;

public class record {
	private int Length;
    private String Content;
    private ArrayList<filed> fieldList;
    

    public record(ArrayList<filed> fieldList){
    		this.fieldList = fieldList;
    }
    
    public record(int Length, String Content) {
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
