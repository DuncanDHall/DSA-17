public class MyLinkedList <T> {

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T val;
		Node prev;
		Node next;

		private Node(T d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T c) {
		addLast(c);
	}

	public T pop() {
		return removeLast();
	}

	public void addLast(T c) {
		Node n = new Node(c, tail, null);
        if (size == 0) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        size++;
	}

	public void addFirst(T c) {
	    Node n = new Node(c, null, head);
	    if (size == 0) {
            tail = n;
        } else {
	        head.prev = n;
        }
        head = n;
	    size++;
	}

	private Node GetNodeAtIndex(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();

	    Node t;
	    if (index < size / 2) {
	        t = head;
            while (index > 0) {
                index--;
                t = t.next;
            }
        } else {
	        t = tail;
	        while (index < size-1) {
                index++;
                t = t.prev;
            }
        }

        return t;
    }

	public T get(int index) {
        return GetNodeAtIndex(index).val;
	}

	public T remove(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
	    if (index == 0) return removeFirst();
	    if (index == size-1) return removeLast();

	    Node t = GetNodeAtIndex(index);
	    t.prev.next = t.next;
	    t.next.prev = t.prev;
	    size--;
		return t.val;
	}

	public T removeFirst() {
	    Node n = head;
	    head = head.next;
	    if (head != null) {
            head.prev = null;
        } else {
	        tail = null;
        }
        size--;
	    return n.val;
	}

	public T removeLast() {
	    Node c = tail;
	    tail = tail.prev;
        if (tail != null) {
            tail.prev = null;
        } else {
            head = null;
        }
        size--;
	    return c.val;
	}
}