package test;

import ADTs.QueueADT;
import org.junit.Before;
import org.junit.Test;
import your_code.MyPriorityQueue;
import your_code.MyQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueueTest {

    private MyQueue queue;
    private MyPriorityQueue maxQueue;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        queue = new MyQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        maxQueue = new MyPriorityQueue();
        maxQueue.enqueue(1);
        maxQueue.enqueue(2);
        maxQueue.enqueue(3);
    }

    /**
     * Tests functionality of a queue
     */
    public void testQueue(QueueADT<Integer> queue) {
        int e = queue.dequeue();
        assertThat(e, is(1));

        queue.enqueue(150);
        assertThat(queue.isEmpty(), is(false));

        e = queue.dequeue();
        assertThat(e, is(2));
        assertThat(queue.isEmpty(), is(false));

        e = queue.dequeue();
        assertThat(e, is(3));
        assertThat(queue.isEmpty(), is(false));

        e = queue.dequeue();
        assertThat(e, is(150));
        assertThat(queue.isEmpty(), is(true));
    }

    /**
     * Tests functionality of your_code.MyQueue
     */
    @Test
    public void testMyQueue() {
        testQueue(queue);
    }

    /**
     * Tests functionality of your_code.MyPriorityQueue
     */
    @Test
    public void testPriorityQueue() {
        maxQueue.enqueue(5);
        assertThat(maxQueue.dequeueMax(), is(5));
        maxQueue.enqueue(2);
        assertThat(maxQueue.dequeueMax(), is(3));
        assertThat(maxQueue.dequeueMax(), is(2));
        assertThat(maxQueue.dequeueMax(), is(2));
        assertThat(maxQueue.dequeueMax(), is(1));
    }

    @Test
    public void testResizeQueue() {
        assertThat(queue.getQ(), is(new int[]{1,2,3,0,0}));
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        assertThat(queue.getQ(), is(new int[]{1,2,3,4,5,6,0,0,0,0}));
    }

    @Test
    public void testLoopQueue() {
        queue.dequeue();
        queue.enqueue(4);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(5);
        queue.enqueue(6);
        assertThat(queue.getQ(), is(new int[]{6, 2, 3, 4, 5}));
    }

    @Test
    public void testResizeU() {
        queue.dequeue();
        assertThat(queue.getQ(), is(new int[]{1, 2, 3, 0, 0}));
        queue.enqueue(4);
        queue.dequeue();
        queue.enqueue(5);
        queue.dequeue();
        queue.enqueue(6);
        assertThat(queue.getQ(), is(new int[]{6,2,3,4,5}));
        queue.enqueue(7);
        assertThat(queue.getQ(), is(new int[]{0,0,0,4,5,6,7,0,0,0}));
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertThat(queue.getQ(), is(new int[]{0,7,0,0,0}));
    }
}
