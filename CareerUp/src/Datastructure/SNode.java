package Datastructure;

public class SNode {
private Integer element; 
public SNode next;

public SNode(Integer s, SNode n) {
element =s;
next = n;
}

public Integer getElement() { return element; }
public SNode getNext() { return next; }


public void setElement(Integer newElem) { element = newElem; }

public void setNext(SNode newNext) {next = newNext;}
}