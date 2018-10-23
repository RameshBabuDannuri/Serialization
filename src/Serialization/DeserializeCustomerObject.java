package Serialization;

import java.io.*;

public class DeserializeCustomerObject {
    public static void main(String args[]){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        Customer customer = null;
        try {
            fileInputStream = new FileInputStream("Customer.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);
            customer = (Customer)objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Customer object de-serialized from "
                + "Customer.ser file\nLet's print to console... \n");

        // printing customer object to console using toString() method
        System.out.println(customer);

    }
}
