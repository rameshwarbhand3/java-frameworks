package com.luv2code.crudDemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    //define fields
    //add constructor
    //add getter and setter
    //add toString method
    //annotate the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    //Do not use cascadeType.All because we don't want to remove instructor ,if we use All the instructor also deleted.
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    //This is easily find corresponding instructor
    private Instructor instructor;

    public Course(String title) {
        this.title = title;
    }

    public Course() {

    }

    public Course(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                '}';
    }


}
