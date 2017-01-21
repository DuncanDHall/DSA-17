public class MyArrayList {
    private Cow[] elems;
    private int size;

    public MyArrayList() {
        elems = new Cow [10];
        size = 0;
    }

    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    public void add(Cow c) {
        if (size == elems.length) {
            doubleLength();
        }

        elems[size] = c;
        size++;
    }

    private void doubleLength() {
        Cow[] newElems = new Cow[size * 2];
        System.arraycopy(elems, 0, newElems, 0, size);
        elems = newElems;
    }

    public int size() {
        return size;
    }

    public Cow get(int index) {
        return elems[index];
    }

    public Cow remove(int index) {
        Cow c = elems[index];  // any wisdom on when a classes should use their own getters/setters?
        System.arraycopy(elems, index+1, elems, index, size-index-1);
        // don't care about elems[size-1] anymore
        size--;
        return c;
    }

    public void add(int index, Cow c) {
        if (size == elems.length) {
            doubleLength();
        }

        System.arraycopy(elems, index, elems, index+1, size-index);
        size++;

        elems[index] = c;
    }
}
