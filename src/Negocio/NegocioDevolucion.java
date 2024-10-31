/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.Devulucion;
import Datos.Libros;
import Datos.Prestamos;

public class NegocioDevolucion {

    Devulucion cp;
    Prestamos vt;

    public void PuenteDevoluvion(Libros prod) {
        cp = new Devulucion();
        cp.Comprar(prod);
    }

    public void PuentePrestamo(Libros prod) {
        vt = new Prestamos();
    }
}
