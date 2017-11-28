package CodeGen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import lexer.SymbolTable;
//resource used: https://www.csee.umbc.edu/portal/help/nasm/sample.shtml
public class CodeGen {

	//variable for inter code
	ArrayList<String> intercode;
	SymbolTable st;
	
	
	//arraylist for the different portions of assembly
	ArrayList<String> data = new ArrayList<String>();
	ArrayList<String> variables = new ArrayList<String>(); //holds the code for the .bss section
	ArrayList<String> functions = new ArrayList<String>(); //holds all the code for functions
	ArrayList<String> main = new ArrayList<String>(); //holds all the code to be used in main function
	
	String [] validStr ={"print", "seti", "setS", "func", "return","call","loop","end","inc","dec"};
	
	
	public CodeGen(ArrayList<String> stream, SymbolTable t){
		intercode = stream;
		st = t;
	}
	
	
	public void run(){
		//setting up .data section
		setupDataArray();
		
		//setting up .bss section
		setupVarArray();
		main.add("main:");
		String stream;
		int startingWord;
		int lookupVal = -1;
		String checkStr= "";
		String var;
		String val;
		int checker;
		String loopCmp = "";
		String loopEnd = "";
		String loopVal = "";
		String loopVar = "";
		boolean func_check = false;
		for(int i = 0; i < intercode.size(); i++){
			stream = intercode.get(i);
			startingWord = getStartingWord(stream);
			
			switch(startingWord){
			
			case 1: //print
				//char print check
				if(stream.indexOf("char") > -1){
					String getChar = stream.substring(11);
					if(func_check){
						functions.add("mov rdi, fmt3");
						functions.add("mov rsi, \'" + getChar + "\'");
						functions.add("mov rax, 0");
						functions.add("call printf");
					}
					else{
						main.add("mov rdi, fmt3");
						main.add("mov rsi, \'" + getChar + "\'");
						main.add("mov rax, 0");
						main.add("call printf");
					}
				break;	
				}
				//non-char print
				else{
					String getVar = stream.substring(6);
					lookupVal = getLookupVal(getVar);
					if(st.lookup(lookupVal).get_varType().equals("STRING")){
						if(func_check){
							functions.add("mov rdi, fmt2");
							functions.add("mov rsi, " + getVar);
							functions.add("mov rax, 0");
							functions.add("call printf");
						}
						else{
							main.add("mov rdi, fmt2");
							main.add("mov rsi, " + getVar);
							main.add("mov rax, 0");
							main.add("call printf");
						}
					break;
					}
					else{
						if(func_check){
							functions.add("mov rdi, fmt1");
							functions.add("mov rsi, " + "[" + getVar + "]");
							functions.add("mov rax, 0");
							functions.add("call printf");
						}
						else{
							main.add("mov rdi, fmt1");
							main.add("mov rsi, " + "[" + getVar + "]");
							main.add("mov rax, 0");
							main.add("call printf");
						}
						
					}
					break;
				}
			case 2: //seti
				checkStr = stream.substring(5);
				checker = checkStr.indexOf(' ');
				var = checkStr.substring(0, checker);
				val = checkStr.substring(checker+3);
				
				if(func_check){
					functions.add("mov rax, " + val);
					functions.add("mov " + "["+ var + "]" + "," + " rax");
					break;
				}
				else{
					main.add("mov rax, " + val);
					main.add("mov " + "["+ var + "]" + "," + " rax");
					break;
				}
				
				
			case 3: //sets
				checkStr = stream.substring(5);
				checker = checkStr.indexOf(' ');
				var = checkStr.substring(0, checker);
				val = checkStr.substring(checker+3);
				
				if(func_check){
					functions.add("mov rax, " + "\"" + val + "\"");
					functions.add("mov " + "[" + var + "]" + "," + " rax");
					break;
				}
				else{
					main.add("mov rax, " + "\"" + val + "\"");
					main.add("mov " + "["+ var + "]"+ "," + " rax");
					break;
				}
				
			case 4: //func
				functions.add(stream.substring(5) + ":");
				func_check = true;
				break;
				
			case 5: //return
				functions.add("ret");
				func_check = false;
				break;
				
			case 6: //call
				if(func_check){
					functions.add("call " + stream.substring(5));
					break;
				}
				else{
					main.add("call " + stream.substring(5));
					break;
				}
			case 7: //loop
				if(func_check){
					String loop_name = "loop" + stream.substring(4);
					//getting next stream
					stream = intercode.get(i + 1);
					i++;
					checkStr = stream.substring(6);
					checker = checkStr.indexOf(' ');
					var = checkStr.substring(0, checker);
					if(getLookupVal(var) > -1){
						var = "[" + var + "]";
					}
					checkStr = checkStr.substring(checker+1);
					checker = checkStr.indexOf(' ');
					String bool = checkStr.substring(0, checker);
					String cmp;
					String jump = getJump(bool);
					val = checkStr.substring(checker + 1);
					if(getLookupVal(val) > -1){
						val = "[" + val + "]";
					}
					functions.add("mov rbx, " + var );
					functions.add("mov r12, " + val);
					functions.add(loop_name+":");
					
					
					cmp = "cmp " + val + ", " + var;
					loopEnd = jump + " " + loop_name;
					break;
				}
				else{
					String loop_name = "loop" + stream.substring(4);
					//getting next stream
					stream = intercode.get(i + 1);
					i++;
					checkStr = stream.substring(6);
					checker = checkStr.indexOf(' ');
					var = checkStr.substring(0, checker);
					if(getLookupVal(var) > -1){
						var = "[" + var + "]";
					}
					checkStr = checkStr.substring(checker+1);
					checker = checkStr.indexOf(' ');
					String bool = checkStr.substring(0, checker);
					String cmp;
					String jump = getJump(bool);
					val = checkStr.substring(checker + 1);
					if(getLookupVal(val) > -1){
						val = "[" + val + "]";
					}
					main.add("mov rbx, " + var );
					main.add("mov r12, " + val);
					main.add(loop_name+":");
					
					loopVar = var;
					loopVal = val;
					loopCmp = "cmp r12, rbx";
					loopEnd = jump + " " + loop_name;
					break;
				}
			case 8: //end
				if(func_check){
					functions.add("mov rbx, " + loopVar );
					functions.add("mov r12, " + loopVal);
					functions.add(loopCmp);
					functions.add(loopEnd);
					break;
				}
				else{
					main.add("mov rbx, " + loopVar );
					main.add("mov r12, " + loopVal);
					main.add(loopCmp);
					main.add(loopEnd);
					break;
				}
			case 9://inc
				if(func_check){
					functions.add("mov rax, ["+stream.substring(4) +"]");
					functions.add("inc rax");
					functions.add("mov "+ "[" +stream.substring(4) +"]" +", rax");
					break;
				}
				else{
					main.add("mov rax, ["+stream.substring(4) +"]");
					main.add("inc rax");
					main.add("mov "+ "["+stream.substring(4)+ "]"+", rax");
					break;
				}
			case 10://dec
				if(func_check){
					functions.add("mov rax, ["+stream.substring(4) +"]");
					functions.add("dec rax");
					functions.add("mov "+ "[" + stream.substring(4) +"]" +", rax");
					break;
				}
				else{
					main.add("mov rax, ["+stream.substring(4) +"]");
					main.add("dec rax");
					main.add("mov "+ "[" + stream.substring(4) +"]" +", rax");
					break;
				}
			
			}
			
		}
		
		
		//add code to main function
		
		//if function is found start adding code to function array until ret is reached
		
		//call buildASM to build the target assembly file   
	}
	
