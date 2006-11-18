/*
 * @(#)JeksJavaPerformanceTest.java   04/19/2001
 *
 * Copyright (c) 1998-2001 Emmanuel PUYBARET / eTeks <info@eteks.com>. All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Visit eTeks web site for up-to-date versions of this file and other
 * Java tools and tutorials : http://www.eteks.com/
 */

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Component;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Window;

import com.eteks.tools.awt.GridBagConstraints2;

import com.eteks.parser.FunctionParser;
import com.eteks.parser.JavaSyntax;
import com.eteks.parser.CompiledFunction;
import com.eteks.parser.CompilationException;

/**
 * Jeks demo. This demo allows to compare the computing average speed
 * of different functions parsed with Jeks and Java function with a speed meter dialog box.
 * This is Java 1.0 compatible code.
 *
 * @version   1.0.2
 * @author    Emmanuel Puybaret
 * @since     Jeks 1.0
 */
public class JeksJavaPerformanceTest
{
  /**
   * An example of use for Pure Java AWT Toolkit.
   */
  public static void main (String args [])
  {
    // First display an information dialog box
    showFirstDialog ();

    // Create an array of the functions to be tested
    FunctionTest tests [] = {new OperatorsTest (),
                             new CommonFunctionsTest (),
                             new MultipleFunctionsTest ()};

    // Show a dialog that compares drawing speed
    showSpeedMeterDialog (tests);
  }

  private static void showFirstDialog ()
  {
    // Dialog box for copyrights
    final Dialog  firstDialog = new Dialog (new Frame (), "Jeks parser", true)
      {
        // This demo uses old Event model to be able to run with JDK 1.0
        public boolean action (Event evt, Object arg)
        {
          // This is a trick to be sure Window.dispose () is called in JDK 1.0
          ((Window)this).dispose ();
          return true;
        }
      };
    Component     object;
    GridBagLayout layout = new GridBagLayout ();
    firstDialog.setLayout (layout);
    firstDialog.add (object = new Label ("Jeks parser / Java performance test."));
    layout.setConstraints (object, new GridBagConstraints2 (GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets (5, 5, 0, 5), 0, 0));
    firstDialog.add (object = new Label ("\u00a9 Copyright 1998-2001 eTeks <info@eteks.com>."));
    layout.setConstraints (object, new GridBagConstraints2 (GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets (0, 5, 20, 5), 0, 0));
    firstDialog.add (object = new Button (" Ok "));
    layout.setConstraints (object, new GridBagConstraints2 (GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets (20, 0, 10, 0), 0, 0));
    firstDialog.setResizable (false);
    firstDialog.pack ();
    // This is a trick to be sure Window.show () is called in JDK 1.0
    ((Window)firstDialog).show ();
  }

  static interface FunctionTest
  {
    FunctionParser PARSER = new FunctionParser (new JavaSyntax ());

    double computeJeksFunction ();

    double computeJavaFunction ();

    int getLoopCount ();
  }

  // Simple calculation test using constant PI, unary and binary operators
  static class OperatorsTest implements FunctionTest
  {
    CompiledFunction   function;

    public OperatorsTest ()
    {
      try
      {
        function = PARSER.compileFunction ("func (x) = Math.PI * +x * (x + -x / 3.) - 5E-2");

        if (computeJeksFunction () != computeJavaFunction ())
          throw new RuntimeException ("Jeks and Java give different results !");
      }
      catch (CompilationException ex)
      {
        ex.printStackTrace ();
        System.exit (0);
      }
    }

    public double computeJeksFunction ()
    {
      return function.computeFunction (new double [] {10});
    }

    public double computeJavaFunction ()
    {
      return func (10);
    }

    public double func (double x)
    {
      return Math.PI * +x * (x + -x / 3.) - 5E-2;
    }

    public int getLoopCount ()
    {
      return 50000;
    }

    public String toString ()
    {
      return "Operators : Math.PI * +x * (x + -x / 3) - 5E-2";
    }
  }

  // Common functions test using sin, cos, pow
  static class CommonFunctionsTest implements FunctionTest
  {
    CompiledFunction function;

    public CommonFunctionsTest ()
    {
      try
      {
        function = PARSER.compileFunction ("func (x, y) = Math.cos (x) * Math.sin (y) + Math.pow (x / y, 1.2)");

        if (computeJeksFunction () != computeJavaFunction ())
          throw new RuntimeException ("Jeks and Java give different results !");
      }
      catch (CompilationException ex)
      {
        ex.printStackTrace ();
        System.exit (0);
      }
    }

    public double computeJeksFunction ()
    {
      return function.computeFunction (new double [] {5.33, 4.5});
    }

    public double computeJavaFunction ()
    {
      return func (5.33, 4.5);
    }

