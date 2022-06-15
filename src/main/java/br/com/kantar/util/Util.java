/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eduardo.Fernando
 */
public class Util {
    

        
    public String obterPontoPraca(String Data,String Praca) throws ParseException{
    
        
    int Ano =  Integer.parseInt(Data.substring(3,4));
    String Mes =  Data.substring(5,7).replaceAll("10", "A").replaceAll("11", "B").replaceAll("12", "C").replaceAll("0", "");
    int Dia =  Integer.parseInt(Data.substring(Data.length()-2,Data.length()));
       
    
    return "0001"+Ano+Mes+Dia+"."+Praca;     
     
    }
    
    
    
    public static void main(String[] args) throws ParseException {
     
        System.out.println(new Util().obterPontoPraca("2022-06-14","103"));
        
        
    }
    
    
}
