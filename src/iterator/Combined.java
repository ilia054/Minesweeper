package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> {
	private Iterator<E> first, second;

	public Combined(Iterable<E> first, Iterable<E> second) {//getting an iterator over our Iterable first and second
		this.first = first.iterator();
		this.second = second.iterator();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator2();
	}

	private class Iterator2 implements Iterator<E> {
		int cnt = 0;// will indicate which one

		@Override
		public boolean hasNext() {//As long as were not "done" with booth
			return (first.hasNext() || second.hasNext());
		}

		@Override
		public E next() {//cnt %2 will let us know who's "Turn" it is to spit out their elements
			E tmp = null;
			if (cnt % 2 == 0 && first.hasNext() || !second.hasNext()) {
				tmp = first.next();
				cnt++;
			} else if (second.hasNext() || first.hasNext()) {
				tmp = second.next();
				cnt++;
			}
			return tmp;

		}
	}

}