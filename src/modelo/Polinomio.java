/*
 * Copyright (C) 2021 Oscar Arenas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package modelo;

/**
 *
 * @author Oscar Arenas
 */
public class Polinomio {

    // Campos (Atributos)
    private double[] coeficientes;

    // Métodos (Comportamientos)
    public Polinomio() {
        coeficientes = new double[0];
    }

    public boolean agregar(double coeficiente, int exponente) {
        if (coeficiente == 0 || exponente < 0) {
            return false;
        }
        if (exponente >= coeficientes.length) {
            cambiarCapacidad(exponente + 1);
        }

        coeficientes[exponente] = coeficiente;
        return true;
    }

    private void cambiarCapacidad(int nc) {
        double[] nuevoVector = new double[nc];
        int limite = nc;

        if (nc > coeficientes.length) {// Aumentar capacidad
            limite = coeficientes.length;
        }

        for (int i = 0; i < limite; i++) {
            nuevoVector[i] = coeficientes[i];
        }
        coeficientes = nuevoVector;
    }

    private void ajustar() {
        int j = coeficientes.length - 1;

        while (j >= 0 && coeficientes[j] == 0) {
            j--;
        }
        if (j < coeficientes.length - 1) {
            cambiarCapacidad(j + 1);
        }
    }

    public boolean esVacio() {
        return coeficientes.length == 0;
    }

    /**
     * Genera una representación en cadena (String) del polinomio. La cadena
     * contiene todos los términos del polinomio en orden descendente de los
     * exponentes.
     *
     * @return Cadena que representa el polinomio.
     */
    @Override
    public String toString() {
        String cadena = "";

        if (!esVacio()) {
            int i = coeficientes.length - 1;
            if (coeficientes[i] < 0) {
                cadena = "-";
            }
            cadena += convertirTermino(coeficientes[i], i);
            i--;

            for (; i >= 0; i--) {
                if (coeficientes[i] > 0) {
                    cadena += " + " + convertirTermino(coeficientes[i], i);
                } else if (coeficientes[i] < 0) {
                    cadena += " - " + convertirTermino(coeficientes[i], i);
                }
            }
        }
        return cadena;
    }

    /**
     * Convierte los parámetros a una representación en cadena (String) del
     * término en función de x. El coeficiente lo hace positivo. Por ejemplo, si
     * el coeficiente es -5 y el exponente es 3 el método retorna la cadena
     * "5x^3"
     */
    private String convertirTermino(double coeficiente, int exponente) {
        String cadena = "";
        if (coeficiente != 0) {
            coeficiente = Math.abs(coeficiente);
            int valorEntero = (int) coeficiente;

            if (valorEntero == coeficiente) {
                cadena += valorEntero;
            } else {
                cadena += coeficiente;
            }
            if (exponente > 0) {
                if (coeficiente == 1) {
                    cadena = "";
                }
                if (exponente == 1) {
                    cadena += "x";
                } else {
                    cadena += "x^" + exponente;
                }
            }
        }
        return cadena;
    }
}
