package parser;

public class SyntaxErrors {

	
	public static void InvalidFunc(String loc){
		System.err.println("Syntax Error: Invalid function call at " + loc);
		SyntaxCheckFailed();
	}
	public static void InvalidPar(){
		System.err.println("Syntax Error: Invalid parentheses");
		SyntaxCheckFailed();
	}
	public static void InvalidToken(String loc){
		System.err.println("Syntax Error: Invalid Token found at " + loc);
		SyntaxCheckFailed();
	}
	
	public static void TooManyToken(int line){
		System.err.println("Syntax Error: Unexpected Token at line " + line);
		SyntaxCheckFailed();
	}
	public static void tooLittleToken(int line){
		System.err.println("Syntax Error: Missing Token at line " + line);
		SyntaxCheckFailed();
	}
	public static void SyntaxCheckFailed(){
		System.err.println("Syntax Check Failed!");
		System.exit(0);
	}
	
}
