package ec2_group9.idat.amorecaffeapp.model;

public class Mesa {

    private String id;
    private String nombre;
    private Boolean estado;

    public Mesa() {
    }

    public Mesa(String id, String nombre, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Mesa(String nombre){
        this.nombre = nombre;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
