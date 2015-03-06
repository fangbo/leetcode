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
public class BSTRecover {
    public void recoverTree(TreeNode root) {
    	if (root == null) {
    		return;
    	}
        List<TreeNode> order = new ArrayList<TreeNode>();
        inorder(order, root);
        
        TreeNode first = null;
        TreeNode second = null;
        for (int i = 0; i <= order.size() - 2; i++) {
        	TreeNode cur = order.get(i);
        	TreeNode next = order.get(i + 1);
        	if (cur.val > next.val) {
        		if (first == null) {
        			first = cur;
        			second = next;
        		} else {
        			second = next;
        		}
        	}
        }
        swap(first, second);
    }
    
    private void swap(TreeNode first, TreeNode second) {
    	int val = first.val;
    	first.val = second.val;
    	second.val = val;
    }
    
    private void inorder(List<TreeNode> order, TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	inorder(order, root.left);
    	order.add(root);
    	inorder(order, root.right);
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(3);
    	
    	new BSTRecover().recoverTree(root);
    	
    	System.out.println(root);
    }
}