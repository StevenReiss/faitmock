/********************************************************************************/
/*                                                                              */
/*              FaitMockDispatcher.java                                         */
/*                                                                              */
/*      Mock dispatcher                                                         */
/*                                                                              */
/*      Written by spr                                                          */
/*                                                                              */
/********************************************************************************/



package edu.brown.cs.faitmock.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


class FaitMockDispatcher implements RequestDispatcher
{


/********************************************************************************/
/*                                                                              */
/*      Constructores                                                           */
/*                                                                              */
/********************************************************************************/

FaitMockDispatcher()
{ }



/********************************************************************************/
/*                                                                              */
/*      Action methods                                                          */
/*                                                                              */
/********************************************************************************/



@Override public void forward(ServletRequest req,ServletResponse resp) 
{
}

@Override public void include(ServletRequest req,ServletResponse resp)
{
}


}       // end of class FaitMockDispatcher




/* end of FaitMockDispatcher.java */
