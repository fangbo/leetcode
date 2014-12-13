public class ValidPalindrome {
	
    public boolean isPalindrome(String s) {
    	if (s == null || s.isEmpty()) {
    		return true;
    	}
    	
    	boolean isPalindrome = false;
    	
    	int i = 0;
    	int j = s.length() - 1;
    	
    	while (i <= j) {
    		char ich = s.charAt(i);
    		
    		while (!Character.isLetter(ich) && !Character.isDigit(ich)) {
    			i++;
    			if (i > s.length() - 1) {
    				break;
    			}
    			ich = s.charAt(i);
    		}
    		
    		char jch = s.charAt(j);
    		while (!Character.isLetter(jch) && !Character.isDigit(jch)) {
    			j--;
    			if (j < 0) {
    				break;
    			}
    			jch = s.charAt(j);
    		}
    		
    		
    		
    		if (i <= s.length() - 1 && j >= 0) {
    			if (Character.toLowerCase(ich) != Character.toLowerCase(jch)) {
        			isPalindrome = false;
        			break;
        		}
    		}
    		
    		i++;
    		j--;
    		
    		if (i >= j) {
    			isPalindrome = true;
    			break;
    		}
    	}
    	
    	return isPalindrome;
    }
    
    public static void main(String[] args) {
    	System.out.println(new ValidPalindrome().isPalindrome(""));
    	System.out.println(new ValidPalindrome().isPalindrome("a"));
    	System.out.println(new ValidPalindrome().isPalindrome(null));
    	System.out.println(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
    	System.out.println(new ValidPalindrome().isPalindrome("race a car"));
    	System.out.println(new ValidPalindrome().isPalindrome(".,"));
    	System.out.println(new ValidPalindrome().isPalindrome("aa"));
    	System.out.println(new ValidPalindrome().isPalindrome("aaa"));
    	System.out.println(new ValidPalindrome().isPalindrome("1a2"));
    }
}