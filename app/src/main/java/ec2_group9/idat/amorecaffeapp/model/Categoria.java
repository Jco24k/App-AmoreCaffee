package ec2_group9.idat.amorecaffeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Categoria implements Parcelable {

    private String id;
    private String nombre;
    private Boolean estado;
    private Producto[] producto;
    private String imagenUrl;

    public Categoria() {
    }

    public Categoria(String id, String nombre, Boolean estado, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.imagenUrl = imagenUrl;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nombre);
        parcel.writeByte((byte) (estado == null ? 0 : estado ? 1 : 2));
        parcel.writeString(imagenUrl);
    }

    protected Categoria(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        byte tmpEstado = in.readByte();
        estado = tmpEstado == 0 ? null : tmpEstado == 1;
        imagenUrl = in.readString();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
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

    public String getImagenUrl() { return imagenUrl; }

    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

}
