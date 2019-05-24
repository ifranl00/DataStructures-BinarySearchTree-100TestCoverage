package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeADTTests {

    /*
	* ∅
    */
	private BinarySearchTreeADTImpl<Integer> TE = null;
	
	/*
	* 1
	* |  ∅
	* |  2
	* |  |  ∅
	* |  |  3
	* |  |  |  ∅
	* |  |  |  4
	* |  |  |  |  ∅
	* |  |  |  |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> T1234 = null;
	
	/*
	* 4
	* |  3
	* |  |  2
	* |  |  |  1
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> T4321 = null;

	/*
	* 50
	* |  20
	* |  |  10
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
	* |  80
	* |  |  70
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  90
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> TC3 = null;

	/*
	* 10
	* |  5
	* |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
	*/
	private BinarySearchTreeADTImpl<Integer> TEx = null;

	/*
	 * 10
	 * |  5
	 * |  |  ∅
	 * |  |  7
	 * |  |  |  6
	 * |  |  |  |  ∅
	 * |  |  |  |  ∅
	 * |  |  |  ∅
	 * |  15
	 * |  |  ∅
	 * |  |  ∅
	 * 
	 */
	private BinarySearchTreeADTImpl<Integer> TV1 = null;


	@Before
	public void setupBSTs() {
		
		TE = new BinarySearchTreeADTImpl<Integer>();
		
		T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1,2,3,4);
		Assert.assertEquals(T1234.toString(), "{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}");
		
		T4321 = new BinarySearchTreeADTImpl<Integer>();
		T4321.insert(4, 3, 2, 1);
		Assert.assertEquals(T4321.toString(), "{4, {3, {2, {1, ∅, ∅}, ∅}, ∅}, ∅}");
		
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 80, 10, 30, 70, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {80, {70, ∅, ∅}, {90, ∅, ∅}}}");
		
		TEx = new BinarySearchTreeADTImpl<Integer>();
		TEx.insert(10, 20, 30, 5);
		Assert.assertEquals(TEx.toString(), "{10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}");
		
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 15);		
		Assert.assertEquals(TV1.toString(), "{10, {5, ∅, {7, {6, ∅, ∅}, ∅}}, {15, ∅, ∅}}");
		
		
	}
	
	@Test
	public void testInsertElementsTrue() {
		
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 19);
		Assert.assertEquals("{10, {5, ∅, {7, {6, ∅, ∅}, ∅}}, {19, ∅, ∅}}", TV1.toString());
		
	}
	
	@Test
	public void testInsertElementsFalse() {
		
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, null);
		Assert.assertEquals("∅", TV1.toString());
		
	}
	
	
	@Test
	public void testInsertCollectionTrue() {
	
		Collection<Integer> a = new ArrayList<Integer>();
		
		a.add(10);
		a.add(5);
		a.add(15);
		a.add(13);
		
		TE.insert(a);
		Assert.assertEquals("{10, {5, ∅, ∅}, {15, {13, ∅, ∅}, ∅}}", TE.toString());
	}
	
	@Test
	public void testInsertCollectionFalse() {
		
		Collection<Integer> a = new ArrayList<Integer>();
		
		a.add(10);
		a.add(5);
		a.add(null);
		a.add(13);
		
		TE.insert(a);
		Assert.assertEquals("∅", TE.toString());
	}
	
	@Test
	public void testInsertElementEmpty() {
		
		TE.insert(10);
	}
	
	@Test
	public void testInsertElement() {
		
		T1234.insert(8);
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, {4, ∅, {8, ∅, ∅}}}}}", T1234.toString());
		
		T1234.insert(6);
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, {4, ∅, {8, {6, ∅, ∅}, ∅}}}}}", T1234.toString());
		
	}
	
	@Test
	public void testInsertElementRepeated() {
		
		T1234.insert(2);
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}", T1234.toString());
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void testWithdrawElementEmpty() {
		
		TE.withdraw(10);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testWithdrawElementNotFound() {
		
		T1234.withdraw(5);
	}
	
	@Test
	public void testWithdrawElementIsLeaf() {
		
		T1234.withdraw(4);
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, ∅}}}", T1234.toString());
	}
	
	@Test
	public void testWithdrawElementOneChildRight() {
		
		T1234.insert(5);
		T1234.withdraw(3);
		Assert.assertEquals("{1, ∅, {2, ∅, {4, ∅, {5, ∅, ∅}}}}", T1234.toString());
		
		TE.insert(10,5,13,7,6,8);
		Assert.assertEquals("{10, {5, ∅, {7, {6, ∅, ∅}, {8, ∅, ∅}}}, {13, ∅, ∅}}", TE.toString());
		
		TE.withdraw(5);
		Assert.assertEquals("{10, {7, {6, ∅, ∅}, {8, ∅, ∅}}, {13, ∅, ∅}}", TE.toString());
	}
	
	@Test
	public void testWithdrawElementOneChildLeft() {
		
		T4321.withdraw(1);
		Assert.assertEquals("{4, {3, {2, ∅, ∅}, ∅}, ∅}", T4321.toString());

		
		TE.insert(10,9,13,8,7);
		Assert.assertEquals("{10, {9, {8, {7, ∅, ∅}, ∅}, ∅}, {13, ∅, ∅}}", TE.toString());
		
		TE.withdraw(8);
		Assert.assertEquals("{10, {9, {7, ∅, ∅}, ∅}, {13, ∅, ∅}}", TE.toString());
	}
	
	@Test
	public void testWithdrawElement2Child() {
		
		TE.insert(50,20,80,60,55,70,100);
		Assert.assertEquals("{50, {20, ∅, ∅}, {80, {60, {55, ∅, ∅}, {70, ∅, ∅}}, {100, ∅, ∅}}}", TE.toString());
		
		TE.withdraw(80);
		Assert.assertEquals("{50, {20, ∅, ∅}, {70, {60, {55, ∅, ∅}, ∅}, {100, ∅, ∅}}}", TE.toString());

	}
	
	@Test
	public void testWithdrawElements() {
		
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 19);
		Assert.assertEquals("{10, {5, ∅, {7, {6, ∅, ∅}, ∅}}, {19, ∅, ∅}}", TV1.toString());
		
		TV1.withdraw(10, 7, 19);
		Assert.assertEquals("{6, {5, ∅, ∅}, ∅}", TV1.toString());
	
	}
	
	@Test
	public void testWithdrawElementsNull() {
		
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 19);
		
		TV1.withdraw(10, null, 5);
		
	}
	
	@Test
	public void testWithdrawCollection() {
		
		Collection<Integer> a = new ArrayList<Integer>();
		
		a.add(10);
		a.add(5);
		a.add(15);
		a.add(13);
		
		TE.insert(a);
		
		TE.withdraw(a);
		Assert.assertEquals("∅", TE.toString());
	}
	
	@Test
	public void testWithdrawCollectionNull() {
		
		Collection<Integer> a = new ArrayList<Integer>();
		
		a.add(null);
		a.add(65);
		
		TE.insert(a);
		TE.withdraw(a);
		Assert.assertEquals("∅", TE.toString());
	}

	
	@Test
	public void testGetSubtreeWithPathOk() {
		
		Assert.assertEquals("{4, ∅, ∅}", T1234.getSubtreeWithPath("111").toString());
		TE.insert(70,90,20,10,6,45);
		Assert.assertEquals("{20, {10, {6, ∅, ∅}, ∅}, {45, ∅, ∅}}", TE.getSubtreeWithPath("0").toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetSubtreeWithPathNotFound() {
		
		TE.insert(70,90,20,10,6,45);
		TE.getSubtreeWithPath("011");
	
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetSubtreeWithPathEmpty() {
		
		TE.getSubtreeWithPath("0");
	}
	
	@Test
	public void testGetSubtreeWithPathNull() {
		
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}", T1234.getSubtreeWithPath("").toString());
		Assert.assertEquals("{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}", T1234.getSubtreeWithPath(null).toString());
		
	}
	
	/*
		@Test
		public void testTagDescendTC4() {
			List<String> lista= new LinkedList<String>();
			TC3.parentChildPairsTagDescend(lista);
			Assert.assertEquals(lista.toString(), "[(80, 90), (80, 70), (50, 80), (50, 20), (20, 30), (20, 10)]");
			TC3.filterTags("descend");
			Assert.assertEquals("{50 [(descend, 4)], {20 [(descend, 6)], {10 [(descend, 7)], ∅, ∅}, {30 [(descend, 5)], ∅, ∅}}, {80 [(descend, 2)], {70 [(descend, 3)], ∅, ∅}, {90 [(descend, 1)], ∅, ∅}}}", TC3.toString());
			
		}
		*/
	
	}


