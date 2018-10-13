package parser;

public class Rules {
	//basic rules
	String [] pluto = {"LPAR", "CHAROP", "ID", "RPAR", "LNEND"};
	String [] asteroid = {"LPAR", "ID", "RPAR", "LPAR", "ID", "RPAR", "LNEND"};
	String [] comet = {"LPAR", "ID", "RPAR", "LPAR", "STRINGOP", "ID", "STRINGOP", "RPAR", "LNEND"};
	String [] earth = {"LPAR", "ID", "RPAR", "LNEND"};
	String [] blackhole = {"LPAR", "ID","RPAR", "LPAR", "OP", "RPAR", "ID", "LNEND"};
	String [] sun = {"LPAR", "ID", "RPAR", "LNEND"};
	String [] sun_end = {"LPAR", "ID", "RPAR", "LNEND"};
	String [] sun_run = {"LPAR", "ID", "RPAR", "LNEND"};
	String [] quantum = {"LBRK", "ID", "BOOL", "ID", "RBRK", "LNEND"};
	String [] quantum_end = {"LNEND"};
	public String[] getRules(String s){
		switch(s){
		case "CPRINT":
			return pluto;
		case "SETVI":
			return asteroid;
		case "SETVS":
			return comet;
		case "PRINTV":
			return earth;
		case "ONE":
			return blackhole;
		case "SFUN":
			return sun;
		case "EFUN":
			return sun_end;
		case "FUNCL":
			return sun_run;
		case "LSTRT":
			return quantum;
		case "LEND":
			return quantum_end;
		default:
			return null;
		}
			
	}
}