    public double func (double x, double y)
    {
      return Math.cos (x) * Math.sin (y) + Math.pow (x / y, 1.2);
    }

    public int getLoopCount ()
    {
      return 10000;
    }

    public String toString ()
    {
      return "CommonFunctions : Math.cos (x) * Math.sin (y) + Math.pow (x / y, 1.2)";
    }
  }

  // Multiple functions test using multiple functions calls
  static class MultipleFunctionsTest implements FunctionTest
  {
    CompiledFunction function;

    public MultipleFunctionsTest ()
    {
      try
      {
        // v1.0.2 changed the order of parameters to be the same as Excel's PMT
        function = PARSER.compileFunction ("capitalPaid (rate, count, capital, testedPMT)= count <= 0 ? 0 : testedPMT - capital * rate + capitalPaid (rate, count - 1, capital - (testedPMT - capital * rate), testedPMT)");
        ((JavaSyntax)PARSER.getSyntax ()).addFunction (function);
        function = PARSER.compileFunction ("computePMT (rate, count, capital, capitalPaid1, capitalPaid2, testedPMT1, testedPMT2)=Math.abs (capitalPaid1 - capital) < 0.01 ? testedPMT1 : Math.abs (capitalPaid1 - capital) < Math.abs (capitalPaid2 - capital) ? computePMT (rate, count, capital, capitalPaid1, capitalPaid (rate, count, capital, testedPMT2 - (testedPMT2 - testedPMT1) / 2), testedPMT1, testedPMT2 - (testedPMT2 - testedPMT1) / 2) : computePMT (rate, count, capital, capitalPaid (rate, count, capital, testedPMT1 + (testedPMT2 - testedPMT1) / 2), capitalPaid2, testedPMT1 + (testedPMT2 - testedPMT1) / 2, testedPMT2)");
        ((JavaSyntax)PARSER.getSyntax ()).addFunction (function);
        function = PARSER.compileFunction ("PMT (rate, count, capital)=computePMT (rate, count, capital, capitalPaid (rate, count, capital, capital / count), capitalPaid (rate, count, capital, capital * Math.pow ((1 + rate), count)) / count, capital / count, capital * Math.pow ((1 + rate), count) / count)");

        if (computeJeksFunction () != computeJavaFunction ())
          throw new RuntimeException ("Jeks and Java give different results !");
      }
      catch (CompilationException ex)
      {
        ex.printStackTrace ();
        System.exit (0);
      }
    }

    public double computeJeksFunction ()
    {
      return function.computeFunction (new double [] {.065, 60, 10000});
    }

    public double computeJavaFunction ()
    {
      return PMT (.065, 60, 10000);
    }

    public double capitalPaid (double rate, double count, double capital, double testedPMT)
    {
      // This could be done faster in Java with a loop
      return count <= 0
               ? 0
               : testedPMT - capital * rate + capitalPaid (rate, count - 1, capital - (testedPMT - capital * rate), testedPMT);
    }

    public double computePMT (double rate, double count, double capital, double capitalPaid1, double capitalPaid2, double testedPMT1, double testedPMT2)
    {
      return Math.abs (capitalPaid1 - capital) < 0.01
               ? testedPMT1
               : Math.abs (capitalPaid1 - capital) < Math.abs (capitalPaid2 - capital)
                   ? computePMT (rate, count, capital, capitalPaid1, capitalPaid (rate, count, capital, testedPMT2 - (testedPMT2 - testedPMT1) / 2), testedPMT1, testedPMT2 - (testedPMT2 - testedPMT1) / 2)
                   : computePMT (rate, count, capital, capitalPaid (rate, count, capital, testedPMT1 + (testedPMT2 - testedPMT1) / 2), capitalPaid2, testedPMT1 + (testedPMT2 - testedPMT1) / 2, testedPMT2);
    }

    public double PMT (double rate, double count, double capital)
    {
      return computePMT (rate, count, capital, capitalPaid (rate, count, capital, capital / count), capitalPaid (rate, count, capital, capital * Math.pow ((1 + rate), count)) / count, capital / count, capital * Math.pow ((1 + rate), count) / count);
    }

    public int getLoopCount ()
    {
      return 100;
    }

    public String toString ()
    {
      return "MultipleFunctions : PMT (rate, count, capital) = computePMT (...)";
    }
  }

