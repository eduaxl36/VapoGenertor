/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.cf;

import static br.com.kantar.cf.Configuracoes.SOURCEFOLDERS;
import br.com.kantar.model.pracas.PosicaoPracaModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo.Fernando
 */
public class Posicoes {
public List<PosicaoPracaModel> Posicoes() throws FileNotFoundException, IOException {

        BufferedReader Leitor = new BufferedReader(new FileReader(new File(SOURCEFOLDERS+"\\CF\\Configuracao/posicoesEmissoras.csv")));
        String Linha = Leitor.readLine();
        PosicaoPracaModel Configuracao;
        List<PosicaoPracaModel> Configuracoes = new ArrayList();

        while (Linha != null) {

            String Valores[] = Linha.split(";");

            int CodigoPraca = Integer.parseInt(Valores[0]);
            String DescricaoPraca = Valores[1];
            int PosicaoEmissora = Integer.parseInt(Valores[2]);
            String DescricaoEmissora = Valores[3];
            int ValorPontoPraca = Integer.parseInt(Valores[4]);
            int ValorInstar = Integer.parseInt(Valores[5]);

    
            Configuracao = new PosicaoPracaModel(CodigoPraca, DescricaoPraca, PosicaoEmissora, DescricaoEmissora, ValorPontoPraca, ValorInstar);

            Configuracoes.add(Configuracao);         
            
            


            Linha = Leitor.readLine();

        }

        return Configuracoes;

    }
    public List<PosicaoPracaModel> Posicoes(int FiltroPraca) throws FileNotFoundException, IOException {

        BufferedReader Leitor = new BufferedReader(new FileReader(new File(SOURCEFOLDERS+"\\CF\\Configuracao/posicoesEmissoras.csv")));
        String Linha = Leitor.readLine();
        PosicaoPracaModel Configuracao;
        List<PosicaoPracaModel> Configuracoes = new ArrayList();

        while (Linha != null) {

            String Valores[] = Linha.split(";");

            int CodigoPraca = Integer.parseInt(Valores[0]);
            String DescricaoPraca = Valores[1];
            int PosicaoEmissora = Integer.parseInt(Valores[2]);
            String DescricaoEmissora = Valores[3];
            int ValorPontoPraca = Integer.parseInt(Valores[4]);
            int ValorInstar = Integer.parseInt(Valores[5]);

            
            if(FiltroPraca==CodigoPraca){
            

            Configuracao = new PosicaoPracaModel(CodigoPraca, DescricaoPraca, PosicaoEmissora, DescricaoEmissora, ValorPontoPraca, ValorInstar);

            Configuracoes.add(Configuracao);         
            
            }


            Linha = Leitor.readLine();

        }

        return Configuracoes;

    }

       
    
}
