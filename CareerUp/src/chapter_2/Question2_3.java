package chapter_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Datastructure.*;

public class Question2_3 {
	private static SLinkedList list = new SLinkedList();
	public static void main(String[] args) {
		// case 2 : one-value array
		List<Integer> array = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(10));
		list.add(array);
		System.out.println("Input list : " + list);
		deleteMiddle(list.head.getNext().getNext().getNext());
		System.out.println("Output list : " + list);

		// case 3 : random array
		array.clear();
		list.clear();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(5));
		list.add(array);
		System.out.println("Input list : " + list);
		deleteMiddle(list.head.getNext().getNext().getNext());
		System.out.println("Output list : " + list);
	}
	public static void deleteMiddle(SNode n){

	        if (n == null || n.getNext() == null)  
	            return ;  
	          
	        SNode temp = n.getNext();  
	        n.setElement(temp.getElement());  
	        n.setNext(temp.getNext());  
	    
		
	}
}
