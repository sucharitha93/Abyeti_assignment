package org.abyeti.productapp;

/**
 * Created by Sucharitha Reddy on 2/3/2015.
 */
public class Orders {
    private String id;
    private Buyer b;
    private Product p;
    private Seller s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Buyer getB() {
        return b;
    }

    public void setB(Buyer b) {
        this.b = b;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public Seller getS() {
        return s;
    }

    public void setS(Seller s) {
        this.s = s;
    }


}
