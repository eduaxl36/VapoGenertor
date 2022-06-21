/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * import java.text.SimpleDateFimport java.util.Date; ormat;
 *
 * @author Eduardo.Fernando
 */
public class Util {

    
        public String obterPontoPracaSFTPMode(String Data,String Praca) throws ParseException {

        int Ano = Integer.parseInt(Data.substring(3, 4));
        String Mes = Data.substring(5, 7).replaceAll("10", "A").replaceAll("11", "B").replaceAll("12", "C").replaceAll("0", "");
        int Dia = Integer.parseInt(Data.substring(Data.length() - 2, Data.length()));

        return "0001" + Ano + Mes + Dia + "."+Praca;

    }
    
    public String obterPontoPraca(String Data) throws ParseException {

        int Ano = Integer.parseInt(Data.substring(3, 4));
        String Mes = Data.substring(5, 7).replaceAll("10", "A").replaceAll("11", "B").replaceAll("12", "C").replaceAll("0", "");
        int Dia = Integer.parseInt(Data.substring(Data.length() - 2, Data.length()));

        return "0001" + Ano + Mes + Dia + ".";

    }

    public String obterNomeArquivoInstarPeloPontoPraca(LocalDate Data, String DescricaoPraca) {

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ddMMyyyy");

        String DataFormatada = DescricaoPraca + " (" + Data.format(formatters) + ").txt";

        return DataFormatada;

    }

    public int obterPracaDoPontoPraca(File ArquivoPontoPraca) {

        LocalDate DataFinal = null;

        try {
            BufferedReader Leitor = new BufferedReader(new FileReader(ArquivoPontoPraca));
            String Linha = Leitor.readLine();

            while (Linha != null) {

                int GetPraca = Integer.parseInt(Linha.substring(0, 3));
                return GetPraca;

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;

    }

    public LocalDate obterDataDoPontoPraca(File ArquivoPontoPraca) {

        LocalDate DataFinal = null;

        try {
            BufferedReader Leitor = new BufferedReader(new FileReader(ArquivoPontoPraca));
            String Linha = Leitor.readLine();

            while (Linha != null) {

                Date DataRecebida = new SimpleDateFormat("ddMMyy").parse(Linha.substring(Linha.length() - 6, Linha.length()));

                DataFinal = DataRecebida.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                Linha = Leitor.readLine();
                break;

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return DataFinal;

    }

    public String obterDescricaoPorCodigo(int CodigoPraca) {

        String PracaDescroicao = null;

        switch (CodigoPraca) {
            case 103:
                PracaDescroicao = "CAM";
                break;
            case 106:
                PracaDescroicao = "GOI";
                break;
            case 108:
                PracaDescroicao = "BHZ";
                break;
            case 109:
                PracaDescroicao = "GRJ";
                break;
            case 110:
                PracaDescroicao = "GSP";
                break;
            case 133:
                PracaDescroicao = "DFE";
                break;
            case 240:
                PracaDescroicao = "CTA";
                break;
            case 241:
                PracaDescroicao = "FLO";
                break;
            case 242:
                PracaDescroicao = "POA";
                break;
            case 357:
                PracaDescroicao = "FOR";
                break;
            case 359:
                PracaDescroicao = "REC";
                break;
            case 360:
                PracaDescroicao = "SAL";
                break;
            case 465:
                PracaDescroicao = "GBE";
                break;
            case 466:
                PracaDescroicao = "MAN";
                break;
            case 985:
                PracaDescroicao = "GVI";
                break;

        }

        return PracaDescroicao;
    }

    public static void main(String[] args) throws ParseException {

//        System.out.println(new Util().obterPracaDoPontoPraca(new File("Entrada/00012607.110")));
        System.out.println(new Util().obterPontoPraca("2022-05-23"));

    }

}
