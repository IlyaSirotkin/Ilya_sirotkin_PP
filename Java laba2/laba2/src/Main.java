import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

//Сироткин Илья 5 группа задание 11 лаба 2 Java
public class Main {
    private static int n;
    private static int m;
    private static int[][] matx;
    private final static Random randomEng =new Random();
public static class Coordinates{
    Integer x;
    Integer y;

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
    private static int task11(){
        System.out.println();
    System.out.println("//////TASK 11///////");
    int norma=0;
        for (int i=0;i<n;++i){
            int sumOfLines=0;
            for (int j = 0; j < m; j++) {
                sumOfLines+=matx[i][j];
            }
            if(sumOfLines>norma) {
                norma = sumOfLines;
            }
        }
        return norma;
    }

    private static boolean task25(){
        System.out.println();
        System.out.println("//////TASK 25///////");
        ArrayList<Coordinates> maximum=new ArrayList<>();
        ArrayList<ArrayList<Integer>> matxOnArrlist=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> buf=new ArrayList<>();
            for (int j = 0; j < m; j++) {
                buf.add(matx[i][j]);
                System.out.print(buf.get(j)+" ");
            }
            matxOnArrlist.add(buf);
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean numberIsBigger = true;
                for (int i_offset = -1; i_offset <= 1; ++i_offset) {
                    for (int j_offset = -1; j_offset <= 1; j_offset++) {
                         if(i+i_offset>=0&&i+i_offset<n&&j+j_offset>=0&&j+j_offset<m) {
                             if (matxOnArrlist.get(i).get(j) < matxOnArrlist.get(i + i_offset).get(j + j_offset)) {
                                 numberIsBigger = false;
                             }
                         }
                    }
                }
                if(numberIsBigger) {
                    maximum.add(new Coordinates(j,i));
                }
            }

        }
        for(int i=0;i<maximum.size();++i){
            matxOnArrlist.get(maximum.get(i).y).set(maximum.get(i).x, 0);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matxOnArrlist.get(i).get(j)+" ");
            }
            System.out.println();
        }
        if(n!=m){
            return false;
        }else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matxOnArrlist.get(i).get(j)!=matxOnArrlist.get(j).get(i)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private static void task39(){
        System.out.println();
        System.out.println("//////TASK 39///////");
        Vector<Vector<Integer>> matxOnVector=new Vector<>();
        Vector<Vector<Double>> smoothedMatx=new Vector<>();
        for (int i = 0; i < n; i++) {
            Vector<Integer> buf=new Vector<>();
            Vector<Double> bufOfDouble=new Vector<>();
            for (int j = 0; j < m; j++) {
                buf.add(matx[i][j]);
                bufOfDouble.add((double)matx[i][j]);
                System.out.print(buf.get(j)+" ");
            }
            matxOnVector.add(buf);
            smoothedMatx.add(bufOfDouble);
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double average=0;
                int countOfElem=0;
                for (int i_offset = -1; i_offset <= 1; ++i_offset) {
                    for (int j_offset = -1; j_offset <= 1; j_offset++) {
                        if(i+i_offset>=0&&i+i_offset<n&&j+j_offset>=0&&j+j_offset<m) {
                           ++countOfElem;
                           average+=matxOnVector.get(i+i_offset).get(j+j_offset);
                        }
                    }
                }
                average-=matxOnVector.get(i).get(j);
                countOfElem--;
                average/=countOfElem;
                smoothedMatx.get(i).set(j,average);
            }
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(String.format("%.1f",smoothedMatx.get(i).get(j)));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        try(BufferedReader cin=new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите размеры матрицы n*m, через пробел");
            String inputData=cin.readLine();
            String[] dataArray=inputData.split(" ");
            n=Integer.parseInt(dataArray[0]);
            m=Integer.parseInt(dataArray[1]);
            matx=new int[n][m];
            for (int i=0;i<n;++i){
                for (int j = 0; j < m; j++) {
                    matx[i][j]= randomEng.nextInt(1,100);
                    System.out.print(matx[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("Норма матрицы " + task11());
            if(task25()){
                System.out.println("Матрица стала симметричной");
            }else{
                System.out.println("Матрица не стала симметричной");
            }
            task39();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}