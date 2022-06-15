/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.kantar.emissoras;

/**
 *
 * @author Eduardo.Fernando
 */
public enum EMISSORAS {
    
    TOTAL_LIGADOS(1),
    TOTAL_ESPECIAL(2),
    GLOBO(3),
    RECORD(4),
    SBT(5),
    BAND(6),
    REDETV(7),
    TVBRASIL(8),
    REDEMINAS(9),
    TV98(10),
    RECORDNEWS(11),
    REDEFAMILIA(12),
    CNT(13),
    TVBRASILIA(14),
    TVDIARIO(15),
    BOASNOVAS(16),
    ACRITICA(17),
    CANAISPAGOS(18),
    ARATU(19),
    REDEVIDA(20),
    TVAPARECIDA(21),
    TVGAZETA(22),;
    
    private int Codigo;

    private EMISSORAS() {
    }

    private EMISSORAS(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
    
    
    
}
