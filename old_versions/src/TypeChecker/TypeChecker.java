package TypeChecker;

import java.util.ArrayList;

import lexer.SymbolTable;
import lexer.Token;
import parser.SemanticErrors;

public class TypeChecker {
	//Note: the reason for the dash at the end of each string is to incorporate the dash as
	//a valid variable symbol
	String lc = "abcdefghijklmnopqrstuvwxyz_";
	String uc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	String digits = "0123456789";
	SymbolTable st  = new SymbolTable();//variable for the symbol table

	//constructor
	public TypeChecker(SymbolTable t){
		st = t;
	}
	
	public boolean isInt(String str){
		try{
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
	public void checkType(ArrayList<Token> tokens){
		String func_call = tokens.get(0).getType();
		switch(func_call){
		
		case "CPRINT":
			String str = tokens.get(3).getType();
			int index = Integer.parseInt(str.substring(3));//getting id lookup number
			str = st.lookup(index).getValue(); // getting value of ID
			//checking if ID is int
			if(isInt(str)){
				//parse of char number
				int num = Integer.parseInt(str);
				//check if valid ASCII
				if(num > -1 && num < 256){
					st.lookup(index).setupChar(num);//sets up ID as Char
				}
				else{
					SemanticErrors.InvalidASCII(tokens.get(0).getLine());
				}
			}
			else{
				SemanticErrors.InputMismatch(tokens.get(0).getLine());
			}
			break;
			
		case "SETVI":
			//setting up variable values
			int lookup; //var to hold st lookup index
			int val_lookup;
			String variable = tokens.get(2).getType(); //getting the ID and lookup num
			variable = variable.substring(3); //setting var = to lookup num
			lookup = Integer.parseInt(variable); //converting to int form
			
			//for var_int
			String var_int = tokens.get(5).getType();
			var_int = var_int.substring(3);
			val_lookup = Integer.parseInt(var_int);
			var_int = st.lookup(val_lookup).getValue();
			
			int line = tokens.get(2).getLine(); //used for throwing errors
			
			//checking if var_int is a valid int before any other work is completed 
			if(!(isInt(var_int))){
				SemanticErrors.InputMismatch(line);
			}
			
			//checking if it has been declared already
			//if yes
			if(st.lookup(lookup).isDec()){
				st.lookup(lookup).set_strValue(null);//removing the string value if there is one
				st.lookup(lookup).set_numValue(var_int);
				break;
			}
			
			
			
			//if no
			variable = st.lookup(lookup).getValue(); // setting to var name
			//checking if variable is valid
			
			
			for(int i = 0; i < variable.length(); i ++){
				char check = variable.charAt(i);
				//checking to make sure a number doesn't come first
				if(i == 0){
					if(digits.indexOf(check) != -1){
						//System.out.println("Check 1");
						SemanticErrors.InvalidVarName(line);
				}
					else{
						//System.out.println("Check 2");
						if(lc.indexOf(check) == -1 && uc.indexOf(check) == -1){
							SemanticErrors.InvalidVarName(line);
						}
					}
				}
				else{ //massive if statement to check if character is either a digit _ or letter
					if(lc.indexOf(check) == -1 && uc.indexOf(check) == -1 && digits.indexOf(check) == -1){
						SemanticErrors.InvalidVarName(line);
					}
				}
			}
			
			//setting up variable
			st.lookup(lookup).setupVariable(line);
			st.lookup(lookup).set_numValue(var_int);
			break;
			
		case "SETVS":
			//setting up variable values
			//var to hold st lookup index
			String var_str;
			variable = tokens.get(2).getType(); //getting the ID and lookup num
			variable = variable.substring(3); //setting var = to lookup num
			lookup = Integer.parseInt(variable); //converting to int form
			
			//for var_int
			var_str = tokens.get(6).getType();
			var_str = var_str.substring(3);
			val_lookup = Integer.parseInt(var_str);
			var_str = st.lookup(val_lookup).getValue();
			
			line = tokens.get(2).getLine(); //used for throwing errors
			
			//checking if it has been declared already
			//if yes
			if(st.lookup(lookup).isDec()){
				st.lookup(lookup).set_numValue(null);
				st.lookup(lookup).set_strValue(var_str);
				break;
			}
			
			
			
			//if no
			variable = st.lookup(lookup).getValue(); // setting to var name
			//checking if variable is valid
			
			
			for(int i = 0; i < variable.length(); i ++){
				char check = variable.charAt(i);
				//checking to make sure a number doesn't come first
				if(i == 0){
					if(digits.indexOf(check) != -1){
						//System.out.println("Check 1");
						SemanticErrors.InvalidVarName(line);
				}
					else{
						//System.out.println("Check 2");
						if(lc.indexOf(check) == -1 && uc.indexOf(check) == -1){
							SemanticErrors.InvalidVarName(line);
						}
					}
				}
				else{ //massive if statement to check if character is either a digit _ or letter
					if(lc.indexOf(check) == -1 && uc.indexOf(check) == -1 && digits.indexOf(check) == -1){
						SemanticErrors.InvalidVarName(line);
					}
				}
			}
			
			//setting up variable
			st.lookup(lookup).setupVariable(line);
			st.lookup(lookup).set_strValue(var_str);
			break;
			
		}
		
	}
	
	public SymbolTable getST(){
		return st;
	}
}
