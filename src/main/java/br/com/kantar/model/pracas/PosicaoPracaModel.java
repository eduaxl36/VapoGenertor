/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.model.pracas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Eduardo.Fernando
 */


public class PosicaoPracaModel {

    private int Id;
    private int CodigoPraca;
    private String DescricaoPraca;
    private int CodigoEmissora;
    private String DescricaoEmissora;
    private int PosicaoPontoPraca;
    private int PosicaoInstar;

    public PosicaoPracaModel() {
    }

    public PosicaoPracaModel(int CodigoPraca, String DescricaoPraca, int CodigoEmissora, String DescricaoEmissora, int PosicaoPontoPraca, int PosicaoInstar) {
        this.CodigoPraca = CodigoPraca;
        this.DescricaoPraca = DescricaoPraca;
        this.CodigoEmissora = CodigoEmissora;
        this.DescricaoEmissora = DescricaoEmissora;
        this.PosicaoPontoPraca = PosicaoPontoPraca;
        this.PosicaoInstar = PosicaoInstar;
    }

    public int getCodigoPraca() {
        return CodigoPraca;
    }

    public void setCodigoPraca(int CodigoPraca) {
        this.CodigoPraca = CodigoPraca;
    }

    public String getDescricaoPraca() {
        return DescricaoPraca;
    }

    public void setDescricaoPraca(String DescricaoPraca) {
        this.DescricaoPraca = DescricaoPraca;
    }

    public int getCodigoEmissora() {
        return CodigoEmissora;
    }

    public void setCodigoEmissora(int CodigoEmissora) {
        this.CodigoEmissora = CodigoEmissora;
    }

    public String getDescricaoEmissora() {
        return DescricaoEmissora;
    }

    public void setDescricaoEmissora(String DescricaoEmissora) {
        this.DescricaoEmissora = DescricaoEmissora;
    }

    public int getPosicaoPontoPraca() {
        return PosicaoPontoPraca;
    }

    public void setPosicaoPontoPraca(int PosicaoPontoPraca) {
        this.PosicaoPontoPraca = PosicaoPontoPraca;
    }

    public int getPosicaoInstar() {
        return PosicaoInstar;
    }

    public void setPosicaoInstar(int PosicaoInstar) {
        this.PosicaoInstar = PosicaoInstar;
    }

    @Override
    public String toString() {
        return "PosicaoPracaModel{" + "CodigoPraca=" + CodigoPraca + ", DescricaoPraca=" + DescricaoPraca + ", CodigoEmissora=" + CodigoEmissora + ", DescricaoEmissora=" + DescricaoEmissora + ", PosicaoPontoPraca=" + PosicaoPontoPraca + ", PosicaoInstar=" + PosicaoInstar + '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    
    
    
    
    
}
