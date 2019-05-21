public class FixedQueue {
    Node head;
    Node tail;
    int headInd = 0, tailInd = 0, count = 0;

    public FixedQueue() {
        head = new Node();
        tail = head;
    }

    public boolean offer(int val) {
        if (tailInd == 5) {
            tailInd = 0;
            Node next = new Node();
            tail.next = next;
            tail = next;
        }
        tail.arr[tailInd++] = val;
        count++;
        return true;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }
        if (headInd == 5) {
            headInd = 0;
            head = head.next;
        }
        count--;
        return head.arr[headInd++];
    }

    public static void main(String[] args) {
        FixedQueue queue = new FixedQueue();
        for (int i = 0; i < 20; i++) {
            System.out.println(queue.offer(i));
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(queue.poll());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.offer(i));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.poll());
        }

    }

}

class Node {
    int[] arr;
    Node next;
    public Node() {
        arr = new int[5];
        next = null;
    }
}

