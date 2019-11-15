package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);

        if (root.children.size() == 0)
            return sb.toString();

        sb.append("[");
        for (int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            if (child == null) {
                sb.append("").append(",");
            } else {
                sb.append(serialize(child)).append(",");
            }

            if (i == root.children.size() - 1)
                sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0)
            return null;

        if (!data.contains("["))
            return new Node(Integer.valueOf(data),  new ArrayList<>());

        int firstSB = data.indexOf("["), lastSB = data.lastIndexOf("]");
        int st = firstSB + 1;
        Node root = new Node(Integer.valueOf(data.substring(0, firstSB)), new ArrayList<>());
        int bCounter = 0;
        for (int i = firstSB + 1; i <= lastSB; i++) {
            if(i == lastSB){
                String subS = data.substring(st, i);
                root.children.add(deserialize(subS));
                st = i + 1;
                continue;
            }

            char c = data.charAt(i);
            if (c == '['){
                bCounter++;
                continue;
            }

            if (c == ']'){
                bCounter--;
                continue;
            }

            if(bCounter == 0 && c == ','){
                String subS = data.substring(st, i);
                root.children.add(deserialize(subS));
                st = i + 1;
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));