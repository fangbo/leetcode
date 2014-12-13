import java.util.ArrayList;
import java.util.List;


public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (numRows == 0) {
    		return result;
    	}
    	
    	List<Integer> l1 = new ArrayList<Integer>();
    	l1.add(1);
    	result.add(l1);
    	
    	for (int i = 1; i < numRows; i++) {
    		List<Integer> l = new ArrayList<Integer>();
    		result.add(l);
    		
    		List<Integer> lastLevel = result.get(i - 1);
    		l.add(1);
    		for (int j = 0; j < lastLevel.size() - 1; j++) {
    			l.add(lastLevel.get(j) + lastLevel.get(j + 1));
    		}
    		l.add(1);
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	System.out.println(new PascalTriangle().generate(5));
    }
}
