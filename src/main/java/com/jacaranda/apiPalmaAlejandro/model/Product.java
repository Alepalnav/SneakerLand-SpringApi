package com.jacaranda.apiPalmaAlejandro.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="productos")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_producto")
	private Integer id;
	
	@NotNull(message="Name may not be null")
	@Column(name="nombre")
	private String name;
	
	@NotNull(message="brand may not be null")
	@Column(name="marca")
	private String brand;

	private String descrip;
	
	@Column(name="talla")
	private Integer size;
	
	@NotNull(message="image may not be null")
	@Column(name="imagen")
	private String image;
	
	@NotNull(message="Name may not be null")
    @Min(value = 1, message = "El precio debe ser un número mayor que cero")
	@Column(name="precio")
	private Double price; 
	
    @Min(value = 1, message = "El stock debe ser un número mayor que cero")
	private Integer stock;
	
	@Column(name="eliminado")
	private Integer remove;
	
	@OneToMany(mappedBy="product")
	private List<Order_details> details;
	
	public Product() {
		super();
	}

	public Product(String name, String brand, String descrip, Integer size, String image, Double price, Integer stock,
			Integer remove) {
		super();
		this.name = name;
		this.brand = brand;
		this.descrip = descrip;
		this.size = size;
		this.image = image;
		this.price = price;
		this.stock = stock;
		this.remove = remove;
	}



	public Product(Integer id, String name, String brand, String descrip, Integer size, String image, Double price,
			Integer stock, Integer remove) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.descrip = descrip;
		this.size = size;
		this.image = image;
		this.price = price;
		this.stock = stock;
		this.remove = remove;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Order_details> getDetails() {
		return details;
	}

	public void setDetails(List<Order_details> details) {
		this.details = details;
	}

	public Integer getRemove() {
		return remove;
	}

	public void setRemove(Integer remove) {
		this.remove = remove;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
