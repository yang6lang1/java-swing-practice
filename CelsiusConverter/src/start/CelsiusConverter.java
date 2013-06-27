package start;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CelsiusConverter extends JFrame {

	private JPanel contentPane;
	private JTextField celsiusField;
	private JLabel lblFahrenheitDegree;
	private JTextField fahrenheitField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CelsiusConverter frame = new CelsiusConverter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void convertButtonPressed(){
		try{
			int tempFahr = (int)((Double.parseDouble(celsiusField.getText()))
	            * 1.8 + 32);
			fahrenheitField.setText(tempFahr+"");
		}catch(Exception e){
			fahrenheitField.setText("Please enter a number!");
		}
		
	}
	/**
	 * Create the frame.
	 */
	public CelsiusConverter() {
		setTitle("Celsius Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		celsiusField = new JTextField();
		celsiusField.setBounds(80, 15, 90, 20);
		contentPane.add(celsiusField);
		celsiusField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Celsius:");
		lblNewLabel_1.setBounds(10, 15, 90, 20);
		contentPane.add(lblNewLabel_1);
		
		lblFahrenheitDegree = new JLabel("Fahrenheit:");
		lblFahrenheitDegree.setBounds(10, 46, 90, 20);
		contentPane.add(lblFahrenheitDegree);
		
		fahrenheitField = new JTextField();
		fahrenheitField.setEditable(false);
		fahrenheitField.setColumns(10);
		fahrenheitField.setBounds(80, 46, 189, 20);
		contentPane.add(fahrenheitField);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertButtonPressed();
			}
		});
		btnConvert.setBounds(180, 14, 89, 23);
		contentPane.add(btnConvert);
		
		JLabel lblNewLabel = new JLabel("Celsius Degree:");
		//contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}
}
