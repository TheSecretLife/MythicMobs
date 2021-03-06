package org.apache.commons.lang3;


























public class ClassPathUtils
{
  public ClassPathUtils() {}
  
























  public static String toFullyQualifiedName(Class<?> context, String resourceName)
  {
    Validate.notNull(context, "Parameter '%s' must not be null!", new Object[] { "context" });
    Validate.notNull(resourceName, "Parameter '%s' must not be null!", new Object[] { "resourceName" });
    return toFullyQualifiedName(context.getPackage(), resourceName);
  }
  















  public static String toFullyQualifiedName(Package context, String resourceName)
  {
    Validate.notNull(context, "Parameter '%s' must not be null!", new Object[] { "context" });
    Validate.notNull(resourceName, "Parameter '%s' must not be null!", new Object[] { "resourceName" });
    return context.getName() + "." + resourceName;
  }
  















  public static String toFullyQualifiedPath(Class<?> context, String resourceName)
  {
    Validate.notNull(context, "Parameter '%s' must not be null!", new Object[] { "context" });
    Validate.notNull(resourceName, "Parameter '%s' must not be null!", new Object[] { "resourceName" });
    return toFullyQualifiedPath(context.getPackage(), resourceName);
  }
  
















  public static String toFullyQualifiedPath(Package context, String resourceName)
  {
    Validate.notNull(context, "Parameter '%s' must not be null!", new Object[] { "context" });
    Validate.notNull(resourceName, "Parameter '%s' must not be null!", new Object[] { "resourceName" });
    return context.getName().replace('.', '/') + "/" + resourceName;
  }
}
