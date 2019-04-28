import java.util.*;

public class DisplayPage {
    public List<String> displayPages(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Iterator<String> iter = input.iterator();
        boolean reachEnd = false;
        int counter = 0;
        while (iter.hasNext()) {
            String cur = iter.next();
            int id = cur.split(",")[0];
            if (!visited.contains(id) || reachEnd) {
                res.add(cur);
                visited.add(id);
                iter.remove();
                counter++;
            }
            
            if (counter == pageSize) {
                reachEnd = false;
                visited.clear();
                counter = 0;
                iter = input.iterator();
            }

            if (!iter.hasNext()) {
                reachEnd = true;
                iter = input.iterator();
            }
        }

        return res;
    }
}
