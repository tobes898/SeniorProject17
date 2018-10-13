
package Compiler;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import CodeGen.CodeGen;
import TypeChecker.TypeChecker;
import interCodeGen.InterCodeGen;
import lexer.LexAnalysis;
import lexer.SymbolTable;
import lexer.Token;
import parser.Parser;
import parser.Semantic_check;
import parser.SyntaxCheck;
import parser.UpdateSymbolTable;
public class master {

	public static void main(String [] args) throws FileNotFoundException{
		//Front End of Compiler
		
		LexAnalysis lex = new LexAnalysis();
		Parser parser = new Parser();
		lex.run();
 		ArrayList<ArrayList<Token>> arr = lex.getTokensArr();
		ArrayList<ArrayList<Token>> arr1 = arr;
		ArrayList<Token> func_calls = new ArrayList<Token>(); //used to store func calls
		//these will be added back to tokens array for semantic parsing
		for(int i = 0; i< arr.size(); i++){
			if(arr.get(i).isEmpty()){
				arr.remove(i);
			}
		}
		
		
		
		
		for(int i = 0; i< arr.size(); i++){
			//System.out.println(arr.get(i));
			func_calls.add(arr.get(i).get(0));
		}
		
		SymbolTable st = lex.getST();
		//runs the parser, tokens array from lex is passed as parameter
		parser.run(arr);
		//restoring original array after syntax pass
		for(int i = 0; i < arr1.size(); i++){
			arr1.get(i).add(0, func_calls.get(i));
		}
		
		Semantic_check sc = new Semantic_check(st, arr1);
		sc.run();
		st = sc.getST();
		
		
		System.out.println("Parsing Complete!");
		
		InterCodeGen intercode = new InterCodeGen(arr1, st);
		
		intercode.run();
		intercode.printingInterCode();
		
		ArrayList<String> intercodeArr = intercode.getInterCode();
		
		CodeGen codegen = new CodeGen(intercodeArr,st);
		codegen.run();
		codegen.buildASM();
		System.out.println("Target program built!");
		
		
		//Back End of Compiler
		
	}
}
