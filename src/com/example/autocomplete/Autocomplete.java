package com.example.autocomplete;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.*;
import java.lang.*;

public class Autocomplete {

    private Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms)
    {
        this.terms = terms;
        Arrays.sort(terms); //uses Term.compareTo(that) -- Comparable
        // for all other classes, the terms is already sorted lexicographically

    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix)
    {
        //slice out the terms with given prefix
        Comparator<Term> c_pf = Term.byPrefixOrder(prefix.length());
        int first = BinarySearchDeluxe.firstIndexOf(terms,
                new Term(prefix, 0),
                c_pf);
        int last = BinarySearchDeluxe.lastIndexOf(terms,
                new Term(prefix, 0),
                c_pf);

        //make a copy of sliced terms

        Term[] temp_term = new Term[last-first+1];
        for(int i=first; i<=last;i++)
        {
            temp_term[i-first] = terms[i];
        }

        //order them based on weight
        Comparator<Term> c_rw = Term.byReverseWeightOrder();
        Arrays.sort(temp_term, c_rw);

        return temp_term;

    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix)
    {
        int r = prefix.length();
        Comparator<Term> c_pf = Term. byPrefixOrder(r);

        int first = BinarySearchDeluxe.firstIndexOf(terms,
                new Term(prefix, 0),
                c_pf);
        int last = BinarySearchDeluxe.lastIndexOf(terms,
                new Term(prefix, 0),
                c_pf);
        StdOut.printf("first");
        return last - first + 1;

    }
    // unit testing (required)
    public static void main(String[] args)
    {
        //Below is provided on the assignment website
        //
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }


        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }


    }
}