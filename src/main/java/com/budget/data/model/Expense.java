package com.budget.data.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="expenses")
public class Expense {

	@Id
	@Column(name="expe_id", unique=true, nullable=false, columnDefinition="INTEGER")
	@SequenceGenerator(name="expe_seq", sequenceName="expe_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="expe_seq")
	private long id;
	
	@Column(name="oper_dt", columnDefinition="DATE")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Calendar date;
	
	@JoinColumn(name="item_id")
	@ManyToOne
	private Item item;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	@Column(name="expe_item_cn", columnDefinition="DECIMAL(10,3)")
	private BigDecimal count;
	
	@Column(name="expe_pric_am", columnDefinition="DECIMAL(10,2)")
	private BigDecimal price;
	
	@Column(name="expe_comm_tx")
	private String comment;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Expense other = (Expense) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
