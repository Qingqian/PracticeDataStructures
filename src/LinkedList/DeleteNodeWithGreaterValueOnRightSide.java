package LinkedList;

import java.util.ArrayList;
/**
 * 	Question: http://www.geeksforgeeks.org/delete-nodes-which-have-a-greater-value-on-right-side/
 * 	Author : vzhao
 * */

public class DeleteNodeWithGreaterValueOnRightSide {
	public ListNode deleteNodes(ListNode head) {
		ArrayList<Integer> max = new ArrayList<Integer>();
		max.add(Integer.MIN_VALUE);
		return deleteNodes(head, max);
	}
	public ListNode deleteNodes(ListNode head, ArrayList<Integer> max) {
		if(head == null)
			return null;
		ListNode next = deleteNodes(head.next, max);
		if(head.val > max.get(0)) {
			max.add(0,head.val);
		} else {
			return next;
		}
		head.next = next;
		return head;
	}
	
	public static void print(ListNode head) {
		ListNode current = head;
		while(current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		//12->15->10->11->5->6->2->3
		ListNode head = new ListNode(12);
		head.next = new ListNode(15);
		head.next.next = new ListNode(10);
		head.next.next.next = new ListNode(11);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(2);
		head.next.next.next.next.next.next.next = new ListNode(3);
		
		DeleteNodeWithGreaterValueOnRightSide object = new DeleteNodeWithGreaterValueOnRightSide();
		head = object.deleteNodes(head);
		print(head);
		
	}
}
