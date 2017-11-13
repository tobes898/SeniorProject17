package lexer;

public class Token {

	String type = "";
	int line = -1;
	int row = -1;
	
	
	public Token(String t, int l, int r){
		type = t;
		line = l;
		row = r;
	}
	
	
	
	//getters for Token class
	
	public int getLine(){
		return line;
	}
	public int getRow(){
		return row;
	}
	
	public String getType(){
		return type;
	}
	//used for testing
	public String toString(){
		return type + " line: " + line + " token: " + row;
	}
	
	public String location(){
		return "line " + line + " token " + row;
	}
}
