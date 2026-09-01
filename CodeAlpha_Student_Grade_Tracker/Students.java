package com.example.student_grade_tracker;

public class Students {
    
    String name;
    double grade;

// Constructor
    public Students(String name, double grade){
        this.name = name;
        this.grade = grade;
    }

    // Method 1
    public String getName(){
        return name;
    }

    // Method 2
    public double getGrade(){
        return grade;
    }
}
