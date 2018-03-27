
public class Field {
	 private int Length;
	    private String Content;
	    private String Type;

	    public Field(String Type, String  Content) {
	        this.Content = Content;
	        this.Type = Type;
	        calLength();
	    }
	    
	    private void calLength(){
	    		if(Type.equals("String")){
	    			Length = Content.getBytes().length;
	    		}
	    		else if(Type.equals("long")){
	    			Length = 8;
	    		}
	    		
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

	    public String getType() {
	        return Type;
	    }

	    public void setType(String Type) {
	        this.Type = Type;
	      
	    }


}
