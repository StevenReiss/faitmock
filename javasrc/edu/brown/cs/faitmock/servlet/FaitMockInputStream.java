/********************************************************************************/
/*                                                                              */
/*              FaitMockInputStream.java                                        */
/*                                                                              */
/*       Dummy input stream                                                     */
/*                                                                              */
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import java.io.ByteArrayInputStream;

import javax.servlet.ServletInputStream;

public class FaitMockInputStream extends ServletInputStream
{



/********************************************************************************/
/*                                                                              */
/*      Private storage                                                         */
/*                                                                              */
/********************************************************************************/

private ByteArrayInputStream dummy_input;




/********************************************************************************/
/*                                                                              */
/*      Constructors                                                            */
/*                                                                              */
/********************************************************************************/

FaitMockInputStream()
{
   String contents = "Dummy Contents";
   dummy_input = new ByteArrayInputStream(contents.getBytes());
}



/********************************************************************************/
/*                                                                              */
/*      I/O methods                                                             */
/*                                                                              */
/********************************************************************************/

@Override
public int read()
{
   return dummy_input.read();
}



}       // end of class FaitMockInputStream




/* end of FaitMockInputStream.java */
