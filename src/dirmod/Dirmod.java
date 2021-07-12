/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dirmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Javier.Kachuka
 */
public class Dirmod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        String texto = cargarTexto();
        Map letraXClave = cargarLetrasXTeclas();
        Integer ultimaTecla = null;
        String secuencia = "";

        //logica principal
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            List data = (List) letraXClave.get(c);
            int tecla = (int) data.get(0);
            int cantidadAPulsar = (int) data.get(1);

            if (ultimaTecla != null) { // validacion para saber si agregar espacio en el caso de ser la misma tecla
                if (ultimaTecla == tecla) {
                    secuencia += " ";
                }
            }
            for (int j = 0; j < cantidadAPulsar; j++) { //se carga la cantidad de veces que se debe presionar la tecla
                secuencia += tecla;
            }
            
            ultimaTecla = tecla;
        }

        System.out.println("RESULTADO:");
        System.out.println(secuencia);
    }

    //solicita el texto al usuario
    public static String cargarTexto() {
        String texto = "";
        System.out.print("Ingrese el texto: ");
        Scanner entrada = new Scanner(System.in);
        texto = entrada.nextLine();
        return texto;
    }

    //carga un Map que contiene las letras del abecedario como clave y un array como valor
    //en la primer posicion del array se guarda que tecla se apreta
    //en la segunda posicion del array se guarda cuantas veces se debe apretar
    public static Map cargarLetrasXTeclas() {
        Map<Character, List> letraXClave = new HashMap<>();
        char letra = 'a';
        //recorrer las teclas 
        for (int tecla = 2; tecla <= 9; tecla++) {
            int hasta = (tecla == 7 || tecla == 9) ? 4 : 3; // las unicas teclas que tienen 4 letras son el 7 y el 9
            for (int pulsar = 1; pulsar <= hasta; pulsar++) {
                List cantidadAPulsar = new ArrayList<>();
                cantidadAPulsar.add(tecla);
                cantidadAPulsar.add(pulsar);
                letraXClave.put(letra, cantidadAPulsar);
                letra++;
            }
        }
        List cantidadAPulsar = new ArrayList<>();
        cantidadAPulsar.add(0);
        cantidadAPulsar.add(1);
        letraXClave.put(' ', cantidadAPulsar);
        return letraXClave;
    }

}
