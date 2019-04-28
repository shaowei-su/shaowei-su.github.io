import java.util.*;

public class QueueWithFixedArray {
    private int fixedLength;
    private int count;
    private int head;
    private int tail;
    private List<Object> headList;
    private List<Object> tailList;

    public QueueWithFixedArray(int cap) {
        this.fixedLength = cap;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
        this.headList = new ArrayList<>();
        this.tailList = this.headList;
    }

    public void offer(int num) {
        if (tail == this.fixedLength - 1) {
            List<Object> next = new ArrayList<>();
            next.add(num);
            tailList.add(next);
            tailList = next;
            tail = 0;
        } else {
            tailList.add(num);
        }
        count++;
        tail++;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }
        int num = (int) headList.get(head);
        head++;
        count--;

        if (head == fixedLength - 1) {
            headList = (List<Object>) headList.get(head);
            head = 0;
        }
        return num;
    }

    public int size() {
        return count;
    }

}
