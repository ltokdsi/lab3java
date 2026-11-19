package ru.rechkalova.tree;

public class Node {
    private Integer value;
    private Node parent;
    private Node left;
    private Node right;

    // Конструктор без аргументов
    public Node() {
    }

    public Integer getValue() { return value; }
    public Node getParent() { return parent; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }

    // Добавление значения
    public void add(int val) {
        if (this.value == null) {
            this.value = val;
            return;
        }
        if (val > this.value) {
            if (this.right == null) {
                this.right = new Node();
                this.right.parent = this;
            }
            this.right.add(val);
        } else {
            if (this.left == null) {
                this.left = new Node();
                this.left.parent = this;
            }
            this.left.add(val);
        }
    }

    // Проверка, есть ли значение в дереве
    public boolean contains(int val) {
        if (this.value == null) return false;
        if (this.value == val) return true;
        if (val > this.value) {
            return right != null && right.contains(val);
        } else {
            return left != null && left.contains(val);
        }
    }

    // Удаление значения (потомки остаются прикрепленными)
    public boolean remove(int val) {
        if (this.value == null) return false;

        if (val < this.value) {
            return left != null && left.remove(val);
        } else if (val > this.value) {
            return right != null && right.remove(val);
        } else {
            // Нашли узел, который надо удалить
            if (left != null && right != null) {
                // Если есть оба потомка, берем минимальное значение справа
                Node minNode = right;
                while (minNode.left != null) {
                    minNode = minNode.left;
                }
                this.value = minNode.value;
                right.remove(minNode.value);
            } else if (left != null) {
                // Есть только левый потомок — переносим его на текущее место
                this.value = left.value;
                this.right = left.right;
                if (this.right != null) this.right.parent = this;
                this.left = left.left;
                if (this.left != null) this.left.parent = this;
            } else if (right != null) {
                // Есть только правый потомок — переносим его на текущее место
                this.value = right.value;
                this.left = right.left;
                if (this.left != null) this.left.parent = this;
                this.right = right.right;
                if (this.right != null) this.right.parent = this;
            } else {
                // Потомков нет (лист) — просто отвязываем от родителя
                if (parent != null) {
                    if (parent.left == this) parent.left = null;
                    else if (parent.right == this) parent.right = null;
                } else {
                    this.value = null;
                }
            }
            return true;
        }
    }

    // Левосторонний обход в глубину (выведет все числа по порядку)
    @Override
    public String toString() {
        if (value == null) return "";
        StringBuilder sb = new StringBuilder();
        if (left != null) sb.append(left.toString());
        sb.append(value).append(" ");
        if (right != null) sb.append(right.toString());
        return sb.toString();
    }
}
