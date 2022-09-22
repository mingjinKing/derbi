package com.derbi.prac.entity;

import lombok.Data;

/**
 * 链表节点
 */
@Data
public class ListNode {
    int val;
    ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

