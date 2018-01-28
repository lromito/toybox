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

package toybox.functional

import spock.lang.*

import java.util.function.*

/**
 *
 * @author Luca Romito
 */
class FunctionUtilsSpec extends Specification {
    @Shared Function throwingFunction = {
            if (it)
                return true
            else 
                throw new IllegalArgumentException()
        }
        
    def "wrapException returns an function that returns an Optional.empty if the original function throws"() {
        given:
            Function wrapped = FunctionUtils.wrapException(throwingFunction)
        when:
            Optional result = wrapped.apply(true)
        then:
            result.isPresent()
        when:
            result = wrapped.apply(false)
        then:
            !result.isPresent()
    }
    
    def "bindFirst binds the first argument of a BiFunction and returns a one-arg Function" () {
        given:
            BiFunction lessThan = { a, b ->
                a < b
            }
        when:
            def bound = FunctionUtils.bindFirst(lessThan, 2)
        then:
            bound.apply(3)
            !bound.apply(1)
    }
    
    def "bindSecond binds the second argument of a BiFunction and returns a one-arg Function" () {
        given:
            BiFunction lessThan = { a, b ->
                a < b
            }
        when:
            def bound = FunctionUtils.bindSecond(lessThan, 2)
        then:
            bound.apply(1)
            !bound.apply(3)
    }
}

