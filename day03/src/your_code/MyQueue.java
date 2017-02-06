package your_code;

import ADTs.QueueADT;

/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {

    int q [];
    int size;
    int front;
    int back;

    public MyQueue() {
        size = 5;
        q = new int[size];
        front = 0;
        back = 0;
    }

    @Override
    public void enqueue(Integer item) {
        q[front] = item;
        front = Math.floorMod(front + 1, size);

        if (front == Math.floorMod(back-1, size)) resize(size * 2);
    }

    private void resize(int newSize) {
        if (newSize >= 5) {
            int[] newQ = new int[newSize];

//            for (int i = front; i != back; i = Math.floorMod(i+1, size)) {
//                newQ[Math.floorMod(i - size, newSize)] = q[i];
//            }

            int i = back;
            int j = Math.floorMod(i, newSize);
            back = j; // new back value;

            while (i != front) {
                newQ[j] = q[i];
                i++;
                if (i >= size) i -= size;
                j++;
                if (j >= newSize) j -= newSize;
            }

            front = j;

            q = newQ;
            size = newSize;
        }
    }

    @Override
    public Integer dequeue() {
        int n = q[back];
        back = Math.floorMod(back + 1, size);
        if (Math.floorMod(front-back, size) < size / 4) resize(size / 2);
        return n;
    }

    @Override
    public boolean isEmpty() {
        return front == back;
    }

    @Override
    public Integer front() {
        return q[front];
    }

    public int[] getQ() {
        return q;
    }
}