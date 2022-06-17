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
public class PracaDao {

    private LocalDate Data;

    private final String HORAINICIO = "05:59";

    private int PosicaoEmissoraPontoPraca;

    private int PosicaoEmissoraInstar;

    private File ArquivoPontoPraca;

    private File ArquivoInstar;
    
    private int Praca;
    
    private int Emissora;

    public PracaDao(LocalDate Data, int PosicaoEmissoraPontoPraca, int PosicaoEmissoraInstar, File ArquivoPontoPraca, File ArquivoInstar, int Praca, int Emissora) {
        this.Data = Data;
        this.PosicaoEmissoraPontoPraca = PosicaoEmissoraPontoPraca;
        this.PosicaoEmissoraInstar = PosicaoEmissoraInstar;
        this.ArquivoPontoPraca = ArquivoPontoPraca;
        this.ArquivoInstar = ArquivoInstar;
        this.Praca = Praca;
        this.Emissora = Emissora;
    }
    

    
    

    public String[] RetornoPontoPracaMenos2(String[] Valores) {

        List<String> tempVal = new LinkedList();

        for (int i = 0; i < Valores.length; i++) {

            if (i >= 2) {

                tempVal.add(Valores[i]);

            }

        }

        String[] Ret = tempVal.toArray(new String[tempVal.size()]);

        return Ret;

    }

    public String FormatarValoresHora(String Periodo) {

        if (Periodo.contains("00:")
                || Periodo.contains("01:")
                || Periodo.contains("02:")
                || Periodo.contains("03:")
                || Periodo.contains("04:")
                || Periodo.contains("05:")) {

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

    public List<String> obterAudienciaInstar() throws FileNotFoundException, IOException, Exception {

        List<String> Audiencias = new LinkedList();

        BufferedReader bf = new BufferedReader(new FileReader(this.ArquivoInstar));

        String linha = bf.readLine();

        DecimalFormat df = new DecimalFormat("#.##");

        df.setRoundingMode(RoundingMode.HALF_EVEN);

        int contador = 0;

        while (linha != null) {

            if (contador > 0) {

                String[] Valores = linha.split(";");

                // JOptionPane.showMessageDialog(null,Valores);
                if (Valores.length > 2) {

                    Audiencias.add(Valores[this.PosicaoEmissoraInstar - 1].replaceAll("\\,", "\\."));

                }

            }

            linha = bf.readLine();
            contador++;

        }

        return Audiencias;

    }

    public List<String> obterAudienciaPontoPraca() throws FileNotFoundException, IOException, Exception {

        List<String> Audiencias = new LinkedList();

        BufferedReader bf = new BufferedReader(new FileReader(this.ArquivoPontoPraca));

        String linha = bf.readLine();

        DecimalFormat df = new DecimalFormat("#.##");

        df.setRoundingMode(RoundingMode.HALF_EVEN);

        int contador = 0;

        while (linha != null) {

            if (contador > 0) {

                df.setRoundingMode(RoundingMode.HALF_EVEN);

                String[] valores = SeparadorAudiencia(linha.replaceAll("\\*", "")).split(" ");

                String ValorRba = df.format(Double.parseDouble(RetornoPontoPracaMenos2(valores)[this.PosicaoEmissoraPontoPraca - 1]) / 10);

                Audiencias.add(ValorRba.replaceAll("\\,", "\\."));

            }

            linha = bf.readLine();
            contador++;

        }

        return Audiencias;
    }

    public List<Praca> obterObjetosAudiencia() throws Exception {

        List<Praca> PracasAudiencias = new ArrayList();

        List<String> PontoPracas = obterAudienciaPontoPraca();
        List<String> Intares = obterAudienciaInstar();

        for (int i = 0; i <= 1439; i++) {

            String PeriodoConvertido = (String) hora.horarioMetd(this.HORAINICIO).get(i);

            DecimalFormat df = new DecimalFormat("#.##");

            float Diferenca =  Float.parseFloat(Intares.get(i)) - Float.parseFloat(PontoPracas.get(i));
            
            //    Intares.get(i)+" "+PontoPracas.get(i)+" "

         PracasAudiencias.add(new Praca(this.Data, this.Praca,this.Emissora, PeriodoConvertido, Float.parseFloat(Intares.get(i)), Float.parseFloat(PontoPracas.get(i)),Diferenca));
        }

        return PracasAudiencias;

    }

    public static void main(String[] args) throws Exception {

//        new PracaDao().obterAudienciaInstar();
//        new PracaDao().obterAudienciaPontoPraca();
        List<Praca>s =new PracaDao(LocalDate.parse("2022-05-15"),1, 1, new File("CF\\Source\\103\\00012530.106"), new File("C:CF\\Source\\103\\GOI (30052022).txt"),103,1).obterObjetosAudiencia();

        for(Praca x:s){
        
            System.out.println(x);
        
        
        }
        
        
//        Map<String, String> j = new PracaDao().obterAudienciaPontoPraca();
//
//        for (Object x : j.keySet()) {
//
//            System.out.println(x + "-" + j.get(x));
//
//        }
    }

}
