import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

public class Writer extends PrintWriter
{
    private boolean fromFile;
    private long start;


    public Writer(OutputStream outputStream, boolean fromFile)
    {
        super(new BufferedOutputStream(outputStream));
        this.fromFile = fromFile;
        start = System.nanoTime();
    }

    public void finish()
    {
        if (fromFile)
        {
            println("time: " + (System.nanoTime() - start) / 1000000);
        }
        flush();
        close();
    }

    public void print(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (i != 0)
                print(' ');
            print(array[i]);
        }
    }

    public <T> void print(Collection<T> collection)
    {
        boolean first = true;
        for (T e : collection)
        {
            if (!first)
            {
                print(' ');
            }
            print(e);
            first = false;
        }
    }
}
