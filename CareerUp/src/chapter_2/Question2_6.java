package chapter_2;

//Given a circular linked list, implement an algorithm which returns the node at the beginning of the loop;


import Datastructure.*;
public class Question2_6 {
    // time, if there is no circular, it will never stop. else n
    // space, 1
    public static SNode detectcircular(SNode head) {
        SNode slow = head;
        SNode fast = head;
 
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if(fast==null||fast.next==null){
        	return null;
        }
        fast = slow;
        slow = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
 
    public static void main(String[] args) {
    	SLinkedList list = new SLinkedList();
    	list.head=new SNode(1,null);
        SNode head = list.head;
        head.next = new SNode(2,null);
        head.next.next = new SNode(3,null);
        head.next.next.next = new SNode(4,null);
        head.next.next.next.next= new SNode(5,null);
        head.next.next.next.next.next = head.next.next;
        System.out.println(detectcircular(head).getElement());
    }
 
}