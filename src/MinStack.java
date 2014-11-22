
class MinStack {
	
	private final static int MAX = 100000;
	
	private int[] list = new int[MAX];
	
	private int[] minIndexes = new int[MAX];
	
	private int topIndex = -1;
	
	public void push(int x) {
		this.list[++this.topIndex] = x;
		if (this.topIndex == 0
				|| this.list[this.minIndexes[this.topIndex-1]] > x) {
			this.minIndexes[this.topIndex] = this.topIndex;
		} else {
			this.minIndexes[this.topIndex] = this.minIndexes[this.topIndex - 1];
		}
	}

	public void pop() {
		this.topIndex--;
	}

	public int top() {
		return this.list[this.topIndex];
	}

	public int getMin() {
		if (this.topIndex >= 0) {
			return this.list[this.minIndexes[this.topIndex]];
		} else {
			return Integer.MAX_VALUE;
		}
		
	}

	public static void main(String[] args) {
		MinStack stack = new MinStack();
		for (int i = 100000; i > 0; i--) {
			stack.push(i);
		}
		
		for (int i = 100000; i> 0; i--) {
			stack.pop();
			System.out.println(stack.getMin());
		}
	}
}
