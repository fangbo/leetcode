import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Triangle {

	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.isEmpty()) {
			return 0;
		}
		
		int[] lastMin = new int[1];
		lastMin[0] = triangle.get(0).get(0);
		
		for (int i = 1; i < triangle.size(); i++) {
			
			List<Integer> values = triangle.get(i);
			
			int[] min = new int[values.size()];
			
			for (int j = 0; j < values.size(); j++) {
				int value = values.get(j);
				if (j == 0) {
					min[j] = lastMin[j] + value;
					
				} else if (j == values.size() - 1) {
					min[j] = lastMin[j - 1] + value;
				} else {
					min[j] = Math.min(lastMin[j - 1] + value, 
							          lastMin[j] + value);
				}
			}
			lastMin = min;
		}
		
		int min = Integer.MAX_VALUE;
		for (int v : lastMin) {
			if (min > v) {
				min = v;
			}
		}
		return min;
	}
	
}
