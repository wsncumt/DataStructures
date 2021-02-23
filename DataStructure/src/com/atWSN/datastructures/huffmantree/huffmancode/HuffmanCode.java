package com.atWSN.datastructures.huffmantree.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) throws IOException {
        String content = "2223";
        //得到字节数组
        byte[] contentBytes = content.getBytes();
        //哈夫曼压缩
        byte[] huffmanCodeBytes = huffmanZip(content);
        //哈夫曼解压缩
        byte[] sourceBytes = decode(huffmanCodes,huffmanCodeBytes);
        System.out.println(new String(sourceBytes));

//        System.out.println(Arrays.toString(contentBytes));
//        List<Node> list = getNodes(contentBytes);
//        Collections.sort(list);
//        System.out.println(list);
//        System.out.println("--------------------------------------------------");
//        Node root = huffmanTreeNode(list);
//        root.preOrder();
//
//        Map<Byte, String> huffmanCode = getCodes(root);
//        System.out.println(huffmanCode);
//
//        byte[] zip = zip(contentBytes,huffmanCode);
//        System.out.println(Arrays.toString(zip));
//        System.out.println((float)(contentBytes.length - zip.length)/contentBytes.length * 100);
//        byte[] zip = huffmanZip(content);
//        System.out.println(Arrays.toString(zip));
//        byte a = (byte) Integer.parseInt("10001010", 2);
//        System.out.println(a);
//        System.out.println("------------------------------");
//        String str = Integer.toBinaryString(a);
//        System.out.println(str);

//        String srcFile = "E://Desktop//数据结构.pdf";
//        String dstFile = "E://Desktop//KMP算法图压缩后.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功");

//        String zipFile = "E://Desktop//KMP算法图压缩后.zip";
//        String dstFile = "E://Desktop//KMP算法图解压后.png";
//        unZipFile(zipFile, dstFile);
//        System.out.println("解压缩文件成功");
    }

    //编写一个方法，将文件解压缩

    /**
     * @param zipFile 待解压文件
     * @param dstFile 文件解压到的路径
     */
    public static void unZipFile(String zipFile, String dstFile) throws IOException {
        //定义文件输入流
        InputStream is = null;
        //定义一个与文件输入关联的对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组（哈夫曼编码处理后的那个数组）
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> map = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(map, huffmanBytes);
            //将bytes写入到目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //编写方法，将一个文件进行压缩

    /**
     * @param srcFile 被压缩文件的全路径
     * @param dstFile 压缩后的文件存放的目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            //创建一个和源文件一样的byte数组
            byte[] b = new byte[fileInputStream.available()];//available()返回源文件的大小
            //读取文件
            fileInputStream.read(b);//把文件的内容读到字节数组中
            //获取到文件对应的赫夫曼编码

            //直接对源文件进行了压缩
            byte[] huffmanZip = huffmanZip(b);
            //创建文件输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanZip);
            //以对象流的方式写入赫夫曼编码，为了恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件，否则无法恢复
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //解码

    //编写一个方法，完成对压缩数据的解码

    /**
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 压缩后得到的数组
     * @return 原字符串对应的字节数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //处理huffmanBytes的前边字节，后两个单独处理
        for (int i = 0; i < huffmanBytes.length - 2; i++) {
            stringBuilder.append(byteToBitString(huffmanBytes[i]));
        }
        //后两个单独处理：哈夫曼编码处理的最后一个字节记录的是最后一个字节的长度
        int l = huffmanBytes[huffmanBytes.length - 1];
        String str  = Integer.toBinaryString((huffmanBytes[huffmanBytes.length - 2]|256));//要和1 0000 0000(十进制为256)的或一下，正数补高位
        stringBuilder.append(str.substring(str.length() - l));
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换
        // 例如之前是97->100，即把97对应的字符转为赫夫曼编码100
        //现在要把100转为对应的字符
        //相当于是反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个'0'或'1'
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b != null) {
                    flag = false;
                } else {//b为空，说明没有匹配到
                    count++;
                }

            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        int index = 0;
        for (Byte by : list) {
            b[index++] = by;
        }
        return b;
    }


    /**
     * 功能：把压缩后的字节数组中的元素转为二进制字符字符串
     *
     * @param bt   传入的byte
     * @return 是bt对应的二进制字符串，是按补码返回的。
     */
    private static String byteToBitString(byte bt) {
        int tmp = bt;
            //对于正数，还存在补高位
        tmp |= 256;//256的二进制码是1 0000 0000；后八位刚好是0

        String str = Integer.toBinaryString(tmp);
        //int对应的字节是32位，要截取最后八位
            return str.substring(str.length() - 8);
    }


    //使用一个方法，将前面的方法封装到一起，便于调用
    //直接处理字符串
    //返回经赫夫曼编码处理后的字节数组
    public static byte[] huffmanZip(String content) {
//        //生成字节数组
//        byte[] contentBytes = content.getBytes();
//        //统计各字符的权值并创建字符对应的叶子节点
//        //将叶子节点放入到ArrayList中
//        List<Node > list =getNodes(contentBytes);
//        //生成哈夫曼树
//        Node root = huffmanTreeNode(list);
//        //通过哈夫曼树生成哈夫曼编码表
//        Map<Byte, String> huffmanCode = getCodes(root);
//        //返回经哈夫曼编码处理后的字节数组
//        return zip(contentBytes,huffmanCode);
        //上边全部的代码可以写成下边这个
        return zip(content.getBytes(), getCodes(huffmanTreeNode(getNodes(content.getBytes()))));
    }

    //处理字符串对应的字节数组
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> list = getNodes(bytes);
        Node root = huffmanTreeNode(list);
        Map<Byte, String> huffmanCode = getCodes(root);
        return zip(bytes, huffmanCode);
    }


    /**
     * @param bytes 接收字节数组
     * @return
     */
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> list = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数，使用map[key,value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {//map中没有该数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //构建Node
        //把每一个键值对转成Node对象
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    //通过List生成赫夫曼树
    public static Node huffmanTreeNode(List<Node> list) {
        if (list.size() == 1) {
            Node root = new Node();
            root.left = list.get(0);
            return root;
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node root = new Node(leftNode.weight + rightNode.weight);
            root.right = rightNode;
            root.left = leftNode;
            list.add(root);
            list.remove(leftNode);
            list.remove(rightNode);
        }
        return list.get(0);
    }

    //通过赫夫曼树生成赫夫曼编码
    //将赫夫曼编码表放入到map中去Map<Byte,String>形式
    //32->01 97->100
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    /**
     * 功能：将所有传入的node节点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes中
     *
     * @param node          传入的赫夫曼树节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径的
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        //
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将传入的code加入到stringBuilder2中
        stringBuilder2.append(code);
        if (node != null) {//node为空不处理
            //判断当前节点是叶子还是非叶子
            if (node.data == null) {//非叶子节点
                //递归处理

                //向左
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//叶子节点
                //表示找到了某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    //为了调用方便，重载该方法
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            System.out.println("哈夫曼数为空，无法构建哈夫曼码");
            return null;
        }
        //生成赫夫曼编码表时，需要拼接路径，使用StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        getCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]

    /**
     * @param bytes        原始字符串对应的byte[]数组
     * @param huffmanCodes 由原始字符串生成的哈夫曼编码表
     * @return 返回的是哈夫曼编码后转成的对应的byte数组
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //先利用赫夫曼编码表将原始的byte[]数组转换为赫夫曼编码字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte elem : bytes) {
            stringBuilder.append(huffmanCodes.get(elem));
        }
        //这句是调试代码查看二进制码的，实际功能中不要
        //System.out.println(stringBuilder.toString());

        //将字符串转为byte数组
        //首先计算字节数组的长度，即stringBuilder的长度/8
        int len = 0;
        //为了便于统一，字节数组的最后一个用于存放赫夫曼编码后的最后那个字节的位长度
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8 + 1;
        } else {
            len = stringBuilder.length() / 8 + 1 + 1;
        }
        int index = 0;//记录放入byte数组的位置
        //创建存储压缩后的byte数组
        byte[] tmp = new byte[len];
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 <= stringBuilder.length()) {
                strByte = stringBuilder.substring(i, i + 8);
                if (i + 8 == stringBuilder.length()) {
                    tmp[len - 1] = 8;
                }
            } else {
                strByte = stringBuilder.substring(i);
                tmp[len - 1] =  (byte) (stringBuilder.length() - i);
            }
            //将strByte转为byte数组
            tmp[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return tmp;
    }
}

class Node implements Comparable<Node> {
    Byte data;//用于存放字符
    int weight;//权值，表示data出现的次数
    Node left;
    Node right;

    public Node() {

    }

    public Node(int weight) {
        this.weight = weight;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
