public int hilbert(int x, int y, int iter) {
    if (iter == 0) {
        return 1;
    }
    int len = 1 << (iter - 1);
    int num = 1 << (2 * (iter - 1));

    if (x >= len && y >= len) {
        // same dir
        return 2 * num + hilbert(x - len, y - len, iter - 1);
    } else if (x < len && y >= len) {
        // same dir
        return num + hilbert(x, y - len, iter - 1);
    } else if (x < len && y < len) {
        // rotate 90 , clock wise
        return hilbert(y, x, iter - 1);
    } else {
        // rotate 90, counter clock wise
        return 3 * num + hilbert(len - y - 1, 2 * len - x - 1, iter - 1);
    }
}
