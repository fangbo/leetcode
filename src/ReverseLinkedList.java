/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class ReverseLinkedList {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
        	return head;
        }
        
        if (m == n) {
        	return head;
        }
        
        boolean inreverse = false;
        int index = 1;
        ListNode current = head;
        
        ListNode prefixNode = head;
        ListNode firstStart = null;
        
        while (current != null) {
        	if (inreverse) {

        		ListNode next = current.next;
        		
        		current.next = prefixNode.next;
        		prefixNode.next = current;
        		
        		current = next;
        		index++;
        		if (index > n) {
        			inreverse = false;
        			break;
        		}
        	} else {
        		if (m == 1) {
        			prefixNode = new ListNode(0);
        			prefixNode.next = current;
        			
        			inreverse = true;
        			firstStart = current;
        			
        		} else {
        			if (index == m - 1) {
            			prefixNode = current;
            		}
            		current = current.next;
            		index++;
            		if (index == m) {
            			inreverse = true;
            			firstStart = current;
            		}
        		}
        		
        	}
        }
        
        if (m == 1) {
        	head = prefixNode.next;
        }
        if (firstStart != null) {
        	firstStart.next = current;
        }
        
    	return head;
    }
    
    private static void print(ListNode head) {
    	ListNode current = head;
    	StringBuilder builder = new StringBuilder();
    	while (current != null) {
    		builder.append(current.val + "  ");
    		current = current.next;
    	}
    	
    	System.out.println(builder);
    }
    
    public static void main(String[] args) {
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(4);
    	head.next.next.next.next = new ListNode(5);
    	
    	print(head);
    	
    	head = new ReverseLinkedList().reverseBetween(head, 1, 2);
    	
    	print(head);
    	
    	head = new ReverseLinkedList().reverseBetween(head, 2, 3);
    	
    	print(head);
    	
    	head = new ListNode(1);
    	head.next = new ListNode(2);
    	
    	head = new ReverseLinkedList().reverseBetween(head, 1, 2);
    	
    	print(head);
    }
    
}