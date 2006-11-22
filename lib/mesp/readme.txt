Useful Information
------------------

The copyfiles.bat file is not required.  It is used to copy only
the necessary files from the com.graphbuilder package hierarchy
that are required for the MESP package.

The makeapi.bat file is also not required.  It is used to generate
the Javadocs from the source and put the output html in the api
directory.

In the src directory, there is a makejar.bat file.  This file will
compile the nested Java files and create a Jar file called Mesp.jar.
Compilation requires Java 1.2 or higher.  After the Jar file is
created, add it to the classpath.  The following is a sample program:

import com.graphbuilder.math.*;

public class MathTest {

	public static void main(String[] args) {
		String s = "pi*r^2";
		Expression x = ExpressionTree.parse(s);

		VarMap vm = new VarMap(false /* case sensitive */);
		vm.setValue("pi", Math.PI);
		vm.setValue("r", 5);

		FuncMap fm = null; // no functions in expression

		System.out.println(x); // (pi*(r^2))
		System.out.println(x.eval(vm, fm)); // 78.53981633974483

		vm.setValue("r", 10);
		System.out.println(x.eval(vm, fm)); // 314.1592653589793
	}
}