package com.example.queue;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	   private Node first;
	   private Node last;
	
	
	   private class Node{
		   private Item item;
		   private Node next;
		   private Node prev;

	   }
	
	   public Deque() {                          // construct an empty deque
		   first = null;
		   last = null;
	   }
	   
	   
	   
	   public boolean isEmpty()  {               // is the deque empty?
		   
		   return first == null;
	   }
	   
	   
	   public int size()   {                     // return the number of items on the deque
		   int count = 0;
		   Node x = first;
		   while(x!=null) {
			   count++;
			   x=x.next;
			   
		   }
		   return count;
	   }
	   
	   
	   public void addFirst(Item item) {          // add the item to the front

		   if(!isEmpty()) {
			   Node oldfirst = first;
			   first = new Node();
			   first.item = item;
			   first.next = oldfirst;
			   oldfirst.prev = first;
		   }
		   else {
			   first = new Node();
			   first.item = item;
			   last = first;
		   }

	   }
	   
	
	   public void addLast(Item item) {          // add the item to the end
	 
		   if(!isEmpty()) {
			   Node newlast = new Node();
			   
			   newlast.item = item;
			   newlast.next=null;
			   last.next=newlast;
			   newlast.prev=last;
			   
			   last=newlast;
		   }
		   else {
			   last = new Node();
			   last.item = item;
			   first = last;
		   }

	   }
	   
	   
	   public Item removeFirst() {               // remove and return the item from the front
		   if(!isEmpty()) {
			   Item item = first.item;
			   first = first.next;
			   return item;
			   
		   }
		   else {
			   return null;
			   
		   }
		   
		   
	   }
	   public Item removeLast() {                // remove and return the item from the end
		   if(!isEmpty()) {
			   Item item = last.item;
			   last.prev.next=null;
			   last = last.prev;
			   return item;
			   
		   }
		   else {
			   return null;
			   
		   }
	   }
	   public Iterator<Item> iterator() {		   // return an iterator over items in order from front to end
		   return new DequeIterator();
	   }
	   
	   private class DequeIterator implements Iterator<Item>{
		   private Node current = first;
		   
		   public boolean hasNext() { return current != null;}
		   public void remove() { /*not supported */}
		   public Item next()
		   {
			   Item item = current.item;
			   current = current.next;
			   return item;
		   }
	   }
	   
	   public void printq() {
       Node x = first;     
		   while(x!=null) {
			   System.out.println(x.item);   
			   x = x.next;
		   }
	   }
	   
	   public static void main(String[] args) {
		   System.out.println("Hello, World");
		   Deque<Integer> q = new Deque<Integer>();
		   q.addFirst(1);
		   q.addFirst(2);
		   q.addFirst(3);
		   //q.printq();
		   q.addLast(1);
		   q.addLast(2);
		   q.addLast(3);
		   //q.printq();
		   int a;
		   a = q.removeFirst();
		   a = q.removeLast();
		   q.printq();
		   System.out.println();
		   System.out.println(q.size());
		   System.out.println();
		   for(Integer x : q) {
			   System.out.println(x);  
		   }

	   }
	}
