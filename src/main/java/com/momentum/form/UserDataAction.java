package com.momentum.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.momentum.entity.AuditTable;
import com.momentum.entity.User;
import com.momentum.service.AuditService;
import com.momentum.service.UserService;

@Component
@RequestScope
public class UserDataAction implements Serializable {

	private final UserData userData;

	@Autowired
	private AuditService auditService;

	@Autowired
	private UserService userService;

	private List<User> users;

	private List<AuditTable> audit;

	public UserDataAction(@Autowired UserData userData, List<User> users, List<AuditTable> audit) {
		super();
		this.userData = userData;
		this.users = users;
		this.audit = audit;

	}

	public String printVal() {

		AuditTable auditTable = new AuditTable();
		auditTable.setEquation(userData.getNumber1());
		auditTable.setAnswer(String.valueOf(eval(userData.getNumber1())));
		auditTable.setUser(auditService.getUser());
		auditTable.setTimeStamp(auditService.getTimeStamp());
		auditTable.setCreatedDate(new Date());

		try {
			auditService.saveData(auditTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public String getAnswer() {
		if (userData.getNumber1() != null) {
			return String.valueOf(eval(userData.getNumber1()));
		} else {
			return "0";
		}
	}

	public double eval(final String str) {
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ')
					nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				nextChar();
				double x = parseExpression();
				if (pos < str.length())
					throw new RuntimeException("Unexpected: " + (char) ch);
				return x;
			}

			// Grammar:
			// expression = term | expression `+` term | expression `-` term
			// term = factor | term `*` factor | term `/` factor
			// factor = `+` factor | `-` factor | `(` expression `)` | number
			// | functionName `(` expression `)` | functionName factor
			// | factor `^` factor

			double parseExpression() {
				double x = parseTerm();
				for (;;) {
					if (eat('+'))
						x += parseTerm(); // addition
					else if (eat('-'))
						x -= parseTerm(); // subtraction
					else
						return x;
				}
			}

			double parseTerm() {
				double x = parseFactor();
				for (;;) {
					if (eat('*'))
						x *= parseFactor(); // multiplication
					else if (eat('/'))
						x /= parseFactor(); // division
					else
						return x;
				}
			}

			double parseFactor() {
				if (eat('+'))
					return +parseFactor(); // unary plus
				if (eat('-'))
					return -parseFactor();
				if (eat('*')) {
					return parseFactor();
				}
				if (eat('^')) {
					return parseFactor();
				}

				double x;
				int startPos = this.pos;
				if (eat('(')) { // parentheses
					x = parseExpression();
					if (!eat(')'))
						throw new RuntimeException("Missing ')'");
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
					while ((ch >= '0' && ch <= '9') || ch == '.')
						nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { // functions
					while (ch >= 'a' && ch <= 'z')
						nextChar();
					String func = str.substring(startPos, this.pos);
					if (eat('(')) {
						x = parseExpression();
						if (!eat(')'))
							throw new RuntimeException("Missing ')' after argument to " + func);
					} else {
						x = parseFactor();
					}
					if (func.equals("sqrt"))
						x = Math.sqrt(x);
					else if (func.equals("sin"))
						x = Math.sin(Math.toRadians(x));
					else if (func.equals("cos"))
						x = Math.cos(Math.toRadians(x));
					else if (func.equals("tan"))
						x = Math.tan(Math.toRadians(x));
					else if (func.equals("pi"))
						x = Math.PI;
					else if (func.equals("e"))
						x = Math.E;
					else
						throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char) ch);
				}

				if (eat('^'))
					x = Math.pow(x, parseFactor()); // exponentiation

				return x;
			}
		}.parse();
	}

	public String getDates() {

		if (userData.getStartDate() != null && userData.getEndDate() != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateStart = sdf.format(userData.getStartDate());
			String dateEnd = sdf.format(userData.getEndDate());
			return "";
		} else {
			return "";
		}

	}

	public List<User> getUsers() {

		return userService.findAllUsers();
	}

	public List<AuditTable> getAudits() {
		return auditService.findByUserName(userData.getUsername());
	}

}
