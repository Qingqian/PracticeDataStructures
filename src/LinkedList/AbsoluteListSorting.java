package LinkedList;

/*
 * Question : http://www.geeksforgeeks.org/sort-linked-list-already-sorted-absolute-values/
 * Author : vzhao
 */
public class AbsoluteListSorting {
	//This takes linear time and constant space
	public ListNode sortedListWithAbsoluteValues(ListNode head)
	{
		if(head == null || head.next == null)
		    return head;
		ListNode current = head.next;
		ListNode previous = head;
		while(current != null) {
			ListNode next = current.next;
		    if(current.val < previous.val) {
		        previous.next = next;
		        current.next= head;
		        head = current;
		    } else {
		        previous = current;
		    }
		    current = next;
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
		// 1 -> -2 -> -3 -> 4 -> -5 -> -6 -> -7 -> -8
		ListNode head = new ListNode(1);
		head.next = new ListNode(-2);
		head.next.next = new ListNode(-3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(-6);
		head.next.next.next.next.next.next = new ListNode(-7);
		head.next.next.next.next.next.next.next = new ListNode(-8);
		
		AbsoluteListSorting object = new AbsoluteListSorting();
		head = object.sortedListWithAbsoluteValues(head);
		print(head);
		
	}
}
