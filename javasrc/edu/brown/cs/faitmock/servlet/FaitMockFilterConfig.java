/********************************************************************************/
/*                                                                              */
/*              FaitMockFilterConfig.java                                       */
/*                                                                              */
/*      Implementation of a filter configuration                                */
/*                                                                              */
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

public class FaitMockFilterConfig implements FilterConfig
{



/********************************************************************************/
/*										*/
/*	Private Storage 							*/
/*										*/
/********************************************************************************/

private Hashtable<String,String>	init_params;
private ServletContext			filter_context;
private String				filter_name;



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

public FaitMockFilterConfig(String nm,ServletContext ctx)
{
   this(nm,ctx,null);
}


public FaitMockFilterConfig(String nm,ServletContext ctx,Map<String,String> init)
{
   filter_name = nm;
   filter_context = ctx;
   init_params = new Hashtable<>();
   if (init != null) init_params.putAll(init);
}



/********************************************************************************/
/*										*/
/*	ServletConfig methods							*/
/*										*/
/********************************************************************************/

@Override public String getInitParameter(String name)
{
   return init_params.get(name);
}


@Override public Enumeration<String> getInitParameterNames()
{
   return init_params.keys();
}


@Override public ServletContext getServletContext()
{
   return filter_context;
}


@Override public String getFilterName()
{
   return filter_name;
}


public void setInitParameter(String n,String v) 
{
   init_params.put(n,v);
}




}       // end of class FaitMockFilterConfig




/* end of FaitMockFilterConfig.java */
