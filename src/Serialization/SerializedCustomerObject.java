package Serialization;

import java.io.*;

public class SerializedCustomerObject {
    public static void main(String args[]){
        Customer customer = new Customer(1,"ramesh",24,"rammi143");
        FileOutputStream fileOutputStream= null;
        ObjectOutputStream objectoutputStream = null;


        try {
            fileOutputStream = new FileOutputStream("Customer.ser");
            objectoutputStream  = new ObjectOutputStream(fileOutputStream);
            objectoutputStream.writeObject(customer);
            objectoutputStream.flush();
            objectoutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Customer object saved to Customer.ser file");

    }
}
