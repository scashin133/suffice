#!/bin/sh
# Requires JDK 1.2 or higher java compiler

JEKSPARSERSRC="com/eteks/parser/*.java com/eteks/parser/node/*.java"

JEKSTOOLSSRC="JeksDisplay.java 
  JeksJavaPerformanceTest.java 
  com/eteks/tools/awt/*.java 
  com/eteks/tools/calculator/*.java 
  com/eteks/tools/d3d/*.java"

JEKSSRC="com/eteks/jeks/*.java 
  com/eteks/tools/awt/SplashScreenWindow.java 
  com/eteks/tools/swing/HTMLDocumentViewer.java "

JEKSRESOURCES=com/eteks/jeks/resources

JEKSICONS=toolbarButtonGraphics/general

JEKSCALCULATORSRC=com/eteks/tools/calculator/*.java

JEKSAPPLETSSRC="com/eteks/tools/calculator/*.java JeksDisplay.java"

PJADOCSRC="com.eteks.parser com.eteks.parser.node com.eteks.jeks"

#jeksparser.jar build
echo Compiling $JEKSPARSERSRC

cd src
mkdir ../lib/tmp1
javac -d ../lib/tmp1 -sourcepath . $JEKSPARSERSRC
jar cfM ../lib/jeksparser.jar -C ../lib/tmp1 .

#jekstools.jar build
echo Compiling $JEKSTOOLSSRC

mkdir ../lib/tmp2
javac -classpath ../lib/jeksparser.jar -d ../lib/tmp2 -sourcepath . $JEKSTOOLSSRC
jar cfM ../lib/jekstools.jar -C ../lib/tmp2 .

#jeks.jar build
echo Compiling $JEKSSRC

mkdir ../lib/tmp3
javac -d ../lib/tmp3 -sourcepath . $JEKSSRC $JEKSPARSERSRC

mkdir ../lib/tmp3/$JEKSRESOURCES
cp $JEKSRESOURCES/* ../lib/tmp3/$JEKSRESOURCES
mkdir ../lib/tmp3/toolbarButtonGraphics
mkdir ../lib/tmp3/$JEKSICONS
cp $JEKSICONS/* ../lib/tmp3/$JEKSICONS

jar cfm ../lib/jeks.jar META-INF/MANIFEST.MF -C ../lib/tmp3 .

# calculator.jar build
echo Compiling $JEKSCALCULATORSRC

mkdir ../lib/tmp4
javac -d ../lib/tmp4 -sourcepath . $JEKSCALCULATORSRC

rm ../lib/tmp4/com/eteks/parser/AbstractSyntax.class
rm ../lib/tmp4/com/eteks/parser/DefaultSyntax.class
rm ../lib/tmp4/com/eteks/parser/DoubleInterpreter.class
rm ../lib/tmp4/com/eteks/parser/Function.class
jar cfM ../lib/calculator.jar -C ../lib/tmp4 .

# applets classes build
echo Compiling $JEKSAPPLETSSRC

javac -d ../lib -sourcepath . $JEKSAPPLETSSRC

# javadoc build
cd ..
javadoc -public -sourcepath src -d doc -version -author -windowtitle "Jeks" -header "<A HREF='http://www.eteks.com'><FONT SIZE='+1'>http://www.eteks.com</FONT></A>" -footer "<P><CENTER>&cp; 1998-2003 eTeks - All rights reserved</CENTER>" com.eteks.parser com.eteks.jeks com.eteks.parser.node
