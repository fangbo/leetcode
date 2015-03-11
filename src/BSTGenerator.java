import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class BSTGenerator {
	
	private TreeNode copy(TreeNode root, int delta) {
		if (root == null) {
			return null;
		}
		
		TreeNode newRoot = new TreeNode(root.val + delta);
		
		newRoot.left = copy(root.left, delta);
		newRoot.right = copy(root.right, delta);
		
		return newRoot;
	}
	
	private Map<Integer, List<TreeNode>> trees = new HashMap<Integer, List<TreeNode>>();
	
    public List<TreeNode> generateTrees(int n) {
    	
    	if (n == 0) {
    		List<TreeNode> list = new ArrayList<TreeNode>();
    		list.add(null);
    		return list;
    	}
    	
    	List<TreeNode> oneTrees = new ArrayList<TreeNode>();
    	oneTrees.add(new TreeNode(1));

    	trees.put(1, oneTrees);
    	
    	if (n == 1) {
    		return oneTrees;
    	}
    	
    	for (int i = 2; i <= n; i++) {

    		List<TreeNode> treesForI = new ArrayList<TreeNode>();
    		trees.put(i, treesForI);
    		
    		for (int j = 1; j <= i; j++) {
    			List<TreeNode> leftTrees = new ArrayList<TreeNode>();
    			
    			if (j > 1) {
    				leftTrees = trees.get(j - 1);
    			}
    			
    			List<TreeNode> rightTrees = new ArrayList<TreeNode>();
    			if (j < i) {
    				List<TreeNode> knownTrees = trees.get(i - j);
    				for (TreeNode tree : knownTrees) {
    					rightTrees.add(copy(tree, j));
    				}
    			}
    			
    			if (leftTrees.isEmpty()) {
    				
    				for (TreeNode rightTree : rightTrees) {
    					TreeNode root = new TreeNode(j);
    					root.right = rightTree;
    					
    					treesForI.add(root);
    				}
    				
    			} else {
    				
    				for (TreeNode leftTree : leftTrees) {
    					
    					if (rightTrees.isEmpty()) {
    						TreeNode root = new TreeNode(j);
        					root.left = leftTree;
        					
    						treesForI.add(root);
    					} else {

        					for (TreeNode rightTree : rightTrees) {
        						TreeNode root = new TreeNode(j);
            					root.left = leftTree;
        						root.right = rightTree;

        						treesForI.add(root);
        					}
    					}
    					
    					
    					
    				}
    			}
    			
    			
    		}
    	}
    	
        return trees.get(n);
    }
    
    public int numTrees(int n) {
        if (n == 0) {
        	return 0;
        }
        
        return generateTrees(n).size();
    }
    
    public static void main(String[] args) {
    	List<TreeNode> trees = (new BSTGenerator().generateTrees(4));
    	
    	System.out.println(trees);
    }
}