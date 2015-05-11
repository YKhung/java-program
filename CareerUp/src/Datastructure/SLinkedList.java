package Datastructure;

import java.util.List;

public class SLinkedList {
public SNode head; 
protected SNode tail;
public int size;

public SLinkedList() {
head = null;
tail= null;
size =0; 
} 
public void add(List<Integer> value) {
	for(Integer elem : value) {
		SNode newNode = new SNode (elem,null);
		if(tail != null)
			tail.setNext(newNode);
		size++;
		tail = newNode;
		if(head == null)
			head = tail;
	}
}

public SLinkedList reverse() {
	SLinkedList list=new SLinkedList();
	SNode prehead = new SNode(0,null);
	SNode cur = head;
	while(cur != null) {
		// insert cur
        SNode tmp = new SNode(cur.getElement(),null);
        tmp.next = prehead.next;
        prehead.next = tmp;
        cur = cur.next;
	}
	prehead = prehead.next;
	list.head=prehead;
	return list;
	
}
public void clear() {
	head = null;
	tail = null;
}
public String toString() {
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
