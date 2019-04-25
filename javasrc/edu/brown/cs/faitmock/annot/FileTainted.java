/********************************************************************************/
/*										*/
/*		FileTainted.java						*/
/*										*/
/*	@FileTainted annotation 						*/
/*										*/
/********************************************************************************/



package edu.brown.cs.faitmock.annot;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Documented
@TypeQualifier
@Target({ElementType.TYPE,ElementType.PARAMETER,
   ElementType.LOCAL_VARIABLE,ElementType.FIELD,ElementType.METHOD,
      ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)

public @interface FileTainted
{

}	// end of interface FileTainted




/* end of FileTainted.java */
