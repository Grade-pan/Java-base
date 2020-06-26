import java.util.Random;

public class Solve_password {
    public static void main(String[] args) {
        String str = getPassword(3);
        String str1 = null;
        System.out.println("密码是：" + str);

        String pool = randomString(3);
        char cs[] = new char[3];
        for (int i = 0; i < cs.length; i++) {
            int index = (int) (Math.random() * pool.length());
            cs[i] = pool.charAt(index);
        }
        String result = new String(cs);


        int i = 0;
        while (!str.equals(result)) {
            i++;
            System.out.println("第" + i + "次猜的密码是" + result);
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < 3; j++) {
                int index = (int) (Math.random() * pool.length());
                sb.append(pool.charAt(index));
            }
            result =sb.toString();
        }
        System.out.println("第"+i+1+"次猜到了，密码是" + result);
    }

    private static String getPassword(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = r.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    private static String randomString(int length) {
        String pool = "";
        for (short i = '0'; i <= '9'; i++) {
            pool += (char) i;
        }
        for (short i = 'a'; i <= 'z'; i++) {
            pool += (char) i;
        }
        for (short i = 'A'; i <= 'Z'; i++) {
            pool += (char) i;
        }
        return pool;
    }
}
