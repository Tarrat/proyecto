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
public class OpcionAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcionAdmin frame = new OpcionAdmin();
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
	public OpcionAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido Administrador");
		lblNewLabel.setBounds(144, 39, 280, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Borrar usuario");
		btnNewButton.setMnemonic('b');
		btnNewButton.setToolTipText("Apertura de ventana de borrado de usuarios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorraUsuario b = new BorraUsuario();
				b.setVisible(true);
				OpcionAdmin.this.setVisible(false);
				OpcionAdmin.this.dispose();
			}
		});
		btnNewButton.setBounds(107, 88, 196, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Borrar libro");
		btnNewButton_1.setMnemonic('l');
		btnNewButton_1.setToolTipText("Apertura de ventana  de borrado de libros");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorraLibro b = new BorraLibro();
				b.setVisible(true);
				OpcionAdmin.this.setVisible(false);
				OpcionAdmin.this.dispose();
			}
		});
		btnNewButton_1.setBounds(107, 122, 196, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Comprobar prestamos");
		btnNewButton_2.setMnemonic('c');
		btnNewButton_2.setToolTipText("Apertura de ventana de comprobacion de prestamos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPrestamo l = new ListaPrestamo();
				l.setVisible(true);
				OpcionAdmin.this.setVisible(false);
				OpcionAdmin.this.dispose();
			}
		});
		btnNewButton_2.setBounds(107, 159, 196, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Desconectar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionAdmin.this.setVisible(false);
				Principal p = new Principal();
				p.setVisible(true);
				OpcionAdmin.this.dispose();
			}
		});
		btnNewButton_3.setToolTipText("Acceso directo a ventana de login");
		btnNewButton_3.setMnemonic('d');
		btnNewButton_3.setBounds(103, 193, 200, 23);
		contentPane.add(btnNewButton_3);
	}

}
