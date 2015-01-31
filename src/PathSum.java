import java.util.ArrayList;
import java.util.Iterator;
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
public class PathSum {
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> resultPaths = new ArrayList<List<Integer>>();
    	if (root == null) {
    		return resultPaths;
    	}
    	
    	List<List<TreeNode>> paths = subPaths(root);

    	Iterator<List<TreeNode>> itr = paths.iterator();
    	while (itr.hasNext()) {
    		List<TreeNode> pathNodes = itr.next();
    		if (sum(pathNodes) == sum) {	
    			resultPaths.add(nodeValues(pathNodes));
    		}
    	}
    	return resultPaths;
    }
    
    private List<Integer> nodeValues(List<TreeNode> nodes) {
    	List<Integer> list = new ArrayList<Integer>();
    	for (TreeNode node : nodes) {
    		list.add(node.val);
    	}
    	
    	return list;
    }
    
    private int sum(List<TreeNode> nodes) {
    	int sum = 0;
    	for (TreeNode node : nodes) {
    		sum += node.val;
    	}
    	return sum;
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
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
    	root.left = new TreeNode(4);
    	root.right = new TreeNode(8);
    	root.left.left = new TreeNode(11);
    	root.left.left.left = new TreeNode(7);
    	root.left.left.right = new TreeNode(2);
    	root.right.left = new TreeNode(13);
    	root.right.right = new TreeNode(4);
    	root.right.right.right = new TreeNode(1);
    	
    	System.out.println(new PathSum().pathSum(root, 22));
    	
    }
    
}