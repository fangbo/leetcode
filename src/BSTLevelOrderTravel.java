import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BSTLevelOrderTravel {
	private TreeMap<Integer, List<Integer>> values = new TreeMap<Integer, List<Integer>>();
	
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	if (root == null) {
    		return new ArrayList<List<Integer>>();
    	}
    	
    	travel(root, 0);
    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>(values.values());
    	
    	for (int i = 0; i < result.size(); i++) {
    		if (i % 2 != 0) {
    			Collections.reverse(result.get(i));
    		}
    	}
    	
    	return result;
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
    	if (root == null) {
    		return new ArrayList<List<Integer>>();
    	}
    	
    	travel(root, 0);
    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>(values.values());
    	
    	return result;
    }
    
    private List<Integer> getValuesForLevel(int level) {
    	List<Integer> levelValues = values.get(level);
    	if (levelValues == null) {
    		levelValues = new ArrayList<Integer>();
    		values.put(level, levelValues);
    	}
    	
    	return levelValues;
    }
    
    private void travel(TreeNode root, int level) {
    	if (root == null) {
    		return;
    	}
    	getValuesForLevel(level).add(root.val);
    	
    	travel(root.left, level + 1);
    	travel(root.right, level + 1);
    }
}