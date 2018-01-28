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

/**
 *
 * @author Luca Romito
 */
class EitherSpec extends Specification {
   
    def "Either.leftFrom must return a Left"() {
        when:
            def test = Either.leftFrom("test")
        then:
            test.isLeft()
            !test.isRight()
    }
    
    def "Either.rightFrom must return a Right"() {
        when:
            def test = Either.rightFrom(42)
        then:
            !test.isLeft()
            test.isRight()
    }
    
    def "map applies either the first function if Left the second function if Right"() {
        given:
            def left = Either.leftFrom(12);
            def right = Either.rightFrom("test");
	when:
            def result = left.map({ Integer.valueOf(it).toString() }, { -> "null"})
        then:
            result.get() == "12"
            result.get() != "null"
            
        when:
            result = right.map({ -> "null"}, { Objects.toString(it) })
        then:
            result.get() == "test"
            result.get() != "null"
    }
    
    /*
	
	@Test
	public void testHash() {
		Either<String, Integer> e1 = Either.leftFrom("test");
		Either<String, Integer> e2 = Either.rightFrom(10);
		
		assertFalse(() -> e1.equals(e2));
		assertThat(() -> e1.hashCode() != e2.hashCode());
	}
        */
       
    def "swap() returns an Either with swapped left and right"() {
        when: 
            def test = Either.leftFrom("test")
        then:
            test.isLeft()
            
        when:
            def swapped = test.swap()         
        then:
            swapped.isRight()
    }
    
    def "Two Either are equals if are both left or right and the values are equal"() {
        given:
            def e1 = Either.leftFrom("test")
        when:
            def e2 = Either.leftFrom("test")
        then:
            e1 == e2
        when:
            def e3 = Either.rightFrom("test")
        then:
            e1 != e3
            e2 != e3
    }
    
    def "if two Eithers are different than their hashCode must be different"() {
        given:
            def e1 = Either.leftFrom("test")
            def e2 = Either.rightFrom(10)
            
        expect:
            e1 != e2
            e1.hashCode() != e2.hashCode()
    }
}

