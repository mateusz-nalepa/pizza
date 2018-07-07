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
import org.junit.Ignore;
import org.junit.Test;
import org.reactivestreams.Publisher;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveRepository;
import pl.tu.kielce.pizza.nauka.reactor.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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
@Ignore

public class Part09AdaptTest {

	Part09Adapt workshop = new Part09Adapt();
	ReactiveRepository<ReactiveUser> repository = new ReactiveUserRepository();

//========================================================================================

	@Test
	public void adaptToFlowable() {
		Flux<ReactiveUser> flux = repository.findAll();
		Flowable<ReactiveUser> flowable = workshop.fromFluxToFlowable(flux);
		StepVerifier.create(workshop.fromFlowableToFlux(flowable))
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void adaptToObservable() {
		Flux<ReactiveUser> flux = repository.findAll();
		Observable<ReactiveUser> observable = workshop.fromFluxToObservable(flux);
		StepVerifier.create(workshop.fromObservableToFlux(observable))
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.JESSE, ReactiveUser.WALTER, ReactiveUser.SAUL)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void adaptToSingle() {
		Mono<ReactiveUser> mono = repository.findFirst();
		Single<ReactiveUser> single = workshop.fromMonoToSingle(mono);
		StepVerifier.create(workshop.fromSingleToMono(single))
				.expectNext(ReactiveUser.SKYLER)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void adaptToCompletableFuture() {
		Mono<ReactiveUser> mono = repository.findFirst();
		CompletableFuture<ReactiveUser> future = workshop.fromMonoToCompletableFuture(mono);
		StepVerifier.create(workshop.fromCompletableFutureToMono(future))
				.expectNext(ReactiveUser.SKYLER)
				.verifyComplete();
	}

}
