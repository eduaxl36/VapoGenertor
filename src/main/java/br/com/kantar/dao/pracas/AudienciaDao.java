/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.dao.pracas;

import static br.com.kantar.cf.Configuracoes.SOURCEFOLDERS;
import br.com.kantar.util.TimeUtil;
import br.com.kantar.cf.Posicoes;
import br.com.kantar.exceptions.ExistingValueExceptions;
import br.com.kantar.exceptions.NoInstarExceptions;
import br.com.kantar.model.pracas.PosicaoPracaModel;
import br.com.kantar.model.pracas.Audiencia;
import br.com.kantar.util.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.naming.NoPermissionException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Eduardo.Fernando
 */
public class AudienciaDao {

    private LocalDate Data;

    private final String HORAINICIO = "05:59";

    private int PosicaoEmissoraPontoPraca;

    private int PosicaoEmissoraInstar;

    private File ArquivoPontoPraca;

    private int Praca;

    private String Emissora;

    private String DataInputString;

    EntityManager Fabrica;

    public AudienciaDao(String DataInputString, EntityManager Fabrica) {

        this.DataInputString = DataInputString;
        this.Fabrica = Fabrica;

    }

    public AudienciaDao(File ArquivoPontoPraca) {

        this.ArquivoPontoPraca = ArquivoPontoPraca;

    }

    public AudienciaDao(LocalDate Data, int PosicaoEmissoraPontoPraca, int PosicaoEmissoraInstar, File ArquivoPontoPraca, int Praca, String Emissora) {
        this.Data = Data;
        this.PosicaoEmissoraPontoPraca = PosicaoEmissoraPontoPraca;
        this.PosicaoEmissoraInstar = PosicaoEmissoraInstar;
        this.ArquivoPontoPraca = ArquivoPontoPraca;

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

        int getPraca = new Util().obterPracaDoPontoPraca(this.ArquivoPontoPraca);
        LocalDate getData = new Util().obterDataDoPontoPraca(this.ArquivoPontoPraca);

        String ArquivoInstar = SOURCEFOLDERS + "/Entrada/" + new Util().obterPracaDoPontoPraca(this.ArquivoPontoPraca) + "/"
                + new Util().obterNomeArquivoInstarPeloPontoPraca(getData, new Util().obterDescricaoPorCodigo(new Util().obterPracaDoPontoPraca(this.ArquivoPontoPraca)));

        List<String> Audiencias = new LinkedList();

        if (!new File(ArquivoInstar).exists()) {

            throw new NoInstarExceptions("Arquivo instar não encontrado ->  " + ArquivoInstar);

        }

        BufferedReader bf = new BufferedReader(new FileReader(new File(ArquivoInstar)));

        String linha = bf.readLine();

        DecimalFormat df = new DecimalFormat("#.##");

        df.setRoundingMode(RoundingMode.HALF_EVEN);

        int contador = 0;

        while (linha != null) {

            if (contador > 0) {

                String[] Valores = linha.split(";");

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

    public List<Audiencia> obterObjetosAudiencia() throws Exception {

        List<Audiencia> PracasAudiencias = new ArrayList();

        List<String> PontoPracas = obterAudienciaPontoPraca();

        List<String> Intares = obterAudienciaInstar();

        List Periodos = TimeUtil.horarioMetd(this.HORAINICIO);

        for (int i = 0; i < PontoPracas.size(); i++) {

            String PeriodoConvertido = (String) Periodos.get(i + 1);

            float Diferenca = Float.parseFloat(Intares.get(i)) - Float.parseFloat(PontoPracas.get(i));

            PracasAudiencias.add(new Audiencia(
                    new Util().obterDataDoPontoPraca(this.ArquivoPontoPraca),
                    new Util().obterPracaDoPontoPraca(this.ArquivoPontoPraca),
                    this.Emissora,
                    FormatarValoresHora(PeriodoConvertido),
                    Float.parseFloat(PontoPracas.get(i)),
                    Float.parseFloat(Intares.get(i)),
                    Diferenca
            )
            );

        }

        return PracasAudiencias;

    }

    public List<Audiencia> obterAudienciasGerais() throws IOException, Exception {

        //  List Pracas = Arrays.asList("103", "106", "108", "109", "110", "133", "240", "241", "242", "357", "359", "360", "465", "466", "985");
//        List Pracas = Arrays.asList("103", "106", "108", "109");
        List<Audiencia> Audiencias = new ArrayList();

//            if (!ArquivoPontoPraca.exists()) {
//
//                throw new NoPermissionException("Arquivo Ponto Praça não Encontrado -> " + ArquivoPontoPraca);
//
//            }
//            int getPraca = new Util().obterPracaDoPontoPraca(ArquivoPontoPraca);
//
//          
        List<PosicaoPracaModel> Configuracoes = new Posicoes().Posicoes();

        for (PosicaoPracaModel Posicao : Configuracoes) {

            File ArquivoPontoPraca = new File(SOURCEFOLDERS + "/Entrada/" + Posicao.getCodigoPraca() + "/" + new Util().obterPontoPraca(this.DataInputString) + Posicao.getCodigoPraca());
            LocalDate getData = new Util().obterDataDoPontoPraca(ArquivoPontoPraca);

            Audiencias.addAll(new AudienciaDao(
                    getData,
                    Posicao.getPosicaoPontoPraca(),
                    Posicao.getPosicaoInstar(),
                    ArquivoPontoPraca,
                    Posicao.getCodigoPraca(),
                    Posicao.getDescricaoEmissora()
            )
                    .obterObjetosAudiencia()
            );

        }

        return Audiencias;

    }

    public Long checarSeDiaExisteBase(String Data) {

        List<Audiencia> Audiencias = Fabrica.createQuery("from Audiencia").getResultList();

        return Audiencias.stream()
                .filter(x -> x.getData().equals(LocalDate.parse(Data)))
                .collect(Collectors.counting());

    }

    public boolean gravarDados() throws Exception {

        Long ValidarDia = checarSeDiaExisteBase(this.DataInputString);

        if (ValidarDia == 0) {

            List<Audiencia> Audiencias = new AudienciaDao(this.DataInputString, this.Fabrica).obterAudienciasGerais();

            Audiencias.forEach(Audiencia -> {
                Fabrica.persist(Audiencia);
            });

        } else {

            throw new ExistingValueExceptions("A data " + this.DataInputString + " ja encontra-se na base de dados, delete primeiro depois insira");

        }

        return ValidarDia > 0;

    }

    public boolean deletarDados() throws Exception {

        Query query = Fabrica.createQuery("delete Audiencia where Data = :Data");
        query.setParameter("Data", LocalDate.parse(this.DataInputString));

        int result = query.executeUpdate();

        if (result > 0) {
            System.out.println("Expensive products was removed");
        }

        return false;

    }

    public static void main(String[] args) throws Exception {

//        new Thread() {
//
//            public void run() {
//
//                try {
//                    new AudienciaDao("2022-06-18", null).obterAudienciasGerais();
//                } catch (Exception ex) {
//                    Logger.getLogger(AudienciaDao.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//
//        }.start();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VapoJPA");
        EntityManager em = emf.createEntityManager();
//        
        em.getTransaction().begin();
        
        new AudienciaDao("2022-06-18", em).gravarDados();
        
        em.getTransaction().commit();
//em.getTransaction().begin();
//new AudienciaDao("2022-06-18", em).deletarDados();
//    em.getTransaction().commit();   
    }

}
