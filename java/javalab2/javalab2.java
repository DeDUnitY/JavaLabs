package javalab2;

import java.util.Objects;

public class javalab2 {

    // Метод для поиска максимальной подстроки без повторяющихся символов
    static public String SubStr(String str) {
        String answer = "";
        int buf[] = new int[255]; 

        for (char i = 'A'; i != 'z'; i++) {
            buf[(int) (i)] = 0;
        }
        int j = 0; 
        String tmp = ""; 

        for (int i = 0; i < str.length(); i++) {
            tmp += str.charAt(i);
            buf[(int) str.charAt(i)] += 1;

            if (buf[(int) str.charAt(i)] == 2) {
                if (str.substring(j, i).length() > answer.length()) {
                    answer = str.substring(j, i);
                }

                while (buf[(int) str.charAt(i)] != 1) {
                    buf[(int) str.charAt(j)] -= 1;
                    j++;
                }
                tmp = str.substring(j, i + 1);
            }
        }
        return answer;
    }

    // Метод для поиска максимальной суммы подмассива
    public static int MaxSubArraySum(int[] arr) {
        int ans = arr[0]; 
        int total = 0; 

        for (int i = 0; i < arr.length; i++) {
            if (total < 0) {
                total = 0; 
            }
            total += arr[i]; 
            ans = Math.max(ans, total); 
        }
        return ans;
    }

    // Метод для объединения двух массивов и их сортировки
    public static int[] ConcatenateArrays(int[] arr1, int[] arr2) {
        int new_len = arr1.length + arr2.length; 
        int[] answer = new int[new_len]; 
        int counter = 0; 

        for (int i = 0; i < arr1.length; i++) {
            answer[counter++] = arr1[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            answer[counter++] = arr2[i];
        }

        for (int i = 0; i < new_len; i++) {
            for (int j = i + 1; j < new_len; j++) {
                if (answer[j] < answer[i]) {
                    int tmp = answer[j];
                    answer[j] = answer[i];
                    answer[i] = tmp;
                }
            }
        }

        return answer;
    }

     // Метод для поворота матрицы вправо на 90 градусов
     public static int[][] rotateMatrixRight(int[][] mat) {
        int[][] rotated = new int[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[i].length; j++)
                rotated[j][i] = mat[mat.length - i - 1][j];
        return rotated;
    }

    // Метод для поворота матрицы влево на 90 градусов
    public static int[][] rotateMatrixLeft(int[][] mat) {
        int[][] rotated = new int[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[i].length; j++)
                rotated[j][i] = mat[i][mat.length - j - 1];
        return rotated;
    }

    // Метод для поиска двух чисел в массиве, сумма которых равна заданному значению
    public static int[] TwoNumSum(int[] arr, int target) {
        int[] ans = new int[2]; 
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    ans[0] = arr[i];
                    ans[1] = arr[j];
                    break;
                }
            }
        }
        if (Objects.isNull(ans[0])) {
            return null;
        }
        return ans;
    }

    // Метод для вычисления суммы всех элементов двумерного массива
    public static int TwoArraySum(int[][] mat) {
        int sum = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }

    // Метод для поиска максимального числа в каждой строке двумерного массива
    public static int[] MaxNumInRow(int[][] mat) {
        int[] ans = new int[mat.length]; 
        int count = 0; 

        for (int i = 0; i < mat.length; i++) {
            int max = -999999; 
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] > max) {
                    max = mat[i][j]; 
                }
            }
            ans[count++] = max; 
        }
        return ans;
    }

        // Метод для печати двумерного массива
        public static void printMat(int[][] mat) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    System.out.print(mat[i][j] + " ");
                }
                System.out.print("\n");
            }
        }
        
    // Основной метод программы
    public static void main(String[] args) {
        int[][] mat = {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 16}};

        String str = "abcadefggge";
        String maxSub = SubStr(str);
        System.out.println("Задача 1: " + str + "Максимальная подстрака: " + maxSub);

        System.out.print("Задача 2: ");
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {10, 8, 6, 4, 2};
        int[] con = ConcatenateArrays(arr2, arr1);
        for (int i = 0; i < con.length; i++) {
            System.out.print(con[i] + " ");
        }
        System.out.println();

        int[] array = {-3, 10, -2, 1, 1, 5, -6};
        System.out.println("Задача 3: " + MaxSubArraySum(array));

        System.out.println("Задача 4: ");
        int[][] rotated = rotateMatrixRight(mat);
        printMat(rotated);

        int[] arr = {1, 2, 3, 9, 0, 0, 0, 1, 2, 3};
        int[] ans = TwoNumSum(arr, 12);
        System.out.println("Задача 5: " + ans[0] + " " + ans[1]);

        System.out.println("Задача 6: " + TwoArraySum(mat));

        int[] a = MaxNumInRow(mat);
        System.out.print("Задача 7: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        System.out.println("Задача 8:");
        int[][] rot = rotateMatrixLeft(mat);
        printMat(rot);
    }
}