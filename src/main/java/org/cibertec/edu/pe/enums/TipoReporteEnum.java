package org.cibertec.edu.pe.enums;

public enum TipoReporteEnum {
    XLSX("xlsx"),
    PDF("pdf");

    public final String tipo;

    private TipoReporteEnum(String tipo) {
        this.tipo = tipo;
    }
}
