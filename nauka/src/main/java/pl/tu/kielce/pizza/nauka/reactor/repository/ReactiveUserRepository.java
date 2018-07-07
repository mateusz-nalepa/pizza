package pl.tu.kielce.pizza.nauka.reactor.repository;

import org.reactivestreams.Publisher;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReactiveUserRepository implements ReactiveRepository<ReactiveUser> {

	private final static long DEFAULT_DELAY_IN_MS = 100;

	private final long delayInMs;

	private final List<ReactiveUser> users;


	public ReactiveUserRepository() {
		this(DEFAULT_DELAY_IN_MS);
	}

	public ReactiveUserRepository(long delayInMs) {
		this.delayInMs = delayInMs;
		users = new ArrayList<>(Arrays.asList(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL));
	}

	public ReactiveUserRepository(ReactiveUser... users) {
		this(DEFAULT_DELAY_IN_MS, users);
	}

	public ReactiveUserRepository(long delayInMs, ReactiveUser... users) {
		this.delayInMs = delayInMs;
		this.users = new ArrayList<>(Arrays.asList(users));
	}


	@Override
	public Mono<Void> save(Publisher<ReactiveUser> userPublisher) {
		return withDelay(Flux.from(userPublisher))
				.doOnNext(users::add)
				.then();
	}

	@Override
	public Mono<ReactiveUser> findFirst() {
		return withDelay(Mono.just(users.get(0)));
	}

	@Override
	public Flux<ReactiveUser> findAll() {
		return withDelay(Flux.fromIterable(users));
	}

	@Override
	public Mono<ReactiveUser> findById(String username) {
		ReactiveUser ReactiveUser = users
				.stream()
				.filter(p -> p.getUsername().equals(username))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No ReactiveUser with username " + username + " found!"));
		return withDelay(Mono.just(ReactiveUser));
	}


	private Mono<ReactiveUser> withDelay(Mono<ReactiveUser> userMono) {
		return Mono
				.delay(Duration.ofMillis(delayInMs))
				.flatMap(c -> userMono);
	}

	private Flux<ReactiveUser> withDelay(Flux<ReactiveUser> userFlux) {
		return Flux
				.interval(Duration.ofMillis(delayInMs))
				.zipWith(userFlux, (i, ReactiveUser) -> ReactiveUser);
	}

}
