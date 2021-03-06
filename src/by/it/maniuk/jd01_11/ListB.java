package by.it.maniuk.jd01_11;

import java.util.*;

public class ListB <T> implements List<T> {

    private T[] elements =  (T[]) new Object[]{};
    private int size = 0;

    @Override
    public boolean add(T t) {

        if (size == elements.length) {
            elements = Arrays.copyOf(elements, (size*3)/2+1);
        }
        elements[size++] = t;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder txt = new StringBuilder("[");
        String delimeter = "";
        for (int i = 0; i < size; i++) {
            txt.append(delimeter).append(elements[i]);
            delimeter =", ";
        }
        txt.append("]");
        return txt.toString();
    }

    public T remove(int index) {
        T element = elements[index];
        System.arraycopy(elements, index+1, elements, index, size-1-index);
        size--;
        return element;

    }

    @Override
    public T set(int index, T element) {
        T oldValue = null;
        if (index <= size )
        oldValue = elements[index];
       elements[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {

        System.arraycopy(elements, index, elements, index + 1,size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        T[] len = (T[]) c.toArray();
        int numNew = len.length;
        if (size+numNew >= elements.length) {
            elements = Arrays.copyOf(elements, (size+numNew));
        }


        System.arraycopy(len, 0, elements, size, numNew);
        size += numNew;
        return numNew != 0;

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
         return Arrays.copyOf(elements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf(elements, size, a.getClass());
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
