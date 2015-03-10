public class InterleaveString {
    public boolean isInterleave(String s1, String s2, String s3) {
 
    	if (s3.length() != (s1.length() + s2.length())) {
    		return false;
    	}
    	
    	int maxLength = Math.max(s1.length(), s2.length()) + 1;
    	boolean[][] interLeaves = new boolean[maxLength][maxLength];
    	
    	interLeaves[0][0] = true;
    	
    	for (int i = 1; i <= s1.length(); i++) {
    		interLeaves[i][0] = interLeaves[i-1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
    	}
    	
    	for (int i = 1; i <= s2.length(); i++) {
    		interLeaves[0][i] = interLeaves[0][i-1] && s2.charAt(i - 1) == s3.charAt(i - 1);
    	}
    	
    	
    	for (int i = 1; i <= s1.length(); i++) {
    		for (int j = 1; j <= s2.length(); j++) {
    			interLeaves[i][j] = (interLeaves[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
    					(interLeaves[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
    		}
    	}
    	
    	return interLeaves[s1.length()][s2.length()];
    }
    
    public static void main(String[] args) {
    	System.out.println(new InterleaveString().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    	System.out.println(new InterleaveString().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}