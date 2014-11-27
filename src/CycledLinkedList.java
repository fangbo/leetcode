

public class CycledLinkedList {
    public boolean hasCycle(ListNode head) {
    	if (head == null || head.next == null) {
    		return false;
    	}
    	
    	ListNode p = head.next;
    	while (true) {
    		if (p.next == null) {
    			return false;
    		}
    		
    		if (p.next == head) {
    			return true;
    		}
    		
    		ListNode tmp = p.next;
    		p.next = head;
    		p = tmp;
    	}
    }
    
    public ListNode detectCycle2(ListNode head) {
    	if (head == null || head.next == null) {
    		return null;
    	}
    	
    	ListNode p = head;
    	ListNode pre = head;
    	
    	ListNode EMPTY = new ListNode(0);
    	
    	while (true) {
    		if (p == null) {
    			return null;
    		}
    		
    		if (p.next == EMPTY) {
    			return p;
    		}
    		pre = p;
    		ListNode tmp = p.next;
			p.next = EMPTY;
    		p = tmp;
    	}
    }
    
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast == null || fast.next == null) {
                return null;    //遇到null了，说明不存在环
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;    //第一次相遇在Z点
            }
        }

        slow = head;    //slow从头开始走，
        while (slow != fast) {    //二者相遇在Y点，则退出
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
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
    
    private static ListNode build(int[] data, int cycleIndex) {
    	ListNode head = new ListNode(data[0]);
    	ListNode p = head;
    	ListNode tail = null;
    	for (int i = 1; i < data.length; i++) {
    		p.next = new ListNode(data[i]);
    		
    		p = p.next;
    	}
    	
		tail = p;
    	p = head;
    	for (int i = 0; i < data.length; i++) {
    		if (i == cycleIndex) {
    			tail.next = p;
    		}
    		
    		p = p.next;
    	}
    	
    	return head;
    }
    
    public static void main(String[] args) {
    	ListNode head = null;
    	
//    	// 1 -> 2
//    	head = new ListNode(1);
//    	head.next = new ListNode(2);
//    	
////    	System.out.println(new Solution().hasCycle(head));
//    	System.out.println(new Solution().detectCycle(head));
//    	
////    	// 1 -> 2 -> 2
////    	head = new ListNode(1);
////    	head.next = new ListNode(2);
////    	head.next.next = head.next;
////    	
//////    	System.out.println(new Solution().hasCycle(head));
////    	System.out.println(new Solution().detectCycle(head).val);
//    	
////    	// 1 -> 2 -> 1
////    	head = new ListNode(1);
////    	head.next = new ListNode(2);
////    	head.next.next = head;
////    	
////////    	System.out.println(new Solution().hasCycle(head));
////    	System.out.println(new Solution().detectCycle(head).val);
////    	
//    	// 1 -> 2 -> 3 -> 1
//    	head = new ListNode(1);
//    	head.next = new ListNode(2);
//    	head.next.next = new ListNode(3);
//    	head.next.next.next = head.next;
////    	System.out.println(new Solution().hasCycle(head));
//    	System.out.println(new Solution().detectCycle(head).val);
////    	
//    	// 3 -> 1 -> 0 -> -4 -> 1
//    	head = new ListNode(3);
//    	head.next = new ListNode(1);
//    	head.next.next = new ListNode(0);
//    	head.next.next.next = new ListNode(-4);
//    	head.next.next.next.next = head.next;
//    	
////    	System.out.println(new Solution().hasCycle(head));
//    	System.out.println(new Solution().detectCycle(head).val);
////    	
    	// 3 -> 1 -> 0 -> -4 -> 3
    	head = new ListNode(3);
    	head.next = new ListNode(1);
    	head.next.next = new ListNode(0);
    	head.next.next.next = new ListNode(-4);
    	head.next.next.next.next = head;
//    	System.out.println(new Solution().hasCycle(head));
    	System.out.println(new CycledLinkedList().detectCycle2(build(new int[]{3, 1, 0, -4}, 0)).val);
    	System.out.println(new CycledLinkedList().detectCycle(build(new int[]{3, 1, 0, -4}, 0)).val);
//    	
    	System.out.println(new CycledLinkedList().detectCycle2(head = build(new int[]{3, 2, 0, -4}, 1)).val);
    	System.out.println(new CycledLinkedList().detectCycle(head = build(new int[]{3, 2, 0, -4}, 1)).val);
    	
    	System.out.println(new CycledLinkedList().detectCycle2(build(new int[]{1, 2}, 0)).val);
    	System.out.println(new CycledLinkedList().detectCycle(build(new int[]{1, 2}, 0)).val);
    }
}