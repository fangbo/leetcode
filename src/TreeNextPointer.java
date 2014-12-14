import java.util.ArrayDeque;
import java.util.Queue;


public class TreeNextPointer {

	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeLinkNode> queue = new ArrayDeque<TreeLinkNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeLinkNode node = queue.poll();
			populate(node);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
    }
	
	private void populate(TreeLinkNode parent) {
		if (parent == null) {
			return;
		}
		
		if (parent.left == null && parent.right == null) {
			return;
		}
		
		TreeLinkNode higherNext = parent.next;
		TreeLinkNode rightNext = null;
		while (higherNext != null) {
			if (higherNext.left != null) {
				rightNext = higherNext.left;
				break;
			} else if (higherNext.right != null) {
				rightNext = higherNext.right;
				break;
			}
			
			higherNext = higherNext.next;
		}
		
		if (parent.left != null && parent.right != null) {

			parent.left.next = parent.right;
			parent.right.next = rightNext;
			
			
		} else if (parent.left != null && parent.right == null) {
			
			parent.left.next = rightNext;
			
		} else if (parent.left == null && parent.right != null) {
			
			parent.right.next = rightNext;
		}

	}
}
