//2023.08.27 Реализация Дерева
//Домашняя задача, Красно черное дерево
package Sem4;


public class TreeRedBlack<V extends Comparable> {
    Node head;

    public enum Color{
        RED, BLACK;
    }
    
    public class Node{
        V value;
        Node childRight;
        Node childLeft;
        Color color;

        public Node(V value){
            this.value = value;
            color = Color.RED;
        }

        @Override 
        public String toString(){
            StringBuilder result = new StringBuilder("Нода:   ");
            result.append(value);
            result.append(" (color: " + color + ")");
            result.append("\nДети: ");
            
            if(this.childLeft != null) result.append(this.childLeft.value);
            result.append(" - ");
            if(this.childRight != null) result.append(this.childRight.value);

            return result.toString();
        }
    }

    
    // Вход в добавление, и балансировку
    public boolean add(V value){
        boolean result = addElement(value);
        if(result) balance();
        return result;
    }
    
    // Добалвяем новую ноду в Дерево 
    public boolean addElement(V value){
        Node node = new Node(value);
        if(head == null) {
            head = node;
            return true;
        }
        Node currentNode = head;
        while(true){
            V currentNodeValue = currentNode.value;
            // Если ключ не уникальный, ни чего не добавляем
            if (currentNodeValue.equals(value)) return false;

            int compare = currentNodeValue.compareTo(value);
            
            if(compare > 0){
                if(currentNode.childLeft == null){
                    currentNode.childLeft = node;
                    return true;
                } else {
                    currentNode = currentNode.childLeft;
                    continue;
                }
            } else {
                if(currentNode.childRight == null){
                    currentNode.childRight = node;
                    return true;
                } else {
                    currentNode = currentNode.childRight;
                    continue;
                }
            }
        }
    }

    // Поиск элемента в дереве
    /**
     * @param curretnNode
     * @param value
     * @return
     */
    public Node find(V value){
        return find(head, value);
    }

    // Поиск бинарный
    public Node find(Node currentNode, V value){
        //Node currentNode = head;
        while(currentNode != null){
            if(currentNode.value.equals(value)) return currentNode;
            int compare = currentNode.value.compareTo(value);

            if(compare > 0){
                if(currentNode.childLeft == null) return null;
                currentNode = currentNode.childLeft;
                continue;
            } else {
                if(currentNode.childRight == null) return null;
                currentNode = currentNode.childRight;
                continue;
            }
        }
        return null;
    }

    // Рекурсивный поиск
    public Node findRecursiv(Node currentNode, V value){
        //Node currentNode = head;
        if(currentNode != null){
            if(currentNode.value.equals(value)) return currentNode;
            if(currentNode.childLeft != null){
                Node resultInLeft = findRecursiv(currentNode.childLeft, value);
                if(resultInLeft != null) return resultInLeft;
            }
            if(currentNode.childRight != null){
                Node resultInRight = findRecursiv(currentNode.childRight, value);
                if(resultInRight != null) return resultInRight;
            }
        }
        return null;
    }

    // Рекурсия 
    private String recursionToString(){
        if(head == null) return "- пусто -";
        return recursionToString(head, "Корень");

    }
    private String recursionToString(Node node, String parent){
        String result;
            String child = node.value.toString();
            result = "(" + parent + ") -> " + child + "[" + node.color + "]; ";
            if(node.childLeft != null) 
                result += recursionToString(node.childLeft, child);
            if(node.childRight != null) 
                result += recursionToString(node.childRight, child);
        return result;
    }

    @Override 
    public String toString(){
        StringBuilder result = new StringBuilder("Дерево: \n");
        result.append(recursionToString());    
        return result.toString();
    }

    private void balance(){
        if(head == null) return;
        //Корень всегда черный
        if(head.color == Color.RED) head.color = Color.BLACK;
        
        if(head.childLeft != null)
            balance1ChildRedIsRight(head.childLeft, head, false);
            balance2ChildIsRedUndItsChildIsRight(head.childLeft, head, false);
            balance3BeideChildrenAreRed(head.childLeft);
        
        if(head.childRight != null)
            balance1ChildRedIsRight(head.childRight, head, true);
            balance2ChildIsRedUndItsChildIsRight(head.childRight, head, true);
            balance3BeideChildrenAreRed(head.childRight);

    }

    //Проверка на условие: Правый ребенок - красный, левый - черный
    //Какую ноду мы оцениваем, ее родитель, как это это ребенок 0 - левый, 1 - правый
    private void balance1ChildRedIsRight(Node node, Node parent, boolean welcheChild){
        if(node != null && node.childRight != null && node.childLeft != null 
        && node.childRight.color == Color.RED 
        && node.childLeft.color == Color.BLACK){
            //Малый правый поворот
            Node x = node;
            Node y = node.childRight;
            Node beetween = y.childLeft;
            
            //делаем корнем поддерева вместо элемента Х, элемент У
            if(welcheChild) parent.childRight = y;
            else parent.childLeft = y;

            //переносим элемент Х в левое дитя элемента У
            y.childLeft = x;

            //переносим элемент beetween (бывший левый ребенок У) в правое дитя Х
            x.childRight = beetween;

            //Перекрашиваем
            x.color = Color.RED;
            y.color = Color.BLACK;

            //Recursia
            balance1ChildRedIsRight(node.childRight, node, true);
            balance1ChildRedIsRight(node.childLeft, node, false);
        }
    }
    
    //Проверка на условие: Левый ребенок - красный, и его левый ребенок - красный
    //Какую ноду мы оцениваем, ее родитель, как это это ребенок 0 - левый, 1 - правый
    private void balance2ChildIsRedUndItsChildIsRight(Node node, Node parent, boolean welcheChild){
        if(node != null && node.childLeft != null && node.childLeft.childLeft != null 
        && node.childLeft.color == Color.RED 
        && node.childLeft.childLeft.color == Color.RED){
            //Малый левый поворот
            Node y = node;
            Node x = node.childLeft;
            Node beetween = x.childRight;
            
            //делаем корнем поддерева вместо элемента У, элемент Х
            if(welcheChild) parent.childRight = x;
            else parent.childLeft = x;

            //переносим элемент У в правое дитя элемента Х
            x.childRight = y;

            //переносим элемент beetween (бывший правый ребенок Х) в левое дитя У
            y.childLeft = beetween;

            //Перекрашиваем
            x.color = Color.BLACK;
            y.color = Color.RED;

            //Recursia
            balance2ChildIsRedUndItsChildIsRight(node.childLeft, node, false);
            if(node.childLeft.childLeft != null)
                balance2ChildIsRedUndItsChildIsRight(node.childLeft.childLeft, node, false);
        }
    }
    
    //Проверка на условие: оба ребенка красные
    //Какую ноду мы оцениваем, ее родитель, как это это ребенок 0 - левый, 1 - правый
    private void balance3BeideChildrenAreRed(Node node){
        if(node != null && node.childRight != null && node.childLeft != null 
        && node.childRight.color == Color.RED 
        && node.childLeft.color == Color.RED ){
            //Смена цвета
            Node x = node;
            Node z = node.childLeft;
            Node y = node.childRight;
            
            //Перекрашиваем
            x.color = Color.RED;
            y.color = Color.BLACK;
            z.color = Color.BLACK;

            //Recursia
            balance3BeideChildrenAreRed(z);
            balance3BeideChildrenAreRed(y);
        }
    }
}
