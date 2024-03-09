package com.jacaranda.apiPalmaAlejandro.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.jacaranda.apiPalmaAlejandro.model.Order;
import com.jacaranda.apiPalmaAlejandro.model.Order_details;

public class OrderDTO {

	
	private Integer id_order;
	
	private Integer user;
	
	private Date date;
	
	private String state;
	
	List<Order_detailsDTO> details;
	
	public OrderDTO() {
		super();
	}

	public OrderDTO(Integer id_order, Integer user, Date date, String state, List<Order_detailsDTO> details) {
		super();
		this.id_order = id_order;
		this.user = user;
		this.date = date;
		this.state = state;
		this.details = details;
	}

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
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

	public List<Order_detailsDTO> getDetails() {
		return details;
	}

	public void setDetails(List<Order_detailsDTO> details) {
		this.details = details;
	}
	
	
	public static List<OrderDTO> convertOrderToDTO(List<Order> list) {
		List<OrderDTO> res = new ArrayList<OrderDTO>();

		for (Order or : list) {

			List<Order_detailsDTO> order_details = new ArrayList<Order_detailsDTO>();
			for (Order_details od : or.getDetails()) {
				Order_detailsDTO oddto = new Order_detailsDTO(od.getOrder().getId_order(), od.getProduct().getId(), od.getCant(), od.getPrice());
				order_details.add(oddto);
			}
			OrderDTO ordto = new OrderDTO(or.getId_order(), or.getUser().getId(),or.getDate(),or.getState(), order_details);
			res.add(ordto);

		}

		return res;
	}
}
