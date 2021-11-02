import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    static private int curCommand = -1;
    static private List<FrogCommand> commands = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Frog frog = new Frog();

        while (true) {
            System.out.println(
                    """
                            Выберите действие:
                            N - прыжок\s
                            << - отменить последнее действие
                            >> - повторить отмененное действие
                            !! - повторить последний прыжок
                            0 - выход
                            """
            );
            String input = scanner.nextLine();
            if (input.equals("0")) break;

            switch (input) {
                case "<<":
                    if (curCommand < 0) {
                        System.out.println("Нечего отменять!");
                    } else {
                        commands.get(curCommand).undo();
                        curCommand--;
                    }
                    break;
                case ">>":
                    if (curCommand + 1 == commands.size()) {
                        System.out.println("Последнее действие не отменено!");
                    } else {
                        curCommand++;
                        commands.get(curCommand).doit();
                    }
                    break;
                case "!!":
                    if (curCommand + 1 != commands.size() || curCommand == -1) {
                        System.out.println("Нечего повторять!");
                    } else {
                        clearJumpHistory();
                        if (commands.get(curCommand).doit()) {
                            commands.add(commands.get(curCommand));
                            curCommand++;
                        }
                    }
                    break;
                default:  //пользователь ввёл новое движение для лягушки
                    //удаляем все команды которые были отменены
                    clearJumpHistory();
                    System.out.println("Введите кол-во клеток поля для прыжка");
                    int steps = Integer.parseInt(scanner.nextLine());
                    FrogCommand cmd = FrogCommands.jumpRightCommand(frog, steps);
                    if (cmd.doit()) {
                        commands.add(cmd);
                        curCommand++;
                    }
                    break;
            }
            frog.printPositionOnField();
        }
    }

    public static void clearJumpHistory() {
        for (ListIterator<FrogCommand> iterator = commands.listIterator(); iterator.hasNext(); ) {
            iterator.next();
            if (iterator.nextIndex() > curCommand + 1) {
                iterator.remove();
            }
        }
    }
}

