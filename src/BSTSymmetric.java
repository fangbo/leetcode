
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BSTSymmetric {
	
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) {
    		return true;
    	}

//    	exchangeChildren(root.left);
    	exchangeChildren(root.right);
    	
    	return isSameTree(root.left, root.right);
    }
    
    private void exchangeChildren(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	TreeNode left = root.left;
    	TreeNode right = root.right;
    	root.left = right;
    	root.right = left;
    	exchangeChildren(root.left);
    	exchangeChildren(root.right);
    }
    
    public boolean isSameTree(TreeNode node1, TreeNode node2) {
    	if (node1 == null) {
    		if (node2 == null) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	if (node2 == null) {
    		if (node1 == null) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	if (node1.val != node2.val) {
    		return false;
    	}
    	
    	return isSameTree(node1.left,node2.left) && isSameTree(node1.right, node2.right);
    }
    
    public static void main(String[] args) {
//    	int[] preorder = new int[]{4, 2, 1, 3, 5, 6};
//    	int[] inorder = new int[]{1, 2, 3, 4, 5, 6};
//    	int[] postorder = new int[]{1, 3, 2, 6, 5, 4};
//
//    	TreeNode root = new BuildTreeFromPreorderPostorder().buildTreeForPreorder(preorder, inorder);
//    	
//    	TreeNode newRoot = new BuildTreeFromPreorderPostorder().buildTree(inorder, postorder);
//    	
//    	System.out.println(new Solution().isSameTree(root, newRoot));
    }
}