package org.apache.commons.lang3.concurrent;

public abstract interface Computable<I, O>
{
  public abstract O compute(I paramI)
    throws InterruptedException;
}
