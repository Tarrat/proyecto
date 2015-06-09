package biblioteca;

import java.awt.EventQueue;

import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class Devoluciones extends JFrame {

	private JPanel contentPane;
	private PrestamoHibernateDAO phd;
	private LibroHibernateDAO lhd;
	private java.util.Date fecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Devoluciones frame = new Devoluciones();
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
	public Devoluciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista de libros prestados para este usuario");
		lblNewLabel.setBounds(58, 11, 292, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 36, 276, 199);
		contentPane.add(scrollPane);
		
		final JList<Prestamo> list = new JList<Prestamo>();
		scrollPane.setViewportView(list);
		phd = new PrestamoHibernateDAO(Principal.em);
		List<Prestamo> listaTotal = new ArrayList<Prestamo>();
		Calendar c = Calendar.getInstance();
		fecha = c.getTime();
		listaTotal = phd.findByDate(fecha, Principal.conectado);
		list.setModel(miModeloLista(listaTotal));
		
		JButton btnNewButton = new JButton("Devolver");
		btnNewButton.setMnemonic('d');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prestamo p = list.getSelectedValue();
				if (p != null){
				EntityTransaction tx = Principal.em.getTransaction();
				tx.begin();
					int numSerie = p.getNumSerie();
					lhd = new LibroHibernateDAO(Principal.em);
					Libro l = lhd.findByPrimaryKey(numSerie);
					l.setPrestado(false);
					p.setFechaEntrega(fecha);
					Principal.em.persist(p);
					Principal.em.persist(l);
					tx.commit();
					List<Prestamo> listaTotal = new ArrayList<Prestamo>();
					listaTotal = phd.findByDate(fecha, Principal.conectado);
					list.setModel(miModeloLista(listaTotal));
				}
			}
		});
		btnNewButton.setToolTipText("Seleccione un libro de la lista para devolver");
		btnNewButton.setBounds(10, 75, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lista");
		btnNewButton_1.setToolTipText("Vuelva a la lista de libros disponibles");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaLibros l = new ListaLibros();
				Devoluciones.this.setVisible(false);
				l.setVisible(true);
				Devoluciones.this.dispose();
			}
		});
		btnNewButton_1.setMnemonic('l');
		btnNewButton_1.setBounds(10, 146, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	private <T> DefaultListModel<T> miModeloLista ( Collection<T> col ){
		DefaultListModel<T> ret = new DefaultListModel<T>();
		for ( T t : col ){
			ret.addElement(t);
		}
		return ret;
	}
}
