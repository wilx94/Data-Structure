package models;


import models.BinarySearchTree;
import models.DoublyLinkedQueue;
import models.KeyComparator;
import models.LinkedList;
import models.List;
import models.Queue;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class BSTImp<K, V> implements BinarySearchTree<K, V> {
	private static final int TABS = 4;
	
	private enum ChildType {
		LEFT, RIGHT;
	}
	
	private class Node {
		private K key;
		private V value;
		private Node leftChild;
		private Node rightChild;
		
		
		public Node() {
			super();
		}
		public Node(K key, V value, Node leftChild, Node rightChild) {
			super();
			this.key = key;
			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public Node getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}
		public Node getRightChild() {
			return rightChild;
		}
		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		
		
	}
	
	private class BSTIterator implements Iterator<V>{
		
		private Queue<V> elements;
		
		private BSTIterator(){
			this.elements = new DoublyLinkedQueue<V>();
			traverseAux(root, this.elements);
		}
		
		

		private void traverseAux(Node N, Queue<V> Q) {
			if (N == null){
				return;
			}
			else {
				traverseAux(N.leftChild, Q);
				Q.enqueue(N.getValue());
				traverseAux(N.rightChild, Q);
			}
		}



		@Override
		public boolean hasNext() {
			return !this.elements.isEmpty();
		}

		@Override
		public V next() {
			if (this.hasNext()){
				V result = this.elements.dequeue();
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
	
	private Node root;
	private int currentSize;
	private KeyComparator<K> comparator;
	
	
	public BSTImp(KeyComparator<K> comparator){
		if (comparator == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		this.root = null;
		this.currentSize = 0;
		this.comparator = comparator;
	}
	
	@Override
	public Iterator<V> iterator() {
		return new BSTIterator();
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public void makeEmpty() {
		while (!this.isEmpty()){
			this.remove(this.root.getKey());
		}
		
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void add(K key, V value) {
		if ((key == null) || (value == null)){
			throw new IllegalArgumentException("Cannot pass argument as null.");
		}
		if (this.root == null){
			this.root = new Node(key, value, null, null);
			this.currentSize++;
		}
		else {
			this.addAux(key, value, this.root);
		}
	}
	
	

	private void addAux(K key, V value, Node N) {
		int comparison = this.comparator.compareTo(key, N.getKey());
		if (comparison < 0){
			if (N.getLeftChild() == null){
				Node newNode = new Node(key, value, null, null);
				N.setLeftChild(newNode);
				this.currentSize++;
			}
			else {
				this.addAux(key, value, N.getLeftChild());
			}
		}
		else {
			if (N.getRightChild() == null){
				Node newNode = new Node(key, value, null, null);
				N.setRightChild(newNode);
				this.currentSize++;
			}
			else {
				this.addAux(key, value, N.getRightChild());
			}
		}
	}

	@Override
	public V remove(K key) {
		if (key == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		if (this.isEmpty()){
			return null;
		}
		int comparison = this.comparator.compareTo(key, this.root.getKey());
		if (comparison == 0){
			V result =this.root.getValue();
			
			// case 1 - root is a leaf
			if (this.size() == 1){
				this.root.setKey(null);
				this.root.setValue(null);
				this.root = null;
				this.currentSize--;
			}
			// case 2 - No right child
			else if (this.root.rightChild == null){
				this.root.setKey(null);
				this.root.setValue(null);
				this.root = this.root.leftChild;
				this.currentSize--;
			}
			// case 3 - Right child is present
			else {
				Node successor = findLeftMost(this.root.rightChild);
				this.root.setKey(successor.getKey());
				this.root.setValue(successor.getValue());
				removeAux(successor.getKey(), this.root.rightChild, this.root, ChildType.RIGHT);		
			}
			return result;
			
		}
		else if (comparison < 0){
			return removeAux(key, this.root.leftChild, this.root, ChildType.LEFT);
		}
		else {
			return removeAux(key, this.root.rightChild, this.root, ChildType.RIGHT);
		}
	}

	
	private Node findLeftMost(Node N) {
		if (N == null){
			throw new IllegalArgumentException("Cannot be null");
		}
		if (N.leftChild == null){
			return N;
		}
		else {
			return findLeftMost(N.leftChild);
		}
	}

	private V removeAux(K key, Node N, Node parent, ChildType type) {
		if (N == null){
			return null;
		}
		int comparison = this.comparator.compareTo(key, N.getKey());
		if (comparison == 0){
			V result =N.getValue();

			// case 1 - N is a leaf
			if ((N.leftChild == null) && (N.rightChild == null)){
				N.setKey(null);
				N.setValue(null);
				if (type == ChildType.LEFT){
					parent.setLeftChild(null);
				}
				else {
					parent.setRightChild(null);
				}
				this.currentSize--;
			}
			// Case 2 - N has no right child
			else if (N.rightChild == null){
				N.setKey(null);
				N.setValue(null);
				if (type == ChildType.LEFT){
					parent.setLeftChild(N.leftChild);
				}
				else {
					parent.setRightChild(N.leftChild);
				}
				this.currentSize--;
			}
			// Case 3 - N has right child
			else {
				Node successor = findLeftMost(N.rightChild);
				N.setKey(successor.getKey());
				N.setValue(successor.getValue());
				removeAux(successor.getKey(), N.rightChild, N, ChildType.RIGHT);		

			}
			return result;
 		}
		else if (comparison < 0){
			return this.removeAux(key, N.leftChild, N, ChildType.LEFT);
		}
		else {
			return this.removeAux(key, N.rightChild, N, ChildType.RIGHT);
		}
	}

	@Override
	public V get(K key) {
		if (key == null){
			throw new IllegalArgumentException("Cannot pass argument as null.");
		}
		return this.getAux(key, this.root);
	}
	
	private V getAux(K key, Node N){
		if (N == null){
			return null;
		}
		else {
			int comparison = this.comparator.compareTo(key, N.getKey());
			if (comparison == 0){
				return N.getValue();
			}
			else if (comparison < 0){
				return this.getAux(key, N.getLeftChild());
			}
			else {
				return this.getAux(key, N.getRightChild());
			}
		}
	}

	@Override
	public List<K> getKeys() {
		List<K> result = new LinkedList<K>();
		if (this.isEmpty()){
			return result;
		}
		else {
			getKeysAux(this.root, result);
			return result;
		}
	}

	private void getKeysAux(Node N, List<K> result) {
		if (N == null){
			return;
		}
		else {
			result.add(N.getKey());
			getKeysAux(N.getLeftChild(), result);
			getKeysAux(N.getRightChild(), result);
		}
	}

	@Override
	public void print(PrintStream out) {
		printAux(out, this.root, 0);
	}

	
	private void printAux(PrintStream out, Node N, int spaces) {
		if (N == null){
			return;
		}
		else {
			printAux(out, N.rightChild, spaces+TABS);
			for (int i=0; i < spaces;++i){
				out.print(" ");
			}
			out.println(N.getKey());
			//out.printf("(%s, %s)\n", N.getKey(), N.getValue()); 
			printAux(out, N.leftChild, spaces+TABS);
		}
		
	}

	@Override
	public int height() {
		if (this.root == null){
			return 0;
		}
		else {
			return this.heightAux(this.root);
		}
	}

	private int heightAux(Node N) {
		if ((N.getLeftChild() == null) && (N.getRightChild() == null)){
			return 0;
		}
		else {
			int leftHeight = 0, rightHeight = 0;
			if (N.getLeftChild() != null){
				leftHeight = this.heightAux(N.getLeftChild());
			}
			if (N.getRightChild() != null){
				rightHeight = this.heightAux(N.getRightChild());
			}
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}

}
