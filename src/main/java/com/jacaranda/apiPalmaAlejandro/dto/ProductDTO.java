package com.jacaranda.apiPalmaAlejandro.dto;

import java.util.ArrayList;
import java.util.List;

import com.jacaranda.apiPalmaAlejandro.model.Order_details;
import com.jacaranda.apiPalmaAlejandro.model.Product;

public class ProductDTO {
	
	private Integer id;
	
	private String name;
	
	private String brand;

	private String descrip;
	
	private Integer size;
	
	private String image;
	
	private Double price; 
	
	private Integer stock;
	
	private Integer remove;
	
	List<Order_detailsDTO> details;

	public ProductDTO() {
		super();
	}
	
	public ProductDTO(String name, String brand, String descrip, Integer size, String image, Double price,
			Integer stock, Integer remove) {
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

	public ProductDTO(Integer id, String name, String brand, String descrip, Integer size, String image, Double price,
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

	public ProductDTO(Integer id, String name, String brand, String descrip, Integer size, String image, Double price,
			Integer stock, Integer remove, List<Order_detailsDTO> details) {
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
		this.details = details;
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

	public Integer getRemove() {
		return remove;
	}

	public void setRemove(Integer remove) {
		this.remove = remove;
	}

	public List<Order_detailsDTO> getDetails() {
		return details;
	}

	public void setDetails(List<Order_detailsDTO> details) {
		this.details = details;
	}
	
	public static List<ProductDTO> convertProductToDTO(List<Product> list) {
		List<ProductDTO> res = new ArrayList<ProductDTO>();

		for (Product p : list) {

			List<Order_detailsDTO> order_details = new ArrayList<Order_detailsDTO>();
			for (Order_details od : p.getDetails()) {
				Order_detailsDTO oddto = new Order_detailsDTO(od.getOrder().getId_order(), od.getProduct().getId(), od.getCant(), od.getPrice());
				order_details.add(oddto);
			}
			ProductDTO pdto = new ProductDTO(p.getId(), p.getName(),p.getBrand(),p.getDescrip(),p.getSize(), p.getImage(), p.getPrice(),p.getStock(),p.getRemove(),order_details);
			res.add(pdto);

		}

		return res;
	}
	
	
}
