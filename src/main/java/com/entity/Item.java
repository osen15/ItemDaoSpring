package com.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ITEMS")
public class Item {
    private Long id;
    private String name;
    private Date dateCreated;
    private Date lastUpdatedDate;
    private String description;

    @Id
    @SequenceGenerator(name = "I_SEQ", sequenceName = "ITEMS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "I_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "ITEM_NAME")
    public String getName() {
        return name;
    }


    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return dateCreated;
    }


    @Column(name = "LAST_UPDATED_DATE")
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(dateCreated, item.dateCreated) &&
                Objects.equals(lastUpdatedDate, item.lastUpdatedDate) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, dateCreated, lastUpdatedDate, description);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", description='" + description + '\'' +
                '}';
    }
}
