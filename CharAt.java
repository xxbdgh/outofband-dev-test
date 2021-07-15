package ChouXiang;

import java.util.Calendar;

public class CharAt {
    public static void main(String[] args) {
        char s="中国人".charAt(1);
        char c = "徐嘉辉".charAt(2);//charAt 获取字符串内该索引处的字符类型
        System.out.println(c);
        System.out.println("abc".compareTo("abb")); //compareTo 从字符串首字母开始比较编码值，只要不为0则返回结果 为0则往后继续对比
        int result1 ="abcd".compareTo("abce");
        System.out.println(result1);
        boolean result2 = "helloWorld.java".contains("ld");
        System.out.println(result2); //contains 判断括号里的字符串在原字符串中是否存在
        boolean result3 = "http：//www.baidu.com".contains(".java");
        System.out.println(result3);
        boolean result4 = "hello World.java".endsWith(".java");//endsWish 判断字符串最后是否存在输入的字符串
        System.out.println(result4);
        System.out.println("aBC".equalsIgnoreCase("aBc"));//equalsIgnoreCase 不区分大小写的判断是否相等
        byte[] bytes = "abcde".getBytes();
        for (int i = 0; i < bytes.length;i++){ System.out.println(bytes[i]); }
        System.out.println("abcjavaC++pythonjavacC#".indexOf("++"));//indexOf 找到输入字符串在原字符串中第一次出现的索引位置
        String s1 ="a";
        System.out.println(s1.isEmpty());//isEmpty 判断原字符串是否是空字符串
        System.out.println("abc".length());//length();判断原字符串中的字符串长度
        // length是属性 length()是方法
        System.out.println("".length());
        System.out.println("abcacbacbacbacbacbacabcabacbacabacbacbacbacbacb".lastIndexOf("cb"));
        //lastIndexOf 指的是输入字符串最后一次出现的索引
        System.out.println("abcdefabcdefabcdefabcde".replace("def", "abc"));
        //replace 通过newchar的方式 修改原字符串中所有的指定oldchar字符
       String []a= "abcdefabcdefabcdefabcde".split("d");
        for (String element: a) { System.out.print(element+" "); }//split 代表 使用给定的字符当作分割符，分割源字符串
        System.out.println();
        boolean b = "avbasfzxcerq".startsWith("a");//startsWish 判断字符串是否以该子字符串结尾
        System.out.println(b);
        System.out.println("http://www.baidu.com".substring(7,10));
        char[] chas = "你是大傻逼".toCharArray();  //toCharArray 把字符串分为一个个的字符
        for (char element : chas) {System.out.print(element); }
        System.out.println("        helloWorld      ".trim());//trim 去除两边的空格
        String s2= String.valueOf(true);  //valueOf  把输入的任何类型的转换成前面对象的类型，然后输出
        System.out.println(s2);
        StringBuilder s3= new StringBuilder(); //用StringBuilder new出一个对象，然后可以用这个对象来保存需要连接的数据（可以是任何数据），最后都会是字符串
        s3.append("wocao"+"wodiu");
        System.out.println(s3);
        new Calendar
    }
}
