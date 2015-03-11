/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode righ
 *     	t;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BuildTreeFromPreorderPostorder {
    public TreeNode buildTreeForPreorder(int[] preorder, int[] inorder) {
        if (preorder == null) {
        	return null;
        }
        
        return buildTreeForPreorder(preorder, 0, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeForPreorder(int[] preorder, int preIndex,
    						   int[] inorder,
    						   int startIndex, int endIndex) {
    	if (startIndex == endIndex) {
    		return new TreeNode(preorder[preIndex]);
    	}
    	if (startIndex > endIndex) {
    		return null;
    	}
    	
    	int rootIndex = startIndex;
    	for (;rootIndex <= endIndex; rootIndex++) {
    		if (preorder[preIndex] == inorder[rootIndex]) {
    			// now, we get the new root
    			break;
    		}
    	}
    	
    	TreeNode root = new TreeNode(inorder[rootIndex]);
    	
    	root.left = buildTreeForPreorder(preorder, preIndex + 1, 
    			inorder, startIndex, rootIndex - 1);
    	
    	root.right = buildTreeForPreorder(preorder, preIndex + (rootIndex - startIndex) + 1, 
    			inorder, rootIndex + 1, endIndex);
    	
    	return root;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
        	return null;
        }
        
        return buildTreeForPostorder(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }
    
    private TreeNode buildTreeForPostorder(int[] inorder,
    				int startIndex, int endIndex,
    				int[] postorder, int postIndex) {
    	if (startIndex == endIndex) {
    		return new TreeNode(postorder[postIndex]);
    	}
    	if (startIndex > endIndex) {
    		return null;
    	}
    	
    	int rootIndex = endIndex;
    	for (; rootIndex >= startIndex; rootIndex--) {
    		if (inorder[rootIndex] == postorder[postIndex]) {
    			break;
    		}
    	}
    	
    	TreeNode root = new TreeNode(inorder[rootIndex]);
    	
    	root.right = buildTreeForPostorder(inorder, rootIndex + 1, endIndex,
    			postorder, postIndex - 1);
    	
    	root.left = buildTreeForPostorder(inorder, startIndex , rootIndex - 1, 
    			postorder, postIndex - (endIndex - rootIndex) - 1);
    	
    	return root;
    }
    
    private static void prePrint(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	System.out.println(root.val);
    	prePrint(root.left);
    	prePrint(root.right);
    }
    
    private static void inPrint(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	inPrint(root.left);
    	System.out.println(root.val);
    	inPrint(root.right);
    }
    
    public static void main(String[] args) {
    	int[] preorder = new int[]{4, 2, 1, 3, 5, 6};
    	int[] inorder = new int[]{1, 2, 3, 4, 5, 6};
    	int[] postorder = new int[]{1, 3, 2, 6, 5, 4};

    	TreeNode root = new BuildTreeFromPreorderPostorder().buildTreeForPreorder(preorder, inorder);
    	System.out.println("========preprint========");
    	prePrint(root);
    	System.out.println("========inprint========");
    	inPrint(root);
    	
    	root = new BuildTreeFromPreorderPostorder().buildTree(inorder, postorder);
    	System.out.println("========preprint========");
    	prePrint(root);
    	System.out.println("========inprint=========");
    	inPrint(root);
    }
}