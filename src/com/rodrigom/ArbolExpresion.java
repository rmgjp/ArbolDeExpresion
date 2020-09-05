package com.rodrigom;

import java.util.Queue;
import java.util.Stack;

public class ArbolExpresion {

    public Nodo construirArbol(String[] expresionPostfija){
        Stack<Nodo> Ramas = new Stack<>();
        Nodo Hoja, Temporal1, Temporal2;

        for (int i = 0; i < expresionPostfija.length; i++) {
            String Token = expresionPostfija[i];
            if(!esOperador(Token)){
                Hoja = new Nodo(Token);
                Ramas.push(Hoja);
            }
            else {
                Hoja = new Nodo(Token);

                Temporal1 = Ramas.pop();
                Temporal2 = Ramas.pop();

                Hoja.setNodoDerecho(Temporal1);
                Hoja.setNodoIzquierdo(Temporal2);

                Ramas.push(Hoja);
            }
        }
        Hoja = Ramas.peek();
        Ramas.pop();

        return Hoja;
    }

    public void inOrden(Nodo hoja){
        if(hoja != null){
            if(hoja.getNodoIzquierdo() != null){
                inOrden(hoja.getNodoIzquierdo());
            }
            System.out.print(hoja.getValor());
            if(hoja.getNodoDerecho() != null){
                inOrden(hoja.getNodoDerecho());
            }
        }
    }

    public void preOrden(Nodo hoja){



        if(hoja != null){
            System.out.print(hoja.getValor());
            if(hoja.getNodoIzquierdo() != null){
                inOrden(hoja.getNodoIzquierdo());
            }
            if(hoja.getNodoDerecho() != null){
                inOrden(hoja.getNodoDerecho());
            }
        }
    }

    public void postOrden(Nodo hoja){
        if(hoja != null){
            if(hoja.getNodoIzquierdo() != null){
                inOrden(hoja.getNodoIzquierdo());
            }

            if(hoja.getNodoDerecho() != null){
                inOrden(hoja.getNodoDerecho());
            }
            System.out.print(hoja.getValor());
        }
    }


    private boolean esOperador(String caracter){
        if(caracter.equals("+") || caracter.equals("-") || caracter.equals("/") ||
                caracter.equals("*") || caracter.equals("^")){
            return true;
        }
        else {
            return false;
        }
    }
}
