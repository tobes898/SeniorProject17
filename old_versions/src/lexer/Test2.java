package lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {

	public static void main(String [] args) throws FileNotFoundException{
		Test2 t2 = new Test2();
		Scanner input = new Scanner(new File("test1.txt"));
		String str = input.nextLine();
		
		 List<String> allMatches = new ArrayList<String>();
		 Matcher m = Pattern.compile("\\s*(\\d+|\\w+|.)")
		     .matcher(str);
		 while (m.find()) {
		   allMatches.add(m.group());
		 }
		
		 
		 //combining strings enclosed in string into one toke
		 System.out.println(allMatches);
		 for(String s : allMatches){
			 System.out.println(s);
		 }
		 ArrayList<String> cleaned_al = new ArrayList<String>();
		 cleaned_al = t2.cleanWS(allMatches);
		 System.out.println(cleaned_al);
	}
	
	public ArrayList<String> buildTokens(ArrayList<String>al){
		
		for(String str : al){
			
			switch(str){
				case "pluto":
					break;
				case "asteroid":
					break;
				case "comet":
					break;
				case "earth":
					break;
				case "sun":
					break;
				case "quantum":
					break;
				case "blackhole":
					break;
				case "comment":
					break;
				case "vacuum":
					break;
				case "(":
					break;
				case ")":
					break;
				case "[":
					break;
				case "]":
					break;
				case "+":
					break;
				case "-":
					break;
				case "*":
					break;
				case "/":
					break;
				case "\\":
					break;
				default: 
					break;
			}
		}
		
		return al; //TODO:Temp return fix later
	}
	
	
	/**
	 * 
	 * @param al arraylist of lexememes with space
	 * @return arraylist with whitespace sorted out
	 */
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
}
