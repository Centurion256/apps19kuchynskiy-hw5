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
    private Integer last;

    public IntStreamFilterIterator(Iterator<Integer> wrapped, IntPredicate predicate) 
    {
        this.wrapped = wrapped;
        this.predicate = predicate;
        this.last = null;
        this.gotoNext();
    }

    public boolean hasNext()
    {
        return this.wrapped.hasNext();
    }

    private void gotoNext()
    {
        if (!this.wrapped.hasNext())
        {
            this.last = null;
            return;
        }
        this.last = this.wrapped.next();
        if (!this.predicate.test(this.last)) 
        {
            this.gotoNext();
        }
        

    }

    public Integer next()
    {
        Integer currentValue = this.last;
        if (currentValue == null)
        {
            throw new IllegalAccessError();
        }
        this.gotoNext();

        return currentValue;
    }
    

       
}