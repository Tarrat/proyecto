package biblioteca;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Sipnosis extends JFrame {
	private static Libro l;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sipnosis frame = new Sipnosis(l);
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
	public Sipnosis(Libro l) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 69, 414, 113);
		contentPane.add(lblNewLabel);
		String sipnosis = l.getSipnosis();
		String titulo = l.getTitulo();
		lblNewLabel.setText(sipnosis);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(162, 11, 158, 42);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setText(titulo);
		
		JButton btnNewButton = new JButton("Lista");
		btnNewButton.setMnemonic('l');
		btnNewButton.setToolTipText("Volver a lista de libros");
		btnNewButton.setBounds(34, 211, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCerrar = new JButton("Login");
		btnCerrar.setToolTipText("Vuelva a la pantalla de login");
		btnCerrar.setMnemonic('o');
		btnCerrar.setBounds(294, 211, 89, 23);
		contentPane.add(btnCerrar);
	}

}
