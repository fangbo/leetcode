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
public class SumRootToLeft {
    
	private List<String> allPath(TreeNode root) {
		List<String> paths = new ArrayList<String>();
		if (root.left == null && root.right == null) {
			paths.add(String.valueOf(root.val));
			return paths;
		}
		
		if (root.left != null) {
			List<String> leftPath = allPath(root.left);
			for (String left : leftPath) {
				paths.add(root.val + left);
			}
		}
		
		if (root.right != null) {
			List<String> rightPath = allPath(root.right);
			for (String right : rightPath) {
				paths.add(root.val + right);
			}
		}
		
		return paths;
	}
	
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int sum = 0;
		List<String> allPaths = allPath(root);
		for (String path : allPaths) {
			sum += Integer.valueOf(path);
		}
		return sum;
    }
}