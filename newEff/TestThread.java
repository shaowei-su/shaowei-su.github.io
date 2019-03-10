import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        ItemQueue itemQueue = new ItemQueue(3);

        Thread producer = new Producer(itemQueue);
        Thread consumer = new Consumer(itemQueue);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    static class ItemQueue {

        private Object[] items = null;
        private int current = 0;
        private int placeIndex = 0;
        private int removeIndex = 0;

        private final Lock lock;
        private final Condition isEmpty;
        private final Condition isFull;

        public ItemQueue(int capacity) {
            this.items = new Object[capacity];
            lock = new ReentrantLock();
            isEmpty = lock.newCondition();
            isFull = lock.newCondition();

        }

        public void add(Object item) throws InterruptedException {
            lock.lock();

            while (current >= items.length) {
                isFull.await();
            }

            items[placeIndex] = item;
            placeIndex = (placeIndex + 1) % items.length;
            ++current;

            isEmpty.signal();
            lock.unlock();
        }

        public Object remove() throws InterruptedException {
            Object item = null;

            lock.lock();

            while (current <= 0) {
                isEmpty.await();
            }

            item = items[removeIndex];
            removeIndex = (removeIndex + 1) % items.length;
            --current;

            isFull.signal();
            lock.unlock();

            return item;
        }

        public boolean isEmpty() {
            return (current == 0 && placeIndex == removeIndex);
        }
    }

    static class Producer extends Thread {
        private final ItemQueue queue;

        public Producer(ItemQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String[] nums = {"1", "2", "3"};

            try {
                for (String num : nums) {
                    System.out.println("[producer] : " + num);
                    queue.add(num);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        private final ItemQueue queue;

        public Consumer(ItemQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                do {
                    Object number = queue.remove();
                    System.out.println("[consumer] : " + number);
                    if (number == null) {
                        return;
                    }
                } while (!queue.isEmpty());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

    




