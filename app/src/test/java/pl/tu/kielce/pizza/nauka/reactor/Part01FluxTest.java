package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
@Ignore
public class Part01FluxTest {

	Part01Flux workshop = new Part01Flux();

//========================================================================================

	@Test
	public void empty() {
		Flux<String> flux = workshop.emptyFlux().log();

		StepVerifier
				.create(flux)
				.verifyComplete();
		// it will just emit a complete event, without sending any data
		//coś jak zwrócenie pustej listy



//		13:25:11.085 [main] INFO  reactor.Flux.Empty.1 - onSubscribe([Fuseable] Operators.EmptySubscription)
//		13:25:11.100 [main] INFO  reactor.Flux.Empty.1 - request(unbounded)
//		13:25:11.100 [main] INFO  reactor.Flux.Empty.1 - onComplete()
	}

//========================================================================================

	@Test
	public void fromValues() {
		Flux<String> flux = workshop.fooBarFluxFromValues();
		StepVerifier
				.create(flux) //jak podam 0,  to oczekuje brak danych, a jednak mam zawołać dwa, to się chrzani
				.expectNext("foo", "bar")
				.expectComplete()
				.verify();
		//lub
		//.expectComplete()
		//.verify();
		//zamiast
		//verifyComplete(); jeśli chce się bardziej rozbudowane rzeczy
	}

//========================================================================================

	@Test
	public void fromList() {
		Flux<String> flux = workshop.fooBarFluxFromList().log();
		StepVerifier.create(flux)
				.expectNextMatches( s -> s.equals("foo"))
				.expectNext("bar")
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void error() {
		Flux<String> flux = workshop.errorFlux();
		StepVerifier.create(flux)
				.verifyError(IllegalStateException.class);
	}

//========================================================================================

	@Test
	public void countEach100ms() {
		Flux<Long> flux = workshop.counter();
		StepVerifier
				.create(flux)
				.expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
				.verifyComplete();
	}

	//========================================================================================

	@Test
	public void countEach100msWithVirtualTime() {
		Supplier<Flux<Long>> flux = workshop.counterSupplier();
		StepVerifier.withVirtualTime(flux)
				.thenAwait(Duration.ofSeconds(10))
				.expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
				.verifyComplete();
	}

}
