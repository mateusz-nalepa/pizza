package pl.tu.kielce.pizza.nauka.reactor;

import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 */
public class Part05Merge {

//========================================================================================

	// TODO Merge flux1 and flux2 values with interleave
	Flux<ReactiveUser> mergeFluxWithInterleave(Flux<ReactiveUser> flux1, Flux<ReactiveUser> flux2) {
		return null;
	}

//========================================================================================

	// TODO Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
	Flux<ReactiveUser> mergeFluxWithNoInterleave(Flux<ReactiveUser> flux1, Flux<ReactiveUser> flux2) {
		return null;
	}

//========================================================================================

	// TODO Create a Flux containing the value of mono1 then the value of mono2
	Flux<ReactiveUser> createFluxFromMultipleMono(Mono<ReactiveUser> mono1, Mono<ReactiveUser> mono2) {
		return null;
	}

}
