package andrews.ucalc;

public final class Chars {
	public static final Long s2i(final String w, int i, int base) {
        return c2i(w.charAt(i), base);
    }
    public static final Long c2i(final Character w, int base) {
        return Long.valueOf(Character.toString(w), base);
    }
}
