package models;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
	
	private class Node {
		private E value;
		private Node next;
		
		public Node(){
			this.value = null;
			this.next = null;
					
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
		
	}
	
	private class ListIterator implements Iterator<E>{
		private Node nextNode;
		
		public ListIterator(){
			this.nextNode = header.getNext();
		}
		
		@Override
		public boolean hasNext() {
			return this.nextNode != null;
		}

		@Override
		public E next() {
			if (this.hasNext()){
				E result = nextNode.getValue();
				nextNode = nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	
		
	}
	
	private int currentSize;
	private Node header;
	
	public LinkedList(){
		this.currentSize = 0;
		this.header = new Node();
	}
	

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public void add(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		if (this.isEmpty()){
			Node newNode = new Node();
			newNode.setValue(obj);
			this.header.setNext(newNode);
			this.currentSize++;
			return;
		}
		Node temp = null;
		for (temp = header.getNext(); temp.getNext() != null; temp = temp.getNext());
		// Node temp = header.getNext();
		// while (temp.getNext() != null){
		// 		temp = temp.getNext();
		//	}
		
		Node newNode = new Node();
		newNode.setValue(obj);
		temp.setNext(newNode);
		this.currentSize++;
		
		
	}

	@Override
	public void add(int index, E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		if ((index < 0) || (index > this.size())){
			throw new IndexOutOfBoundsException();
		}
		if (index == this.size()){
			this.add(obj);
		}
		else {
			Node temp1 = null, temp2 = null;
			int counter = 0;
			for (temp1 = this.header.getNext(), temp2 = this.header; counter < index;
					temp2 = temp1, temp1 = temp1.getNext(), counter++);
			Node newNode = new Node();
			newNode.setValue(obj);
			newNode.setNext(temp1);
			temp2.setNext(newNode);
			this.currentSize++;
		}
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		// Easy way to do it!
//		int index = this.firstIndex(obj);
//		if (index < 0){
//			return false;
//		}
//		else {
//			return this.remove(index);
//		}
		Node temp1 = null, temp2 = null;
		for (temp1 = header.getNext(), temp2 = header; temp1 != null; 
				temp2 = temp1, temp1 = temp1.getNext()){
			if (temp1.getValue().equals(obj)){
				temp2.setNext(temp1.getNext());
				temp1.setValue(null);
				temp1.setNext(null);
				this.currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		int counter = 0;
		Node temp1 = null, temp2 = null;

		for (temp1 = header.getNext(), temp2 = header; temp1 != null; 
				temp2 = temp1, temp1 = temp1.getNext(), counter++){
			if (counter == index){
				temp2.setNext(temp1.getNext());
				temp1.setValue(null);
				temp1.setNext(null);
				this.currentSize--;
				return true;
			}
		}
		return false;
	
	}

	@Override
	public int removeAll(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		int counter = 0;
		while(this.remove(obj)){
			counter++;
		}
		return counter;
	}

	@Override
	public E get(int index) {
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		int counter = 0;
		E result = null;
		for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
			if (counter == index){
				result= temp.getValue();
				break;
			}
		}
		return result;
	}

	@Override
	public E set(int index, E obj) {
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");	
		}
		int counter = 0;
		E result = null; // old value
		for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
			if (counter == index){
				result= temp.getValue();
				temp.setValue(obj);
				break;
			}
		}
		return result;
	}

	@Override
	public E first() {
		if (this.isEmpty()){
			return null;
		}
		else {
			// Node N1 = this.header;
			// Node N2 = N1.getNext();
			// return N2.getValue();
			return this.header.getNext().getValue();
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()){
			return null;
		}
		else {
			Node temp = null;
			for (temp = header.getNext(); temp.getNext() != null ; temp  = temp.getNext());
			return temp.getValue();
		}
	}

	@Override
	public int firstIndex(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		int counter = 0;
		for (Node temp = header.getNext(); temp != null; temp = temp.getNext(), counter++){
			if (temp.getValue().equals(obj)){
				return counter;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		int counter = 0, lastSeen = -1;
		for (Node temp =header.getNext(); temp != null; temp = temp.getNext(), counter++){
			if (temp.getValue().equals(obj)){
				lastSeen = counter;
			}
		}
		return lastSeen;
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
	public boolean contains(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		for (Node temp = header.getNext(); temp != null; temp = temp.getNext()){
			if (temp.getValue().equals(obj)){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public void clear() {
		while(!this.isEmpty() && this.remove(0));
	}

}
