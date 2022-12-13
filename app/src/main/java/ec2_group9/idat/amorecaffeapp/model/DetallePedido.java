package ec2_group9.idat.amorecaffeapp.model;

public class DetallePedido {
    private String cabeceraPedidoId;
    private String productoId;
    private Double precio;
    private int cantidad;
    private Double subtotal;
    private Boolean estado;
    private CabeceraPedido cabeceraPedido;
    private Producto producto;

    public DetallePedido() {
    }

    public DetallePedido(Double precio, int cantidad, Double subtotal, Producto producto) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public CabeceraPedido getCabeceraPedido() {
        return cabeceraPedido;
    }

    public void setCabeceraPedido(CabeceraPedido cabeceraPedido) {
        this.cabeceraPedido = cabeceraPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

