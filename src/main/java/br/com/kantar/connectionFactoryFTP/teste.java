/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.connectionFactoryFTP;

import br.com.kantar.cf.Configuracoes;
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
public class teste {

    
    
    
    
//    public boolean DownloadArquivo(){
//    
//    boolean DownloadEfetuado = false;
//    
//    
//        
//    
//    
//    }
//    
    
    
    public static void teste(String Data,String Praca,String Saida) throws JSchException, SftpException, IOException, ParseException {
    
    
    
        boolean DownloadEfetuado = false;
        
        ConnectionSFTP Conexao = new ConnectionSFTP(new Configuracoes().obterConfiguracao("user"), new Configuracoes().obterConfiguracao("senha"), new Configuracoes().obterConfiguracao("host"), Integer.parseInt(new Configuracoes().obterConfiguracao("port")));

       
        
        
        ChannelSftp InstanciaCanal = Conexao.Conexao();

        Vector Arquivos = InstanciaCanal.ls("/Brasil/BR/00550"+Praca+"/TCNET");
        
        for (int i = 0; i < Arquivos.size(); i++) {
            ChannelSftp.LsEntry Entrada = (ChannelSftp.LsEntry) Arquivos.get(i);
     
            String ArquivoDownload = new Util().obterPontoPraca(Data, Praca);
            
            if(Entrada.getFilename().equals(ArquivoDownload)){
            
            InstanciaCanal.get("/Brasil/BR/00550"+Praca+"/TCNET/"+ArquivoDownload,Saida+"/"+Praca+"/"+ArquivoDownload);
   
            DownloadEfetuado=true;
            
            
            }
            
     

        }

        
        
        Conexao.Conexao().exit();
        Conexao.Conexao().disconnect();
        Conexao.Conexao().quit();
        
        InstanciaCanal.exit();
        InstanciaCanal.disconnect();
        
    
        System.out.println(DownloadEfetuado);
    
    
    
    
    }
    
    
    public static void main(String[] args) throws JSchException, SftpException, IOException, ParseException {

        
        teste("2022-06-14","103","Temp");
        teste("2022-06-14","106","Temp");
      
        
    }

}
