import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class WordsHolder {
	
	public List<String> words = new LinkedList<String>();
	
	public WordsHolder addWords(List<String> word) {
		words.addAll(word);
		return this;
	}
	
	public boolean hasWords() {
		return !words.isEmpty();
	}
	
	public String toString() {
		return words.toString();
	}
}

public class WordBreak {
	
	private int maxLength = Integer.MIN_VALUE;
	
	Map<String, Boolean> values = new HashMap<String, Boolean>();
	
    public boolean checkWordBreak(String s, Set<String> dict) {
    	for (String d : dict) {
    		if (maxLength < d.length()) {
    			maxLength = d.length();
    		}
    	}
    	
        return recursiveCheck(s, dict);
    }
    
    private boolean recursiveCheck(String s, Set<String> dict) {
    	if (values.containsKey(s)) {
    		return values.get(s);
    	}
    	
        StringBuilder word = new StringBuilder();
        boolean result = false;
        for (int i = 0; i < s.length(); i++) {
        	word.append(s.charAt(i));
        	
        	if (dict.contains(word.toString())) {
        		if (i == s.length() - 1) {
        			result = true;
        		} else {
        			String substring = s.substring(i+1, s.length());
            		
            		if (values.containsKey(substring)) {
            			result = values.get(substring);
            		} else {
            			result = recursiveCheck(substring, dict);
            		}
        		}
        		
        		if (result) {
        			values.put(s, result);
        			return result;
        		}
        	}
        	
        	if (i >= maxLength) {
        		result = false;
        		break;
        	}
        }
        values.put(s, result);
        return result;
    }
    
    private static String listToString(List<String> list) {
    	StringBuilder builder = new StringBuilder();
    	for (String s : list) {
    		builder.append(s).append(" ");
    	}
    	return builder.toString().substring(0, builder.length() - 1);
    }
    
    Map<String, List<WordsHolder>> words = new HashMap<String, List<WordsHolder>>();
    
    
    
    public List<String> wordBreak(String s, Set<String> dict) {
    	
    	for (String d : dict) {
    		if (maxLength < d.length()) {
    			maxLength = d.length();
    		}
    	}
    	
    	List<String> result = new LinkedList<String>();

    	List<WordsHolder> holders = recursiveBreak(s, dict);
    	for (WordsHolder h : holders) {
    		result.add(listToString(h.words));
    	}
        
    	return result;
    }
    
    public List<WordsHolder> recursiveBreak(String s, Set<String> dict) {
    	if (values.containsKey(s)) {
    		return words.get(s);
    	}
    	
        StringBuilder word = new StringBuilder();
        List<WordsHolder> myWords = new ArrayList<WordsHolder>();
        
        for (int i = 0; i < s.length(); i++) {
        	word.append(s.charAt(i));
        	
        	if (dict.contains(word.toString())) {
        		List<WordsHolder> subHolders = new LinkedList<WordsHolder>();
        		
        		if (i == s.length() - 1) {
        			
        			subHolders.add(new WordsHolder().addWords(Arrays.asList(s)));
        			
        			
        		} else {
        			String substring = s.substring(i+1, s.length());
            		
            		if (words.containsKey(substring)) {
            			subHolders = words.get(substring);
            			
            		} else {
            			subHolders = recursiveBreak(substring, dict);
            		}
            		List<WordsHolder> newHolders = new LinkedList<WordsHolder>();
            		for (WordsHolder holder : subHolders) {
            			newHolders.add(new WordsHolder().addWords(holder.words));
            		}
            		subHolders = newHolders;
            		for (WordsHolder holder : subHolders) {
            			holder.words.add(0, word.toString());
            		}
            		
            		
        		}
        		
        		myWords.addAll(subHolders);
        	}
        	
        	if (i >= maxLength) {
        		break;
        	}
        }
        words.put(s, myWords);
    	return myWords;
    }
    
    public static void main(String[] args) {
    	Set<String> set = new HashSet<String>(Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"
}));
//    	System.out.println("123".substring(3, 3).length());
    	System.out.println((new WordBreak()).wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", set));
    	System.out.println((new WordBreak()).wordBreak("ab", new HashSet<String>(Arrays.asList(new String[]{"a","b"}))));
    	System.out.println((new WordBreak()).wordBreak("abcd", new HashSet<String>(Arrays.asList(new String[]{"a","abc","b","cd"}))));
    	System.out.println((new WordBreak()).wordBreak("catsanddog", new HashSet<String>(Arrays.asList(new String[]{"cat","cats","and","sand","dog"}))));
    }
}