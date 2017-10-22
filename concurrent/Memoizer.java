package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;











































public class Memoizer<I, O>
  implements Computable<I, O>
{
  private final ConcurrentMap<I, Future<O>> cache = new ConcurrentHashMap();
  



  private final Computable<I, O> computable;
  



  private final boolean recalculate;
  




  public Memoizer(Computable<I, O> computable)
  {
    this(computable, false);
  }
  












  public Memoizer(Computable<I, O> computable, boolean recalculate)
  {
    this.computable = computable;
    this.recalculate = recalculate;
  }
  
















  public O compute(final I arg)
    throws InterruptedException
  {
    for (;;)
    {
      Future<O> future = (Future)cache.get(arg);
      if (future == null) {
        Callable<O> eval = new Callable()
        {
          public O call() throws InterruptedException
          {
            return computable.compute(arg);
          }
        };
        FutureTask<O> futureTask = new FutureTask(eval);
        future = (Future)cache.putIfAbsent(arg, futureTask);
        if (future == null) {
          future = futureTask;
          futureTask.run();
        }
      }
      try {
        return future.get();
      } catch (CancellationException e) {
        cache.remove(arg, future);
      } catch (ExecutionException e) {
        if (recalculate) {
          cache.remove(arg, future);
        }
        
        throw launderException(e.getCause());
      }
    }
  }
  









  private RuntimeException launderException(Throwable throwable)
  {
    if ((throwable instanceof RuntimeException))
      return (RuntimeException)throwable;
    if ((throwable instanceof Error)) {
      throw ((Error)throwable);
    }
    throw new IllegalStateException("Unchecked exception", throwable);
  }
}
