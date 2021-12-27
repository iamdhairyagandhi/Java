package listClasses;

import java.util.*;

import listClasses.BasicLinkedList.Node;

/**
 * Implements a generic sorted list using a provided Comparator. It extends
 * BasicLinkedList class.
 * 
 * @author Dept of Computer Science, UMCP
 * 
 */

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	private Comparator<T> comparator;

	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public SortedLinkedList<T> addToList(T data) {
		if (head == null) {
			Node newData = new Node(data, head);
			head = newData;
			tail = newData;
		} else {
			head = new Node(data, head);
		}
		listSize++;
		return this;
	}

	public SortedLinkedList<T> add(T element) {
		Node inputData = head;
		Node transferVar = null;
		if (head == null) {
			head = new Node(element, null);
			tail = head;
			listSize++;
			return this;
		}
		while (inputData != null) {
			if (comparator.compare(inputData.data, element) >= 0) {
				if (inputData == head) {
					addToList(element);
					return this;
				}
				break;
			}
			transferVar = inputData;
			inputData = inputData.next;
		}

		if (inputData == tail) {
			transferVar.next = new Node(element, inputData);
			tail = transferVar.next;
		} else {
			transferVar.next = new Node(element, inputData);
		}
		listSize++;
		return this;
	}

	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

}