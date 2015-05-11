package chapter_2;

import java.util.Stack;

import Datastructure.*;

//implement a function to check if a linked list is a palindrome.
public class Question2_7 {
	public static void main(String arg[]){
		SLinkedList list=new SLinkedList();
		SNode head =new SNode(1,null);
        list.head=head;
        head.next = new SNode(2,null);
        head.next.next = new SNode(3,null);
        head.next.next.next = new SNode(3,null);
        head.next.next.next.next = new SNode(2,null);
        head.next.next.next.next.next= new SNode(1,null);
        System.out.println(list.reverse());
        System.out.println(isPalindrome(head));
	}
	static boolean isPalindrome(SNode head){
		SNode fast=head;
		SNode slow=head;
		Stack<Integer> stack =new Stack<Integer>();
		while(fast!=null&&fast.next!=null){
			stack.push(slow.getElement());
			slow=slow.next;
			fast=fast.next.next;
		}
		if(fast!=null){
			slow=slow.next;
		}
		while(slow!=null){
			int top=stack.pop().intValue();
			if(top!=slow.getElement()){
				return false;
			}
			slow=slow.next;
		}
		return true;
	}
	public static String toString(SNode head) {
		StringBuilder result = new StringBuilder();
		for(SNode ptr = head; ptr != null; ptr = ptr.getNext()) {
			result.append("[");
			result.append(ptr.getElement());
			result.append("] -> ");
		}
		result.append("<null>");
		return result.toString();
	}
}
