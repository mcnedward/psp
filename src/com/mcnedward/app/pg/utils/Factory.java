package com.mcnedward.app.pg.utils;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class Factory
{
  private Hashtable ht;
  private Class superclass;
  private Class[] constructor_param;
  private String registry;
  
  public Factory(Class paramClass, Class[] paramArrayOfClass, String paramString)
  {
    this.superclass = paramClass;
    this.constructor_param = paramArrayOfClass;
    this.registry = paramString;
    
    this.ht = new Hashtable();
    
    ClassLoader localClassLoader = Factory.class.getClassLoader();
    
    URL localURL = localClassLoader.getResource(paramString);
    if (localURL != null) {
      try
      {
        InputStream localInputStream = localURL.openStream();
        
        Properties localProperties = new Properties();
        
        localProperties.load(localInputStream);
        
        Enumeration localEnumeration = localProperties.propertyNames();
        while (localEnumeration.hasMoreElements())
        {
          String str = (String)localEnumeration.nextElement();
          Class localClass;
          try
          {
            localClass = Class.forName(localProperties.getProperty(str));
          }
          catch (ClassNotFoundException localClassNotFoundException)
          {
            localClass = null;
          }
          if (localClass != null) {
            if (!register(str, localClass)) {}
          }
        }
        localInputStream.close();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private boolean register(String paramString, Class paramClass)
  {
    if (!this.superclass.isAssignableFrom(paramClass)) {
      return false;
    }
    if (this.ht.containsKey(paramString)) {
      return false;
    }
    try
    {
      paramClass.getConstructor(this.constructor_param);
    }
    catch (Exception localException)
    {
      return false;
    }
    this.ht.put(paramString, paramClass);
    
    return true;
  }
  
  public Object createObject(String paramString, Object[] paramArrayOfObject)
  {
    Class localClass = (Class)this.ht.get(paramString);
    if (localClass == null) {
      return null;
    }
    try
    {
      Constructor localConstructor = localClass.getConstructor(this.constructor_param);
      
      return localConstructor.newInstance(paramArrayOfObject);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}
