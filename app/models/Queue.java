package models;

import java.io.PrintStream;

public interface Queue<E> {
	
	public int size();
	
	public boolean isEmpty();
	
	public void makeEmpty();
	
	public E front();
	
	public E dequeue();
	
	public void enqueue(E obj);
	
	public void print(PrintStream out);
	

}
