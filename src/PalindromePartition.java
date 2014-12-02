import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PalindromePartition {
	
	
	private Map<String, List<List<String>>> partitions = new HashMap<String, List<List<String>>>();
	
	private static boolean isPost(String s) {
		if (s.length() == 1) {
			return true;
		}
		int mid = s.length() / 2;
		for (int i = 0; i <= mid; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		
		return true;
	}
	
    public List<List<String>> partition(String s) {
    	partitions = new HashMap<String, List<List<String>>>();
    	if (s == null) {
    		return null;
    	}
        return subpartition(s);
    }
    
    private List<List<String>> subpartition(String s) {
    	List<List<String>> partition = partitions.get(s);
    	if (partition != null) {
    		return partition;
    	}
    	
    	partition = new ArrayList<List<String>>();
    	if (s.isEmpty()) {
    		partitions.put(s, partition);
    		return partition;
    	}
    	
    	StringBuilder pre = new StringBuilder();
    	for (int i = 0; i < s.length(); i++) {
    		pre.append(s.charAt(i));
    		
    		if (isPost(pre.toString())) {
    			System.out.println("s:" + s + ",pre:" + pre);
    			List<List<String>> subPartitions = new ArrayList<List<String>>();
    			if (i == s.length() - 1) {
    				
    				List<String> l = new ArrayList<String>();
    				l.add(pre.toString());
    				partition.add(l);

    			} else {
    				List<List<String>> subs = subpartition(s.substring(i + 1, s.length()));
    				
    				for (List<String> l : subs) {
    					subPartitions.add(new ArrayList<String>(l));
    				}
    				
    				if (!subPartitions.isEmpty()) {
    					
    					for (List<String> list : subPartitions) {
            				list.add(0, pre.toString());
            			}
        				
        				partition.addAll(subPartitions);
    				}
    			}
    		}
    	}
    	partitions.put(s, partition);
    	return partition;
    }
    
    public int minCut2(String s) {
    	min = new HashMap<String, Integer>();
    	return subMinCut(s);
    }
    
    private Map<String, Integer> min = new HashMap<String, Integer>();
    
    public int subMinCut(String s) {
    	if (min.containsKey(s)) {
    		return min.get(s).intValue();
    	}
    	
    	if (s.length() == 0) {
    		min.put(s, 0);
    		return 0;
    	}
    	
    	if (isPost(s)) {
    		min.put(s, 0);
    		return 0;
    	}
    	
    	StringBuilder pre = new StringBuilder();
    	int curMin = Integer.MAX_VALUE;
    	for (int i = 0; i < s.length(); i++) {
    		pre.append(s.charAt(i));
    		if (isPost(pre.toString())) {
    			String subStr = s.substring(i + 1, s.length());
    			if (isPost(subStr)) {
    				curMin = 0;
    				break;
    			}
    			
    			int subMin = subMinCut(subStr);
    			if (subMin != Integer.MAX_VALUE) {
    				if (curMin > subMin) {
    					curMin = subMin;
    				}
    			}
    			
    		}
    	}

    	if (curMin != Integer.MAX_VALUE) {
    		curMin += 1;
    	}
    	
    	min.put(s, curMin);
    	
    	return curMin;
    }
    
    public int minCut(String s) {
    	if (s == null || s.length() <= 1) {
    		return 0;
    	}
    	
    	int[] minCuts = new int[s.length()];
    	boolean[][] pal = new boolean[s.length()][s.length()];
    	
    	for (int i = s.length() - 1; i >= 0; i--) {
    		minCuts[i] = s.length() - 1 - i;
    		for (int j = i; j < s.length(); j++) {
    			
    			if (j == i) {
    				pal[i][j] = true;
    			} else {
    				if (s.charAt(j) != s.charAt(i)) {
    					continue;
    				}
    				if (j - i >= 2) {
    					pal[i][j] = pal[i + 1][j - 1];
    				} else {
    					pal[i][j] = true;
    				}
    				
    			}
    			if (pal[i][j]) {
    				
    				// i to j is palindrome
    				if (j == s.length() - 1) {
    					minCuts[i] = 0;
    				} else {
    					minCuts[i] = Math.min(minCuts[j + 1] + 1, minCuts[i]);
    				}
    			}
    		}
    	}
    	return minCuts[0];
    }
    
    public static void main(String[] args) {
    	System.out.println(isPost("a"));
    	System.out.println(isPost("aa"));
    	System.out.println(isPost("aba"));
    	System.out.println(isPost("abba"));
    	System.out.println(isPost("abca"));
    	System.out.println(isPost("abbc"));
    	
    	System.out.println(new PalindromePartition().partition("abbc"));
    	System.out.println(new PalindromePartition().partition("abc"));
    	System.out.println(new PalindromePartition().partition("aba"));
    	System.out.println(new PalindromePartition().minCut("abc"));
    	System.out.println(new PalindromePartition().minCut("ab"));
    	System.out.println(new PalindromePartition().minCut("bb"));
    	System.out.println(new PalindromePartition().minCut("bbc"));
//    	System.out.println(new Solution().minCut("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    	
    }
}