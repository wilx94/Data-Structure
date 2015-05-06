package models;

public class LongComparator implements KeyComparator<Long> {

	@Override
	public int compareTo(Long key1, Long key2) {
		return key1.compareTo(key2);
	}

}