	//getting lookup value for var
	public int getLookupVal(String name){
		for(int i = 1; i<=st.getSize(); i++){
			if(st.lookup(i).get_varName() != null){
				if(st.lookup(i).get_varName().equals(name))
					return i;
			}
			
		}
		return -1;
	}
	
	
	//setting up .bss section
	public void setupVarArray(){
		variables.add("section .bss");
		for(int i = 1; i <= st.getSize(); i++){
			String type = st.lookup(i).getType();
			if(type.equals("VAR")){
				variables.add(st.lookup(i).get_varName() + ": resb 255");
			}
		}
	}
	
	//setting up .data section
	public void setupDataArray(){
		data.add("section .data");
		data.add("fmt1: db \"%d\",10,0");
		data.add("fmt2: db \"%s\",10,0");
		data.add("fmt3: db \"%c\",10,0");
	}
	
	public String getJump(String str){
		String [] jumps = {"JNE", "JE", "JL", "JGE", "JG", "JLE"};
		String [] bools = {"NTEQL", "BOOLEQL", "LESSTHAN", "GTREQL", "GRTHAN", "LESSEQL"};
		for(int i = 0; i < bools.length; i++){
			if(bools[i].equals(str)){
				return jumps[i];
			}
		}
		return "";
	}

	
	
	public void buildASM() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter("source.asm");
		//printing to asm file
		writer.println("extern printf"); //declaring the external printf function
		writer.println();
		
		//printing .data section to .asm file
		for(int i = 0; i < data.size(); i++){
			writer.println(data.get(i));
		}
		writer.println();
		//printing .bss section
		for(int i = 0; i < variables.size(); i++){
			writer.println(variables.get(i));
		}
		writer.println();
		//setting up .text section
		writer.println("section .text");
		writer.println("global main");
		writer.println("");
		
		//setting up the functions
		for(int i = 0; i < functions.size(); i++){
			writer.println(functions.get(i));
		}
		writer.println();
		
		for(int i = 0; i < main.size(); i++){
			writer.println(main.get(i));
		}
		writer.println("mov rax, 0");
		writer.println("ret");
		
		writer.close();
	}
	
	public int getStartingWord(String str){
		for(int i = 0; i < validStr.length; i++){
			if(str.indexOf(validStr[i]) > - 1){
				if(validStr[i].equals("loop")){
					if(str.indexOf("end") > -1){
						return 8;
					}
					else{
						return i+1;
					}
				}
				return i+1;
				
			}
		}
		return -1;
	}
	
	
	
}
