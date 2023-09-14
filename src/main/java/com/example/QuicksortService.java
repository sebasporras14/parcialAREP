package com.example;


public class QuicksortService {

    public static Double[] quickSort(Double[] lista, int inicio, int fin){
        if(inicio < fin ){
            int p = particion(lista, inicio, fin);
            quickSort(lista, inicio, p-1);
            quickSort(lista, p+1, fin);
        }
        return lista; 
    }

    public static int particion(Double[] lista, int inicio, int fin){
        int p = lista.length-1;
        for(int i=0 ; i < lista.length ; i++){
            if(lista[i] > lista[p]){
                Double comodin = lista[i];
                lista[i] = lista[p];
                lista[p] = comodin;
                p = i-1;
            }
        }
        return p;
    }}
    
    
