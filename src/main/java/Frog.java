import java.util.Arrays;

public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    private final char frogTag = '\u0284';
    private final char emptyField = '_';
    protected char[] gameField = {emptyField,
            emptyField,
            emptyField,
            emptyField,
            emptyField,
            frogTag,
            emptyField,
            emptyField,
            emptyField,
            emptyField,
            emptyField};

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        if (steps + position < MIN_POSITION) {
            System.out.println("Лягушка выпрыгнет из лужи! Выберите другую дистанцию прыжка");
            return false;
        } else if (steps + position > MAX_POSITION) {
            System.out.println("Лягушка выпрыгнет из лужи! Выберите другую дистанцию прыжка");
            return false;
        } else {
            position = position + steps;
            return true;
        }
    }

    public void printPositionOnField() {
        for (int i = 0; i <= gameField.length; i++) {
            if (gameField[i] == frogTag) {
                gameField[i] = emptyField;
                break;
            }
        }
        gameField[position] = frogTag;
        System.out.println(Arrays.toString(gameField));
    }
}