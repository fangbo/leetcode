
public class Stock2 {

	public int maxProfit(int[] prices) {
        int n = prices.length;
        int nh0=0;
        int h0=Integer.MIN_VALUE;
        int nh1=Integer.MIN_VALUE;
        int h1=Integer.MIN_VALUE;
        int nh2=Integer.MIN_VALUE;

        for (int i=0;i<n;i++)
        {
            int newh0 = Math.max(h0,-prices[i]);
            int newnh1= Math.max(nh1,newh0+prices[i]);
            int newh1 = Math.max(h1,newnh1-prices[i]);
            int newnh2 = Math.max(nh2,newh1+prices[i]);

            h0 = newh0;
            nh1 = newnh1;
            h1 = newh1;
            nh2 = newnh2;
        }
        return Math.max(nh0,Math.max(nh1,nh2));  
    }
}
