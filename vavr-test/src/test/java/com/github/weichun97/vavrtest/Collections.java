package com.github.weichun97.vavrtest;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.*;
import org.junit.Test;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class Collections {

    @Test
    public void testList(){
        assert 6 == List.of(1, 2, 3).sum().intValue();
    }

    @Test
    public void testStream(){
        Stream<Integer> intStream = Stream.iterate(0, i -> i + 1)
                .take(10);
        assertEquals(10, intStream.size());

        long evenSum = intStream.filter(i -> i % 2 == 0)
                .sum()
                .longValue();
        assertEquals(20, evenSum);

        Stream<Integer> s1 = Stream.tabulate(5, (i)-> i + 1);
        assertEquals(s1.get(0).intValue(), 1);

        Stream<Integer> s = Stream.of(2,1,3,4);
        Stream<Tuple2<Integer, Integer>> s2 = s.zip(List.of(7,8,9));
        Tuple2<Integer, Integer> t1 = s2.get(0);
        assertEquals(t1._1().intValue(), 2);
        assertEquals(t1._2().intValue(), 7);
    }

    @Test
    public void testArray(){
        Array<Integer> rArray = Array.range(1, 5);
        assertFalse(rArray.contains(5));

        Array<Integer> rArray2 = Array.rangeClosed(1, 5);
        assertTrue(rArray2.contains(5));

        Array<Integer> rArray3 = Array.rangeClosedBy(1,6,2);
        assertEquals(rArray3.size(), 3);

        Array<Integer> intArray = Array.of(1, 2, 3);
        Array<Integer> newArray = intArray.removeAt(1);

        assertEquals(3, intArray.size());
        assertEquals(2, newArray.size());
        assertEquals(3, newArray.get(1).intValue());

        Array<Integer> array2 = intArray.replace(1, 5);
        assertEquals(array2.get(0).intValue(), 5);
    }

    @Test
    public void testVector(){
        Vector<Integer> intVector = Vector.range(1, 5);
        Vector<Integer> newVector = intVector.replace(2, 6);

        assertEquals(4, intVector.size());
        assertEquals(4, newVector.size());

        assertEquals(2, intVector.get(1).intValue());
        assertEquals(6, newVector.get(1).intValue());
    }

    @Test
    public void testCharSeq(){
        CharSeq chars = CharSeq.of("vavr");
        CharSeq newChars = chars.replace('v', 'V');

        assertEquals(4, chars.size());
        assertEquals(4, newChars.size());

        assertEquals('v', chars.charAt(0));
        assertEquals('V', newChars.charAt(0));
        assertEquals("Vavr", newChars.mkString());
    }

    @Test
    public void testHashSet(){
        HashSet<Integer> set0 = HashSet.rangeClosed(1,5);
        HashSet<Integer> set1 = HashSet.rangeClosed(3, 6);
        assertEquals(set0.union(set1), HashSet.rangeClosed(1,6));
        assertEquals(set0.diff(set1), HashSet.rangeClosed(1,2));
        assertEquals(set0.intersect(set1), HashSet.rangeClosed(3,5));

        HashSet<String> set = HashSet.of("Red", "Green", "Blue");
        HashSet<String> newSet = set.add("Yellow");
        assertEquals(3, set.size());
        assertEquals(4, newSet.size());
        assertTrue(newSet.contains("Yellow"));
    }

    @Test
    public void testTreeSet(){
        SortedSet<String> set = TreeSet.of("Red", "Green", "Blue");
        assertEquals("Blue", set.head());
        SortedSet<Integer> intSet = TreeSet.of(1,2,3);
        assertEquals(2, intSet.average().get().intValue());

        SortedSet<String> reversedSet
                = TreeSet.of(Comparator.reverseOrder(), "Green", "Red", "Blue");
        assertEquals("Red", reversedSet.head());
        String str = reversedSet.mkString(" and ");
        assertEquals("Red and Green and Blue", str);
    }

    @Test
    public void testBitSet(){
        BitSet<Integer> bitSet = BitSet.of(1,2,3,4,5,6,7,8);
        BitSet<Integer> bitSet1 = bitSet.takeUntil(i -> i > 4);
        assertEquals(bitSet1.size(), 4);
    }

    @Test
    public void testHashMap(){
        Map<Integer, List<Integer>> map = List.rangeClosed(0, 10)
                .groupBy(i -> i % 2);
        assertEquals(2, map.size());
        assertEquals(6, map.get(0).get().size());
        assertEquals(5, map.get(1).get().size());

        Map<String, String> map1
                = HashMap.of("key1", "val1", "key2", "val2", "key3", "val3");
        Map<String, String> fMap
                = map1.filterKeys(k -> k.contains("1") || k.contains("2"));
        assertFalse(fMap.containsKey("key3"));
        Map<String, String> fMap2
                = map1.filterValues(v -> v.contains("3"));
        assertEquals(fMap2.size(), 1);
        assertTrue(fMap2.containsValue("val3"));

        Map<String, Integer> map2 = map1.map(
                (k, v) -> Tuple.of(k, Integer.valueOf(v.charAt(v.length() - 1) + "")));
        assertEquals(map2.get("key1").get().intValue(), 1);
    }

    @Test
    public void testTreeMap(){
        SortedMap<Integer, String> map
                = TreeMap.of(3, "Three", 2, "Two", 4, "Four", 1, "One");
        assertEquals(1, map.keySet().toJavaArray()[0]);
        assertEquals("Four", map.get(4).get());
    }

    @Test
    public void testJava2Vavr(){
//        java.util.List<Integer> javaList = java.util.Arrays.asList(1, 2, 3, 4);
//        List<Integer> vavrList = List.ofAll(javaList);
//        java.util.stream.Stream<Integer> javaStream = javaList.stream();
//        Set<Integer> vavrSet = HashSet.ofAll(javaStream);

        List<Integer> vavrList = IntStream.range(1, 10)
                .boxed()
                .filter(i -> i % 2 == 0)
                .collect(List.collector());
        assertEquals(4, vavrList.size());
        assertEquals(2, vavrList.head().intValue());
    }

    @Test
    public void testVavr2Java(){
        Integer[] array = List.of(1, 2, 3)
                .toJavaArray(Integer.class);
        assertEquals(3, array.length);
        java.util.Map<String, Integer> map = List.of("1", "2", "3")
                .toJavaMap(i -> Tuple.of(i, Integer.valueOf(i)));
        assertEquals(2, map.get("2").intValue());

        java.util.Set<Integer> javaSet = List.of(1, 2, 3)
                .collect(Collectors.toSet());
        assertEquals(3, javaSet.size());
        assertEquals(1, javaSet.toArray()[0]);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void givenVavrList_whenViewConverted_thenException() {
        java.util.List<Integer> javaList = List.of(1, 2, 3)
                .asJava();
        assertEquals(3, javaList.get(2).intValue());
        javaList.add(4);
    }

    @Test
    public void givenVavrList_whenViewConverted() {
        java.util.List<Integer> javaList2 = List.of(1, 2, 3)
                .asJavaMutable();
        javaList2.add(4);
        assertEquals(4, javaList2.get(3).intValue());
    }
}
