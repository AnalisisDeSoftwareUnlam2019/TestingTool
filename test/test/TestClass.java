package test;

public class TestClass {
	
	public static int testPerfectMethod() {
		// THIS IS A VALUE
		int value = 0;
		// HERE WE ADD
		value++;
		// HERE WE RETURN
		return value;
	}
	
	public static boolean testMoreCommentsMethod() {
		boolean theTrue = true;
		boolean theFalse = false;
		return theTrue == theFalse;
	}
	
	public static int testModularizeMethod(int b1, int b2, int b3, int b4, int b5) {
		// IF DE B1
		if(b1 >= b2 || b1 >= b3 || b1 >= b4) {
			// RETORNO B1
			return b1;
		}
		// IF DE B2
		if(b2 >= b1 || b2 >= b3 || b2 >= b4) {
			// RETORNO B2
			return b2;
		}
		// IF DE B3
		if(b3 >= b1 || b3 >= b2 || b3 >= b4) {
			// RETORNO B3
			return b3;
		}
		// IF DE B4
		if(b4 >= b1 || b4 >= b2 || b4 >= b3) {
			// RETORNO B4
			return b4;
		}
		// RETORNO B5
		return b5;
	}
	
	public static int testModularizeAndCommentMethod(int b1, int b2, int b3, int b4, int b5) {
		if(b1 >= b2 || b1 >= b3 || b1 >= b4) {
			return b1;
		}
		if(b2 >= b1 || b2 >= b3 || b2 >= b4) {
			return b2;
		}
		if(b3 >= b1 || b3 >= b2 || b3 >= b4) {
			return b3;
		}
		if(b4 >= b1 || b4 >= b2 || b4 >= b3) {
			return b4;
		}
		return b5;
	}
	
	public static String tipoTriangulo(int a, int b, int c) {
		if((a > 0 && b > 0 && c > 0) && (a + b > c && b + c > a && c + a > b)) {
			if(a == b && b == c) {
				return "Equilatero";
			} else if(a != b && b != c && a != c) {
				return "Escaleno";
			} else {
				return "Isosceles";
			}
		} else {
			return "No es triangulo";
		}
	}

}
