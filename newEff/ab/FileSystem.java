import java.util.*;

public FileSystem {
    Map<String, Integer> pathMap;
    Map<String, Runnable> callbackMap;

    public FileSystem() {
        pathMap = new HashMap<>();
        callbackMap = new HashMap<>();
        pathMap.put("", 0);
    }

    public boolean create(String path, int value) {
        if (pathMap.containsKey(path)) {
            return false;
        }

        int lastSlash = path.lastIndexOf("/");
        if (!path.containsKey(path.substring(0, lastSlash))) {
            return false;
        }

        pathMap.put(path, value);
        return true;
    }

    public boolean set(String path, int value) {
        if (!pathMap.containsKey(path)) {
            return false;
        }

        pathMap.put(path, value);

        // trigger
        while (path.length() > 0) {
            if (callbackMap.containsKey(path)) {
                callbackMap.get(path).run();
            }
            int lastSlash = path.lastIndexOf("/");
            path = path.substring(0, lastSlash);
        }

        return true;
    }

    public int get(String path) {
        return pathMap.get(path);
    }

    public boolean watch(String path, Runnable callback) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        callbackMap.put(path, callback);
        return true;
    }
}
