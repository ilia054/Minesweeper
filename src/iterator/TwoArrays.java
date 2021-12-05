package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoArrays implements Iterable<Integer> {//Not much, very self explanatory 
	private int[] arr;

	public TwoArrays(int[] a1, int[] a2) {//we will create anew array from our a1 and a2
		int i=0,count=0;// iterator
		arr = new int[a1.length + a2.length];
		while (i < arr.length) {
			if (count < a1.length)
				arr[i++] = a1[count];

			if (count < a2.length)
				arr[i++] = a2[count];
			count++;
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new Iterator1();
	}

	private class Iterator1 implements Iterator<Integer> {
		private int count = 0;

		@Override
		public boolean hasNext() {
			return count < arr.length;
		}

		@Override
		public Integer next() {
			if (count == arr.length)
				throw new NoSuchElementException();
			count++;
			return arr[count-1];
		}

	}

}
