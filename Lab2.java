import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
public class Lab2 {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static boolean isPrime(int a){   //функция проверки число на простое
        boolean res = true;
        if (a <= 1) res = false;
        else {
            for (int i = 2; i <= (int) Math.sqrt(a); i++) {
                if (a % i == 0) {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] prime = new int[n]; //пустой массив для хранения результатов первого пункта
        int j = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i : a) {   //подсчёт количества простых чисел в массиве и заполнение
                            // результирующего массива простыми числами
            if (isPrime(i)) {
                prime[j] = i;
                j++;
            }
        }
        int l = j;
        for (int i : a) {   //заполнение результирующего массива не простыми числами
            if (!isPrime(i)) {
                prime[l] = i;
                l++;
            }
        }
        for (int i = 0; i < j - 1; i++) {   //сортировка методом выбора первой части массива (простых чисел)
            int min = i;
            for (int k = i + 1; k < j; k++) {
                if (prime[min] > prime[k]) {
                    min = k;
                }
            }
            if (min != i) {
                int ram = prime[i];
                prime[i] = prime[min];
                prime[min] = ram;
            }
        }
        if (j != 0) {
            for (int i = j - 1; i < prime.length - 1; i++) {    //сортировка методом выбора второй части массива
                int max = i;
                for (int k = i + 1; k < prime.length; k++) {
                    if (prime[max] < prime[k]) {
                        max = k;
                    }
                }
                if (max != i) {
                    int ram = prime[i];
                    prime[i] = prime[max];
                    prime[max] = ram;
                }
            }
        }
        else {
            for (int i = j; i < prime.length; i++) {
                int max = i;
                for (int k = i + 1; k < prime.length; k++) {
                    if (prime[max] < prime[k]) {
                        max = k;
                    }
                }
                if (max != i) {
                    int ram = prime[i];
                    prime[i] = prime[max];
                    prime[max] = ram;
                }
            }
        }
        for (int i : prime) out.print(i + " "); //вывод результирующего массива
        out.println("\n" + j);  //вывод количества простых чисел
        for (int i : a) {   //вывод статуса каждого числа в изначальном массиве
            if(isPrime(i)) out.print("Простое ");
            else if (i == 1) out.print("Единица ");
                else out.print("Составное ");
        }
        for (int i = 0; i < a.length; i++) {    //замена каждого элемента его суммой цифр
            int sum = 0;
            while (a[i] != 0) {
                sum += Math.abs(a[i]%10);
                a[i] /= 10;
            }
            a[i] = sum;
        }
        out.print("\n");
        for (int i : a) out.print(i + " "); //вывод результирующего массива
    }
}