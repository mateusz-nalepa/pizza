/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.tu.kielce.pizza.nauka.reactor;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.reactivestreams.Publisher;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * Learn how to adapt from/to RxJava 2 Observable/Single/Flowable and Java 8+ CompletableFuture.
 *
 * Mono and Flux already implements Reactive Streams interfaces so they are natively
 * Reactive Streams compliant + there are {@link Mono#from(Publisher)} and {@link Flux#from(Publisher)}
 * factory methods.
 *
 * For RxJava 2, you should not use Reactor Adapter but only RxJava 2 and Reactor Core.
 *
 * @author Sebastien Deleuze
 */
public class Part09Adapt {

	ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Adapt Flux to RxJava Flowable
	Flowable<ReactiveUser> fromFluxToFlowable(Flux<ReactiveUser> flux) {
		return null;
	}

	// TODO Adapt RxJava Flowable to Flux
	Flux<ReactiveUser> fromFlowableToFlux(Flowable<ReactiveUser> flowable) {
		return null;
	}

//========================================================================================

	// TODO Adapt Flux to RxJava Observable
	Observable<ReactiveUser> fromFluxToObservable(Flux<ReactiveUser> flux) {
		return null;
	}

	// TODO Adapt RxJava Observable to Flux
	Flux<ReactiveUser> fromObservableToFlux(Observable<ReactiveUser> observable) {
		return null;
	}

//========================================================================================

	// TODO Adapt Mono to RxJava Single
	Single<ReactiveUser> fromMonoToSingle(Mono<ReactiveUser> mono) {
		return null;
	}

	// TODO Adapt RxJava Single to Mono
	Mono<ReactiveUser> fromSingleToMono(Single<ReactiveUser> single) {
		return null;
	}

//========================================================================================

	// TODO Adapt Mono to Java 8+ CompletableFuture
	CompletableFuture<ReactiveUser> fromMonoToCompletableFuture(Mono<ReactiveUser> mono) {
		return null;
	}

	// TODO Adapt Java 8+ CompletableFuture to Mono
	Mono<ReactiveUser> fromCompletableFutureToMono(CompletableFuture<ReactiveUser> future) {
		return null;
	}

}
