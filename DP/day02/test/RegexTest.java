import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RegexTest {
    @Test
    public void testRegex1() {
        assertThat(Regex.isMatch("aa", "a"), is(false));
    }

    @Test
    public void testRegex2() {
        assertThat(Regex.isMatch("aaa", "aa"), is(false));
    }

    @Test
    public void testRegex3() {
        assertThat(Regex.isMatch("aaa", "aa."), is(true));
    }

    @Test
    public void testRegex4() {
        assertThat(Regex.isMatch("aaa", "a*"), is(true));
    }

    @Test
    public void testRegex5() {
        assertThat(Regex.isMatch("aaa", ".*"), is(true));
    }

    @Test
    public void testRegex6() {
        assertThat(Regex.isMatch("aab", "c*a*b"), is(true));
    }

    @Test
    public void testRegex7() {
        assertThat(Regex.isMatch("aab", "c*a*b"), is(true));
    }

    @Test
    public void testRegex8() {
        assertThat(Regex.isMatch("aaa", "aaa"), is(true));
    }

    @Test
    public void testRegex9() {
        assertThat(Regex.isMatch("aaa", "a.a"), is(true));
    }

    @Test
    public void testRegex10() {
        assertThat(Regex.isMatch("b", "b.*c"), is(false));
    }

    @Test
    public void testRegex11() {
        assertThat(Regex.isMatch("aaa", "ab*ac*a"), is(true));
    }

    @Test
    public void testRegex12() {
        assertThat(Regex.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"), is(true));
    }

    @Test
    public void testRegex13() {
        assertThat(Regex.isMatch("bbba", "..a*"), is(false));
    }

    @Test
    public void testRegex14() {
        assertThat(Regex.isMatch("bbba", "..*a"), is(true));
    }

    @Test
    public void testRegex15() {
        assertThat(Regex.isMatch("bbggab", "b.*c*"), is(true));
    }

    @Test
    public void testRegex16() {
        assertThat(Regex.isMatch("bbggab", "b.*c"), is(false));
    }

    @Test
    public void testRegex17() {
        assertThat(Regex.isMatch("bbggabcc", "b.*c"), is(true));
    }

    @Test
    public void testRegex18() {
        assertThat(Regex.isMatch("cbccccccccbabcbacabbabaabacabccbbcbcbabacccccccccccccccccccccaccbcabccccbcbacbacbbbaaccccbabbbbbbbbbbbbbbbbbbbccbbccaaaaaaaaaaaaaaaaaaaaabbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaabbbcbbccaaabaaaaaaaaaacccccccccccbbbbbbbbbbbbbbbaaaaaaabcbbccccacaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccbcbbbaaababbbcccbaccacbbabcaaaaacbbaabcbbbbaacbcbcaaaaaaabaacccbbcccbbbacbcbccaaccabcbbccbbbbbbbbbcccacacccccccccccccccccaccccccccccccccccccccbbbbbbbbabaaaaaaaaaaabcabcababcabacbcabbccaabbcccaabbabacacccbaaaccabbbbbbbbbbbbbbbbbbaaccabcbbbbbbbcbabcaabcabccbccababbbbbbbbbccaacccccccccbcbcaaaccabbacabbccbcccbbcbcabbbbbcaaccacccccccccccccccccccaacccabbbbccbaabbbbcaacacacbabcaabcacaccaaaaaababbaccabbaaaacababaccccccccccccccccccccbaaaaaacaabbcbcccccccccccbbbbbbbbbbbbbbbbaabacccccccbccaaaaaaaaaaaaaaaacbcccaabbbbbbbbbbaabbabbbabacbcaaabbbaaabaabbcaccbabacbacaaacbaabbabbbbbbaccaaaaaaaaabacbbbabcaaabacbbbbbbbbaaabcccbccaccbbbbbbbbbbbaaaaabcaabccbccbbbbbbcbbbcbbacabcabaacbababbbbbbbbbbbbaacaaaaaaaaaaaaaaaaacbbcccaabbbbbbbbbbacacccbcaabcaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbabbabccaaabbabbbcaccacbccaccabacaabbaaaacbaabbacbbccaaabcacacccccaaccbaacabbbbbbacbbabacabcaccccaacacaaaabcaaabccccccaaccccacbccccccccccaabbbbbaccbbbbbbbbbbbbbbbbbbaacaccbcccccccccccbabccacacabbbbccbaaaacacaccbccbaabccaccacbabcbbaaaacbbabaabacccccccccccccccccccccccbcbcacbbbbbbbbbcabbaccbbbaacabcbaaaaaaaaaaaabacaaabbbaccccccccaccaaaaccbbabaabbcaccccbbccabbccaccbcabbababacbbbbbbbbbbbbbbbbbbaabcccccccccabcaabbbccbcabbbbabbbcccbabaaaaaaaaaaaaaccabbaacbaaabacbabcaccabcaaacaababacacacbacbbbbbbbbbbbbbbbbbbbbbbbbcaaccbcaaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcbacaabaaaabbbbbbbbbbbbbbbbbbbbbbbbaacccccccccccccbbacbbbcabaaaaabccbbbbbbbbbbbbbacbbabbcccabacaaacbcaccbbbbcbacbacabbacccaaabbbcacbcccaacbaacbbaccabcccbacccbacabaacbcbaacccabccccbabaaaabaacbbacccccccabaaaaaabbbcababbbbaaacaaaaaaaaaaaacbaacbcbabcabbacbcccccbaabbbbbaccccabcccbaaccccaccccccbaaccbabcbcbaaccccaaaacacaaaabcbccbabcbaccbbbabcbcbbabbcccaacacabbbcabbcaabbcbabbbbcccbbacbbbabccccccccccccccccccccccccccccccbbacaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcbabbaccbabbcccccccccbccaabbcccccccbaccaaaaaaaacbccccccccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaacbbbbbabcccababacaacbbaabccbbacabbcacabccababaccbbbbbbbbbacbabbaaabaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcabbaabaaaaaaaaaaabcaaabbbbbbbbbbbcbbbbbbbbbbbbaaacabbbaaacaaaaaaabbbaaccacbbbbcccaababcacabcbabccbcbacaacbbbcaccaacbabbbbbbcbbbacacccccaaaaaacabccccabbcacbacbbacbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcacacbbbabacacccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccbcbacabcaaaaaaaaaaaacccbbacababbaabbaaaaaacacbbcaaaaaaaaccaccacbccbccccbbccaabcabcabccbcccaccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaababccccccccccccccccccccbcacabcbcbbccbaacbaacaaaaaaaccbcbccccccaacbabccbacbcacbcbbbcaaaaaaaaaaaaaaaaaacbaccaaaccaacccaaaccabacccaabbbbabacccccccccccccccccbcbabccaaaaaaaaaaaaacccccccccccccccbcccbccaabccbbcccccccccccccccccccccaaaabcccccccccccccccacabbaabbaabccccaaaaaaaaaaaaaaaaabacbaccbbacabcacbccccccbbbabccbbcacaaabacccccccccccccccccccccacaccbcbbbcccacccbacabccbaaabaabbccaaaccbbccccccccccabbbaacbbbbbbbbbbccbbbbbbbbbbcaaabbbbbbbbbbbbbbbbbbbbbccacbacbabcacbcbaaacccaaaaaacccbacbcaaaaaaaaaaaaaaaaacacbbcbbbacbcccbcbaaaabaabcabbbbbbbbbbbbbbbbbbbbbbbbbbbbbaacbaaccbbbbbbbbbbbbbbbbbbbbacacbbcbaaaaaaaaaaaaacbaaccabcaabbbbbcaccaabbaabbbaabbbbaacacbaabaccccbbbabbbbbbbbbbbbbbbbbbcabccabbccaaccccbccbacaccbacbcaababcccccccbbaaaccbccbcbcaccbcacbbbccbacbbacaaaacbbaaabbacbabbacabbcabaaccbaacccccccabcaaacabcaccbcababaabcbabcbbbbbacaaaaacabbbcccaaaccacccccccccccccaccacbccabccccbacaaaabcbabbbbbbbbbbcbcbbbccccbbbbbbbbbcbcbaaaaaaaaaaaaaaaaaaaabbbbabacababbaccaccbabccababbaabccbcaaaaacbbccbcbcbbbcccbccbcaaccabcbbabccbbcaaaaaaaabbcbaccccccbcbbbabcccccccccccccccccabbbbabbccbabaacacbbcacccccccccccccccccccccccccccccacaccababaacccccccccccccccccccccccacaacaabaacbccccccccccccccaabaaccbcbbbbbacaabbabaabaccaaacabbcabcaaaaaaaacabcccccccccccccccccabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabcaacacaccabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbbbbbbbbbbbbbaaaacccccccccbccccccbabbaaaaaccccccccaaaccbcccbacaaaaaaaaaaaaaaaabcbababcccccacbbbbbbccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcabbbbabbbbacbcbcccababbbbbaccbbccacaabbbbbbbbbbbbbbbbbbbbbbbbbcaccccccbabbbccaccbbbacbbbabacacabccbcbbbbbbbcabcbcbbccabacabaccccccbccaaababbbbaaabccbccacbbbbcacccccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccaabbcccccccccccccccccccccbcaaaacaacbcaccbaaaaaaaaaaaaaaaaccaabacbaaaccccbbbaaaaaaaaaaaaaaaaaaaaaaabaaccbccaaabcacbcbbacbaaaaaaaaaaaaaaaaaaaacacaaaaaaaaaaaaaaaaaacccacbbbbbbbabbbaaaaaaccccccbaabcbaaccacaaaaaaaaaabacccacccccccccccccccccccccbbbbbbcaaaaaaaaaaaaaaaaaaaaaaaaaaaaabccacaaaacbababcbbcbccbcabbaccabcbbbbbbcccccccccaaaaaaaaaaaaabcbabcbcbbccccaaaaaaaaaaaaaaaaaaaaaaaccaaaaaaaaacaaaaaaacccbabbcbbbbcbbcaaaaaaaaaaaaaaacbbaabaaaaaaaaaaaaaaaacaaaaaaaacaccaaaaaacaabbbbbbbbbbcccacabbaaaacbbccaaaaaaaacacabbcaccccccccccccccccccccbabacaaaabaaacabbccbcccccccccbbcabaabaaaaaaaaaaaaaaaabaaccbababcaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabaaccccccccccccbabcaacaacaaaacbccaccbccbcabbbbbaccbcacacacaabbbabacabccaaaaaaaaaaaaaaaaaaaaccabcccabcabccaaabaaabcbbbabaccacabaacbaabccbaccabcabacabaccccccccccccccccccbcbaacaacccccccccccccccccacccacabcabcccabbbbbbccbcbaacccbccbcabbcaccccbaaacabcccccccccccccccbaacacaacbacaacbbbbbbbbbbbbbbbbbbcbccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccabbaaaaaaaabbabcacbccbabcccbbbabacbbbbbbbbbbbbbbbbbbbbbbcbaaaaaabbbabbbbbbbbbbbbbbaaaaaaaaaaaaaaaaacbbbbbbaaabbacbaabcacabccacbbbccbacccccccccccbcbabccccaacbaaaaaaccbcaccccccccccccbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbccccccccccccccccccccccccabbbbbbaabbabbcbcccccccccccccccccababcbcacaacccccaabcaccbaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccbcbacacacbabccbcbaaaaaaaaaaaaaaaaaaaaaaabcbbbbbbbbbbbbbcbcccccccccccabaacbbccccbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbacccaaaaaaaaaaaaaaaaabcaacaababbcccccbacccababbbcabbbbbccabbbbbbbbbbccbcbbbbbbccaccccccccccccabbcbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaacbabbbbbbbbabcccccaabbbabbababbcbbbbbbbbbbbabaabbbccccbacbacbbbcbcbbabbacacaabcacbbcbbaaacbabbbacbbbbbaaaaaaaaaaaaaaaaaaaaaaaccaccbcbbbbbbbbbbbbbbbbbbabccccccccccccccbcccccccccacabaaaaabbbbacbaccccabbbbbbbbbbbbbbbbbbbbbbbbbbbacbcabcaaaaaaaaabbaaabaaaabaccbccaccabbbbbaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccabbbbbbbbbaccacacacaabbbbbbbbbabbcbabcaaabbbbbbbbbbbbbbbbbbbbbbbcbccaaacaacbbbaccbbbbbbbcbbaccaaaacbbbbbbbbbbbbbbbababbbbbcaccbaccbacbabbbbbbcbbbbccccccccbaabbbbbbbbbbbbbbbbbbbbbbbbbccbaaaaabacabbaaaabccccbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbaaaacbbbbbbbbbbbbbbbacbbaaaaaabcccbbaaaaaaaaaabbcbcaabbaaaaaaaaacaaaaccccccccccccccccccaaaacbcccccccccccccccccccccaaaacbbbbbbbbbbccacaaaaababbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccabacccaacbbaccacbabaabccccccbbaaaabcacbbbbbbbbbbbbbbbbbbbbbabababbccaaababccacbaaccaccacaaaaaaaaaacbaccccacabcabccbbbaaabbbbbbbbccbbccccacbcccccccccccccccaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaccbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabccccccccccccccccccbcbbbbbbbbbccccabbbbbbbbbcccccccccccccccccccccccccccccccccccbbaaabcbbbcccbaaacbbbbbbbbbbbbbbbbbbbbbbbacacbcaacbbababcaabbbbbaabbabcccccccbccbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaacacacbccccccccccccccbbccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbcccccccccccbbbbbbbbbbbaacbcccaaaaaaaaaaaaaaaaacbcbacaaccbccccbbbbbbbbbaccaaaaaaaaaaaaaacabbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccccacbbaccccbbcbcbbbcbcccabcbbabacbbbbcbbacccccccccccccccccccccccccccccbcccccccccabcaaaaaaaaaacaabaacabbbbbbaccbcbacbbcccbaccccccccccccccccccccccacacaacaacaabbcbaacbaccacbccabbbacacaccaababbccccbabacacbbbbccaaaaaaaaaaaaacaaaaaaaaaaabbbbbbbbbbcaacabccabbabcbaaabacbacbbbbaabbbbbbbbbbbbbccaaccbbbbbbbbbbbbbbbbbbbbbbbbbbaccabaaaccbcacbbbabcbcacaccbbbcbcbbbbbbbbbcbbbbbbbbbbbbbbbbbbbbbbaccbaacabccbabacbcbcbcbbbbbbbbbbcacbbabccaacbccccccbcccccccabcaabcbcccccccccccccaabacbbbbbbbacaaaaaabcaacc", "c.c*b.bc.aca.ba.aabacabccbbc*bc*bab.cc*a.c.*cccc.*.c.bab*c*bbc.a*a*b*a*.bc*b*cb.*aaa*.a*aac.c*cb*ba*a.b..*c.caca*aa*..ccbc.bba.*a*ac.baabcbbbbaa..cbcaa*b.*c*abcb*ccb*cc.aca.c*cac*b*..a*b.*a*.*aaaccab.b*..*b*c.*b.b*b.cc.ac*c..bc.*..*abbcc.c.c*b..bcab*bcaaccac*a*acc.ab*c*.b..*ac*c.a*caabbc.*c*.bb*.aabac*.c*ca*c.cccaab*.*cb*ac.aac*cbaa..*b*ac.a*a*.a..bb..c.aab..b*.*abc..bc.ac.b*a*..c*.*a.bcca*..*b*bcbbbcbb.*a.babab*aaca*c.bc*ccaab*c*bab*c.cccbc*..*.cac.ba*a*ab.bbab..*ccac.c...ca.acaabba*.baa.bac.*bca.acccc..ac..*b*.b.cba*babacabcac*a...caa.ab.aaa.c*aaccccacbc*aabbb*ac.*.bb*aacac*bc*b*ba..*a.acabb.b.*c.ba..ac.caccbc.*cb.ab.*.a.*.b.c*..bc.cb*b*ca.ba.cbbbaacab.ba*a.*.*bbac*.ac*a*ccb.a.*b.ba.ac...bb*.aa.c*.cab..abbbc*.cab*b.bbbcccba.a*a.*baa.b.c.*cac*cac..c.b*...ccb*ca.*c.*aa*bcb..aab.a..b*a.c*.b.*cbb.....aaaa.ccb*b..bb.b*.cc.*abaca*cbcac.bb.ba*cbac.acab.*a.*cccbac*.bac.baa...baaccca.c.ccb.b...*a.*bac*.*abaa*bb*ca.abbbba*ca*cbaacbc.a.*.ba*b*accccab.ccbaacc*.ac*cb.accba.cbc..acc*aa*caca*...cc.*b*.*..ccc.acacab.bca.*b*bc.*cb.ba.c*c*b.acaa*b*cba.ba.cba..cc*.cc.*c*ba..aaaaaaaac.c*.a*.a*cb*abb*c*cabab.*a.b..abcc.bacab.c.cab.*ab.bac*b*.cbab.aaaba..*ab*c.b.a.bb*a*bca.abb*bbcb*aa*.a..ba.acaaa*b.ba..*b..*.c*.a*a..b.*c..*bacbb.cb*.*.*ccca*acc*cb*cbacabca*cc..bacababb.abbaa*aaaaca.bbcaa*cc.*a*.babcc*bcacabcbc...cbaacbaaca*ccb.bc*c*..ab*c.*bc..acbcac...bbca*.ba.*aaa.c.*bac*bcbabcca*c*.c*b.*b.c*c*cc.*c*.*.c.a*abacb.c.*bc.c.aab.cc*.*ab.*bbcc*b*c*abb*aacbb*ccb*caaabb*bccacbacbab.b*..bcbaaa.cc.a.aa*c*b.cbcaa*..cbb.*abb*a.*a.c.b*ac.cbb.b*a*c.aac.*bba.....aaba.cccbb*abb*.ab.*c*cabbcc.ac*a*b..bac...b.*c*bb..accb..*ccba.bbaca*cbbaa..*abbca.*c*abcaaab*..bcaccb.a.*bbbbaca.*cc*.*.cbab*.cb*.bbbcc*b*..c.a*b..babacaba.*a.*ccb..c.bbcccb.*caa.c.*bbca...*aaa*bbcba.ccc*.cb.*.abc*abb*babbc.b.baacacbb.acc*cac.*c*.caac.ab.ac.c*.c*...acc..bbbbba.*..a.c.*aaacabbcabca*ca.cc*c*.ab*abcaaca.accab*.bb*ba*c*.c*babba*c*c*.*aca*b.b..ab.c*acb*cca*.a*abc.b*.b*.c.cbcc.*ababbb*a.cbb.c....b*.c.c*.c...*c*cbbb.cb*.ba.*.cab..bcb*b.*c.bacab.c*bc...a.ab*baa.bcc..c.cb.bbcac*b*ccc*a.bb.cccc*b.*a.c.acbc.cc.a*a..c.*.ba*baa..*a.bcb.acb..a*ca*.a*.cc.acb*.a.*b.a*c*b.a.c.b*aac*c.caa*..cccac*.bb*.a*bc..ca*.*ccabcb*c*a*bcb.b.b.bbcc.ca*cca*caa*c.*c*bbbbcb*ca*cb*aa.a*caa*.*b*.cca.abbaaaa.....a*.*.*..*.acc*..*.ba.aaa.baa..abbc..c*b*.*.a*ba.*c*aaba*aa*b*..aac*babc.aca.*caccbccbcab*a.*bba.*.bc*c.a*acc.bcc.abcabcca*b.*aa..cbbb.*c*b..a.caac*c...c.ca.cabc.c.b*ccbcb.accc.c*bcabb.a.cccbaa.ca.c*ccba..*aacbaca*acb*cbcc..aa*accab.aaaa*b.*bab.ccbb*baba.bbb*.ba*..*b*ab*aa*.bb*aaabc*bacb.a.*abc.a.b..c.bac*c..bcb.bccc.aac.a*accbcac*b*abbbbc*ab*baab.*bc*cab.bcbcaa*caac*aabc*....a*b*c*bc*bac.cac.*a*b.bb*.bc..c*abaac*bbc.c.b*aa*a*b..a.ccaa*.*c*.*...abbbb.cc.bbb.b*.*cb*c.ac*a.bcb*.b*a*a*cbab*abc*a.*bab*babbc.*b*ba.aabbbc*bacb.cb*bbc.....*bacb*ba*a*a.*b*abc*.c*ccaca.c*.a*a*b.bbacbaccccab*..*c.bcaa*b.a*b*a*a*baccbc.acc.b*bb*aaab*ccabb*bac.a.acaca.bb*aa*.bc.a.*aab*b.c..*..c.*aa.cb*b*ab.bb*bbcaccb..cba..ab*b*bcbbb*b.c*baabbb.bb*bccba*a.ba...baaaabc*cb*b*a.aacbb*.cb*a*.aabcc*.cb.a*a*bbcbcaabba*c.aa.c*a*cbc*..a.acb*..a.aaaa...*.bbb*b*a*ac.abaccc.a.b.accacb..a.bc*c*bba*bcacbb*b..babab.c.aaabab...c.aa..accaca*acbac*acab.abc.bb*aaab*bb.*bccbb.c..ac.cc*aa*c.a*a*c*.ba*..cc*.cb*cc.*b*c*bbaa.bcb.bcc.ba..c.b*b*.cac.caac...bab..ab*.*c.cccccb.a*cb*a*.*c.c*c.cbc*cb.c*bbb.b*c*b*aac.cc*aa*.b.*caac*.ccccb*b*a..aa*.cabbb*c*.cbb.c*.*.*c*c*bc*abca*acaaba..*.ab*.c.*bb.ccbac*cc*.*aba.b.c.*b.cacb.*a*ca*b*.a.cab...bbabcbaaa.a.*.b.cb*aab*cc.accb*.acc.ba..*bcbca.accb*bbb*.bcb*cb*.ccb.*b*c.cb..b*cc.a.bc*cbc*c..caabc.c*a*..cb*aca*bc...."), is(true));
    }
}
