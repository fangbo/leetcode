import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


class ValueHolder {
	
	private int value;
	
	private int counter = 1;
	
	public ValueHolder(int value) {
		this.value = value;
	}
	
	public void setValue(int value) {
		this.counter++;
		this.value = value;
	}
	
	public int getValue() {
		this.counter++;
		return this.value;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
}

class ValueNode {
	int key;
	
	int value;
	
	ValueNode next;
	
	ValueNode pre;
}

public class LRUCache {
    
	private int capacity = 0;
	
	private int size = 0;
	
	private Map<Integer, ValueNode> values = new HashMap<Integer, ValueNode>();
	
	private ValueNode head = null;
	
	private ValueNode tail = null;
	
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
    	ValueNode value = this.values.get(key);
    	if (value == null) {
    		return -1;
    	}

    	this.popNode(value);

    	return value.value;
    }
    
    private void popNode(ValueNode node) {
    	if (this.head == node) {
    		return;
    	}
    	
    	ValueNode pre = node.pre;
    	
    	pre.next = node.next;
    	if (node.next != null) {
    		node.next.pre = pre;
    	}
    	
    	if (tail == node) {
    		tail = node.pre;
    	}
    	
    	head.pre = node;
    	
    	node.next = head;
    	node.pre = null;
    	
    	head = node;
    }
    
    public void set(int key, int value) {
    	ValueNode node = this.values.get(key);
		if (node != null) {
			node.value = value;
			
		} else {
			// cache is full, remove the tail node;
			if (this.size == this.capacity) {
				
				this.values.remove(this.tail.key);
				if (this.head == this.tail) {
					this.head = null;
					this.tail = null;
				} else {
					this.tail = this.tail.pre;
				}
				
				this.size--;
			}
				
			node = new ValueNode();
			this.values.put(key, node);
			node.value = value;
			node.key = key;
			
			if (head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				node.pre = tail;
				tail = node;
			}
			
			this.size++;
				
		}
		
		this.popNode(node);
    }
    
    public static void main(String[] args) {
    	LRUCache cache = new LRUCache(1);
    	cache.set(2, 1);
    	cache.get(2);
    	cache.set(3, 2);
    	cache.get(2);
    	cache.get(3);
    }
}