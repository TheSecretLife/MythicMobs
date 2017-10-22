package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.EnumSet;























@Deprecated
public class NumericEntityUnescaper
  extends CharSequenceTranslator
{
  private final EnumSet<OPTION> options;
  
  public static enum OPTION
  {
    semiColonRequired,  semiColonOptional,  errorIfNoSemiColon;
    








    private OPTION() {}
  }
  







  public NumericEntityUnescaper(OPTION... options)
  {
    if (options.length > 0) {
      this.options = EnumSet.copyOf(Arrays.asList(options));
    } else {
      this.options = EnumSet.copyOf(Arrays.asList(new OPTION[] { OPTION.semiColonRequired }));
    }
  }
  





  public boolean isSet(OPTION option)
  {
    return (options != null) && (options.contains(option));
  }
  


  public int translate(CharSequence input, int index, Writer out)
    throws IOException
  {
    int seqEnd = input.length();
    
    if ((input.charAt(index) == '&') && (index < seqEnd - 2) && (input.charAt(index + 1) == '#')) {
      int start = index + 2;
      boolean isHex = false;
      
      char firstChar = input.charAt(start);
      if ((firstChar == 'x') || (firstChar == 'X')) {
        start++;
        isHex = true;
        

        if (start == seqEnd) {
          return 0;
        }
      }
      
      int end = start;
      
      while ((end < seqEnd) && (((input.charAt(end) >= '0') && (input.charAt(end) <= '9')) || 
        ((input.charAt(end) >= 'a') && (input.charAt(end) <= 'f')) || (
        (input.charAt(end) >= 'A') && (input.charAt(end) <= 'F')))) {
        end++;
      }
      
      boolean semiNext = (end != seqEnd) && (input.charAt(end) == ';');
      
      if (!semiNext) {
        if (isSet(OPTION.semiColonRequired)) {
          return 0;
        }
        if (isSet(OPTION.errorIfNoSemiColon)) {
          throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
        }
      }
      try
      {
        int entityValue;
        if (isHex) {
          entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 16);
        } else
          entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 10);
      } catch (NumberFormatException nfe) {
        int entityValue;
        return 0;
      }
      int entityValue;
      if (entityValue > 65535) {
        char[] chars = Character.toChars(entityValue);
        out.write(chars[0]);
        out.write(chars[1]);
      } else {
        out.write(entityValue);
      }
      
      return 2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
    }
    return 0;
  }
}
