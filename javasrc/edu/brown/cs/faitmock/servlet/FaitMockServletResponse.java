/********************************************************************************/
/*										*/
/*		FaitMockServletResponse.java					*/
/*										*/
/*	Mocking servlet response						*/
/*										*/
/********************************************************************************/
/*	Copyright 2013 Brown University -- Steven P. Reiss		      */
/*********************************************************************************
 *  Copyright 2013, Brown University, Providence, RI.				 *
 *										 *
 *			  All Rights Reserved					 *
 *										 *
 *  Permission to use, copy, modify, and distribute this software and its	 *
 *  documentation for any purpose other than its incorporation into a		 *
 *  commercial product is hereby granted without fee, provided that the 	 *
 *  above copyright notice appear in all copies and that both that		 *
 *  copyright notice and this permission notice appear in supporting		 *
 *  documentation, and that the name of Brown University not be used in 	 *
 *  advertising or publicity pertaining to distribution of the software 	 *
 *  without specific, written prior permission. 				 *
 *										 *
 *  BROWN UNIVERSITY DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS		 *
 *  SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND		 *
 *  FITNESS FOR ANY PARTICULAR PURPOSE.  IN NO EVENT SHALL BROWN UNIVERSITY	 *
 *  BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY 	 *
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,		 *
 *  WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS		 *
 *  ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE 	 *
 *  OF THIS SOFTWARE.								 *
 *										 *
 ********************************************************************************/


package edu.brown.cs.faitmock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import edu.brown.cs.faitmock.annot.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public class FaitMockServletResponse implements HttpServletResponse {


/********************************************************************************/
/*										*/
/*	Private Storage 							*/
/*										*/
/********************************************************************************/

private Map<String,List<String>> header_map;
private String		    char_encoding;
private Locale		    content_locale;
private int		    buffer_size;
private String		    content_type;
private int		    response_status;




/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

public FaitMockServletResponse()
{
   header_map = new HashMap<>();
   char_encoding = "UTF-8";
   content_locale = Locale.getDefault();
   buffer_size = 8192;
   content_type = "text/html";
   response_status = 200;
}



/********************************************************************************/
/*										*/
/*	HttpServletResponse methods						*/
/*										*/
/********************************************************************************/

@Override
public void addCookie(Cookie c)
{}

@Override
public void addDateHeader(String name,long date)
{
   addHeader(name, new Date(date).toString());
}

@Override
public void addHeader(String name,String value)
{
   List<String> vals = header_map.get(name);
   if (vals == null) {
      vals = new ArrayList<>();
      header_map.put(name,vals);
    }
   vals.add(value);
}

@Override
public void addIntHeader(String name,int v)
{
   addHeader(name, Integer.toString(v));
}

@Override
public Collection<String> getHeaderNames()
{
   return header_map.keySet();
}


@Override
public String getHeader(String name)
{
   List<String> vals = header_map.get(name);
   if (vals == null || vals.isEmpty()) return null;
   return vals.get(0);
}


@Override
public Collection<String> getHeaders(String name)
{
   return header_map.get(name);
}


@Override public int getStatus()
{
   return response_status;
}


@Override
public boolean containsHeader(String name)
{
   return header_map.containsKey(name);
}


@Override
public String encodeRedirectUrl(String url)
{
   return encodeRedirectURL(url);
}

@Override
public String encodeRedirectURL(String url)
{
   return url;
}


@Override
public String encodeUrl(String url)
{
   return encodeURL(url);
}


@Override
public String encodeURL(String url)
{
   return url;
}


@Override
public void sendError(int ec)
{}

@Override
public void sendError(int ec,@HtmlUntainted String msg)
{}

@Override
public void sendRedirect(@HtmlUntainted String loc)
{}

@Override
public void setStatus(int sc)
{
   response_status = sc;
}

@Override
public void setStatus(int sc,@HtmlUntainted String msg)
{
   response_status = sc;
}

@Override
public void setDateHeader(String name,long date)
{
   addDateHeader(name, date);
}

@Override
public void setHeader(String name,String v)
{
   addHeader(name, v);
}


@Override
public void setIntHeader(String name,int v)
{
   addIntHeader(name, v);
}


/********************************************************************************/
/*										*/
/*	ServletResponse methods 						*/
/*										*/
/********************************************************************************/

@Override
public void flushBuffer()
{}

@Override
public int getBufferSize()
{
   return buffer_size;
}

@Override
public void setBufferSize(int v)
{
   buffer_size = v;
}

@Override
public String getCharacterEncoding()
{
   return char_encoding;
}


@Override public void setCharacterEncoding(String enc)
{
   char_encoding = enc;
}

@Override public String getContentType()
{
   return content_type;
}

@Override
public Locale getLocale()
{
   return content_locale;
}

@Override
public void setLocale(Locale l)
{
   content_locale = l;
}

@Override
public void setContentType(String type)
{
   content_type = type;
}

@Override
public void setContentLength(int ln)
{}

@Override
public PrintWriter getWriter()
{
   try {
      return new PrintWriter("servlet.out");
   }
   catch (IOException e) {
      return null;
   }
}

@Override
public ServletOutputStream getOutputStream()
{
   return new FaitMockOutputStream();
}


@Override
public void resetBuffer()
{}

@Override
public boolean isCommitted()
{
   return false;
}

@Override
public void reset()
{}





} // end of class FaitMockServletResponse


/* end of FaitMockServletResponse.java */

