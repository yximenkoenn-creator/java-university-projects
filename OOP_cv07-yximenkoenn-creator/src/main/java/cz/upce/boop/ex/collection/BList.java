package cz.upce.boop.ex.collection;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface BList<T> extends BIterable<T> {

    static <T> BList<T> of(T... values) {
        BGenericList<T> list = new BGenericList<>();

        for (T value : values) {
            list.add(value);
        }

        return list.asReadOnly();
    }

    void add(T o);

    int size();

    T get(int i);

    T set(int index, T element);

    T remove(int index);

    void clear();

    BIterator<T> iterator();

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
