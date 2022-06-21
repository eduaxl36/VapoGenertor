/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.model.pracas;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Eduardo.Fernando
 */
@Entity
public class Audiencia {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
    
    private LocalDate Data;

    private int Praca;
    
    private String Emissora;

    private String Duracao;

    private float ValorPontoPraca;

    private float ValorInstar;

    private float Diferenca;

   

    public Audiencia() {
    }

    public Audiencia(LocalDate Data, int Praca, String Emissora, String Duracao, float ValorPontoPraca, float ValorInstar, float Diferenca) {
        this.Data = Data;
        this.Praca = Praca;
        this.Emissora = Emissora;
        this.Duracao = Duracao;
        this.ValorPontoPraca = ValorPontoPraca;
        this.ValorInstar = ValorInstar;
        this.Diferenca = Diferenca;
    }

    public float getDiferenca() {
        return Diferenca;
    }

    public void setDiferenca(float Diferenca) {
        this.Diferenca = Diferenca;
    }

    public float getValorInstar() {
        return ValorInstar;
    }

    public void setValorInstar(float ValorInstar) {
        this.ValorInstar = ValorInstar;
    }

    public float getValorPontoPraca() {
        return ValorPontoPraca;
    }

    public void setValorPontoPraca(float ValorPontoPraca) {
        this.ValorPontoPraca = ValorPontoPraca;
    }

    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String Duracao) {
        this.Duracao = Duracao;
    }

    public String getEmissora() {
        return Emissora;
    }

    public void setEmissora(String Emissora) {
        this.Emissora = Emissora;
    }

    public int getPraca() {
        return Praca;
    }

    public void setPraca(int Praca) {
        this.Praca = Praca;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate Data) {
    }

    @Override
    public String toString() {
        return "Praca{" + "Data=" + Data + ", Praca=" + Praca + ", Emissora=" + Emissora + ", Duracao=" + Duracao + ", ValorPontoPraca=" + ValorPontoPraca + ", ValorInstar=" + ValorInstar + ", Diferenca=" + Diferenca + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    
    

}
