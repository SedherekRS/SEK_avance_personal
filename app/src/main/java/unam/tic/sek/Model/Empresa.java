package unam.tic.sek.Model;

public class Empresa {
    private int id;
    private String nombre;
    private String rfc;
    private String direccion;
    private String telefono;
    private int periodoPago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(int periodoPago) {
        this.periodoPago = periodoPago;
    }
}
