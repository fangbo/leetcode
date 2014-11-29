import java.util.HashMap;
import java.util.Map;


class Counter {
	int value = 1;
}

public class SingleNumber {
    public int singleNumber(int[] A) {
    	Map<Integer, Counter> counters = new HashMap<Integer, Counter>();
    	
    	for (int i : A) {
    		Counter c = counters.get(i);
    		if (c == null) {
    			c = new Counter();
    			counters.put(i, c);
    		} else {
    			c.value++;
    		}
    		
    	}
    	int single = 0;
    	for (Map.Entry<Integer, Counter> item : counters.entrySet()) {
    		if (item.getValue().value < 3) {
    			single = item.getKey().intValue();
    			break;
    		}
    	}
    	return single;
    }
    
    public static void main(String[] args) {
    	System.out.println(new SingleNumber().singleNumber(new int[]{1,1,2,2,3,4,5,4,5}));
    }
    
    
}