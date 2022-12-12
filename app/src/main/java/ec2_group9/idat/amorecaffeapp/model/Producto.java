package ec2_group9.idat.amorecaffeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {

    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private Boolean estado;
    private String imagenUrl;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String id, String nombre, String descripcion, double precio, int cantidad, Categoria categoria, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.estado = estado;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nombre);
        parcel.writeString(descripcion);
        parcel.writeDouble(precio);
        parcel.writeInt(cantidad);
        parcel.writeByte((byte) (estado == null ? 0 : estado ? 1 : 2));
        parcel.writeString(imagenUrl);
        parcel.writeParcelable(categoria, i);
    }

    protected Producto(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        cantidad = in.readInt();
        byte tmpEstado = in.readByte();
        estado = tmpEstado == 0 ? null : tmpEstado == 1;
        imagenUrl = in.readString();
        categoria = in.readParcelable(Categoria.class.getClassLoader());
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

}
