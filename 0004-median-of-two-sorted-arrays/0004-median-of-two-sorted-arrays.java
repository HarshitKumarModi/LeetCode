class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;

        int half = (m + n + 1) / 2;

        while(left <= right){
            int i = left + (right - left) / 2;
            int j = half - i;

            int nums1left;
            int nums1right;
            int nums2left;
            int nums2right;

            if(i == 0){
                nums1left = Integer.MIN_VALUE;
            } else {
                nums1left = nums1[i-1];
            }

            if(i == m){
                nums1right = Integer.MAX_VALUE;
            } else {
                nums1right = nums1[i];
            }

            if(j == 0){
                nums2left = Integer.MIN_VALUE;
            } else {
                nums2left = nums2[j-1];
            }

            if(j == n){
                nums2right = Integer.MAX_VALUE;
            } else {
                nums2right = nums2[j];
            }

            if(nums1left <= nums2right && nums2left <= nums1right){
                if((m+n) % 2 == 1){
                    return Math.max(nums1left, nums2left);
                }
                return (Math.max(nums1left, nums2left) + Math.min(nums1right, nums2right)) / 2.0;
            } else if(nums1left > nums2right){
                right = i - 1;
            } else{
                left = i + 1;
            }
        }
        return 0.0;
    }
}