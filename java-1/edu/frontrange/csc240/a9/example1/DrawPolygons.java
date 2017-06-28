
package edu.frontrange.csc240.a9.example1;

/**
 * This program (this class and the associated classes is based on the program of
 * Figure 13.28 DrawPolygons (Drawing polygons) of Edition 10 of the textbook.
 * <p>
 * It has been revised to make the {@code main} method just the initiator of the
 * program, and to introduce a Model, View and Controller paradigm. In doing so,
 * the building of the GUI has been removed from a constructor, and the starting
 * of the GUI is now done on the Event Dispatch Thread (EDT).
 *
 * @author		Deitel &amp; Associates (see copyright at end)
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.0, 2016-08-22, CSC-240 Assignment 9, Sample
 */
public class DrawPolygons
{
/**
 * The main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a9.example1.DrawPolygons</pre>
 *
 * @param args		unused
 */
public static void main(String[] args)
{
	PolygonsView.launch();
}
}

/*-************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
