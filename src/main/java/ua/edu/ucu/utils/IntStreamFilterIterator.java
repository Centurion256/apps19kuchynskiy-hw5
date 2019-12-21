package ua.edu.ucu.utils;

import java.util.Iterator;
import ua.edu.ucu.function.IntPredicate;

/**
 * IntStreamFilterIterator
 */
public class IntStreamFilterIterator implements Iterator<Integer>
{
    private Iterator<Integer> wrapped;
    private IntPredicate predicate;

    public IntStreamFilterIterator(Iterator<Integer> wrapped, IntPredicate predicate) 
    {
        this.wrapped = wrapped;
        this.predicate = predicate;
    }

    public boolean hasNext()
    {
        return this.wrapped.hasNext();
    }

    private void gotoNext(Integer currentValue)
    {
        while (!this.predicate.test(currentValue))
        {
            if (this.wrapped.hasNext())
            {
                this.wrapped.next();
            }
            else
            {
                break;
            }
        }
    }

    public Integer next()
    {
        Integer currentValue = this.wrapped.next();
        this.gotoNext(currentValue);
        return currentValue;
    }
    

       
}