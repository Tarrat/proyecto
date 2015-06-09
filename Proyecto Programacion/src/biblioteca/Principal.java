package biblioteca;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Principal extends JFrame {
	private SocioHibernateDAO shd;
	protected static Socio conectado;
	protected static EntityManager em;
	private JPanel contentPane;
	private JTextField textUser;
	private JLabel aviso;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracle");
		System.out.println( emf.getProperties() );
	    em = emf.createEntityManager();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Registrate");
		btnNewButton.setMnemonic('r');
		btnNewButton.setToolTipText("Pulse aqui para nuevo registro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.this.setVisible(false);
				Registro reg = new Registro();
				reg.setVisible(true);
			}
		});
		btnNewButton.setBounds(45, 137, 103, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setMnemonic('l');
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				buscaUsuario(textUser.getText(), passwordField.getText());
				
			}
		});
		btnNewButton_1.setBounds(257, 137, 103, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(45, 67, 73, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(45, 95, 73, 14);
		contentPane.add(lblNewLabel_1);
		
		textUser = new JTextField();
		textUser.setBounds(158, 67, 86, 20);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenido al prestamo automatico de libros");
		lblNewLabel_2.setBounds(71, 11, 289, 20);
		contentPane.add(lblNewLabel_2);
		
		aviso = new JLabel("Usuario o password erroneos");
		aviso.setForeground(Color.RED);
		aviso.setBounds(104, 33, 211, 23);
		contentPane.add(aviso);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 92, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton_2 = new JButton("Cerrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setMnemonic('c');
		btnNewButton_2.setToolTipText("Cierre de aplicacion");
		btnNewButton_2.setBounds(158, 137, 89, 23);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel_3 = new JLabel("Este usuario esta en proceso de baja");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(104, 171, 305, 15);
		contentPane.add(lblNewLabel_3);
		aviso.setVisible(false);
		lblNewLabel_3.setVisible(false);
	}
	
	
	@SuppressWarnings("static-access")
	private void buscaUsuario(String text, String text2) {
		lblNewLabel_3.setVisible(false);
		shd = new SocioHibernateDAO(em);
		if (text2.length() == 0 || text.length() == 0){
			aviso.setVisible(true);
		}
		else{
			Socio s = shd.findByUser(text, text2);
			if ( s == null){
				textUser.setText("");
				passwordField.setText("");
				aviso.setVisible(true);
				}
			else if (s.getBaja()){
				lblNewLabel_3.setVisible(true);
			}
			else if (s.getNombre().equals("Administrador")){
				Principal.this.setVisible(false);
				this.conectado = s;
				OpcionAdmin a = new OpcionAdmin();
				a.setVisible(true);
				Principal.this.dispose();
			}
			else{
				Principal.this.setVisible(false);
				this.conectado = s;
				ListaLibros LB = new ListaLibros();
				LB.setVisible(true);
				Principal.this.dispose();

			}
		}
	}
}
