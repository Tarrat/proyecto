package biblioteca;

import java.awt.EventQueue;

import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class ListaLibros extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private LibroHibernateDAO lhd;
	private PrestamoHibernateDAO phd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaLibros frame = new ListaLibros();
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
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public ListaLibros() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Lista de libros disponibles");
		lblNewLabel.setBounds(203, 11, 214, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 52, 277, 289);
		contentPane.add(scrollPane);

		final JList list = new JList();
		scrollPane.setViewportView(list);
		rellenaLista(list);

		JButton btnNewButton = new JButton("Por titulo");
		btnNewButton.setMnemonic('t');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titulo = textField.getText();
				if (titulo!=""){
					lhd = new LibroHibernateDAO(Principal.em);
					List<Libro> listaTitulo = new ArrayList<Libro>();
					listaTitulo = lhd.findByTittle(titulo);
					list.setModel(miModeloLista(listaTitulo));
				}
			}
		});
		btnNewButton.setBounds(10, 85, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Busqueda");
		lblNewLabel_1.setBounds(10, 53, 89, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_1.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(camposVacios()){
					rellenaLista(list);
				}
			}
		});
		textField.setToolTipText("Escriba aqui el libro que desea encontrar");
		textField.setBounds(144, 86, 158, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Por autor");
		btnNewButton_1.setMnemonic('a');
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String autor = textField_1.getText();
				if (autor!= ""){
					lhd = new LibroHibernateDAO(Principal.em);
					List<Libro> listaAutor = new ArrayList<Libro>();
					listaAutor = lhd.findByAuthor(autor);
					list.setModel(miModeloLista(listaAutor));
				}
			}
		});
		btnNewButton_1.setBounds(10, 143, 89, 23);
		contentPane.add(btnNewButton_1);

		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(camposVacios()){
					rellenaLista(list);
				}
			}
		});
		textField_1.setToolTipText("Escriba aqui el autor que desea encontrar");
		textField_1.setBounds(144, 144, 158, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_2 = new JButton("Acceso directo a devoluciones");
		btnNewButton_2.setMnemonic('v');
		btnNewButton_2.setToolTipText("Pulse aqui para acceder a la pantalla personal de devoluciones");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaLibros.this.setVisible(false);
				Devoluciones dev = new Devoluciones();
				dev.setVisible(true);
				ListaLibros.this.dispose();
			}
		});
		btnNewButton_2.setBounds(45, 300, 230, 23);
		contentPane.add(btnNewButton_2);

		final JLabel lblNoPuedeTomar = new JLabel("No puede tomar prestado ningun libro");
		lblNoPuedeTomar.setForeground(Color.RED);
		lblNoPuedeTomar.setBounds(53, 193, 222, 14);
		contentPane.add(lblNoPuedeTomar);
		lblNoPuedeTomar.setVisible(false);

		final JButton btnNewButton_3 = new JButton("Tomar prestado");
		btnNewButton_3.setMnemonic('p');
		btnNewButton_3.setToolTipText("Pulse para tomar prestado el libro seleccionado");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puedeTomarPrestado(lblNoPuedeTomar, btnNewButton_3);
				//Calendar c = Calendar.getInstance();
				//c.add(Calendar.DATE, -30);
				java.util.Date fecha =calculaFecha();
				Libro l = (Libro) list.getSelectedValue();
				if (l!=null & btnNewButton_3.isEnabled() == true){
					EntityTransaction tx = Principal.em.getTransaction();
					tx.begin();
					Prestamo p = new Prestamo(fecha);
					p.setSocio(Principal.conectado);
					l.setPrestado(true);
					p.setLibro(l);
					Principal.em.persist(p);
					tx.commit();
					List<Libro> listaTotal = new ArrayList<Libro>();
					listaTotal = lhd.findNotBorrowed();
					list.setModel(miModeloLista(listaTotal));
				}
				puedeTomarPrestado(lblNoPuedeTomar, btnNewButton_3);
			}

		});
		btnNewButton_3.setBounds(45, 231, 230, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Donar Libro");
		btnNewButton_4.setMnemonic('d');
		btnNewButton_4.setToolTipText("Pulse para donar un libro de su propiedad");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaLibros.this.setVisible(false);
				Donar d = new Donar();
				d.setVisible(true);
				ListaLibros.this.dispose();
			}
		});
		btnNewButton_4.setBounds(45, 266, 230, 23);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Sipnosis");
		btnNewButton_5.setMnemonic('s');
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Libro l = (Libro) list.getSelectedValue();
				if(l!= null){
					String titulo = l.getTitulo();
					String sipnosis = l.getSipnosis();
					JOptionPane.showMessageDialog(ListaLibros.this,
							sipnosis,
							titulo,
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton_5.setToolTipText("Presione aqui para leer la sipnosis del libro seleccionado");
		btnNewButton_5.setBounds(144, 36, 158, 23);
		contentPane.add(btnNewButton_5);

		puedeTomarPrestado(lblNoPuedeTomar, btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("Desconectar");
		btnNewButton_6.setToolTipText("Acceso directo a la ventana de logeo");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal p = new Principal();
				p.setVisible(true);
				ListaLibros.this.setVisible(false);
				ListaLibros.this.dispose();
			}
		});
		btnNewButton_6.setMnemonic('c');
		btnNewButton_6.setBounds(45, 334, 230, 23);
		contentPane.add(btnNewButton_6);
		
		final JLabel lblNoPuedeDarse = new JLabel("No puede darse de baja, tiene prestamos pendientes");
		lblNoPuedeDarse.setForeground(Color.RED);
		lblNoPuedeDarse.setBounds(329, 352, 332, 34);
		contentPane.add(lblNoPuedeDarse);
		lblNoPuedeDarse.setVisible(false);
		
		final JButton btnNewButton_7 = new JButton("Darme de baja");
		btnNewButton_7.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				phd = new PrestamoHibernateDAO(Principal.em);
				List<Prestamo> prestamos = new ArrayList<Prestamo>();
				Calendar c = Calendar.getInstance();
				prestamos = phd.findByDate (c.getTime(), Principal.conectado);
				if (prestamos.isEmpty() && btnNewButton_7.isEnabled()){
					EntityTransaction tx = Principal.em.getTransaction();
					tx.begin();
					Principal.conectado.setBaja(true);
					Principal.em.persist(Principal.conectado);
					tx.commit();
					Principal p = new Principal();
					p.setVisible(true);
					ListaLibros.this.setVisible(false);
					ListaLibros.this.dispose();
				}
				else{
					btnNewButton_7.enable(false);
					lblNoPuedeDarse.setVisible(true);
				}
			}
		});
		btnNewButton_7.setToolTipText("Cuando pulse este boton no podra volver a inciar sesion con este usuario");
		btnNewButton_7.setMnemonic('b');
		btnNewButton_7.setBounds(45, 368, 230, 23);
		contentPane.add(btnNewButton_7);
		
		

	}

	@SuppressWarnings("deprecation")
	private void puedeTomarPrestado(final JLabel lblNoPuedeTomar,
			final JButton btnNewButton_3) {
		java.util.Date fecha = calculaFecha();
		phd = new PrestamoHibernateDAO(Principal.em);
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		prestamos = phd.findByDate (fecha, Principal.conectado);
		if (!prestamos.isEmpty()){
			int contador = 0;
			for (Prestamo p : prestamos){
				if (p.getFechaEntrega()== null){
					contador += 1;

					if (contador == 5){
						lblNoPuedeTomar.setVisible(true);
						btnNewButton_3.enable(false);
					}
					else{
						btnNewButton_3.enable(true);
						lblNoPuedeTomar.setVisible(false);
					}
				}
				else{
					java.util.Date fechaEntre = p.getFechaEntrega();
					java.util.Date fechaPrestamo = p.getFecha();
					long diferenciaDias = (fechaEntre.getTime() - fechaPrestamo.getTime())/(1000 * 60 * 60 * 24);
					long diferenciaHoyEntrega = (fecha.getTime() - fechaEntre.getTime())/(1000 * 60 * 60 * 24);
					if (diferenciaDias >= 10 && diferenciaHoyEntrega <= 5){
						lblNoPuedeTomar.setVisible(true);
						btnNewButton_3.enable(false);
					}
					else{
						btnNewButton_3.enable(true);
						lblNoPuedeTomar.setVisible(false);
					}
				}
			}
		}
	}


	@SuppressWarnings("unchecked")
	private void rellenaLista(@SuppressWarnings("rawtypes") final JList list) {
		lhd = new LibroHibernateDAO(Principal.em);
		List<Libro> listaTotal = new ArrayList<Libro>();
		listaTotal = lhd.findNotBorrowed();
		list.setModel(miModeloLista(listaTotal));
	}

	private java.util.Date calculaFecha() {
		Calendar c = Calendar.getInstance();
		String dia = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha = null;
		try {
			fecha = sdf.parse(dia);
		} catch (ParseException e1) {
		}
		return fecha;
	}

	private <T> DefaultListModel<T> miModeloLista ( Collection<T> col ){
		DefaultListModel<T> ret = new DefaultListModel<T>();
		for ( T t : col ){
			ret.addElement(t);
		}
		return ret;
	}

	private boolean camposVacios() {
		if (textField.getText().equals("") && textField_1.getText().equals("")){
			return true;
		}
		return false;
	}
}
