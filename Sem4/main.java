package Sem4;



public class main {

    public static void main(String[] args) {
    
        HashTable<Integer, Employee> ht = new HashTable<>();
        
        ht.add(1,new Employee("Иван", "Иванов", 31));
        ht.add(2,new Employee("Иван", "Петров", 32));
        ht.add(3,new Employee("Иван", "Сидоров", 33));
        ht.add(4,new Employee("Иван", "Смирнов", 34));
        ht.add(5,new Employee("Иван", "Бармалеев", 35));
        ht.add(6,new Employee("Петр", "Иванов", 36));
        ht.add(7,new Employee("Петр", "Петров", 37));
        ht.add(8,new Employee("Петр", "Сидоров", 38));
        
        
        System.out.println("\n" + ht);

        ht.add(9,new Employee("Петр", "Бармалеев", 31));
        ht.add(10,new Employee("Петр", "Пылесовов", 32));
        ht.add(11,new Employee("Сидр", "Иванов", 33));
        ht.add(12,new Employee("Сидр", "Петров", 34));
        ht.add(13,new Employee("Сидр", "Сидоров", 35));
        ht.add(14,new Employee("Сидр", "Бармалеев", 36));
        ht.add(15,new Employee("Сидр", "Пылесосов", 37));
        ht.add(16,new Employee("Тихон", "Иванов", 38));
        ht.add(17,new Employee("Борис", "Иванов", 39));
        ht.add(16,new Employee("Огурец", "Иванов", 1));
        ht.add(17,new Employee("Помидор", "Иванов", 1));

        System.out.println("\n" + ht);

    }
 }
