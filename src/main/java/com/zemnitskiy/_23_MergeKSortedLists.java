package com.zemnitskiy;

/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

        Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
        [
        1->4->5,
        1->3->4,
        2->6
        ]
merging them into one sorted list:
        1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 104
        0 <= lists[i].length <= 500
        -104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class _23_MergeKSortedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode listNode = new _23_MergeKSortedLists().mergeKLists(new ListNode[]{l1, l2, l3});
        System.out.println(listNode);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        ListNode firstNode = new ListNode(0);
        ListNode current = firstNode;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        return firstNode.next;
    }
}

final class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode currentNode = this;
        while (currentNode != null) {
            sb.append(currentNode.val);
            currentNode = currentNode.next;
        }
        return sb.toString();
    }
}
