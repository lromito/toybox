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

package toybox.utils

import spock.lang.*

/**
 *
 * @author Luca Romito
 */

class OptionalConvertersSpec extends Specification {

    def "Optional of boxed int is converted to OptionalInt"() {
        given:
        def value = 42
        def boxedValue = Integer.valueOf(value)
        Optional<Integer> original = Optional.of(boxedValue)

        when:
        def converted = OptionalConverters.boxedToOptionalInt(original)

        then:
        converted.isPresent() && original.isPresent()
        converted.getAsInt() == original.get().intValue()

        when:
        original = Optional.empty()
        converted = OptionalConverters.boxedToOptionalInt(original)

        then:
        converted.isPresent() == original.isPresent()
    }


    def "Optional of boxed long is converted to OptionalLong"() {
        given:
        def value = 42L
        def boxedValue = Long.valueOf(value)
        Optional<Long> original = Optional.of(boxedValue)

        when:
        def converted = OptionalConverters.boxedToOptionalLong(original)

        then:
        converted.isPresent() && original.isPresent()
        converted.getAsLong() == original.get().longValue()

        when:
        original = Optional.empty()
        converted = OptionalConverters.boxedToOptionalLong(original)

        then:
        converted.isPresent() == original.isPresent()
    }

    def "Optional of boxed double is converted to OptionalDouble"() {
        given:
        def value = 42d
        def boxedValue = Double.valueOf(value)
        Optional<Double> original = Optional.of(boxedValue)

        when:
        OptionalDouble converted = OptionalConverters.boxedToOptionalDouble(original)

        then:
        converted.isPresent() && original.isPresent()
        converted.getAsDouble() == original.get().doubleValue()

        when:
        original = Optional.empty()
        converted = OptionalConverters.boxedToOptionalDouble(original)

        then:
        converted.isPresent() == original.isPresent()
    }

    def "castToOptional returns an Optional with a converted value or empty if it cannot be converted"() {
        given:
        CharSequence aString = "A string"
        when:
        def optional = OptionalConverters.castIntoOptional(aString, String.class)
        then:
        optional.isPresent()
        optional.orElse("fail") == aString

        when:
        optional = OptionalConverters.castIntoOptional(aString, Long.class)
        then:
        !optional.isPresent()
    }

}

