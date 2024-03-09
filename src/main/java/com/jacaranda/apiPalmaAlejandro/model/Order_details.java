package com.jacaranda.apiPalmaAlejandro.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="detalle_pedidos")
public class Order_details {

	@Id
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Order order;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Product product;
	
	@Column(name="cantidad")
	private Integer cant;
	
	@Column(name="precio")
	private Double price;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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

	@Override
	public int hashCode() {
		return Objects.hash(cant, order, price, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order_details other = (Order_details) obj;
		return Objects.equals(cant, other.cant) && Objects.equals(order, other.order)
				&& Objects.equals(price, other.price) && Objects.equals(product, other.product);
	}
	
	
	
}
