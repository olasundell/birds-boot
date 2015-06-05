import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


public class ObsTest {
	AtomicInteger integer = new AtomicInteger();

	@Before
	public void resetInteger() {
		integer.set(0);
	}

	@Test
	public void methodCallTest() {
		Observable<String> obs = testObs();

		Assert.assertNotNull(obs);
		Assert.assertEquals(1, integer.get());
	}

	@Test
	public void futureTest() {
		Observable<String> obs = obsFuture();
		Assert.assertNotNull(obs);
		String s = obs.toBlocking().single();

		Assert.assertEquals("foo", s);
		Assert.assertEquals(1, integer.get());
	}

	public Observable<String> testObs() {
		return Observable.just(this.foo())
				.switchIfEmpty(Observable.just(this.bar()));
	}

	public Observable<String> obsFuture() {
		return Observable.from(fooFuture())
				.switchIfEmpty(Observable.from(barFuture()));
	}

	private String foo() {
//		System.out.println("foo");
		integer.incrementAndGet();
		return "foo";
	}

	private String bar() {
//		System.out.println("bar");
		integer.incrementAndGet();
		return "bar";
	}

	private Future<String> fooFuture() {
		return new CompletableFuture<String>() {
			@Override
			public String get() throws InterruptedException, ExecutionException {
				return foo();
			}
		};
	}

	private Future<String> barFuture() {
		return new CompletableFuture<String>() {
			@Override
			public String get() throws InterruptedException, ExecutionException {
				return bar();
			}
		};
	}
}
