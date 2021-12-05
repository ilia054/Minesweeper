// 10136915
package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import util.Tester;

class IteratorTesterBase extends Tester {
	<E> String iterableToString(Iterable<E> it) {
		StringBuilder b = new StringBuilder();
		for (E e : it)
			b.append(e + " ");
		return b.toString();
	}
}

class InfiniteIterable implements Iterable<Integer> {
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int i = 0;

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Integer next() {
				int res = i;
				i = (i + 1) % 5;
				return res;
			}
		};
	}
}

// Structure

class TwoArraysSk implements Iterable<Integer> {
	public TwoArraysSk(int[] a1, int[] a2) {}
	public Iterator<Integer> iterator() { return null; }
}

class CombinedSk<E> implements Iterable<E> {
	public CombinedSk(Iterable<E> first, Iterable<E> second) {}
	public Iterator<E> iterator() { return null; }

}

public class IteratorTester extends IteratorTesterBase {

	void testStructure() {
		initStructureTest();
		testEqualClasses(TwoArrays.class, TwoArraysSk.class);
		testEqualClasses(Combined.class, CombinedSk.class);
	}
	
	void testTwoArrays() {
		initPublishedTest(TwoArrays.class);
		int[] a1 = { 1, 2, 3, 4 };
		int[] a2 = { 100, 101, 102, 103, 104, 105, 106 };

		TwoArrays aa = new TwoArrays(a1, a2);
		checkEq(iterableToString(aa), "1 100 2 101 3 102 4 103 104 105 106 ");
	}

	void testTwoArrays2() {
		initPublishedTest(TwoArrays.class);
		int[] a1 = { 1, 2, 3, 4 };
		int[] a2 = { 100, 101, 102, 103, 104, 105, 106 };

		TwoArrays aa = new TwoArrays(a1, a2);
		Iterator<Integer> i = aa.iterator();
		checkEq(i.next(), 1, "first next()");
		checkEq(i.next(), 100, "second next()");
		checkEq(i.next(), 2, "third next()");
		checkEq(i.next(), 101, "forth next()");
		checkEq(i.hasNext(), true, "hasNext() after four calls to next()");
	}

	
	void testCombined() {
		initPublishedTest(Combined.class);
		List<String> list = Arrays.asList("one", "two", "three");
		Set<String> set = new TreeSet<>();
		set.addAll(Arrays.asList("B", "A", "D", "C", "E"));
		
		Combined<String> c = new Combined<>(set, list);
		checkEq(iterableToString(c), "A one B two C three D E ", null);
	}

	void testInfinite() {
		initPublishedTest(Combined.class);

		List<Integer> list1 = Arrays.asList(10, 20, 30);
		
		Combined<Integer> c = new Combined<Integer>(list1, new InfiniteIterable());
		Iterator<Integer> it = c.iterator();
		checkEq(it.next(), 10, "first next()");
		checkEq(it.next(), 0, "second next()");
		checkEq(it.next(), 20, "third next()");
		checkEq(it.next(), 1, "second next()");
	}

	// ------------------------------------------------------------

	public static void main(String[] args) {
		new IteratorTester().myMain("iterator");
	}

	// ------------------------------------------------------------
	// Here you can choose which tests to run.

	public void myTests() {
		testStructure();
		testTwoArrays();
		testTwoArrays2();
		testCombined();
		testInfinite();
	}
}
