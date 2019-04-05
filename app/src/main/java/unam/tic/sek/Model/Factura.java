package unam.tic.sek.Model;

import java.util.Date;

public class Factura {
    private int id;
    private String numeroFactura;
    private double monto;
    private String moneda;
    private Date fechaFactura;
    private String rutaFacturaXML;
    private String rutaFacturaPDF;
    private Empresa empresa;
    private Dolar dolar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getRutaFacturaXML() {
        return rutaFacturaXML;
    }

    public void setRutaFacturaXML(String rutaFacturaXML) {
        this.rutaFacturaXML = rutaFacturaXML;
    }

    public String getRutaFacturaPDF() {
        return rutaFacturaPDF;
    }

    public void setRutaFacturaPDF(String rutaFacturaPDF) {
        this.rutaFacturaPDF = rutaFacturaPDF;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Dolar getDolar() {
        return dolar;
    }

    public void setDolar(Dolar dolar) {
        this.dolar = dolar;
    }
}