  private static void showSpeedMeterDialog (final FunctionTest tests [])
  {
    final Dialog  speedMeterDialog = new Dialog (new Frame (), "Average computation duration", false)
      {
        // This demo uses old Event model to be able to run with JDK 1.0
        public boolean action (Event evt, Object arg)
        {
          System.exit (0);
          return true;
        }
      };
    // Build the speed meter dialog with all its labels
    GridBagLayout layout = new GridBagLayout ();
    speedMeterDialog.setLayout (layout);
    // Left column titles panel
    Panel titlePanel = new Panel ();
    titlePanel.setLayout (new GridLayout (tests.length + 1, 1, 0, 10));
    // Speed values table panel
    Panel speedPanel = new Panel ();
    speedPanel.setLayout (new GridLayout (tests.length + 1, 3, 10, 10));
    titlePanel.add (new Label (""));
    speedPanel.add (new Label ("Jeks (\u00b5s)", Label.CENTER));
    speedPanel.add (new Label ("Java (\u00b5s)", Label.CENTER));
    speedPanel.add (new Label ("Ratio (Jeks/Java)", Label.CENTER));

    Label jeksAverageDurationLabel [] = new Label [tests.length];
    Label javaAverageDurationLabel [] = new Label [tests.length];
    Label ratioLabel []               = new Label [tests.length];
    for (int i = 0; i < tests.length; i++)
    {
      titlePanel.add (new Label (tests [i].toString ()));
      jeksAverageDurationLabel [i] = new Label ("0000000 ", Label.RIGHT);
      jeksAverageDurationLabel [i].setForeground (Color.green);
      jeksAverageDurationLabel [i].setBackground (Color.black);
      speedPanel.add (jeksAverageDurationLabel [i]);
      javaAverageDurationLabel [i] = new Label ("0000000 ", Label.RIGHT);
      javaAverageDurationLabel [i].setForeground (Color.red);
      javaAverageDurationLabel [i].setBackground (Color.black);
      speedPanel.add (javaAverageDurationLabel [i]);
      ratioLabel [i] = new Label ("      1 ", Label.RIGHT);
      ratioLabel [i].setForeground (Color.lightGray);
      ratioLabel [i].setBackground (Color.black);
      speedPanel.add (ratioLabel [i]);
    }
    speedMeterDialog.add (titlePanel);
    layout.setConstraints (titlePanel, new GridBagConstraints2 (0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets (10, 10, 10, 10), 0, 0));
    speedMeterDialog.add (speedPanel);
    layout.setConstraints (speedPanel, new GridBagConstraints2 (1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets (10, 0, 10, 10), 0, 0));
    Button button = new Button (" Quit ");
    speedMeterDialog.add (button);
    layout.setConstraints (button, new GridBagConstraints2 (0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets (10, 0, 10, 0), 0, 0));
    speedMeterDialog.setResizable (false);
    speedMeterDialog.pack ();
    // This is a trick to be sure Window.show () is called in JDK 1.0
    ((Window)speedMeterDialog).show ();

    final long jeksTotalDuration [] = new long [tests.length];
    final long javaTotalDuration [] = new long [tests.length];
    for (int testCount = 1; ; testCount++)
    {
      for (int i = 0; i < tests.length; i++)
      {
        long currentTime = System.currentTimeMillis ();

        jeksTotalDuration [i] += getJeksTestDuration (tests [i]);
        jeksAverageDurationLabel [i].setText (String.valueOf ((float)jeksTotalDuration [i] * 1000f / (testCount * tests [i].getLoopCount ())) + " ");

        javaTotalDuration [i] += getJavaTestDuration (tests [i]);
        javaAverageDurationLabel [i].setText (String.valueOf ((float)javaTotalDuration [i] * 1000f / (testCount * tests [i].getLoopCount ())) + " ");

        // Update ratio label
        ratioLabel [i].setText (javaTotalDuration [i] != 0
                                   ? String.valueOf ((int)Math.round ((double)jeksTotalDuration [i] / javaTotalDuration [i])) + " "
                                   : "");
        try
        {
          long ellapsedTime = System.currentTimeMillis () - currentTime;
          // Let a chance to your system to breath a little :
          // If ellapsedTime was less than 0.05 s then wait next .1 second
          //                                      otherwise wait .1 s
          Thread.sleep (ellapsedTime < 50
                         ? (100 - ellapsedTime)
                         : 100);
        }
        catch (InterruptedException e)
        { }
      }
    }
  }

  private static long getJeksTestDuration (FunctionTest test)
  {
    System.gc ();

    // Get current time, compute many times and return duration
    long currentTime = System.currentTimeMillis ();
    for (int i = test.getLoopCount (); i-- > 0; )
      test.computeJeksFunction ();
    return System.currentTimeMillis () - currentTime;
  }

  private static long getJavaTestDuration (FunctionTest test)
  {
    System.gc ();

    // Get current time, compute many times and return duration
    long currentTime = System.currentTimeMillis ();
    for (int i = test.getLoopCount () * 100; i-- > 0; )
      test.computeJavaFunction ();
    return (System.currentTimeMillis () - currentTime) / 100;
  }
}

