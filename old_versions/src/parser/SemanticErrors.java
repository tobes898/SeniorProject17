package parser;

public class SemanticErrors {

	public static void InputMismatch(int line){
		System.err.println("Input mismatch found at line " + line);
		SemanticCheckFailed();
	}
	
	public static void InvalidFuncCall(int line){
		System.err.println("Invalid Function Call found at line " + line);
		SemanticCheckFailed();
	}
	
	public static void InvalidASCII(int line){
		System.err.println("Invalid ASCII number found at line " + line);
		SemanticCheckFailed();
	}
	
	
	public static void SemanticCheckFailed(){
		System.out.println("Semantic Check Failed!");
		System.exit(0);
	}
	
	public static void notDec(int line){
		System.err.println("Variable is not declared at line " + line);
		SemanticCheckFailed();
	}
	
	public static void MissingLoopEnd(int line){
		System.err.println("Loop end missing from loop found at line " + line);
		SemanticCheckFailed();
	}
	
	public static void MissingLoopStart(){
		System.err.println("Missing start to loop");
		SemanticCheckFailed();
	}
	
	public static void MissingFuncEnd(int line){
		System.err.println("Function end missing from loop found at line " + line);
		SemanticCheckFailed();
	}
	
	public static void InvalidVarName(int line){
		System.err.println("Invalid Variable Name found at line "+ line);
		SemanticCheckFailed();
	}
	public static void InvalidSize(int line){
		System.err.println("Invalid int/str size at line "+ line);
		SemanticCheckFailed();
	}
	
	public static void NegativeNum(int line){
		System.err.println("Op will result in negative number at line " + line);
		SemanticCheckFailed();
	}
}
