/********************************************************************************/
/*										*/
/*		FaitMockServletContext.java					*/
/*										*/
/*	Default/Mock servlet context						*/
/*										*/
/*	Written by spr								*/
/*										*/
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

public class FaitMockServletContext implements ServletContext
{



/********************************************************************************/
/*										*/
/*	Private storage 							*/
/*										*/
/********************************************************************************/

private Hashtable<String,Object> attribute_map;
private Hashtable<String,String> parameter_map;
private Hashtable<String,Servlet>      servlet_map;



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

public FaitMockServletContext()
{
   attribute_map = new Hashtable<>();
   parameter_map = new Hashtable<>();
   servlet_map = new Hashtable<>();
}



/********************************************************************************/
/*										*/
/*	ServletContext methods							*/
/*										*/
/********************************************************************************/

@Override public Object getAttribute(String name)
{
   return attribute_map.get(name);
}

@Override public Enumeration<String> getAttributeNames()
{
   return attribute_map.keys();
}


@Override public void setAttribute(String n,Object v)
{
   attribute_map.put(n,v);
}


@Override public void removeAttribute(String name)
{
   attribute_map.remove(name);
}



@Override public ClassLoader getClassLoader()
{
   return getClass().getClassLoader();
}

@Override public ServletContext getContext(String path)
{
   return this;
}

@Override public String getContextPath()
{
   return "/servlet";
}


@Override public Set<SessionTrackingMode> getDefaultSessionTrackingModes()
{
   return null;
}

@Override public int getEffectiveMajorVersion()
{
   return 5;
}

@Override public int getEffectiveMinorVersion()
{
   return 1;
}

@Override public Set<SessionTrackingMode> getEffectiveSessionTrackingModes()
{
   return null;
}


@Override public void setSessionTrackingModes(Set<SessionTrackingMode> mds)
{
}


@Override public String getInitParameter(String name)
{
   return parameter_map.get(name);
}


@Override public Enumeration<String> getInitParameterNames()
{
   return parameter_map.keys();
}


@Override public boolean setInitParameter(String n,String v)
{
   if (parameter_map.containsKey(n)) return false;
   parameter_map.put(n,v);
   return true;
}


@Override public JspConfigDescriptor getJspConfigDescriptor()
{
   return null;
}


@Override public int getMajorVersion()
{
   return 5;
}


@Override public String getMimeType(String file)
{
   return null;
}


@Override public int getMinorVersion()
{
   return 1;
}


@Override public RequestDispatcher getNamedDispatcher(String nm)
{
   return null;
}


@Override public String getRealPath(String path)
{
   return path;
}

public String getRequestCharacterEncoding()
{
   return "UTF-8";
}

public void setRequestCharacterEncoding(String e)
{
}


@Override public URL getResource(String path)
{
   return getClassLoader().getResource(path);
}


@Override public InputStream getResourceAsStream(String path)
{
   return getClassLoader().getResourceAsStream(path);
}


@Override public Set<String> getResourcePaths(String path)
{
   return Set.of("TestPath1","TestPath2");
}


public String getResponseCharacterEncoding()
{
   return "UTF-8";
}


public String getServerInfo()
{
   return "MOCKER";
}

@Override public String getServletContextName()
{
   return "Mocker";
}




@Override public SessionCookieConfig getSessionCookieConfig()
{
   return null;
}


public int getSessionTimeout()
{
   return 500;
}


public String getVirtualServerName()
{
   return "MOCKER";
}


/********************************************************************************/
/*										*/
/*	Servlet methods 							*/
/*										*/
/********************************************************************************/

public <T extends Servlet> T createServlet(Class<T> cls) throws ServletException
{
   try {
      T rslt = cls.getConstructor().newInstance();
      return rslt;
    }
   catch (Throwable t) {
      throw new ServletException(t);
    }
}


@Override public Servlet getServlet(String nm)
{
   return servlet_map.get(nm);
}


@Override public ServletRegistration getServletRegistration(String name)
{
   return null;
}


@Override public Map<String,ServletRegistration> getServletRegistrations()
{
   return null;
}


@Override public Enumeration<Servlet> getServlets()
{
   return servlet_map.elements();
}


@Override public Enumeration<String> getServletNames()
{
   return servlet_map.keys();
}


@Override public ServletRegistration.Dynamic addServlet(String nm,Class<? extends Servlet> cls)
{
   try {
      Servlet s = createServlet(cls);
      servlet_map.put(nm,s);
    }
   catch (Exception e) { }

   return null;
}


@Override public ServletRegistration.Dynamic addServlet(String nm,String cls)
{
   try {
      Class<?> c = Class.forName(cls);
      Servlet s = (Servlet) c.getConstructor().newInstance();
      servlet_map.put(nm,s);
    }
   catch (Throwable t) { }

   return null;
}


@Override public ServletRegistration.Dynamic addServlet(String nm,Servlet s)
{
   servlet_map.put(nm,s);

   return null;
}


public void addFaitServlet(String nm,Servlet s)
{
   addServlet(nm,s);
}



/********************************************************************************/
/*										*/
/*	Dispatcher								*/
/*										*/
/********************************************************************************/

@Override public RequestDispatcher getRequestDispatcher(String nm)
{
   Servlet s = servlet_map.get(nm);
   return new FaitMockRequestDispatcher(s);
}


private class FaitMockRequestDispatcher implements RequestDispatcher {

