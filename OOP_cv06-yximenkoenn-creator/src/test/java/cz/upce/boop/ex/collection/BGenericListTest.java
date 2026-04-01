package cz.upce.boop.ex.collection;

import cz.upce.boop.ex.collection.BGenericList;
import cz.upce.boop.ex.collection.BList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BGenericListTest {

    private BGenericList<String> list;

    @BeforeEach
    public void setUp() {
        list = new BGenericList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
    }

    @Test
    public void test1() {
        assertEquals("one", list.get(0));

    }

    @Test
    public void test2() {
        assertEquals(4, list.size());

    }

    @Test
    public void test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.get(-1);
        });
    }

    @Test
    public void test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.get(5);
        });
    }

    @ParameterizedTest
    @CsvSource({
        "0, one",
        "1, two",
        "2, three",
        "3, four"
    })
    public void test5(int index, String expected) {
        assertEquals(expected, list.get(index));
    }

    @Test
    public void test6() {
        list.add("five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
    }

    @Test
    public void test7() {
        BList<String> readOnly = list.asReadOnly();
        assertInstanceOf(BGenericList.BReadOnlyList.class, readOnly);

    }

    @Test
    public void test8() {
        BList<String> readOnly = list.asReadOnly();
        BGenericList<String> expected = new BGenericList<>();
        expected.add("one");
        expected.add("two");
        expected.add("three");
        expected.add("four");
        assertIterableEquals(expected, readOnly);
    }

    @Test
    public void test9() {
        BList<String> readOnly = list.asReadOnly();
        list.add("five");
        BGenericList<String> expected = new BGenericList<>();
        expected.add("one");
        expected.add("two");
        expected.add("three");
        expected.add("four");
        assertIterableEquals(expected, readOnly);

    }

    @Test
    public void test10() {
        BList<String> readOnly = list.asReadOnly();
        assertThrows(UnsupportedOperationException.class, () -> {
            readOnly.add("five");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            readOnly.set(0, "x");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            readOnly.remove(0);
        });
    }
}
