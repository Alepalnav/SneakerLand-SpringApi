package com.jacaranda.apiPalmaAlejandro.model;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pedidos")
public class Order {

	@Id
	@Column(name="id_pedido")
	private Integer id_order;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private User user;
	
	@Column(name="fecha")
	private Date date;
	
	@Column(name="estado")
	private String state;
	
	@OneToMany(mappedBy="order")
	List<Order_details> details;

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Order_details> getDetails() {
		return details;
	}

	public void setDetails(List<Order_details> details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, details, id_order, state, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(date, other.date) && Objects.equals(details, other.details)
				&& Objects.equals(id_order, other.id_order) && state == other.state && Objects.equals(user, other.user);
	}
	
	
	
	
}
