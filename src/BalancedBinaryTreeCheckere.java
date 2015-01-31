import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BalancedBinaryTreeCheckere {
	
	Map<TreeNode, Integer> depths = new HashMap<TreeNode, Integer>();
	
    public boolean isBalanced(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	
    	depth(root);
    	
    	LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	while (!queue.isEmpty()) {
    		TreeNode t = queue.poll();
    		if (!check(t)) {
    			return false;
    		}
    		if (t.left != null) {
    			queue.add(t.left);
    		}
    		if (t.right != null) {
    			queue.add(t.right);
    		}
    	}
    	
    	return true;
    }
    
    private boolean check(TreeNode node) {
    	if (node == null) {
    		return true;
    	}
    	int leftDepth = node.left == null ? 0 : depths.get(node.left);
    	int rightDepth = node.right == null ? 0 : depths.get(node.right);
    	
    	
    	if (Math.abs(leftDepth - rightDepth) > 1) {
    		return false;
    	}
    	
    	return true;
    }
    
    private int depth(TreeNode node) {
    	if (node == null) {
    		return 0;
    	}
    	
    	int leftDepth = depth(node.left);
    	int rightDepth = depth(node.right);
    	
    	int depth = Math.max(leftDepth, rightDepth) + 1;
    	depths.put(node, depth);
    	
    	return depth;
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
    	root.right = new TreeNode(8);
    	root.right.right = new TreeNode(4);
    	
    	System.out.println(new BalancedBinaryTreeCheckere().isBalanced(root));
    	
    }
    
    
}