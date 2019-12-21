package ua.edu.ucu.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ua.edu.ucu.function.*;
import ua.edu.ucu.utils.*;

public class AsIntStream implements IntStream {

    private Iterator<Integer> content;

    private AsIntStream(int[] values) {
        
        this.content = new IntStreamIterator(values);

    }

    private AsIntStream(Iterator<Integer> iterator)
    {
        this.content = iterator;
    }

    public static IntStream of(int... values) 
    {
        return new AsIntStream(values);
    }

    @Override
    public Double average() 
    {
        int sum = 0;
        int amount = 0;
        while (this.content.hasNext())
        {
            sum += this.content.next();
            amount++;
        }
        return (Double) (double) sum / amount;
    }


    @Override
    public Integer max() 
    {
        int max = Integer.MIN_VALUE;
        int nextVal = 0;
        while (this.content.hasNext())
        {
            nextVal = this.content.next();
            if (nextVal > max)
            {
                max = nextVal;
            }
        }
        return (Integer) max;
    }

    @Override
    public Integer min() 
    {
        int min = Integer.MAX_VALUE;
        int nextVal = 0;
        while (this.content.hasNext())
        {
            nextVal = this.content.next();
            if (nextVal < min)
            {
                min = nextVal;
            }
        }
        return (Integer) min;
    }

    @Override
    public long count() 
    {
        long length = 0;
        while (this.content.hasNext())
        {
            this.content.next();
            length++;
        }
        return length;
    }

    @Override
    public Integer sum() 
    {
        int total = 0;
        int nextVal = 0;
        while (this.content.hasNext())
        {
            nextVal = this.content.next();
            total += nextVal;
        }
        return (Integer) total;
    }

    @Override
    public IntStream filter(IntPredicate predicate) 
    {
        return new AsIntStream(new IntStreamFilterIterator(this.content, predicate));
    }

    @Override
    public void forEach(IntConsumer action) 
    {
        while (this.content.hasNext())
        {
            action.accept(this.content.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new IntStreamMapIterator(this.content, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) 
    {
        return new AsIntStream(new IntStreamFlatMapIterator(this.content, func));    
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) 
    {
        int convolution = identity;
        while (this.content.hasNext())
        {
            convolution = op.apply(convolution, this.content.next());
        }
        return convolution;
    }

    @Override
    public int[] toArray() 
    {
        List<Integer> tempList = new ArrayList<>();
        while (this.content.hasNext())
        {
            tempList.add(this.content.next());
        }
        int[] result = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++)
        {
            result[i] = tempList.get(i);
        }
        return result;
    }

}
