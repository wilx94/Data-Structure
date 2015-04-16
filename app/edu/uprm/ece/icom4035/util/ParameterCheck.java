package edu.uprm.ece.icom4035.util;

import models.SortedList;

public class ParameterCheck {
	
	public static  void checkNull(Object obj){
		if(obj == null){
			throw new NullPointerException();
		}
	}
	
	public static void checkSGRRange(int size, int index){

		if(index < 0 || index >= size )
			throw new IndexOutOfBoundsException("index"+index);
	}
	
	

}
