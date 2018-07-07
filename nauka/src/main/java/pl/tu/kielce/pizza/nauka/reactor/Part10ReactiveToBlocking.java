package pl.tu.kielce.pizza.nauka.reactor;

import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
public class Part10ReactiveToBlocking {

//========================================================================================

	// TODO Return the ReactiveUser contained in that Mono
	ReactiveUser monoToValue(Mono<ReactiveUser> mono) {
		return null;
	}

//========================================================================================

	// TODO Return the users contained in that Flux
	Iterable<ReactiveUser> fluxToValues(Flux<ReactiveUser> flux) {
		return null;
	}

}
