package com.minsait.order_service.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders") // "order" es palabra reservada en SQL, así que usamos "orders"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String customerName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "order_id")  // clave foránea en ProductOrder
  /* Actualmente estás usando una relación @OneToMany hacia Product,
     lo cual no representa correctamente una relación muchos a muchos,
     que es lo que necesitas para reflejar que:
     Un pedido puede contener varios productos
     Y un producto puede pertenecer a varios pedidos
  * */

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    // Getters y setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
