package org.apache.commons.lang3.concurrent;













































































public abstract class LazyInitializer<T>
  implements ConcurrentInitializer<T>
{
  private static final Object NO_INIT = new Object();
  
  private volatile T object = NO_INIT;
  





  public LazyInitializer() {}
  




  public T get()
    throws ConcurrentException
  {
    T result = object;
    
    if (result == NO_INIT) {
      synchronized (this) {
        result = object;
        if (result == NO_INIT) {
          object = (result = initialize());
        }
      }
    }
    
    return result;
  }
  
  protected abstract T initialize()
    throws ConcurrentException;
}
