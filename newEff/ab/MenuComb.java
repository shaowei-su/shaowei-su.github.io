import java.util.*;

public class MenuComb{

public List<List<Double>> getComb(double[] prices, double target) {
    List<List<Double>> res = new ArrayList<>();
    int centsTarget = (int) Math.round(target * 100);
    Arrays.sort(prices);
    int[] centsPrices = new int[prices.length];
    for (int i = 0; i < prices.length; i++) {
        centsPrices[i] = (int) Math.round(prices[i] * 100);
    }

    dfs(res, 0, centsTarget, centsPrices, new ArrayList<Double>(), prices);
    return res;
}

public void dfs(List<List<Double>> res, int pos, int centsTarget, int[] centsPrices, List<Double> tmp, double[] prices) {
    if (centsTarget == 0) {
        res.add(new ArrayList<Double>(tmp));
        return;
    }
    for (int i = pos; i < centsPrices.length; i++) {
        if (i > pos && centsPrices[i] == centsPrices[i - 1]) {
            continue;
        }
        if (centsPrices[i] > centsTarget) {
            return;
        }
        tmp.add(prices[i]);
        dfs(res, i, centsTarget - centsPrices[i], centsPrices, tmp, prices);
        tmp.remove(tmp.size() - 1);
    }
}

    public static void main(String[] args) {
        double[] prices = {12.2, 11.3, 10.4, 8.5, 1.6};
        MenuComb mc = new MenuComb();
        List<List<Double>> res = mc.getComb(prices, 20d);
        System.out.println(res);
    }
}
