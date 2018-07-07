package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
@Ignore

public class Part10ReactiveToBlockingTest {

	Part10ReactiveToBlocking workshop = new Part10ReactiveToBlocking();
	ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();

//========================================================================================

	@Test
	public void mono() {
		Mono<ReactiveUser> mono = repository.findFirst();
		ReactiveUser ReactiveUser = workshop.monoToValue(mono);
		assertEquals(ReactiveUser.SKYLER, ReactiveUser);
	}

//========================================================================================

	@Test
	public void flux() {
		Flux<ReactiveUser> flux = repository.findAll();
		Iterable<ReactiveUser> users = workshop.fluxToValues(flux);
		Iterator<ReactiveUser> it = users.iterator();
		assertEquals(ReactiveUser.SKYLER, it.next());
		assertEquals(ReactiveUser.JESSE, it.next());
		assertEquals(ReactiveUser.WALTER, it.next());
		assertEquals(ReactiveUser.SAUL, it.next());
		assertFalse(it.hasNext());
	}

}
