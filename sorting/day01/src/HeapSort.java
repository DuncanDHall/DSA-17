public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    private boolean hasLeftChild(int i) {
        return leftChild(i) < size;
    }

    private boolean hasRightChild(int i) {
        return rightChild(i) < size;
    }

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    public void heapify(int i) {
        int lc = leftChild(i);
        int rc = rightChild(i);

        if (hasLeftChild(i) && hasRightChild(i)) {
            if (heap[lc] > heap[rc] && heap[lc] > heap[i]) {
                swap(i, lc);
                heapify(lc);
            }
            else if (heap[rc] > heap[i]) {
                swap(i, rc);
                heapify(rc);
            }
        }
        else if (hasLeftChild(i) && heap[lc] > heap[i]) {
            swap(i, lc);
            heapify(lc);
        }
        else if (hasRightChild(i) && heap[rc] > heap[i]) {
            swap(i, rc);
            heapify(rc);
        }

    }

    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }


    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        buildHeapFrom(array);
        int[]res = new int[size];
        int j = 0;
        for (int i=size-1; i>=0; i--) {
            swap(i, 0);
            size--;
            res[i] = heap[size];
            heapify(0);
        }
        return res;
    }

    private void swap(int a, int b) {
        int c = heap[a];
        heap[a] = heap[b];
        heap[b] = c;
    }

}
