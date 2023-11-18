package com.gym.gymapp.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "data_store")
public class storeGym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "id_foto")
    private Long Id_Foto;

    @Column(name = "title", columnDefinition = "TEXT")
    private String Title;

    @Column(name = "deskripsi", columnDefinition = "TEXT")
    private String deskripsi;

    @Column(name = "harga")
    private String harga;

    @Column(name = "date")
    private java.util.Date Date;

   @Column(name = "link_tokped", columnDefinition = "TEXT")
   private String Link_Tokped;

   @Column(name = "link_shopee", columnDefinition = "TEXT")
   private String Link_Shopee;

public Long getId() {
    return Id;
}

public void setId(Long id) {
    Id = id;
}

public Long getId_Foto() {
    return Id_Foto;
}

public void setId_Foto(Long id_Foto) {
    Id_Foto = id_Foto;
}

public String getTitle() {
    return Title;
}

public void setTitle(String title) {
    Title = title;
}

public java.util.Date getDate() {
    return Date;
}

public void setDate(java.util.Date date) {
    Date = date;
}

public String getDeskripsi() {
    return deskripsi;
}

public void setDeskripsi(String deskripsi) {
    this.deskripsi = deskripsi;
}

public String getHarga() {
    return harga;
}

public void setHarga(String harga) {
    this.harga = harga;
}

public String getLink_Tokped() {
    return Link_Tokped;
}

public void setLink_Tokped(String link_Tokped) {
    Link_Tokped = link_Tokped;
}

public String getLink_Shopee() {
    return Link_Shopee;
}

public void setLink_Shopee(String link_Shopee) {
    Link_Shopee = link_Shopee;
}

   
}
