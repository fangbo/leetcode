
public class DistinctSubsequence {
	
	
    public int numDistinct(String S, String T) {
    	if (S == null || T == null || S.isEmpty() || T.isEmpty() || S.length() < T.length()) {
    		return 0;
    	}
    	
    	int[][] nums = new int[S.length()][T.length()];
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = 0; j < nums[i].length; j++) {
    			nums[i][j] = 0;
    		}
    	}
    	
    	if (S.charAt(S.length() - 1) == T.charAt(T.length() - 1)) {
    		nums[S.length() - 1][T.length() - 1] = 1;
    	}
    	
    	int j = T.length() - 1;
    	for (int i = S.length() - 2; i >= 0; i--) {
    		if (S.charAt(i) == T.charAt(j)) {
    			nums[i][j] = nums[i+1][j] + 1;
    		} else {
    			nums[i][j] = nums[i+1][j];
    		}
    		
    	}
    	
    	for (j = T.length() - 2; j >= 0; j--) {
    		for (int i = S.length() - (T.length() - j); i >= 0; i--) {
    			if (S.charAt(i) == T.charAt(j)) {
    				nums[i][j] = nums[i+1][j+1] + nums[i+1][j];
    			} else {
    				nums[i][j] = nums[i+1][j];
    			}
    			
    		}
    	}
        return nums[0][0];
    }
    
    
    public static void main(String[] args) {
    	System.out.println(new DistinctSubsequence().numDistinct("ab", "a"));
    	System.out.println(new DistinctSubsequence().numDistinct("rabbbit", "rabbit"));
    }
}