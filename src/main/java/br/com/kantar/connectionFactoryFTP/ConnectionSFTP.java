/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.connectionFactoryFTP;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 *
 * @author Eduardo.Fernando
 */
public class ConnectionSFTP {

    private String Usuario;
    private String Senha;
    private String Host;
    private int Porta;
    private final String CRIPTOGRAFICA = "StrictHostKeyChecking";
    private final String PROTOCOLO = "sftp";
    private final String REPLY = "no";

    public ConnectionSFTP(String Usuario, String Senha, String Host, int Porta) {
        this.Usuario = Usuario;
        this.Senha = Senha;
        this.Host = Host;
        this.Porta = Porta;
    }

    public Session obterSessao() throws JSchException{
    
        JSch ConexaoInterna = new JSch();

        Session Sessao = ConexaoInterna.getSession(this.Usuario, this.Host, this.Porta);

        Sessao.setPassword(this.Senha);

        java.util.Properties config = new java.util.Properties();

        config.put(CRIPTOGRAFICA, REPLY);

        Sessao.setConfig(config);

        Sessao.connect();
        
    
    return Sessao;
    
    }
    
    public void destruirSessao() throws JSchException {

        obterSessao().disconnect();

    }
    
    
    
    public ChannelSftp Conexao() throws JSchException {


        ChannelSftp Canal = (ChannelSftp) obterSessao().openChannel(PROTOCOLO);

        Canal.connect();

        return Canal;

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String Host) {
        this.Host = Host;
    }

    public int getPorta() {
        return Porta;
    }

    public void setPorta(int Porta) {
        this.Porta = Porta;
    }

}
