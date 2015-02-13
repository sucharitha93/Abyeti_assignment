package org.abyeti.productapp;

/**
 * Created by Sucharitha Reddy on 2/5/2015.
 */
public class Orders {
    private int id;
    private int sid;
    private int pid;
    private int cid;
    private float price;
    private String pname;
    private String description;
    private String category;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", sid=" + sid +
                ", pid=" + pid +
                ", cid=" + cid +
                ", price=" + price +
                ", pname='" + pname + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
