package ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import funcion.Funcion;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.imageio.ImageIO;


import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;



import javax.swing.Action;

import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import java.io.FileNotFoundException;

import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class TestingTool extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<Funcion> funciones = new ArrayList<Funcion>();
	private List listMetodos = new List();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblComentarCodigo = new JLabel();
	private JLabel lblModularizar = new JLabel();
	private JLabel lblTodoBien = new JLabel();
	private final Action action = new SwingAction();;
	String ruta;
	private List codigo = new List();
	ArrayList<int[]> matriz= new ArrayList<int[]>();
	private boolean resultados = false;
	
	
	String lim = "hola";
	
	Color colorfondodefault;
    Highlighter hilit;

    JTextArea textArea = new JTextArea();
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;

    Highlighter.HighlightPainter painter, painterFor, painterWhile;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestingTool frame = new TestingTool();
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
	public TestingTool() {
		

		setTitle("Testing Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(0, 0, 728, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(122, 22, 616, 20);

		textField = new JTextField();
		textField.setBounds(122, 573, 558, 20);

		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("M�todos de la clase:");

		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));

		lblNewLabel.setBounds(20, 338, 129, 34);
		
		contentPane.add(lblNewLabel);
		listMetodos.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				textArea.removeAll();
				matriz.clear();
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				mostrarMetodo();
			
			}

		});
		listMetodos.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarMetodo();
				
			}
			
		});
		listMetodos.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				mostrarMetodo();
				
			}
		});
		
		listMetodos.setBounds(20, 378, 660, 188);
		contentPane.add(listMetodos);
		
		hilit = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);

        painterFor = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        painterWhile = new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
       
	
		

		JLabel lblNewLabel_1 = new JLabel("Estado del Metodo");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 11, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lineas totales");
		lblNewLabel_2.setBounds(144, 14, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Comentarios");
		lblNewLabel_3.setBounds(317, 14, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("% de comentarios");
		lblNewLabel_4.setBounds(484, 14, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Complejidad Ciclom�tica");
		lblNewLabel_5.setBounds(154, 39, 114, 14);

		contentPane.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 11, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(388, 11, 86, 20);

		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(583, 11, 86, 20);

		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(278, 36, 86, 20);

		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		
		
		
		JButton btnNewButton = new JButton("Destino");
		btnNewButton.setBounds(20, 572, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					elegirArchivo();
					textField.setText(ruta);
					
						
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		contentPane.add(btnNewButton);
		
		JLabel lblCodigoFuente = new JLabel("C�digo del M�todo");
		lblCodigoFuente.setFont(new Font("Arial", Font.BOLD, 12));
		lblCodigoFuente.setForeground(new Color(0, 0, 0));
		lblCodigoFuente.setBounds(20, 36, 114, 34);
		contentPane.add(lblCodigoFuente);
		
		JButton btnNewButton_1 = new JButton("An\u00E1lisis");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnNewButton_1.setAction(action);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularValoresAnalisis();
			}
		});
		btnNewButton_1.setBounds(551, 310, 129, 34);
		contentPane.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 60, 536, 239);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
				textArea.setForeground(Color.BLACK);
				textArea.setEditable(false);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(63, 148, 71, 20);

		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(63, 179, 71, 20);
		contentPane.add(textField_6);
		
		JLabel lblFanIn = new JLabel("FanIn");
		lblFanIn.setBounds(10, 151, 48, 14);
		contentPane.add(lblFanIn);
		
		JLabel lblFanOut = new JLabel("FanOut");
		lblFanOut.setBounds(10, 179, 48, 14);
		contentPane.add(lblFanOut);
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(10, 123, 67, 14);
		contentPane.add(lblLongitud);
		
		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(10, 98, 67, 14);
		contentPane.add(lblVolumen);
		
		JLabel lblEzfuerzo = new JLabel("Esfuerzo");
		lblEzfuerzo.setBounds(10, 73, 67, 14);
		contentPane.add(lblEzfuerzo);
		
		textField_7 = new JTextField();
		textField_7.setBounds(63, 123, 71, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(63, 98, 71, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(63, 70, 71, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblReporteDeAnalisis = new JLabel("Reporte");
		lblReporteDeAnalisis.setFont(new Font("Arial", Font.BOLD, 12));
		lblReporteDeAnalisis.setForeground(new Color(0, 0, 0));
		lblReporteDeAnalisis.setBounds(144, 310, 56, 14);
		contentPane.add(lblReporteDeAnalisis);
		contentPane.add(lblComentarCodigo);
		lblComentarCodigo.setVisible(false);
		contentPane.add(lblModularizar);
		lblModularizar.setVisible(false);
		contentPane.add(lblTodoBien);
		lblTodoBien.setVisible(false);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Correr Analisis");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private void mostrarMetodo(){ 
		//Si se corrio un analisis se limpian los campos al cambiar de metodo
		if(resultados)
			limpiarRegistro();
		
		String linea = "";
		String subLinea="";
		int indeX = 0;
		int indeY = 0;

		textArea.removeAll();
		matriz.clear();
		
		try {
		for(int i = 0; i< funciones.get(listMetodos.getSelectedIndex()).getCodigo().size();i++){
			indeX = linea.length();
			indeY = indeX+funciones.get(listMetodos.getSelectedIndex()).getCodigo().get(i).length();
			subLinea = funciones.get(listMetodos.getSelectedIndex()).getCodigo().get(i);
			linea = linea+"\n"+funciones.get(listMetodos.getSelectedIndex()).getCodigo().get(i);


			if(subLinea.contains("for(")){
				int a[] = {indeX,indeY};
				matriz.add(a);					
			}
			
			if(subLinea.contains("if(")) {
				int a[] = {indeX, indeY};
				matriz.add(a);
			}
			
			if(subLinea.contains("while(")) {
				int a[] = {indeX, indeY};
				matriz.add(a);
			}
		}
		} catch (ArrayIndexOutOfBoundsException e) {
			indeX= indeY = 0;
			textArea.removeAll();
			matriz.clear();
			textArea.requestFocus();
			return;
		}
		textArea.setText(linea);
		textArea.setHighlighter(hilit);
		for(int i = 0; i<matriz.size();i++){
			 try {

					hilit.addHighlight(matriz.get(i)[0], matriz.get(i)[1], painterFor);
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		indeX= indeY = 0;
		textArea.requestFocus();
									
		
	}
	
	private void limpiarRegistro() {
		
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
		lblComentarCodigo.setVisible(false);
		lblModularizar.setVisible(false);
		lblTodoBien.setVisible(false);
		resultados=false;
	}
	
	/*
	 * LOGICA DE PANTALLA
	 */
	private void elegirArchivo() throws FileNotFoundException {
		//Scanner entrada = null;
		listMetodos.removeAll();
		textArea.setText("");

		JFileChooser selectorArchivos = new JFileChooser();
	
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos Java", "java");
		selectorArchivos.setFileFilter(filtro);
		selectorArchivos.showOpenDialog(selectorArchivos);
		
		ruta = selectorArchivos.getSelectedFile().getAbsolutePath();
		buscarMetodos(ruta);
	}
	
	private void buscarMetodos(String ruta) throws FileNotFoundException {
		
		funciones = Funcion.getFuncionesArchivo(ruta);
		
		for (int j = 0; j < funciones.size(); j++) {
			listMetodos.add(funciones.get(j).namefuncion);
		}
		
	}
	
	private void calcularValoresAnalisis(){
		try{
			int index = listMetodos.getSelectedIndex();
			
			int lineasCodigo = funciones.get(index).getLlaveCierreFun()-funciones.get(index).getNumLiniaIni()+1;
			int lineasComentadas =  funciones.get(index).getCantlineaComentario();
			
			//int porcentajeCodigoComentado = (funciones.get(index).getCantlineaComentario()* 100)/lineasCodigo;
			int porcentajeCodigoComentado = (lineasComentadas* 100)/(lineasCodigo - lineasComentadas);
			
			
			
			resultados = true;
			textField_1.setText(lineasCodigo - lineasComentadas+"");
			textField_2.setText(funciones.get(index).getCantlineaComentario()+"");
			textField_3.setText(porcentajeCodigoComentado+" %");
			textField_4.setText(funciones.get(index).getComplejidadCiclomatica()+"");
			textField_5.setText(funciones.get(index).getFanIn()+"");
			textField_6.setText(funciones.get(index).getFanOut()+"");
			textField_7.setText(funciones.get(index).getLongitud()+"");
			textField_8.setText(String.format("%.2f", funciones.get(index).getVolumen()));
			textField_9.setText(String.format("%.2f" , funciones.get(index).getEsfuerzo()));
			
			if(porcentajeCodigoComentado < 50) {
				lblComentarCodigo.setText("Se recomienda comentar m\u00E1s el c\u00F3digo");
				lblComentarCodigo.setBackground(new Color(128, 128, 128));
				lblComentarCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblComentarCodigo.setBounds(200, 310, 254, 14);
				lblComentarCodigo.setVisible(true);
				
			}
			else {
				lblComentarCodigo.setVisible(false);
			}
				
			
			if(Integer.valueOf(textField_4.getText()) > 10 ){
				lblModularizar.setText("Hay que modularizar el metodo para bajar su complejidad ciclomatica");
				lblModularizar.setBackground(new Color(128, 128, 128));
				lblModularizar.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblModularizar.setBounds(200, 310, 400, 14);
				lblModularizar.setVisible(true);
			}
			else {
				lblModularizar.setVisible(false);
			}
				
			
			if(lblComentarCodigo.isVisible() == false && lblModularizar.isVisible() == false) {
				lblTodoBien.setText("El metodo cumple con las metricas de mantenibilidad");
				lblTodoBien.setBackground(new Color(128, 128, 128));
				lblTodoBien.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTodoBien.setBounds(200, 310, 400, 14);
				lblTodoBien.setVisible(true);
			}
			
		}catch(Exception e) {
			resultados = false;
			return;
		}
		

		}
}