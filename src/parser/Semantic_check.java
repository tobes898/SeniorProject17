package parser;

import java.net.StandardSocketOptions;
import java.util.ArrayList;

import TypeChecker.TypeChecker;
import lexer.SymbolTable;
import lexer.Token;

public class Semantic_check {

	SymbolTable st;
	ArrayList<ArrayList<Token>> tokens;
	String lc = "abcdefghijklmnopqrstuvwxyz_";
	String uc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	String digits = "0123456789";
	int loopStart = 0;
	int loopEnds = 0;
	
	public Semantic_check(SymbolTable t, ArrayList<ArrayList<Token>> token){
		st = t;
		tokens = token;
		
	}
	
	public void run(){
		//looping through line of tokens
		TypeChecker tc = new TypeChecker(st);
		ArrayList<Token> token_stream = new ArrayList<Token>();
		for(int i = 0; i< tokens.size(); i++){
			token_stream = tokens.get(i);
			String func_call =  token_stream.get(0).getType();
			
			runSemanticCheck(func_call,tc, token_stream,i);
			
			
		}
		if(loopStart< loopEnds){
			SemanticErrors.MissingLoopStart();
		}
		System.out.println("Semantic check sucessful!");
		
		
	}
	
	public SymbolTable getST(){
		return st;
	}
	
	public void runSemanticCheck(String s, TypeChecker tc, ArrayList<Token> token_stream ,int i){
		String idVal;
		int lookup;
		int line = i;
		boolean endFound = false;
		//variables used for checking nested loops
		
		
		switch(s){
		case "CPRINT":
			tc.checkType(token_stream);
			st = tc.getST();
			break;
		case "SETVI":
			tc.checkType(token_stream);
			st = tc.getST();
			break;
		case "SETVS":
			tc.checkType(token_stream);
			st = tc.getST();
			break;
		case "PRINTV":
			//getting symbol table number
			idVal = token_stream.get(2).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			
			//checking if value is declared
			if(!(st.lookup(lookup).isDec()))
				SemanticErrors.notDec(st.lookup(lookup).getLine());
			break;
		
		case "ONE": //TODO: May need to look at this again in future
			idVal = token_stream.get(2).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			
			//checking if value is declared
			if(!(st.lookup(lookup).isDec()))
				SemanticErrors.notDec(st.lookup(lookup).getLine());
			else if(!st.lookup(lookup).get_StrValue().isEmpty())
				SemanticErrors.InputMismatch(st.lookup(lookup).getLine());
			
			//add code to make sure second var is 1
			idVal = token_stream.get(7).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			int checkInt = Integer.parseInt(st.lookup(lookup).getValue());
			if(checkInt != 1){
				SemanticErrors.InputMismatch(st.lookup(lookup).getLine());
			}
			//add code to make sure op doesn't result in negative number
			idVal = token_stream.get(5).getType();
			
			if(idVal.equals("SUBOP")){
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				checkInt = st.lookup(lookup).get_numValue();
				if(checkInt-1 < 0){
					SemanticErrors.NegativeNum(st.lookup(lookup).getLine());
				}
			}
			
			break;
			
		case "SFUN":
			idVal = token_stream.get(2).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			
			//if it has already been declared before
			if(st.lookup(lookup).isFuncDec()){
				st.lookup(lookup).setupFunction(i);
			}
			else if(st.lookup(lookup).isDec()){
				SemanticErrors.InvalidVarName(st.lookup(lookup).getLine());
			}
			//if new function
			else{
				String variable = st.lookup(lookup).getValue(); // setting to var name
				//checking if variable is valid
				
				
				for(int j = 0; j < variable.length(); j ++){
					char check = variable.charAt(j);
					//checking to make sure a number doesn't come first
					if(j == 0){
						if(digits.indexOf(check) != -1){
							//System.out.println("Check 1");
							SemanticErrors.InvalidVarName(i);
					}
						else{
							//System.out.println("Check 2");
							if(lc.indexOf(check) == -1 && uc.indexOf(check) == -1){
								SemanticErrors.InvalidVarName(i);
							}
						}
					}
					else{ //massive if statement to check if character is either a digit _ or letter
						if(lc.indexOf(check) == -1 && uc.indexOf(check) == -1 && digits.indexOf(check) == -1){
							SemanticErrors.InvalidVarName(i);
						}
					}
				}
				st.lookup(lookup).setupFunction(i);
			}
			
			//looping through remaining token streams looking for func end
			endFound = false;
			for(int j = i; j < tokens.size(); j++){
				String check_func = tokens.get(j).get(0).getType();
				if(check_func.equals("EFUN")){
					endFound = true;
					break;
				}		
			}
			if(!endFound)
				SemanticErrors.MissingFuncEnd(st.lookup(lookup).getLine());
			break;
			
			
		case "EFUN":
			idVal = token_stream.get(2).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			line = st.lookup(lookup).getLine(); //TODO: Make sure this remains as start line
			if(!(st.lookup(lookup).getType().equals("FUNC")))
				SemanticErrors.InputMismatch(line);
			
			//setting function end point
			st.lookup(lookup).setFunctionEnd(i);
			
			
		case "FUNCL":
			idVal = token_stream.get(2).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			
			//checking if value is declared
			if(!(st.lookup(lookup).isFuncDec()))
				SemanticErrors.notDec(st.lookup(lookup).getLine());
			break;
			
		case "LSTRT":
			//checking if var's are declared
			loopStart ++;
			//1st var
			idVal = token_stream.get(2).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			if(!(st.lookup(lookup).isDec()))
				if(!(st.lookup(lookup).getType().equals("INT")))
					SemanticErrors.InputMismatch(st.lookup(lookup).getLine());
			
			//2nd var
			idVal = token_stream.get(4).getType();
			idVal = idVal.substring(3);
			lookup = Integer.parseInt(idVal);
			if(!(st.lookup(lookup).isDec()))
				if(!(st.lookup(lookup).getType().equals("INT")))
					SemanticErrors.InputMismatch(st.lookup(lookup).getLine());
			
			//looping through remaining token streams looking for loop end
			endFound = false;
			for(int j = i; j < tokens.size(); j++){
				String check_func = tokens.get(j).get(0).getType();
				if(check_func.equals("LEND")){
					endFound = true;
					break;
				}		
			}
			if(!endFound)
				SemanticErrors.MissingLoopEnd(st.lookup(lookup).getLine());
			break;
			
		case "LEND":
			loopEnds++;
			
		}
		
		
		//looping through symbol table checking that int and str sizes are valid
		String lentest = "";
		for(int j = 1; j <=st.getSize(); j++){
			if(st.lookup(j).getType().equals("STRING")){
				lentest = st.lookup(j).getValue();
				if(lentest.indexOf("WHITESPACE") > -1){
					lentest = lentest.replaceAll("WHITESPACE", " ");
					st.lookup(j).set_Value(lentest);
				}
				if(lentest.length()>8){
					SemanticErrors.InvalidSize(st.lookup(j).getLine());
				}
			}
			else if(st.lookup(j).getType().equals("INT")){
				lentest = st.lookup(j).getValue();
				if(lentest.length()>8){
					SemanticErrors.InvalidSize(st.lookup(j).getLine());
				}
			}
		}
		
	}
	
}
