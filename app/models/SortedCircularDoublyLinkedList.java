package models;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.ece.icom4035.util.ParameterCheck;



public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements
		SortedList<E> {

	private class Node {

		private E data;
		private Node next;
		private Node prev;

		/**
		 * Node of the linked List
		 */
		private Node() {
			this.data = null;
			this.next = this;
			this.prev = this;
		}

		/**
		 * Return the date
		 * @return - E
		 */
		public E getData() {
			return this.data;
		}
		/**
		 * Set the data.
		 * @param data - E
		 */
		public void setData(E data) {
			this.data = data;
		}
		/**
		 * Return the next node
		 * @return - Node
		 */
		public Node getNext() {
			return next;
		}
		/**
		 * Set the next node
		 * @param next - Node
		 */
		public void setNext(Node next) {
			this.next = next;
		}
		/**
		 * Get previous Node
		 * @return - Node
		 */
		public Node getPrev() {
			return prev;
		}
		/**
		 * Set the previous node.
		 * @param prev - Node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;
		}

	}

	private Node header;
	private int currentSize;

	/**
	 * Create an Objet of SortedCircularDoublyLinkedList
	 */
	public SortedCircularDoublyLinkedList() {
		this.header = new Node();
		this.currentSize = 0;
	}

	/**
	 * Return the iterator of this SortedCircularDoublyLinkedList.
	 */
	@Override
	public Iterator<E> iterator() {
		return new SortedCircularLinkedListIterator();
	}

	/**
	 * Add the new Node in sorted Order.
	 */
	@Override
	public boolean add(E obj) {

		ParameterCheck.checkNull(obj);

		Node newNode = new Node();
		newNode.setData(obj);

		// if is the first node 
		if (this.size() == 0) {
			this.header.setNext(newNode);
			this.header.setPrev(newNode);

			newNode.setNext(this.header);
			newNode.setPrev(this.header);

			this.currentSize++;
			return true;
		}

		
		// if is not the first node
		Node temp;
		
		// run until find the node that is bigger than the one that you want to add
		for (temp = this.header.getNext(); !temp.equals(this.header)
				&& !(temp.getData().compareTo(obj) > 0); temp = temp.getNext())
			;

		//add it
		temp.getPrev().setNext(newNode);
		newNode.setPrev(temp.getPrev());

		newNode.setNext(temp);
		temp.setPrev(newNode);

		this.currentSize++;

		return true;

	}
	/**
	 * Return the size of the SortedCircularDoublyLinkedList
	 */
	@Override
	public int size() {
		return this.currentSize;
	}

	/**
	 * Remove a Node in this list
	 */
	@Override
	public boolean remove(E obj) {

		// Run above all the list if is necessary
		for (Node temp = this.header.getNext(); !temp.equals(this.header); temp = temp
				.getNext()) {
			
			//if the object is found erase it and finish
			if (temp.getData().equals(obj)){

				temp.getPrev().setNext(temp.getNext());
			temp.getNext().setPrev(temp.getPrev());

			temp.setData(null);
			temp.setNext(null);
			temp.setPrev(null);
			
			this.currentSize--;

			return true;
			}

		}

		return false;
	}

	/**
	 * Remove the object in the index
	 */
	@Override
	public boolean remove(int index) {

		ParameterCheck.checkSGRRange(this.size(), index);
		int i = 0;
		Node temp = this.header.getNext();

		
		while (i < index) {
			i++;
			temp = temp.getNext();
		}

		temp.getPrev().setNext(temp.getNext());
		temp.getNext().setPrev(temp.getPrev());

		temp.setData(null);
		temp.setNext(null);
		temp.setPrev(null);
		
		
		this.currentSize--;

		return true;

	}

	/**
	 * Remove all the E objects.
	 */
	@Override
	public int removeAll(E obj) {
		int count = 0;

		//Run the list from begining to end.
		for (Node temp = this.header.getNext(); !temp.equals(this.header); temp = temp
				.getNext()) {
			if (temp.getData().equals(obj)) {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());

				temp.setData(null);
				temp.setNext(null);
				temp.setPrev(null);

				count++;

			}
		}

		return count;
	}

	/**
	 * Return the first element in the list
	 */
	@Override
	public E first() {
		return this.header.getNext().getData();
	}
	/**
	 * Return the last element in the list.
	 */
	@Override
	public E last() {
		return this.header.getPrev().getData();
	}

	/**
	 * Return the element in a especific index.
	 */
	@Override
	public E get(int index) {

		ParameterCheck.checkSGRRange(this.size(), index);
		Node temp = this.header.getNext();
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}

		return temp.getData();

	}

	/**
	 * Clear the list.
	 */
	@Override
	public void clear() {
		while (!this.isEmpty() && this.remove(0))
			;

	}

	/**
	 * Check if is member
	 */
	@Override
	public boolean contains(E e) {
		for (E temp : this) {
			if (temp.equals(e))
				return true;
		}
		return false;
	}

	/** 
	 * Check if is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Return the iterator of the list.
	 */
	@Override
	public Iterator<E> iterator(int index) {
		
		return new SortedCircularLinkedListIterator(index);
		
		}

	/**
	 * Return the first index of an element.
	 */
	@Override
	public int firstIndex(E e) {
		int i = 0;

		for (E temp : this) {

			if (temp.equals(e))
				return i;

			i++;
		}
		return -1;
	}

	/**
	 * Return the last index of an element
	 */
	@Override
	public int lastIndex(E e) {
		int i = 0;
		E temp;

		//run backward until find the element or didn't
		while (this.reverseIterator().hasPrevious()) {
			temp = this.reverseIterator().previous();
			if (temp.equals(e))
				return i;
			i++;
		}

		return -1;
	}

	/**
	 * REturn the reverse iterator
	 */
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new Reverse();
	}

	/**
	 * REverse iterator begining in a especific number
	 */
	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new Reverse(index);
	}

	/**
	 * The class who implement the reverse iterator
	 * @author leno-nex
	 *
	 * @param <E>
	 */
	private class Reverse<E> implements ReverseIterator<E> {

		Node currentPosition = new Node();

		/**
		 * contructor of a class
		 */
		private Reverse() {
			this.currentPosition = header.getPrev();
		}

		private Reverse(int index) {
			
			ParameterCheck.checkSGRRange(size(), index);

			this.currentPosition = header.getPrev();
			for (int i = 0; i < index; currentPosition = currentPosition.getNext(),i++)
				;

		}

		/**
		 * Return true if have previous
		 */
		@Override
		public boolean hasPrevious() {
			return !this.currentPosition.equals(header);
		}

		/**
		 * Return the previous element
		 */
		@Override
		public E previous() {
			
			E result = (E) this.currentPosition.getData();
			this.currentPosition = this.currentPosition.getPrev();

			return (E) result;
		}

	}
		/**
		 * A class that implement the iterator 
		 * @author leno-nex
		 *
		 * @param <E>
		 */
	private class SortedCircularLinkedListIterator<E> implements Iterator<E> {

		Node currentNode;

		/**
		 * Constructor of a class
		 */
		private  SortedCircularLinkedListIterator() {

			this.currentNode = header.getNext();
			
		}
		
		/**
		 * Conructor from a especific index.
		 * @param index
		 */
		private SortedCircularLinkedListIterator(int index){
			
			ParameterCheck.checkSGRRange(size(), index);
			this.currentNode = header.getNext();
			
			for(int i = 0; i < index;i++){
				
				this.currentNode = this.currentNode.getNext();
				
			}
		}
		/**
		 * Return true if have previous.
		 */
		@Override
		public boolean hasNext() {
			return !this.currentNode.equals(header);
		}

		/**
		 * Return the next element.
		 */
		@Override
		public E next() {
			if(!this.hasNext())
				throw new NoSuchElementException();
			
			E result = (E)this.currentNode.getData();
			
			this.currentNode = this.currentNode.getNext();
			
			
			return result;
		}

		/**
		 * Is unsupported.
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
