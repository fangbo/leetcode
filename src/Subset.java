import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Subset {
	
	class Node {
		
		private int i;
		
		private int[] num;
		
		private Node trueChild;
		
		private Node falseChild;
		
		private boolean isTrue = false;
		
		public Node(boolean isTrue, int i, int[] num) {
			this.isTrue = isTrue;
			this.i = i;
			this.num = num;
		}

		public void computeChild() {
			if (this.i == num.length - 1) {
				return;
			}
			
			this.trueChild = new Node(true, this.i + 1, num);
			this.falseChild = new Node(false, this.i + 1, num);
			
			this.trueChild.computeChild();
			this.falseChild.computeChild();
		}
		
		public String toString() {
			return String.valueOf(num[i]) + "  " + this.isTrue;
		}
		
	}
	
	class SubSet {
		
		private int[] num;
		
		private int[] values;
		
		public SubSet(List<Node> paths) {
			
		}
	}
	
    public List<List<Integer>> subsetsWithDup(int[] num) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (num == null || num.length == 0) {
    		result.add(new ArrayList<Integer>());
    		return result;
    	}
    	
    	Node root = new Node(false, 0, num);
    	root.computeChild();
    	List<List<Node>> paths = new ArrayList<List<Node>>();
    	paths.addAll(paths(root, new ArrayList<Node>()));
    	
    	root = new Node(true, 0, num);
    	root.computeChild();
    	paths.addAll(paths(root, new ArrayList<Node>()));

    	Set<String> resultStr = new HashSet<String>();
    	for (List<Node> path : paths) {
    		List<Integer> a = new ArrayList<Integer>();
    		for (Node n : path) {
    			if (n.isTrue) {
    				a.add(num[n.i]);
    			}
    		}
    		Collections.sort(a);
    		StringBuilder builder = new StringBuilder();
    		for (int i = 0; i < a.size(); i++) {
    			builder.append(a.get(i));
    			if (i < a.size() - 1) {
    				builder.append(",");
    			}
    		}
    		resultStr.add(builder.toString());
    	}
    	
    	System.out.println(resultStr);
    	for (String value : resultStr) {
    		if (value.length() == 0) {
    			result.add(new ArrayList<Integer>());
    		} else {
    			List<Integer> a = new ArrayList<Integer>();
    			for (String i : value.split(",")) {
    				a.add(Integer.valueOf(i));
    			}
    			Collections.sort(a);
    			result.add(a);
    		}
    	}
    	
    	Collections.sort(result, new Comparator<List<Integer>>(){

			public int compare(List<Integer> o1, List<Integer> o2) {
				if (o1.size() == 0) {
					return -1;
				}
				if (o2.size() == 0) {
					return 1;
				}
				int len = Math.min(o1.size(), o2.size());
				for (int i = 0; i < len; i++) {
					if (o1.get(i) < o2.get(i)) {
						return -1;
					} else if (o1.get(i) > o2.get(i)) {
						return 1;
					}
				}
				if (o1.size() == o2.size()) {
					return 0;
				} else if (o1.size() < o2.size()) {
					return -1;
				} else {
					return 1;
				}
				
			}
    		
    	});
    	return result;
    }
    
    private List<List<Node>> paths(Node node, List<Node> parent) {
		List<Node> newParent = new ArrayList<Node>(parent);
		newParent.add(node);
		
		List<List<Node>> result = new ArrayList<List<Node>>();
		
		if (node.trueChild == null && node.falseChild == null) {
			result.add(newParent);
		} else {
			if (node.trueChild != null) {
				result.addAll(paths(node.trueChild, newParent));
			}
			if (node.falseChild != null) {
				result.addAll(paths(node.falseChild, newParent));
			}
		}
		
		return result;
		
	}

	public static void main(String[] args) {
		int[] a = new int[]{4,4,4,1,4};

		System.out.println(new Subset().subsetsWithDup(a));
	}
}
