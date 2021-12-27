package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */

/*
 * addtoend addtofront getfirst getlast getreversearraylist getreverselist
 * getsize iterator remove retrievefirst retrievelast
 */
public class StudentTests {

	@Test

	public void testGetLastAddToEnd() {

		BasicLinkedList<String> test = new BasicLinkedList<String>();

		test.addToFront("a");
		test.addToFront("e");
		test.addToFront("b");
		test.addToEnd("d");

		String result = test.getLast();
		String expectedResult = "d";

		assertEquals(result, expectedResult);

	}

	@Test

	public void testRetrieveFirstGetFirstAddToFront() {

		BasicLinkedList<String> test = new BasicLinkedList<String>();

		test.addToFront("x");
		test.addToEnd("b");
		test.addToFront("a");

		String result1 = test.getFirst();
		String result = test.retrieveFirstElement();

		// System.out.println(result1);
		String expectedResult = "a";

		assertEquals(result, expectedResult, result1);

	}

	@Test

	public void testRetrieveLastGetReverse() {

		BasicLinkedList<String> test = new BasicLinkedList<String>();

		test.addToFront("r");
		test.addToFront("q");
		test.addToFront("p");

		String result = test.retrieveLastElement();
		String expectedResult = "r";
		assertEquals(result, expectedResult);

	}

	public void testGetSizeAddToList() {
		SortedLinkedList<String> test = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);

		test.addToFront("1");
		test.addToFront("2");
		test.addToFront("3");

		int expectedResult = 3;
		assertEquals(expectedResult, test.getSize());
		test.add("4");
		assertEquals(4,test.getSize());
	}
	
	public void testRemove() {
		SortedLinkedList<String> test = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);

		test.addToFront("1");
		test.addToFront("2");
		test.addToFront("3");

		test.remove("2");
		
		assertEquals(2,test.getSize());
	}
	
}
