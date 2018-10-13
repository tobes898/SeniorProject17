package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexer.Token;

//master class for parser (Syntax Check & Semantic Check
public class Parser {

	public void run(ArrayList<ArrayList<Token>> tokens) throws FileNotFoundException{
		SyntaxCheck sc = new SyntaxCheck();
		sc.run(tokens);
	}
}
