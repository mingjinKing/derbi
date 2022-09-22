package com.derbi.prac;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTest {

    @Test
    public void testSearch(){
        int[] nums = new int[]{4,2,8,7};
        int target = 8;
        int result = search(nums, target);
    }


    @Test
    public void testRemoveElement(){
        int[] nums = new int[]{3,2,2,3};
        int val = 3;
        int result = removeElement(nums, val);
    }

    @Test
    public void testSortedSquares(){
        int[] nums = new int[]{-4,-1,0,3,10};
        sortedSquares(nums);
    }

    @Test
    public void testMinSubArrayLen(){
        int[] nums = new int[]{2,3,1,2,4,3};
        int result = minSubArrayLen(7, nums);
        Assert.assertEquals(2,result);
    }

    @Test
    public void testGenerateMatrix(){
        int[][] nums = new int[][]{{1,2,3},{8,9,4},{7,6,5}};
        int[][] result = generateMatrix(3);
        Assert.assertArrayEquals(nums, result);
    }

    /**
     * 二分查找：
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * 所有元素是不重复的
     * 注意开闭区间
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if(target < nums[0] || target> nums[end]){
            return -1;
        }
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] < target){
                start = mid + 1;
            }else if(nums[mid] > target){
                end = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 移除元素：
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组
     * 双指针
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int leftIndex = 0;
        int rightIndex = nums.length-1;
        while(leftIndex <= rightIndex){
            while(leftIndex <= rightIndex && nums[leftIndex] != val){
                leftIndex++;
            }
            while(leftIndex <= rightIndex && nums[rightIndex] == val){
                rightIndex--;
            }
            if(leftIndex < rightIndex){
                // 前++是先自加再使用而后++是先使用再自加
                nums[leftIndex++] = nums[rightIndex--];
            }
        }
        return leftIndex;
    }

    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int index = nums.length - 1;
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            if(nums[left] * nums[left] <= nums[right] * nums[right]){
                result[index] = nums[right] * nums[right];
                index--;
                right--;
            } else {
                result[index] = nums[left] * nums[left];
                index--;
                left++;
            }
        }
        return result;
    }


    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
     * 如果不存在符合条件的子数组，返回 0 。
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int subStrLen = 0;
        int sum = 0;
        int j = 0;
        for(int i = j; i < nums.length; i++){
            sum += nums[i];
            while(sum >= target){
                subStrLen = i-j+1;
                result = result > subStrLen ? subStrLen : result;
                sum -= nums[j++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * 示例:
     * 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
     *
     * 关键点：模拟真实场景，编写代码
     *  1. 左闭右开或者左开右闭
     *  2. 确定循环的圈数loop: n/2
     *  3. 每一圈的起始位置：startX、startY
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] nums = new int[n][n];
        int value = 1;
        int loop = n/2;
        int startX = 0;
        int startY= 0;
        int offset = 1;
        int i = 0;
        int j = 0;
        while (loop-- != 0){
            for (i = startY; i < n-offset; i++){
                nums[startX][i] = value;
                value++;
            }

            for(j = startX; j < n-offset; j++){
                nums[j][i] = value;
                value++;
            }

            for (; i > startY; i--){
                nums[j][i] = value;
                value++;
            }

            for (; j > startX; j--){
                nums[j][i] = value;
                value++;
            }

            startX++;
            startY++;

            offset++;
        }
        if(n%2 == 1){
            nums[n/2][n/2] = value;
        }
        return nums;
    }
}
