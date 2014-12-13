import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class WordNode {
	
	String word;
	
	int hop = Integer.MAX_VALUE;
	
	Set<WordNode> neighbors = new HashSet<WordNode>();
	
	List<List<String>> paths = new ArrayList<List<String>>();
	
	public WordNode(String word) {
		this.word = word;
	}
	
	public void addNeighbor(WordNode word) {
		this.neighbors.add(word);
	}
	
	public int hashCode() {
		return this.word.hashCode();
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof WordNode)) {
			return false;
		}
		
		return ((WordNode)obj).word.equals(this.word);
	}
	
	public String toString() {
		return this.word;
	}
	
}


public class WordLadder {

	private boolean isNeighbor(String a, String b) {
		boolean hasOneDiff = false;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				if (hasOneDiff) {
					return false;
				}
				hasOneDiff = true;
			}
		}
		return hasOneDiff;
	}
	
	private Set<WordNode> nodes = new HashSet<WordNode>();
	
	
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		
		for (String d : dict) {
			nodes.add(new WordNode(d));
		}
		
		WordNode startNode = new WordNode(start);
		WordNode endNode = new WordNode(end);
		
		for (WordNode node : nodes) {
			if (node.word.equals(start)) {
				startNode = node;
			} else if (node.word.equals(end)) {
				endNode = node;
			}
		}

		nodes.add(startNode);
		nodes.add(endNode);

		Map<String, WordNode> unvisited = new HashMap<String, WordNode>();
		for (WordNode node : nodes) {
			unvisited.put(node.word, node);
		}

		
		Queue<WordNode> queue = new ArrayDeque<WordNode>();
		
		startNode.hop = 0;
		queue.add(startNode);
		unvisited.remove(startNode.word);
		startNode.paths.add(Arrays.asList(startNode.word));
		
		int minHop = Integer.MAX_VALUE;
		Set<WordNode> visited = new HashSet<WordNode>();
		while (!queue.isEmpty()) {
			
			WordNode node = queue.poll();
			if (visited.contains(node)) {
				continue;
			}
			if (node.hop > minHop) {
				continue;
			}
			
			unvisited.remove(node.word);
			visited.add(node);

			for (int i = 0; i < node.word.length(); i++) {
				StringBuilder builder = new StringBuilder(node.word);
				for (char ch = 'a'; ch <= 'z'; ch++) {
					builder.setCharAt(i, ch);
					
					String newWord = builder.toString();
					if (unvisited.containsKey(newWord)) {
						WordNode dnode = unvisited.get(newWord);

						if (dnode.hop <= node.hop) {
							continue;
						}
						
						dnode.hop = node.hop + 1;
			
						if (dnode.word.equals(end)) {
							minHop = dnode.hop;
						}
						
						for(List<String> l : node.paths) {
							List<String> tmp = new ArrayList<String>(l);
							tmp.add(dnode.word);
							dnode.paths.add(tmp);
						}
						
						queue.add(dnode);
					}
				}
			}

		}
		return endNode.paths;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(new WordLadder().findLadders("a", "c", new HashSet<String>(Arrays.asList(new String[]{"a", "b", "c"}))));
		BufferedReader reader = 
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(
										new File("/home/fangbo/leetcode/leetcode/src/data"))));
		String data = reader.readLine().replace("\"", "");
		long start = System.currentTimeMillis();
		System.out.println(new WordLadder().findLadders("charge", 
													  "comedo", 
													  (new HashSet<String>(
															  Arrays.asList(
																	  data.split(","))))
												      ));
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(new WordLadder().findLadders("hit", "cog", 
				new HashSet<String>(Arrays.asList(new String[]{"hot","dot","dog","lot","log"}))));
		
	}
}
