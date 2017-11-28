package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexAnalysis {

	SymbolTable st  = new SymbolTable();//variable for the symbol table
	int counter = 1;
	//arraylist that each line of token streams
	ArrayList<ArrayList<Token>> tokens_master = new ArrayList<ArrayList<Token>>();

	public void run() throws FileNotFoundException{
		LexAnalysis lx = new LexAnalysis(); // var for lexer
		Scanner input = new Scanner(new File("source.txt")); //scanner for source code file
		PrintWriter writer = new PrintWriter("tokens.txt"); //writer for token file
		int counter = 1; //keeping track of line number
		while(input.hasNextLine()){
			String str = input.nextLine();
			str = str.trim();//trims the line up
			if(str.length() != 0){
				List<String> allMatches = lx.getLexeme(str);
				//replacing ws with ws token
				ArrayList<String> cleaned_al = new ArrayList<String>();
				cleaned_al = lx.cleanWS(allMatches);
				//create tokens
				
				ArrayList<Token> tokens = lx.buildTokens(cleaned_al,counter);
				
				//adds the token stream to the master list
				tokens_master.add(tokens);
				
				//prints the tokens to text file for visual purposes
				//TODO: remove after front end is complete
				for(int i = 0; i < tokens.size(); i++){
					if(i ==  tokens.size() - 1){
						writer.println(tokens.get(i).getType());
					}
					else{
						writer.print(tokens.get(i).getType() + " ");
					}
					
				}
				counter++;
			}
			
			}
		writer.close();
		
		//prints symbol table to file for visual purposes
		//TODO: Remove after front end is complete
		writeST(lx.st);
		setST(lx.st);
			
		 

	}
	public List<String> getLexeme(String str){
		 List<String> allMatches = new ArrayList<String>();
		 Matcher m = Pattern.compile("\\s*(\\d+|\\w+|.)")
		     .matcher(str);
		 while (m.find()) {
		   allMatches.add(m.group());
		 }
		 return allMatches;
	}
	
	public ArrayList<Token> buildTokens(ArrayList<String>al , int line){
		ArrayList<Token> tokens = new ArrayList<Token>();
		String concatStr = "";
		boolean strbool = false;
		boolean cmtbool = false;
		boolean dupcheck = false;
		
		for(int i = 0; i < al.size(); i++){
			String str = al.get(i);
			switch(str){
				case "pluto":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("CPRINT", line, i));
						break;
					}
				case "asteroid":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("SETVI", line, i));
						break;
					}
				case "comet":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("SETVS", line, i));
						break;
					}
				case "earth":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("PRINTV", line, i));
						break;
					}
				case "sun":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("SFUN", line, i));
						 break;
					}
				case "sun_end":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("EFUN", line, i));
						break;
					}
				case "sun_run":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("FUNCL", line, i));
						break;
					}
				case "quantum":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("LSTRT", line, i));
						break;
					}
				case "quantum_end":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("LEND", line, i));
						break;
					}
				case "blackhole":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("ONE", line, i));
						break;
					}
				case "comment":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						cmtbool = true;
						break;
					}
				case "vacuum":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("INFT", line, i));
						break;
					}
				case "(":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("LPAR", line, i));
						break;
					}
				case ")":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("RPAR", line, i));
						break;
					}
				case "[":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("LBRK", line, i));
						break;
					}
				case "]":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("RBRK", line, i));
						break;
					}
				case "+":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("ADDOP", line, i));
						break;
					}
				case "-":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("SUBOP", line, i));
						break;
					}
					//Not used in lang TODO: add if time
					/*
				case "*":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add("MULOP");
						break;
					}
					*/ 
				case "<":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					
					else{
						String peek = "";
						if(i+1 < al.size()){
							peek = al.get(i+1);
							if(peek.equals("=")){
								tokens.add(new Token("GTREQL", line, i));
								i = i+1;
								break;
							}
							else{
								tokens.add(new Token("GRTHAN", line, i));
								break;
							}
						}
					}
				case ">":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						String peek = "";
						if(i+1 < al.size()){
							peek = al.get(i+1);
							if(peek.equals("=")){
								tokens.add(new Token("LESSEQL", line, i));
								i = i+1;
								break;
							}
							else{
								tokens.add(new Token("LESSTHAN", line, i));
								break;
							}
							
						}
						
					}
				case "=":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						String peek = "";
						if(i+1 < al.size()){
							peek = al.get(i+1);
							if(peek.equals("=")){
								tokens.add(new Token("BOOLEQL", line, i));
								i = i+1;
								break;
							}
							else{
								tokens.add(new Token("EQL", line, i));
								break;
							}
						}
						
					}
				case "!":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					String peek = "";
					if(i+1<al.size()){
						peek = al.get(i+1);
						if(peek.equals("=")){
							tokens.add(new Token("NTEQL", line ,i));
							i = i+1;
							break;
						}
						
					}
					//not used in lang TODO: Add if time
					/*
				case "/":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add("DIVOP");
						break;
					}
					*/
					
				case "\\":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("CHAROP", line, i));
						break;
					}
				case "\"":
					if(strbool){
						strbool = false;
						st.insert(counter, new ID(concatStr, "STRING", line, i));
						
						concatStr = "";
						tokens.add(new Token("ID-" + counter, line, i));
						counter++;
						tokens.add(new Token("STRINGOP", line, i));
						break;
					}
					else if(cmtbool){
						break;
					}
					else{
						strbool = true;
						tokens.add(new Token("STRINGOP", line, i));
						break;
					}
				case ",":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("VAROP", line, i));
						break;
					}
				case ";":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("LNEND", line, i));
						break;
					}
				case ":":
					if(strbool){
					}
					else{
						String peek1 = "";
						if(i+1 < al.size()){
							peek1 = al.get(i+1);
							if(peek1.equals(":")){
								cmtbool = false;
								i = i+1;
								break;
							}
							else{
								break;
							}
						}
					}
					
				case "bigfreeze":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						tokens.add(new Token("END", line, i));
					}
				case "WHITESPACE":
					if(strbool){
					}
					else if(cmtbool){
						break;
					}
					else{
						break;
					}
				default: 
					if(strbool){
						concatStr = concatStr + al.get(i);
						break;
					}
					else if(cmtbool){
						break;
					}
					else{
						for(int j = 1; j-1 < st.st.size(); j++){ //TODO: THIS NEEDS TO BE BETTER IMPLT - ONLY TEMP FIX
							if(st.st.get(j).getValue().equals(str) && !(Character.isDigit(st.st.get(j).getValue().charAt(0)))){
								tokens.add(new Token("ID" +"-" + j, line, i));
								dupcheck = true;
								break;
							}
						}
						if(dupcheck){
							dupcheck = false;
							break;
						}
						else{
							st.insert(counter, new ID(str,"INT", line, i));
							tokens.add(new Token("ID" +"-" + counter, line, i));
							counter++;
							break;	
						}
							
						
						
					}
			}
		}
		return tokens; 
	}
	
	
	//method to clean up whitespace
	public ArrayList<String> cleanWS(List<String>al){
		ArrayList<String> cleaned_al = new ArrayList<String>(); //arraylist where whitespace is cleaned
		
		for(int i = 0; i < al.size(); i++){
			String str = al.get(i);
			if(str.contains(" ")){
				while(str.contains(" ")){
					cleaned_al.add("WHITESPACE");
					str = str.substring(1, str.length());
				}
				cleaned_al.add(str);
			}
			else{
				cleaned_al.add(str);
			}
		
	}
		return cleaned_al;
	}
	
	//prints symbol table to file
	public void writeST(SymbolTable full_st) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter("SymbolTable.txt"); //writer for token file
		for(int i = 1; i <= full_st.getSize(); i++){
			writer.println(i + " " + full_st.lookup(i));
		}
		writer.close();
	}
	
	
	public ArrayList<ArrayList<Token>> getTokensArr(){
		return tokens_master;
	}
	
	
	public void setST(SymbolTable t){
		st = t;
	}
	public SymbolTable getST(){
		return st;
	}
	//may not be needed
	public boolean variableCheck(String token){
		char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'
				+ 'q','r','s','t','u','v','w','x','y','z'};
		
		for(char c : alphabet){
			if(token.indexOf(c) > -1){
				return true;
				
			}
		}
		return false;
	}
}
