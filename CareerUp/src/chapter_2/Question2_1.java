package chapter_2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import Datastructure.*;

//Wirte code to remove duplicate from an unsosrted linked list
//if a temporary buffer is not allowed.
public class Question2_1 {
	private static SLinkedList list = new SLinkedList(); 
	public static void main(String[] args) {
		
		// case 1 : empty list
		RemoveDuplicate2();
		System.out.println("Input list - empty");
		System.out.println("Output list : " + list);
		// case 2 : one-value array
		List<Integer> array = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(1));
		System.out.println("Input list : " + array);
		list.add(array);
		removeDuplicate();
		System.out.println("Output list : " + list);
		// case 3 : random array
		array.clear();
		list.clear();
		for(int i = 0; i < 20; i++)
			array.add(random.nextInt(5));
		System.out.println("Input list : " + array);
		list.add(array);
		RemoveDuplicate2();
		System.out.println("Output list : " + list);
	}
 
	public static void removeDuplicate() {

        Hashtable<Integer, Boolean> hashtable = new Hashtable<Integer, Boolean>();  
        SNode pre = list.head;  
        SNode cur = pre;  
          
        while (cur != null)  
        {  
            // If find a duplicate, bypass current node  
            if (hashtable.containsKey(cur.getElement()))  
                pre.setNext(cur.getNext());  
            else  
            {  
            // If no duplicate, add current node data to hash table, and then modify pointer  
                hashtable.put(cur.getElement(), true);  
                pre = cur;  
            }  
              
            cur = cur.getNext();  
        }
	} 
    public static void RemoveDuplicate2()  
    {  
        SNode pre = list.head;  
        SNode cur = pre;  
          
        while (cur != null)  
        {  
            // Use a temporary Node to iterate all prior nodes  
            SNode temp = list.head;  
            for (; temp.getNext() != cur; temp = temp.getNext())  
                if (temp.getElement() == cur.getElement())  
                    break;  
              
            // If temp.next doesn't equal to cur; then we find a duplicate, bypass current node  
            if (temp.getNext() != cur)  
                pre.setNext(cur.getNext());  
            else  
                pre = cur;  
              
            cur = cur.getNext();  
        }  
    } 	
	

    public static void PrintList()  
    {  
        SNode temp = list.head;  
        while (temp.getNext() != null)  
        {  
            temp = temp.getNext();  
            System.out.print(temp.getElement() + " ");  
        }  
          
        System.out.println();  
    }
}
