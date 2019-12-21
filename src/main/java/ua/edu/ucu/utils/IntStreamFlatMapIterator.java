package ua.edu.ucu.utils;

import java.util.Iterator;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.stream.IntStream;

/**
 * IntStreamFilterIterator
 */
public class IntStreamFlatMapIterator implements Iterator<Integer>
{
    private Iterator<Integer> wrapped;
    private IntToIntStreamFunction mapper;
    private int localIndex = 0;
    private int[] localArray = new int[0];

    public IntStreamFlatMapIterator(Iterator<Integer> wrapped, IntToIntStreamFunction mapper) 
    {
        this.wrapped = wrapped;
        this.mapper = mapper;
    }

    public boolean hasNext()
    {
        return this.wrapped.hasNext();
    }

    private void gotoNext()
    {
        this.localIndex++;
        if (localIndex >= localArray.length)
        {
            int nextValue = this.wrapped.next();
            IntStream stream = this.mapper.applyAsIntStream(nextValue);
            this.localArray = stream.toArray();
            this.localIndex = 0;
        }

    }

    public Integer next()
    {
        this.gotoNext();
        return this.localArray[this.localIndex];
    }
    

       
}