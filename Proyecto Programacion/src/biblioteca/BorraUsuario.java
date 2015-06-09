package biblioteca;

import java.awt.EventQueue;

import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class BorraUsuario extends JFrame {

	private JPanel contentPane;
	private SocioHibernateDAO shd;
	private PrestamoHibernateDAO phd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorraUsuario frame = new BorraUsuario();
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
	@SuppressWarnings({"rawtypes" })
	public BorraUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Volver a menu anterior");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionAdmin a = new OpcionAdmin();
				BorraUsuario.this.setVisible(false);
				a.setVisible(true);
				BorraUsuario.this.dispose();
			}
		});
		btnNewButton_1.setBounds(97, 143, 202, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 11, 139, 105);
		contentPane.add(scrollPane);
		
		final JList list = new JList();
		scrollPane.setViewportView(list);
		shd = new SocioHibernateDAO (Principal.em);
		rellenaLista(list);
		
		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socio s = (Socio) list.getSelectedValue();
				if (s != null){
				phd = new PrestamoHibernateDAO (Principal.em);
				List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
				listaPrestamos = phd.findByNumSocio(s.getNumSocio());
				EntityTransaction tx = Principal.em.getTransaction();
				tx.begin();
				for (Prestamo p : listaPrestamos){
					Principal.em.remove(p);
				}
				Principal.em.remove(s);
				tx.commit();
				}
				rellenaLista(list);
			}
		});



		btnNewButton_2.setToolTipText("Click para borrar el usuario seleccionado");
		btnNewButton_2.setMnemonic('b');
		btnNewButton_2.setBounds(48, 51, 121, 23);
		contentPane.add(btnNewButton_2);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rellenaLista(final JList list) {
		List<Socio> listaSocios = new ArrayList<Socio>();
		listaSocios = shd.findBajas();
		list.setModel(miModeloLista(listaSocios));
	}
	
	private <T> DefaultListModel<T> miModeloLista ( Collection<T> col ){
		DefaultListModel<T> ret = new DefaultListModel<T>();
		if (col != null){
		for ( T t : col ){
			ret.addElement(t);
		}
		return ret;
		}
		else return ret;
	}
}
