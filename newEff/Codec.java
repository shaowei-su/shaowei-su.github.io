import java.util.*;
/*
 * @lc app=leetcode id=428 lang=java
 *
 * [428] Serialize and Deserialize N-ary Tree
 *
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/description/
 *
 * algorithms
 * Hard (52.95%)
 * Total Accepted:    9.4K
 * Total Submissions: 17.7K
 * Testcase Example:  '{"$id":"1","children":[{"$id":"2","children":[{"$id":"5","children":[],"val":5},{"$id":"6","children":[],"val":6}],"val":3},{"$id":"3","children":[],"val":2},{"$id":"4","children":[],"val":4}],"val":1}'
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary
 * tree is a rooted tree in which each node has no more than N children. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that an N-ary tree can be serialized to a
 * string and this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following 3-ary tree
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so
 * please be creative and come up with different approaches yourself.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * N is in the range of  [1, 1000]
 * Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 * 
 * 
 */
/*
// Definition for a Node.
// */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
class Codec {

    public static void main(String[] args) {
        Codec cd = new Codec();
        Node left = new Node(5, null);
        Node right = new Node(6, null);
        List<Node> l = new ArrayList<>();
        l.add(left);
        l.add(right);
        Node root = new Node(3, l);
        System.out.println(cd.serialize(root));
        System.out.println(cd.deserialize("1[3[5,6],2,4]"));
    }
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        if (root.children != null && root.children.size() > 0) {
            sb.append("[");
            for (Node child : root.children) {
                sb.append(serialize(child));
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append("]");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
       if (data == null || data.length() == 0) {
           return null;
       }
       if (!data.contains("[")) {
           return new Node(Integer.parseInt(data), null);
       }
       int first = data.indexOf("[");
       List<Node> children = new ArrayList<>();
       String[] childrenData = data.substring(first + 1, data.length() - 1).split(",");
       for (String cd : childrenData) {
           children.add(deserialize(cd));
       }
       Node res = new Node(Integer.parseInt(data.substring(0, first)), children);
       return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
