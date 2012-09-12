package com.oracle.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.functions.Block;
import java.util.streams.StreamBuilder;

public class StreamAPITest {

    @Test
    public void test() {
        Assert.assertEquals(
                "Foo",
                Arrays.asList("Foo", "Bar", "Baz").stream().findFirst().get()
        );
    }

    @Test
    public void test1() {
        Assert.assertEquals(
                Arrays.asList("Bar", "Baz"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .filter((s) -> s.startsWith("B"))
                        .into(new ArrayList<String>()
                        )
        );
    }

    @Test
    public void test2() {
        Assert.assertEquals(
                Arrays.asList(3, 3, 3),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .map((s) -> s.length())
                        .into(new ArrayList<Integer>())
        );
    }

    @Test
    public void test3() {
        Assert.assertEquals(
                Integer.valueOf(9),
                Arrays.asList("Foo", "BarBar", "BazBazBaz")
                        .stream()
                        .map(s -> s.length())
                        .reduce((l, r) -> (l > r ? l : r))
                        .get()
        );
    }

    @Test
    public void test5() {
        Assert.assertEquals(
                Arrays.asList("Foo", "Bar", "Baz"),
                Arrays.asList("Foo Bar Baz")
                        .stream()
                        .flatMap((Block<? super String> sink, String element) -> Arrays.asStream(element.split(" ")).forEach(sink))
                        .into(new ArrayList<String>()));
    }

    @Test
    public void test6() {
        Assert.assertEquals(
                new HashSet<String>() {{ add("Foo"); add("Bar"); add("Baz"); }},
                Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar")
                        .stream()
                        .uniqueElements()
                        .into(new HashSet<String>())
        );
    }

    @Test
    public void test7() {
        Assert.assertEquals(
                Arrays.asList("Bar", "Baz", "Foo"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .sorted((o1, o2) -> o1.compareTo(o2))
                        .into(new ArrayList<String>())
        );
    }

    @Test
    public void test8() {
        Assert.assertEquals(
                Arrays.asList("Foo", "FooBar", "FooBarBaz"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .cumulate((l, r) -> l + r)
                        .into(new ArrayList<String>())
        );
    }




}
