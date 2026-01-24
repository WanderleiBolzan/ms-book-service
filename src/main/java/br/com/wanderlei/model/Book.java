package br.com.wanderlei.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Entity(name="book")
public class Book implements Serializable {

    private static final Long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false, length = 180)
    private String author;
    @Column(nullable = false, length = 250)
    private String title;
    @Column(name="launch_date", nullable = false)
    @Temporal (TemporalType.DATE)
    private Date launchData;
    @Column(nullable = false)
    private Double price;
    @Transient
    private String currency;
    @Transient
    private String environment;

    public Book() {}

    public Book(Long id, String author, String title, Date launchData,
                Double price, String currency, String environment) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.launchData = launchData;
        this.price = price;
        this.currency = currency;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLaunchData() {
        return launchData;
    }

    public void setLaunchData(Date launchData) {
        this.launchData = launchData;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals (getId ( ), book.getId ( )) && Objects.equals (getAuthor ( ), book.getAuthor ( )) && Objects.equals (getTitle ( ), book.getTitle ( )) && Objects.equals (getLaunchData ( ), book.getLaunchData ( )) && Objects.equals (getPrice ( ), book.getPrice ( )) && Objects.equals (getCurrency ( ), book.getCurrency ( )) && Objects.equals (getEnvironment ( ), book.getEnvironment ( ));
    }

    @Override
    public int hashCode() {
        return Objects.hash (getId ( ), getAuthor ( ), getTitle ( ), getLaunchData ( ), getPrice ( ), getCurrency ( ), getEnvironment ( ));
    }
}
