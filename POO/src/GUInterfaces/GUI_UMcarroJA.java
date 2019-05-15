
//------------------------------------------------------------------

package GUInterfaces;

//------------------------------------------------------------------

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;

//------------------------------------------------------------------

public class GUI_UMcarroJA {  

	//------------------------------------------------------------------

	private final static String applicationName = "UMCarroJA!";
	private final static String basePathImages = "imgs/";

	private final static int x_size = 600, y_size = 600;

	private static JFrame applicationMainWindow = new JFrame();

	//------------------------------------------------------------------

	public void main_menu_view () {

		applicationMainWindow = new JFrame(applicationName);

		applicationMainWindow.setSize(500, 500);
		applicationMainWindow.setLayout(null);
		applicationMainWindow.setVisible(true);

		applicationMainWindow
			.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		applicationMainWindow.getContentPane().setBackground(Color.black);

		//---------------------------------------------------------

		ImageIcon log_image = new ImageIcon(basePathImages + "login_icon.png");

		JButton login_buttom = new JButton(log_image);

		//setBounds(x, y, width, height);
		login_buttom.setBounds(x_size/2, y_size/2, 90, 25);

		login_buttom.addActionListener(new ActionListener() {

			public void actionPerformed (ActionEvent e) {

				applicationMainWindow.setVisible(false);
			}

		});

		applicationMainWindow.add(login_buttom);

		//---------------------------------------------------------
		
		JLabel mainText_label_1 = new JLabel("Bem-Vindo à APP UMCarroJA!");
		JLabel logText_label_2  = new JLabel("Efetue o log-in, por favor.");

		applicationMainWindow.add(mainText_label_1, BorderLayout.CENTER);
		applicationMainWindow.add(logText_label_2, BorderLayout.CENTER);

	}

	//------------------------------------------------------------------

}