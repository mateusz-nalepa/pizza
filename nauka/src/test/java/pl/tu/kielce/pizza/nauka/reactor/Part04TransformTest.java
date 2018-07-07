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
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
@Ignore
public class Part04TransformTest {

	Part04Transform workshop = new Part04Transform();
	ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();

//========================================================================================

	@Test
	public void transformMono() {
		Mono<ReactiveUser> mono = repository.findFirst();
		StepVerifier.create(workshop.capitalizeOne(mono))
				.expectNext(new ReactiveUser("SWHITE", "SKYLER", "WHITE"))
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void transformFlux() {
		Flux<ReactiveUser> flux = repository.findAll();
		StepVerifier.create(workshop.capitalizeMany(flux))
				.expectNext(
					new ReactiveUser("SWHITE", "SKYLER", "WHITE"),
					new ReactiveUser("JPINKMAN", "JESSE", "PINKMAN"),
					new ReactiveUser("WWHITE", "WALTER", "WHITE"),
					new ReactiveUser("SGOODMAN", "SAUL", "GOODMAN"))
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void  asyncTransformFlux() {
		Flux<ReactiveUser> flux = repository.findAll();
		StepVerifier.create(workshop.asyncCapitalizeMany(flux))
				.expectNext(
					new ReactiveUser("SWHITE", "SKYLER", "WHITE"),
					new ReactiveUser("JPINKMAN", "JESSE", "PINKMAN"),
					new ReactiveUser("WWHITE", "WALTER", "WHITE"),
					new ReactiveUser("SGOODMAN", "SAUL", "GOODMAN"))
				.verifyComplete();
	}

}
