public class MyArrayList <T> {
    private T[] elems;
    private int size;

    public MyArrayList() {
        elems = (T[]) new Object[10];
        size = 0;
    }

    public MyArrayList(int capacity) {
        elems = (T[]) new Object[capacity];
        size = 0;
    }

    public void add(T c) {
        if (size >= elems.length) {
            setCapacity(size * 2);
        }

        elems[size] = c;
        size++;
    }

    public void setCapacity(int capacity) {
        if (capacity > 10) {
            T[] newElems = (T[]) new Object[capacity];
            System.arraycopy(elems, 0, newElems, 0, size);
            elems = newElems;
        }
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    public T remove(int index) {
        T c = elems[index];  // any wisdom on when a classes should use their own getters/setters?
        System.arraycopy(elems, index+1, elems, index, size-index-1);
        // don't care about elems[size-1] anymore
        size--;

        if (size <= elems.length / 4) {
            setCapacity(size / 2);
        }

        return c;
    }

    public void add(int index, T c) {
        if (size >= elems.length) {
            setCapacity(size * 2);
        }

        System.arraycopy(elems, index, elems, index+1, size-index);
        size++;

        elems[index] = c;
    }
}
