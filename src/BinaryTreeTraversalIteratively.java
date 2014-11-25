import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { 
		val = x;
	}
}

public class BinaryTreeTraversalIteratively {
	
    public List<Integer> postorderTraversal(TreeNode root) {
//    	List<Integer> result = new LinkedList<Integer>();
//    	postorder(root, result);
//    	return result;
//    	
    	return this.postorderTraversalIterate(root);
    }
    
    private void postorder(TreeNode root, List<Integer> result) {
    	if (root == null) {
    		return;
    	}
    	
    	postorder(root.left, result);
    	postorder(root.right, result);
    	result.add(root.val);
    }
	
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> result = new LinkedList<Integer>();

    	if (root == null) {
    		return result;
    	}
    	
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);
    	
    	while (!stack.isEmpty()) {
    		TreeNode node = stack.pop();
    		result.add(node.val);
    		
    		if (node.right != null) {
    			stack.push(node.right);
    		}
    		
    		if (node.left != null) {
    			stack.push(node.left);
    		}
    	}
    	
    	return result;
    }
    
    public List<Integer> postorderTraversalIterate(TreeNode root) {
    	List<Integer> result = new LinkedList<Integer>();

    	if (root == null) {
    		return result;
    	}
    	
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	Stack<TreeNode> rootNodes = new Stack<TreeNode>();
    	
    	rootNodes.push(root);
    	
    	if (root.left != null) {
    		stack.push(root.left);
    	}
    	
    	if (root.right != null) {
    		stack.push(root.right);
    	}
    	
    	while (!stack.isEmpty()) {
    		TreeNode node = stack.pop();
    		rootNodes.push(node);
    		if (node.left != null) {
    			stack.push(node.left);
    		}
    		if (node.right != null) {
    			stack.push(node.right);
    		}
    	}

    	while (!rootNodes.isEmpty()) {
    		result.add(rootNodes.pop().val);
    	}
    	
    	return result;
    }

    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(4);
    	root.right = new TreeNode(3);
    	root.left.left = new TreeNode(2);
    	
    	System.out.println(new BinaryTreeTraversalIteratively().postorderTraversal(root));
    	
    	root = new TreeNode(2);
    	root.left = new TreeNode(3);
    	root.left.left = new TreeNode(1);
    	
    	System.out.println(new BinaryTreeTraversalIteratively().postorderTraversal(root));
    		
    	root = new TreeNode(2);
    	root.left = new TreeNode(1);
    	
    	System.out.println(new BinaryTreeTraversalIteratively().postorderTraversal(root));
    	
    	root = new TreeNode(1);
    	root.left = new TreeNode(5);
    	root.right = new TreeNode(4);
    	root.left.left = new TreeNode(2);
    	root.left.left.left = new TreeNode(3);
    	
    	System.out.println(new BinaryTreeTraversalIteratively().postorderTraversal(root));
    }
    
    
}