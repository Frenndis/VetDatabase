
package Controlador;


public class Funciones {
    public boolean verCampo(String campo) {
		boolean valida = false;
		if (campo.trim().length() > 0) {
			valida = true;
		}
		return valida;
	}
	
}
