package models;

import java.io.PrintStream;
import java.util.Iterator;



public interface BinarySearchTree<K, V> extends Iterable<V> {
	public int size();
	
	public void makeEmpty();
	
	public boolean isEmpty();
	
	public void add(K key, V value);
	
	public V remove(K key);
	
	public V get(K key);
	
	public List<K> getKeys();
	
	public void print(PrintStream out);
	
	public int height();
	

}
