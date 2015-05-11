package chapter_2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Datastructure.*;
//implement an algorithm to find the kth to last element of a singly linked list
public class Question2_2 {
	private static SLinkedList list = new SLinkedList();
	private static Integer i = 0;
	private static Integer i1 = 0;
	public static void main(String[] args) {
		// case 2 : one-value array
		List<Integer> array = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(10));
		list.add(array);
		System.out.println("Output list : " + list);
		FindKth(list.head,3);
		System.out.println(FindKth2(list.head,3).getElement());
		System.out.println(FindKth3(list.head,3).getElement());

		// case 3 : random array
		array.clear();
		list.clear();
		i=0;
		i1=0;
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(5));
		list.add(array);
		System.out.println("Output list : " + list);
		FindKth(list.head,3);
		System.out.println(FindKth2(list.head,3).getElement());
		System.out.println(FindKth3(list.head,3).getElement());
	}
	public static int FindKth(SNode head, Integer k){
		if(head==null){
			return 0;
		}
		i=FindKth(head.getNext(),k)+1;
		if(i==k){
			System.out.println(head.getElement());
		}
	
		return i;
	}
	public static SNode FindKth2(SNode head, Integer k){
        SNode first=head;
        SNode second=first;
        for(int j=0;j<k-1;j++){
        	if(second==null)return null;
        	second=second.getNext();	
        }
        if(second==null)return null;
        
        while(second.getNext()!=null){
        	second=second.getNext();
        	first=first.getNext();
        	
        }
        
        return first;
        
	}
	public static SNode FindKth3(SNode head, Integer k){
		if(head==null){
			return null;
		}
		SNode node=FindKth3(head.getNext(),k);
		i1=i1+1;
		if(i1==k){
			return head;
		}
	
		return node;
        
	}
}
