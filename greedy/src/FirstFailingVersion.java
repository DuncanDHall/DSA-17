public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {

        return firstBadVersionHelper(1, n, isBadVersion);
    }

    private static long firstBadVersionHelper(long lower, long upper, IsFailingVersion isBadVersion) {

        if (lower == upper) {
            return lower;
        }

        long split = (lower + upper) / 2;

        if (isBadVersion.isFailingVersion(split)) {
            return firstBadVersionHelper(lower, split, isBadVersion);
        } else {
            return firstBadVersionHelper(split + 1, upper, isBadVersion);
        }

    }
}
