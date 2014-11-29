import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;



class Child implements Comparable<Child>{
	
	int index;
	
	int rating;
	
	int candy = 1;

	@Override
	public int compareTo(Child o) {
		int value = Integer.valueOf(this.rating).compareTo(o.rating);
		if (value != 0) {
			return value;
		}
		return Integer.valueOf(this.index).compareTo(o.index);
	}
	
}


public class Candy {
    public int candy(int[] ratings) {
    	
    	PriorityQueue<Child> children = new PriorityQueue<Child>();

    	Map<Integer, Child> childIndexes = new HashMap<Integer, Child>();
    	
    	for (int index = 0; index < ratings.length; index++) {
    		Child c = new Child();
    		c.index = index;
    		c.rating = ratings[index];
    		children.offer(c);
    		
    		childIndexes.put(index, c);
    	}
    	
    	while (!children.isEmpty()) {
    		Child child = children.poll();
    		int leftIndex = child.index - 1;
    		if (leftIndex >= 0) {
    			Child leftChild = childIndexes.get(leftIndex);
    			if (leftChild.rating > child.rating &&
    					leftChild.candy <= child.candy) {
    				leftChild.candy = child.candy + 1;
    			}
    		}
    		
    		int rightIndex = child.index + 1;
    		if (rightIndex <= ratings.length - 1) {
    			Child rightChild = childIndexes.get(rightIndex);
    			
    			if (rightChild.rating > child.rating &&
    					rightChild.candy <= child.candy) {
    				rightChild.candy = child.candy + 1;
    			}
    			
    		}
    	}
    	
    	int minCandies = 0;
    	for (Map.Entry<Integer, Child> item : childIndexes.entrySet()) {
    		minCandies += item.getValue().candy;
    	}
    	return minCandies;
    }
    
    public static void main(String[] args) {
    	System.out.println(new Candy().candy(new int[]{2, 5, 6, 1}));
    	System.out.println(new Candy().candy(new int[]{2, 2}));
    	System.out.println(new Candy().candy(new int[]{1, 3, 5}));
    	System.out.println(new Candy().candy(new int[]{1, 2, 3, 2, 4}));
    }
}