package com.hquery.algorithm.leetcode;

/**
 * @author hquery.huang
 * 2019/5/24 12:00:37
 */
public class Solution203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode newNode = head;
        while (head != null) {
            // 删除自身节点
            if (head.val == val) {
                newNode = head.next;
                head = head.next;
                continue;
            }
            // 下一个节点是删除节点
            if (head.next != null && head.next.val == val) {
                if (head.next.next != null) {
                    head.next = head.next.next;
                } else {
                    head.next = null;
                }
            } else if (head.next == null) {
                break;
            } else {
                head = head.next;
            }
        }
        return newNode;
    }

    /**
     * 别人的优雅实现
     *
     * @param head
     * @param val
     * @return com.hquery.algorithm.leetcode.ListNode
     * @author hquery
     * 2019/5/24 12:28:29
     */
//    public ListNode removeElements(ListNode head, int val) {
//        ListNode header = new ListNode(-1);
//        header.next = head;
//        ListNode cur = header;
//        while (cur.next != null) {
//            if (cur.next.val == val) {
//                cur.next = cur.next.next;
//            } else {
//                cur = cur.next;
//            }
//        }
//        return header.next;
//    }

    public static void main(String[] args) {
        ListNode node = new ListNode(7);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode listNode = new Solution203().removeElements(node, 7);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
