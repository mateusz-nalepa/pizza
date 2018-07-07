package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 */
@Ignore

public class Part05MergeTest {

	Part05Merge workshop = new Part05Merge();

	final static ReactiveUser MARIE = new ReactiveUser("mschrader", "Marie", "Schrader");
	final static ReactiveUser MIKE = new ReactiveUser("mehrmantraut", "Mike", "Ehrmantraut");

	ReactiveRepository<ReactiveUser> repositoryWithDelay = new ReactiveUserRepository(500);
	ReactiveRepository<ReactiveUser> repository          = new ReactiveUserRepository(MARIE, MIKE);

//========================================================================================

	@Test
	public void mergeWithInterleave() {
		Flux<ReactiveUser> flux = workshop.mergeFluxWithInterleave(repositoryWithDelay.findAll(), repository.findAll());
		StepVerifier.create(flux)
				.expectNext(MARIE, MIKE, ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void mergeWithNoInterleave() {
		Flux<ReactiveUser> flux = workshop.mergeFluxWithNoInterleave(repositoryWithDelay.findAll(), repository.findAll());
		StepVerifier.create(flux)
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL, MARIE, MIKE)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void multipleMonoToFlux() {
		Mono<ReactiveUser> skylerMono = repositoryWithDelay.findFirst();
		Mono<ReactiveUser> marieMono = repository.findFirst();
		Flux<ReactiveUser> flux = workshop.createFluxFromMultipleMono(skylerMono, marieMono);
		StepVerifier.create(flux)
				.expectNext(ReactiveUser.SKYLER, MARIE)
				.verifyComplete();
	}

}
