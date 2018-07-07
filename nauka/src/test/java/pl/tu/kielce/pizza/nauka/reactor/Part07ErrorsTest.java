/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.tu.kielce.pizza.nauka.reactor;

import org.junit.Ignore;
import org.junit.Test;
import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to deal with errors.
 *
 * @author Sebastien Deleuze
 * @see Exceptions#propagate(Throwable)
 */
@Ignore

public class Part07ErrorsTest {

	Part07Errors workshop = new Part07Errors();

//========================================================================================

	@Test
	public void monoWithValueInsteadOfError() {
		Mono<ReactiveUser> mono = workshop.betterCallSaulForBogusMono(Mono.error(new IllegalStateException()));
		StepVerifier.create(mono)
				.expectNext(ReactiveUser.SAUL)
				.verifyComplete();

		mono = workshop.betterCallSaulForBogusMono(Mono.just(ReactiveUser.SKYLER));
		StepVerifier.create(mono)
				.expectNext(ReactiveUser.SKYLER)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void fluxWithValueInsteadOfError() {
		Flux<ReactiveUser> flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.error(new IllegalStateException()));
		StepVerifier.create(flux)
				.expectNext(ReactiveUser.SAUL, ReactiveUser.JESSE)
				.verifyComplete();

		flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.just(ReactiveUser.SKYLER, ReactiveUser.WALTER));
		StepVerifier.create(flux)
				.expectNext(ReactiveUser.SKYLER, ReactiveUser.WALTER)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void handleCheckedExceptions() {
		Flux<ReactiveUser> flux = workshop.capitalizeMany(Flux.just(ReactiveUser.SAUL, ReactiveUser.JESSE));

		StepVerifier.create(flux)
				.verifyError(Part07Errors.GetOutOfHereException.class);
	}

}
