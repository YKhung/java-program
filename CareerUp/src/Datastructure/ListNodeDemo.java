package Datastructure;

import java.util.Random;
import java.util.Stack;

public class ListNodeDemo {
    private static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    public static void main(String[] args) {
    	ListNode head = createList(10,10);
		printList(head);
		//System.out.println("Length:"+getListLength(head));
		//GetKthNodeFromBack(head,5);
		//printList(reverseListRec(head));
//        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(3);
//        ListNode n4 = new ListNode(4);
//        ListNode n5 = new ListNode(5);
//        ListNode n6 = new ListNode(6);
//        
//        n1.next=n2;
//        n2.next=n3;
//        n3.next=n4;
//        n4.next=n5;
//        n5.next=n6;
//        n6.next=n2;
//		hasCycle(n1);
		
		reversePrintListRev(head);

    }
    // create a new linkedlist, num is the number of Linkedlist, size is the range of value of each element of Linkedlist.
    public static ListNode createList(int num, int size) {
    	Random random = new Random();
    	ListNode tem = null;
    	ListNode head = null;
		for(int i = 0; i < num; i++){
			if(i == 0){
				tem = new ListNode(random.nextInt(size));
				head =tem;
			} else {
			tem.next= new ListNode(random.nextInt(size));
			tem=tem.next;
			}
		}
		return head;
    }
    //Print List
    public static void printList(ListNode head) {  
        while (head != null) {
            System.out.print("["+head.val + "]-> ");         
            head = head.next;
        }
        if(head == null){
        	System.out.print("null");
        }
        
        System.out.println();
    }
    //Return length of linkedList
    public static int getListLength(ListNode head){
    	if(head == null){
    		return 0;
    	}
    	int length = 0;
    	while(head != null){
    		length++;
    		head = head.next;
    	}
		return length;
    	
    }
    /*reverseList the LinkedList
     * We use DummyNode.
     * For example:
     * 1 -> 2 -> 3 -> 4 
     * Step 1:
     *  cur
     *   1 -> 2 -> 3 -> 4 
     * DummyNode        temp              temp                                      DummyNode 
     *  0 ->null        1->null          1->null  //temp.next = DummyNode.next;    0->1 -> null      //DummyNode.next = temp;
     * Step 2:
     *    cur
     *1 -> 2 -> 3 -> 4               
     * DummyNode              temp        temp                                       DummyNode 
     *  0-> 1 -> null        2->null      2->1->null //temp.next = DummyNode.next;   0->2->1-> null  //DummyNode.next = temp;   
     * Step 3:
     *         cur
     *1 -> 2 -> 3 -> 4               
     * DummyNode              temp        temp                                       DummyNode 
     * 0->2->1-> null        3->null      3->2->1->null //temp.next = DummyNode.next;   0->3->2->1-> null  //DummyNode.next = temp;  
     * Step 4:
     *              cur
     *1 -> 2 -> 3 -> 4               
     * DummyNode              temp        temp                                            DummyNode 
     * 0->3->2->1-> null        4->null   4->3->2->1->null //temp.next = DummyNode.next;   0->4->3->2->1-> null  //DummyNode.next = temp;  
     * Fially
     * DummyNode                     DummyNode
     * 0->4->3->2->1-> null         4->3->2->1-> null    //DummyNode=DummyNode.next;
     * */
    public static ListNode reverseList(ListNode head){
    	if(head == null){
    		return null;
    	}
    	ListNode DummyNode = new ListNode(0);
    	ListNode cur = head;
    	while(cur != null){
    		ListNode temp = new ListNode(cur.val);
    		temp.next = DummyNode.next;
    		DummyNode.next = temp;
    		cur=cur.next;
    		
    	}
    	DummyNode=DummyNode.next;
		return DummyNode;
    	
    }
    /* 
    head 
       1 -> 2 -> 3 -> 4 
    
     head 
       1-------------- 
                           | 
              4 -> 3 -> 2                    // Node reHead = reverseListRec(head.next); 
          reHead      head.next 
        
              4 -> 3 -> 2 -> 1               // head.next.next = head; 
          reHead 
           
               4 -> 3 -> 2 -> 1 -> null      // head.next = null; 
          reHead 
*/
    public static ListNode reverseListRec(ListNode head) {
        // if there is no node, or only one node, just return;
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode reHead = reverseListRec(head.next); // 先求解子问题。
        head.next.next = head;  // 将head 与被解决的子串的尾部相连。
        head.next = null;
        
        return reHead;
    }
    // Get the k node from tail.
    public static void GetKthNodeFromBack(ListNode head, int k) {
    	if(head == null){
    		System.out.println("null");
    	}
    	ListNode fast = head;
    	while(k > 0){
    		if(fast == null){
    			//K > size of List
    			System.out.println("null");
    		}
    		fast = fast.next;
    		k--;
    	}
    	while(fast != null){
    		fast = fast.next;
    		head = head.next;
    	}
    	System.out.println("The Kth node from back :"+head.val);
    	
    }
    // Get the k Node from tail; Using Rec;
    public static int reGetKthNodeRec2(ListNode head, int k) {
        if (head == null) {
            return 0;
        }        
        
        int len = reGetKthNodeRec2(head.next, k);
        if (len == k - 1) {
            System.out.println(head.val);
        }
        
        return len + 1;
    }
    //Find the Cycle Point.
    public static void hasCycle(ListNode head) {

        
        ListNode slow = head;   
        ListNode fast = head;   
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) { // Meet, Have cycle
                break;
            }
        }
        slow = head;
        while(slow != fast){
        	slow = slow.next;
        	fast = fast.next;
        }
        
        System.out.println("Cycle point is :" +slow.val);
        
    }
    /*
     *  Find the middle of LinkedList: getMiddleNode
     *  
     *  We use to points, fast and slow
     *  fast moves two step
     *  slow moves one step
     *  
     *  
     *  
     *  
     *  corner:
     *  n = 1，get out of while.
     *
     *  1->2->3->4
     *  return 2
     */ 
    public static ListNode getMiddleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;            
        }
        
        return slow;
    }
    /* reversePrintListStack
     * */
    public static void reversePrintListStack(ListNode head) {
        if (head == null) {
            return;
        }
        
        System.out.println();
        
        Stack<Integer> s = new Stack<Integer>();
        while (head != null) {
            s.push(head.val);
            head = head.next;
        }
        
        // print the next first.
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
    /* reversePrintListStack use rec.
     * */
    public static ListNode reversePrintListRev(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode rev = reversePrintListRev(head.next);
        
        System.out.println(head.val);
        return rev;
        

    }
    // delete
    public static void Delete(ListNode head, ListNode toBeDeleted) {
        if (head == null) {
            return;
        }
        
        if (toBeDeleted.next != null) {
            toBeDeleted.val = toBeDeleted.next.val;
            toBeDeleted.next = toBeDeleted.next.next;
        } else {
            while (head != null) {
                if (head.next == toBeDeleted) {
                    head.next = toBeDeleted.next;
                    return;
                }
                head = head.next;
            }
        }
        
        return;
    }
    //mergeLink
    public static ListNode mergeLink (ListNode aLink, ListNode bLink) {
        ListNode dummy = new ListNode(0);

        ListNode root = dummy;

        while (aLink != null && bLink != null) {
            if (aLink.val < bLink.val) {
                dummy.next = aLink;
                dummy = aLink;
                aLink = aLink.next;
                
            } else {
                dummy.next = bLink;
                dummy = bLink;
                bLink = bLink.next;
                
            }       
        }

        if (aLink != null) {
            dummy.next = aLink;
        } else {
            dummy.next = bLink;
        }

        return root.next;
    }
    public static boolean isIntersect(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return false;
        }
        
        ListNode head1C = hasCycleRetNode(head1);
        ListNode head2C = hasCycleRetNode(head2);
        
        // 两个链表都是有环的。
        if (head1C != null && head2C != null) {
            ListNode tmp = head1C;
            do {
                // 放在前面判断，因为有可能当前节点就是要求的结果
                if (head1C == head2C) {
                    return true;
                }
                
                // 跑一圈来找是不是同一个圈。
                head1C = head1C.next;
            } while (tmp != head1C);
            
            return false;
        // 两个链表都是没有环的
        } else if (head1C == null && head2C == null){
            while (head1.next != null) {
                head1 = head1.next;
            }
            
            while (head2.next != null) {
                head2 = head2.next;
            }
            
            // 无环的话，应该具有相同的尾节点.
            return head1 == head2;
        } else {
            return false;
        }
    }
    
    /**
     * 如果有环，返回在环内的某节点。否则返回null 
     */
    public static ListNode hasCycleRetNode(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode s = head;
        ListNode f = head;
        
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                return f;
            }
        }
        
        return null;
    }
}
