package ec2_group9.idat.amorecaffeapp.model;

public class Categoria {

    private String id;
    private String nombre;
    private Boolean estado;
    private String imagenUrl;
    private Producto[] producto;

    public Categoria() {
    }

    public Categoria(String id, String nombre, Boolean estado, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.imagenUrl = imagenUrl;
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

    public Producto[] getProducto() {
        return producto;
    }

    public void setProducto(Producto[] producto) {
        this.producto = producto;
    }

    public String getImagenUrl() {  return imagenUrl; }

    public void setImagenUrl(String imagenUrl) {this.imagenUrl = imagenUrl;}
}
