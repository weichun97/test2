package com.github.weichun97.vavrtest;

import one.util.streamex.DoubleStreamEx;
import one.util.streamex.EntryStream;
import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class StreamExTest {

    @Test
    public void test3(){
        List<User> users = new ArrayList<>();
        List<String> userNames = StreamEx.of(users)
                .map(User::getName)
                .toList();

        Map<Role, List<User>> role2users = StreamEx.of(users)
                .groupingBy(User::getRole);

        assertEquals("1; 2; 3", StreamEx.of(1, 2, 3).joining("; "));

        List usersAndRoles = Arrays.asList(new User(), new Role());
        List<Role> roles = StreamEx.of(usersAndRoles)
                .select(Role.class)
                .toList();

        List<User> users2 = new ArrayList<>();
        users2.add(new User(1, "test"));
        List<String> appendedUsers = StreamEx.of(users2)
                .map(User::getName)
                .prepend("(none)")
                .append("LAST")
                .toList();

        List<User> users3 = new ArrayList<>();
        users3.add(new User(1, null));
        users3.add(new User(2, "张三"));
        for (String line : StreamEx.of(users3).map(User::getName).nonNull()) {
            System.out.println(line);
        }

        short[] src = {1,2,3};
        char[] output = IntStreamEx.of(src)
                .map(x -> x * 5)
                .toCharArray();

        double[] diffBetweenPairs = getDiffBetweenPairs(1, 2, 3, 5);

        Map<String, Role> nameToRole = new HashMap<>();
        nameToRole.put("first", new Role());
        nameToRole.put("second", null);
        Set<String> nonNullRoles = StreamEx.ofKeys(nameToRole, Objects::nonNull)
                .toSet();


        Map<User, List<Role>> users2roles = transformMap(StreamEx.of(io.vavr.collection.List.of(new User(1, "张三"),
                new User(2, "张三"),
                new User(3, "张三")
        ).toJavaList()).groupingBy(User::getRole));

        Map<Integer, String> mapToString = EntryStream.of(users2roles)
                .mapKeys(User::getId)
                .mapValues(String::valueOf)
                .toMap();

    }

    public Map<User, List<Role>> transformMap(
            Map<Role, List<User>> role2users) {
        Map<User, List<Role>> users2roles = EntryStream.of(role2users)
                .flatMapValues(List::stream)
                .invert()
                .grouping();
        return users2roles;
    }

    public double[] getDiffBetweenPairs(double... numbers) {
        return DoubleStreamEx.of(numbers)
                .pairMap((a, b) -> b - a)
                .toArray();
    }

    public static class User {
        int id;
        String name;
        Role role = new Role();

        // standard getters, setters, and constructors
        public User() {
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }
    }

    public static class Role {
    }
}
