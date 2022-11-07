package com.example.todorestapi.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

//JPAで使用するためのEntity
@Entity
@Table(name = "todo")
@Data
public class TaskEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id=null;

     @Column(name = "store")
    private String store=null;

     @Column(name = "price")
    private Integer price=null;  
}
