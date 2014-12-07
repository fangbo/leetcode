import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


class Board {
	
	public static final char X = 'X';
	public static final char O = 'O';
	
	char[][] board;
	
	int height = 0;
	int width = 0;
	
	Map<String, BoardNode> nodes = new HashMap<String, BoardNode>();

	public Board(char[][] board) {
		this.board = board;
		this.height = board.length;
		if (this.height >= 1) {
			this.width = board[0].length;
		}
		
	
		for (int i = 0; i < height; i++) {
			int jLen = this.board[i].length;
			for (int j = 0; j < jLen; j++) {
				BoardNode node = getNode(i, j);
				if (node == null) {
					node = buildNode(i, j);
				}
				
				if( i > 0 ) {
					BoardNode top = getNode(i - 1, j);
					if (top == null) {
						top = buildNode(i - 1, j);
					}
					node.addNeighbor(top);
				}
				
				if (i < height - 1) {
					BoardNode down = getNode(i + 1, j);
					if (down == null) {
						down = buildNode(i + 1, j);
					}
					node.addNeighbor(down);
				}
				
				if (j > 0) {
					BoardNode left = getNode(i, j - 1);
					if (left == null) {
						left = buildNode(i, j - 1);
					}
					node.addNeighbor(left);
				}
				
				if (j < jLen - 1) {
					BoardNode right = getNode(i, j + 1);
					if (right == null) {
						right = buildNode(i, j + 1);
					}
					node.addNeighbor(right);
				}
			}
		}
	}
	
	
	public void solve() {
		List<BoardNode> deads = new ArrayList<BoardNode>();
		for (Map.Entry<String, BoardNode> entry : this.nodes.entrySet()) {
			BoardNode node = entry.getValue();
			if (node.isDead()) {
				deads.add(node);
			}
		}

		for (BoardNode deadNode : deads) {
			markDead(deadNode);
		}
		
		for (Map.Entry<String, BoardNode> entry: this.nodes.entrySet()) {
			BoardNode node = entry.getValue();
			if (node.isDead()) {
				continue;
			}
			this.board[node.i][node.j] = X;
		}
	}
	
	private void markDead(BoardNode node) {
		Set<BoardNode> exclusives = new HashSet<BoardNode>();

		Stack<BoardNode> stack = new Stack<BoardNode>();
		stack.push(node);
		while (!stack.isEmpty()) {
			BoardNode top = stack.pop();
			
			if (exclusives.contains(top)) {
				continue;
			}
			
			exclusives.add(top);
			top.setDead();
			for (BoardNode n : top.neighbors) {
				if (exclusives.contains(n) ||
						n.isDead()) {
					continue;
				}
				
				stack.push(n);
			}
		}
	}
	
	public BoardNode getNode(int i, int j) {
		BoardNode node = this.nodes.get(String.format("%s-%s", i, j));
		return node;
	}
	
	public BoardNode getOrBuildNode(int i, int j) {
		BoardNode node = this.nodes.get(String.format("%s-%s", i, j));
		if (node == null) {
			return buildNode(i, j);
		} else {
			return node;
		}
	}
	
	public BoardNode buildNode(int i, int j) {
		BoardNode node = new BoardNode(i, j);
		
		if (this.board[i][j] == X) {
			node.setMark();
		}
		
		if (this.board[i][j] == O &&
				(i == 0 || i == (height - 1) || j == 0 || j == (height - 1))) {
			node.setDead();
		}
		
		this.nodes.put(String.format("%s-%s", i, j), node);
		
		return node;
	}
}


class BoardNode {
	
	int i;
	
	int j;
	
	List<BoardNode> neighbors = new ArrayList<BoardNode>();
	
	private boolean dead = false;
	
	private boolean mark = false;
	
	public BoardNode(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public String toString() {
		return String.format("(%s, %s)", i, j);
	}
	
	public boolean isMark() {
		return this.mark;
	}
	
	public void checkAndSetMark() {
		if (this.dead) {
			return;
		}
		
		boolean canMark = true;
		for (BoardNode n : this.neighbors) {
			if (!n.isMark()) {
				canMark = false;
			}
		}
		
		if (canMark) {
			this.setMark();
		}
	}
	
	public void setMark() {
		this.mark = true;
	}
	
	public void setDead() {
		this.dead = true;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void addNeighbor(BoardNode node) {
		if (node.isMark()) {
			return;
		}
		this.neighbors.add(node);
	}
}

public class SurroundedRegion {

	private int height;
	private int width;
	
	private int[] unionSet;
	private boolean[] hasEdgeO;
	
	public void solve(char[][] board) {
		
		if (board.length == 0 || board[0].length == 0) {
			return;
		}
		
		this.height = board.length;
		this.width = board[0].length;
		
		this.unionSet = new int[this.width * this.height];
		this.hasEdgeO = new boolean[this.unionSet.length];
		
		for (int i = 0; i < this.unionSet.length; i++) {
			this.unionSet[i] = i;
		}
		
		for (int i = 0; i < this.unionSet.length; i++) {
			int x = i / this.width;
			int y = i % this.width;
			this.hasEdgeO[i] = board[x][y] == 'O' && (x == 0 || y == 0 || x == height - 1 || y == width - 1);
		}
		
		for (int i = 0;  i < this.unionSet.length; i++) {
			int x = i / this.width;
			int y = i % this.width;
			
			int up = x - 1;
			int right = y + 1;
			
			if (up >= 0 && board[x][y] == board[up][y]) {
				union(i, i - width);
			}
			if (right < width && board[x][y] == board[x][right]) {
				union(i, i + 1);
			}
		}
		
		for (int i = 0; i < this.unionSet.length; i++) {
			int x = i / this.width;
			int y = i % this.width;
			
			if (board[x][y] == 'O' && !this.hasEdgeO[findSet(i)]) {
				board[x][y] = 'X';
			}
		}
	}
	
	private void union(int x, int y) {
		int xroot = findSet(x);
		int yroot = findSet(y);
		unionSet[xroot] = yroot;
		boolean hasEdgeO = this.hasEdgeO[xroot] || this.hasEdgeO[yroot];
		this.hasEdgeO[yroot] = hasEdgeO;
	}
	
	private int findSet(int x) {
		if (unionSet[x] == x) {
			return x;
		}
		
		unionSet[x] = findSet(unionSet[x]);
		return unionSet[x];
	}
	
	public static void main(String[] args) throws Exception{
		SurroundedRegion s = new SurroundedRegion();
		BufferedReader reader = 
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream(
										new File("/home/fangbo/leetcode/leetcode/src/data"))));
		
		String line = reader.readLine();
		String[] strArr = line.split(",");
		char[][] board = new char[strArr.length][];

		for (int i = 0; i < strArr.length; i++) {
			String str = strArr[i];
			str = str.replace("\"", "");
			char[] c = new char[str.length()];
			board[i] = c;
			for (int j = 0; j < c.length; j++) {
				c[j] = str.charAt(j);
			}
		}
//		board = new char[][]{
//				{'X', 'O' ,'X', 'X'},
//				{'X', 'O', 'O', 'X'},
//				{'X', 'O' ,'O', 'X'},
//				{'X', 'X' ,'X', 'O'},
//  		};
		s.solve(board);
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i]);
		}
	}
}
