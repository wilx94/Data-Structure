package models;



import java.io.PrintStream;

public class DoublyLinkedQueue<E> implements Queue<E> {
	
	private class Node {
		private E value;
		private Node next;
		private Node prev;
		
		public Node() {
			super();
		}
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrev() {
			return prev;
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}
	
	private int currentSize;
	
	private Node header;
	
	private Node tail;
	
	public DoublyLinkedQueue(){
		this.currentSize =0;
		
		this.header = new Node();
		
		this.tail = new Node();
		
		this.header.setNext(this.tail);
		this.tail.setPrev(this.header);
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void makeEmpty() {
		while (!this.isEmpty()){
			this.dequeue();
		}
	}

	@Override
	public E front() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.header.getNext().getValue();
		}
	}

	@Override
	public E dequeue() {
		if (this.isEmpty()){
			return null;
		}
		else {
			E result = this.header.getNext().getValue();
			this.header.getNext().getNext().setPrev(this.header);
			Node temp = this.header.getNext();
			this.header.setNext(this.header.getNext().getNext());
			temp.setNext(null);
			temp.setPrev(null);
			temp.setValue(null);
			this.currentSize--;
			return result;
		}
	}

	@Override
	public void enqueue(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null");
		}
		Node newNode = new Node();
		newNode.setValue(obj);
		newNode.setNext(this.tail);
		newNode.setPrev(this.tail.getPrev());
		this.tail.getPrev().setNext(newNode);
		this.tail.setPrev(newNode);
		this.currentSize++;
	}

	@Override
	public void print(PrintStream out) {
		for (Node temp = header.getNext(); temp != tail; temp = temp.getNext()){
			out.println(temp.getValue());
		}

	}

}
