
public class Solution {

    private static int min(int x, int y) {
    	return x > y ? y : x;
    }
    
    private static int max(int x, int y) {
    	return x > y ? x : y;
    }
    
    public int maxProduct(int[] A) {
    	
    	int maxSoFar = 1;

    	boolean initialize = false;
    	
    	boolean hasSetMaxSoFar = false;
    	
    	int maxPositive = 0;
    	int minNegative = 1;
    	
    	
    	for (int i = 0; i < A.length; i++) {
    		if (!initialize) {
    			if (A[i] == 0) {
    				maxPositive = 0;
    				minNegative = 1;

    				initialize = false;
    				
    				if (!hasSetMaxSoFar) {
    					maxSoFar = A[i];
    					hasSetMaxSoFar = true;
    				}
    			} else if(A[i] > 0) {
    				maxPositive = A[i];
    				minNegative = 1;
    				
    				initialize = true;
    				
    				if (!hasSetMaxSoFar) {
    					maxSoFar = A[i];
    					hasSetMaxSoFar = true;
    				} else {
    					maxSoFar = max(maxSoFar, A[i]);
    				}
    			} else {
    				maxPositive = 0;
    				minNegative = A[i];
    				
    				initialize = true;
    				
    				if (!hasSetMaxSoFar) {
    					maxSoFar = A[i];
    					hasSetMaxSoFar = true;
    				} else {
    					maxSoFar = max(maxSoFar, A[i]);
    				}
    			}
    		} else {
    			
    			if (A[i] == 0) {
    				maxSoFar = max(maxSoFar, A[i]);
    				
    				maxPositive = 0;
    				minNegative = 1;
    				
    				initialize = false;
    			} else if (A[i] > 0) {
    				if (maxPositive == 0) {
    					maxPositive = A[i];
    				} else {
    					maxPositive *= A[i];
    				}
    				
    				maxSoFar = max(maxSoFar, maxPositive);
    				
    				if (minNegative == 1) {
    				} else {
    					minNegative *= A[i];
    				}
    				
    			} else {
    				if (minNegative == 1) {
    					if (maxPositive == 0) {
    						minNegative = A[i];
    					} else {
    						minNegative = maxPositive * A[i];
    						maxPositive = 0;
    					}
    					
    				} else {
    					if (maxPositive == 0) {
    						maxPositive = minNegative * A[i];
    						minNegative = A[i];
    						
    						maxSoFar = max(maxSoFar, maxPositive);
    						
    					} else {
    						
        						int temp = maxPositive;
        						maxPositive = minNegative * A[i];
        						minNegative = temp * A[i];
        						
        						maxSoFar = max(maxSoFar, maxPositive);
    					}
    					
    				}
    				
    			}
    		}
    	}
    	return maxSoFar;
    }

    private int product(int[] A, int left, int right) {
        int product = 1;
        for (int i = left; i <= right; i++) {
            product *= A[i];
        }

        return product;
    }
    

    public static void main(String[] args) throws Exception{
        System.out.println(new Solution().maxProduct(new int[]{1, -2, 0}));
        System.out.println(new Solution().maxProduct(new int[]{-2, 0 ,1}));
        System.out.println(new Solution().maxProduct(new int[]{0, 2}));
        System.out.println(new Solution().maxProduct(new int[]{0}));
        System.out.println(new Solution().maxProduct(new int[]{-1, -2, -9, -6}));
        System.out.println(new Solution().maxProduct(new int[]{-2, 5, -2, -4, 3}));
    }
}
