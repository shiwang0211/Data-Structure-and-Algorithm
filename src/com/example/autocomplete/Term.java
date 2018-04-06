package com.example.autocomplete;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Term implements Comparable<Term> {

    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight)
    {
        this.query = query;
        this.weight = weight;
    }

    // private static nested class
    private static class ByReverseWeightOrder implements Comparator<Term>
    {
        @Override
        public int compare(Term a, Term b)
        {
            Long x = a.weight;
            Long y = b.weight;
            return -x.compareTo(y); //descending order
        }
    }

    // private static nested class
    private static class ByPrefixOrder implements Comparator<Term> //implement interface
    {
        //take the value of r when initializing it
        private int r;

        public ByPrefixOrder(int r) {
            this.r = r;
        }

        @Override
        public int compare(Term v, Term w) {
            String a = "", b = "";
            if(v.query.length() < r)
                a = v.query;
            else
                a = v.query.substring(0, r);

            if(w.query.length() < r)
                b = w.query;
            else
                b = w.query.substring(0, r);
            return a.compareTo(b);
        }
    }


    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() //The return type is still Comparator<Term>
    {
        //initialize a nested static class, which implements interface with Comparator<Term>
        return new ByReverseWeightOrder();
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r)
    {
        return new ByPrefixOrder(r);
    }


    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that)
    {
        return(this.query.compareTo(that.query));
    }


    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString()
    {
        return (weight + "\t" + query);
    }

    // unit testing (required)
    public static void main(String[] args)
    {


    }
}
