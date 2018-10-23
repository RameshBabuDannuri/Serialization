package Serialization;

import java.io.Serializable;

public class Customer implements Serializable {
    // static data member
    static transient int customerCount = 2;
    transient final int id;
    private String name;
    private int age;
    private transient String password;


    public Customer(int id, String name, int age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password  = password;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age= " + age +
                ", password= "+password+
                ", customerCount="+customerCount+
                '}';
    }
}
