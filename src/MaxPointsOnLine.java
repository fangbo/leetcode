import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

class Point {
	int x;
	int y;
	
	Point() {x = 0; y = 0;}
	
	Point(int a, int b) { x = a; y = b;}
}

class Value {
	int x = 0;
	
	void inc() {
		this.x++;
	}
}
public class MaxPointsOnLine {
	
	
    public int maxPoints(Point[] points) {
        int max = 0;

        Map<Double, Integer> eachMaxes = new HashMap<Double, Integer>();
        
        for (int i = 0; i < points.length; i++) {
        	Point point = points[i];

        	eachMaxes.clear();
        	int duplicate = 1;
        	
        	for (int j = 0; j < points.length; j++) {
        		if (i == j) {
        			continue;
        		}
        		
        		Point temp = points[j];
        		if (temp.x == point.x && temp.y == point.y) {
        			duplicate++;
        			continue;
        		}
        		
        		double slope = 1.0;
        		if (temp.x == point.x) {
        			slope = 1.0 * Integer.MAX_VALUE;
        		} else {
        			slope = 1.0 * (temp.y - point.y) / (temp.x - point.x);
        		}
        		
        		if (eachMaxes.containsKey(slope)) {
        			eachMaxes.put(slope, eachMaxes.get(slope) + 1);
        		} else {
        			eachMaxes.put(slope, 1);
        		}
        		
        	}
        	if (eachMaxes.isEmpty()) {
        		max = duplicate;
        	}
        	
        	for (Map.Entry<Double, Integer> item : eachMaxes.entrySet()) {
        		max = Math.max(max, item.getValue() + duplicate);
        	}
        }
        
        return max;
    }
    
    public static void main(String[] args) {
    	Point[] points = null;
    	
    	
    	points = new Point[]{new Point(0, 0), new Point(0, 0), new Point(3, 5), new Point(0, 1)};
    	System.out.println(new MaxPointsOnLine().maxPoints(points));
    	
//    	points = new Point[1000];
//    	for (int i = 0; i < 1000; i++) {
//    		points[i] = new Point(new Random().nextInt(10000), new Random().nextInt(10000));
//    	}
    	
    	long start = System.currentTimeMillis();
    	points = new Point[]{new Point(29,87),new Point(145,227),new Point(400,84),new Point(800,179),new Point(60,950),new Point(560,122),new Point(-6,5),new Point(-87,-53),new Point(-64,-118),new Point(-204,-388),new Point(720,160),new Point(-232,-228),new Point(-72,-135),new Point(-102,-163),new Point(-68,-88),new Point(-116,-95),new Point(-34,-13),new Point(170,437),new Point(40,103),new Point(0,-38),new Point(-10,-7),new Point(-36,-114),new Point(238,587),new Point(-340,-140),new Point(-7,2),new Point(36,586),new Point(60,950),new Point(-42,-597),new Point(-4,-6),new Point(0,18),new Point(36,586),new Point(18,0),new Point(-720,-182),new Point(240,46),new Point(5,-6),new Point(261,367),new Point(-203,-193),new Point(240,46),new Point(400,84),new Point(72,114),new Point(0,62),new Point(-42,-597),new Point(-170,-76),new Point(-174,-158),new Point(68,212),new Point(-480,-125),new Point(5,-6),new Point(0,-38),new Point(174,262),new Point(34,137),new Point(-232,-187),new Point(-232,-228),new Point(232,332),new Point(-64,-118),new Point(-240,-68),new Point(272,662),new Point(-40,-67),new Point(203,158),new Point(-203,-164),new Point(272,662),new Point(56,137),new Point(4,-1),new Point(-18,-233),new Point(240,46),new Point(-3,2),new Point(640,141),new Point(-480,-125),new Point(-29,17),new Point(-64,-118),new Point(800,179),new Point(-56,-101),new Point(36,586),new Point(-64,-118),new Point(-87,-53),new Point(-29,17),new Point(320,65),new Point(7,5),new Point(40,103),new Point(136,362),new Point(-320,-87),new Point(-5,5),new Point(-340,-688),new Point(-232,-228),new Point(9,1),new Point(-27,-95),new Point(7,-5),new Point(58,122),new Point(48,120),new Point(8,35),new Point(-272,-538),new Point(34,137),new Point(-800,-201),new Point(-68,-88),new Point(29,87),new Point(160,27),new Point(72,171),new Point(261,367),new Point(-56,-101),new Point(-9,-2),new Point(0,52),new Point(-6,-7),new Point(170,437),new Point(-261,-210),new Point(-48,-84),new Point(-63,-171),new Point(-24,-33),new Point(-68,-88),new Point(-204,-388),new Point(40,103),new Point(34,137),new Point(-204,-388),new Point(-400,-106)};
    	System.out.println(new MaxPointsOnLine().maxPoints(points));
    	System.out.println("cost: " + (System.currentTimeMillis() - start));
    	
    }
}