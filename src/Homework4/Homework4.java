package Homework4;
//
import java.util.Random;
import java.util.Scanner;

public class Homework4 {
    public static int SIZE = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    //improved method checkWin
    public static boolean checkWin(char symb) {
        int horizontal;
        int vertical;
        int firstDiag = 0;
        int secondDiag = 0;
        for (int i = 0; i < SIZE; i++) {
            horizontal = 0;
            vertical = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) {
                    horizontal++;
                    if (horizontal >= SIZE) {
                        return true;
                    }
                } else if (map[j][i] == symb) {
                    vertical++;
                    if (vertical >= SIZE) {
                        return true;
                    }
                }
            }

            if (map[i][i] == symb) {
                firstDiag++;
                if (firstDiag >= SIZE) {
                    return true;
                }
            } else {
                firstDiag = 0;
            }

            if (map[SIZE - 1 - i][i] == symb) {
                secondDiag++;
                if (secondDiag >= SIZE) {
                    return true;
                }
            } else {
                secondDiag = 0;
            }
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    //improved AI
    public static void aiTurn() {
        int x, y;
        int humanHorizontal = 0;
        int humanVertical = 0;
        int humanFirstDiag = 0;
        int humanSecondDiag = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    humanHorizontal++;
                }
                if (humanHorizontal >= SIZE - 1 && map[i][j] == DOT_EMPTY) {
                    for (int aiHorizontal = 0; aiHorizontal < SIZE; aiHorizontal++) {
                        if (map[i][aiHorizontal] == DOT_EMPTY) {
                            map[i][aiHorizontal] = DOT_O;
                            return;
                        }
                    }
                }

                if (map[j][i] == DOT_X) {
                    humanVertical++;
                }
                if (humanVertical >= SIZE - 1 && map[j][i] == DOT_EMPTY) {
                    for (int aiVertical = 0; aiVertical < SIZE; aiVertical++) {
                        if (map[aiVertical][i] == DOT_EMPTY) {
                            map[aiVertical][i] = DOT_O;
                            return;
                        }
                    }
                }
            }
            if (map[i][i] == DOT_X) {
                humanFirstDiag++;
            }
            if (humanFirstDiag >= SIZE - 1 && map[i][i] == DOT_EMPTY) {
                for (int aiFirstDiag = 0; aiFirstDiag < SIZE; aiFirstDiag++) {
                    if (map[aiFirstDiag][aiFirstDiag] == DOT_EMPTY) {
                        map[aiFirstDiag][aiFirstDiag] = DOT_O;
                        return;
                    }
                }
            }

            if (map[SIZE - 1 - i][i] == DOT_X) {
                humanSecondDiag++;
            }
            if (humanSecondDiag >= SIZE - 1 && map[i][i] == DOT_EMPTY) {
                for (int aiSecondDiag = 0; aiSecondDiag < SIZE; aiSecondDiag++) {
                    if (map[SIZE - 1 - i][aiSecondDiag] == DOT_EMPTY) {
                        map[SIZE - 1 - i][aiSecondDiag] = DOT_O;
                        return;
                    }
                }
            }
        }

        int gridCenter = (((SIZE + 1) / 2) - 1);
        if (map[gridCenter][gridCenter] == DOT_EMPTY) {
            map[gridCenter][gridCenter] = DOT_O;
            return;
        }

        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
}
