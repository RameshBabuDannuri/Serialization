package Serialization;

import java.io.*;

class Customer1 implements Serializable {

    // member variables for Customer
    int customerId;
    String customerName;

    // 2-arg parameterized constructor for Customer
    public Customer1(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId
                + ", customerName=" + customerName + "]";
    }
}
class Employee implements Serializable {

    // member variables for Employee
    int employeeId;
    String employeeName;

    // 2-arg parameterized constructor for Employee
    public Employee(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId
                + ", employeeName=" + employeeName + "]";
    }
}
class Student implements Serializable {

    // member variables for Student
    int studentId;
    String studentName;

    // 2-arg parameterized constructor for Student
    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId
                + ", studentName=" + studentName + "]";
    }
}
//Program 1: When order of serialization is known, we can de-serialize in same order
public class OrderOfSerializationDemo1 {
    public static void main(String[] args) {

        Customer1 customer = new Customer1(101, "Jeremy Krist");
        Employee employee = new Employee(111, "Mike Gentry");
        Student student = new Student(121, "Azeem Sayed");

        // creating output stream variables
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating customer object reference
        // to hold values after de-serialization
        Customer1 deSerializeCustomer = null;
        Employee deSerializeEmployee = null;
        Student deSerializeStudent = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("OrderOfObjects.ser");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving customer object's value to stream
            oos.writeObject(customer);
            oos.writeObject(employee);
            oos.writeObject(student);
            oos.flush();
            oos.close();

            System.out.println("Serialization: All objects "
                    + "saved to OrderOfObjects.ser file\n");

            // reading binary data
            fis = new FileInputStream("OrderOfObjects.ser");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // reading object's value and casting to respective class
            deSerializeCustomer = (Customer1 ) ois.readObject();
            deSerializeEmployee = (Employee) ois.readObject();
            deSerializeStudent = (Student) ois.readObject();
            ois.close();

            System.out.println("De-Serialization: All objects "
                    + "de-serialized from OrderOfObjects.ser file\n");
        }
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }

        // printing customer object to console using toString() method
        System.out.println("Printing values "
                + "from de-serialized object... \n");
        System.out.println(deSerializeCustomer);
        System.out.println(deSerializeEmployee);
        System.out.println(deSerializeStudent);
    }
}
/*
* Program 2: De-serialization is done in different than serialization order
------------------------------------------------------------------------------------
In this program, irrespective of whether we know serialization order or NOT, we will perform de-serialization 
in some random order



Let’s see what happens, if we change the de-serialization order (other than from serialization order)

Serializing order

Customer
Employee
Student
De-Serializing order

Student
Customer
Employee


OrderOfSerializationDeSerialization.java



package in.bench.resources.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OrderOfSerializationDeSerialization {

    public static void main(String[] args) {

        Customer customer = new Customer(101, "Jeremy Krist");
        Employee employee = new Employee(111, "Mike Gentry");
        Student student = new Student(121, "Azeem Sayed");

        // creating output stream variables
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating customer object reference
        // to hold values after de-serialization
        Customer deSerializeCustomer = null;
        Employee deSerializeEmployee = null;
        Student deSerializeStudent = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("OrderOfObjects.ser");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving customer object's value to stream
            oos.writeObject(customer);
            oos.writeObject(employee);
            oos.writeObject(student);
            oos.flush();
            oos.close();

            System.out.println("Serialization: All objects "
                    + "saved to OrderOfObjects.ser file\n");

            // reading binary data
            fis = new FileInputStream("OrderOfObjects.ser");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // reading object's value and casting to respective class
            deSerializeStudent = (Student) ois.readObject();
            deSerializeCustomer = (Customer) ois.readObject();
            deSerializeEmployee = (Employee) ois.readObject();
            ois.close();

            System.out.println("De-Serialization: All objects "
                    + "de-serialized from OrderOfObjects.ser file\n");
        }
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }

        // printing customer object to console using toString() method
        System.out.println("Printing values"
                + " from de-serialized object... \n");
        System.out.println(deSerializeCustomer);
        System.out.println(deSerializeEmployee);
        System.out.println(deSerializeStudent);
    }
}
Output:

Serialization: All objects saved to OrderOfObjects.ser file

Exception in thread "main" java.lang.ClassCastException: in.bench.resources
.serialization.Customer cannot be cast to
in.bench.resources.serialization.Student
    at in.bench.resources.serialization.OrderOfSerializationDeSerialization
.main(OrderOfSerializationDeSerialization.java:109)


Explanation:

Here serialization order is Customer –> Employee –> Student
But we are de-serializing in different order i.e.; Student –> Customer –> Employee
So, while de-serializing 1st time when we read object from serialized file, it returns Customer object, as we
serialized Customer object first
But instead of type-casting to Customer object, we type-casted to Student object –> which results in throwing 
java.lang.ClassCastException
To overcome this exception, we can use instanceOf operator
Move to program 3 –> for much improved version using instanceOf operator


 Program 3: When order of serialization is unknown, how can we overcome this situation?
 =========================================================================================================
We can use instanceOf operator to check the respective object first iterating through while loop

Later, we can assign it to correct class by type-casting

Note: here, program will throw java.io.EOFException for condition checked inside parenthesis of while loop

But we can catch this exception and take corrective action (like here, we can print “End of file message” to console)

OrderOfSerializationDeSerialization.java


package in.bench.resources.serialization;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OrderOfSerializationDeSerialization {

    public static void main(String[] args) throws IOException {

        Customer customer = new Customer(101, "Jeremy Krist");
        Employee employee = new Employee(111, "Mike Gentry");
        Student student = new Student(121, "Azeem Sayed");

        // creating output stream variables
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        // creating input stream variables
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // creating customer object reference
        // to hold values after de-serialization
        Customer deSerializeCustomer = null;
        Employee deSerializeEmployee = null;
        Student deSerializeStudent = null;

        try {
            // for writing or saving binary data
            fos = new FileOutputStream("OrderOfObjects.ser");

            // converting java-object to binary-format
            oos = new ObjectOutputStream(fos);

            // writing or saving customer object's value to stream
            oos.writeObject(customer);
            oos.writeObject(employee);
            oos.writeObject(student);
            oos.flush();
            oos.close();

            System.out.println("Serialization: All objects "
                    + "saved to OrderOfObjects.ser file\n");

            // reading binary data
            fis = new FileInputStream("OrderOfObjects.ser");

            // converting binary-data to java-object
            ois = new ObjectInputStream(fis);

            // temp Object variable
            Object object = null;

            // iterating, reading & casting to respective class
            while((object = ois.readObject()) != null){
                if(object instanceof Customer)
                    deSerializeCustomer = (Customer) object;
                else if(object instanceof Employee)
                    deSerializeEmployee = (Employee) object;
                else if(object instanceof Student)
                    deSerializeStudent = (Student) object;
            } // END of while loop
        }
        catch (EOFException eofex) {
            // eofex.printStackTrace();
            System.out.println("De-Serialization: All objects "
                    + "de-serialized from OrderOfObjects.ser file\n");
            System.out.println("End of file reached...\n");
        }
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        catch (ClassNotFoundException ccex) {
            ccex.printStackTrace();
        }
        finally {
            ois.close(); // closing stream
        }

        // printing customer object to console using toString() method
        System.out.println("Printing values "
                + "from de-serialized object... \n");
        System.out.println(deSerializeCustomer);
        System.out.println(deSerializeEmployee);
        System.out.println(deSerializeStudent);
    }
}

Serialization: All objects saved to OrderOfObjects.ser file

De-Serialization: All objects de-serialized from OrderOfObjects.ser file

End of file reached...

Printing values from de-serialized object...

Customer [customerId=101, customerName=Jeremy Krist]
Employee [employeeId=111, employeeName=Mike Gentry]
Student [studentId=121, studentName=Azeem Sayed]


Important points about Serialization Order:
Rule 1: all classes that need to be serialized must implement java.io.Serializable interface
Order of Serialization is very important to know, because we need to follow the same order while de-serializing the objects
If the Order of Serialization is unknown, then it may throw java.lang.ClassCastException
To overcome ClassCastException, we can 1st check type of object using instanceOf operator and then assign it to proper class after doing necessary type-casting
Exception: iterating through while loop may throw EOFException, we need catch this exception and handle it properly



* */
