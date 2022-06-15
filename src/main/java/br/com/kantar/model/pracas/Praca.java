/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.model.pracas;

import java.time.LocalDate;

/**
 *
 * @author Eduardo.Fernando
 */
public class Praca {
    
    
    private LocalDate Data;
   
    private int Emissora;
   
    private String Duracao;
    
    private float ValorPontoPraca;
        
    private float ValorInstar;
    
    private float Diferenca;

    public Praca() {
    }

    public Praca(LocalDate Data, int Emissora, String Duracao, float ValorPontoPraca, float ValorInstar, float Diferenca) {
        this.Data = Data;
        this.Emissora = Emissora;
        this.Duracao = Duracao;
        this.ValorPontoPraca = ValorPontoPraca;
        this.ValorInstar = ValorInstar;
        this.Diferenca = Diferenca;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate Data) {
        this.Data = Data;
    }

    public int getEmissora() {
        return Emissora;
    }

    public void setEmissora(int Emissora) {
        this.Emissora = Emissora;
    }

    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String Duracao) {
        this.Duracao = Duracao;
    }

    public float getValorPontoPraca() {
        return ValorPontoPraca;
    }

    public void setValorPontoPraca(float ValorPontoPraca) {
        this.ValorPontoPraca = ValorPontoPraca;
    }

    public float getValorInstar() {
        return ValorInstar;
    }

    public void setValorInstar(float ValorInstar) {
        this.ValorInstar = ValorInstar;
    }

    public float getDiferenca() {
        return Diferenca;
    }

    public void setDiferenca(float Diferenca) {
        this.Diferenca = Diferenca;
    }

    @Override
    public String toString() {
        return "Praca{" + "Data=" + Data + ", Emissora=" + Emissora + ", Duracao=" + Duracao + ", ValorPontoPraca=" + ValorPontoPraca + ", ValorInstar=" + ValorInstar + ", Diferenca=" + Diferenca + '}';
    }

   
    
    
    
    
    
}
