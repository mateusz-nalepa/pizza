package pl.tu.kielce.pizza.nauka.reactor;

import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

	// TODO Capitalize the ReactiveUser username, firstname and lastname
	Mono<ReactiveUser> capitalizeOne(Mono<ReactiveUser> mono) {
		return null;
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName
	Flux<ReactiveUser> capitalizeMany(Flux<ReactiveUser> flux) {
		return null;
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	Flux<ReactiveUser> asyncCapitalizeMany(Flux<ReactiveUser> flux) {
		return null;
	}

	Mono<ReactiveUser> asyncCapitalizeUser(ReactiveUser u) {
		return Mono.just(new ReactiveUser(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
