package StudentInfoManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        loop : while (true) {
            System.out.println("-------Welcome To Student Information Management System-------");
            System.out.println("Please select your option -> ");
            System.out.println("1: Add a new Student");
            System.out.println("2: Delete a student");
            System.out.println("3: Update a student`s info");
            System.out.println("4: View all student`s info");
            System.out.println("5: Exit.");

            Scanner sc = new Scanner(System.in);
            String option = sc.next();
            switch (option) {
               case "1" -> addStudent(list);
               case "2" -> deleteStudent(list);
               case "3" -> updateStudent(list);
               case "4" -> queryStudent(list);
               case "5" -> {
                   System.out.println("Exit");
                   //break loop;
                   System.exit(0); //Stop JVM
               }
                default -> System.out.println("Error: Negative option ");
            }
        }
    }

    //add a student
    public static void addStudent(ArrayList<Student> list){
        // create a student object with null parameter constructor
        Student s = new Student();

        Scanner sc = new Scanner(System.in);
        String id = null;
        while (true) {
            System.out.println("Please input student ID: ");
            id = sc.next();
            boolean flag = contains(list, id);
            if (flag){
                //id exists, needs another input
                System.out.println("ID already exist，Please try again: ");
            }else {
                //id do not exist，value can be set
                s.setId(id);
                break;
            }
        }


        System.out.println("Please input student`s name: ");
        String name = sc.next();
        s.setName(name);

        System.out.println("Please input student`s age: ");
        int age = sc.nextInt();
        s.setAge(age);

        System.out.println("Please input student`s home address: ");
        String address = sc.next();
        s.setAddress(address);

        // add student obj to the arrayList.
        list.add(s);

        //alert user
        System.out.println("Student info added -> ");
    }

    // delete a student
    public static void deleteStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a student ID for deletion: ");
        String id = sc.next();
        // query id index in arrayList.
        int index = getIndex(list, id);
        // if index >= 0, means exist, continue deletion.
        // if -1，means id none exist，break out from the method，and return to menu.
        if (index >= 0){

            list.remove(index);
            System.out.println("Student ID "+ id + " has been successfully deleted.");
        }else {
            System.out.println("ID none exist，deletion failed.");
        }
    }

    //update
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a student ID for update: ");
        String id = sc.next();


        int index = getIndex(list, id);

        if (index == -1){
            System.out.println("Student ID" + id+ " does not exist, please try again.");
            return; // break out
        }
        // The ID exists if the code has reached this part.
        // acquire student obj for update
        Student stu = list.get(index);

        // input info as required for update
        System.out.println("Please input student`s name for update: ");
        String newName = sc.next();
        stu.setName(newName);

        System.out.println("Please input student`s age for update: ");
        int newAge = sc.nextInt();
        stu.setAge(newAge);

        System.out.println("Please input student`s home address for update: ");
        String newAddress = sc.next();
        stu.setAddress(newAddress);

        System.out.println("Student info update successful. ");
    }

    // query student
    public static void queryStudent(ArrayList<Student> list){

        if (list.size() == 0) {
            System.out.println("Empty space ，please query after insertions. ");
            //break out
            return;
        }
        // print header
        System.out.println("ID\tName\tAge\tHome address");
        // ArrayList is not empty if the code has reached here.
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t"
                    + stu.getName() + "\t"
                    + stu.getAge() + "\t"
                    +stu.getAddress());
        }
        
    }

    // determine whether ID exists in the arrayList
    public static boolean contains(ArrayList<Student> list, String id) {
        // Loop through every student obj in the arrayList
        // Compare desired ID with existing ID.
        /*for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sid = stu.getId();
            if (sid.equals(id) ){
                return true;
            }
        }
        return false;*/
        return getIndex(list, id) >= 0;
    }

    // a method to acquire index with ID
    public static int getIndex(ArrayList<Student> list, String id){
        // loop through the arrayList
        for (int i = 0; i < list.size(); i++) {
            // acquire every student obj
            Student stu = list.get(i);
            // acquire id of every student obj
            String sid = stu.getId();
            // Compare desired query ID with existing ID in the arrayList
            if(sid.equals(id)){
                // if true, then return the index
                return i;
            }
        }
        // if index wasn`t found after the loop through
        // id none exist, return -1
        return -1;
    }
}
