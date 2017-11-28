package interCodeGen;

import java.util.ArrayList;

import lexer.SymbolTable;
import lexer.Token;

public class InterCodeGen {

	ArrayList<ArrayList<Token>> tokens; //normal var to hold the token line arrays
	SymbolTable st = new SymbolTable();
	ArrayList<String> intercode; //var to hold the inter code
	
	public InterCodeGen(ArrayList<ArrayList<Token>> t, SymbolTable tb){
		
		tokens = t;
		st = tb;
		intercode = new ArrayList<String>();
	}
	
	public void run(){
		ArrayList<Token> token_stream;
		String func_call;
		String idVal;
		int lookup;
		int count = 1; //counter used for loop dec
		int loopEnds = 0;
		String type =""; //used for checking type
		for(int i = 0; i < tokens.size(); i++){
			token_stream = tokens.get(i); //getting a line of the array list of tokens
			String str;
			func_call = token_stream.get(0).getType();
			
			switch(func_call){
			
			case "CPRINT":
				//print char "ID"
				idVal = token_stream.get(3).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				
				intercode.add("print char " + st.lookup(lookup).get_charValue());
				break;
				
			case "SETVI":
				str = "seti ";
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				str += st.lookup(lookup).get_varName() + " = ";
				
				idVal = token_stream.get(5).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				str += st.lookup(lookup).getValue();
				
				intercode.add(str); //seti [var] = [value]
				break;
				
			case "SETVS":
				//set "var" = str "ID"
				str = "setS ";
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				str += st.lookup(lookup).get_varName() + " = ";
				
				idVal = token_stream.get(6).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				str += st.lookup(lookup).getValue();
				
				intercode.add(str); //setS [var] = ["[value]"]
				break;
				
			case "PRINTV":
				//print lit "var"
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				
				intercode.add("print " + st.lookup(lookup).get_varName()); //print [var]
				break;
			case "ONE":
				//add/sub var(dest) (1)
				

				//OP
				idVal = token_stream.get(5).getType();
				
				if(idVal.equals("ADDOP"))
					str= "inc ";
				else{
					str= "dec ";
				}
				//first var
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				str += st.lookup(lookup).get_varName();
				intercode.add(str);
				
				break;
			case "SFUN":
				//funcSTRT "var"
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				
				intercode.add("func " + st.lookup(lookup).get_funcName()); //func [var]
				break;
			case "EFUN":
				//funcEND "var" 
				intercode.add("return"); //ret
				break;
			case "FUNCL":
				//goto func "var" line #
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				
				intercode.add("call " + st.lookup(lookup).get_funcName()); //call [var]
				break;
			case "LSTRT":
				intercode.add("loop"+ count);
				//if "val" "bool" "val" goto L
				str = "while ";
				
				//getting first ID
				idVal = token_stream.get(2).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				type = st.lookup(lookup).getType();
				if(type.equals("VAR")){
					str += st.lookup(lookup).get_varName();
				}
				else{
					str += st.lookup(lookup).getValue();
				}
				
				//getting BOOL
				idVal = token_stream.get(3).getType();
				str += " " + idVal + " ";
				
				//getting second ID
				idVal = token_stream.get(4).getType();
				idVal = idVal.substring(3);
				lookup = Integer.parseInt(idVal);
				type = st.lookup(lookup).getType();
				if(type.equals("VAR")){
					str += st.lookup(lookup).get_varName();
				}
				else{
					str += st.lookup(lookup).getValue();
				}
				
				intercode.add(str);
				count++;
				break;
			case "LEND":
				loopEnds++;
				intercode.add("end loop" + (count - loopEnds));
				//incrementing count up for another loop dec
				//return
				break;
			
			}
			
		}
	}
	
	public void printingInterCode(){
		for(int i = 0; i < intercode.size(); i++){
			System.out.println(intercode.get(i));
		}
	}
	public ArrayList<String> getInterCode(){
		return intercode;
	}
}
