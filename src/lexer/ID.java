package lexer;

public class ID {
	
	//basic variables
	String value;
	String type;
	int line;
	int row;
	
	//used for variables
	String var_name; 
	int dec_line; //variable that holds the line that the variable is declared
	String str_value = null;
	int num_value = -1;
	String var_type;
	
	//function variables
	String func_name;
	int func_start; //line function starts
	int func_end; //line function ends
	
	//loop variables
	int loop_start;
	int loop_end;
	
	//used for char
	char c;
	
	//default constructor
	public ID(){
		value = "";
		type = "";
		line = -1;
		row = -1;
	}
	
	//non-default
	public ID(String name, String t, int l, int r){
		value = name;
		type = t;
		line = l;
		row = r;
		var_name = null;
		dec_line = -1;
		func_start = -1;
		func_end = -1;
		
	}
	public String getValue(){
		return value;
	}
	public String toString(){
		return value + " " + type;
	}
	public void setupVariable(int line){
		//clearing variables - used if redeclaring a variable;
		num_value = -1;
		str_value = "";
		
		dec_line = line;
		var_name = value;
		type = "VAR";
		value = null;
	}
	
	public void setFunctionStart(int line){
		func_start = line;
	}
	
	public void setFunctionEnd(int line){
		func_end = line;
	}
	
	public int getLine(){
		return line;
	}
	
	public String getType(){
		return type;
	}
	
	public void setupChar(int num){
		type = "CHAR";
		value = null;
		c = (char)num;
	}
	//used for variable dec
	public void set_strValue(String str){
		str_value = str;
		var_type = "STRING";
	}
	//used for variable dec
	public void set_numValue(String str){
		if(str == "")
			num_value = -1;
		else{
		int num = Integer.parseInt(str);
		num_value = num;
		var_type = "INT";
		}
		
	}
	
	public boolean isDec(){
		if(dec_line == -1)
			return false;
		return true;
	}
	
	public boolean isFuncDec(){
		if(func_start > -1 && func_end > -1)
			return true;
		return false;
	}
	public void setupFunction(int line){
		func_name = value;
		value = null;
		type = "FUNC";
		setFunctionStart(line);
		setFunctionEnd(-1); //in case function is being redeclared
	}
	public String get_StrValue(){
		return str_value;
	}
	public int get_numValue(){
		return num_value;
	}
	public char get_charValue(){
		return c;
	}
	public String get_varName(){
		return var_name;
	}
	public String get_funcName(){
		return func_name;
	}
	public void set_Value(String str){
		value = str;
	}
	public String get_varType(){
		return var_type;
	}
}
