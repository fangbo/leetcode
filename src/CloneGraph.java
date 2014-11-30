import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
        	return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> oldToNew = 
        		new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        
        Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
    	stack.push(node);
    	
    	while (!stack.isEmpty()) {
    		UndirectedGraphNode p = stack.pop();
    		if (!oldToNew.containsKey(p)) {
    			UndirectedGraphNode newP = new UndirectedGraphNode(p.label);
    			oldToNew.put(p, newP);
    		}
    		
    		for (UndirectedGraphNode neighbor : p.neighbors) {
    			if (!oldToNew.containsKey(neighbor)) {
    				stack.push(neighbor);
    			}
    			
    		}
    	}
    	
    	stack.push(node);
    	Set<UndirectedGraphNode> iterated = new HashSet<UndirectedGraphNode>();
    	while (!stack.isEmpty()) {
    		UndirectedGraphNode old = stack.pop();
    		if (iterated.contains(old)) {
    			continue;
    		}
    		iterated.add(old);
    		UndirectedGraphNode newNode = oldToNew.get(old);
    		
    		for (UndirectedGraphNode neighbor : old.neighbors) {
				newNode.neighbors.add(oldToNew.get(neighbor));
    			
    			stack.push(neighbor);
    		}
    	}
        return oldToNew.get(node);
    }
}