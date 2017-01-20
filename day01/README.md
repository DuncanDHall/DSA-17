# `ArrayList`s

## Learning Goals

- Understand memory allocation of arrays
- Understand the need and implementation of dynamically sized arrays
- Be able to analyze time complexities of data structures

## `MyArrayList`

In this homework assignment, you are going to write your own implementation of an ArrayList.
```java
// MyArrayList.java
public class MyArrayList {
...
}
```

With each problem, we will add functionality to our classes to more closely match the real functionality of a real Java `ArrayList`.

Sidd would like to start a farm that only contains cows. He wants your help in creating an `ArrayList` to store these cows in.

You will be provided a `Cow` class that your `MyArrayList` will *exclusively* store. Each `Cow` has the following properties:
```java
// Cow.java
public class Cow {
    String name;
    int age;
    String color;
    ...
}
```
The tests you will be running on your `MyArrayList` class will be using this `Cow` class.

# Assignment

## Reading

- In *Think Algorithms*, read sections **3.4 and 4.3.** If you're having trouble with resizing in your `MyArrayList` implementation, read **4.4**

## Code

1. Sidd's cows can vary in number. Create a constructor for `MyArrayList` that accepts a `capacity` parameter and initializes a `MyArrayList` of length `capacity`.

    `public MyArrayList(int capacity)`

1. By default, Sidd will only raise 10 cows. Specify a new empty constructor the initializes the cow array's size to 10.

    `public MyArrayList()`

2. Implement a method called `add` that will add a Cow to the end of your `MyArrayList`. Assume Sidd will never go above `capacity`.

    `public void add(Cow c)`

3. How many cows does Sidd have? Implement a method called `size` that returns the number of cows currently in your farm. Make sure to make this an `O(1)` operation.

    `public int size()`

4. Implement a method called `get` that will return a `Cow` given the index of the `Cow`. Throw an `IndexOutOfBoundsException` if a `Cow` doesn't exist at the given index.

    `public Cow get(int index)`

5. Some cows might leave the farm for various reasons, and they might leave in any order. If a cow in the middle leaves, we want to shift all the cows over so there are no empty spaces. Implement a method called `remove` that will remove a cow. Return the removed `Cow` so we can "deal" with it.

    `public Cow remove(int index)`

6. How can we add a `Cow` in the middle of our `MyArrayList`? Overload the `add` function to take in an index as a first argument, and inserts a cow at that index. Note that we are not replacing any cows, just inserting one in between two other cows. Perform error handling here using `IndexOutOfBoundsException`.

    `public void add(int index, Cow c)`

7. Thanks to your help, Sidd's milk business has really gotten off the ground. He is able to expand his farm and is able to accept lots of cows. Modify your code so that we can **optimally** perform resizes. We never want to allocate more space than we need to, but we should also have an *average* insertion time complexity of `O(1)`. Make sure that your `MyArrayList` stays above 25% full. This will require halving the size of your array if it is less than 25% full, and doubling the size of it when it fills up. Think carefully about what your code will do: when down-sizing, make sure to have a "lower limit" so that the array does not eventually resize to length 1. You may find the function [`System.arraycopy`](https://docs.oracle.com/javase/7/docs/api/java/lang/System.html#arraycopy(java.lang.Object,%20int,%20java.lang.Object,%20int,%20int)) helpful.

## Totally Optional

If you're itching to write more code, try implementing one (or more) of the following in class problems in Java:

- Given an array with an odd number of elements, find the median without sorting.  
- We call an array circularly sorted if there exists some starting index after which the entire list is sorted (allows wrapping back around to the beginning). For instance, `[8,9,10,6,7]` is circularly sorted with starting index 3. `[3, 5, 6, 4, 5]` is not circularly sorted. Write a function that will return the starting index of the sorted (in ascending order) array, or -1 if there is none.
- Given an array of unique integers and an integer `n`, return the number of pairs of integers in that array who sum to the input `n`. Do not reuse elements. For example,`numPairs([1,7,3,4,5,2],8)` returns `2`: `[1,7]` and `[3,5]`. Note that it does not return `3`, because `[4, 4]` does not count.
- Find the longest sorted substring of an input array. Return an array of size 2: the starting and ending indices of said substring.
