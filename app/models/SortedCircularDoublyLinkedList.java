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

		private Node() {
			this.data = null;
			this.next = this;
			this.prev = this;
		}

		public E getData() {
			return this.data;
		}

		public void setData(E data) {
			this.data = data;
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

	private Node header;
	private int currentSize;

	public SortedCircularDoublyLinkedList() {
		this.header = new Node();
		this.currentSize = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new SortedCircularLinkedListIterator();
	}

	@Override
	public boolean add(E obj) {

		ParameterCheck.checkNull(obj);

		Node newNode = new Node();
		newNode.setData(obj);

		if (this.size() == 0) {
			this.header.setNext(newNode);
			this.header.setPrev(newNode);

			newNode.setNext(this.header);
			newNode.setPrev(this.header);

			this.currentSize++;
			return true;
		}

		Node temp;

		for (temp = this.header.getNext(); !temp.equals(this.header)
				&& !(temp.getData().compareTo(obj) > 0); temp = temp.getNext())
			;

		temp.getPrev().setNext(newNode);
		newNode.setPrev(temp.getPrev());

		newNode.setNext(temp);
		temp.setPrev(newNode);

		this.currentSize++;

		return true;

	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {

		for (Node temp = this.header.getNext(); !temp.equals(this.header); temp = temp
				.getNext()) {
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

	@Override
	public int removeAll(E obj) {
		int count = 0;

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

	@Override
	public E first() {
		return this.header.getNext().getData();
	}

	@Override
	public E last() {
		return this.header.getPrev().getData();
	}

	@Override
	public E get(int index) {

		ParameterCheck.checkSGRRange(this.size(), index);
		Node temp = this.header.getNext();
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}

		return temp.getData();

	}

	@Override
	public void clear() {
		while (!this.isEmpty() && this.remove(0))
			;

	}

	@Override
	public boolean contains(E e) {
		for (E temp : this) {
			if (temp.equals(e))
				return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public Iterator<E> iterator(int index) {
		
		return new SortedCircularLinkedListIterator(index);
		
		}

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

	@Override
	public int lastIndex(E e) {
		int i = 0;
		E temp;

		while (this.reverseIterator().hasPrevious()) {
			temp = this.reverseIterator().previous();
			if (temp.equals(e))
				return i;
			i++;
		}

		return -1;
	}

	@Override
	public ReverseIterator<E> reverseIterator() {
		return new Reverse();
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new Reverse(index);
	}

	private class Reverse<E> implements ReverseIterator<E> {

		Node currentPosition = new Node();

		private Reverse() {
			this.currentPosition = header.getPrev();
		}

		private Reverse(int index) {
			
			ParameterCheck.checkSGRRange(size(), index);

			this.currentPosition = header.getPrev();
			for (int i = 0; i < index; currentPosition = currentPosition.getNext(),i++)
				;

		}

		@Override
		public boolean hasPrevious() {
			return !this.currentPosition.equals(header);
		}

		@Override
		public E previous() {
			
			E result = (E) this.currentPosition.getData();
			this.currentPosition = this.currentPosition.getPrev();

			return (E) result;
		}

	}

	private class SortedCircularLinkedListIterator<E> implements Iterator<E> {

		Node currentNode;

		private  SortedCircularLinkedListIterator() {

			this.currentNode = header.getNext();
			
		}
		
		private SortedCircularLinkedListIterator(int index){
			
			ParameterCheck.checkSGRRange(size(), index);
			this.currentNode = header.getNext();
			
			for(int i = 0; i < index;i++){
				
				this.currentNode = this.currentNode.getNext();
				
			}
		}
		@Override
		public boolean hasNext() {
			return !this.currentNode.equals(header);
		}

		@Override
		public E next() {
			if(!this.hasNext())
				throw new NoSuchElementException();
			
			E result = (E)this.currentNode.getData();
			
			this.currentNode = this.currentNode.getNext();
			
			
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
