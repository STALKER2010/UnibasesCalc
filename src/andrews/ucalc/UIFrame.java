package andrews.ucalc;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Cursor;

import javax.swing.JTextArea;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIFrame extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = -6373767470343788852L;
	private JPanel contentPane;
	private JTextField expressionField;
	private JTextField resultField;
	private JPanel buttonsPanel;
	private JButton but7;
	private JButton but8;
	private JButton btnbase;
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JButton but5;
	private JButton but6;
	private JPanel panel;
	private JTextArea lblSolvation;
	private JTextField baseField;

	/**
	 * Create the frame.
	 */
	public UIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		setTitle("UniBase Calc");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		expressionField = new JTextField();
		panel.add(expressionField);
		expressionField.setText("212_b4 * 33_b4");
		expressionField.setToolTipText("Expression to evaluate");

		baseField = new JTextField();
		baseField.setPreferredSize(new Dimension(40, 28));
		baseField.setMinimumSize(new Dimension(40, 28));
		baseField.setText("4");
		panel.add(baseField, BorderLayout.EAST);
		expressionField.addActionListener(this);
		expressionField.addKeyListener(this);

		resultField = new JTextField();
		resultField
				.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		resultField.setFocusable(false);
		resultField.setEditable(false);
		resultField.setToolTipText("Result of evaluation");
		contentPane.add(resultField, BorderLayout.SOUTH);
		resultField.setColumns(10);

		buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(buttonsPanel, BorderLayout.CENTER);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

		but1 = new JButton("+");
		but1.setFocusable(false);
		but1.setAlignmentY(Component.TOP_ALIGNMENT);
		but1.setVerticalAlignment(SwingConstants.TOP);
		but1.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but1);

		but2 = new JButton("-");
		but2.setFocusable(false);
		but2.setAlignmentY(Component.TOP_ALIGNMENT);
		but2.setVerticalAlignment(SwingConstants.TOP);
		but2.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but2);

		but3 = new JButton("*");
		but3.setFocusable(false);
		but3.setAlignmentY(Component.TOP_ALIGNMENT);
		but3.setVerticalAlignment(SwingConstants.TOP);
		but2.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but3);

		but4 = new JButton("/");
		but4.setFocusable(false);
		but4.setAlignmentY(Component.TOP_ALIGNMENT);
		but4.setVerticalAlignment(SwingConstants.TOP);
		but4.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but4);

		but5 = new JButton("(");
		but5.setFocusable(false);
		but5.setAlignmentY(Component.TOP_ALIGNMENT);
		but5.setVerticalAlignment(SwingConstants.TOP);
		but5.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but5);

		but6 = new JButton(")");
		but6.setFocusable(false);
		but6.setAlignmentY(Component.TOP_ALIGNMENT);
		but6.setVerticalAlignment(SwingConstants.TOP);
		but6.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but6);

		but7 = new JButton("%");
		but7.setFocusable(false);
		but7.setAlignmentY(Component.TOP_ALIGNMENT);
		but7.setVerticalAlignment(SwingConstants.TOP);
		but7.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but7);

		but8 = new JButton(".");
		but8.setFocusable(false);
		but8.setAlignmentY(Component.TOP_ALIGNMENT);
		but8.setVerticalAlignment(SwingConstants.TOP);
		but8.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(but8);

		btnbase = new JButton("_b");
		btnbase.setFocusable(false);
		btnbase.setAlignmentY(Component.TOP_ALIGNMENT);
		btnbase.setVerticalAlignment(SwingConstants.TOP);
		btnbase.setHorizontalAlignment(SwingConstants.LEFT);
		buttonsPanel.add(btnbase);

		lblSolvation = new JTextArea("Solution");
		lblSolvation.setFocusable(false);
		lblSolvation.setEditable(false);
		lblSolvation.setPreferredSize(new Dimension(150, 16));
		lblSolvation.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblSolvation.setAlignmentY(Component.TOP_ALIGNMENT);
		lblSolvation.setMinimumSize(new Dimension(150, 16));
		lblSolvation.setMaximumSize(new Dimension(150, 16));
		contentPane.add(lblSolvation, BorderLayout.EAST);

		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		but5.addActionListener(this);
		but6.addActionListener(this);
		but7.addActionListener(this);
		but8.addActionListener(this);
		btnbase.addActionListener(this);
		lblSolvation.addKeyListener(this);

		calculate(expressionField.getText());
	}

	public void actionPerformed(ActionEvent evt) {
		Object srcO = evt.getSource();
		if (srcO instanceof JButton) {
			JButton b = (JButton) srcO;
			expressionField.setText(expressionField.getText() + b.getText());
		}
		calculate(expressionField.getText());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		calculate(expressionField.getText());
	}

	public void calculate(final String s) {
		Expression e = new Expression(s);
		try {
			int base = 10;
			{
				try {
					base = Integer.valueOf(baseField.getText());
				} catch (NumberFormatException ex1) {
				}
			}
			String res = Long.toString(e.evaluate(), base);
			resultField.setText(res);
			solutionChange(s);
		} catch (Exception ex) {
		}
	}

	private void solutionChange(final String s) {
		final int cP = countOf(s, '+');
		final int cMi = countOf(s, '-');
		final int cMu = countOf(s, '*');
		final int cD = countOf(s, '/');
		if ((cP + cMi + cMu + cD) == 1) {
			if (cP == 1) {
				plus(s);
			} else if (cMi == 1) {
				minus(s);
			} else if (cMu == 1) {
				mul(s);
			} else if (cD == 1) {
				div(s);
			}
		}
	}

	private void plus(final String s) {
		// TODO Auto-generated method stub

	}

	private void minus(final String s) {
		// TODO Auto-generated method stub

	}

	private static final Pattern baseP = Pattern.compile(".*_b(\\d+)");

	private void mul(final String str) {
		try {
			final String[] data = str.split("\\*");
			String fD = data[0].trim().intern();
			String sD = data[1].trim().intern();
			int base1 = 10;
			int base2 = 10;
			{
				if (fD.contains("_b")) {
					final Matcher m = baseP.matcher(fD);
					if (m.matches()) {
						base1 = Integer.valueOf(m.group(1));
					}
				}
				if (sD.contains("_b")) {
					final Matcher m = baseP.matcher(sD);
					if (m.matches()) {
						base2 = Integer.valueOf(m.group(1));
					}
				}
			}
			fD = fD.substring(0, fD.indexOf("_"));
			sD = sD.substring(0, sD.indexOf("_"));
			Long f = 0L;
			Long s = 0L;
			try {
				f = Long.valueOf(fD, base1);
				s = Long.valueOf(sD, base2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			final List<String> proc = new ArrayList<String>();
			for (Character c : sD.toCharArray()) {
				final Long cl = Chars.c2i(c, base2);
				proc.add(Long.toString(cl * f, base2));
			}
			final StringBuilder res = new StringBuilder();
			final long maxN = Math.max(f, s);
			res.append("   ");
			res.append(fD);
			res.append("\r\n");
			res.append(" + ");
			res.append(sD);
			res.append("\r\n");
			final int lineLen = Long.toString(maxN).length() * 6;
			for (int i = 0; i < lineLen; i++) {
				res.append("-");
			}
			res.append("\r\n");
			for (int i = proc.size() - 1; i >= 0; i--) {
				res.append(" ");
				res.append(proc.get(i));
				res.append("\r\n");
			}
			for (int i = 0; i < lineLen; i++) {
				res.append("-");
			}
			res.append("\r\n");
			res.append(resultField.getText());
			lblSolvation.setText(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void div(final String s) {
		// TODO Auto-generated method stub

	}

	public static final int countOf(final String s, final char c) {
		return s.length() - s.replace(Character.toString(c), "").length();
	}

}
