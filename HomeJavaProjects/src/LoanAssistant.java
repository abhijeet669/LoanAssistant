import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoanAssistant {
	 static boolean computePayment;
		
	public static void main(String args[])
	{
		 JFrame frame=new JFrame();
			JTextField tf1=new JTextField();
			JTextField tf2=new JTextField();
			JTextField tf3=new JTextField();
			JTextField tf4=new JTextField();
	      		 
			JButton button=new JButton("Compute Monthly Payments");
			JButton button2=new JButton("New Loan Analysis");
			JButton button3=new JButton("x");
			JButton button4=new JButton("Exit");
		    JButton button5=new JButton("x");	
	
	
	Color Yellow =new Color(255,255,128);
   Color white=new Color(255,255,255);
   
   Font myFont = new Font("Arial", Font.PLAIN, 14);
	
	
	JLabel l1=new JLabel("Loan Balance");
	JLabel l2=new JLabel("Interest Rate");
	JLabel l3=new JLabel("Number Of Payments");
	JLabel l4=new JLabel("Monthly Payments");
  
   JLabel l6=new JLabel("Loan Analysis:");
  
   tf1.setFont(myFont);
   tf2.setFont(myFont);
   tf3.setFont(myFont);
   tf4.setFont(myFont);
   
  l1.setFont(myFont);
  l2.setFont(myFont);
  l3.setFont(myFont);
  l4.setFont(myFont);
   JTextArea ta=new JTextArea();
    
   ta.setFont(myFont);
   
   //Shifting the control using enter
   tf1.addActionListener((ActionListener)new ActionListener()
   		{
   	public void actionPerformed(ActionEvent e)
   	{
   		tf1.transferFocus();
   	}
   	   });
          
   
   tf2.addActionListener((ActionListener)new ActionListener()
	{
      public void actionPerformed(ActionEvent e)
       {
	       tf2.transferFocus();
       }
  });
   
   
   tf3.addActionListener((ActionListener)new ActionListener()
	{
     public void actionPerformed(ActionEvent e)
       {
	        tf3.transferFocus();
       }
  });
   
   
   tf4.addActionListener((ActionListener)new ActionListener()
	{
     public void actionPerformed(ActionEvent e)
      {
	     tf4.transferFocus();
      }
  });
   
   
   button5.addActionListener((ActionListener) new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			monthsButtonActionPerformed(e);
		}
   
		private void monthsButtonActionPerformed(ActionEvent e) 
		{
			ta.setFocusable(false);
			computePayment=true;
			button5.setVisible(false);
			button3.setVisible(true);
			tf3.setEditable(true);
			tf4.setBackground(Yellow);
			tf3.setBackground(white);
			tf4.setText("");
			tf4.setEditable(false);
			button.setText("Compute Monthly Payment");
			button2.setEnabled(false);
			
		}

	});

   
   button3.addActionListener((ActionListener) new ActionListener() {
   	public void actionPerformed(ActionEvent e)
   	{
   		paymentButtonActionPerformed(e);
   		
   	}
   
	private void paymentButtonActionPerformed(ActionEvent e) 
		{
			ta.setFocusable(false);
		 computePayment=false;
			button3.setVisible(false);
   		tf3.setEditable(false);
   		button5.setVisible(true);
   		tf3.setBackground(Yellow);
   		tf3.setText("");
   		tf4.setBackground(white);
   		button.setText("Compute Number Of Payments");
   		button2.setEnabled(false);
			
		}
	});
   
   button.addActionListener((ActionListener)new ActionListener()
   		{
   	public void actionPerformed(ActionEvent e)
   	    {
   		computeButtonActionPerformed(e);
   		}
   	
   	public boolean validateDecimalNumber(JTextField tf)
   	{
   		String s=tf.getText().trim();
   		boolean hasDecimal=false;
   		boolean valid=true;
   		if(s.length()==0)
   		{
   			valid=false;
   		}
   		else
   		{
   			for(int i=0;i<s.length();i++)
   			{
   				char c=s.charAt(i);
   				if (c >= '0' && c <= '9')
   				{
   					continue;
   				}
   				else if (c == '.' && !hasDecimal)
   				{
   					hasDecimal=true;
   				}
   				else
   				{
   				// invalid character found
   				valid = false;
   				}

   			}
   		}
   		tf.setText(s);
   		if (!valid)
   		{
   		tf.requestFocus();
   		}
   		return (valid);
   	}

		private void computeButtonActionPerformed(ActionEvent e) 
		{
			double balance,interest,payment,loanBalance,finalPayment;
   		int months;
   		double monthly_interest,multiplier;
   		if (validateDecimalNumber(tf1))
   		{
   		balance=Double.valueOf(tf1.getText()).doubleValue();
   		}
   		else
   		{
   			JOptionPane.showConfirmDialog(null, "Invalid or empty Loan Balance entry.\nPlease correct.", "Balance Input Error", JOptionPane.DEFAULT_OPTION,
   					JOptionPane.INFORMATION_MESSAGE);
   		return;
   		}
           if(validateDecimalNumber(tf2))
           {
   		interest=Double.valueOf(tf2.getText()).doubleValue();
           }
           else
           {
           	JOptionPane.showConfirmDialog(null, "Invalid or empty Interest Rate entry.\nPlease correct.", "Interest Input Error", JOptionPane.DEFAULT_OPTION,
           			JOptionPane.INFORMATION_MESSAGE);
           	return;
           }
   		monthly_interest=interest/1200;
   		
   		if(computePayment)
   		{
   			if (validateDecimalNumber(tf3))
   			{
	
   		months=Integer.valueOf(tf3.getText()).intValue();
   			}
   			else
   			{
   				JOptionPane.showConfirmDialog(null, "Invalid or empty Number of Payments entry.\nPlease correct.", "Number of Payments Input Error",
   						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
   				return;
   
   			}
   		if(interest==0)
   		{
   			payment=balance/months;
   		}
   		else
   		{
   			
   		
   		multiplier = Math.pow(1 + monthly_interest, months);
   		payment = balance * monthly_interest * multiplier / (multiplier - 1);
   		}
   		tf4.setText(new DecimalFormat("0.00").format(payment));
   		}
   		else
   		{    	
   			if(validateDecimalNumber(tf4))
   		{
   			payment =Double.valueOf(tf4.getText()).doubleValue();
   			if (payment <= (balance * monthly_interest + 1.0))
   			{
   			if (JOptionPane.showConfirmDialog(null, "Minimum payment must be $" +
   			new DecimalFormat("0.00").format((int)(balance * monthly_interest + 1.0)) + "\n" + "Do you want to use the minimum payment?", "Input Error", JOptionPane.YES_NO_OPTION,
   			JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
   			{
   			tf3.setText(new DecimalFormat("0.00").format((int)
   			(balance * monthly_interest + 1.0)));
   			payment =
   			Double.valueOf(tf3.getText()).doubleValue();
   			}
   			else
   			{
   			tf3.requestFocus();
   			return;
   			}
   			}

   		}
   		else
   		{
   			JOptionPane.showConfirmDialog(null, "Invalid or empty Monthly Payment entry.\nPlease correct.", "Payment Input Error", JOptionPane.DEFAULT_OPTION,
   					JOptionPane.INFORMATION_MESSAGE);
   			return;
   		}
   			if(interest==0)
   			{
   				months=(int)(balance/payment);
   			}
   			else
   			{
   			months = (int)((Math.log(payment) - Math.log(payment - balance * monthly_interest))/ Math.log(1 + monthly_interest));
   			}
   		    tf3.setText(String.valueOf(months));
		}
   				
   					payment =Double.valueOf(tf4.getText()).doubleValue();
   				
   					ta.setText("Loan Balance: $" + new
   							DecimalFormat("0.00").format(balance));
   							ta.append("\n" + "Interest Rate: " + new
   							DecimalFormat("0.00").format(interest) + "%");
   							loanBalance = balance;
   							for (int paymentNumber = 1; paymentNumber <= months - 1; paymentNumber++)
   							{
   							loanBalance += loanBalance * monthly_interest - payment;
   							}
   							finalPayment = loanBalance;
   							if (finalPayment > payment)
   							{
   								loanBalance += loanBalance * monthly_interest - payment;
   								finalPayment = loanBalance;
   								months++;
   								tf4.setText(String.valueOf(months));
   							}
   								ta.append("\n\n" + String.valueOf(months - 1) + " Payments of $" + new
   									DecimalFormat("0.00").format(payment));
   									ta.append("\n" + "Final Payment of: $" + new
   									DecimalFormat("0.00").format(finalPayment));
   									ta.append("\n" + "Total Payments: $" + new
   									DecimalFormat("0.00").format((months - 1) * payment + finalPayment));
   									ta.append("\n" + "Interest Paid $" + new
   									DecimalFormat("0.00").format((months - 1) * payment + finalPayment - balance));
   									button.setEnabled(false);
   									button2.setEnabled(true);
  										button2.requestFocus();
   							
   							
		}	
		});
 
   button4.addActionListener((ActionListener)new ActionListener()
   {
   	public void actionPerformed(ActionEvent e)
   	{
   		System.exit(0);
   	}
   });
   
   button2.addActionListener((ActionListener)new ActionListener()
   		{
   	public void actionPerformed(ActionEvent e)
   	{
   		if(computePayment)
   		{
   			tf3.setText("");
   			
   		}
   		else
   		{
   			tf4.setText("");
   		}
   		ta.setText("");
   		button.setEnabled(true);
   		button2.setEnabled(false);
   		tf1.requestFocus();
   	}
   	
   		});
   ta.setBounds(300,90,200,200);
   
	l1.setBounds(10,50,100,40);
   l2.setBounds(10,80,100,40);
   l3.setBounds(10,110,200,40);
   l4.setBounds(10,140,200,40);

	l6.setBounds(300,50,100,30);

	
	button.setBounds(60,200,220,30);
	button2.setBounds(65,250,180,30);
   button3.setBounds(250,120,40,20);
   button4.setBounds(350,300,100,30);
   button5.setBounds(250,150,40,20);
   
	tf1.setBounds(140,60,100,20);
	tf2.setBounds(140,90,100,20);
	tf3.setBounds(140,120,100,20);
	tf4.setBounds(140,150,100,20);
   
	

   frame.add(button5);
	frame.add(ta);
	frame.add(l6);

   frame.add(tf1);
	frame.add(tf2);
	frame.add(tf3);
	frame.add(tf4);
	frame.add(button);
   frame.add(button2);
   frame.add(button3);
   frame.add(button4);
	frame.add(l1);
	frame.add(l2);
	frame.add(l3);
	frame.add(l4);
	frame.setSize(600,400);
	frame.setLayout(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	}
}



