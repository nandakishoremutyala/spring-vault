/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.vault.support;

/**
 * Strategy interface to control whether to retain or drop a
 * {@link org.springframework.vault.core.lease.domain.Lease} after a failure.
 *
 * @author Mark Paluch
 * @since 2.2
 */
@FunctionalInterface
public interface LeaseStrategy {

	/**
	 * Return {@literal true} to drop the lease after {@link Throwable error} happened.
	 * {@literal false} to retain the lease.
	 *
	 * @param error the error that occurred.
	 * @return {@literal true} to drop the lease after {@link Throwable error} happened.
	 * {@literal false} to retain the lease.
	 */
	boolean shouldDrop(Throwable error);

	/**
	 * Predefined strategy to drop leases on error.
	 *
	 * @return
	 */
	static LeaseStrategy dropOnError() {
		return error -> true;
	}

	/**
	 * Predefined strategy to retain leases on error.
	 *
	 * @return
	 */
	static LeaseStrategy retainOnError() {
		return error -> false;
	}
}
