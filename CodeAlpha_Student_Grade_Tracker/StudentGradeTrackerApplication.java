package com.example.student_grade_tracker;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentGradeTrackerApplication {

    public static ArrayList<Students> students = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

	try{
        while (true){
            System.out.println("\n\n|| STUDENT GRADE TRACKER ||");
            System.out.println();
            System.out.println("1. Add Students and Grades");
            System.out.println("2. View List of Students and Grades");
            System.out.println("3. View Summary (Highest, Lowest, Average)");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1: 
                    addStudent();
					sc.nextLine();
                    break;

                case 2:
                    viewStudent();
					sc.nextLine();
                    break;

                case 3:
                    viewSummary();
					sc.nextLine();
                    break;

                case 4:
                    System.out.println("\nProgram Exited\nThank you.\n");
                    return;

                default:
                    System.out.println("Enter Valid Choice (1-4)");
					sc.nextLine();
                    break;
            }
        }
	} catch (Exception e){
		System.out.println("\nNo string Value. Only choose (1-4)\nExited the program. Try later\n");
	}

    }

    public static void addStudent(){
        System.out.println("\n|| Add Students and Grades ||\n");
        System.out.print("Enter Student's Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Student's Grade: ");
        double grade = sc.nextDouble();
        sc.nextLine(); // Consumes newline after nextDouble()
        students.add(new Students(name, grade));
        System.out.println("Added Successfully");
    }

    public static void viewStudent(){
        System.out.println("\n|| Students's List ||\n");
        if(students.isEmpty()){
            System.out.println("No Students are found !");
            return;
        }
        
        for(int i = 0; i < students.size(); i++){
            Students s = students.get(i);
            System.out.println((i + 1) + ". Name: " + s.getName() + "\n   Marks: " + s.getGrade());
        }
    }

    public static void viewSummary(){
        System.out.println("\n|| Students Summary ||\n");
        
        
        if(students.isEmpty()){
            System.out.println("No Students are found !");
			sc.nextLine();
            return;
        }

        double total = 0;
        double highestgrade = students.get(0).getGrade();
        String higheststudent = students.get(0).getName();

        double lowestgrade = students.get(0).getGrade();
        String loweststudent = students.get(0).getName();

        for (int i = 0; i < students.size(); i++) {
            Students s = students.get(i);
            total = total + s.getGrade();

            if(s.getGrade() > highestgrade){
                highestgrade = s.getGrade();
                higheststudent = s.getName();
            }

           
            if(s.getGrade() < lowestgrade){
                lowestgrade = s.getGrade();
                loweststudent = s.getName();
            }
        }

        double average = total / students.size();

        System.out.println("The Student who got the highest grade");
        System.out.println("Name: " + higheststudent);
        System.out.println("Grade/Score: " + highestgrade);
        System.out.println();
        System.out.println("The Student who got the lowest grade");
        System.out.println("Name: " + loweststudent);
        System.out.println("Grade/Score: " + lowestgrade);
        System.out.println();
        System.out.println("The Average grade of students: " + average);
    }
}