package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.BlockingUserRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Learn how to call blocking code from Reactive one with adapted concurrency strategy for
 * blocking code that produces or receives data.
 *
 * For those who know RxJava:
 *  - RxJava subscribeOn = Reactor subscribeOn
 *  - RxJava observeOn = Reactor publishOn
 *
 * @author Sebastien Deleuze
 * @see Flux#subscribeOn(Scheduler)
 * @see Flux#publishOn(Scheduler)
 * @see Schedulers
 */
@Ignore


public class Part11BlockingToReactiveTest {

	Part11BlockingToReactive workshop = new Part11BlockingToReactive();

//========================================================================================

	@Test
    @Ignore
	public void slowPublisherFastSubscriber() {
		BlockingUserRepository repository = new BlockingUserRepository();
		Flux<ReactiveUser> flux = workshop.blockingRepositoryToFlux(repository);
		assertEquals("The call to findAll must be deferred until the flux is subscribed", 0, repository.getCallCount());
		StepVerifier.create(flux)
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL)
				.verifyComplete();
	}

//========================================================================================

	@Test
    @Ignore
	public void fastPublisherSlowSubscriber() {
		ReactiveRepository<ReactiveUser> reactiveRepository = new ReactiveUserRepository();
		BlockingUserRepository blockingRepository = new BlockingUserRepository(new ReactiveUser[]{});
		Mono<Void> complete = workshop.fluxToBlockingRepository(reactiveRepository.findAll(), blockingRepository);
		assertEquals(0, blockingRepository.getCallCount());
		StepVerifier.create(complete)
				.verifyComplete();
		Iterator<ReactiveUser> it = blockingRepository.findAll().iterator();
		assertEquals(ReactiveUser.SKYLER, it.next());
		assertEquals(ReactiveUser.JESSE, it.next());
		assertEquals(ReactiveUser.WALTER, it.next());
		assertEquals(ReactiveUser.SAUL, it.next());
		assertFalse(it.hasNext());
	}

}
