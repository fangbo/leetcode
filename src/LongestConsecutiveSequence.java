import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    
	Map<Integer, Integer> maxes = new HashMap<Integer, Integer>();
	
	Map<Integer, Integer> unionSet = new HashMap<Integer, Integer>();
	
	public int longestConsecutive(int[] num) {
    	for (int i : num) {
    		unionSet.put(i, i);
    		maxes.put(i, 1);
    	}
    	
    	for (int i : num) {
    		
    		Integer leftRoot = null;
    		if (i > Integer.MIN_VALUE) {
    			leftRoot = findSet(i - 1);
    		}
    		
    		Integer rightRoot = null;
    		if (i < Integer.MAX_VALUE) {
    			rightRoot = findSet(i + 1);
    		}
    		
    		if (leftRoot != null && rightRoot != null) {
    			
    			if (rightRoot.intValue() != leftRoot.intValue()) {
    				
    				unionSet.put(rightRoot, leftRoot);
    				if (leftRoot.intValue() != findSet(i).intValue()) {
    					maxes.put(leftRoot, maxes.get(leftRoot) + maxes.get(rightRoot) + 1);
    				} else {
    					maxes.put(leftRoot, maxes.get(leftRoot) + maxes.get(rightRoot));
    				}
        			unionSet.put(i, leftRoot);
        			
    			} else {
    				maxes.put(leftRoot, maxes.get(leftRoot));
    				unionSet.put(i, leftRoot);
    				
    			}
    			
    		} else {
    			
    			if (leftRoot != null && findSet(i).intValue() != leftRoot.intValue()) {
    				unionSet.put(i, leftRoot);
    				maxes.put(leftRoot, maxes.get(leftRoot) + 1);
    			}
    			
    			if (rightRoot != null && findSet(i).intValue() != rightRoot.intValue()) {
    				unionSet.put(i, rightRoot);
    				maxes.put(rightRoot, maxes.get(rightRoot) + 1);
    			}
    		}
    	}
    	
    	int max = 0;
    	for(Map.Entry<Integer, Integer> entry :maxes.entrySet()) {
    		if (max < entry.getValue()) {
    			max = entry.getValue();
    		}
    	}
    	
    	return max;
    }
	
	private void union(int x, int y) {
		Integer xRoot = findSet(x);
		Integer yRoot = findSet(y);
		int newMax = maxes.get(xRoot) + maxes.get(yRoot);
		unionSet.put(yRoot, xRoot);
		maxes.put(xRoot, newMax);
	}
	
	private Integer findSet(int x) {
		Integer root = unionSet.get(x);
		if (root == null) {
			return null;
		}
		if (root == x) {
			return x;
		}
		if (unionSet.get(x) == x) {
			return x;
		}
		Integer set = findSet(root);
		unionSet.put(x, set);
		return set;
	}
    
    public static void main(String[] args) {
    	System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{-299, 100, 4, 8, 1, 3, 2}));
    	System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{1, 2, 3, 6}));
    	System.out.println(Integer.MAX_VALUE);
    	System.out.println(Integer.MIN_VALUE);
    	System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645}));
    }
} 