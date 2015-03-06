import java.util.ArrayList;
import java.util.List;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BSTValidate {
    public boolean isValidBST(TreeNode root) {
    	return isValidateBST(root, new ArrayList<TreeNode>(), new ArrayList<TreeNode>());
    }
    
    private boolean isValidateBST(TreeNode root, List<TreeNode> lessParents, List<TreeNode> greaterParents) {
    	if (root == null) {
    		return true;
    	}
    	
    	if (root.left != null) {
    		if (root.left.val >= root.val) {
    			return false;
    		}
    	}
    	
    	if (root.right != null) {
    		if (root.right.val <= root.val) {
    			return false;
    		}
    	}
    	
    	for (TreeNode t : lessParents) {
    		if (root.val <= t.val) {
    			return false;
    		}
    	}
    	for (TreeNode t : greaterParents) {
    		if (root.val >= t.val) {
    			return false;
    		}
    	}
    	
    	List<TreeNode> newLessParents = new ArrayList<TreeNode>(lessParents);
    	newLessParents.add(root);
    	List<TreeNode> newGreaterParents = new ArrayList<TreeNode>(greaterParents);
    	newGreaterParents.add(root);
    	
    	return isValidateBST(root.left, lessParents, newGreaterParents) &&
    			isValidateBST(root.right, newLessParents, greaterParents);
    	
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(10);
    	root.left = new TreeNode(5);
    	root.left.right = new TreeNode(19);
    	root.right = new TreeNode(15);
    	root.right.left = new TreeNode(11);
    	
    	System.out.println(new BSTValidate().isValidBST(root));
    }
}