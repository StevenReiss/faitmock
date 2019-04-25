/********************************************************************************/
/*                                                                              */
/*              FaitMockJspFactory.java                                         */
/*                                                                              */
/*      description of class                                                    */
/*                                                                              */
/*      Written by spr                                                          */
/*                                                                              */
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.ErrorData;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;
import javax.servlet.jsp.tagext.BodyContent;

@SuppressWarnings("deprecation") public class FaitMockJspFactory extends JspFactory
{


/********************************************************************************/
/*                                                                              */
/*      Private Storage                                                         */
/*                                                                              */
/********************************************************************************/

private JspEngineInfo           engine_info;
private ServletContext          servlet_context;



/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

public FaitMockJspFactory(ServletContext ctx)
{
   engine_info = null;
   servlet_context = ctx;
}



@Override public JspEngineInfo getEngineInfo()
{
   return engine_info;
}



@Override public JspApplicationContext getJspApplicationContext(ServletContext ctx)
{
   return null;
}


@Override public PageContext getPageContext(Servlet s,ServletRequest req,ServletResponse resp,
      String errorurl,boolean session,int buf,boolean autoflush)
{
   FaitMockPageContext ctx = new FaitMockPageContext();
   ctx.initialize(s,req,resp,errorurl,session,buf,autoflush);
   return ctx;
}


@Override public void releasePageContext(PageContext ctx)
{ }



/********************************************************************************/
/*                                                                              */
/*      Page Context                                                            */
/*                                                                              */
/********************************************************************************/

public class FaitMockPageContext extends PageContext {
 
   private Servlet our_servlet;
   private ServletRequest our_request;
   private ServletResponse our_response;
   private Hashtable<String,Object> page_attributes;
   private JspWriter output_stream;
   
   FaitMockPageContext() {
      our_servlet = null;
      our_request = null;
      our_response = null;
      page_attributes = new Hashtable<>();
      output_stream = null;
    }
   
   @Override public void initialize(Servlet s,ServletRequest req,ServletResponse resp,
         String errorurl,boolean needssession,int buf,boolean flush) {
      our_servlet = s;
      our_request = req;
      our_response = resp;
    }
   
   @Override public void forward(String path)                   { }
   @Override public ErrorData getErrorData()                    { return null; }
   @Override public Exception getException()                    { return null; }
   @Override public Object getPage()                            { return our_servlet; }
   @Override public ServletRequest getRequest()                 { return our_request; }
   @Override public ServletResponse getResponse()               { return our_response; }
   @Override public ServletConfig getServletConfig()            { return our_servlet.getServletConfig(); }
   @Override public ServletContext getServletContext()          { return servlet_context; }
   @Override public HttpSession getSession()                    { return null; }
   @Override public void handlePageException(Exception e)       { }
   @Override public void handlePageException(Throwable t)       { }
   @Override public void include(String path)                   { }
   @Override public void include(String path,boolean flush)     { }
   @Override public BodyContent pushBody()                      { return null; }
   @Override public void release()                              { }
   
   @Override public Object findAttribute(String name)           { return page_attributes.get(name); }
   @Override public Object getAttribute(String name)            { return page_attributes.get(name); }
   @Override public Object getAttribute(String name,int scope)  { return page_attributes.get(name); }
   @Override public Enumeration<String> getAttributeNamesInScope(int scp) {
      return page_attributes.keys();
    }
   @Override public int getAttributesScope(String name)         { return 0; }
   @Override public ELContext getELContext()                    { return null; }
   public ExpressionEvaluator getExpressionEvaluator()          { return null; }
   @Override public JspWriter getOut() {
      if (output_stream == null) output_stream = new FaitMockJspWriter();
      return output_stream;
    }
      
   public VariableResolver getVariableResolver()                { return null; }
   @Override public void removeAttribute(String name)           { page_attributes.remove(name); }
   @Override public void removeAttribute(String name,int scp)   { page_attributes.remove(name); }  
   @Override public void setAttribute(String name,Object val)   { page_attributes.put(name,val); }
   @Override public void setAttribute(String name,Object val,int s) { page_attributes.put(name,val); }
   
}       // end of inner class FaitMockPageContext





}       // end of class FaitMockJspFactory




/* end of FaitMockJspFactory.java */
