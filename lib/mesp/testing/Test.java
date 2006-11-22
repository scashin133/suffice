import java.io.*;
import java.util.*;
import com.graphbuilder.math.*;

/**
Internal testing purposes only.
*/
public class Test {

	public static void main(String[] args) throws Throwable {
		BufferedReader br = new BufferedReader(new FileReader(new File("testcases.txt")));

		String s = br.readLine(); // read header

		while ((s = br.readLine()) != null) {
			int x1 = s.indexOf('"');
			int x2 = s.indexOf('"', x1 + 1);

			String expression = s.substring(x1 + 1, x2);
			String toStr = null;
			boolean canParse = true;
			double result = 0;
			String[] var = null;
			double[] val = null;

			int x3 = s.indexOf('"', x2 + 1);

			if (x3 != -1) {
				int x4 = s.indexOf('"', x3 + 1);
				toStr = s.substring(x3 + 1, x4);
				String t = s.substring(x2 + 1, x3).trim();
				StringTokenizer st = new StringTokenizer(t, "\t");
				st.nextToken();
				String vars = st.nextToken();
				result = Double.parseDouble(st.nextToken());

				st = new StringTokenizer(vars, ",");
				var = new String[st.countTokens()];
				val = new double[var.length];

				for (int i = 0; i < var.length; i++) {
					String n = st.nextToken();
					int x = n.indexOf("=");
					var[i] = n.substring(0, x).trim();
					val[i] = Double.parseDouble(n.substring(x + 1));
				}
			}
			else {
				canParse = false;
			}

			if (canParse) {
				Expression x = null;

				try {
					x = ExpressionTree.parse(expression);
				} catch (Throwable t) {
					System.out.println("expected no error parsing: " + expression);
					throw t;
				}

				FuncMap fm = new FuncMap();
				fm.loadDefaultFunctions();

				VarMap vm = new VarMap();
				for (int i = 0; i < var.length; i++)
					vm.setValue(var[i], val[i]);

				double r = x.eval(vm, fm);

				int c = 0;

				if (r != result)
					System.out.println("incorrect value, expected: " + result + " but got: " + r + "  " + expression);
				else
					c++;

				if (!x.toString().equals(toStr))
					System.out.println("incorrect toString: " + toStr + " but got: " + x.toString());
				else
					c++;

				if (c == 2) System.out.println("good");
			}
			else {
				ExpressionParseException epe = null;
				try {
					ExpressionTree.parse(expression);
				} catch (ExpressionParseException e) {
					epe = e;
				}

				if (epe == null)
					System.out.println("failure expected but passed: " + expression);
				else
					System.out.println("good");
			}
		}

		br.close();
	}
}