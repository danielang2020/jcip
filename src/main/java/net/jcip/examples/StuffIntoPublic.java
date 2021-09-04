package net.jcip.examples;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {
    public  Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }

	public static void main(String[] args) {
		StuffIntoPublic stuffIntoPublic = new StuffIntoPublic();
		new Thread(() ->{
			stuffIntoPublic.initialize();
		}).start();
		new Thread(() ->{
			stuffIntoPublic.holder.assertSanity();
		}).start();
	}
}
