package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1,message = "*Book name is required")
    @Column(name = "book")
    private String book;

    @PositiveOrZero(message = "*You entered a negative number")
    @Column(name = "num")
    private int num;

    @PositiveOrZero(message = "*You entered a negative number")
    @Column(name="price")
    private int price;

    @Lob
    @Column(name = "image")
    private String img;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_categorization")
    private Categorization catBook;


    @ManyToMany
    @JoinTable(name = "user_carts",
            joinColumns = @JoinColumn(name = "id_library"),
            inverseJoinColumns = @JoinColumn(name = "id_shopping")
    )
    private List<ShoppingCart> shoppingCarts;

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Library() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Categorization getCatBook() {
        return catBook;
    }

    public void setCatBook(Categorization catBook) {
        this.catBook = catBook;
    }

    public void getImgBook2(String imgBook) {
    }
}