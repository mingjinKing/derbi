package com.derbi.prac;

import org.junit.Test;

public class SortTest {

    @Test
    public void testQuickSort(){
        int nums[] = new int[]{4,5,1,9,6,7,19,11,3,8};
        quickSort(nums, 0, 9);
        int s =0;
    }


    /**
     * 快排：
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     * @param nums
     * @return
     */
   /* public int[] quickSort(int[] nums, int left, int right){
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            quickSort(nums, left, partitionIndex - 1);
            quickSort(nums, partitionIndex + 1, right);
        }
        return nums;
    }*/

    /**
     * 在数组的头部和尾部分别设置一个哨兵，同时向对方走去。尾部的哨兵如发现有比基准数小的数，停下。
     * 头部的哨兵如发现有比基准数大的数，停下。交换两个数。再重新走重复前面的交换过程。直到两个哨兵相遇，交换基准数和尾哨兵。
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private void quickSort(int[] nums, int start, int end){
       /* int index = left + 1;
        for(int i = index; i <= right; i++){
            if(nums[i] < nums[left]){
                exchange(nums, i, index);
                index ++;
            }
        }
        exchange(nums, left, index-1);
        return index - 1;*/
        if(start >= end){
            return;
        }
        int left = start;
        int right = end;
        int base = nums[left];
        while (left < right){
            while (left < right && nums[right] >= base){
                right--;
            }
            while (left < right && nums[left] <= base){
                left++;
            }
            exchange(nums, left, right);
        }
        exchange(nums, start, right);

        quickSort(nums, start, right-1);
        quickSort(nums, right+1, end);
    }

    private void exchange(int arr[], int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
