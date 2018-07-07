package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
@Ignore

public class Part08OtherOperationsTest {

	Part08OtherOperations workshop = new Part08OtherOperations();

	final static ReactiveUser MARIE = new ReactiveUser("mschrader", "Marie", "Schrader");
	final static ReactiveUser MIKE = new ReactiveUser("mehrmantraut", "Mike", "Ehrmantraut");

//========================================================================================

	@Test
	public void zipFirstNameAndLastName() {
		Flux<String> usernameFlux = Flux.just(ReactiveUser.SKYLER.getUsername(), ReactiveUser.JESSE.getUsername(), ReactiveUser.WALTER.getUsername(), ReactiveUser.SAUL.getUsername());
		Flux<String> firstnameFlux = Flux.just(ReactiveUser.SKYLER.getFirstname(), ReactiveUser.JESSE.getFirstname(), ReactiveUser.WALTER.getFirstname(), ReactiveUser.SAUL.getFirstname());
		Flux<String> lastnameFlux = Flux.just(ReactiveUser.SKYLER.getLastname(), ReactiveUser.JESSE.getLastname(), ReactiveUser.WALTER.getLastname(), ReactiveUser.SAUL.getLastname());
		Flux<ReactiveUser> userFlux = workshop.userFluxFromStringFlux(usernameFlux, firstnameFlux, lastnameFlux);
		StepVerifier.create(userFlux)
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void fastestMono() {
		ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository(MARIE);
		ReactiveRepository<ReactiveUser> repositoryWithDelay = new ReactiveUserRepository(250, MIKE);
		Mono<ReactiveUser> mono = workshop.useFastestMono(repository.findFirst(), repositoryWithDelay.findFirst());
		StepVerifier.create(mono)
				.expectNext(MARIE)
				.verifyComplete();

		repository = new ReactiveUserRepository(250, MARIE);
		repositoryWithDelay = new ReactiveUserRepository(MIKE);
		mono = workshop.useFastestMono(repository.findFirst(), repositoryWithDelay.findFirst());
		StepVerifier.create(mono)
				.expectNext(MIKE)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void fastestFlux() {
		ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository(MARIE, MIKE);
		ReactiveRepository<ReactiveUser> repositoryWithDelay = new ReactiveUserRepository(250);
		Flux<ReactiveUser> flux = workshop.useFastestFlux(repository.findAll(), repositoryWithDelay.findAll());
		StepVerifier.create(flux)
				.expectNext(MARIE, MIKE)
				.verifyComplete();

		repository = new ReactiveUserRepository(250, MARIE, MIKE);
		repositoryWithDelay = new ReactiveUserRepository();
		flux = workshop.useFastestFlux(repository.findAll(), repositoryWithDelay.findAll());
		StepVerifier.create(flux)
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void complete() {
		ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();
		PublisherProbe<ReactiveUser> probe = PublisherProbe.of(repository.findAll());
		Mono<Void> completion = workshop.fluxCompletion(probe.flux());
		StepVerifier.create(completion)
				.verifyComplete();
		probe.assertWasRequested();
	}

//========================================================================================

	@Test
	public void nullHandling() {
		Mono<ReactiveUser> mono = workshop.nullAwareUserToMono(ReactiveUser.SKYLER);
		StepVerifier.create(mono)
				.expectNext(ReactiveUser.SKYLER)
				.verifyComplete();
		mono = workshop.nullAwareUserToMono(null);
		StepVerifier.create(mono)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void emptyHandling() {
		Mono<ReactiveUser> mono = workshop.emptyToSkyler(Mono.just(ReactiveUser.WALTER));
		StepVerifier.create(mono)
				.expectNext(ReactiveUser.WALTER)
				.verifyComplete();
		mono = workshop.emptyToSkyler(Mono.empty());
		StepVerifier.create(mono)
				.expectNext(ReactiveUser.SKYLER)
				.verifyComplete();
	}

}
