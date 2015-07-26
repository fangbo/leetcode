/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
    	if (head == null) {
    		return null;
    	}
    	
    	ListNode lessHead = null;
    	ListNode lessTail = null;
    	
    	ListNode greateHead = null;
    	ListNode greateTail = null;

    	ListNode t = head;
    	
    	while (t != null) {
    		ListNode temp = t.next;
    		if (t.val < x) {
    			if (lessHead == null) {
    				lessTail = t;
    				lessHead = t;
    				
    				lessTail.next = null;
    				lessHead.next = null;
    			} else {
    				lessTail.next = t;
    				lessTail = t;
    				lessTail.next = null;
    			}
    			
    		} else {
    			if (greateHead == null) {
    				greateTail = t;
    				greateHead = t;
    				greateTail.next = null;
    				greateHead.next = null;
    			} else {
    				greateTail.next = t;
    				greateTail = t;
    				greateTail.next = null;
    			}
    		}
    		t = temp;
    	}
    	
    	ListNode newHead = null;
    	
    	if (lessHead != null) {
    		newHead = lessHead;
    		lessTail.next = greateHead;
    	} else {
    		newHead = greateHead;
    	}
    	return newHead;
    }
    
    private static final ListNode list(int[] values) {
    	ListNode head = null;
    	
    	ListNode t = null;
    	for (int x : values) {
    		ListNode temp = new ListNode(x);
    		if (head == null) {
    			head = temp;
    			t = temp;
    		} else {
    			t.next = temp;
    			t = temp;
    		}
    	}
    	
    	return head;
    }
    
    private static final void print(ListNode head) {
    	ListNode t = head;
    	while (t != null) {
    		System.out.print(" " + t.val);
    		t  = t.next;
    	}
    	System.out.println("");
    }
    
    public static void main(String[] args) {
    	ListNode head = list(new int[]{1, 0});

    	print(head);
    	
    	head = new PartitionList().partition(head, 0);
    	
    	print(head);
    }
}