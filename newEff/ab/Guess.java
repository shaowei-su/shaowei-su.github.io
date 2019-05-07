public class Guess {

    private int validate(String g) {

    }

    private String guess() {
        List<Integer> res = new ArrayList<>();
        List<Integer> cands = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int count = 0;
        Iterator<Integer> iter = cands.iterator();
        while (iter.hasNext() && res.size() < 4) {
            int cand = iter.next();
            count++;
            int guessCount = res.size();
            String guessCand = genNumber(res, cand);
            int validateGuess = validate(guessCand);
            if (validateGuess == guessCount) {
                iter.remove();
            } else {
                for (int i = guessCount; i < validateGuess; i++) {
                    res.add(cand);
                }
            }
        }

        return toString(res);
    }

    private String genNumber(List<Integer> res, Integer cand) {
        StringBuilder sb = new StringBuilder();
        for (Integer r : res) {
            sb.append(r);
        }
        for (int i = res.size(); i < 4; i++) {
            sb.append(cand);
        }
        return sb.toString();
    }


}
