import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestoreIPAddress {
 
	class Node {
		
		private String value;
		
		private int order = 0;
		
		private String subvalue;
		
		public Node(int order, String value, String subvalue) {
			this.subvalue = subvalue;
			this.order = order;
			this.value = value;
		}
		
		private List<Node> children = new ArrayList<Node>();
		
		public void computeChildren() {
			if (this.order == 4) {
				return;
			}
			String childValue = null;
			String childSub = null;
			
			// get first one
			if (this.subvalue.length() >= 1) {
				childValue = this.subvalue.substring(0, 1);
				childSub = this.subvalue.substring(1);
				this.children.add(new Node(this.order + 1, childValue, childSub));
			}
			// get first two
			if (this.subvalue.length() >= 2) {
				childValue = this.subvalue.substring(0, 2);
				childSub = this.subvalue.substring(2);
				if (!childValue.startsWith("0")) {
					this.children.add(new Node(this.order + 1, childValue, childSub));
				}
			}
	
			// get first three
			if (this.subvalue.length() >= 3) {
				childValue = this.subvalue.substring(0, 3);
				if (!childValue.startsWith("0")) {
					if (255 >= Integer.valueOf(childValue)) {
						childSub = this.subvalue.substring(3);
						this.children.add(new Node(this.order + 1, childValue, childSub));
					}
				}
				
			}
			
			for (Node child : this.children) {
				child.computeChildren();
			}
		}
	}
	
	public List<String> restoreIpAddresses(String s) {
		if (s == null || s.isEmpty()) {
			return new ArrayList<String>();
		}
        Node root = new Node(0, null, s);
        root.computeChildren();
        List<List<Node>> paths = paths(root, new ArrayList<Node>());
        
        Set<String> result = new HashSet<String>();
        for (List<Node> path : paths) {
        	if (path.isEmpty() || path.size() < 5) {
        		continue;
        	}
        	
        	if (path.get(4).subvalue.length() > 0) {
        		continue;
        	}

        	StringBuilder builder = new StringBuilder();
        	for (int i = 1; i < path.size(); i++) {
        		builder.append(path.get(i).value);
        		builder.append(".");
        	}
        	String value = builder.toString();
        	result.add(value.substring(0, value.length() - 1));
        }
        return new ArrayList<String>(result);
        
    }
	
	private List<List<Node>> paths(Node node, List<Node> parent) {
		if (node.children.isEmpty()) {
			List<Node> newParent = new ArrayList<Node>();
			newParent.addAll(parent);
			newParent.add(node);
			List<List<Node>> result = new ArrayList<List<Node>>();
			result.add(newParent);
			return result;
		}
		
		List<Node> newParent = new ArrayList<Node>(parent);
		newParent.add(node);
		
		List<List<Node>> result = new ArrayList<List<Node>>();
		for (Node child : node.children) {
			result.addAll(paths(child, newParent));
		}
		
		return result;
		
	}
	
	public static void main(String[] args) {
		System.out.println("a".substring(1));
		System.out.println(new RestoreIPAddress().restoreIpAddresses("25525511135"));
		System.out.println(new RestoreIPAddress().restoreIpAddresses("1000"));
		System.out.println(new RestoreIPAddress().restoreIpAddresses("010010"));
	}
}