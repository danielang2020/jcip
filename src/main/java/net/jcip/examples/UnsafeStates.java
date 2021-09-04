package net.jcip.examples;

/**
 * UnsafeStates
 * <p/>
 * Allowing internal mutable state to escape
 *
 * @author Brian Goetz and Tim Peierls
 */
class UnsafeStates {
    private String[] states = new String[]{
        "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }

	public static void main(String[] args) {
		UnsafeStates us = new UnsafeStates();
		String[] copyStates = us.getStates();
		copyStates[0] = "You don't know"; // Here the change to `copyStates` also affect the `states` variable since they're both reference to the same array.
//		for (String s : copyStates) {
//			s = "you don't know";
//		}
		for (String s : us.getStates()) {
			System.out.println(s);
		}
	}
}
