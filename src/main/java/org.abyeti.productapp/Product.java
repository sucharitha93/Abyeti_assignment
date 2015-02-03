package org.abyeti.productapp;

public class Product {
	private String id;
	private String name;
	private String category;
	private String description;
	private String price;
	private String seller;
	Product(){}
	 public String getid()
	 {
		 return id;
	 }
	 public String getname()
	 {
		 return name;
	 }
	 public String getcategory()
	 {
		 return category;
	 }
	 public String getdescription()
	 {
		 return description;
	 }
	 public String getprice()
	 {
		 return price;
	 }
	 public String getseller()
	 {
		 return seller;
	 }
	 public void setid(String id)
	 {
		 this.id=id;
	 }
	 public void setname(String name)
	 {
		 this.name=name;
	 }
	 public void setcategory(String category)
	 {
		 this.category=category;
	 }
	 public void setdescription(String description)
	 {
		 this.description=description;
	 }
	 public void setprice(String price)
	 {
		 this.price=price;
	 }
	 public void setseller(String seller)
	 {
		 this.seller=seller;
	 }

	@Override
	public String toString() {
		return "Product{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", category='" + category + '\'' +
				", description='" + description + '\'' +
				", price='" + price + '\'' +
				", seller='" + seller + '\'' +
				'}';
	}

}


