import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ConvertSortedArrayToBST {
    
    public TreeNode sortedArrayToBST(int[] num) {
    	if (num == null || num.length == 0) {
    		return null;
    	}
    	return sortedArrayToBST(num, 0, num.length - 1);
    }
    
    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
    	if (num == null) {
    		return null;
    	}
    	if (start == end) {
    		return new TreeNode(num[start]); 
    	}
    	
    	int mid = (start + end) / 2;
    	TreeNode root = new TreeNode(num[mid]);
    	if (start < mid) {
    		root.left = sortedArrayToBST(num, start, mid - 1);
    	}
    	if (mid < end) {
    		root.right = sortedArrayToBST(num, mid + 1, end);
    	}
    	return root;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    	List<Integer> nums = new ArrayList<Integer>();
    	ListNode p = head;
    	while (p != null) {
    		nums.add(p.val);
    		p = p.next;
    	}
    	int[] num = new int[nums.size()];
    	for (int i = 0; i < num.length; i++) {
    		num[i] = nums.get(i).intValue();
    	}
        return sortedArrayToBST(num);
    }
    
    public static void main(String[] args) {
//    	ListNode head = new ListNode(-1);
//    	head.next = new ListNode(0);
//    	head.next.next = new ListNode(1);
//    	head.next.next.next = new ListNode(2);
//    	
//    	TreeNode node = new Solution().sortedListToBST(head);
//    	
//    	System.out.println(node);
    	
    	int[] num = new int[]{0, 1, 2};
    	TreeNode root = new ConvertSortedArrayToBST().sortedArrayToBST(num);
    	
    	System.out.println(root);
    }
}