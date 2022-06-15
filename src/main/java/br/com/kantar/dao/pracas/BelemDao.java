/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.dao.pracas;

import br.com.kantar.model.pracas.Praca;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class BelemDao {

    private LocalDate Data;

    private final String HORAINICIO = "05:59";

    private int PosicaoEmissoraPontoPraca;

    private int PosicaoEmissoraInstar;

    private File ArquivoPontoPraca;

    private File ArquivoInstar;

    public String FormatarValoresHora(String Periodo) {

        if (Periodo.contains("00:")
                || Periodo.contains("01:")
                || Periodo.contains("02:")
                || Periodo.contains("03:")
                || Periodo.contains("04:")
                || Periodo.contains("05:")
                || Periodo.contains("00:")) {

            Periodo = "1." + Periodo;

        } else {

            Periodo = "0." + Periodo;

        }

        return Periodo;

    }

    public String SeparadorAudiencia(String Linha) {

        StringBuilder ValorAudiencia = new StringBuilder(Linha);

        int Posicao = 3;
        int Indice = ValorAudiencia.length() - Posicao;
        while (Indice > 0) {
            ValorAudiencia.insert(Indice, " ");
            Indice = Indice - Posicao;
        }
        return ValorAudiencia.toString();

    }

//    Multimap<String, MyObj> multimap = 
//      MultimapBuilder.treeKeys().linkedListValues().build();
//  
    public List<String> obterAudienciaInstar() throws FileNotFoundException, IOException, Exception {

        List<String> Audiencias = new LinkedList();

        BufferedReader bf = new BufferedReader(new FileReader(new File("c:/teste/GSP (30052022).txt")));

        String linha = bf.readLine();

        DecimalFormat df = new DecimalFormat("#.##");

        df.setRoundingMode(RoundingMode.HALF_EVEN);

        int contador = 0;

        while (linha != null) {

            if (contador > 0) {

                String[] Valores = linha.split(";");
                
                if(Valores.length>2){
          
                    
                Audiencias.add(Valores[1].replaceAll("\\,", "\\."));
                
                }
                               
                 
                
                
               
              
            }

            linha = bf.readLine();
            contador++;

        }

        return Audiencias;

    }

    public List<String> obterAudienciaPontoPraca() throws FileNotFoundException, IOException, Exception {

        List<String> Audiencias = new LinkedList();

        BufferedReader bf = new BufferedReader(new FileReader(new File("c:/teste/00012530.110")));

        String linha = bf.readLine();

        DecimalFormat df = new DecimalFormat("#.##");

        df.setRoundingMode(RoundingMode.HALF_EVEN);

        int contador = 0;

        while (linha != null) {

            if (contador > 0) {

                df.setRoundingMode(RoundingMode.HALF_EVEN);

                String[] valores = SeparadorAudiencia(linha.replaceAll("\\*", "")).split(" ");
                     
                String ValorRba = df.format(Double.parseDouble(valores[2]) / 10);

                Audiencias.add(ValorRba.replaceAll("\\,", "\\."));

            }

            linha = bf.readLine();
            contador++;

        }

        return Audiencias;
    }

    public List<Praca> obterObjetosAudiencia() throws Exception {

        List<Praca> PracasAudiencias = new ArrayList();
        
        List<String>PontoPracas = obterAudienciaPontoPraca();
        List<String>Intares = obterAudienciaInstar();

        for (int i = 0; i <= 1439; i++) {

            String PeriodoConvertido = (String) hora.horarioMetd(this.HORAINICIO).get(i);
DecimalFormat df = new DecimalFormat("#.##");
  
                 System.out.println(df.format(Float.parseFloat(Intares.get(i))-Float.parseFloat(PontoPracas.get(i))));
           //    Intares.get(i)+" "+PontoPracas.get(i)+" "
            
            
//          PracasAudiencias.add(new Praca(LocalDate.parse("2022-05-22"), 1, PeriodoConvertido, Float.parseFloat(Intares.get(i)), Float.parseFloat(PontoPracas.get(i)), 0.0f));

        }

        return PracasAudiencias;

    }

    public static void main(String[] args) throws Exception {

//        new BelemDao().obterAudienciaInstar();
//        new BelemDao().obterAudienciaPontoPraca();
        System.out.println(new BelemDao().obterObjetosAudiencia());

//        Map<String, String> j = new BelemDao().obterAudienciaPontoPraca();
//
//        for (Object x : j.keySet()) {
//
//            System.out.println(x + "-" + j.get(x));
//
//        }
    }

}
