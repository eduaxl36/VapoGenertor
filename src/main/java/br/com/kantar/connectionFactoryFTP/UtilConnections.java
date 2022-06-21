/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.connectionFactoryFTP;

import br.com.kantar.cf.Configuracoes;
import static br.com.kantar.cf.Configuracoes.SOURCEFOLDERS;
import br.com.kantar.util.Util;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

/**
 *
 * @author Eduardo.Fernando
 */
public class UtilConnections {

    

    public boolean DownloadArquivo(String Data, String Praca, String Saida) throws IOException, JSchException, SftpException, ParseException {

        boolean DownloadEfetuado = false;

        ConnectionSFTP Conexao = new ConnectionSFTP(new Configuracoes().obterConfiguracao("user"), new Configuracoes().obterConfiguracao("senha"), new Configuracoes().obterConfiguracao("host"), Integer.parseInt(new Configuracoes().obterConfiguracao("port")));

        ChannelSftp InstanciaCanal = Conexao.Conexao();

        Vector Arquivos = InstanciaCanal.ls("/Brasil/BR/00550" + Praca + "/TCNET");
//        
        for (int i = 0; i < Arquivos.size(); i++) {
            ChannelSftp.LsEntry Entrada = (ChannelSftp.LsEntry) Arquivos.get(i);

            String ArquivoDownload = new Util().obterPontoPracaSFTPMode(Data, Praca);

            if (Entrada.getFilename().equals(ArquivoDownload)) {

                InstanciaCanal.get("/Brasil/BR/00550" + Praca + "/TCNET/" + ArquivoDownload, Saida + "/" + Praca + "/" + ArquivoDownload);

                DownloadEfetuado = true;

            }

        }

        Conexao.Conexao().exit();

        Conexao.Conexao().disconnect();

        Conexao.Conexao().quit();

        InstanciaCanal.exit();

        InstanciaCanal.disconnect();

        return DownloadEfetuado;
    }

    public void DownloadTodasAsPracas(String Data) throws Exception {

        new UtilConnections().DownloadArquivo(Data, "103", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "106", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "108", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "109", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "110", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "133", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "240", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "241", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "242", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "357", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "359", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "360", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "465", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "466", SOURCEFOLDERS+"/Entrada");
        new UtilConnections().DownloadArquivo(Data, "985", SOURCEFOLDERS+"/Entrada");
//        

    }

    public static void main(String[] args) throws Exception {

            
        new UtilConnections().DownloadTodasAsPracas("2022-06-18");


    }

}
