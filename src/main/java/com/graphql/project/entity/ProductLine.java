package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name ="ProductLine.findProductLineByImage",
                        query = "SELECT p FROM ProductLine p WHERE p.image = :image"),
                @NamedQuery(name = "ProductLine.findProductLineAll",
                        query ="SELECT p FROM ProductLine p LEFT JOIN FETCH p.products" )
        }
)
public class ProductLine {

    @Id
    @GeneratedValue
    private int productLineId;
    private String descInText;
    private String descInHTML;
    @Column(
            length = 100
    )
    private String image;
    @OneToMany(mappedBy = "productLineId",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;

    public ProductLine(String descInText, String descInHTML, String image) {
        this.descInText = descInText;
        this.descInHTML = descInHTML;
        this.image = image;
    }

    public ProductLine() {
    }

    public int getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(int productLineId) {
        this.productLineId = productLineId;
    }

    public String getDescInText() {
        return descInText;
    }

    public void setDescInText(String descInText) {
        this.descInText = descInText;
    }

    public String getDescInHTML() {
        return descInHTML;
    }

    public void setDescInHTML(String descInHTML) {
        this.descInHTML = descInHTML;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
