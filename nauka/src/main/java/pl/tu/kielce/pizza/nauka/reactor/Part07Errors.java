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

import pl.tu.kielce.pizza.nauka.reactor.domain.ReactiveUser;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to deal with errors.
 *
 * @author Sebastien Deleuze
 * @see Exceptions#propagate(Throwable)
 */
public class Part07Errors {

//========================================================================================

	// TODO Return a Mono<ReactiveUser> containing ReactiveUser.SAUL when an error occurs in the input Mono, else do not change the input Mono.
	Mono<ReactiveUser> betterCallSaulForBogusMono(Mono<ReactiveUser> mono) {
		return null;
	}

//========================================================================================

	// TODO Return a Flux<ReactiveUser> containing ReactiveUser.SAUL and ReactiveUser.JESSE when an error occurs in the input Flux, else do not change the input Flux.
	Flux<ReactiveUser> betterCallSaulAndJesseForBogusFlux(Flux<ReactiveUser> flux) {
		return null;
	}

//========================================================================================

	// TODO Implement a method that capitalizes each ReactiveUser of the incoming flux using the
	// #capitalizeUser method and emits an error containing a GetOutOfHereException error
	Flux<ReactiveUser> capitalizeMany(Flux<ReactiveUser> flux) {
		return null;
	}

	ReactiveUser capitalizeUser(ReactiveUser ReactiveUser) throws GetOutOfHereException {
		if (ReactiveUser.equals(ReactiveUser.SAUL)) {
			throw new GetOutOfHereException();
		}
		return new ReactiveUser(ReactiveUser.getUsername(), ReactiveUser.getFirstname(), ReactiveUser.getLastname());
	}

	protected final class GetOutOfHereException extends Exception {
	}

}