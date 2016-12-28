package calculator;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculator implements ActionListener {
	
	private JButton button;
	private JFrame frame;
	private JTextField textField = new JTextField();
	private GridBagLayout layout = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	private boolean complete = false;
	
	
	public Calculator() {
		
		frame = new JFrame("Calculator");
		frame.setSize(300, 300);
		frame.setLayout(layout);
		
		gbc.fill = GridBagConstraints.BOTH;
		
		
		buildKeyboard();
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	

	public static void main(String[] args) {
		
SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new Calculator();
			}
		});

	}
	
	private void calculateResult() {
		
		complete = true;
		
		StringBuffer sb = new StringBuffer(textField.getText());
		char[] signs = new char[sb.length()];
		int count = 0;
		
		for (int i = 0; i < sb.length(); i++)
			if (sb.charAt(i) == '+' | sb.charAt(i) == '-' | sb.charAt(i) == '*' | sb.charAt(i) == '/')
			{
				signs[count] = sb.charAt(i);
				sb.setCharAt(i, '&');
				count++;
			}
		
		
		String splitLine = sb.toString();
		
		
		String[] numbers = splitLine.split("&");
		
		for (String line : numbers)
			if (line.isEmpty())
			{
				textField.setText(("ERROR"));
				return;
			}
		
		double total;
		
		try
		{
			total = Double.parseDouble(numbers[0]);
		}
		catch (NumberFormatException e)
		{
			textField.setText("ERROR");
			return;
		}
		
		
		
		for (int i = 1; i < numbers.length; i++)
		{
			double a;
			try
			{
				a = Double.parseDouble(numbers[i]);				
			}
			catch (NumberFormatException e)
			{
				textField.setText("ERROR");
				return;
			}
			
			char c = signs[i - 1];
			
			switch (c) {
			
			case '+': total += a;
			          break;
			case '-': total -= a;
			          break;
			case '*': total *= a;
			          break;
			case '/': if (a != 0)
						total /= a;
					  else
						  if (total == 0 & a == 0)
					  {
						  textField.setText("0");
						  return;
					  }
						  else
						  {
							  textField.setText("ERROR");
							  return;
						  }
			          break;			
			}
			
		}
		
		Formatter formatter = new Formatter(Locale.UK);
		
		if (total - Math.floor(total) > 0)
		{
			formatter.format("%-20.8f", total);
			String s = formatter.toString();
			textField.setText(trimLine(s));
		}
		else
			{
			 textField.setText(Integer.valueOf((int) total).toString());
			}
		formatter.close();
		
		
	}
	
	private void buildKeyboard() {
		
		textField.setText("");
		textField.addActionListener(ae -> {
			complete = true;
			calculateResult();
		});
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.1;
		frame.add(textField, gbc);
		
		gbc.weighty = 1.0;
		
		button = new JButton("7");
		button.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		frame.add(button, gbc);
		
		button = new JButton("8");
		button.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		frame.add(button, gbc);
		
		button = new JButton("9");
		button.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 1;
		frame.add(button, gbc);
		
		button = new JButton("/");
		button.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 1;
		frame.add(button, gbc);
		
		button = new JButton("C");
		button.addActionListener(ae -> textField.setText(""));
		button.setBackground(Color.red);
		button.setForeground(Color.white);
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		frame.add(button, gbc);
		
		button = new JButton("4");
		button.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		frame.add(button, gbc);
		
		button = new JButton("5");
		button.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		frame.add(button, gbc);
		
		button = new JButton("6");
		button.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 2;
		frame.add(button, gbc);
		
		button = new JButton("*");
		button.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 2;
		frame.add(button, gbc);
		
		button = new JButton("1");
		button.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.add(button, gbc);
		
		button = new JButton("2");
		button.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		frame.add(button, gbc);
		
		button = new JButton("3");
		button.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 3;
		frame.add(button, gbc);
		
		button = new JButton("+");
		button.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 3;
		frame.add(button, gbc);
		
		button = new JButton("=");
		button.addActionListener(ae -> calculateResult());
		button.setBackground(Color.green);
		button.setForeground(Color.white);
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		frame.add(button, gbc);
		
		button = new JButton("0");
		button.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		frame.add(button, gbc);
		
		button = new JButton(".");
		button.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 4;
		frame.add(button, gbc);
		
		button = new JButton("sqrt");
		button.addActionListener(ae -> {
			try
			{
				complete = true;
				Double value = Double.valueOf(textField.getText());
				value = Math.sqrt(value);
				textField.setText(value.toString());
			}
			catch (NumberFormatException e)
			{
				textField.setText("ERROR");
				return;
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 4;
		frame.add(button, gbc);
		
		button = new JButton("-");
		button.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 4;
		frame.add(button, gbc);
		
		
		
	}

   private String trimLine(String s) {
		
		if (s.contains("."))
		{
			char[] charArray = s.toCharArray();
			
			int index = charArray.length - 1;
			
			while (charArray[index] == '0' || charArray[index] == '.')
			{
				index--;
			}
			
			s = s.substring(0, index + 1);
			
			return s ;
		}
		else return s;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (!complete)
			 textField.setText(textField.getText() + ae.getActionCommand());
		   else
		   {
			   textField.setText(ae.getActionCommand());
			   complete = false;
		   }
		
	}

}
