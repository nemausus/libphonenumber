import java.io.*;
import java.util.StringTokenizer;

public class Reader extends BufferedReader
{
    private static StringTokenizer tokenizer;

    public Reader(String fileName) throws FileNotFoundException
    {
        super(new FileReader(fileName));
    }

    public Reader(InputStream in)
    {
        super(new InputStreamReader(in));
    }

    int[] readIntArray(int size) throws IOException
    {
        int[] array = new int[size];
        for (int i = 0; i < size; ++i)
        {
            array[i] = nextInt();
        }
        return array;
    }

    int nextInt() throws IOException
    {
        return Integer.parseInt(nextString());
    }

    long nextLong() throws IOException
    {
        return Long.parseLong(nextString());
    }

    double nextDouble() throws IOException
    {
        return Double.parseDouble(nextString());
    }

    String nextString() throws IOException
    {
        while (tokenizer == null || !tokenizer.hasMoreTokens())
        {
            tokenizer = new StringTokenizer(readLine());
        }
        return tokenizer.nextToken();
    }

    public String nextLine() throws IOException
    {
        return readLine();
    }
}
