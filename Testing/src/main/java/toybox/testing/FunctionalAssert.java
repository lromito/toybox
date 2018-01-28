/*
 * Copyright 2017 .
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
package testing;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * These methods enable the usage of Java 8 Lambdas in JUnit tests.
 *
 * @author Luca Romito
 * @since 1.8
 *
 */
public final class FunctionalAssert {

    private static final String DEFAULT_TEST_MESSAGE = "Test failed";

    /**
     * Simpler Assert function mimicking JUnit 5.x
     *
     * @param message - A lambda function lazily providing the error message.
     * @param condition - A lambda function returning the condition to test
     */
    public static void assertThat(Supplier<String> message, BooleanSupplier condition) {
        try {
            if (!condition.getAsBoolean()) {
                throw new AssertionError(message.get());
            }
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new AssertionError(message.get());
        }
    }

    public static void assertThat(String message, BooleanSupplier condition) {
        assertThat(() -> message, condition);
    }

    public static void assertThat(BooleanSupplier condition) {
        assertThat(DEFAULT_TEST_MESSAGE, condition);
    }

    public static void assertFalse(Supplier<String> message, BooleanSupplier condition) {
        assertThat(message, () -> !condition.getAsBoolean());
    }

    public static void assertFalse(String message, BooleanSupplier condition) {
        assertFalse(() -> message, condition);
    }

    public static void assertFalse(BooleanSupplier condition) {
        assertFalse(DEFAULT_TEST_MESSAGE, condition);
    }

    public static void assertAll(Supplier<String> message, BooleanSupplier... conditions) {
        for (BooleanSupplier condition : conditions) {
            assertThat(message, condition);
        }
    }

//	public static void assertAll(String message, ThrowingBooleanSupplier... conditions) {
//		assertAll(() -> message, conditions);
//	}
//	
//	public static void assertAll(ThrowingBooleanSupplier... conditions) {
//		assertAll(DEFAULT_TEST_MESSAGE, conditions);
//	}
}
