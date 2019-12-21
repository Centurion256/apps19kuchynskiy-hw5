package ua.edu.ucu.utils;

import java.util.Iterator;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntUnaryOperator;

/**
 * IntStreamFilterIterator
 */
public class IntStreamMapIterator implements Iterator<Integer>
{
    private Iterator<Integer> wrapped;
    private IntUnaryOperator func;

    public IntStreamMapIterator(Iterator<Integer> wrapped, IntUnaryOperator func) 
    {
        this.wrapped = wrapped;
        this.func = func;
    }

    public boolean hasNext()
    {
        return this.wrapped.hasNext();
    }

    public Integer next()
    {
        Integer currentValue = this.wrapped.next();
        return this.func.apply(currentValue);
    }
    

       
}