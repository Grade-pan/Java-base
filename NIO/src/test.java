import java.io.*;

public class test {
    public static void main(String[] args) {
        Long startTime = System.nanoTime();
        File file = new File("H:\\大明王朝\\01.mkv");
        File file1 = new File("H:\\01.mkv");
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file1));

            int i = 0;
            while ((i = in.read()) != -1) {
                os.write(i);
            }
            os.flush();
            os.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("读取500mb文件，缓冲为20mb，IO所需的时间为： " + (endTime - startTime) + "ns");
    }
}
