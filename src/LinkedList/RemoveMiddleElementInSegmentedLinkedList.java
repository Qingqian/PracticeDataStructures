package LinkedList;

/*	Question: http://www.geeksforgeeks.org/given-linked-list-line-segments-remove-middle-points
 *  Author : vzhao
 *  Hint: Instead of tracking the next node, we can just compare the current node and the next.next node
 * */

public class RemoveMiddleElementInSegmentedLinkedList {
	public ListNode removeMiddleElements(ListNode head) {
		if(head == null || head.next == null || head.next.next == null) 
			return head;
		ListNode current = head;
		ListNode next = head.next;
		ListNode nextNext = head.next.next;
		while(nextNext != null) {
			// we should replace it with x-coordinate and y-coordinate
			if(current.val == nextNext.val) {
				current.next = nextNext;
				next = nextNext;
				nextNext = nextNext.next;
			} else {
				current = current.next;
				next = next.next;
				nextNext = nextNext.next;
			}
		}
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
		
		print(head);
		
	}
}
