package com.jacaranda.apiPalmaAlejandro.dto;

import java.util.ArrayList;
import java.util.List;

import com.jacaranda.apiPalmaAlejandro.model.Order;
import com.jacaranda.apiPalmaAlejandro.model.Order_details;
import com.jacaranda.apiPalmaAlejandro.model.User;

public class UserDTO {
	
	private Integer id;

	private String username;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String role;
	
	List<OrderDTO> orders;

	public UserDTO() {
		super();
	}
	
	

	public UserDTO(Integer id, String username, String name, String email, String password, String address,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
	}



	public UserDTO(Integer id, String username, String name, String email, String password, String address, String role,
			List<OrderDTO> orders) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.orders = orders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}

	
	public static List<UserDTO> convertUserToDTO(List<User> list) {
		List<UserDTO> res = new ArrayList<UserDTO>();

		for (User u : list) {

			List<OrderDTO> orders = new ArrayList<OrderDTO>();
			for (Order od : u.getOrders()) {
				
				List<Order_detailsDTO> order_details = new ArrayList<Order_detailsDTO>();
				for(Order_details odd : od.getDetails()) {
					Order_detailsDTO odddto = new Order_detailsDTO(odd.getOrder().getId_order(), odd.getProduct().getId(), odd.getCant(), odd.getPrice());
					order_details.add(odddto);
				}
				
				OrderDTO oddto = new OrderDTO(od.getId_order(), od.getUser().getId(), od.getDate(), od.getState(), order_details);
				orders.add(oddto);
			}
			UserDTO udto = new UserDTO(u.getId(), u.getUsername(),u.getName(),u.getEmail(),u.getPassword(),u.getAddress(),u.getRole(), orders);
			res.add(udto);

		}

		return res;
	}
	
	
}
