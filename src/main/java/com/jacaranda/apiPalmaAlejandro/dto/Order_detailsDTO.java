package com.jacaranda.apiPalmaAlejandro.dto;

import java.util.ArrayList;
import java.util.List;

import com.jacaranda.apiPalmaAlejandro.model.Order_details;

public class Order_detailsDTO {
	
	private Integer order;
	
	private Integer product;
	
	private Integer cant;
	
	private Double price;

	public Order_detailsDTO() {
		super();
	}

	public Order_detailsDTO(Integer order, Integer product, Integer cant, Double price) {
		super();
		this.order = order;
		this.product = product;
		this.cant = cant;
		this.price = price;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public static List<Order_detailsDTO> convertToDTO(List<Order_details> lista){
        List<Order_detailsDTO> result = new ArrayList<Order_detailsDTO>();

        for(Order_details order_details : lista) {
        	Order_detailsDTO oddto = new Order_detailsDTO(order_details.getOrder().getId_order(), order_details.getProduct().getId(), order_details.getCant(), order_details.getPrice());
            result.add(oddto);
        }

        return result;
    }

}
