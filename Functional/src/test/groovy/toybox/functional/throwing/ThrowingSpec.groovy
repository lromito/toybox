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

package toybox.functional.throwing

import spock.lang.*

import toybox.functional.throwing.predicates.PredicateChainer

/**
 *
 * @author Luca Romito
 */
class ThrowingSpec extends Specification {

    @Shared Closure thrower =  {
		if (!it.isEmpty()) 
                    return true;
		else 
                    throw new Exception("I throw!");
		}
    
    def testOnEmptyList(PredicateChainer chainer) {
        List emptyList = [].asImmutable()
        chainer.test(emptyList)
    }
    
    def "testing true condition"() {
        when:
            def chainer = Throwing.throwingPredicate{ it.isEmpty() }
        then:
            testOnEmptyList(chainer)
    }
    
    def "testing false condition"() {
        when:
            def chainer = Throwing.throwingPredicate{ !it.isEmpty() }
        then:
            !testOnEmptyList(chainer)
    }
  
  
    def "orReturnTrue returns true if a function throws"() {
        when:
            def chainer = Throwing.throwingPredicate(thrower).orReturnTrue()
        then:
            testOnEmptyList(chainer)
    }
    
    def "orElseThrow throws the expected Exception"() {
        when:
            def chainer = Throwing.throwingPredicate(thrower).elseThrow(IllegalArgumentException.metaClass.&invokeConstructor)
            testOnEmptyList(chainer)
        then:
            IllegalArgumentException e = thrown()
    }
}

