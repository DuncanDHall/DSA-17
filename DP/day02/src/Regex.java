import java.util.Arrays;

public class Regex {

    public static boolean isMatch(String s, String p) {

        // because java uses bytes not bits for boolean arrays, this is actually worse than a single int array...
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] computed = Arrays.copyOf(memo, memo.length);

//        return isMatchBU(s, p);
        return isMatchRecursive(0, 0, s, p, memo, computed);

    }

    private static boolean isMatchBU(String s, String p) {
        char[] S = (" " + s).toCharArray();
        char[] P = (" " + p).toCharArray();

        boolean[][] memo = new boolean[S.length][P.length];
        memo[0][0] = true;  // memo stores whether s[:i] and p[:j] match
                            // THIS IS INDEXED BY LENGTH OF STRING / PATTERN

        // empty pattern with string is not a match
        for (int i = 1; i < S.length; i++) {
            memo[i][0] = false;
        }

        // empty string with pattern can be a match
        boolean allStarsSoFar = true;
        boolean even = false;
        for (int j = 1; j < P.length; j++) {
            if (allStarsSoFar) {
                if (even) {
                    if (P[j] != '*') allStarsSoFar = false;
                }
            }
            memo[0][j] = allStarsSoFar;
            even = !even;
        }

        for (int i = 1; i < S.length; i++) {
            for (int j = 1; j < P.length; j++) {

                // Recurrence relation

                // case star present
                if (P[j] == '*') {
                    memo[i][j] = (
                            // match zero occurrences
                            memo[i][j - 2]
                            // match one more occurrence
                            || charMatches(P[j - 1], S[i]) && memo[i - 1][j]
                            );
                }

                // case pattern has . or letter
                else {
                    memo[i][j] = charMatches(P[j], S[i]) && memo[i - 1][j - 1];
                }

            }
        }
        return memo[S.length - 1][P.length - 1];
    }





    private static boolean isMatchRecursive(int i, int j, String s, String p, boolean[][] memo, boolean[][] computed) {
        // TODO doesn't use the memo somehow...

        // base
        if (i == s.length()) {  // ran out of string
            if (j == p.length()) return true;
            else if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return isMatchRecursive(i, j + 2, s, p, memo, computed);
            }
            return false;
        }
        if (j == p.length()) return false;  // ran out of pattern with more string to go


        // memo checking
        if (computed[i][j]) {
            System.out.println("here");
            // memoizing is pointless
            return memo[i][j];
        }


        // recursive relation
        boolean match = false;

        // next pattern token is *
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {

            // check 1... n chars eaten by c*
            if (charMatches(p.charAt(j), s.charAt(i))) {
                for (int k = i; k < s.length(); k++) {
                    if (!charMatches(p.charAt(j), s.charAt(k))) {
                        break;
                    } else if (isMatchRecursive(k + 1, j + 2, s, p, memo, computed)){
                        match = true;
                        break;
                    }
                }
            }

            // check 0 chars eaten
            else {
                match = isMatchRecursive(i, j + 2, s, p, memo, computed);
            }
        }

        // next pattern token is single character
        else {
            match = charMatches(p.charAt(j), s.charAt(i)) && isMatchRecursive(i + 1, j + 1, s,  p, memo, computed);
        }


        // memoize and return
        computed[i][j] = true;
        memo[i][j] = match;
        return match;

    }

    private static boolean charMatches(char p, char s) {
        return p == s || p == '.';
    }

    public static void main(String[] args) {

        System.out.println(Regex.isMatch(
                "cbccccccccbabcbacabbabaabacabccbbcbcbabacccccccccccccccccccccaccbcabccccbcbacbacbbbaaccccbabbbbbbbbbbbbbbbbbbbccbbccaaaaaaaaaaaaaaaaaaaaabbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaabbbcbbccaaabaaaaaaaaaacccccccccccbbbbbbbbbbbbbbbaaaaaaabcbbccccacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccbcbbbaaababbbcccbaccacbbabcaaaaacbbaabcbbbbaacbcbcaaaaaaabaacccbbcccbbbacbcbccaaccabcbbccbbbbbbbbbcccacacccccccccccccccccaccccccccccccccccccccbbbbbbbbabaaaaaaaaaaabcabcababcabacbcabbccaabbcccaabbabacacccbaaaccabbbbbbbbbbbbbbbbbbaaccabcbbbbbbbcbabcaabcabccbccababbbbbbbbbccaacccccccccbcbcaaaccabbacabbccbcccbbcbcabbbbbcaaccacccccccccccccccccccaacccabbbbccbaabbbbcaacacacbabcaabcacaccaaaaaababbaccabbaaaacababaccccccccccccccccccccbaaaaaacaabbcbcccccccccccbbbbbbbbbbbbbbbbaabacccccccbccaaaaaaaaaaaaaaaacbcccaabbbbbbbbbbaabbabbbabacbcaaabbbaaabaabbcaccbabacbacaaacbaabbabbbbbbaccaaaaaaaaabacbbbabcaaabacbbbbbbbbaaabcccbccaccbbbbbbbbbbbaaaaabcaabccbccbbbbbbcbbbcbbacabcabaacbababbbbbbbbbbbbaacaaaaaaaaaaaaaaaaacbbcccaabbbbbbbbbbacacccbcaabcaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbabbabccaaabbabbbcaccacbccaccabacaabbaaaacbaabbacbbccaaabcacacccccaaccbaacabbbbbbacbbabacabcaccccaacacaaaabcaaabccccccaaccccacbccccccccccaabbbbbaccbbbbbbbbbbbbbbbbbbaacaccbcccccccccccbabccacacabbbbccbaaaacacaccbccbaabccaccacbabcbbaaaacbbabaabacccccccccccccccccccccccbcbcacbbbbbbbbbcabbaccbbbaacabcbaaaaaaaaaaaabacaaabbbaccccccccaccaaaaccbbabaabbcaccccbbccabbccaccbcabbababacbbbbbbbbbbbbbbbbbbaabcccccccccabcaabbbccbcabbbbabbbcccbabaaaaaaaaaaaaaccabbaacbaaabacbabcaccabcaaacaababacacacbacbbbbbbbbbbbbbbbbbbbbbbbbcaaccbcaaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcbacaabaaaabbbbbbbbbbbbbbbbbbbbbbbbaacccccccccccccbbacbbbcabaaaaabccbbbbbbbbbbbbbacbbabbcccabacaaacbcaccbbbbcbacbacabbacccaaabbbcacbcccaacbaacbbaccabcccbacccbacabaacbcbaacccabccccbabaaaabaacbbacccccccabaaaaaabbbcababbbbaaacaaaaaaaaaaaacbaacbcbabcabbacbcccccbaabbbbbaccccabcccbaaccccaccccccbaaccbabcbcbaaccccaaaacacaaaabcbccbabcbaccbbbabcbcbbabbcccaacacabbbcabbcaabbcbabbbbcccbbacbbbabccccccccccccccccccccccccccccccbbacaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcbabbaccbabbcccccccccbccaabbcccccccbaccaaaaaaaacbccccccccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaacbbbbbabcccababacaacbbaabccbbacabbcacabccababaccbbbbbbbbbacbabbaaabaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcabbaabaaaaaaaaaaabcaaabbbbbbbbbbbcbbbbbbbbbbbbaaacabbbaaacaaaaaaabbbaaccacbbbbcccaababcacabcbabccbcbacaacbbbcaccaacbabbbbbbcbbbacacccccaaaaaacabccccabbcacbacbbacbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcacacbbbabacacccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccbcbacabcaaaaaaaaaaaacccbbacababbaabbaaaaaacacbbcaaaaaaaaccaccacbccbccccbbccaabcabcabccbcccaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaababccccccccccccccccccccbcacabcbcbbccbaacbaacaaaaaaaccbcbccccccaacbabccbacbcacbcbbbcaaaaaaaaaaaaaaaaaacbaccaaaccaacccaaaccabacccaabbbbabacccccccccccccccccbcbabccaaaaaaaaaaaaacccccccccccccccbcccbccaabccbbcccccccccccccccccccccaaaabcccccccccccccccacabbaabbaabccccaaaaaaaaaaaaaaaaabacbaccbbacabcacbccccccbbbabccbbcacaaabacccccccccccccccccccccacaccbcbbbcccacccbacabccbaaabaabbccaaaccbbccccccccccabbbaacbbbbbbbbbbccbbbbbbbbbbcaaabbbbbbbbbbbbbbbbbbbbbccacbacbabcacbcbaaacccaaaaaacccbacbcaaaaaaaaaaaaaaaaacacbbcbbbacbcccbcbaaaabaabcabbbbbbbbbbbbbbbbbbbbbbbbbbbbbaacbaaccbbbbbbbbbbbbbbbbbbbbacacbbcbaaaaaaaaaaaaacbaaccabcaabbbbbcaccaabbaabbbaabbbbaacacbaabaccccbbbabbbbbbbbbbbbbbbbbbcabccabbccaaccccbccbacaccbacbcaababcccccccbbaaaccbccbcbcaccbcacbbbccbacbbacaaaacbbaaabbacbabbacabbcabaaccbaacccccccabcaaacabcaccbcababaabcbabcbbbbbacaaaaacabbbcccaaaccacccccccccccccaccacbccabccccbacaaaabcbabbbbbbbbbbcbcbbbccccbbbbbbbbbcbcbaaaaaaaaaaaaaaaaaaaabbbbabacababbaccaccbabccababbaabccbcaaaaacbbccbcbcbbbcccbccbcaaccabcbbabccbbcaaaaaaaabbcbaccccccbcbbbabcccccccccccccccccabbbbabbccbabaacacbbcacccccccccccccccccccccccccccccacaccababaacccccccccccccccccccccccacaacaabaacbccccccccccccccaabaaccbcbbbbbacaabbabaabaccaaacabbcabcaaaaaaaacabcccccccccccccccccabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabcaacacaccabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbbbbbbbbbbbbbaaaacccccccccbccccccbabbaaaaaccccccccaaaccbcccbacaaaaaaaaaaaaaaaabcbababcccccacbbbbbbccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcabbbbabbbbacbcbcccababbbbbaccbbccacaabbbbbbbbbbbbbbbbbbbbbbbbbcaccccccbabbbccaccbbbacbbbabacacabccbcbbbbbbbcabcbcbbccabacabaccccccbccaaababbbbaaabccbccacbbbbcacccccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccaabbcccccccccccccccccccccbcaaaacaacbcaccbaaaaaaaaaaaaaaaaccaabacbaaaccccbbbaaaaaaaaaaaaaaaaaaaaaaabaaccbccaaabcacbcbbacbaaaaaaaaaaaaaaaaaaaacacaaaaaaaaaaaaaaaaaacccacbbbbbbbabbbaaaaaaccccccbaabcbaaccacaaaaaaaaaabacccacccccccccccccccccccccbbbbbbcaaaaaaaaaaaaaaaaaaaaaaaaaaaaabccacaaaacbababcbbcbccbcabbaccabcbbbbbbcccccccccaaaaaaaaaaaaabcbabcbcbbccccaaaaaaaaaaaaaaaaaaaaaaaccaaaaaaaaacaaaaaaacccbabbcbbbbcbbcaaaaaaaaaaaaaaacbbaabaaaaaaaaaaaaaaaacaaaaaaaacaccaaaaaacaabbbbbbbbbbcccacabbaaaacbbccaaaaaaaacacabbcaccccccccccccccccccccbabacaaaabaaacabbccbcccccccccbbcabaabaaaaaaaaaaaaaaaabaaccbababcaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabaaccccccccccccbabcaacaacaaaacbccaccbccbcabbbbbaccbcacacacaabbbabacabccaaaaaaaaaaaaaaaaaaaaccabcccabcabccaaabaaabcbbbabaccacabaacbaabccbaccabcabacabaccccccccccccccccccbcbaacaacccccccccccccccccacccacabcabcccabbbbbbccbcbaacccbccbcabbcaccccbaaacabcccccccccccccccbaacacaacbacaacbbbbbbbbbbbbbbbbbbcbccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccabbaaaaaaaabbabcacbccbabcccbbbabacbbbbbbbbbbbbbbbbbbbbbbcbaaaaaabbbabbbbbbbbbbbbbbaaaaaaaaaaaaaaaaacbbbbbbaaabbacbaabcacabccacbbbccbacccccccccccbcbabccccaacbaaaaaaccbcaccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbccccccccccccccccccccccccabbbbbbaabbabbcbcccccccccccccccccababcbcacaacccccaabcaccbaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccbcbacacacbabccbcbaaaaaaaaaaaaaaaaaaaaaaabcbbbbbbbbbbbbbcbcccccccccccabaacbbccccbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbacccaaaaaaaaaaaaaaaaabcaacaababbcccccbacccababbbcabbbbbccabbbbbbbbbbccbcbbbbbbccaccccccccccccabbcbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaacbabbbbbbbbabcccccaabbbabbababbcbbbbbbbbbbbabaabbbccccbacbacbbbcbcbbabbacacaabcacbbcbbaaacbabbbacbbbbbaaaaaaaaaaaaaaaaaaaaaaaccaccbcbbbbbbbbbbbbbbbbbbabccccccccccccccbcccccccccacabaaaaabbbbacbaccccabbbbbbbbbbbbbbbbbbbbbbbbbbbacbcabcaaaaaaaaabbaaabaaaabaccbccaccabbbbbaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccabbbbbbbbbaccacacacaabbbbbbbbbabbcbabcaaabbbbbbbbbbbbbbbbbbbbbbbcbccaaacaacbbbaccbbbbbbbcbbaccaaaacbbbbbbbbbbbbbbbababbbbbcaccbaccbacbabbbbbbcbbbbccccccccbaabbbbbbbbbbbbbbbbbbbbbbbbbccbaaaaabacabbaaaabccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaaaacbbbbbbbbbbbbbbbacbbaaaaaabcccbbaaaaaaaaaabbcbcaabbaaaaaaaaacaaaaccccccccccccccccccaaaacbcccccccccccccccccccccaaaacbbbbbbbbbbccacaaaaababbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccabacccaacbbaccacbabaabccccccbbaaaabcacbbbbbbbbbbbbbbbbbbbbbabababbccaaababccacbaaccaccacaaaaaaaaaacbaccccacabcabccbbbaaabbbbbbbbccbbccccacbcccccccccccccccaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabccccccccccccccccccbcbbbbbbbbbccccabbbbbbbbbcccccccccccccccccccccccccccccccccccbbaaabcbbbcccbaaacbbbbbbbbbbbbbbbbbbbbbbbacacbcaacbbababcaabbbbbaabbabcccccccbccbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaacacacbccccccccccccccbbccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbcccccccccccbbbbbbbbbbbaacbcccaaaaaaaaaaaaaaaaacbcbacaaccbccccbbbbbbbbbaccaaaaaaaaaaaaaacabbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccccacbbaccccbbcbcbbbcbcccabcbbabacbbbbcbbacccccccccccccccccccccccccccccbcccccccccabcaaaaaaaaaacaabaacabbbbbbaccbcbacbbcccbaccccccccccccccccccccccacacaacaacaabbcbaacbaccacbccabbbacacaccaababbccccbabacacbbbbccaaaaaaaaaaaaacaaaaaaaaaaabbbbbbbbbbcaacabccabbabcbaaabacbacbbbbaabbbbbbbbbbbbbccaaccbbbbbbbbbbbbbbbbbbbbbbbbbbaccabaaaccbcacbbbabcbcacaccbbbcbcbbbbbbbbbcbbbbbbbbbbbbbbbbbbbbbbaccbaacabccbabacbcbcbcbbbbbbbbbbcacbbabccaacbccccccbcccccccabcaabcbcccccccccccccaabacbbbbbbbacaaaaaabcaacc",
                "cb.*babcbaca.*ab.*.*bacabc*b*cbcbabac*ac*bcabc*bcbacbacb*a*c*bab*c*b*c*a*b*a*b*cb*c*a*ba*c*b*a*bcb*c*aca*c*bcb*a*bab*c*bac*acb*abca*cb*a*bcb*a*cbcbca*ba*c*b*c*b*acbcbc*a*c*abcb*c*b*c*acac*ac*b*aba*bcabcababcabacbcab*c*a*b*c*a*b*abacac*ba*c*ab*a*c*abcb*cbabca*bcabc*bc*abab*c*a*c*bcbca*c*ab*acab*c*bc*b*cbcab*ca*c*ac*a*c*ab*c*ba*b*ca*cacacbabca*bcacac*a*bab*ac*ab*a*cababac*ba*ca*b*cbc*b*a*bac*bc*a*cbc*a*b*a*b*ab*abacbca*b*a*ba*b*cac*babacbaca*cba*b*ab*ac*a*bacb*abca*bacb*a*bc*bc*ac*b*a*bca*bc*bc*b*cb*cb*acabcaba*cbabab*a*ca*cb*c*a*b*acac*bca*bcac*ba*b*ab*abc*a*b*ab*cac*acbc*ac*abaca*b*a*cba*b*acb*c*a*bcacac*a*c*ba*cab*acb*abacabcac*a*caca*bca*bc*a*c*acbc*a*b*ac*b*a*cac*bc*babc*acacab*c*ba*cacac*bc*ba*bc*ac*acbabcb*a*cb*aba*bac*bcbcacb*cab*ac*b*a*cabcba*baca*b*ac*ac*a*c*b*aba*b*cac*b*c*ab*c*ac*bcab*ababacb*a*bc*abca*b*c*bcab*ab*c*baba*c*ab*a*cba*bacbabcac*abca*ca*babacacacbacb*ca*c*bca*c*a*bcbaca*ba*b*a*c*b*acb*caba*bc*b*acb*ab*c*abaca*cbcac*b*cbacbacab*ac*a*b*cacbc*a*cba*cb*ac*abc*bac*bacaba*cbcba*c*abc*baba*ba*cb*ac*aba*b*cabab*a*ca*cba*cbcbabcab*acbc*ba*b*ac*abc*ba*c*ac*ba*c*babcbcba*c*a*caca*bcbc*babcbac*b*abcbcb*ab*c*a*cacab*cab*ca*b*cbab*c*b*acb*abc*b*aca*b*cbab*ac*bab*c*bc*a*b*c*bac*a*cbc*a*ba*cb*abc*ababaca*cb*a*bc*b*acab*cacabc*ababac*b*acbab*a*ba*b*cab*a*ba*bca*b*cb*a*cab*a*ca*b*a*c*acb*c*a*babcacabcbabc*bcbaca*cb*cac*a*cbab*cb*acac*a*cabc*ab*cacbacb*acb*cacacb*abacac*a*c*bcbacabca*c*b*acabab*a*b*a*cacb*ca*c*ac*acbc*bc*b*c*a*bcabcabc*bc*ac*a*babc*bcacabcbcb*c*ba*cba*ca*c*bcbc*a*cbabc*bacbcacbcb*ca*cbac*a*c*a*c*a*c*abac*a*b*abac*bcbabc*a*c*bc*bc*a*bc*b*c*a*bc*acab*a*b*a*bc*a*bacbac*b*acabcacbc*b*abc*b*caca*bac*acac*bcb*c*ac*bacabc*ba*ba*b*c*a*c*b*c*ab*a*cb*c*b*ca*b*c*acbacbabcacbcba*c*a*c*bacbca*cacb*cb*acbc*bcba*ba*bcab*a*cba*c*b*acacb*cba*cba*c*abca*b*cac*a*b*a*b*a*b*a*cacba*bac*b*ab*cabc*ab*c*a*c*bc*bacac*bacbca*babc*b*a*c*bc*bcbcac*bcacb*c*bacb*aca*cb*a*b*acbab*acab*caba*c*ba*c*abca*cabcac*bcababa*bcbabcb*aca*cab*c*a*c*ac*ac*acbc*abc*baca*bcbab*cbcb*c*b*cbcba*b*abacabab*ac*ac*babc*abab*a*bc*bca*cb*c*bcbcb*c*bc*bca*c*abcb*abc*b*ca*b*cbac*bcb*abc*ab*ab*c*baba*cacb*cac*acac*ababa*c*aca*ca*ba*cbc*a*ba*c*bcb*aca*b*aba*bac*a*cab*cabca*cabc*ab*abca*cacac*ab*ab*a*c*bc*bab*a*c*a*c*bc*baca*bcbababc*acb*c*a*bcab*ab*acbcbc*abab*ac*b*c*aca*b*cac*bab*c*ac*b*acb*abacacabc*bcb*cabcbcb*c*abacabac*bc*a*bab*a*bc*bc*acb*cac*b*c*a*b*c*bca*ca*cbcac*ba*c*a*bacba*c*b*a*ba*c*bc*a*bcacbcb*acba*caca*c*acb*ab*a*c*ba*bcba*c*aca*bac*ac*b*ca*bc*aca*cbababcb*cbc*bcab*ac*abcb*c*a*bcbabcbcb*c*a*c*a*ca*c*bab*cb*cb*ca*cb*a*ba*ca*cac*a*ca*b*c*acab*a*cb*c*a*cacab*cac*babaca*ba*cab*c*bc*b*caba*ba*ba*c*bababca*ba*b*aba*c*babca*ca*ca*cbc*ac*bc*bcab*ac*bcacacaca*b*abacabc*a*c*abc*abcabc*a*ba*bcb*abac*acaba*cba*bc*bac*abcabacabac*bcba*ca*c*ac*acabcabc*ab*c*bcba*c*bc*bcab*cac*ba*cabc*ba*caca*cbaca*cb*cbc*ba*c*ab*a*b*abcacbc*babc*b*abacb*cba*b*ab*a*cb*a*b*acba*bcacabc*acb*c*bac*bcbabc*a*cba*c*bcac*b*ab*c*ab*a*b*ab*cbc*ababcbcaca*c*a*bcac*ba*b*c*bcbacacacbabc*bcba*bcb*cbc*aba*cb*c*b*a*b*ac*a*bca*ca*bab*c*bac*abab*cab*c*ab*c*bcb*c*ac*ab*cb*ab*a*cbab*abc*a*b*ab*abab*cb*aba*b*c*bacbacb*cbcb*ab*acaca*bcacb*cb*a*cbab*acb*a*c*ac*bcb*abc*bc*acaba*b*acbac*ab*acbcabca*b*a*ba*bac*bc*ac*ab*a*b*c*ab*ac*acacaca*b*ab*cbabca*b*cbc*a*ca*cb*ac*b*cb*ac*a*cb*abab*cac*bac*bacbab*cb*c*ba*b*c*ba*bacab*a*bc*b*a*cb*acb*a*bc*b*a*b*cbca*b*a*ca*c*a*cbc*a*cb*c*aca*bab*a*c*abac*a*cb*ac*acbaba*bc*b*a*bcacb*ababab*c*a*babc*acba*c*ac*aca*cbac*acabcabc*b*a*b*c*b*c*acbc*a*ca*c*ba*bc*bcb*c*ab*c*b*a*bcb*c*ba*cb*acacbca*cb*ababca*b*a*b*abc*bc*b*a*cacacbc*b*c*b*c*b*a*cbc*a*cbcbaca*c*bc*b*ac*a*cab*c*acb*ac*b*cbcb*cbc*abcb*abacb*cb*ac*bc*abca*ca*ba*cab*ac*bcbacb*c*bac*acaca*ca*ca*b*cba*cbac*acbc*ab*acacac*a*bab*c*babacacb*c*a*ca*b*ca*cabc*ab*abcba*bacbacb*a*b*c*a*c*b*ac*aba*c*bcacb*abcbcacac*b*cbcb*cb*ac*ba*cabc*babacbcbcbcb*cacb*abc*a*cbc*bc*abca*bcbc*a*bacb*aca*bca*c*"
        ));

//        Regex.isMatch("cbccccccccbabcbacabbabaabacabccbbcbcbabacccccccccccccccccccccaccbcabccccbcbacbacbbbaaccccbabbbbbbbbbbbbbbbbbbbccbbccaaaaaaaaaaaaaaaaaaaaabbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaabbbcbbccaaabaaaaaaaaaacccccccccccbbbbbbbbbbbbbbbaaaaaaabcbbccccacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccbcbbbaaababbbcccbaccacbbabcaaaaacbbaabcbbbbaacbcbcaaaaaaabaacccbbcccbbbacbcbccaaccabcbbccbbbbbbbbbcccacacccccccccccccccccaccccccccccccccccccccbbbbbbbbabaaaaaaaaaaabcabcababcabacbcabbccaabbcccaabbabacacccbaaaccabbbbbbbbbbbbbbbbbbaaccabcbbbbbbbcbabcaabcabccbccababbbbbbbbbccaacccccccccbcbcaaaccabbacabbccbcccbbcbcabbbbbcaaccacccccccccccccccccccaacccabbbbccbaabbbbcaacacacbabcaabcacaccaaaaaababbaccabbaaaacababaccccccccccccccccccccbaaaaaacaabbcbcccccccccccbbbbbbbbbbbbbbbbaabacccccccbccaaaaaaaaaaaaaaaacbcccaabbbbbbbbbbaabbabbbabacbcaaabbbaaabaabbcaccbabacbacaaacbaabbabbbbbbaccaaaaaaaaabacbbbabcaaabacbbbbbbbbaaabcccbccaccbbbbbbbbbbbaaaaabcaabccbccbbbbbbcbbbcbbacabcabaacbababbbbbbbbbbbbaacaaaaaaaaaaaaaaaaacbbcccaabbbbbbbbbbacacccbcaabcaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbabbabccaaabbabbbcaccacbccaccabacaabbaaaacbaabbacbbccaaabcacacccccaaccbaacabbbbbbacbbabacabcaccccaacacaaaabcaaabccccccaaccccacbccccccccccaabbbbbaccbbbbbbbbbbbbbbbbbbaacaccbcccccccccccbabccacacabbbbccbaaaacacaccbccbaabccaccacbabcbbaaaacbbabaabacccccccccccccccccccccccbcbcacbbbbbbbbbcabbaccbbbaacabcbaaaaaaaaaaaabacaaabbbaccccccccaccaaaaccbbabaabbcaccccbbccabbccaccbcabbababacbbbbbbbbbbbbbbbbbbaabcccccccccabcaabbb", "c.c*b.bc.aca.ba.aabacabccbbc*bc*bab.cc*a.c.*cccc.*.c.bab*c*bbc.a*a*b*a*.bc*b*cb.*aaa*.a*aac.c*cb*ba*a.b..*c.caca*aa*..ccbc.bba.*a*ac.baabcbbbbaa..cbcaa*b.*c*a");

//        Regex.isMatch("cbccccccccbabcbacabbabaabacabccbbcbcbabacccccccccccccccccccccaccbcabccccbcbacbacbbbaaccccbabbbbbbbbbbbbbbbbbbbccbbccaaaaaaaaaaaaaaaaaaaaabbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaabbbcbbccaaabaaaaaaaaaacccccccccccbbbbbbbbbbbbbbbaaaaaaabcbbccccacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccbcbbbaaababbbcccbaccacbbabcaaaaacbbaabcbbbbaacbcbcaaaaaaabaacccbbcccbbbacbcbccaaccabcbbccbbbbbbbbbcccacacccccccccccccccccaccccccccccccccccccccbbbbbbbbabaaaaaaaaaaabcabcababcabacbcabbccaabbcccaabbabacacccbaaaccabbbbbbbbbbbbbbbbbbaaccabcbbbbbbbcbabcaabcabccbccababbbbbbbbbccaacccccccccbcbcaaaccabbacabbccbcccbbcbcabbbbbcaaccacccccccccccccccccccaacccabbbbccbaabbbbcaacacacbabcaabcacaccaaaaaababbaccabbaaaacababaccccccccccccccccccccbaaaaaacaabbcbcccccccccccbbbbbbbbbbbbbbbbaabacccccccbccaaaaaaaaaaaaaaaacbcccaabbbbbbbbbbaabbabbbabacbcaaabbbaaabaabbcaccbabacbacaaacbaabbabbbbbbaccaaaaaaaaabacbbbabcaaabacbbbbbbbbaaabcccbccaccbbbbbbbbbbbaaaaabcaabccbccbbbbbbcbbbcbbacabcabaacbababbbbbbbbbbbbaacaaaaaaaaaaaaaaaaacbbcccaabbbbbbbbbbacacccbcaabcaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbabbabccaaabbabbbcaccacbccaccabacaabbaaaacbaabbacbbccaaabcacacccccaaccbaacabbbbbbacbbabacabcaccccaacacaaaabcaaabccccccaaccccacbccccccccccaabbbbbaccbbbbbbbbbbbbbbbbbbaacaccbcccccccccccbabccacacabbbbccbaaaacacaccbccbaabccaccacbabcbbaaaacbbabaabacccccccccccccccccccccccbcbcacbbbbbbbbbcabbaccbbbaacabcbaaaaaaaaaaaabacaaabbbaccccccccaccaaaaccbbabaabbcaccccbbccabbccaccbcabbababacbbbbbbbbbbbbbbbbbbaabcccccccccabcaabbbccbcabbbbabbbcccbabaaaaaaaaaaaaaccabbaacbaaabacbabcaccabcaaacaababacacacbacbbbbbbbbbbbbbbbbbbbbbbbbcaaccbcaaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcbacaabaaaabbbbbbbbbbbbbbbbbbbbbbbbaacccccccccccccbbacbbbcabaaaaabccbbbbbbbbbbbbbacbbabbcccabacaaacbcaccbbbbcbacbacabbacccaaabbbcacbcccaacbaacbbaccabcccbacccbacabaacbcbaacccabccccbabaaaabaacbbacccccccabaaaaaabbbcababbbbaaacaaaaaaaaaaaacbaacbcbabcabbacbcccccbaabbbbbaccccabcccbaaccccaccccccbaaccbabcbcbaaccccaaaacacaaaabcbccbabcbaccbbbabcbcbbabbcccaacacabbbcabbcaabbcbabbbbcccbbacbbbabccccccccccccccccccccccccccccccbbacaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcbabbaccbabbcccccccccbccaabbcccccccbaccaaaaaaaacbccccccccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaacbbbbbabcccababacaacbbaabccbbacabbcacabccababaccbbbbbbbbbacbabbaaabaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcabbaabaaaaaaaaaaabcaaabbbbbbbbbbbcbbbbbbbbbbbbaaacabbbaaacaaaaaaabbbaaccacbbbbcccaababcacabcbabccbcbacaacbbbcaccaacbabbbbbbcbbbacacccccaaaaaacabccccabbcacbacbbacbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcacacbbbabacacccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccbcbacabcaaaaaaaaaaaacccbbacababbaabbaaaaaacacbbcaaaaaaaaccaccacbccbccccbbccaabcabcabccbcccaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaababccccccccccccccccccccbcacabcbcbbccbaacbaacaaaaaaaccbcbccccccaacbabccbacbcacbcbbbcaaaaaaaaaaaaaaaaaacbaccaaaccaacccaaaccabacccaabbbbabacccccccccccccccccbcbabccaaaaaaaaaaaaacccccccccccccccbcccbccaabccbbcccccccccccccccccccccaaaabcccccccccccccccacabbaabbaabccccaaaaaaaaaaaaaaaaabacbaccbbacabcacbccccccbbbabccbbcacaaabacccccccccccccccccccccacaccbcbbbcccacccbacabccbaaabaabbccaaaccbbccccccccccabbbaacbbbbbbbbbbccbbbbbbbbbbcaaabbbbbbbbbbbbbbbbbbbbbccacbacbabcacbcbaaacccaaaaaacccbacbcaaaaaaaaaaaaaaaaacacbbcbbbacbcccbcbaaaabaabcabbbbbbbbbbbbbbbbbbbbbbbbbbbbbaacbaaccbbbbbbbbbbbbbbbbbbbbacacbbcbaaaaaaaaaaaaacbaaccabcaabbbbbcaccaabbaabbbaabbbbaacacbaabaccccbbbabbbbbbbbbbbbbbbbbbcabccabbccaaccccbccbacaccbacbcaababcccccccbbaaaccbccbcbcaccbcacbbbccbacbbacaaaacbbaaabbacbabbacabbcabaaccbaacccccccabcaaacabcaccbcababaabcbabcbbbbbacaaaaacabbbcccaaaccacccccccccccccaccacbccabccccbacaaaabcbabbbbbbbbbbcbcbbbccccbbbbbbbbbcbcbaaaaaaaaaaaaaaaaaaaabbbbabacababbaccaccbabccababbaabccbcaaaaacbbccbcbcbbbcccbccbcaaccabcbbabccbbcaaaaaaaabbcbaccccccbcbbbabcccccccccccccccccabbbbabbccbabaacacbbcacccccccccccccccccccccccccccccacaccababaacccccccccccccccccccccccacaacaabaacbccccccccccccccaabaaccbcbbbbbacaabbabaabaccaaacabbcabcaaaaaaaacabcccccccccccccccccabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabcaacacaccabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbbbbbbbbbbbbbaaaacccccccccbccccccbabbaaaaaccccccccaaaccbcccbacaaaaaaaaaaaaaaaabcbababcccccacbbbbbbccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcabbbbabbbbacbcbcccababbbbbaccbbccacaabbbbbbbbbbbbbbbbbbbbbbbbbcaccccccbabbbccaccbbbacbbbabacacabccbcbbbbbbbcabcbcbbccabacabaccccccbccaaababbbbaaabccbccacbbbbcacccccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccaabbcccccccccccccccccccccbcaaaacaacbcaccbaaaaaaaaaaaaaaaaccaabacbaaaccccbbbaaaaaaaaaaaaaaaaaaaaaaabaaccbccaaabcacbcbbacbaaaaaaaaaaaaaaaaaaaacacaaaaaaaaaaaaaaaaaacccacbbbbbbbabbbaaaaaaccccccbaabcbaaccacaaaaaaaaaabacccacccccccccccccccccccccbbbbbbcaaaaaaaaaaaaaaaaaaaaaaaaaaaaabccacaaaacbababcbbcbccbcabbaccabcbbbbbbcccccccccaaaaaaaaaaaaabcbabcbcbbccccaaaaaaaaaaaaaaaaaaaaaaaccaaaaaaaaacaaaaaaacccbabbcbbbbcbbcaaaaaaaaaaaaaaacbbaabaaaaaaaaaaaaaaaacaaaaaaaacaccaaaaaacaabbbbbbbbbbcccacabbaaaacbbccaaaaaaaacacabbcaccccccccccccccccccccbabacaaaabaaacabbccbcccccccccbbcabaabaaaaaaaaaaaaaaaabaaccbababcaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabaaccccccccccccbabcaacaacaaaacbccaccbccbcabbbbbaccbcacacacaabbbabacabccaaaaaaaaaaaaaaaaaaaaccabcccabcabccaaabaaabcbbbabaccacabaacbaabccbaccabcabacabaccccccccccccccccccbcbaacaacccccccccccccccccacccacabcabcccabbbbbbccbcbaacccbccbcabbcaccccbaaacabcccccccccccccccbaacacaacbacaacbbbbbbbbbbbbbbbbbbcbccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccabbaaaaaaaabbabcacbccbabcccbbbabacbbbbbbbbbbbbbbbbbbbbbbcbaaaaaabbbabbbbbbbbbbbbbbaaaaaaaaaaaaaaaaacbbbbbbaaabbacbaabcacabccacbbbccbacccccccccccbcbabccccaacbaaaaaaccbcaccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbccccccccccccccccccccccccabbbbbbaabbabbcbcccccccccccccccccababcbcacaacccccaabcaccbaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccbcbacacacbabccbcbaaaaaaaaaaaaaaaaaaaaaaabcbbbbbbbbbbbbbcbcccccccccccabaacbbccccbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbacccaaaaaaaaaaaaaaaaabcaacaababbcccccbacccababbbcabbbbbccabbbbbbbbbbccbcbbbbbbccaccccccccccccabbcbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaacbabbbbbbbbabcccccaabbbabbababbcbbbbbbbbbbbabaabbbccccbacbacbbbcbcbbabbacacaabcacbbcbbaaacbabbbacbbbbbaaaaaaaaaaaaaaaaaaaaaaaccaccbcbbbbbbbbbbbbbbbbbbabccccccccccccccbcccccccccacabaaaaabbbbacbaccccabbbbbbbbbbbbbbbbbbbbbbbbbbbacbcabcaaaaaaaaabbaaabaaaabaccbccaccabbbbbaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccabbbbbbbbbaccacacacaabbbbbbbbbabbcbabcaaabbbbbbbbbbbbbbbbbbbbbbbcbccaaacaacbbbaccbbbbbbbcbbaccaaaacbbbbbbbbbbbbbbbababbbbbcaccbaccbacbabbbbbbcbbbbccccccccbaabbbbbbbbbbbbbbbbbbbbbbbbbccbaaaaabacabbaaaabccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaaaacbbbbbbbbbbbbbbbacbbaaaaaabcccbbaaaaaaaaaabbcbcaabbaaaaaaaaacaaaaccccccccccccccccccaaaacbcccccccccccccccccccccaaaacbbbbbbbbbbccacaaaaababbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccabacccaacbbaccacbabaabccccccbbaaaabcacbbbbbbbbbbbbbbbbbbbbbabababbccaaababccacbaaccaccacaaaaaaaaaacbaccccacabcabccbbbaaabbbbbbbbccbbccccacbcccccccccccccccaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabccccccccccccccccccbcbbbbbbbbbccccabbbbbbbbbcccccccccccccccccccccccccccccccccccbbaaabcbbbcccbaaacbbbbbbbbbbbbbbbbbbbbbbbacacbcaacbbababcaabbbbbaabbabcccccccbccbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaacacacbccccccccccccccbbccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbcccccccccccbbbbbbbbbbbaacbcccaaaaaaaaaaaaaaaaacbcbacaaccbccccbbbbbbbbbaccaaaaaaaaaaaaaacabbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccccacbbaccccbbcbcbbbcbcccabcbbabacbbbbcbbacccccccccccccccccccccccccccccbcccccccccabcaaaaaaaaaacaabaacabbbbbbaccbcbacbbcccbaccccccccccccccccccccccacacaacaacaabbcbaacbaccacbccabbbacacaccaababbccccbabacacbbbbccaaaaaaaaaaaaacaaaaaaaaaaabbbbbbbbbbcaacabccabbabcbaaabacbacbbbbaabbbbbbbbbbbbbccaaccbbbbbbbbbbbbbbbbbbbbbbbbbbaccabaaaccbcacbbbabcbcacaccbbbcbcbbbbbbbbbcbbbbbbbbbbbbbbbbbbbbbbaccbaacabccbabacbcbcbcbbbbbbbbbbcacbbabccaacbccccccbcccccccabcaabcbcccccccccccccaabacbbbbbbbacaaaaaabcaacc",
//                  "c.c*b.bc.aca.ba.aabacabccbbc*bc*bab.cc*a.c.*cccc.*.c.bab*c*bbc.a*a*b*a*.bc*b*cb.*aaa*.a*aac.c*cb*ba*a.b..*c.caca*aa*..ccbc.bba.*a*ac.baabcbbbbaa..cbcaa*b.*c*abcb*ccb*cc.aca.c*cac*b*..a*b.*a*.*aaaccab.b*..*b*c.*b.b*b.cc.ac*c..bc.*..*abbcc.c.c*b..bcab*bcaaccac*a*acc.ab*c*.b..*ac*c.a*caabbc.*c*.bb*.aabac*.c*ca*c.cccaab*.*cb*ac.aac*cbaa..*b*ac.a*a*.a..bb..c.aab..b*.*abc..bc.ac.b*a*..c*.*a.bcca*..*b*bcbbbcbb.*a.babab*aaca*c.bc*ccaab*c*bab*c.cccbc*..*.cac.ba*a*ab.bbab..*ccac.c...ca.acaabba*.baa.bac.*bca.acccc..ac..*b*.b.cba*babacabcac*a...caa.ab.aaa.c*aaccccacbc*aabbb*ac.*.bb*aacac*bc*b*ba..*a.acabb.b.*c.ba..ac.caccbc.*cb.ab.*.a.*.b.c*..bc.cb*b*ca.ba.cbbbaacab.ba*a.*.*bbac*.ac*a*ccb.a.*b.ba.ac...bb*.aa.c*.cab..abbbc*.cab*b.bbbcccba.a*a.*baa.b.c.*cac*cac..c.b*...ccb*ca.*c.*aa*bcb..aab.a..b*a.c*.b.*cbb.....aaaa.ccb*b..bb.b*.cc.*abaca*cbcac.bb.ba*cbac.acab.*a.*cccbac*.bac.baa...baaccca.c.ccb.b...*a.*bac*.*abaa*bb*ca.abbbba*ca*cbaacbc.a.*.ba*b*accccab.ccbaacc*.ac*cb.accba.cbc..acc*aa*caca*...cc.*b*.*..ccc.acacab.bca.*b*bc.*cb.ba.c*c*b.acaa*b*cba.ba.cba..cc*.cc.*c*ba..aaaaaaaac.c*.a*.a*cb*abb*c*cabab.*a.b..abcc.bacab.c.cab.*ab.bac*b*.cbab.aaaba..*ab*c.b.a.bb*a*bca.abb*bbcb*aa*.a..ba.acaaa*b.ba..*b..*.c*.a*a..b.*c..*bacbb.cb*.*.*ccca*acc*cb*cbacabca*cc..bacababb.abbaa*aaaaca.bbcaa*cc.*a*.babcc*bcacabcbc...cbaacbaaca*ccb.bc*c*..ab*c.*bc..acbcac...bbca*.ba.*aaa.c.*bac*bcbabcca*c*.c*b.*b.c*c*cc.*c*.*.c.a*abacb.c.*bc.c.aab.cc*.*ab.*bbcc*b*c*abb*aacbb*ccb*caaabb*bccacbacbab.b*..bcbaaa.cc.a.aa*c*b.cbcaa*..cbb.*abb*a.*a.c.b*ac.cbb.b*a*c.aac.*bba.....aaba.cccbb*abb*.ab.*c*cabbcc.ac*a*b..bac...b.*c*bb..accb..*ccba.bbaca*cbbaa..*abbca.*c*abcaaab*..bcaccb.a.*bbbbaca.*cc*.*.cbab*.cb*.bbbcc*b*..c.a*b..babacaba.*a.*ccb..c.bbcccb.*caa.c.*bbca...*aaa*bbcba.ccc*.cb.*.abc*abb*babbc.b.baacacbb.acc*cac.*c*.caac.ab.ac.c*.c*...acc..bbbbba.*..a.c.*aaacabbcabca*ca.cc*c*.ab*abcaaca.accab*.bb*ba*c*.c*babba*c*c*.*aca*b.b..ab.c*acb*cca*.a*abc.b*.b*.c.cbcc.*ababbb*a.cbb.c....b*.c.c*.c...*c*cbbb.cb*.ba.*.cab..bcb*b.*c.bacab.c*bc...a.ab*baa.bcc..c.cb.bbcac*b*ccc*a.bb.cccc*b.*a.c.acbc.cc.a*a..c.*.ba*baa..*a.bcb.acb..a*ca*.a*.cc.acb*.a.*b.a*c*b.a.c.b*aac*c.caa*..cccac*.bb*.a*bc..ca*.*ccabcb*c*a*bcb.b.b.bbcc.ca*cca*caa*c.*c*bbbbcb*ca*cb*aa.a*caa*.*b*.cca.abbaaaa.....a*.*.*..*.acc*..*.ba.aaa.baa..abbc..c*b*.*.a*ba.*c*aaba*aa*b*..aac*babc.aca.*caccbccbcab*a.*bba.*.bc*c.a*acc.bcc.abcabcca*b.*aa..cbbb.*c*b..a.caac*c...c.ca.cabc.c.b*ccbcb.accc.c*bcabb.a.cccbaa.ca.c*ccba..*aacbaca*acb*cbcc..aa*accab.aaaa*b.*bab.ccbb*baba.bbb*.ba*..*b*ab*aa*.bb*aaabc*bacb.a.*abc.a.b..c.bac*c..bcb.bccc.aac.a*accbcac*b*abbbbc*ab*baab.*bc*cab.bcbcaa*caac*aabc*....a*b*c*bc*bac.cac.*a*b.bb*.bc..c*abaac*bbc.c.b*aa*a*b..a.ccaa*.*c*.*...abbbb.cc.bbb.b*.*cb*c.ac*a.bcb*.b*a*a*cbab*abc*a.*bab*babbc.*b*ba.aabbbc*bacb.cb*bbc.....*bacb*ba*a*a.*b*abc*.c*ccaca.c*.a*a*b.bbacbaccccab*..*c.bcaa*b.a*b*a*a*baccbc.acc.b*bb*aaab*ccabb*bac.a.acaca.bb*aa*.bc.a.*aab*b.c..*..c.*aa.cb*b*ab.bb*bbcaccb..cba..ab*b*bcbbb*b.c*baabbb.bb*bccba*a.ba...baaaabc*cb*b*a.aacbb*.cb*a*.aabcc*.cb.a*a*bbcbcaabba*c.aa.c*a*cbc*..a.acb*..a.aaaa...*.bbb*b*a*ac.abaccc.a.b.accacb..a.bc*c*bba*bcacbb*b..babab.c.aaabab...c.aa..accaca*acbac*acab.abc.bb*aaab*bb.*bccbb.c..ac.cc*aa*c.a*a*c*.ba*..cc*.cb*cc.*b*c*bbaa.bcb.bcc.ba..c.b*b*.cac.caac...bab..ab*.*c.cccccb.a*cb*a*.*c.c*c.cbc*cb.c*bbb.b*c*b*aac.cc*aa*.b.*caac*.ccccb*b*a..aa*.cabbb*c*.cbb.c*.*.*c*c*bc*abca*acaaba..*.ab*.c.*bb.ccbac*cc*.*aba.b.c.*b.cacb.*a*ca*b*.a.cab...bbabcbaaa.a.*.b.cb*aab*cc.accb*.acc.ba..*bcbca.accb*bbb*.bcb*cb*.ccb.*b*c.cb..b*cc.a.bc*cbc*c..caabc.c*a*..cb*aca*bc....");
    }
}
