package biblioteca;

import java.awt.EventQueue;

import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Donar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Donar frame = new Donar();
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
	public Donar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDonacionDeLibros = new JLabel("Donacion de libros");
		lblDonacionDeLibros.setBounds(156, 11, 201, 28);
		contentPane.add(lblDonacionDeLibros);
		
		JLabel lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setBounds(10, 58, 104, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor");
		lblNewLabel_1.setBounds(10, 109, 104, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sipnosis");
		lblNewLabel_2.setBounds(10, 167, 104, 20);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(78, 58, 346, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(78, 109, 346, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(78, 167, 346, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Donar");
		btnNewButton.setToolTipText("Presione este boton para formalizar su donacion");
		btnNewButton.setMnemonic('d');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titulo = textField.getText();
				String autor = textField_1.getText();
				String sipnosis = textField_2.getText();
				if(titulo != "" && sipnosis != "" && autor != ""){
				EntityTransaction tx = Principal.em.getTransaction();
				tx.begin();
				Libro l = new Libro (autor, titulo, sipnosis);
				Principal.em.persist(l);
				tx.commit();
				Donar.this.setVisible(false);
				Opcion o = new Opcion();
				o.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(78, 209, 156, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Donar.this.setVisible(false);
				ListaLibros lb = new ListaLibros();
				lb.setVisible(true);
				Donar.this.dispose();
			}
		});
		btnNewButton_1.setMnemonic('v');
		btnNewButton_1.setToolTipText("Si se ha equivocado y no desea donar ningun libro, vuelva a lista de libros disponibles");
		btnNewButton_1.setBounds(281, 209, 143, 23);
		contentPane.add(btnNewButton_1);
	}
}
