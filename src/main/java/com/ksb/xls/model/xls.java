package com.ksb.xls.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "xls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class xls  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Published")
    private boolean published;

    // this specific method will run and give a formatted string
    @Override
    public String toString(){
        return "Excel [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }
}
