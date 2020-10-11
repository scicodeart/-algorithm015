/**
 * @author Lai
 * @version 1.0
 * @date 2020-10-10 15:47
 */
public class SearchInRotatedSortedArray {
    class Solution {
        public int search(int[] nums, int target) {

            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            if (n == 1){
                return nums[0] == target ? 0 : -1;
            }
            int l = 0, r = n - 1;
            int mid ;
            while (l <= r){
                mid = r + (r - l)/2;
                if (nums[mid] == target){
                    return mid;
                }
                if (nums[l] <= nums[mid]) {
                    // 如果target在左边
                    if (nums[l] <= target && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[r]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
}
