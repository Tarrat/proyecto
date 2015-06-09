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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Registro extends JFrame {
	private SocioHibernateDAO shd;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	@SuppressWarnings("deprecation")
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registro");
		lblNewLabel.setBounds(170, 11, 51, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(55, 70, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(55, 105, 89, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("DNI");
		lblNewLabel_3.setBounds(55, 144, 46, 14);
		contentPane.add(lblNewLabel_3);

		final JLabel lblNewLabel_6 = new JLabel("DNI no valido. Debe contener ocho cifras sin letra");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(55, 169, 295, 14);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);


		final JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setMnemonic('r');
		btnNewButton.setToolTipText("Pulse para formalizar su registro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estaVacio(btnNewButton);
				dniValido(btnNewButton, lblNewLabel_6);
				existeUsuario(btnNewButton, lblNewLabel_6);
				if (!btnNewButton.isEnabled()==false && (!textField.getText().equals("Administrador"))){
					String nombre = textField.getText();
					String contraseña = passwordField.getText();
					String email = textField_2.getText();
					EntityTransaction tx = Principal.em.getTransaction();
					tx.begin();
					Socio s = new Socio (nombre, contraseña, email);
					Principal.em.persist(s);
					tx.commit();
					Registro.this.setVisible(false);
					ListaLibros lb = new ListaLibros();
					lb.setVisible(true);
					Principal.conectado = s;
					Registro.this.dispose();
				}
				else if (!btnNewButton.isEnabled()==false && (textField.getText().equals("Administrador"))){
					String nombre = textField.getText();
					String contraseña = passwordField.getText();
					String email = textField_2.getText();
					EntityTransaction tx = Principal.em.getTransaction();
					tx.begin();
					Socio s = new Socio (nombre, contraseña, email);
					Principal.em.persist(s);
					tx.commit();
					Registro.this.setVisible(false);
					OpcionAdmin a = new OpcionAdmin();
					a.setVisible(true);
					Principal.conectado = s;
					Registro.this.dispose();
				}
			}
		});
		btnNewButton.setBounds(55, 202, 89, 23);
		contentPane.add(btnNewButton);

		final JLabel lblNewLabel_5 = new JLabel("Usuario no disponible");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(218, 42, 128, 14);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);

		textField = new JTextField();
		textField.setToolTipText("Escriba su nombre de usuario");
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				existeUsuario(btnNewButton, lblNewLabel_5);
				estaVacio(btnNewButton);
			}
		});
		textField.setBounds(218, 67, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setToolTipText("Introduzca su DNI, solo 8 cifras");
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				dniValido(btnNewButton, lblNewLabel_6);
				estaVacio(btnNewButton);
			}
		});


		textField_2.setBounds(218, 141, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setMnemonic('c');
		btnNewButton_1.setToolTipText("Pulse para volver a la pantalla del login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro.this.setVisible(false);
				Principal pri = new Principal();
				pri.setVisible(true);
				Registro.this.dispose();
			}
		});
		btnNewButton_1.setBounds(218, 202, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(243, 28, 46, 14);
		contentPane.add(lblNewLabel_4);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Introduzca su password. Se recomienda minimo 8 caracteres");
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				estaVacio(btnNewButton);
			}

		});
		passwordField.setBounds(218, 102, 86, 20);
		contentPane.add(passwordField);


	}
	@SuppressWarnings("deprecation")
	private void estaVacio(final JButton btnNewButton) {
		if (passwordField.getText().length() == 0 || textField.getText() == "" || textField_2.getText() == ""){
			btnNewButton.enable(false);
		}
		else btnNewButton.enable(true);
	}

	@SuppressWarnings("deprecation")
	private void existeUsuario(final JButton btnNewButton,
			final JLabel lblNewLabel_5) {
		shd = new SocioHibernateDAO(Principal.em);
		String nombreSocio = textField.getText();
		List<Socio> listaSocios = new ArrayList<Socio>();
		listaSocios = shd.findAll();
		for (Socio s : listaSocios){
			if (s.getNombre().equals(nombreSocio) || (nombreSocio == "")){
				btnNewButton.enable(false);
				lblNewLabel_5.setVisible(true);
				break;
			}
			else btnNewButton.enable(true);
			lblNewLabel_5.setVisible(false);
		}
	}

	@SuppressWarnings("deprecation")
	private void dniValido(final JButton btnNewButton,
			final JLabel lblNewLabel_6) {
		shd = new SocioHibernateDAO(Principal.em);
		String dniSocio = textField_2.getText();
		int largoDni = 8;
		if ((dniSocio.length()) != largoDni){
			lblNewLabel_6.setVisible(true);
			btnNewButton.enable(false);
		}
		else if (dniSocio.length() == largoDni){
			try {
				@SuppressWarnings("unused")
				int dni = Integer.parseInt(dniSocio);
			} catch (NumberFormatException e1) {
				lblNewLabel_6.setVisible(true);
				btnNewButton.enable(false);
			} 
			List<Socio> listaSocios = new ArrayList<Socio>();
			listaSocios = shd.findAll();
			for (Socio s : listaSocios){
				if (s.getDni().equals(dniSocio)){
					btnNewButton.enable(false);
					lblNewLabel_6.setVisible(true);
					break;
				}
				else btnNewButton.enable(true);
				lblNewLabel_6.setVisible(false);
			}
		}
	}

}
