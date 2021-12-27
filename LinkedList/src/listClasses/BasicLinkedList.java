package listClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicLinkedList<T> extends java.lang.Object implements Iterable<T> {
	protected Node head;
	protected Node tail;
	protected int listSize;

	protected class Node {
		T data;
		Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		protected Node(T data) {
			this.data = data;
			next = null;
		}

	}

	public BasicLinkedList() {
		head = null;
		tail = null;
		listSize = 0;
	}

	public int getSize() {
		return listSize;
	}

	public BasicLinkedList<T> addToFront(T data) {
		if (head == null) {
			Node newNode = new Node(data, head);
			head = newNode;
			tail = newNode;
		} else {
			head = new Node(data, head);
		}
		listSize++; // 2
		return this;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data, null);

		if (tail == null) {
			head = newNode;
			tail = newNode;

		} else {
			tail.next = newNode;
			tail = newNode;
		}
		listSize++; // 1
		return this;
	}

	public T getFirst() {
		if (head == null) {
			return null;
		} else {
			return head.data;
		}
	}

	public T getLast() {
		if (tail == null) {
			return null;
		} else {
			return tail.data;
		}
	}

	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		T firstData = head.data;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		listSize--; // 3
		return firstData;
	}

	/*
	 * public T retrieveLastElement() { if (tail == null) { return null; }
	 * 
	 * T lastData = tail.data;
	 * 
	 * 
	 * if (tail == null) { head = null; } listSize--; return lastData;
	 * 
	 * }
	 */

	public T retrieveLastElement() {
		if (head == null)
			return null;
		if (head.next == null) {
			return null;
		}
		T data = tail.data;
		Node inputData = head;
		while (inputData.next.next != null) {
			inputData = inputData.next;
		}
		inputData.next = null;
		tail = inputData;
		listSize--; // 4
		return data;
	}

	// if the targetData is the same as data at node...
	// check to see if head needs to be removed
	// check to see if tail needs to be
	// this will indicate that the last node will be removed
	// last node is removed
	// node before tail points toward null
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node inputData = head;
		Node transferVar = null;

		while (inputData != null) {
			if (comparator.compare(inputData.data, targetData) == 0) {
				if (inputData == head) {
					head = head.next;
					inputData = head;
				} else if (inputData == tail) {
					inputData = null;
					tail = transferVar;
					transferVar.next = null;
				} else {
					transferVar.next = inputData.next;
					inputData = inputData.next;
				}
				listSize--;
			} else {
				transferVar = inputData;
				inputData = inputData.next;
			}
		}
		return this;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node inputData = head;

			@Override
			public boolean hasNext() {
				if (inputData == null) {
					return false;
				}
				return true;
			}

			@Override
			public T next() {
				if (head == null) {
					throw new NoSuchElementException("Iteration has no more elements");
				}
				T data = inputData.data;
				inputData = inputData.next;
				return data;
			}

			public void remove() {
				throw new UnsupportedOperationException("Not implementing remove");
			}
		};
	}

	public ArrayList<T> getReverseArrayList() {
		return getReverseArrayList(head, new ArrayList<T>());
	}

	//after checking if pointer = null, 
	//new array list is made, 
	//add the data of the head to the list.
	public ArrayList<T> getReverseArrayList(Node pointer, ArrayList<T> list) {

		if (pointer == null) {
			return list;
		}

		list.add(0, pointer.data);
		return getReverseArrayList(pointer.next, list);
	}

	public BasicLinkedList<T> getReverseList() {
		return getReverseList(head, new BasicLinkedList<T>());
	}

	private BasicLinkedList<T> getReverseList(Node pointer, BasicLinkedList<T> list) {

		if (pointer == null) {
			return list;
		}

		// after a new list is made, add the head to the list.
		list.addToFront(pointer.data);
		return getReverseList(pointer.next, list); // call the method with the next node
	}

	/*
	 * public static void main (String args[]) { Node pract = new Node(new
	 * Integer(5));
	 * 
	 * }
	 */
}
