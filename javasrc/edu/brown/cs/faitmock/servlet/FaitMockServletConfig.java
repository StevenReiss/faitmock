/********************************************************************************/
/*										*/
/*		FaitMockServletConfig.java					*/
/*										*/
/*	Default/mock servlet configuration					*/
/*										*/
/*	Written by spr								*/
/*										*/
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class FaitMockServletConfig implements ServletConfig
{


/********************************************************************************/
/*										*/
/*	Private Storage 							*/
/*										*/
/********************************************************************************/

private Hashtable<String,String>	init_params;
private ServletContext			servlet_context;
private String				servlet_name;



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

public FaitMockServletConfig(String nm,ServletContext ctx)
{
   this(nm,ctx,null);
}


public FaitMockServletConfig(String nm,ServletContext ctx,Map<String,String> init)
{
   servlet_name = nm;
   servlet_context = ctx;
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
   return servlet_context;
}


@Override public String getServletName()
{
   return servlet_name;
}


public void setInitParameter(String n,String v) 
{
   init_params.put(n,v);
}


}	// end of class FaitMockServletConfig




/* end of FaitMockServletConfig.java */

