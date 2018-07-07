package pl.tu.kielce.pizza.nauka.reactor;

import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

	// TODO Create a Flux of ReactiveUser from Flux of username, firstname and lastname.
	Flux<ReactiveUser> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
		return null;
	}

//========================================================================================

	// TODO Return the mono which returns its value faster
	Mono<ReactiveUser> useFastestMono(Mono<ReactiveUser> mono1, Mono<ReactiveUser> mono2) {
		return null;
	}

//========================================================================================

	// TODO Return the flux which returns the first value faster
	Flux<ReactiveUser> useFastestFlux(Flux<ReactiveUser> flux1, Flux<ReactiveUser> flux2) {
		return null;
	}

//========================================================================================

	// TODO Convert the input Flux<ReactiveUser> to a Mono<Void> that represents the complete signal of the flux
	Mono<Void> fluxCompletion(Flux<ReactiveUser> flux) {
		return null;
	}

//========================================================================================

	// TODO Return a valid Mono of ReactiveUser for null input and non null input ReactiveUser (hint: Reactive Streams do not accept null values)
	Mono<ReactiveUser> nullAwareUserToMono(ReactiveUser ReactiveUser) {
		return null;
	}

//========================================================================================

	// TODO Return the same mono passed as input parameter, expect that it will emit ReactiveUser.SKYLER when empty
	Mono<ReactiveUser> emptyToSkyler(Mono<ReactiveUser> mono) {
		return null;
	}

}
