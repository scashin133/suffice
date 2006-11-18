REM Requires JDK 1.2 or higher java compiler

@echo off

SET JEKSPARSERSRC=com\eteks\parser\*.java com\eteks\parser\node\*.java

SET JEKSTOOLSSRC=JeksDisplay.java JeksJavaPerformanceTest.java com\eteks\tools\awt\*.java com\eteks\tools\calculator\*.java com\eteks\tools\d3d\*.java

SET JEKSSRC=com\eteks\jeks\*.java com\eteks\tools\awt\SplashScreenWindow.java com\eteks\tools\swing\HTMLDocumentViewer.java 
SET JEKSRESOURCES=com\eteks\jeks\resources
SET JEKSICONS=toolbarButtonGraphics\general

SET JEKSCALCULATORSRC=com\eteks\tools\calculator\*.java

SET JEKSAPPLETSSRC=com\eteks\tools\calculator\*.java JeksDisplay.java

SET PJADOCSRC=com.eteks.parser com.eteks.parser.node com.eteks.jeks

REM
REM jeksparser.jar build
REM
echo Compiling %JEKSPARSERSRC%

cd src
md ..\lib\tmp1
javac -d ..\lib\tmp1 -sourcepath . %JEKSPARSERSRC%
jar cfM ..\lib\jeksparser.jar -C ..\lib\tmp1 .

REM
REM jekstools.jar build
REM
echo Compiling %JEKSTOOLSSRC%

md ..\lib\tmp2
javac -classpath ..\lib\jeksparser.jar -d ..\lib\tmp2 -sourcepath . %JEKSTOOLSSRC%
jar cfM ..\lib\jekstools.jar -C ..\lib\tmp2 .

REM
REM jeks.jar build
REM
echo Compiling %JEKSSRC%

md ..\lib\tmp3
javac -d ..\lib\tmp3 -sourcepath . %JEKSSRC% %JEKSPARSERSRC%

md ..\lib\tmp3\%JEKSRESOURCES%
copy %JEKSRESOURCES% ..\lib\tmp3\%JEKSRESOURCES%
md ..\lib\tmp3\toolbarButtonGraphics
md ..\lib\tmp3\%JEKSICONS%
copy %JEKSICONS% ..\lib\tmp3\%JEKSICONS%

jar cfm ..\lib\jeks.jar META-INF\MANIFEST.MF -C ..\lib\tmp3 .

REM
REM calculator.jar build
REM
echo Compiling %JEKSCALCULATORSRC%

md ..\lib\tmp4
javac -d ..\lib\tmp4 -sourcepath . %JEKSCALCULATORSRC%

del ..\lib\tmp4\com\eteks\parser\AbstractSyntax.class
del ..\lib\tmp4\com\eteks\parser\DefaultSyntax.class
del ..\lib\tmp4\com\eteks\parser\DoubleInterpreter.class
del ..\lib\tmp4\com\eteks\parser\Function.class
jar cfM ..\lib\calculator.jar -C ..\lib\tmp4 .

REM
REM applets classes build
REM
echo Compiling %JEKSAPPLETSSRC%

javac -d ..\lib -sourcepath . %JEKSAPPLETSSRC%

REM
REM javadoc build
REM
cd ..
javadoc -public -sourcepath src -d doc -version -author -windowtitle "Jeks" -header "<A HREF='http://www.eteks.com'><FONT SIZE='+1'>http://www.eteks.com</FONT></A>" -footer "<P><CENTER>&copy; 1998-2003 eTeks - All rights reserved</CENTER>" com.eteks.parser com.eteks.jeks com.eteks.parser.node
