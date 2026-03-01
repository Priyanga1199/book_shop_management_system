package com.example.BookShopApp;
import org.hibernate.validator.constraints.Range; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity
public class Book 
{
	@Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Please Enter Book Title") 
	private String title; 
	@NotNull(message="Please Enter Book Author Name")
	private String author;
	@NotNull(message="Please Enter Book Price")
	@Range(min=100,max=10000,message="Invalid Price")
	private double price;
	@NotNull(message="Please Enter Book Quantity")
	@Range(min=0,max=100,message="Invalid Quantity")
	private int quantity;
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id; 
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getAuthor() 
	{
		return author;
	}
	public void setAuthor(String author)
	{
	    this.author = author; 
	}
	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public int getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
