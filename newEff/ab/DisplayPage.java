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

    

    public List<String> displayPages2(List<String> input, int pageSize) {
        Map<Integer, List<String>> idMap = new HashMap<>();
        for (String in : input) {
            int id = Integer.parseInt(in.split(",")[0]);
            List<String> records = idMap.computeIfAbsent(id, l -> new ArrayList<>());
            records.add(in);
        }

        Deque<Tuple> queue = new LinkedList<>();
        for (Map.Entry<Integer, List<String>> entry : idMap.entrySet()) {
            queue.offer(new Tuple(entry.getKey().intValue(), 0, entry.getValue().get(0)));
        }

        List<String> res = new ArrayList<>();
        int count = 0;

        while (!queue.isEmpty()) {
            Tuple cur = queue.poll();
            res.add(cur.record);
            if (idMap.get(cur.id).size() > cur.pos + 1) {
                queue.offer(new Tuple(cur.id, cur.pos + 1, idMap.get(cur.id).get(cur.pos + 1)));
            }
            if (count == pageSize) {
                //end of current one
                //here, if resutls are saved to temporary container then we can sort/order that temp container!
            }




        }


        return res;
    }

}

class Tuple {
    int id;
    int pos;
    String record;
    public Tuple(int id, int pos, String record) {
        this.id = id;
        this.pos = pos;
        this.record = record;
    }
}
