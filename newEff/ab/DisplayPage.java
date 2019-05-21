import java.util.*;

public class DisplayPage {
    public List<String> displayPages(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Iterator<String> iter = input.iterator();
        int counter = 0;
        while (iter.hasNext()) {
            String cur = iter.next();
            int id = Integer.parseInt(cur.split(",")[0]);
            if (!visited.contains(id)) {
                res.add(cur);
                visited.add(id);
                iter.remove();
                counter++;
            }
            
            if (counter == pageSize) {
                visited.clear();
                counter = 0;
                iter = input.iterator();
            }

            if (!iter.hasNext()) {
                iter = input.iterator();
                visited.clear();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        DisplayPage dp = new DisplayPage();
        List<String> input = new ArrayList<>();
        input.add("1,28,310.6,SF");
        input.add("1,5,204.1,SF");
        input.add("20,7,203.2,Oakland");
        input.add("6,8,202.2,SF");
        input.add("6,10,199.1,SF");
        input.add("1,16,190.4,SF");
        input.add("6,29,185.2,SF");
        input.add("7,20,180.1,SF");
        input.add("6,21,162.1,SF");
        input.add("2,18,161.2,SF");
        input.add("2,30,149.1,SF");
        input.add("3,76,146.2,SF");
        input.add("2,14,141.1,San Jose");

        List<String> res = dp.displayPages2(input, 5);
        for (String s : res) {
            System.out.println(s);
        }



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
            count++;
            if (idMap.get(cur.id).size() > cur.pos + 1) {
                queue.offer(new Tuple(cur.id, cur.pos + 1, idMap.get(cur.id).get(cur.pos + 1)));
            }
            if (count == pageSize) {
                //end of current one
                //here, if resutls are saved to temporary container then we can sort/order that temp container!
                count = 0;
                res.add("");
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
