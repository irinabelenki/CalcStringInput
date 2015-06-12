import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter line in regular format");
		try {
			String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
			String expressionInBrackets = null;
			int openBracket = -1;
			int closeBracket = -1;
			while(line.length() > 1) {
				System.out.println("Current line: " + line);
				openBracket = line.lastIndexOf('(');
				if(openBracket == -1) {
					String resString = computeExpression(line);
					System.out.println("Result: " + resString);
					break;
				}
				else {
					closeBracket = line.substring(openBracket).indexOf(')');
					if ( closeBracket == -1 ) {
						throw new Exception("Invalid input string");
					}
					expressionInBrackets = line.substring(openBracket + 1, openBracket + closeBracket);
					System.out.println("Expression in brackets: " + expressionInBrackets);
				}
				String resString = computeExpression(expressionInBrackets);
				line = line.substring(0, openBracket) + 
					   resString + 
					   line.substring(openBracket + closeBracket + 1);
				System.out.println("Line after expression computing: " + line);
			}			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

	}
	
	public static String computeExpression(String expression) throws Exception {
		StringTokenizer st = new StringTokenizer(expression);
		List<String> elementsList = new ArrayList<String>();
        while (st.hasMoreTokens()) {
        	elementsList.add(st.nextToken());
        }
        for(int i = 0; i < 2; i++) {
			if (elementsList.size() == 1) {
				break;
			}
				ListIterator<String> it = elementsList.listIterator();
				while (it.hasNext()) {
					int nextIndex = it.nextIndex();
					String next = it.next();
					if (i == 0 && next.equals("*")) {
						if(nextIndex - 1 < 0 ||
						   nextIndex + 1 >= elementsList.size()) {
							throw new Exception("Invalid input string");
						}
						System.out.println(elementsList.get(nextIndex - 1));
						System.out.println(elementsList.get(nextIndex + 1));
						int res;
						try {
							res = Integer.parseInt(elementsList.get(nextIndex - 1)) *
							      Integer.parseInt(elementsList.get(nextIndex + 1));
						}
						catch(NumberFormatException nfe) {
							throw new Exception("Invalid operand");
						}
						elementsList.set(nextIndex, String.valueOf(res));
						elementsList.remove(nextIndex + 1);
						elementsList.remove(nextIndex - 1);
						break;
					}
					else if(i == 0 && next.equals("/")) {
						System.out.println(elementsList.get(nextIndex - 1));
						System.out.println(elementsList.get(nextIndex + 1));
						if(Integer.parseInt(elementsList.get(nextIndex + 1)) == 0) {
							throw new Exception("Cannot divide by zero");
						}
						int res = Integer.parseInt(elementsList.get(nextIndex - 1)) /
								  Integer.parseInt(elementsList.get(nextIndex + 1));
						elementsList.set(nextIndex, String.valueOf(res));
						elementsList.remove(nextIndex + 1);
						elementsList.remove(nextIndex - 1);
						break;
					}
					else if(i == 1 && next.equals("+")) {
						System.out.println(elementsList.get(nextIndex - 1));
						System.out.println(elementsList.get(nextIndex + 1));
						int res = Integer.parseInt(elementsList.get(nextIndex - 1)) +
								  Integer.parseInt(elementsList.get(nextIndex + 1));
						elementsList.set(nextIndex, String.valueOf(res));
						elementsList.remove(nextIndex + 1);
						elementsList.remove(nextIndex - 1);
						break;
					}
					else if(i == 1 && next.equals("-")) {
						System.out.println(elementsList.get(nextIndex - 1));
						System.out.println(elementsList.get(nextIndex + 1));
						int res = Integer.parseInt(elementsList.get(nextIndex - 1)) -
								  Integer.parseInt(elementsList.get(nextIndex + 1));
						elementsList.set(nextIndex, String.valueOf(res));
						elementsList.remove(nextIndex + 1);
						elementsList.remove(nextIndex - 1);
						break;
					}
				}
			
		}
		return elementsList.get(0);
	}

}
