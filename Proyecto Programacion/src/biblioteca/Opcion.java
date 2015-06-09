package biblioteca;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Opcion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opcion frame = new Opcion();
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
	public Opcion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u00BFQue desea hacer?");
		lblNewLabel.setBounds(144, 23, 159, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Donar otro libro");
		btnNewButton.setMnemonic('d');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Donar d = new Donar();
				Opcion.this.setVisible(false);
				d.setVisible(true);
				Opcion.this.dispose();
			}
		});
		btnNewButton.setBounds(10, 132, 131, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.setMnemonic('c');
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
		btnNewButton_1.setBounds(290, 132, 134, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ir a la lista de libros");
		btnNewButton_2.setMnemonic('l');
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaLibros ll = new ListaLibros();
				Opcion.this.setVisible(false);
				ll.setVisible(true);
				Opcion.this.dispose();
			}
		});
		btnNewButton_2.setBounds(144, 132, 141, 23);
		contentPane.add(btnNewButton_2);
	}

}
