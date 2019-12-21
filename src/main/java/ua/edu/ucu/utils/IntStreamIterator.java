package ua.edu.ucu.utils;

import java.util.Iterator;

/**
 * IntStreamIterator
 */
public class IntStreamIterator implements Iterator<Integer>{

    private int[] array;
    private int index = 0;

    public IntStreamIterator(int[] values) 
    {
        this.array = values;
    }

    public boolean hasNext()
    {
        return this.index < this.array.length;
    }

    private void gotoNext()
    {
        this.index += 1;
    }

    public Integer next()
    {
        Integer currentValue = this.array[this.index];
        this.gotoNext();
        return currentValue;
    }
    
}