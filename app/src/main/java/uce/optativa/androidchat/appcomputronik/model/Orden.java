package uce.optativa.androidchat.appcomputronik.model;

/**
 * Created by Alexis on 10/02/2017.
 */

public class Orden {
    private Integer ordNumero;
    private String ordFechaEmision;
    private String ordProblema;
    private String fkEstCodigo;

    public Orden(Integer ordNumero, String ordFechaEmision, String ordProblema, String fkEstCodigo) {
        this.ordNumero = ordNumero;
        this.ordFechaEmision = ordFechaEmision;
        this.ordProblema = ordProblema;
        this.fkEstCodigo = fkEstCodigo;
    }

    public Integer getOrdNumero() {
        return ordNumero;
    }

    public void setOrdNumero(Integer ordNumero) {
        this.ordNumero = ordNumero;
    }

    public String getOrdFechaEmision() {
        return ordFechaEmision;
    }

    public void setOrdFechaEmision(String ordFechaEmision) {
        this.ordFechaEmision = ordFechaEmision;
    }

    public String getOrdProblema() {
        return ordProblema;
    }

    public void setOrdProblema(String ordProblema) {
        this.ordProblema = ordProblema;
    }

    public String getFkEstCodigo() {
        return fkEstCodigo;
    }

    public void setFkEstCodigo(String fkEstCodigo) {
        this.fkEstCodigo = fkEstCodigo;
    }
}
