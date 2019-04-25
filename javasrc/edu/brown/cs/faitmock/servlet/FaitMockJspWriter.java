/********************************************************************************/
/*										*/
/*		FaitMockJspWriter.java						*/
/*										*/
/*	Dummy jsp writer							*/
/*										*/
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import javax.servlet.jsp.JspWriter;
import edu.brown.cs.faitmock.annot.HtmlUntainted;

class FaitMockJspWriter extends JspWriter
{



/********************************************************************************/
/*										*/
/*	Constructors								*/
/*										*/
/********************************************************************************/

FaitMockJspWriter()
{
   super(1024,true);
}



/********************************************************************************/
/*										*/
/*	I/O methods								*/
/*										*/
/********************************************************************************/

@Override public void clear()				{ }

@Override public void clearBuffer()			{ }

@Override public void close()				{ }

@Override public void flush()				{ }

@Override public int getRemaining()			{ return 1024; }

@Override public void newLine()
{
   System.out.println();
}


@Override public void print(boolean b)		
{
   System.out.print(b);
}


@Override public void print(char c)		
{
   System.out.print(c);
}


@Override public void print(@HtmlUntainted char [] s)		
{
   System.out.print(s);
}


@Override public void print(double d)		
{
   System.out.print(d);
}


@Override public void print(float f)		
{
   System.out.print(f);
}


@Override public void print(int i)		
{
   System.out.print(i);
}


@Override public void print(long l)		
{
   System.out.print(l);
}


@Override public void print(@HtmlUntainted Object o)		
{
   System.out.print(o);
}


@Override public void print(@HtmlUntainted String s)		
{
   System.out.print(s);
}


@Override public void println() 		
{
   System.out.println();
}


@Override public void println(boolean b)		
{
   System.out.println(b);
}


@Override public void println(char c)		
{
   System.out.println(c);
}


@Override public void println(@HtmlUntainted char [] s) 		
{
   System.out.println(s);
}


@Override public void println(double d) 		
{
   System.out.println(d);
}


@Override public void println(float f)		
{
   System.out.println(f);
}


@Override public void println(int i)		
{
   System.out.println(i);
}


@Override public void println(long l)		
{
   System.out.println(l);
}


@Override public void println(@HtmlUntainted Object o)		
{
   System.out.println(o);
}


@Override public void println(@HtmlUntainted String s)		
{
   System.out.println(s);
}

/********************************************************************************/
/*										*/
/*	Writer methods								*/
/*										*/
/********************************************************************************/


@Override public void write(@HtmlUntainted char [] cbuf,int off,int len)
{
   // output here
}



}	// end of class FaitMockJspWriter




/* end of FaitMockJspWriter.java */
