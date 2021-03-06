public class Test1 {
    public static void main(String[] args) {
        int[] arr = {559,526,134,1,568,197,532};
        ListNode A = new ListNode(arr[0]);
        ListNode cur = A;
//        System.out.println(A);
//        System.out.println(cur);
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        boolean flag = chkPalindrome(A);
    }

    public static boolean chkPalindrome(ListNode A) {
        // write code here
        if (A == null || A.next == null) {
            return true;
        }
        int len = length(A);
        int mid = len >> 1;
        ListNode midNode = A;
        ListNode preNode = new ListNode(0);
        preNode.next = midNode;
        for (int i = 0; i < mid; i++) {
            midNode = midNode.next;
            preNode = preNode.next;
        }
        if (len % 2 == 1) {
            midNode = midNode.next;
        }
        //翻转链表
        ListNode newNode = reverse(A, mid);
        while (newNode != null) {
            if (newNode.val != midNode.val) {
                return false;
            }
            newNode = newNode.next;
            midNode = midNode.next;
        }
        return true;
    }

    //计算链表长度
    public static int length(ListNode A) {
        ListNode cur = A;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    //翻转链表
    public static ListNode reverse(ListNode A, int step) {
        ListNode preNode = null;
        ListNode curNode = A;
        for (int i = 0; i < step; i++) {
            ListNode nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    public static ListNode FindKthToTail(ListNode head, int k) {
        if (k <= 0) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k - 1; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


