package Lab_0;

public class Variant18 {
    public static int Integer(int N) throws IllegalArgumentException
    {
        if(N<=999)
            throw new IllegalArgumentException("N must be bigger than 999");
        return (N/1000)%10;
    }

    public static boolean BooleanExpressions(int a, int b, int c)
    {
        return a==b || a==c || b==c;
    }

    public static int ConditionalOperator(int a, int b, int c)
    {
        if(a!=b && a!=c)
            return 0;
        else if(b!=a)
            return 1;
        return 2;
    }

    public static String SelectionOperator(int N) throws IllegalArgumentException
    {
        if(N<100 || N>999)
            throw new IllegalArgumentException("100<=N<=999");
        int h = N/100;
        StringBuilder result = new StringBuilder();
        switch (h) {
            case 1 -> result.append("сто");
            case 2 -> result.append("двісті");
            case 3 -> result.append("триста");
            case 4 -> result.append("чотириста");
            case 5 -> result.append("п'ятсот");
            case 6 -> result.append("шістсот");
            case 7 -> result.append("сімсот");
            case 8 -> result.append("вісімсот");
            case 9 -> result.append("дев'ятсот");
        }
        int d = (N/10)%10;
        if(d==1)
        {
            int o = N%10;
            switch (o) {
                case 0 -> result.append(" десять");
                case 1 -> result.append(" одинадцять");
                case 2 -> result.append(" дванадцять");
                case 3 -> result.append(" тринадцять");
                case 4 -> result.append(" чотирнадцять");
                case 5 -> result.append(" п'ятнадцять");
                case 6 -> result.append(" шістнадцять");
                case 7 -> result.append(" сімнадцять");
                case 8 -> result.append(" вісімнадцять");
                case 9 -> result.append(" дев'ятнадцять");
            }
            return result.toString();
        }
        switch (d) {
            case 2 -> result.append(" двадцять");
            case 3 -> result.append(" тридцять");
            case 4 -> result.append(" сорок");
            case 5 -> result.append(" п'ятдесят");
            case 6 -> result.append(" шістдесят");
            case 7 -> result.append(" сімдесят");
            case 8 -> result.append(" вісімдесят");
            case 9 -> result.append(" дев'яносто");
        }
        int o = N%10;
        switch (o) {
            case 1 -> result.append(" один");
            case 2 -> result.append(" два");
            case 3 -> result.append(" три");
            case 4 -> result.append(" чотири");
            case 5 -> result.append(" п'ять");
            case 6 -> result.append(" шість");
            case 7 -> result.append(" сім");
            case 8 -> result.append(" вісім");
            case 9 -> result.append(" дев'ять");
        }
    return result.toString();
    }

    public static double LoopWithParameter(double A, int N) throws IllegalArgumentException
    {
        if(N<=0)
            throw new IllegalArgumentException("N must be bigger than 0");
        double result = 0;
        for(int i = 0; i <= N; ++i)
            result+=Math.pow(-1, i)*Math.pow(A, i);
        return result;
    }

    public static int[] ConditionalLoop(int N) throws IllegalArgumentException
    {
        if(N<=0)
            throw new IllegalArgumentException("N must be bigger than 0");
        int[] result = new int[2];
        while (N>0)
        {
            ++result[0];
            result[1]+=N%10;
            N/=10;
        }
        return result;
    }

    public static int MaxElements(int N, int[] A)
    {
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        for(int i = 0; i < N; ++i)
        {
            if(A[i] > max)
            {
                max = A[i];
                start = end = i;
            }
            else if(A[i] == max)
                end = i;
        }
        if(end == start)
            return 0;
        return end - start - 1;
    }

    public static int ArrayElements(int[] A) throws IllegalArgumentException
    {
        if(A.length != 10)
            throw new IllegalArgumentException("Array mast contain 10 elements");
        for(int i = 0; i < 9; ++i) {
            if (A[i] < A[9])
                return i;
        }
        return 0;
    }

    public static int[] MatrixProducts(int[][] A, int M, int N, int K) throws IllegalArgumentException
    {
        if(K<1 || K>N)
            throw new IllegalArgumentException("1<K<N");
        --K;
        int[] result = new int[2];
        result[1] = 1;
        for(int i = 0; i < N; ++i)
        {
            result[0]+=A[K][i];
            result[1]*=A[K][i];
        }
        return result;
    }
}
