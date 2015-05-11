package chapter_2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//You have two numbers represented by a linked list, where each node contains a
//single digit. The digits are stored in reverse order, such that the Ts digit is at the
//head of the list. Write a function that adds the two numbers and returns the sum
//as a linked list
import Datastructure.*;

public class Question2_5 {
	
	private static SLinkedList result = new SLinkedList();
	public static Question2_5 a=new Question2_5();
	public static void main(String[] args) {
		// case 2 : one-value array
		
		SLinkedList list = new SLinkedList();
		SLinkedList list2 = new SLinkedList();
		List<Integer> array = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 5; i++)
			array.add(random.nextInt(10));
		list.add(array);
		array.clear();
		for(int i = 0; i < 3; i++)
			array.add(random.nextInt(10));
		list2.add(array);
		System.out.println("Input list : " + list+list2);
		System.out.println("output list : " + addtwonumber2(list,list2));
		// case 3 : random array
		array.clear();
		list.clear();

	}
	

	public static SLinkedList addtwonumber(SLinkedList list,SLinkedList list2){
		SLinkedList dummylist = new SLinkedList();
		SNode dummy = null;
		int carry=0;
		SNode l=list.head;
		SNode l2=list2.head;
		dummylist.head=dummy;
		System.out.println("Input list : " + list.size);
		while(l!=null||l2!=null||carry!=0){
			int val=carry;
			if(l!=null){
				val+=l.getElement();
				l=l.getNext();
				
			}
			if(l2!=null){
				val+=l2.getElement();
				l2=l2.getNext();
				
			}
			SNode node = new SNode(val%10,null);
			carry=val/10;
			if(dummylist.head==null){
				dummylist.head=node;
				dummy=dummylist.head;
			}
			else{
			dummy.setNext(node);
			dummy=node;}
			
		}
		return dummylist;
		
	}
	public static SLinkedList addtwonumber2(SLinkedList list,SLinkedList list2){
		if(list==null&& list2==null){
			return null;
		}
		int len=list.size;
		int len2=list2.size;
		
		if(len<len2){
			list=padList(list,len2-len);}
		else {
			list2=	padList(list2,len-len2);
			}
		
		SNode l=list.head;
		SNode l2=list2.head;
		temp dummy=null;
			int val=0;
			if(l!=null){
				val+=l.getElement();				
			}
			if(l2!=null){
				val+=l2.getElement();
				
			}
			
			if(l!=null||l2!=null){
				dummy = addtwonumber3(l==null?null:l.getNext(),l2==null?null:l2.getNext());
			}			
			SNode node = new SNode(val%10+dummy.carry,null);
				node.setNext(dummy.node);
				result.head=node;

			
		
		return result;
		
	}
	public static SLinkedList padList(SLinkedList l,int i){
		SNode head=l.head;
		for(int j=0;j<i;j++){
			SNode node=new SNode(0,head);
			head=node;
		}
		l.head=head;
		return l;
		
	}
	public static temp addtwonumber3(SNode l,SNode l2){
		if(l==null&& l2==null){
			return a.new temp();
		}
		temp dummy=null;

			if(l!=null||l2!=null){
				dummy = addtwonumber3(l==null?null:l.getNext(),l2==null?null:l2.getNext());
			}
			int val=dummy.carry;

				if(l!=null){
					val+=l.getElement();				
				}
				if(l2!=null){
					val+=l2.getElement();
					
				}
				SNode node=new SNode(val%10,null);
				node.setNext(dummy.node);
				dummy=a.new temp(val/10, node);
			
			
		
		return dummy;
		
	}
	public class temp {
		public Integer carry; 
		public SNode node;
		public temp() {
			carry =0;
			node = null;
		}
		public temp(Integer s, SNode n) {
			carry =s;
			node = n;
		}}
	
}
