package com.example.queue;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        arr = (Item[]) new Object[1]; //Implementing a generic collection in Effective Java
        size = 0; //also the last index

    }
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return size == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return size;
    }

    public int arrlen()
    {
        return arr.length;
    }

    private void resize(int cap)
    {
        Item[] copy = (Item[]) new Object[cap];//Implementing a generic collection
        for(int i = 0; i< size; i++)
            copy[i] = arr[i];
        arr = copy;
    }
    public void enqueue(Item item)           // add the item
    {
        if(size == arr.length) resize(2 * arr.length);
        arr[size++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    {
        if(size == 0) throw new NoSuchElementException();
        int temp = StdRandom.uniform(0, size);
        Item item = arr[temp];

        arr[temp] = arr[size-1];// use the last one to fill in the random gap
        arr[size-1] = null; //set last one to null
        size--;

        if(size > 0 && size == arr.length/4) //size is the actual number of items
            resize(arr.length/2);

        return item;

    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if(size == 0) throw new NoSuchElementException();
        int temp = StdRandom.uniform(0, size);
        return arr[temp];

    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();

    }


    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int[] perm; // gives random permutation of index;
        private int count;

        public RandomizedQueueIterator(){ //constructor
            if(size == 0) throw new NoSuchElementException();

            perm = new int[size];
            for(int i=0;i<size;i++)
                perm[i] = i;
            StdRandom.shuffle(perm);
            count = 0;
        }
        public boolean hasNext() { return count<size;}
        public void remove() { /*not supported */}
        public Item next()
        {
            return arr[perm[count++]];
        }
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        RandomizedQueue<String> arr = new RandomizedQueue<String>();
        System.out.println("Array size is: " + arr.size + ", Array Length is:" + arr.arrlen());
        arr.enqueue("aaa");
        arr.enqueue("bbb");
        arr.enqueue("ccc");
        System.out.println("Array size is: " + arr.size + ", Array Length is:" + arr.arrlen());
        arr.enqueue("ddd");
        arr.enqueue("eee");
        arr.enqueue("ccc");
        System.out.println("Array size is: " + arr.size + ", Array Length is:" + arr.arrlen());

        for(String s : arr)
        {
            System.out.println("Iterator with random order: " + s);
        }

        System.out.println("Random Sample " + arr.sample());
        System.out.println("Random Sample " + arr.sample());
        System.out.println("Random Sample " + arr.sample());
        System.out.println("Random Sample " + arr.sample());

        System.out.println("Dequeued Sample " + arr.dequeue());
        System.out.println("Dequeued Sample " + arr.dequeue());
        System.out.println("Dequeued Sample " + arr.dequeue());
        System.out.println("Array size is: " + arr.size + ", Array Length is:" + arr.arrlen());
        System.out.println("Dequeued Sample " + arr.dequeue());
        System.out.println("Array size is: " + arr.size + ", Array Length is:" + arr.arrlen());

        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }

    }
}