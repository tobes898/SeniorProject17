package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import lexer.Token;

public class SyntaxCheck {
	
	public void run(ArrayList<ArrayList<Token>> tokens) throws FileNotFoundException{
		//declaring class var
		SyntaxCheck syncheck = new SyntaxCheck();
		Rules r = new Rules();
		
		//TODO:temp to avoid error
		
		//handling token streams
		
		//creating token_stream to parse through and removing it
		//from master list
		ArrayList<Token> token_stream;
		
		for(int i = 0; i< tokens.size(); i++){
			token_stream = tokens.get(i);
			
			//handling syntax check
			
			//getting top node (function call)
			if(token_stream.size() != 0){
				Token func_call = token_stream.get(0);
				
				//sets string to type, checks to see if it matches a rule set
				String check = func_call.getType();
				String [] rules = r.getRules(check);
				//if rule set not found, error thrown with location of error
				if(rules == null){
					SyntaxErrors.InvalidFunc(func_call.location());
				}
				
				
				//checking syntax of token stream
				syncheck.compareSyntax(token_stream, rules);
			}
			
			
		}
		System.out.println("Syntax check successful!");
		
		
		
		//TODO:fix this
		//syncheck.compareSyntax(tokenLine, rules);
	}
	
	//eliminating all whitespace from array as it is not necessary
	public ArrayList<ArrayList<Token>> removeAllWS(ArrayList<ArrayList<Token>> tokens){
		int size1;
		for(int i=0; i<tokens.size(); i++){
			size1 = tokens.get(i).size();
			for(int j = 0; j<size1; j++){
				String ph = tokens.get(i).get(j).getType();
				if(ph.equals("WHITESPACE")){
					tokens.get(i).remove(j);
					size1 --;
				}
			}
		}
		
		return tokens;
		
	}
	
	
	public boolean opCheck(String s){
		String [] ops = {"ADDOP", "SUBOP"};
		for(String op : ops){
			if(s.equals(op))
				return true;
		}
		return false;
	}
	
	public boolean boolCheck(String s){
		String [] bools = {"GTREQL", "GRTHAN", "LESSTHAN", "LESSEQL", "BOOLEQL","NTEQL"};
		for(String bool : bools){
			if(s.equals(bool))
				return true;
		}
		return false;
	}
	
	public void compareSyntax(ArrayList<Token> tokens, String [] rules){
		String token;
		String rule_token;
		
		//variable to be used for errors
		int line = tokens.get(0).getLine();
		
		//removing func call from token stream as it is already handled
		tokens.remove(0);
		
		//compare sizes of arrays to make sure there are no missing elements
		if(!(rules.length == tokens.size())){
			//for(int i = 0; i<rules.length; i++)
				//System.out.print(rules[i] + " ");
			//System.out.println();
			//System.out.println(tokens);
			//System.out.println(rules.length);
			//System.out.println(tokens.size());
			if(rules.length > tokens.size()){
				SyntaxErrors.tooLittleToken(line);
			}
			else{
				SyntaxErrors.TooManyToken(line);
				
			}
		}
		
		//loops through comparing each token to correct syntax
		for(int i=0;i < rules.length; i++){
			token = tokens.get(i).getType();
			rule_token = rules[i];
			//handling an ID token
			//TODO: add check to make sure token length more than 2
			if(token.substring(0,2).equals("ID")){
				token = "ID";
			}
			
			//handing ops
			if(rule_token.equals("OP")){
				if(opCheck(token))
					token = "OP";
			}
			
			
			//handling bools	
			if(rule_token.equals("BOOL")){
				if(boolCheck(token))
					token = "BOOL";
			}
			
			//if tokens don't match error thrown
			if(!(token.equals(rule_token))){
				SyntaxErrors.InvalidToken(tokens.get(i).location());
			}
			
		}
	}
	
}
