package biblioteca;

import java.awt.EventQueue;

import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class BorraLibro extends JFrame {
	private LibroHibernateDAO lhd;
	private JPanel contentPane;
	private JTextField textField;
	private PrestamoHibernateDAO phd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorraLibro frame = new BorraLibro();
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
	@SuppressWarnings({ "rawtypes" })
	public BorraLibro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Titulo del libro");
		lblNewLabel.setBounds(29, 49, 179, 26);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 83, 188, 118);
		contentPane.add(scrollPane);

		final JList list = new JList();
		scrollPane.setViewportView(list);


		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				rellenaLista(list);

			}
		});
		textField.setBounds(218, 52, 188, 20);
		contentPane.add(textField);
		textField.setColumns(10);


		JLabel lblSeleccioneIdDe = new JLabel("Seleccione id de la lista");
		lblSeleccioneIdDe.setBounds(29, 135, 179, 26);
		contentPane.add(lblSeleccioneIdDe);

		JButton btnNewButton = new JButton("Borrar libro");
		btnNewButton.setMnemonic('b');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = (int) list.getSelectedValue();
				lhd = new LibroHibernateDAO(Principal.em);
				Libro l = lhd.findByPrimaryKey(id);
				phd = new PrestamoHibernateDAO(Principal.em);
				List<Prestamo> prestamosLibro = new ArrayList<Prestamo>();
				EntityTransaction tx = Principal.em.getTransaction();
				tx.begin();
				prestamosLibro = phd.findByNumSerie(id);
				for (Prestamo p : prestamosLibro){
					Principal.em.remove(p);
				}
				Principal.em.remove(l);
				tx.commit();
				rellenaLista(list);
			}
		});
		btnNewButton.setBounds(29, 209, 157, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setMnemonic('v');
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionAdmin a = new OpcionAdmin();
				BorraLibro.this.setVisible(false);
				a.setVisible(true);
				BorraLibro.this.dispose();
			}
		});
		btnNewButton_1.setBounds(29, 239, 157, 23);
		contentPane.add(btnNewButton_1);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rellenaLista(final JList list) {
		if (!textField.getText().equals("")){
			lhd = new LibroHibernateDAO(Principal.em);
			String autor = textField.getText();
			List<Libro> listaTotal = new ArrayList<Libro>();
			listaTotal = lhd.findByTittle(autor);
			DefaultListModel miModelo = new DefaultListModel();
			for (Libro l : listaTotal){
				miModelo.addElement(l.getNumSerie());
			}
			list.setModel(miModelo);
		}
	}

}
