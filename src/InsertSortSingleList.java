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


public class InsertSortSingleList {
	
	private ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode midPre = getMidPre(head);
		ListNode mid = midPre.next;
		midPre.next = null;
		
		ListNode head1 = mergeSort(head);
		ListNode head2 = mergeSort(mid);
		
		return merge(head1, head2);
	}
	
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
	
	private ListNode merge(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		ListNode head = null;
		ListNode tail = null;
		
		ListNode p = head1;
		ListNode q = head2;
		
		while (p != null && q != null) {
			if (p.val < q.val) {
				if (head == null) {
					head = p;
					tail = p;
					
					p = p.next;
				} else {
					tail.next = p;
					tail = p;
					p = p.next;
				}
				
			} else {
				if (head == null) {
					head = q;
					tail = q;
					
					q = q.next;
				} else {
					tail.next = q;
					tail = q;
					q = q.next;
				}
			}
		}
		
		if (p != null) {
			tail.next = p;
		}
		if (q != null) {
			tail.next = q;
		}
		
		return head;
	}
	
    public ListNode insertionSortList(ListNode head) {
    	
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode newHead = head;
    	ListNode pre = head;
    	ListNode current = pre.next;
    	
    	while (current != null) {
    		pre.next = current.next;
    		
    		current.next = newHead;
    		newHead = current;
    		
    		ListNode slot = newHead;
    		int slotValue = newHead.val;
    		
    		while (slot != pre && slotValue > slot.next.val) {
    			slot.val = slot.next.val;
    			slot = slot.next;
    		}
    		
    		slot.val = slotValue;
    		
    		current = pre.next;
    	}
    	
    	return newHead;
    	
    	
    }
 
    private static ListNode buildList(int[] data) {
    	ListNode node = new ListNode(data[0]);
    	ListNode head = node;
    	for (int i = 1; i < data.length; i++) {
    		node.next = new ListNode(data[i]);
    		node = node.next;
    	}
    	
    	return head;
    }
    
    private static void printList(ListNode head) {
    	if (head == null) {
    		return;
    	}
    	
    	ListNode current = head;
    	while (current != null) {
    		System.out.println(current.val);
    		current = current.next;
    	}
    }
    
    public static void main(String[] args) {
    	ListNode head = new ListNode(3);
    	head.next = new ListNode(2);
    	head.next = new ListNode(4);
    	
    	printList(new InsertSortSingleList().insertionSortList(buildList(new int[]{3, 2, 4})));
    	printList(new InsertSortSingleList().insertionSortList(buildList(new int[]{3})));
    	printList(new InsertSortSingleList().insertionSortList(buildList(new int[]{3, 2})));
    	
    }
    
}