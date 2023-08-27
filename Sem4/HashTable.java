//2023.08.27 Реализация Хэш таблицы
package Sem4;

import java.lang.reflect.Array;

public class HashTable<K, V> {

    int INT_BASKET_SIZE = 16;
    public static final double LOAD_FACTOR = 0.75;
    private int sizeHashTable = 0;
    private Basket[] baskets;

    // Конструктор нулевой
    public HashTable(){
        //baskets = new Basket()[INT_BASKET_SIZE];
        //baskets = (Basket[]) new Object[INT_BASKET_SIZE];
        //baskets = new Basket[16];
        baskets = (Basket[]) Array.newInstance(Basket.class, INT_BASKET_SIZE);
    }

    //-------------------------------------------------------
    // Один элемент нашей хэш таблицы
    private class Entry{
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return key + " = " + value;
        }
    }

    //-------------------------------------------------------
    // Корзина - сущность
    public class Basket{
        private Node head;

        private class Node{
            private Node next;
            private Entry entry;

            public Node(Entry entry){
                this.entry = entry;
            }
        }

        // Подсчет нод, в текущем бакете
        private int size(){
            int size = 0;
            if (head == null) return 0;

            Node currentNode = head;
            while (currentNode != null){
                size++;
                currentNode = currentNode.next;
            }
            return size;
        }

        // Поиск ноды, в текущем бакете
        public V find(K key){
            Node curretnNode = head;
            while (curretnNode != null){
                if(curretnNode.entry.key.equals(key)){
                    return curretnNode.entry.value;
                }
                curretnNode = curretnNode.next;
            }
            return null;
        }

        // Добавление ноды в текущий бакет
        public void add(Entry entry) {
            Node node = new Node(entry);
            if (head == null) {
                head = node;
                return;
            }
            Node currentNode = head;
            while(currentNode != null){
                if (currentNode.next == null){
                    currentNode.next = node;
                    return;
                } else {
                    currentNode = currentNode.next;
                }
            }
        }

        // Удаление ноды из текущего бакета
        public boolean remove(K key) {
            Node currentNode = head;
            Node previousNode = null;
            while (currentNode != null){
                if(currentNode.entry.key.equals(key)){
                    if (previousNode == null){
                        head = null;
                    } else {
                        previousNode.next = currentNode.next;
                    }
                    return true;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            return false;
        }
    }

//-------------------------------------------------------

    // Поиск элемента (возврат значения), в хэш таблице
    public V find(K key){
        int index = calculateBascketIndex(key);
        Basket basket = baskets[index];
        return basket.find(key);
    }

    // Добавление элемента, в хэш таблице
    public void add(K key, V value){
        add(new Entry(key, value));
    }

    public void add(Entry entry){
        //Entry entry = new Entry(key, value);
        int index = calculateBascketIndex(entry.key);
        if (baskets[index] == null){
            baskets[index] = new Basket();
        }
        baskets[index].add(entry);
        
        sizeHashTable++;
        if (needResize()) resize();
        return;
    }
 
    // Удалением элемента, из хэш таблици
    public void remove(K key){
        int index = calculateBascketIndex(key);
        if (baskets[index] == null) return;
        boolean flag = baskets[index].remove(key);
    }

    // Расчет индекса в массиве корзин
    private int calculateBascketIndex(K key){
        return Math.abs(key.hashCode()) % baskets.length;
    }

    // Узнаем количество элементов в хэш таблице
    private int size(){
        int size = 0;
        for (int i = 0; i < baskets.length; i++){
            if( baskets[i] != null)
                size += baskets[i].size();
        }
        return size;

    }

    // Проверяем, пора ли изменять количество бакетов
    private boolean needResize(){
        if (sizeHashTable*100/baskets.length > LOAD_FACTOR*100) return true;
        return false;
    }

    // Увеличиваем количество бакетов в 2 раза
    private void resize(){
        //Актуализируем размер
        sizeHashTable = size();
        if (!needResize()) return;

        int oldSize = baskets.length;
        int newSize = oldSize * 2;
        Basket[] oldBaskets = baskets;
        
        baskets = (Basket[]) Array.newInstance(Basket.class, newSize);
        sizeHashTable = 0;

        // Из старой хэш таблицы выбираем все корзины, а из них все элементы
        for (int i = 0; i < oldBaskets.length; i++){
            Basket currentOldBasket = oldBaskets[i];

            if(currentOldBasket != null){
                Basket.Node currentNode = currentOldBasket.head;
                while(currentNode != null){
                    // добравляем в новую хэш таблицу (новый массив корзин)
                    add(currentNode.entry);
                    currentNode = currentNode.next;
                }
            }
        }
        //Актуализируем размер
        sizeHashTable = size();
    }

    
    // Печат
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("Содержимое Хэш-Таблицы: \n");
        int count = 0;
        for(int i = 0; i < baskets.length; i++){
            if(baskets[i] == null) continue;
            result.append("Backet: " + i + " - ");

            Basket.Node currentNode = baskets[i].head;
            while (currentNode != null){
                count++;
                result.append("[");
                result.append(currentNode.entry);
                result.append("]");

                if(count < sizeHashTable) result.append(", ");

                currentNode = currentNode.next;
            }
            result.append("\n");
        }
        result.append("\nВсего бакетов: " + baskets.length);
        result.append(", всего элементов: " + size());
        return result.toString();
    }
}
