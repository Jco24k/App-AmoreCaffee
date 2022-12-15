package ec2_group9.idat.amorecaffeapp.model;

import java.util.Date;

public class CabeceraPedido {
    private String id;
    private String forma_pago;
    private String cliente_pedido;
    private Date fecha;
    private Double total;
    private Boolean estado;
    private String tipo_pedido;
    private Cliente cliente;
    private Mesa mesa;
    private String message;
    public CabeceraPedido() {
    }

    public CabeceraPedido(String id, String forma_pago, String cliente_pedido, Date fecha, Double total, Boolean estado, String tipo_pedido, Cliente cliente, Mesa mesa) {
        this.id = id;
        this.forma_pago = forma_pago;
        this.cliente_pedido = cliente_pedido;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.tipo_pedido = tipo_pedido;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public CabeceraPedido(String forma_pago, String cliente_pedido, Double total, String tipo_pedido, Cliente cliente, Mesa mesa) {
        this.forma_pago = forma_pago;
        this.cliente_pedido = cliente_pedido;
        this.total = total;
        this.tipo_pedido = tipo_pedido;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public CabeceraPedido(String forma_pago, String cliente_pedido, Double total, String tipo_pedido, Cliente cliente) {
        this.forma_pago = forma_pago;
        this.cliente_pedido = cliente_pedido;
        this.total = total;
        this.tipo_pedido = tipo_pedido;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public String getCliente_pedido() {
        return cliente_pedido;
    }

    public void setCliente_pedido(String cliente_pedido) {
        this.cliente_pedido = cliente_pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getTipo_pedido() {
        return tipo_pedido;
    }

    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
