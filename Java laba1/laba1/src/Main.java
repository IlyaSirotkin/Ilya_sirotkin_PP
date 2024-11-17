import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//ln((1+x)/(1-x))
//Сироткин Илья 5 группа 2 курс
public class Main {
    public static void main(String[] args) {
        try (BufferedReader cin = new BufferedReader(new InputStreamReader(System.in))) {
            String str=cin.readLine();
            String[] words=str.split(" ");
            double x=Double.parseDouble( words[0]);
            int k=Integer.parseInt(words[1]);
            if(Math.abs(x)<1) {
                final double e = Math.pow(0.1, k);
                int i = 3;
                double sum, d;
                sum = d = x;
                while (Math.abs(d) > e) {
                    d = Math.pow(x, i) / i;
                        sum += d;

                    i += 2;
                }
                System.out.println(Math.log((1 + x) / (1 - x)));
                System.out.println(2 * sum);
            }else System.out.println("Abs(X) more than 1 ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}