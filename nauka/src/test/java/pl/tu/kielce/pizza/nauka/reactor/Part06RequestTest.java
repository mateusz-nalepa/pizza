package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
@Ignore

public class Part06RequestTest {

	Part06Request workshop = new Part06Request();
	ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();

//========================================================================================

	@Test
	public void requestAll() {
		Flux<ReactiveUser> flux = repository.findAll();
		StepVerifier verifier = workshop.requestAllExpectFour(flux);
		verifier.verify();
	}

//========================================================================================

	@Test
	public void requestOneByOne() {
		Flux<ReactiveUser> flux = repository.findAll();
		StepVerifier verifier = workshop.requestOneExpectSkylerThenRequestOneExpectJesse(flux);
		verifier.verify();
	}

//========================================================================================

	@Test
	public void experimentWithLog() {
		Flux<ReactiveUser> flux = workshop.fluxWithLog();
		StepVerifier.create(flux, 0)
				.thenRequest(1)
				.expectNextMatches(u -> true)
				.thenRequest(1)
				.expectNextMatches(u -> true)
				.thenRequest(2)
				.expectNextMatches(u -> true)
				.expectNextMatches(u -> true)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void experimentWithDoOn() {
		Flux<ReactiveUser> flux = workshop.fluxWithDoOnPrintln();
		StepVerifier.create(flux)
				.expectNextCount(4)
				.verifyComplete();
	}
}
