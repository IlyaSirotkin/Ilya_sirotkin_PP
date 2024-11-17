import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//ln((1+x)/(1-x)) with BigDecimal
//Сироткин Илья 5 группа 2 курс
public class Main {
    public static void main(String[] args) {
        try (BufferedReader cin = new BufferedReader(new InputStreamReader(System.in))) {
            String str=cin.readLine();
            String[] words=str.split(" ");
            BigDecimal x=BigDecimal.valueOf(Double.parseDouble( words[0])), k=BigDecimal.valueOf(Integer.parseUnsignedInt(words[1]));
            if(x.abs().doubleValue()<1 || k.intValue()<=0) {
                final BigDecimal e = BigDecimal.valueOf(Math.pow(0.1, k.intValue()));
                int i = 3;
                BigDecimal sum, d;
                sum = d = x;
                while (d.abs().compareTo(e)>0) {
                    d=x.pow(i).divide((new BigDecimal(i)), new MathContext(16, RoundingMode.HALF_UP));
                    sum=sum.add(d, new MathContext(16, RoundingMode.HALF_UP));
                    i += 2;
                }
                System.out.println(Math.log((1 + x.doubleValue()) / (1 - x.doubleValue())));
                System.out.println(sum.multiply(new BigDecimal(2)));
            }else System.out.println("Abs(X) more than 1 or k less rhan 0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}