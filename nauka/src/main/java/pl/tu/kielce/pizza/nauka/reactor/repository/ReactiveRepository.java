package pl.tu.kielce.pizza.nauka.reactor.repository;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveRepository<T> {

	//informuje że dane zostały zapisane w bazie
	//dzięki temu, że parametr to publisher, to można używać dowolnej implementacji tego API
	Mono<Void> save(Publisher<T> publisher);

	Mono<T> findFirst();

	Flux<T> findAll();

	Mono<T> findById(String id);
}
