package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.reflect.FieldUtils;


















































































public class ReflectionDiffBuilder
  implements Builder<DiffResult>
{
  private final Object left;
  private final Object right;
  private final DiffBuilder diffBuilder;
  
  public <T> ReflectionDiffBuilder(T lhs, T rhs, ToStringStyle style)
  {
    left = lhs;
    right = rhs;
    diffBuilder = new DiffBuilder(lhs, rhs, style);
  }
  
  public DiffResult build()
  {
    if (left.equals(right)) {
      return diffBuilder.build();
    }
    
    appendFields(left.getClass());
    return diffBuilder.build();
  }
  
  private void appendFields(Class<?> clazz) {
    for (Field field : FieldUtils.getAllFields(clazz)) {
      if (accept(field)) {
        try {
          diffBuilder.append(field.getName(), FieldUtils.readField(field, left, true), 
            FieldUtils.readField(field, right, true));
        }
        catch (IllegalAccessException ex)
        {
          throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
        }
      }
    }
  }
  
  private boolean accept(Field field) {
    if (field.getName().indexOf('$') != -1) {
      return false;
    }
    if (Modifier.isTransient(field.getModifiers())) {
      return false;
    }
    return !Modifier.isStatic(field.getModifiers());
  }
}
