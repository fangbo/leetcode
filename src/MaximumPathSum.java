
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class MaximumPathSum {
	
	int maxValue = Integer.MIN_VALUE;
	
    public int maxPathSum(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	if (root.left == null && root.right == null) {
    		return root.val;
    	}

    	maxPathDown(root);
    	return maxValue;
    }
    
    private int maxPathDown(TreeNode node) {
    	if (node == null) {
    		return 0;
    	}
    	int left = Math.max(0, maxPathDown(node.left));
    	int right = Math.max(0, maxPathDown(node.right));
    	maxValue = Math.max(maxValue, left + right + node.val);
    	return Math.max(left,  right) + node.val;
    }
    
}