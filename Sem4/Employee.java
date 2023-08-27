package Sem4;

public class Employee {
    public String name;
    public String lastName;
    public Integer age;


    public Employee(String name, String lastName, Integer age){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString(){
        if(name == null || name == ""){name = " - ";}
        if(lastName == null || lastName == ""){lastName = " - ";}
        return "Employee: "+ name + " "+ lastName + " ("+ age +" лет)";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Employee castO = (Employee) o;
        if(!this.name.equals(castO.name)) return false;
        if(!this.lastName.equals(castO.lastName)) return false;
        if(!this.age.equals(castO.age)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();

        result = 31 * result + lastName.hashCode();
        result = 31 * result + age.hashCode();

        return result;
    }

    
}

