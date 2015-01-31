import java.util.ArrayList;
import java.util.List;


public class MinDepthBinaryTree {

	
    public int minDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	List<List<TreeNode>> paths = subPaths(root);
    	int min = Integer.MAX_VALUE;
    	for (List<TreeNode> l : paths) {
    		if (min > l.size()) {
    			min = l.size();
    		}
    	}
    	return min;
    }
    
    private List<List<TreeNode>> subPaths(TreeNode node) {
    	
    	List<List<TreeNode>> subPaths = new ArrayList<List<TreeNode>>();
    	
    	if (node.left == null && node.right == null) {
    		List<TreeNode> paths = new ArrayList<TreeNode>();
    		paths.add(node);
    		subPaths.add(paths);
    		return subPaths;
    	}

    	List<List<TreeNode>> leftSubPaths = null;
    	List<List<TreeNode>> rightSubPaths = null;
    	
    	if (node.left != null && node.right != null) {
    		leftSubPaths = subPaths(node.left);
    		rightSubPaths = subPaths(node.right);
    		
    	} else if (node.left != null) {
    		leftSubPaths = subPaths(node.left);
    		
    	} else if (node.right != null) {
    		rightSubPaths = subPaths(node.right);
    	}
    	
    	if (leftSubPaths != null) {
    		for (List<TreeNode> l : leftSubPaths) {
        		l.add(0, node);
        	}
    		subPaths.addAll(leftSubPaths);
    	}
    	if (rightSubPaths != null) {
    		for (List<TreeNode> l : rightSubPaths) {
        		l.add(0, node);
        	}
    		subPaths.addAll(rightSubPaths);
    	}
    	
    	return subPaths;
    }
}
