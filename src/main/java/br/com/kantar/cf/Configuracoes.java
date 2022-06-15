/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.cf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class Configuracoes {
    

    
    
    public String obterConfiguracao(String Chave) throws FileNotFoundException, IOException{
    
    BufferedReader Leitor = new BufferedReader(new FileReader(new File("CF/Cf.csv")));
    String Linha =Leitor.readLine();
    Map Configuracoes = new LinkedHashMap();
    
    
    
    while(Linha!=null){
    
    
    String Dados[] =  Linha.split(";");
    
    Configuracoes.put(Dados[0], Dados[1]);
        
   
    Linha = Leitor.readLine();
       
        
    }
    
    
       return Configuracoes.get(Chave).toString();
    
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        System.out.println(new Configuracoes().obterConfiguracao("senha"));
        
        
    }
    
    
}
