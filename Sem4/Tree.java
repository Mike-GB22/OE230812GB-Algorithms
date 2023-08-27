//2023.08.27 Реализация Дерева
package Sem4;


public class Tree<V extends Comparable> {
    Node head;

    public class Node{
        V value;
        Node childRight;
        Node childLeft;

        public Node(V value){
            this.value = value;
        }

        @Override 
        public String toString(){
            StringBuilder result = new StringBuilder("Нода:   ");
            result.append(value);
            result.append("\nДети: ");
            
            if(this.childLeft != null) result.append(this.childLeft.value);
            result.append(" - ");
            if(this.childRight != null) result.append(this.childRight.value);

            return result.toString();
        }
    }

    // Добалвяем новую ноду в Дерево 
    public boolean add(V value){
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
            result = "(" + parent + ") -> " + child + "; ";
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
}
