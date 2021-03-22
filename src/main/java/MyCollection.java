import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection<E> implements Collection<E> {

    private int size;

    private Object[] elementData = new Object[10];

    @Override
    public boolean add(E e) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public boolean contains(Object o) {
        for( Object el : elementData ) {
            if( el.equals(o) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return elementData;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length >= size) {
            for ( int i = 0; i < size; ++i ) {
                a[i] = (T) elementData[i];
            }
        }
        return a;
    }

    @Override
    public boolean remove(Object o) {
        for ( int i = 0; i < size; ++i ) {
            if( elementData[i].equals(o) ) {
                for ( int j = ++i; j < size; ++j ) {
                    elementData[j-1] = elementData[j];
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean all = false;
        for ( Object otherEl : c ) {
            all &= contains( otherEl );
        }
        return all;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for ( E otherEl : c ) {
            add( otherEl );
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean deleted = false;
        for ( Object otherEl : c ) {
            deleted |= remove( otherEl );
        }
        return deleted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for ( Object el : elementData ) {
            if ( !c.contains(el) ) {
                changed |= remove( el );
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        elementData = new Object[10];
        size = 0;
    }

    private class MyIterator<T> implements Iterator<T> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if(cursor >= size){
                throw new NoSuchElementException();
            }
            return (T) elementData[cursor++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}