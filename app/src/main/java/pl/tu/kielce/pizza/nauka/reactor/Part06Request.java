package pl.tu.kielce.pizza.nauka.reactor;


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
public class Part06Request {

	ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
	StepVerifier requestAllExpectFour(Flux<ReactiveUser> flux) {
		return null;
	}

//========================================================================================

	// TODO Create a StepVerifier that initially requests 1 value and expects ReactiveUser.SKYLER then requests another value and expects ReactiveUser.JESSE.
	StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<ReactiveUser> flux) {
		return null;
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
	Flux<ReactiveUser> fluxWithLog() {
		return null;
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
	Flux<ReactiveUser> fluxWithDoOnPrintln() {
		return null;
	}

}
