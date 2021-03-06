

public class RotateSortedArray {

    public int findMin(int[] num) {
    
        return findMin(num, 0, num.length - 1);
    }

    private int findMin(int[] num, int left, int right) {
        int mid = (left + right) / 2;
        if (left == mid) {
            return num[left] > num[right] ? num[right] : num[left];
        }

        if (num[mid] > num[right]) {
            return findMin(num, mid, right);
        } else {
            return findMin(num, left, mid);
        }
    }
}
