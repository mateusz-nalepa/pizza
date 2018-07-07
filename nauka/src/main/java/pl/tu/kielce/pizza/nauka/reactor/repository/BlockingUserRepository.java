package pl.tu.kielce.pizza.nauka.reactor.repository;

import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.publisher.Mono;

public class BlockingUserRepository implements BlockingRepository<ReactiveUser>{

	private final ReactiveRepository<ReactiveUser> reactiveRepository;

	private int callCount = 0;

	public BlockingUserRepository() {
		reactiveRepository = new ReactiveUserRepository();
	}

	public BlockingUserRepository(long delayInMs) {
		reactiveRepository = new ReactiveUserRepository(delayInMs);
	}

	public BlockingUserRepository(ReactiveUser... users) {
		reactiveRepository = new ReactiveUserRepository(users);
	}

	public BlockingUserRepository(long delayInMs, ReactiveUser... users) {
		reactiveRepository = new ReactiveUserRepository(delayInMs, users);
	}


	@Override
	public void save(ReactiveUser ReactiveUser) {
		callCount++;
		reactiveRepository.save(Mono.just(ReactiveUser)).block();
	}

	@Override
	public ReactiveUser findFirst() {
		callCount++;
		return reactiveRepository.findFirst().block();
	}

	@Override
	public Iterable<ReactiveUser> findAll() {
		callCount++;
		return reactiveRepository.findAll().toIterable();
	}

	@Override
	public ReactiveUser findById(String username) {
		callCount++;
		return reactiveRepository.findById(username).block();
	}

	public int getCallCount() {
		return callCount;
	}
}
