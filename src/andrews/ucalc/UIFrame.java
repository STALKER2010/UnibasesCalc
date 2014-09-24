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
import javax.swing.JLabel;
import java.awt.Dimension;

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
	private JLabel lblSolvation;
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

		lblSolvation = new JLabel("Solvation");
		lblSolvation.setPreferredSize(new Dimension(150, 16));
		lblSolvation.setVerticalAlignment(SwingConstants.TOP);
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
				} catch (NumberFormatException ex1) {}
			}
			String res = Long.toString(e.evaluate(), base);
			resultField.setText(res);
		} catch (Exception ex) {
		}
	}

}