   private Servlet to_servlet;

   FaitMockRequestDispatcher(Servlet s) {
      to_servlet = s;
    }

   @Override public void forward(ServletRequest rq,ServletResponse rs)
	throws ServletException, IOException {
      if (to_servlet != null) to_servlet.service(rq,rs);
    }

   @Override public void include(ServletRequest rq,ServletResponse rs)
	throws ServletException, IOException {
      DispatcherType dt = rq.getDispatcherType();
      if (rq instanceof FaitMockServletRequest) {
	 FaitMockServletRequest myrq = (FaitMockServletRequest) rq;
	 myrq.setDispatcherType(DispatcherType.INCLUDE);
       }
      if (to_servlet != null) to_servlet.service(rq,rs);
      if (rq instanceof FaitMockServletRequest) {
	 FaitMockServletRequest myrq = (FaitMockServletRequest) rq;
	 myrq.setDispatcherType(dt);
       }
    }

}	// end of inner class FaitMockRequestDispatcher


/********************************************************************************/
/*										*/
/*	Role methods								*/
/*										*/
/********************************************************************************/

public void declareRoles(String ... rolenames)
{

}


/********************************************************************************/
/*										*/
/*	Filter methods								*/
/*										*/
/********************************************************************************/

@Override public FilterRegistration.Dynamic addFilter(String n,Class<? extends Filter> filter)
{
   return null;
}


@Override public FilterRegistration.Dynamic addFilter(String n,Filter f)
{
   return null;
}


@Override public FilterRegistration.Dynamic addFilter(String n,String f)
{
   return null;
}



@Override public FilterRegistration getFilterRegistration(String name)
{
   return null;
}

@Override public Map<String,FilterRegistration> getFilterRegistrations()
{
   return null;
}

@Override public <T extends Filter> T createFilter(Class<T> cls)
{
   return null;
}




/********************************************************************************/
/*										*/
/*	Listener methods							*/
/*										*/
/********************************************************************************/

@Override public void addListener(Class<? extends EventListener> cls)
{ }


@Override public void addListener(String cls)
{ }


@Override public <T extends EventListener> void addListener(T t)
{ }


@Override public <T extends EventListener> T createListener(Class<T> cls)
{
   return null;
}





/********************************************************************************/
/*										*/
/*	Log methods								*/
/*										*/
/********************************************************************************/

@Override public void log(String msg)
{
   System.err.println("SERVLET: " + msg);
}


@Override public void log(String msg,Throwable t)
{
   System.err.println("SERVLET: " + msg);
   t.printStackTrace();
}


@Override public void log(Exception t,String msg)
{
   log(msg,t);
}






}	// end of class FaitMockServletContext




/* end of FaitMockServletContext.java */
