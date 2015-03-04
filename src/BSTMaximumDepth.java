import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BSTMaximumDepth {

	private Map<TreeNode, Integer> depths = new HashMap<TreeNode, Integer>();
	
    public int maxDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
        depths.put(root, 1);
        
        travel(root);

        List<Integer> d = new ArrayList<Integer>(depths.values());
        Collections.sort(d);
        
        return d.get(d.size() - 1);
    }
    
    private void travel(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	int rootDepth = depths.get(root);
    	
    	if (root.left != null) {
    		depths.put(root.left, rootDepth + 1);
    	}
    	
    	if (root.right != null) {
    		depths.put(root.right, rootDepth + 1);
    	}
    	
    	travel(root.left);
    	travel(root.right);
    	
    }
}
