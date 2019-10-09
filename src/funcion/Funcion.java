package funcion;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Funcion {

	public static int CANTLINEAARCHIVO = 0;
	public String namefuncion;
	public String shortName;

	public int getCantlineaComentario() {
		return cantlineaComentario;
	}

	private int numLiniaIni;
	// private int numLineaFin;
	// private int cantParam;

	private int cantIf;

	public int getNumLiniaIni() {
		return numLiniaIni;
	}

	public int getLlaveCierreFun() {
		return llaveCierreFun;
	}

	private int cantFor;
	private int cantWhile;
	private int cantlineaComentario;
	private int llaveAperturaFun;
	private int llaveCierreFun;
	private int contadorLlaves;
	private ArrayList<String> codigo;
	private int fanIn;
	private int fanOut;
	private int N1;
	private int N2;
	private int n1;
	private int n2;
	ArrayList<String> listaDeOperadores = new ArrayList<String>();
	ArrayList<String> listaDeOperandos = new ArrayList<String>();

	public Funcion() {
		this.namefuncion = "";
		this.numLiniaIni = 0;
		// this.numLineaFin = 0;
		this.cantIf = 0;
		this.cantFor = 0;
		this.cantWhile = 0;
		this.cantlineaComentario = 0;
		this.llaveAperturaFun = 0;
		this.llaveCierreFun = 0;
		this.contadorLlaves = 0;
		this.codigo = new ArrayList<String>();
		this.fanIn = 0;
		this.fanOut = 0;
		this.N1 = 0;
		this.N2 = 0;
		this.n1 = 1;
		this.n2 = 1;

	}

	private static String cargarShortNameFuncion(String nombre) {
		String[] aux = null;
		String aux2 = "";
		String shortName1 = "";
		// System.out.println(nombre);
		aux = nombre.split(" ");
		for (int i = 0; i < aux.length; i++) {
			if (aux[i].contains("("))
				aux2 = aux[i];
		}

		// System.out.println(aux2);
		aux2 = aux2.replace("(", " ");
		aux = aux2.split(" ");
		shortName1 = aux[0];
		// System.out.println("productos(string".split("("));
		// shortName1 = aux2.split("(");
		// System.out.println(shortName1);
		return shortName1;

	}

	public int getFanIn() {
		return fanIn;
	}

	public int getFanOut() {
		return fanOut;
	}

	public static ArrayList<Funcion> getFuncionesArchivo(String archivo) throws FileNotFoundException {

		ArrayList<Funcion> listaFun = new ArrayList<Funcion>();
		Funcion fun = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File(archivo));
		String linea;
		boolean cargandoFuncion = false;

		while (sc.hasNextLine()) {
			CANTLINEAARCHIVO++;
			linea = sc.nextLine();
			// linea = linea.trim();

			if (esFuncion(linea)) {
				fun = new Funcion();
				// System.out.println(linea);
				fun.codigo.add(linea);
				fun.namefuncion = linea;
				fun.namefuncion = fun.namefuncion.replace("{", "");
				fun.namefuncion = fun.namefuncion.trim();
				fun.shortName = cargarShortNameFuncion(fun.namefuncion);
				// System.out.println(fun.namefuncion);

				cargandoFuncion = true;
				fun.numLiniaIni = CANTLINEAARCHIVO;
				if (linea.contains("{")) {
					fun.llaveAperturaFun = CANTLINEAARCHIVO;
				}

			} else {
				if (cargandoFuncion) {
					fun.codigo.add(linea);
					if (linea.contains("if("))
						fun.cantIf = fun.cantIf + ajusteCondiciones(linea) ;
					if (linea.contains("for("))
						fun.cantFor++;
					if (linea.contains("while("))
						fun.cantWhile++;
					if (linea.contains("/*") || linea.contains("*") || linea.contains("//"))
						fun.cantlineaComentario++;
				}

			}

			if (linea.contains("{") && cargandoFuncion) {
				if (fun.contadorLlaves == 0)
					fun.llaveAperturaFun = CANTLINEAARCHIVO;
				fun.contadorLlaves++;

			}
			if (linea.contains("}") && cargandoFuncion) {
				fun.contadorLlaves--;
				if (cargandoFuncion && fun.contadorLlaves == 0) {
					cargandoFuncion = false;
					listaFun.add(fun);
					// System.out.println("se termino de cargar funcion");
					fun.llaveCierreFun = CANTLINEAARCHIVO;
				}
			}

		}

		cargarFanInOut(listaFun);
		return listaFun;

	}
	
	public static int ajusteCondiciones(String cadena){
		int cantAnd = 0;
		int cantOr = 0;
		cadena = cadena.replace("&&", ",");
		cadena = cadena.replace("||", ",");
		String Auxiliar[] = cadena.split(",");

		System.out.println("linea: " + cadena+" --and: " +cantAnd +" | or: "+ cantOr);
		
		return Auxiliar.length;
	}
	/*
	 * tengo que ver bien la logica, pero la idea es que agarro una funion y
	 * recorro todo el codigo, suamdno las veces que aparecio esta.
	 *
	 */

	public static int contadorDeAparciciones(String lineaCodigo, String nombrefun) {
		int contador = 0;
		while (lineaCodigo.indexOf(nombrefun) > -1) {
			lineaCodigo = lineaCodigo.substring(lineaCodigo.indexOf(nombrefun) + nombrefun.length(),
					lineaCodigo.length());
			contador++;
		}
		return contador;
	}

	private static String compactacionDeCodigo(ArrayList<String> codigo) {
		String lineaDeCodigo = "";
		for (int i = 1; i < codigo.size(); i++)
			lineaDeCodigo = lineaDeCodigo + codigo.get(i);
		return lineaDeCodigo;
	}

	public static void cargarFanInOut(ArrayList<Funcion> list) {

		String codigoDeFuncion = "";
		int aux;
		;

		for (int i = 0; i < list.size(); i++) {
			codigoDeFuncion = compactacionDeCodigo(list.get(i).codigo);

			for (int j = 0; j < list.size(); j++) {

				if (i != j) {
					aux = contadorDeAparciciones(codigoDeFuncion, list.get(j).shortName + "(");
					if (aux != 0) {
						// System.out.println("contador: "+ aux + " | funcion:
						// "+ list.get(j).shortName+"(" + " | funcionNro: "+j);
						list.get(i).fanOut = list.get(i).fanOut + aux;
						list.get(j).fanIn = list.get(j).fanIn + aux;
						aux = 0;
					}

				}
			}
			// System.out.println("*********************************************");

		}
	}

	public static boolean esFuncion(String linea) {

		if (linea.contains("package") || linea.contains("import") || linea.contains("class")
				|| linea.contains("interface") || linea.contains("Enum") || linea.contains("/*") || linea.contains("*/")
				|| linea.startsWith("*", 1))
			// TODAVIA NO VALIDE EL TEMA DE LAS DELCARACION DE PARAMETROS CON
			// LLAVE
			return false;
		if (linea.contains("(") && linea.contains(")") && (linea.contains("public") || linea.contains("private")))
			return true;
		else
			return false;

	}

	public int getComplejidadCiclomatica() {
		return this.cantIf + this.cantWhile + this.cantFor + 1;
	}

	public static void mostrarFuncion(Funcion fun) {

		System.out.println("=====================================================");
		System.out.println("namefuncion: " + fun.namefuncion);
		System.out.println("numLiniaIni: " + fun.numLiniaIni);
		// System.out.println("numLineaFin:"+fun.numLineaFin);
		System.out.println("cantIf: " + fun.cantIf);
		System.out.println("cantFor: " + fun.cantFor);
		System.out.println("cantWhile: " + fun.cantWhile);
		System.out.println("cantlineaComentario: " + fun.cantlineaComentario);
		System.out.println("llaveAperturaFun: " + fun.llaveAperturaFun);
		System.out.println("llaveCierreFun: " + fun.llaveCierreFun);
		System.out.println("contadorLlaves:" + fun.contadorLlaves);
		System.out.println("FanIn:" + fun.fanIn);
		System.out.println("FanOut:" + fun.fanOut);

		System.out.println("===============codigo==================================");
		for (int i = 0; i < fun.codigo.size(); i++)
			System.out.println(fun.codigo.get(i));

	}

	public ArrayList<String> getCodigo() {
		return this.codigo;
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
		final String[] operadores = { "if(", "for(", "while(", "break", "then", "=", "*", "+", "-", "||", "&&", "/",
				"{", "}", ">", "<", "<=", ">=", "%", "do", "int", "double", "this", "String", "float",
				"System.out.println", "return", "false", "true", "null", "boolean", "final", "else" };

		for (int i = 0; i < operadores.length; i++) {
			if (string.contains(operadores[i])) {
				if (!(listaDeOperadores.contains(operadores[i])))
					listaDeOperadores.add(operadores[i]);

				return true;
			}
		}
		if (!(listaDeOperandos.contains(string)))
			listaDeOperandos.add(string);

		return false;
	}

	public double getVolumen() {

		calcularn1yn2();
		int n = this.n1 + this.n2, nGr = this.getLongitud();
		return nGr * (Math.log10(n) / Math.log10(2));
	}

	private void calcularn1yn2() {

		this.n1 = listaDeOperadores.size();
		this.n2 = listaDeOperandos.size();
	}

	public double getEsfuerzo() {

		final double constanteJava = 0.1;
		double esfuerzo = this.getVolumen() / constanteJava;
		return esfuerzo;
	}

	public int getFanIn(List listMetodos) {

		return 0;
	}

	public int getFanOut(List listMetodos) {

		return 0;
	}
}
