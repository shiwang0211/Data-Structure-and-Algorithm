
package com.example.autocomplete;
import java.util.*;

public class BinarySearchDeluxe {


    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c)
    {
        // assume a is already sorted
        if(a == null || key == null || c == null)
            throw new NullPointerException("Argument is null");

        //corner 1
        if(a.length==0)
            return -1;

        //corner 2
        if(a.length==1) {
            if (c.compare(a[0], key) == 0)
                return 0;
            else
                return -1;
        }

        int first, last, mid;
        int result = -1;
        Key element;

        first = 0;
        last = a.length - 1;

        while(first <= last) {
            mid = (first + last) / 2;
            element = a[mid];
            if (c.compare(key, element) == 0)
            {   // Note how it differs from regular BS
                last = mid - 1;
                result = mid;
                //return mid;
            }

            if (c.compare(key, element) > 0)
                first = mid + 1;
            if (c.compare(key, element) < 0)
                last = mid - 1;
        }
        return result;




    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c)
    {
        // assume a is already sorted
        if(a == null || key == null || c == null)
            throw new NullPointerException("Argument is null");

        //corner 1
        if(a.length==0)
            return -1;

        //corner 2
        if(a.length==1) {
            if (c.compare(a[0], key) == 0)
                return 0;
            else
                return -1;
        }

        int first, last, mid;
        int result = -1;
        Key element;

        first = 0;
        last = a.length - 1;

        while(first <= last) {
            mid = (first + last) / 2;
            element = a[mid];
            if (c.compare(key, element) == 0)
            {// Note how it differs from regular BS
                first = mid + 1;
                result = mid;
                //return mid;
            }

            if (c.compare(key, element) > 0)
                first = mid + 1;
            if (c.compare(key, element) < 0)
                last = mid - 1;
        }
        return result;




    }


    // unit testing (required)
    public static void main(String[] args)
    {



    }
}