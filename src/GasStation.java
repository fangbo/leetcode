import java.util.Stack;



class Station implements Comparable<Station>{
	
	int index;
	
	int gas;
	
	int cost;
	
	int remain;

	public Station(int index, int gas, int cost) {
		this.index = index;
		this.gas = gas;
		this.cost = cost;
		this.remain = this.gas - this.cost;
	}
	
	@Override
	public int compareTo(Station o) {
		return -Integer.valueOf(this.remain).compareTo(Integer.valueOf(o.remain));
	}
	
}

class StationRemain {
	int startIndex;
	int endIndex;
	int remain;
}


public class GasStation {
	
	private boolean check(int[] gas, int[] cost, int station) {
		int remain = 0;
		for (int i = 0; i < gas.length; i++) {
			int currentStation = (station + i + gas.length) % gas.length;
			remain += gas[currentStation];
			
			if (remain < cost[currentStation]) {
				return false;
			}
			
			remain -= cost[currentStation];
		}
		return true;
	}
    public int canCompleteCircuit(int[] gas, int[] cost) {

    	Stack<StationRemain> stack = new Stack<StationRemain>();

    	StationRemain remain = new StationRemain();
    	remain.startIndex = 0;
    	remain.endIndex = 0;
    	remain.remain = gas[0] - cost[0];
    	
    	stack.push(remain);
    	for (int i = 1; i < gas.length; i++) {
    		StationRemain preRemain = stack.pop();
    		
    		int eachRemain = gas[i] - cost[i];
    		if (preRemain.remain < 0 ||
    				(preRemain.remain + eachRemain) < 0) {
    			StationRemain curRemain = new StationRemain();
    			curRemain.startIndex = i;
    			curRemain.endIndex = i;
    			curRemain.remain = eachRemain;
    			
    			stack.push(preRemain);
    			stack.push(curRemain);
    		} else {
				preRemain.endIndex = i;
				preRemain.remain = preRemain.remain + eachRemain;
				
				stack.push(preRemain);
    		}
    	}
    	StationRemain topRemain = stack.pop();
    	stack.push(topRemain);
    	int totalRemain = 0;
    	while (!stack.isEmpty()) {
    		totalRemain += stack.pop().remain;
    	}
    	
    	if (totalRemain < 0) {
    		return -1;
    	}
    	return topRemain.startIndex;
    }
    
    public static void main(String[] args) {
    	System.out.println(new GasStation().canCompleteCircuit(new int[]{2, 3, 1}, new int[]{4, 5, 3}));
    	System.out.println(new GasStation().canCompleteCircuit(new int[]{2, 6, 5}, new int[]{4, 5, 3}));
    	System.out.println(new GasStation().canCompleteCircuit(new int[]{2, 4}, new int[]{3, 4}));
    }
}