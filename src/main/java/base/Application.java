package base;

public class Application {
    public static void main(String[] args){

        Stack<Integer> stack = new Stack<>(10);
        System.out.println("Проверка на пустоту:");
        System.out.println(stack.empty());
        System.out.println("--------------");

        System.out.println("Добавление элементов: 1,2,3,4");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("--------------");

        System.out.println("Вывод элементов в порядке расположения");
        stack.printStack();
        System.out.println("--------------");

        System.out.println("Удаление элемента: ");
        stack.pop();
        stack.printStack();
        System.out.println("--------------");

    }
}
