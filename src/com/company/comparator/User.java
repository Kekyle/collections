package com.company.comparator;

import java.util.TreeSet;

public class User
//        implements Comparable<User>
{

//    public static void main(String[] args) {
//        TreeSet<User> users = new TreeSet<>(new UserComparator());
//        users.add(new User("Test 1", 22));
//        users.add(new User("Test 4", 19));
//        users.add(new User("Test 3", 14));
//        users.add(new User("Test 5", 33));
//        System.out.println(users);
//    }

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public int compareTo(User o) {
//        return this.name.compareTo(o.name);
//    }
}
