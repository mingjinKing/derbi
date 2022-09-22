package com.derbi.prac;

import com.derbi.prac.entity.ListNode;
import org.junit.Test;

public class ListTest {


    @Test
    public void testRemoveElements(){

        ListNode node4 = new ListNode(6, null);
        ListNode node3 = new ListNode(7,node4 );
        ListNode node2 = new ListNode(6, node3);
        ListNode node1 = new ListNode(3, node2);
        ListNode head = new ListNode(1, node1);
        ListNode result = removeElements(head, 6);
        while (result.getNext() != null){
            System.out.print(result.getVal());
            result = result.getNext();
        }
    }


    /**
     * 题意：删除链表中等于给定值 val 的所有节点。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.getVal() == val){
            head = head.getNext();
        }
        if(head == null){
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.getNext();
        while(cur != null){
            if(cur.getVal() == val){
                pre.setNext(cur.getNext());
            }else{
                pre = cur;
            }
            cur = cur.getNext();
        }
        return head;
    }

}
