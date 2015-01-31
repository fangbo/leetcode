import java.util.LinkedList;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class FlattenBinaryTree {
    public void flatten(TreeNode root) {
    	if (root == null) {
    		return;
    	}
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        putQueue(root, queue);
        
        TreeNode last = null;
        while(!queue.isEmpty()) {
        	TreeNode t = queue.poll();
        	if (last != null) {
        		last.left = null;
        		last.right = t;
        	}
        	last = t;
        }
    }
    
    private void putQueue(TreeNode root, LinkedList<TreeNode> queue) {
    	if (root == null) {
    		return;
    	}
    	
    	queue.add(root);
    	putQueue(root.left, queue);
    	putQueue(root.right, queue);
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(5);
    	root.left.left = new TreeNode(3);
    	root.left.right = new TreeNode(4);
    	root.right.right = new TreeNode(6);
    	new FlattenBinaryTree().flatten(root);
    	
    	System.out.println(root);
    }
}