package andrews.ucalc;

import java.util.*;

public class Expression {
	/*
	 * Strings used for storing expression.
	 */
	String s, x;

	/*
	 * Term evaluator for number literals.
	 */
	long term() {
		long ans = 0;
		StringBuffer temp = new StringBuffer();
		String base = new String();
		boolean readingBase = false;
		boolean readingBaseCleared = false;
		final String baseDeclaration = "b";
		while (s.length() > 0) {
			final char cur = s.charAt(0);
			if (cur == '_') {
				readingBase = true;
				s = s.substring(1);
				continue;
			}
			if (readingBase) {
				if (Character.isAlphabetic(cur)) {
					base += cur;
					s = s.substring(1);
					continue;
				} else if (Character.isDigit(cur)) {
					if (!readingBaseCleared
							&& base.toString().equals(baseDeclaration)) {
						base = base.replace(base, "");
						readingBaseCleared = true;
					}
					if (readingBaseCleared) {
						base += cur;
						s = s.substring(1);
						continue;
					} else {
						readingBase = false;
					}
				} else {
					readingBase = false;
				}
			}
			if (!readingBase) {
				if (cur == '.') {
					temp.append('.');
					s = s.substring(1);
				}
				if (cur == 'e' || cur == 'E') {
					temp.append('e');
					s = s.substring(1);
					temp.append(s.charAt(0));
					s = s.substring(1);
				}
				if (cur == '%') {
					String prefix = "";
					int cnt = 0;
					for (char c : temp.toString().toCharArray()) {
						if (Character.isDigit(c))
							cnt++;
					}
					if (cnt == 1) {
						prefix = "0.0";
					} else if (cnt == 2) {
						prefix = "0.";
					} else {
						temp.insert(temp.length() - 2, ".");
					}
					temp.insert(0, prefix);
					s = s.substring(1);
					break;
				}
				if (Character.isDigit(cur) || Character.isAlphabetic(cur)) {
					temp.append(cur);
					s = s.substring(1);
				} else {
					break;
				}
			}
		}
		int base_i = 10;
		if (base.length() > 0) {
			base_i = Integer.valueOf(base.toString()).intValue();
		}
		ans = Long.valueOf(temp.toString(), base_i).longValue();
		return ans;
	}

	/*
	 * Parentheses solver.
	 */
	long paren() {
		long ans;
		if (s.charAt(0) == '(') {
			s = s.substring(1);
			ans = add();
			s = s.substring(1);
		} else {
			ans = term();
		}
		return ans;
	}

	/*
	 * Exponentiation solver.
	 */
	long exp() {
		boolean neg = false;
		if (s.charAt(0) == '-') {
			neg = true;
			s = s.substring(1);
		}
		long ans = paren();
		while (s.length() > 0) {
			if (s.charAt(0) == '^') {
				s = s.substring(1);
				boolean expNeg = false;
				if (s.charAt(0) == '-') {
					expNeg = true;
					s = s.substring(1);
				}
				double e = paren();
				if (ans < 0) { // if it's negative
					long x = 1;
					if (Math.ceil(e) == e) { // only raise to an integer
						if (expNeg)
							e *= -1;
						if (e == 0)
							ans = 1;
						else if (e > 0)
							for (int i = 0; i < e; i++)
								x *= ans;
						else
							for (int i = 0; i < -e; i++)
								x /= ans;
						ans = x;
					} else {
						ans = Double.valueOf(Math.log(-1)).longValue(); // otherwise
																		// make
																		// it
																		// NaN
					}
				} else if (expNeg)
					ans = Double.valueOf(Math.exp(-e * Math.log(ans)))
							.longValue();
				else
					ans = Double.valueOf(Math.exp(e * Math.log(ans)))
							.longValue();
			} else
				break;
		}
		if (neg)
			ans *= -1;
		return ans;
	}

	/*
	 * Trigonometric function solver.
	 */
	long trig() {
		long ans = 0;
		boolean found = false;
		if (s.indexOf("sin") == 0) {
			s = s.substring(3);
			ans = Double.valueOf(Math.sin(trig())).longValue();
			found = true;
		} else if (s.indexOf("cos") == 0) {
			s = s.substring(3);
			ans = Double.valueOf(Math.cos(trig())).longValue();
			found = true;
		} else if (s.indexOf("tan") == 0) {
			s = s.substring(3);
			ans = Double.valueOf(Math.tan(trig())).longValue();
			found = true;
		}
		if (!found) {
			ans = exp();
		}
		return ans;
	}

	/*
	 * Multiplication, division expression solver.
	 */
	long mul() {
		long ans = trig();
		if (s.length() > 0) {
			while (s.length() > 0) {
				if (s.charAt(0) == '*') {
					s = s.substring(1);
					ans *= trig();
				} else if (s.charAt(0) == '/') {
					s = s.substring(1);
					ans /= trig();
				} else
					break;
			}
		}
		return ans;
	}

	/*
	 * Addition, subtraction expression solver.
	 */
	long add() {
		long ans = mul();
		while (s.length() > 0) {
			if (s.charAt(0) == '+') {
				s = s.substring(1);
				ans += mul();
			} else if (s.charAt(0) == '-') {
				s = s.substring(1);
				ans -= mul();
			} else {
				break;
			}
		}
		return ans;
	}

	/*
	 * Public access method to evaluate this expression.
	 */
	public long evaluate() {
		s = x.intern();
		long last = add();
		return last;
	}

	/*
	 * Creates new Expression.
	 */
	public Expression(String s) {
		// remove white space, assume only spaces or tabs
		StringBuffer b = new StringBuffer();
		StringTokenizer t = new StringTokenizer(s, " ");
		while (t.hasMoreElements())
			b.append(t.nextToken());
		t = new StringTokenizer(b.toString(), "\t");
		b = new StringBuffer();
		while (t.hasMoreElements())
			b.append(t.nextToken());

		x = b.toString();
	}

	/*
	 * The String value of this Expression.
	 */
	public String toString() {
		return x.intern();
	}
}
