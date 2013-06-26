import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class HelloWorldSwing extends JFrame {

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel celsiusLabel;
    private javax.swing.JButton convertButton;
    private javax.swing.JLabel fahrenheitLabel;
    private javax.swing.JTextField tempTextField;
    // End of variables declaration//GEN-END:variables

	private JTextField nameField;
	private JTextField brithdayField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloWorldSwing frame = new HelloWorldSwing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelloWorldSwing() {
		this.initComponents();
	}
	
	private void initComponents(){
		
		//CelsiusConverter.java
		tempTextField = new JTextField();
        celsiusLabel = new javax.swing.JLabel();
        convertButton = new javax.swing.JButton();
        fahrenheitLabel = new javax.swing.JLabel();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Celsius Converter");
 
        celsiusLabel.setText("Celsius");
 
        convertButton.setText("Convert");
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });
 
        fahrenheitLabel.setText("Fahrenheit");
        
        JButton displayNameButton = new JButton();
        displayNameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        displayNameButton.setText("DisplayName");
        
        JButton displayBirthdayButton = new JButton();
        displayBirthdayButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		displayBirthdayButtonActionPerformed(e);
        	}
        });
        displayBirthdayButton.setText("DisplayBirthday");
        
        nameField = new JTextField();
        
        brithdayField = new JTextField();
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        					.addGroup(layout.createSequentialGroup()
        						.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(celsiusLabel))
        					.addGroup(layout.createSequentialGroup()
        						.addComponent(convertButton)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(fahrenheitLabel))
        					.addComponent(displayNameButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(nameField)
        					.addComponent(displayBirthdayButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(brithdayField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(tempTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(celsiusLabel))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(convertButton)
        				.addComponent(fahrenheitLabel))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(displayNameButton)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(displayBirthdayButton)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(brithdayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(67, Short.MAX_VALUE))
        );
        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {tempTextField, convertButton});
        getContentPane().setLayout(layout);
        pack();
	}

	private void displayBirthdayButtonActionPerformed(java.awt.event.ActionEvent evt){
		//click the button then display my birthday
		brithdayField.setText("1992 03 30");
	}
	 private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
		//Parse degrees Celsius as a double and convert to Fahrenheit
		        int tempFahr = (int)((Double.parseDouble(tempTextField.getText()))
		            * 1.8 + 32);
		        fahrenheitLabel.setText(tempFahr + " Fahrenheit");
		    }//GEN-LAST:event_convertButtonActionPerformed
}
