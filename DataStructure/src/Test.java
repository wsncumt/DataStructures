

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.*;
import java.io.*;

public class Test {

//["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
//        [[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.top();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.getMin();
        minStack.push(-2147483648);
        minStack.top();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
//        File f = new File("e:/desktop/a.txt");
//        File f1 = new File("e:/desktop/b.txt");
//        int i =1;
//        i ^= 1;
//
//        try {
//            f.createNewFile();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        f.renameTo(f1);
//        f.setReadOnly();
//        System.out.println(f.getAbsolutePath());

//        A a = new A();
//        Object o;
//        A b;
//        try {
//            o = a.clone();
//            b = (A) (o);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        OutputStream fos = null;
//        DataOutputStream dfos;
//        BufferedOutputStream bos;
//        try {
//            fos = new FileOutputStream("a.txt");
//            dfos = new DataOutputStream(fos);
//            bos = new BufferedOutputStream(fos);
//            dfos.writeLong(10l);
//            bos.flush();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        long a = System.nanoTime();
//
//        String src = "E:\\Desktop\\数据结构.pdf";
//        String dst = "E:\\Desktop\\数据结构copy.pdf";
//        fileCopy2(src, dst);
//        long b = System.nanoTime();
//        System.out.println((b - a) / 1e9);
//        int[][] nums = {{3,4,5},{3,2,6},{2,2,1}};
//        int ret = longestIncreasingPath(nums);


//        String s = "abcabczzzde";
//        String p = "*abc???de*";
//        boolean ret = isMatch(s,p);
//        try{}catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    static List<Integer> t = new ArrayList();
    static List<List<Integer>> ans = new ArrayList();

    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

    public static int longestIncreasingPath(int[][] matrix) {
        int[][] visited = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (visited[i][j] == 0) {
                    max = dfs(i, j, matrix, visited);
                }
            }
        }
        return max;
    }

    public static int dfs(int i, int j, int[][] matrix, int[][] visited) {
        if ((i < 0 || i >= visited.length) && (j < 0 || j >= visited[0].length)) {
            return 0;
        }
        if (visited[i][j] > 0) {
            return visited[i][j];
        }
        int max = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] < matrix[i][j]) {
            max = Math.max(max, dfs(i - 1, j, matrix, visited));
        }
        if (i + 1 < visited.length && matrix[i + 1][j] < matrix[i][j]) {
            max = Math.max(max, dfs(i + 1, j, matrix, visited));
        }
        if (j - 1 >= 0 && matrix[i][j - 1] < matrix[i][j]) {
            max = Math.max(max, dfs(i, j - 1, matrix, visited));
        }
        if (j + 1 < visited[0].length && matrix[i][j + 1] < matrix[i][j]) {
            max = Math.max(max, dfs(i, j + 1, matrix, visited));
        }

        visited[i][j] = max + 1;
        return visited[i][j];
    }

    public static boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[p.length() + 1][s.length() + 1];
        match[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                if (s.charAt(j - 1) == p.charAt(i - 1)) {
                    match[i][j] = match[i - 1][j - 1];
                } else {
                    if (p.charAt(i - 1) == '*') {
//                        match[i][j] = true;
                        match[i][j] = match[i - 1][j - 1] || match[i][j - 1];
                    } else if (p.charAt(i - 1) == '?') {
                        if (match[i - 1][j - 1] == true) {
                            match[i][j] = true;
                        }

                    }
                }
            }
        }

        return match[p.length()][s.length()];
    }

    //文件拷贝
    public static void fileCopy1(String src, String dst) {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);

            byte[] bs = new byte[1024 * 4];
            while (true) {
                int len = fis.read(bs);
                if (len == -1) {
                    break;
                }
                fos.write(bs, 0, len);
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                fis.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
            try {
                fos.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    //文件拷贝2
    public static void fileCopy2(String src, String dst) {
        InputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(src);
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream(dst);
            bos = new BufferedOutputStream(fos);
            byte[] bs = new byte[1024 * 4];
            while (true) {
                int len = bis.read(bs);
                if (len == -1) {
                    break;
                }
                bos.write(bs, 0, len);
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                bos.close();
                fos.close();
                bis.close();
                fis.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    //文件拷贝3
    public static void fileCopy3(String src, String dst) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel channel1 = null;
        FileChannel channel2 = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            channel1 = fis.getChannel();
            channel2 = fos.getChannel();
            while (true) {
                int a = channel1.read(buffer);
                if (a == -1) {
                    break;
                }
                buffer.flip();
                channel2.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                channel2.close();
                channel1.close();
                fos.close();
                fis.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    //文件拷贝4
    public static void fileCopy4(String src, String dst) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel channel1 = null;
        FileChannel channel2 = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            channel1 = fis.getChannel();
            channel2 = fos.getChannel();
            MappedByteBuffer map = channel1.map(FileChannel.MapMode.READ_ONLY, 0, channel1.size());
            channel2.write(buffer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                channel2.close();
                channel1.close();
                fos.close();
                fis.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    //文件拷贝5
    public static void fileCopy5(String src, String dst) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel channel1 = null;
        FileChannel channel2 = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            channel1 = fis.getChannel();
            channel2 = fos.getChannel();
            channel1.transferTo(0,channel1.size(),channel2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                channel2.close();
                channel1.close();
                fos.close();
                fis.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
}

class A implements Externalizable, Cloneable {
    int age;
    String name;

    public A() {
    }

    @Override
    public String toString() {
        return "A{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        age = in.readInt();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class B implements Cloneable, Serializable {
    //其他的属性和方法
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object o = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);//把文件写入baos
            byte[] bs = baos.toByteArray();//将其转为字节数组,放的是当前对象的全部字节

            bais = new ByteArrayInputStream(bs);
            ois = new ObjectInputStream(bais);
            o = ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                baos.close();
                ois.close();
                bais.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
        return o;
    }

    public static void delDir(File dir) {
        File[] fs = dir.listFiles();
        for (File file : fs) {
            if (file.isFile()) {//file是文件则直接删掉
                file.delete();
            }
            if (file.isDirectory()) {//file是目录则删掉目录
                delDir(file);
            }
        }
        dir.delete();//将dir目录删除掉
    }

    public static void listJavaFiles(File dir) {
        File[] ret = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) return true;
                if (pathname.isFile()) {
                    String name = pathname.getName();
                    if (name.endsWith(".java")) {
                        return true;
                    }
                }
                return false;
            }
        });
        for (File f : ret) {
            if (f.isFile()) System.out.println(f.getAbsolutePath());
            if (f.isDirectory()) listJavaFiles(f);
        }
    }

    static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
    public List<List<Integer>> rightSideView(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        TreeNode node = new TreeNode();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++){
                node = queue.poll();
                tmp.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ans.add(tmp);
        }
        return ans;
    }
}

class MinStack {
    Stack<Integer> A = null;
    Stack<Integer> B = null;
    /** initialize your data structure here. */
    public MinStack() {
        A = new Stack();
        B = new Stack();
    }

    public void push(int x) {
        if(B.isEmpty()){
            B.push(x);
        }else{
            int min = B.peek();
            if(x < min){
                B.push(x);
            }else{
                B.push(min);
            }
        }
        A.push(x);
        //B.push(x < B.peek() ? x : B.peek()) ;

    }

    public void pop() {
        if(A.isEmpty()){
            return ;
        }
        A.pop();
        B.pop();
    }

    public int top() {
        return A.peek();
    }

    public int getMin() {
        return B.peek();
    }


}