package com.mcnedward.app.pg.utils;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;

public abstract class PropUtils
{
  public static final char LABEL_RIGHT_DELIMITER = '>';
  public static final char LABEL_LEFT_DELIMITER = '<';
  
  public static Properties getPropertiesWithPrefix(Properties paramProperties, String paramString)
  {
    Properties localProperties = new Properties();
    
    Enumeration localEnumeration = paramProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      if (str.startsWith(paramString)) {
        localProperties.setProperty(str.substring(paramString.length()), paramProperties.getProperty(str));
      }
    }
    return localProperties;
  }
  
  public static Properties putPrefixToProperties(String paramString, Properties paramProperties)
  {
    Properties localProperties = new Properties();
    
    Enumeration localEnumeration = paramProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      
      localProperties.setProperty(paramString + str, paramProperties.getProperty(str));
    }
    return localProperties;
  }
  
  public static Properties substituteLabels(Properties paramProperties1, Properties paramProperties2)
  {
    Properties localProperties1 = new Properties();
    for (Enumeration localEnumeration = paramProperties1.propertyNames(); localEnumeration.hasMoreElements();)
    {
      String str1 = (String)localEnumeration.nextElement();
      
      String str2 = paramProperties1.getProperty(str1);
      
      str2.trim();
      if (isLabel(str2))
      {
        Properties localProperties2 = getPropertiesWithPrefix(paramProperties2, str2);
        localProperties2 = putPrefixToProperties(str1, localProperties2);
        
        localProperties1.putAll(localProperties2);
      }
      else
      {
        localProperties1.setProperty(str1, str2);
      }
    }
    return localProperties1;
  }
  
  public static boolean isLabel(String paramString)
  {
    return (paramString.indexOf('<') == 0) && (paramString.indexOf('>') == paramString.length() - 1);
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    Properties localProperties1 = new Properties();
    Properties localProperties2 = new Properties();
    
    FileInputStream localFileInputStream1 = new FileInputStream(paramArrayOfString[0]);
    FileInputStream localFileInputStream2 = new FileInputStream(paramArrayOfString[1]);
    
    localProperties1.load(localFileInputStream1);
    localProperties2.load(localFileInputStream2);
    
    Properties localProperties3 = substituteLabels(localProperties1, localProperties2);
    
    System.out.println(localProperties3);
  }
}
