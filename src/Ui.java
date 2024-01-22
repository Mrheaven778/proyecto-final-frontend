import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class Ui {

	private static ArrayList<Campeon> campeones;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        String response = Api.peticionApi();
        if (response != null) {
            System.out.println("La api no responde ");
        }
        System.out.println(response);
        Json.guardadDatos(response);
         campeones =  Json.getChapeones();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ui window = new Ui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(28,25,23));
		frame.setResizable(false);
		frame.setBounds(100, 100, 906, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Proyecto Final Sergio Abrodes");
		lblTitulo.setForeground(new Color(10,189,16));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setBounds(321, 46, 305, 62);
		frame.getContentPane().add(lblTitulo);
		
		JButton btnNewXml = new JButton("Crear XML");
		btnNewXml.setForeground(new Color(255, 255, 255));
		btnNewXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewXml.setBorderPainted(false);
		btnNewXml.setBackground(new Color(14,89,19));
		btnNewXml.setBounds(31, 537, 89, 23);
		frame.getContentPane().add(btnNewXml);
		
		JButton btnCrearJson = new JButton("Crear JSON");
		btnCrearJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearJson.setForeground(Color.WHITE);
		btnCrearJson.setBorderPainted(false);
		btnCrearJson.setBackground(new Color(14, 89, 19));
		btnCrearJson.setBounds(418, 537, 89, 23);
		frame.getContentPane().add(btnCrearJson);
		
		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.setForeground(new Color(255, 255, 255));
		btnVerDatos.setBorderPainted(false);
		btnVerDatos.setBackground(new Color(14, 89, 19));
		btnVerDatos.setBounds(700, 85, 105, 23);
		frame.getContentPane().add(btnVerDatos);
		
		
		JLabel lblNombreCampeones = new JLabel("Nombre");
		lblNombreCampeones.setFont(new Font("Cascadia Code", Font.BOLD, 20));
		lblNombreCampeones.setForeground(new Color(255, 255, 255));
		lblNombreCampeones.setBounds(195, 107, 76, 14);
		lblNombreCampeones.setVisible(false);
		frame.getContentPane().add(lblNombreCampeones);
		
		JLabel lblViewChamp = new JLabel("New label");
		lblViewChamp.setVisible(false);
		lblViewChamp.setBounds(195, 132, 76, 243);
		frame.getContentPane().add(lblViewChamp);
		String f = "";
		for (Campeon campeon : campeones) {
			f = campeon.getName()+ "\n" + f ;
		}
		lblViewChamp.setText(f);
	
		btnVerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNombreCampeones.setVisible(true);
				lblViewChamp.setVisible(true);
				
			}
		});
	
		
	}
}
