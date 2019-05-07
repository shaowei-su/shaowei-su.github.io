import java.util.*;

public class CIDR {

    public List<String> ip2CIDR(String ip, int k) {
        List<String> res = new ArrayList<>();
        String[] parts = ip.split("\\.");
        long ipVal = 0L;
        long upper = 0L;
        for (String part : parts) {
            upper = upper * 256 + 255;
            ipVal = ipVal * 256 + Integer.parseInt(part);
        }
        while (k > 0 && ipVal <= upper) {
            long tmp = ipVal & -ipVal;
            while (tmp > k) {
                tmp /= 2;
            }
            res.add(getCIDR(ipVal, tmp));
            ipVal += tmp;
            k -= tmp;
        }
        return res;
    }

    public String getCIDR(long ipVal, long tmp) {
        int[] res = new int[4];
        for (int i = 0; i < 4; i++) {
            res[i] = (int) (ipVal & 255);
            ipVal /= 256;
        }
        int netId = 32;
        while (tmp > 0) {
            tmp /= 2;
            netId--;
        }
        netId++;
        return res[3] + "." + res[2] + "." + res[1] + "." + res[0] + "/" + netId;
    }

    public static void main(String[] args) {
        CIDR c = new CIDR();
        String ip = "255.255.255.255";
        System.out.println(c.ip2CIDR(ip, 10));
        String ip2 = "255.255.255.7";
        System.out.println(c.ip2CIDR(ip2, 10));
    }



}
