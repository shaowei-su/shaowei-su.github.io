/*
 * [535] Encode and Decode TinyURL
 *
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 *
 * algorithms
 * Medium (74.13%)
 * Total Accepted:    47.8K
 * Total Submissions: 64.5K
 * Testcase Example:  '"https://leetcode.com/problems/design-tinyurl"'
 *
 * Note: This is a companion problem to the System Design problem: Design
 * TinyURL.
 * 
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
 * as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need
 * to ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 * 
 */
public class Codec {
    private static int LENGTH = 6;
    private Map<String, String> l2s = new HashMap<String, String>();
    private Map<String, String> s2l = new HashMap<String, String>();
    Random rand = new Random();  
    
    public String randomGen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int r = rand.nextInt(62);
            if (r < 10) {
                sb.append(r);
            } else  if (r < 36) {
                sb.append((char)'a' + (r - 10));
            } else {
                sb.append((char)'A' + (r - 36));
            }
        }
        return sb.toString();
    }

    public String getRandString() {
        String r = randomGen();
        while (s2l.keySet().contains(r)) {
            r = randomGen();
        }
        return r;
    }


    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (l2s.containsKey(longUrl)) {
            return l2s.get(longUrl);
        }

        String shortUrl = getRandString();
        l2s.put(longUrl, shortUrl);
        s2l.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return s2l.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
