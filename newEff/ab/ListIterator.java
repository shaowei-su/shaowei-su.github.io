import java.util.*;

public class ListIterator {
    Iterator<List<Integer>> rowIter;
    Iterator<Integer> colIter;

    public ListIterator(List<List<Integer>> lists) {
        rowIter = lists.iterator();
        colIter = null;
    }

    public Integer next() {
        return colIter.next();
    }

    public boolean hasNext() {
        while ((colIter == null || !colIter.hasNext()) && rowIter.hasNext()) {
            colIter = rowIter.next().iterator();
        }
        return colIter != null && colIter.hasNext();
    }

    public void remove() {
        colIter.remove();
    }

    public void remove2() {
        // here, needs to make sure colIter is not null
        while (colIter == null && rowIter.hasNext()) {
            colIter = rowIter.next().iterator();
        }
        if (colIter != null) {
            colIter.remove();
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 3, 5));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(2, 4, 7, 9));
        lists.add(l1);
        lists.add(l2);
        ListIterator it = new ListIterator(lists);
        while (it.hasNext()) {
           int n = it.next();
           if (n % 2 == 0) {
               it.remove();
           }
        }
        ListIterator it2 = new ListIterator(lists);
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }

    }
        




}
