package herramienta;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Analizador {

	public static int CANTIDAD_LINEAS = 0;
	private static final String[] OPERADORES = { "if(", "for(", "while(", "break", "then", "=", "*", "+", "-", "||", "&&", "/",
			"{", "}", ">", "<", "<=", ">=", "%", "do", "int", "double", "this", "String", "float",
			"System.out.println", "return", "false", "true", "null", "boolean", "final", "else" };

	public String nombreMetodo;
	public String metodoAbreviado;
	private int lineaInicial;
	private int cantIf;
	private int cantFor;
	private int cantWhile;
	private int cantLlaves;
	private int cantComentario;
	private int llaveAperturaFun;
	private int llaveCierreFun;
	private int fanIn;
	private int fanOut;
	private int N1;
	private int N2;
	private int n1;
	private int n2;
	private ArrayList<String> codigo;
	private ArrayList<String> listaOperadores = new ArrayList<String>();
	private ArrayList<String> listaOperandos = new ArrayList<String>();
	

	public Analizador() {
		this.nombreMetodo = "";
		this.lineaInicial = 0;
		this.cantIf = 0;
		this.cantFor = 0;
		this.cantWhile = 0;
		this.cantComentario = 0;
		this.llaveAperturaFun = 0;
		this.llaveCierreFun = 0;
		this.cantLlaves = 0;
		this.fanIn = 0;
		this.fanOut = 0;
		this.N1 = 0;
		this.N2 = 0;
		this.n1 = 1;
		this.n2 = 1;
		this.codigo = new ArrayList<String>();
	}

	private static String cargarMetodoAbreviado(String nombre) {
		String[] aux = null;
		String aux2 = "";
		String shortMethodName = "";
		aux = nombre.split(" ");
		for (int i = 0; i < aux.length; i++) {
			if (aux[i].contains("("))
				aux2 = aux[i];
		}

		aux2 = aux2.replace("(", " ");
		aux = aux2.split(" ");
		shortMethodName = aux[0];
		return shortMethodName;

	}



	public static ArrayList<Analizador> getMetodosArchivo(String archivo) throws FileNotFoundException {

		ArrayList<Analizador> listaFun = new ArrayList<Analizador>();
		Analizador clase = null;
		Scanner sc = new Scanner(new File(archivo));
		String linea;
		boolean cargandoFuncion = false;

		while (sc.hasNextLine()) {
			CANTIDAD_LINEAS++;
			linea = sc.nextLine();

			if (esFuncion(linea)) {
				clase = new Analizador();
				clase.codigo.add(linea);
				clase.nombreMetodo = linea;
				clase.nombreMetodo = clase.nombreMetodo.replace("{", "");
				clase.nombreMetodo = clase.nombreMetodo.trim();
				clase.metodoAbreviado = cargarMetodoAbreviado(clase.nombreMetodo);

				cargandoFuncion = true;
				clase.lineaInicial = CANTIDAD_LINEAS;
				if (linea.contains("{")) {
					clase.llaveAperturaFun = CANTIDAD_LINEAS;
				}

			} else {
				if (cargandoFuncion) {
					clase.codigo.add(linea);
					if (linea.contains("if("))
						clase.cantIf = clase.cantIf + ajusteCondiciones(linea);
					if (linea.contains("for("))
						clase.cantFor++;
					if (linea.contains("while("))
						clase.cantWhile++;
					if (linea.contains("/*") || linea.contains("*") || linea.contains("//"))
						clase.cantComentario++;
				}

			}

			if (linea.contains("{") && cargandoFuncion) {
				if (clase.cantLlaves == 0)
					clase.llaveAperturaFun = CANTIDAD_LINEAS;
				clase.cantLlaves++;

			}
			if (linea.contains("}") && cargandoFuncion) {
				clase.cantLlaves--;
				if (cargandoFuncion && clase.cantLlaves == 0) {
					cargandoFuncion = false;
					listaFun.add(clase);
					clase.llaveCierreFun = CANTIDAD_LINEAS;
				}
			}

		}
		sc.close();
		cargarFanInOut(listaFun);
		return listaFun;

	}

	public static int ajusteCondiciones(String cadena) {
		cadena = cadena.replace("&&", ",");
		cadena = cadena.replace("||", ",");
		String auxiliar[] = cadena.split(",");

		return auxiliar.length;
	}

	public static int contadorDeAparciciones(String lineaCodigo, String nombrefun) {
		int contador = 0;
		while (lineaCodigo.indexOf(nombrefun) > -1) {
			lineaCodigo = lineaCodigo.substring(lineaCodigo.indexOf(nombrefun) + nombrefun.length(),
					lineaCodigo.length());
			contador++;
		}
		return contador;
	}

	private static String concatenarCodigo(ArrayList<String> codigo) {
		String lineaDeCodigo = "";
		for (int i = 1; i < codigo.size(); i++)
			lineaDeCodigo = lineaDeCodigo + codigo.get(i);
		return lineaDeCodigo;
	}

	public static void cargarFanInOut(ArrayList<Analizador> list) {

		String codigoFuncion = "";
		int aux;

		for (int i = 0; i < list.size(); i++) {
			codigoFuncion = concatenarCodigo(list.get(i).codigo);

			for (int j = 0; j < list.size(); j++) {

				if (i != j) {
					aux = contadorDeAparciciones(codigoFuncion, list.get(j).metodoAbreviado + "(");
					if (aux != 0) {
						list.get(i).fanOut = list.get(i).fanOut + aux;
						list.get(j).fanIn = list.get(j).fanIn + aux;
						aux = 0;
					}
				}
			}
		}
	}

	public static boolean esFuncion(String linea) {

		if (linea.contains("package") || linea.contains("import") || linea.contains("class")
				|| linea.contains("interface") || linea.contains("Enum") || linea.contains("/*") || linea.contains("*/")
				|| linea.startsWith("*", 1))
			return false;
		
		if (linea.contains("(") && linea.contains(")") && (linea.contains("public") || linea.contains("private")))
			return true;

		return false;

	}
	
	public int getComplejidadCiclomatica() {
		return this.cantIf + this.cantWhile + this.cantFor + 1;
	}

	public int getLongitud() {
		this.N1 = 0;
		this.N2 = 0;
		for (int i = 1; i < this.codigo.size(); i++) {

			String[] aux = null;

			aux = this.codigo.get(i).trim().split(" ");
			for (int j = 0; j < aux.length; j++) {
				String[] aux2 = null;
				aux[j] = aux[j].replace("(", " ");
				aux2 = aux[j].split(" ");
				for (int k = 0; k < aux2.length; k++) {
					if (esOperador(aux[j]))
						this.N1++;
					else
						this.N2++;
				}
			}
		}

		return this.N1 + this.N2 - 1;
	}

	private boolean esOperador(String string) {
		for (int i = 0; i < OPERADORES.length; i++) {
			if (string.contains(OPERADORES[i])) {
				if (!(listaOperadores.contains(OPERADORES[i])))
					listaOperadores.add(OPERADORES[i]);

				return true;
			}
		}
		if (!(listaOperandos.contains(string)))
			listaOperandos.add(string);
		return false;
	}

	public double getVolumen() {
		asignarn1n2();
		int longitud = this.getLongitud();
		int n = this.n1 + this.n2; 
		return longitud * (Math.log10(n) / Math.log10(2));
	}

	private void asignarn1n2() {
		this.n1 = listaOperadores.size();
		this.n2 = listaOperandos.size();
	}

	public double getEsfuerzo() {
		final double constante = 0.1;
		double esfuerzo = this.getVolumen() / constante;
		return esfuerzo;
	}
	
	public ArrayList<String> getCodigo() {
		return this.codigo;
	}
	
	public int getCantComentario() {
		return cantComentario;
	}

	public int getLineaInicial() {
		return lineaInicial;
	}

	public int getLlaveCierreFun() {
		return llaveCierreFun;
	}
	
	public int getFanIn() {
		return fanIn;
	}

	public int getFanOut() {
		return fanOut;
	}

	public int getFanIn(List listMetodos) {
		return 0;
	}

	public int getFanOut(List listMetodos) {
		return 0;
	}
}
