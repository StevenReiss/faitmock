/********************************************************************************/
/*                                                                              */
/*              FaitMockOutputStream.java                                       */
/*                                                                              */
/*      Dummy output class for servlets                                         */
/*                                                                              */
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import javax.servlet.ServletOutputStream;

class FaitMockOutputStream extends ServletOutputStream
{



/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

FaitMockOutputStream() 
{ }



/********************************************************************************/
/*                                                                              */
/*      I/O methods                                                             */
/*                                                                              */
/********************************************************************************/

@Override
public void write(int v)
{
   System.out.write(v);
}


}       // end of class FaitMockOutputStream




/* end of FaitMockOutputStream.java */
