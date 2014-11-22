import java.util.LinkedList;
import java.util.List;


public class ReverseSentence {

	private static final char SPACE = ' ';
	
	private List<String> buildWords(String s) {
		List<String> words = new LinkedList<String>();
		
		StringBuilder word = new StringBuilder();
		boolean inWord = false;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == SPACE) {
				if (inWord) {
					words.add(word.toString());
					inWord = false;
					word = new StringBuilder();
				} else {
					continue;
				}
			} else {
				word.append(c);
				inWord = true;
			}
		}
		
		if (inWord) {
			words.add(word.toString());
		}
		
		return words;
	}
	
	public String reverseWords(String s) {
		List<String> words = buildWords(s);
		StringBuilder reverse = new StringBuilder();
		for (int i = words.size() - 1; i >= 0; i--) {
			reverse.append(words.get(i));
			if (i != 0) {
				reverse.append(" ");
			}
			
		}
		return reverse.toString();
    }
	
	public static void main(String[] args) {
		new ReverseSentence().reverseWords(" i am a");
		new ReverseSentence().reverseWords("i a-m  a ");
		
	}
}
