package com.novikroman.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(name = "publication_of_date", nullable = false)
    private Date numberOfPage;

    @ManyToMany
    @JoinTable(
            name = "article_x_author",
            joinColumns = @JoinColumn(name = "id_article"),
            inverseJoinColumns = @JoinColumn(name = "id_author")
    )
    private Set<User> users;

    @OneToMany(mappedBy = "articles")
    private Set<Source> sources;

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Date getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Date numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public Set<User> getAuthors() {
        return users;
    }

    public void setAuthors(Set<User> users) {
        this.users = users;
    }

    public Set<Source> getSources() {
        return sources;
    }

    public void setSources(Set<Source> sources) {
        this.sources = sources;
    }
}
