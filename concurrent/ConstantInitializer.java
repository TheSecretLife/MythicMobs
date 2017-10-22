package org.apache.commons.lang3.concurrent;

import java.util.Objects;













































public class ConstantInitializer<T>
  implements ConcurrentInitializer<T>
{
  private static final String FMT_TO_STRING = "ConstantInitializer@%d [ object = %s ]";
  private final T object;
  
  public ConstantInitializer(T obj)
  {
    object = obj;
  }
  






  public final T getObject()
  {
    return object;
  }
  






  public T get()
    throws ConcurrentException
  {
    return getObject();
  }
  






  public int hashCode()
  {
    return getObject() != null ? getObject().hashCode() : 0;
  }
  









  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ConstantInitializer)) {
      return false;
    }
    
    ConstantInitializer<?> c = (ConstantInitializer)obj;
    return Objects.equals(getObject(), c.getObject());
  }
  







  public String toString()
  {
    return String.format("ConstantInitializer@%d [ object = %s ]", new Object[] { Integer.valueOf(System.identityHashCode(this)), 
      String.valueOf(getObject()) });
  }
}
