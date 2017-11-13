package parser;

import java.util.ArrayList;

import lexer.ID;
import lexer.SymbolTable;
import lexer.Token;

public class UpdateSymbolTable {

	
	SymbolTable st;
	ArrayList<ArrayList<Token>> token_stream;
	
	
	char [] lc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	char [] uc = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	char [] nums = {'0','1','2','3','4','5','6','7','8','9'};
	char valid_symbol = '-';
	
	
	int st_size = -1;
	public UpdateSymbolTable(SymbolTable t, ArrayList<ArrayList<Token>> tokens){
		st = t;
		token_stream = tokens;
	}
	//FIXME: Remove at a later date
	//Now considered legacy - no used in current version of parsing
	public SymbolTable edit_ST(){
		//loops through symbol table values
		String func_call = ""; //holds function call-to be used to update symbol table
		ID id; //holds id in symbol table
		int ID_line;
		for(int i = 1; i <= st.getSize(); i++){
			id = st.lookup(i);
			ID_line = id.getLine() - 1;//gets line to look up function call - minus 1 to acct for 0 index
			
			func_call = token_stream.get(ID_line).get(0).getType(); //returns the function call for line
			
			//if char
			if(func_call.equals("CPRINT")){
				charWork(i);
			}
			//if variable
			else if(func_call.equals("SETVS")){
				AssignmentWork_STR(i);
				i++; //skipping next ID
			}
			else if(func_call.equals("SETVI")){
				AssignmentWork_INT(i);
				i++;//skipping next ID
			}

		}
		
		
		return st;//fine
		
	}
	
	
	
	public void functionWork(int i){
		//TODO: Add me later
	}
	
	
	public void AssignmentWork_INT(int i){
		String ID_type = st.lookup(i+1).getType();
		if(ID_type.equals("STRING")){
			SemanticErrors.InputMismatch(st.lookup(i).getLine());
		}
		else{
			st.lookup(i).setupVariable(st.lookup(i).getLine()); //setups ID as variable
			st.lookup(i).set_numValue(st.lookup(i+1).getValue()); //sets the variable value equal to the next ID
		}
	}
	//function that creates variables in symbol table
	public void AssignmentWork_STR(int i){
		//setting up variable
		String ID_type = st.lookup(i + 1).getType();
		if(ID_type.equals("INT")){
			//code to setup string var
			SemanticErrors.InputMismatch(st.lookup(i).getLine());
		}
		else{
			st.lookup(i).setupVariable(st.lookup(i).getLine()); //setups ID as variable
			st.lookup(i).set_strValue(st.lookup(i+1).getValue()); //sets the variable value equal to the next ID
		}
		
		//TODO: Add setting a variable equal to a variable
	}
	
	
	
	
	//Char function that handles setting up char in symbol table
	public void charWork(int i){
		//checking if char value is within range
		String convert_temp;
		convert_temp = st.lookup(i).getValue();
		int c_lookup;
		c_lookup = Integer.parseInt(convert_temp);
		if(c_lookup >= 0 && c_lookup < 256){
			st.lookup(i).setupChar(c_lookup);
		}
		else{
			SemanticErrors.InvalidASCII(st.lookup(i).getLine());
		}
		
	}
	
	//TODO: Major Overhaul of this function...
	//change to the type check 
	//-valid inputs, correct variable names
	public SymbolTable update_ST(){
		
		//getting size of symbol table
		st_size = st.getSize();
		
		for(int i = 1; i<=st_size; i++){
			boolean num = true;
			//get ID
			ID id = st.lookup(i);
			//get value of id
			String value = id.getValue();
			
			
			//loop for each char in id checking to make sure it is a valid ID
			for(int j = 0; j < value.length(); j++){
				char ph = value.charAt(j);
				System.out.println(ph);
				//checking if first character is a digit
				if(j == 0){
					if(Character.isDigit(ph)){
						SemanticErrors.InvalidVarName(id.getLine());
					}
				}
				else{
					if(!Character.isLetterOrDigit(ph)){
						if(!(ph == valid_symbol)){
							SemanticErrors.InvalidVarName(id.getLine());
						}
					}else if(Character.isLetter(ph) ==true){
						num = false;
					}
				}
			}
			
			
			//updates to symbol table
			if(!num){
				st.lookup(i).setupVariable(id.getLine());
			}
			
			
		}
		return st;
	}
	//testing 
	public void testSTValue(){
		System.out.println(st.lookup(2).getValue());
	}
	
}
