package com.example.queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args)   // unit testing (optional)
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> s = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            s.enqueue(string);
        }

        for (int i = 0; i < k; i ++) {
            StdOut.println(s.dequeue());
        }


    }

}
