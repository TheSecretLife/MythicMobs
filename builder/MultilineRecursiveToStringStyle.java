package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.ClassUtils;



































































public class MultilineRecursiveToStringStyle
  extends RecursiveToStringStyle
{
  private static final long serialVersionUID = 1L;
  private static final int INDENT = 2;
  private int spaces = 2;
  



  public MultilineRecursiveToStringStyle()
  {
    resetIndent();
  }
  



  private void resetIndent()
  {
    setArrayStart("{" + System.lineSeparator() + spacer(spaces));
    setArraySeparator("," + System.lineSeparator() + spacer(spaces));
    setArrayEnd(System.lineSeparator() + spacer(spaces - 2) + "}");
    
    setContentStart("[" + System.lineSeparator() + spacer(spaces));
    setFieldSeparator("," + System.lineSeparator() + spacer(spaces));
    setContentEnd(System.lineSeparator() + spacer(spaces - 2) + "]");
  }
  





  private StringBuilder spacer(int spaces)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < spaces; i++) {
      sb.append(" ");
    }
    return sb;
  }
  
  public void appendDetail(StringBuffer buffer, String fieldName, Object value)
  {
    if ((!ClassUtils.isPrimitiveWrapper(value.getClass())) && (!String.class.equals(value.getClass())) && 
      (accept(value.getClass()))) {
      spaces += 2;
      resetIndent();
      buffer.append(ReflectionToStringBuilder.toString(value, this));
      spaces -= 2;
      resetIndent();
    } else {
      super.appendDetail(buffer, fieldName, value);
    }
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, Object[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void reflectionAppendArrayDetail(StringBuffer buffer, String fieldName, Object array)
  {
    spaces += 2;
    resetIndent();
    super.reflectionAppendArrayDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, long[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, int[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, short[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, byte[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, char[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, double[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, float[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
  
  protected void appendDetail(StringBuffer buffer, String fieldName, boolean[] array)
  {
    spaces += 2;
    resetIndent();
    super.appendDetail(buffer, fieldName, array);
    spaces -= 2;
    resetIndent();
  }
}
