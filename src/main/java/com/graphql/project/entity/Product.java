package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name="Product.findProductByVendor ", query = "SELECT p FROM Product p  WHERE p.vendor = :vendor")
        }
)
public class Product {
    @Id
    @GeneratedValue
    private int productCode;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "id"
    )
    private ProductLine productLineId;
    private String name;
    private String vendor;
    private  String pdtDescription;
    private int scale;
    private int qntyInStock;
    @Column(
            precision = 19,
            scale = 0
    )
    private BigDecimal buyPrice;
    private String MSRP;

    @OneToMany(mappedBy = "ProductId")
    @JsonManagedReference
    private List<Order_Product> orderProducts;


    public Product(int productCode,
                   ProductLine productLineId, String name,
                   String vendor,
                   String pdtDescription, int scale,
                   int qntyInStock, BigDecimal buyPrice,
                   String MSRP,
                   List<Order_Product> orderProducts) {
        this.productCode = productCode;
        this.productLineId = productLineId;
        this.name = name;
        this.vendor = vendor;
        this.pdtDescription = pdtDescription;
        this.scale = scale;
        this.qntyInStock = qntyInStock;
        this.buyPrice = buyPrice;
        this.MSRP = MSRP;
        this.orderProducts = orderProducts;
    }

    public Product(ProductLine productLine_id, String name, String vendor, String pdtDescription, int qntyInStock, BigDecimal buyPrice, String MSRP) {
        this.productLineId = productLine_id;
        this.name = name;
        this.vendor = vendor;
        this.pdtDescription = pdtDescription;
        this.qntyInStock = qntyInStock;
        this.buyPrice = buyPrice;
        this.MSRP = MSRP;
    }

    public Product() {
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public ProductLine getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(ProductLine productLineId) {
        this.productLineId = productLineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getPdtDescription() {
        return pdtDescription;
    }

    public void setPdtDescription(String pdtDescription) {
        this.pdtDescription = pdtDescription;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getQntyInStock() {
        return qntyInStock;
    }

    public void setQntyInStock(int qntyInStock) {
        this.qntyInStock = qntyInStock;
    }

    public String getMSRP() {
        return MSRP;
    }

    public void setMSRP(String MSRP) {
        this.MSRP = MSRP;
    }

    public List<Order_Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Order_Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
