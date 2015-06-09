package biblioteca;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ListaPrestamo extends JFrame {

	private JPanel contentPane;
	private PrestamoHibernateDAO phd;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPrestamo frame = new ListaPrestamo();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListaPrestamo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista completa de prestamos");
		lblNewLabel.setBounds(42, 30, 225, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 55, 382, 196);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		phd = new PrestamoHibernateDAO(Principal.em);
		List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
		listaPrestamos = phd.findAll();
		DefaultListModel miModelo = new DefaultListModel();
		for (Prestamo p : listaPrestamos){
			miModelo.addElement(p.getNumPrestamo() + "-" + p.socio.getNombre());
		}
		list.setModel(miModelo);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPrestamo.this.setVisible(false);
				OpcionAdmin a = new OpcionAdmin();
				a.setVisible(true);
				ListaPrestamo.this.dispose();
			}
		});
		btnNewButton.setMnemonic('v');
		btnNewButton.setToolTipText("Vuelve al selector de opciones del administrador");
		btnNewButton.setBounds(293, 26, 131, 23);
		contentPane.add(btnNewButton);
		
	}

}
