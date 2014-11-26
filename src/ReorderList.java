import java.util.Stack;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class ReorderList {
	
	private ListNode getMidPre(ListNode head) {
		ListNode p = head;
		ListNode q = head;
		
		while (p != null) {
			p = p.next;
			if (p != null) {
				p = p.next;
			}
			if (p != null) {
				q = q.next;
			}
			
		}
		return q;
	}
	
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
        	return;
        }
        
        ListNode head1 = head;
        ListNode midPre = getMidPre(head1);
        ListNode head2 = midPre.next;
        midPre.next = null;
        
        Stack<ListNode> postNodes = new Stack<ListNode>();
        while (head2 != null) {
        	ListNode next = head2.next;
        	head2.next = null;
        	postNodes.push(head2);
        	head2 = next;
        }
        
        while (!postNodes.isEmpty()) {
        	ListNode postNode = postNodes.pop();
        	ListNode next = head1.next;
        	
        	head1.next = postNode;
        	postNode.next = next;
        	
        	head1 = next;
        }
    }
    
    private static void print(ListNode head) {
    	if (head == null) {
    		return;
    	}

    	ListNode p = head;
    	while (p != null) {
    		System.out.println(p.val);
    		p = p.next;
    	}
    }
    
    public static void main(String[] args) {
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	
    	head.next.next = new ListNode(3);
    	
    	head.next.next.next = new ListNode(4);
    	
    	new ReorderList().reorderList(head);
    	print(head);
    	
    }
}