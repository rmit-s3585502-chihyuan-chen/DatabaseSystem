import java.util.ArrayList;





public class Record {
	private int Length;
    private String Content;
    private ArrayList<Field> fieldList;
    

    public Record(ArrayList<Field> fieldList){
    		this.fieldList = fieldList;
    		calLength();
    		
    }
    
    public ArrayList<Field> getField(){
    	return fieldList;
    }
    private void calLength(){
	Length=18;
    	for(int i=0;i<fieldList.size();i++){
		Length+= fieldList.get(i).getLength();
	}
}
    
    
    public Record(int Length, String Content) {
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
