import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
public class Lab2 {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) throws IOException {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean b;
        String[] mask = new String[n];    //маска
        int[] prime = new int[n];   //пустой массив для хранения результатов первого пункта
        int j = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < a.length; i++) {    //заполнение маски
            b = true;
            if (a[i] == 1) mask[i] = "Единица";
            else{
                if (a[i] < 1) mask[i] = "Составное";
                else {
                    for (int k = 2; k*k <= a[i]; k++) {
                        if (a[i] % k == 0) {
                            mask[i] = "Составное";
                            b = false;
                            break;
                        }
                    }
                    if (b) mask[i] = "Простое";
                }
            }
        }
        for (int i = 0; i < a.length; i++) {   //подсчёт количества простых чисел в массиве и заполнение
                                               // результирующего массива простыми числами
            if ("Простое".equals(mask[i])) {
                prime[j] = a[i];
                j++;
            }
        }
        int l = j;
        for (int i = 0; i < a.length; i++) {   //заполнение результирующего массива не простыми числами
            if ("Составное".equals(mask[i]) || "Единица".equals(mask[i])) {
                prime[l] = a[i];
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
        for (int i : prime) out.print(i + " "); //вывод результирующего массива
        out.println("\n" + j);  //вывод количества простых чисел
        for (String i : mask) {   //вывод статуса каждого числа в изначальном массиве
            out.print(i + " ");
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
