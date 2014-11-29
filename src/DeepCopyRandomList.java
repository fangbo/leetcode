import java.util.HashMap;
import java.util.Map;


public class DeepCopyRandomList {
	
    public RandomListNode copyRandomList(RandomListNode head) {
    	if (head == null) {
    		return null;
    	}
    	
    	Map<RandomListNode, RandomListNode> oldToNew = new HashMap<RandomListNode, RandomListNode>();
    	Map<RandomListNode, RandomListNode> newToOld = new HashMap<RandomListNode, RandomListNode>();
    	
    	RandomListNode newHead = new RandomListNode(head.label);

    	if (head.next == null) {
    		if (head.random == head) {
    			newHead.random = newHead;
    		}
    		return newHead;
    	}
    	
    	RandomListNode oldNode = head;
    	RandomListNode newNode = newHead;
    	
    	while (oldNode != null) {

    		oldToNew.put(oldNode, newNode);
    		newToOld.put(newNode, oldNode);
    		
    		if (oldNode.next != null) {
	    		RandomListNode newNext = new RandomListNode(oldNode.next.label);
	    		newNode.next = newNext;
    		}

    		oldNode = oldNode.next;
    		newNode = newNode.next;
    	}
    	
    	newNode = newHead;
    	while (newNode != null) {
    		RandomListNode random = newToOld.get(newNode).random;
    		if (random != null) {
    			newNode.random = oldToNew.get(random);
    		}
    		
    		newNode = newNode.next;
    	}
    	
    	return newHead;
    }
    
    private static void print(RandomListNode head) {
    	if (head == null) {
    		return;
    	}
    	RandomListNode q = head;
    	while (q != null) {
    		RandomListNode next = q.next;
    		RandomListNode random = q.random;
    		System.out.println("[value:" + q.label + "][next:" + (next == null ? "null]" : next.label + "]") + "[random:" + (random == null ? "null]" : random.label + "]"));
    		q = q.next;
    	}
    }
    
    public static void main(String[] args) {
    	RandomListNode head = null;
    	head = new RandomListNode(1);
    	RandomListNode node2 = new RandomListNode(2);
    	RandomListNode node3 = new RandomListNode(3);
    	
    	head.next = node2;
    	head.random = node3;
    	node2.next = node3;
    	node2.random = head;
    	node3.random = node2;

    	print(head);
    	print(new DeepCopyRandomList().copyRandomList(head));
    	print(new DeepCopyRandomList().copyRandomList(null));
    	print(new DeepCopyRandomList().copyRandomList(new RandomListNode(2)));
    }
}