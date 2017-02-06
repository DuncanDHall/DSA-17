package your_code;

import ADTs.StackADT;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    Node top;
    Node maxTop;

    private class Node {
        int val;
        Node next;

        private Node(int d, Node prev, Node next) {
            this.val = d;
            this.next = next;
        }
    }

    public MyStack() {
        top = null;
        maxTop = null;
    }

    @Override
    public void push(Integer e) {
        Node n = new Node(e, null, top);
        top = n;

        Node m;
        if (maxTop == null || maxTop.val < e) {
            m = new Node(e, null, maxTop);
        } else {
            m = new Node(maxTop.val, null, maxTop);
        }
        maxTop = m;
    }

    @Override
    public Integer pop() {
        Node n = top;
        top = n.next;

        maxTop = maxTop.next;

        return n.val;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Integer peek() {
        return top.val;
    }

    public Integer maxElement() {
        if (maxTop == null) return null;
        return maxTop.val;
    }
}
