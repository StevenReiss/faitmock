/********************************************************************************/
/*										*/
/*		FaitMockSession.java						*/
/*										*/
/*	Mock servlet session							*/
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

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;


@SuppressWarnings("deprecation")
public class FaitMockSession implements HttpSession {


/********************************************************************************/
/*										*/
/*	Private Storage 							*/
/*										*/
/********************************************************************************/

private Hashtable<String, Object> attribute_map;
private long			  creation_time;
private long			  last_time;
private int			  inactive_interval;
private String			  session_id;
private ServletContext            servlet_context;


/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

public FaitMockSession(ServletContext ctx)
{
   servlet_context = ctx;
   attribute_map = new Hashtable<>();
   creation_time = System.currentTimeMillis();
   last_time = creation_time;
   inactive_interval = 5000;
   session_id = "S_" + creation_time + "_" + ((int) (Math.random() * 1000000));
}


/********************************************************************************/
/*										*/
/*	HttpSession methods							*/
/*										*/
/********************************************************************************/

@Override
public Object getAttribute(String name)
{
   return attribute_map.get(name);
}


@Override
public Enumeration<String> getAttributeNames()
{
   return attribute_map.keys();
}

@Override
public long getCreationTime()
{
   return creation_time;
}


@Override
public String getId()
{
   noteUsed();
   return session_id;
}

@Override
public long getLastAccessedTime()
{
   return last_time;
}


@Override
public int getMaxInactiveInterval()
{
   return inactive_interval;
}


@Override
public ServletContext getServletContext()
{
   return servlet_context;
}


@Override
public HttpSessionContext getSessionContext()
{
   return null;
}


@Override
public Object getValue(String name)
{
   return getAttribute(name);
}


@Override
public String[] getValueNames()
{
   Collection<String> nms = attribute_map.keySet();
   int ln = nms.size();
   return nms.toArray(new String[ln]);
}


@Override
public void invalidate()
{
   session_id = null;
}


@Override
public boolean isNew()
{
   return last_time == creation_time;
}


@Override
public void putValue(String name,Object o)
{
   setAttribute(name, o);
}


@Override
public void removeAttribute(String name)
{
   attribute_map.remove(name);
}


@Override
public void removeValue(String name)
{
   removeAttribute(name);
}


@Override
public void setAttribute(String name,Object o)
{
   attribute_map.put(name, o);
}


@Override
public void setMaxInactiveInterval(int v)
{
   inactive_interval = v;
}


void noteUsed()
{
   last_time = System.currentTimeMillis();
}


} // end of class FaitMockSession


/* end of FaitMockSession.java */

