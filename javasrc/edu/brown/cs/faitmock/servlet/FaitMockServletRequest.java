/********************************************************************************/
/*										*/
/*		FaitMockServletRequest.java					*/
/*										*/
/*	description of class							*/
/*										*/
/*	Written by spr								*/
/*										*/
/********************************************************************************/


package edu.brown.cs.faitmock.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Tainted;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


public class FaitMockServletRequest implements HttpServletRequest {


/********************************************************************************/
/*										*/
/*	Private storage 							*/
/*										*/
/********************************************************************************/

private ServletContext              servlet_context;
private Cookie[]		    cookie_set;
private Map<String, Vector<String>> header_data;
private String			    request_method;
private URI			    request_uri;
private FaitMockSession 	      cur_session;
private Hashtable<String, Object>   attribute_map;
private String			    char_encoding;
private Hashtable<String, String[]> param_map;
private DispatcherType		      dispatch_type;



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

public FaitMockServletRequest(ServletContext ctx)
{
   servlet_context = ctx;
   cookie_set = new Cookie[1];
   cookie_set[0] = new Cookie("sample","value");
   header_data = new HashMap<>();
   Vector<String> vs = new Vector<>();
   vs.add("text-html");
   header_data.put("Content-Type", vs);
   try {
      if (Math.random() >= 0.5) {
	 request_uri = new URI("http://localhost.test:9999/sample/request/5.get");
	 request_method = "POST";
      }
      else {
	 request_uri = new URI("http://localhost.test:9999/sample/request?get=5");
	 request_method = "GET";
      }
   }
   catch (URISyntaxException e) {}
   cur_session = null;

   attribute_map = new Hashtable<>();
   char_encoding = "UTF-8";
   param_map = new Hashtable<>();
   dispatch_type = DispatcherType.REQUEST;
}


/********************************************************************************/
/*										*/
/*	HttpServletRequest methods						*/
/*										*/
/********************************************************************************/

@Override
public String getAuthType()
{
   return BASIC_AUTH;
}


@Override
public @Tainted Cookie[] getCookies()
{
   return cookie_set;
}


@Override
public long getDateHeader(String name)
{
   String hv = getHeader(name);
   if (hv == null) return -1;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   try {
      Date d = sdf.parse(hv);
      return d.getTime();
   }
   catch (ParseException _ex) {}

   return -1;
}


@Override
public @Tainted String getHeader(String name)
{
   Vector<String> hvs = header_data.get(name);
   if (hvs == null || hvs.isEmpty()) return null;
   return hvs.get(0);
}


@Override
public @Tainted Enumeration<String> getHeaders(String name)
{
   Vector<String> hvs = header_data.get(name);
   if (hvs == null) hvs = new Vector<>();
   return hvs.elements();
}


@Override
public @Tainted Enumeration<String> getHeaderNames()
{
   Vector<String> hvs = new Vector<>(header_data.keySet());
   return hvs.elements();
}


@Override
public int getIntHeader(String name)
{
   String v = getHeader(name);
   if (v == null) return -1;
   try {
      return Integer.parseInt(v);
   }
   catch (NumberFormatException e) {
      return -1;
   }
}


@Override
public String getMethod()
{
   return request_method;
}


@Override
public @Tainted String getPathInfo()
{
   return request_uri.getPath();
}


@Override
public @Tainted String getPathTranslated()
{
   return request_uri.getPath();
}


@Override
public @Tainted String getContextPath()
{
   String p = request_uri.getPath();
   int idx0 = p.indexOf("/");
   int idx1 = p.indexOf("/", 1);
   if (idx0 < 0) return "";
   if (idx0 > 0) idx1 = idx0;
   if (idx1 < 0) return p;
   return p.substring(0, idx1);
}


@Override
public @Tainted String getQueryString()
{
   return request_uri.getQuery();
}


@Override
public String getRemoteUser()
{
   return null;
}


@Override
public boolean isUserInRole(String role)
{
   return false;
}


@Override
public Principal getUserPrincipal()
{
   return null;
}


@Override
public @Tainted String getRequestedSessionId()
{
   if (cur_session != null) return cur_session.getId();
   return null;
}

@Override
public String getRequestURI()
{
   return request_uri.toString();
}

@Override
public StringBuffer getRequestURL()
{
   return new StringBuffer(request_uri.toString());
}


@Override
public String getServletPath()
{
   return "edu.brown.cs.test.Servlet";
}


@Override
public HttpSession getSession()
{
   return getSession(true);
}


@Override
public HttpSession getSession(boolean create)
{
   if (cur_session != null) {
      cur_session.noteUsed();
      return cur_session;
   }
   if (create) {
      cur_session = new FaitMockSession(servlet_context);
   }
   return cur_session;
}


@Override
public boolean isRequestedSessionIdValid()
{
   return true;
}


@Override
public boolean isRequestedSessionIdFromCookie()
{
   return true;
}


@Override
public boolean isRequestedSessionIdFromURL()
{
   return false;
}


@Override
public boolean isRequestedSessionIdFromUrl()
{
   return false;
}


@Override
public void login(String user,String password)
{ }


@Override
public void logout()
{ }


@Override public boolean authenticate(HttpServletResponse resp)
{
   return true;
}


@Override
public @Tainted Collection<Part> getParts()
{
   return new ArrayList<Part>();
}


@Override public @Tainted Part getPart(String name)
{
   return null;
}



/********************************************************************************/
/*										*/
/*	Servlet Request methods 						*/
/*										*/
/********************************************************************************/

@Override
public @Tainted Object getAttribute(String name)
{
   return attribute_map.get(name);
}

@Override
public @Tainted Enumeration<String> getAttributeNames()
{
   return attribute_map.keys();
}


@Override
public String getCharacterEncoding()
{
   return char_encoding;
}


@Override
public void setCharacterEncoding(String env)
{
   char_encoding = env;
}


@Override
public int getContentLength()
{
   return getIntHeader("Content-Length");
}

@Override
public @Tainted String getContentType()
{
   return getHeader("Content-Type");
}


@Override
public DispatcherType getDispatcherType()
{
   return dispatch_type;
}

public void setDispatcherType(DispatcherType dt)
{
   dispatch_type = dt;
}


@Override
public ServletInputStream getInputStream()
{
   return new FaitMockInputStream();
}


@Override
public String getLocalAddr()
{
   return "128.148.32.5";
}


@Override
public @Tainted String getParameter(String name)
{
   String[] v = param_map.get(name);
   if (v == null || v.length == 0) return null;
   return v[0];
}

@Override
public @Tainted Enumeration<String> getParameterNames()
{
   return param_map.keys();
}


@Override
public @Tainted String[] getParameterValues(String name)
{
   return param_map.get(name);
}


@Override
public @Tainted Map<String, String[]> getParameterMap()
{
   return param_map;
}


@Override
public String getProtocol()
{
   return "HTTP/1.1";
}


@Override
public String getScheme()
{
   return request_uri.getScheme();
}


@Override
public @Tainted String getServerName()
{
   return request_uri.getHost();
}


@Override
public int getServerPort()
{
   return request_uri.getPort();
}


@Override
public ServletContext getServletContext()
{
   return null;
}


@Override
public @Tainted BufferedReader getReader()
{
   return new BufferedReader(new InputStreamReader(getInputStream()));
}

@Override
public @Tainted String getRemoteAddr()
{
   return "fred4.cs.brown.edu:80";
}


@Override
public @Tainted String getRemoteHost()
{
   return "fred4.cs.brown.edu";
}

@Override
public int getRemotePort()
{
   return 23744;
}


@Override
public void setAttribute(String name,Object o)
{
   attribute_map.put(name, o);
}


@Override
public void removeAttribute(String name)
{
   attribute_map.remove(name);
}


@Override public String getLocalName()
{
   return "fred4.cs.brown.edu";
}

@Override public int getLocalPort()
{
   return 80;
}


@Override
public Locale getLocale()
{
   return Locale.getDefault();
}

@Override
public Enumeration<Locale> getLocales()
{
   Vector<Locale> v = new Vector<>();
   v.add(Locale.getDefault());
   v.add(Locale.ENGLISH);
   return v.elements();
}

@Override
public boolean isSecure()
{
   return request_uri.getScheme().equals("HTTPS");
}


@Override
public RequestDispatcher getRequestDispatcher(String path)
{
   return new FaitMockDispatcher();
}


@Override
public String getRealPath(String path)
{
   return path;
}


@Override public AsyncContext startAsync()
{
   return null;
}


@Override public AsyncContext startAsync(ServletRequest sr,ServletResponse resp)
{
   return null;
}


@Override public boolean isAsyncStarted()
{
   return false;
}


@Override public boolean isAsyncSupported()
{
   return false;
}


@Override public AsyncContext getAsyncContext()
{
   return null;
}



/********************************************************************************/
/*                                                                              */
/*      Dummy dispatcher                                                        */
/*                                                                              */
/********************************************************************************/

private static class FaitMockDispatcher implements RequestDispatcher
{
   @Override public void forward(ServletRequest req,ServletResponse resp) {
    }
 
   @Override public void include(ServletRequest req,ServletResponse resp) {
    }
   
}       // end of inner class FaitMockDispatcher



} // end of class FaitMockServletRequest


/* end of FaitMockServletRequest.java */
