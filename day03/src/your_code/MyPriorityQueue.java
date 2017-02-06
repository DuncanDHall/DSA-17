package your_code;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    Node max = null;

    private class Node {
        int val;
        Node next;

        private Node(int d, Node next) {
            this.val = d;
            this.next = next;
        }
    }

    public void enqueue(int item) {
        if (max == null || max.val <= item) {
            max = new Node(item, max);
            return;
        }

        Node n = max;
        while (n.next != null && n.next.val > item) n = max.next;
        Node m = new Node(item, n.next);
        n.next = m;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        Node n = max;
        max = max.next;
        return n.val;
    }

}