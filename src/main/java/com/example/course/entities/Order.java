package com.example.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "tb_order") //to change the name of the table in the database
public class Order implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant moment;
	
	
	//in the database will save as a integer.
	private Integer orderStatus;
	
	
	
	
	@ManyToOne //because this is a foreign key that has many to one Client.
	@JoinColumn(name = "client_id") //name that will show in the database
	private User client;
	
	
	@OneToMany(mappedBy = "id.order") //because OrderItemPK has a property order.
	private Set<OrderItem> items = new HashSet<>();
	
	
	@OneToOne(mappedBy = "order" , cascade = CascadeType.ALL) 
	//mapped to have the same ID, if the Order is code 5
	//the payment will be code 5 as well.
	private Payment payment;
	
	
	public Order()
	{
		
	}
	
	public Order(Long id, Instant moment, User client, OrderStatus orderStatus)
	{
		this.id = id;
		this.moment = moment;
		this.client = client;
		 setOrderStatus(orderStatus); //in the parameter of the constructor will pass
		 // a orderStatus enum, but calling the setOrderStatus, will return the orderStatus integer.
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	
	
	
	//return a orderstatus Integer as a string.
	public OrderStatus getOrderStatus()
	{
		return OrderStatus.valueOf(orderStatus);
	}
	
	//pass a parater a OrderStatus, and get the code (integer of the OrderStatus passed in the parameter.
	public void setOrderStatus(OrderStatus orderStatus)
	{
		if (orderStatus != null)
		{
			this.orderStatus = orderStatus.getCode();

		}
	}
	
	public Set<OrderItem> getItems()
	{
		return items;
	}
	
	

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public int hashCode() {
		 final int prime = 31;
		 int result =  1;
		 result = prime * result + ((id == null) ? 0 : id.hashCode());
		 return result;
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
		return Objects.equals(id, other.id);
	}
	

}
