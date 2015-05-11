package chapter_2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//Write code to partition a linked list around a value x, such that all nodes less than
//x come before all nodes greater than or equal to x
import Datastructure.*;
public class Question2_4 {
	private static SLinkedList list = new SLinkedList();
	public static void main(String[] args) {
		// case 2 : one-value array
		List<Integer> array = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(10));
		list.add(array);
		System.out.println("Input list : " + list);
		System.out.println("output list : " + partition(list,4));
		// case 3 : random array
		array.clear();
		list.clear();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(5));
		list.add(array);
		System.out.println("Input list : " + list);
		System.out.println("output list : " + partition(list,2));
	}
	@SuppressWarnings("null")
	public static SLinkedList partition(SLinkedList list,int x){
		SLinkedList before = new SLinkedList();
		SLinkedList after = new SLinkedList();
		SNode beforeE=null;
		SNode afterE=null;
		SNode node=list.head;
		while(node!=null){
			SNode next=node.getNext();
			node.setNext(null);
			if(node.getElement()<x){
				if(before.head==null){
					before.head=node;
					beforeE=before.head;
				}else {
					beforeE.setNext(node);
					beforeE=node;
				}
			}else{
				if(after.head==null){
					after.head=node;
					afterE=after.head;
				}else {
					afterE.setNext(node);
					afterE=node;
				}
			}
			
			node=next;			
		}
		if(before.head==null)
			return after;
		beforeE.setNext(after.head);

		return before;
	}
}
