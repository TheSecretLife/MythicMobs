package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerializationUtils
{
  public SerializationUtils() {}
  
  /* Error */
  public static <T extends Serializable> T clone(T object)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: invokestatic 2	org/apache/commons/lang3/SerializationUtils:serialize	(Ljava/io/Serializable;)[B
    //   10: astore_1
    //   11: new 3	java/io/ByteArrayInputStream
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 4	java/io/ByteArrayInputStream:<init>	([B)V
    //   19: astore_2
    //   20: new 5	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream
    //   23: dup
    //   24: aload_2
    //   25: aload_0
    //   26: invokevirtual 6	java/lang/Object:getClass	()Ljava/lang/Class;
    //   29: invokevirtual 7	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   32: invokespecial 8	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream:<init>	(Ljava/io/InputStream;Ljava/lang/ClassLoader;)V
    //   35: astore_3
    //   36: aconst_null
    //   37: astore 4
    //   39: aload_3
    //   40: invokevirtual 9	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream:readObject	()Ljava/lang/Object;
    //   43: checkcast 10	java/io/Serializable
    //   46: astore 5
    //   48: aload 5
    //   50: astore 6
    //   52: aload_3
    //   53: ifnull +31 -> 84
    //   56: aload 4
    //   58: ifnull +22 -> 80
    //   61: aload_3
    //   62: invokevirtual 11	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream:close	()V
    //   65: goto +19 -> 84
    //   68: astore 7
    //   70: aload 4
    //   72: aload 7
    //   74: invokevirtual 13	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   77: goto +7 -> 84
    //   80: aload_3
    //   81: invokevirtual 11	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream:close	()V
    //   84: aload 6
    //   86: areturn
    //   87: astore 5
    //   89: aload 5
    //   91: astore 4
    //   93: aload 5
    //   95: athrow
    //   96: astore 8
    //   98: aload_3
    //   99: ifnull +31 -> 130
    //   102: aload 4
    //   104: ifnull +22 -> 126
    //   107: aload_3
    //   108: invokevirtual 11	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream:close	()V
    //   111: goto +19 -> 130
    //   114: astore 9
    //   116: aload 4
    //   118: aload 9
    //   120: invokevirtual 13	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   123: goto +7 -> 130
    //   126: aload_3
    //   127: invokevirtual 11	org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream:close	()V
    //   130: aload 8
    //   132: athrow
    //   133: astore_3
    //   134: new 15	org/apache/commons/lang3/SerializationException
    //   137: dup
    //   138: ldc 16
    //   140: aload_3
    //   141: invokespecial 17	org/apache/commons/lang3/SerializationException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   144: athrow
    //   145: astore_3
    //   146: new 15	org/apache/commons/lang3/SerializationException
    //   149: dup
    //   150: ldc 19
    //   152: aload_3
    //   153: invokespecial 17	org/apache/commons/lang3/SerializationException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   156: athrow
    // Line number table:
    //   Java source line #78	-> byte code offset #0
    //   Java source line #79	-> byte code offset #4
    //   Java source line #81	-> byte code offset #6
    //   Java source line #82	-> byte code offset #11
    //   Java source line #84	-> byte code offset #20
    //   Java source line #85	-> byte code offset #26
    //   Java source line #84	-> byte code offset #36
    //   Java source line #92	-> byte code offset #39
    //   Java source line #93	-> byte code offset #48
    //   Java source line #95	-> byte code offset #52
    //   Java source line #93	-> byte code offset #84
    //   Java source line #84	-> byte code offset #87
    //   Java source line #95	-> byte code offset #96
    //   Java source line #96	-> byte code offset #134
    //   Java source line #97	-> byte code offset #145
    //   Java source line #98	-> byte code offset #146
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	object	T
    //   10	6	1	objectData	byte[]
    //   19	6	2	bais	ByteArrayInputStream
    //   35	92	3	in	ClassLoaderAwareObjectInputStream
    //   133	8	3	ex	ClassNotFoundException
    //   145	8	3	ex	IOException
    //   37	80	4	localThrowable3	Throwable
    //   46	3	5	readObject	T
    //   87	7	5	localThrowable1	Throwable
    //   68	5	7	localThrowable	Throwable
    //   96	35	8	localObject	Object
    //   114	5	9	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   61	65	68	java/lang/Throwable
    //   39	52	87	java/lang/Throwable
    //   39	52	96	finally
    //   87	98	96	finally
    //   107	111	114	java/lang/Throwable
    //   20	84	133	java/lang/ClassNotFoundException
    //   87	133	133	java/lang/ClassNotFoundException
    //   20	84	145	java/io/IOException
    //   87	133	145	java/io/IOException
  }
  
  public static <T extends Serializable> T roundtrip(T msg)
  {
    return (Serializable)deserialize(serialize(msg));
  }
  
















  public static void serialize(Serializable obj, OutputStream outputStream)
  {
    Validate.isTrue(outputStream != null, "The OutputStream must not be null", new Object[0]);
    try { ObjectOutputStream out = new ObjectOutputStream(outputStream);Throwable localThrowable3 = null;
      try { out.writeObject(obj);
      }
      catch (Throwable localThrowable1)
      {
        localThrowable3 = localThrowable1;throw localThrowable1;
      } finally {
        if (out != null) if (localThrowable3 != null) try { out.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else out.close();
      } } catch (IOException ex) { throw new SerializationException(ex);
    }
  }
  







  public static byte[] serialize(Serializable obj)
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
    serialize(obj, baos);
    return baos.toByteArray();
  }
  
  /* Error */
  public static <T> T deserialize(InputStream inputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: iconst_1
    //   5: goto +4 -> 9
    //   8: iconst_0
    //   9: ldc 33
    //   11: iconst_0
    //   12: anewarray 22	java/lang/Object
    //   15: invokestatic 23	org/apache/commons/lang3/Validate:isTrue	(ZLjava/lang/String;[Ljava/lang/Object;)V
    //   18: new 34	java/io/ObjectInputStream
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 35	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   26: astore_1
    //   27: aconst_null
    //   28: astore_2
    //   29: aload_1
    //   30: invokevirtual 36	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   33: astore_3
    //   34: aload_3
    //   35: astore 4
    //   37: aload_1
    //   38: ifnull +29 -> 67
    //   41: aload_2
    //   42: ifnull +21 -> 63
    //   45: aload_1
    //   46: invokevirtual 37	java/io/ObjectInputStream:close	()V
    //   49: goto +18 -> 67
    //   52: astore 5
    //   54: aload_2
    //   55: aload 5
    //   57: invokevirtual 13	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   60: goto +7 -> 67
    //   63: aload_1
    //   64: invokevirtual 37	java/io/ObjectInputStream:close	()V
    //   67: aload 4
    //   69: areturn
    //   70: astore_3
    //   71: aload_3
    //   72: astore_2
    //   73: aload_3
    //   74: athrow
    //   75: astore 6
    //   77: aload_1
    //   78: ifnull +29 -> 107
    //   81: aload_2
    //   82: ifnull +21 -> 103
    //   85: aload_1
    //   86: invokevirtual 37	java/io/ObjectInputStream:close	()V
    //   89: goto +18 -> 107
    //   92: astore 7
    //   94: aload_2
    //   95: aload 7
    //   97: invokevirtual 13	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   100: goto +7 -> 107
    //   103: aload_1
    //   104: invokevirtual 37	java/io/ObjectInputStream:close	()V
    //   107: aload 6
    //   109: athrow
    //   110: astore_1
    //   111: new 15	org/apache/commons/lang3/SerializationException
    //   114: dup
    //   115: aload_1
    //   116: invokespecial 28	org/apache/commons/lang3/SerializationException:<init>	(Ljava/lang/Throwable;)V
    //   119: athrow
    // Line number table:
    //   Java source line #191	-> byte code offset #0
    //   Java source line #192	-> byte code offset #18
    //   Java source line #194	-> byte code offset #29
    //   Java source line #195	-> byte code offset #34
    //   Java source line #196	-> byte code offset #37
    //   Java source line #195	-> byte code offset #67
    //   Java source line #192	-> byte code offset #70
    //   Java source line #196	-> byte code offset #75
    //   Java source line #197	-> byte code offset #111
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	inputStream	InputStream
    //   26	78	1	in	ObjectInputStream
    //   110	6	1	ex	Exception
    //   28	67	2	localThrowable3	Throwable
    //   33	2	3	obj	T
    //   70	4	3	localThrowable1	Throwable
    //   52	4	5	localThrowable	Throwable
    //   75	33	6	localObject	Object
    //   92	4	7	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   45	49	52	java/lang/Throwable
    //   29	37	70	java/lang/Throwable
    //   29	37	75	finally
    //   70	77	75	finally
    //   85	89	92	java/lang/Throwable
    //   18	67	110	java/lang/ClassNotFoundException
    //   18	67	110	java/io/IOException
    //   70	110	110	java/lang/ClassNotFoundException
    //   70	110	110	java/io/IOException
  }
  
  public static <T> T deserialize(byte[] objectData)
  {
    Validate.isTrue(objectData != null, "The byte[] must not be null", new Object[0]);
    return deserialize(new ByteArrayInputStream(objectData));
  }
  











  static class ClassLoaderAwareObjectInputStream
    extends ObjectInputStream
  {
    private static final Map<String, Class<?>> primitiveTypes = new HashMap();
    private final ClassLoader classLoader;
    
    static {
      primitiveTypes.put("byte", Byte.TYPE);
      primitiveTypes.put("short", Short.TYPE);
      primitiveTypes.put("int", Integer.TYPE);
      primitiveTypes.put("long", Long.TYPE);
      primitiveTypes.put("float", Float.TYPE);
      primitiveTypes.put("double", Double.TYPE);
      primitiveTypes.put("boolean", Boolean.TYPE);
      primitiveTypes.put("char", Character.TYPE);
      primitiveTypes.put("void", Void.TYPE);
    }
    







    ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader)
      throws IOException
    {
      super();
      this.classLoader = classLoader;
    }
    







    protected Class<?> resolveClass(ObjectStreamClass desc)
      throws IOException, ClassNotFoundException
    {
      String name = desc.getName();
      try {
        return Class.forName(name, false, classLoader);
      } catch (ClassNotFoundException ex) {
        try {
          return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException cnfe) {
          Class<?> cls = (Class)primitiveTypes.get(name);
          if (cls != null) {
            return cls;
          }
          throw cnfe;
        }
      }
    }
  }
}
