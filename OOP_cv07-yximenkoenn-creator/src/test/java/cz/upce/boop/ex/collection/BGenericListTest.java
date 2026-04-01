package cz.upce.boop.ex.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BGenericListTest {

    @Test
    void cloneCreatesNewButEqualList() {
        BGenericList<Integer> original = new BGenericList<>();
        original.add(1);
        original.add(2);
        original.add(3);

        BGenericList<Integer> clone = original.clone();

        assertNotSame(original, clone);
        assertEquals(original, clone);
    }

    @Test
    void cloneAddToOriginalDoesNotAffectClone() {
        BGenericList<Integer> original = new BGenericList<>();
        original.add(10);
        original.add(20);

        BGenericList<Integer> clone = original.clone();

        original.add(30);

        assertEquals(2, clone.size());
    }

    @Test
    void cloneRemoveFromOriginalDoesNotAffectClone() {
        BGenericList<Integer> original = new BGenericList<>();
        original.add(5);
        original.add(6);
        original.add(7);

        BGenericList<Integer> clone = original.clone();

        original.remove(1);

        assertEquals(3, clone.size());
    }

    @Test
    void readOnlyCloneReturnsSameInstance() {
        BGenericList<Integer> list = new BGenericList<>();
        list.add(1);
        list.add(2);

        BGenericList.BReadOnlyList<Integer> readOnly
                = (BGenericList.BReadOnlyList<Integer>) list.asReadOnly();

        BGenericList.BReadOnlyList<Integer> clone = readOnly.clone();

        assertSame(readOnly, clone);
    }

    @Test
    void ofCreatesListWithCorrectValues() {
        BList<Integer> list = BList.of(7, 8, 9);

        assertEquals(3, list.size());
        assertEquals(7, list.get(0));
        assertEquals(8, list.get(1));
        assertEquals(9, list.get(2));
    }

    @Test
    void ofListIsImmutableForAdd() {
        BList<Integer> list = BList.of(1, 2, 3);

        assertThrows(UnsupportedOperationException.class, () -> {
            list.add(99);
        });
    }

    @Test
    void ofListIsImmutableForRemove() {
        BList<String> list = BList.of("a", "b", "c");

        assertThrows(UnsupportedOperationException.class, () -> {
            list.remove(0);
        });
    }

    @Test
    void ofListIsImmutableForSet() {
        BList<String> list = BList.of("x", "y");

        assertThrows(UnsupportedOperationException.class, () -> {
            list.set(0, "z");
        });
    }
}
