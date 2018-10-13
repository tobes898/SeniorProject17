package lexer;

import java.util.HashMap;

public class SymbolTable {
	
	HashMap<Integer, ID> st = new HashMap<Integer, ID>();
	
	
	public void insert(int numID, ID item){
		st.put(numID, item);
	}
	
	
	public ID lookup(int numID){
		return st.get(numID);
	}
	
	public int getSize(){
		return st.size();
	}
	
}
