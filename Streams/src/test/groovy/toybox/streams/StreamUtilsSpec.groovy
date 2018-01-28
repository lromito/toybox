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

package toybox.streams

import spock.lang.*
import java.util.stream.*

/**
 *
 * @author Luca Romito
 */
class StreamUtilsSpec extends Specification {
	
    def "unfold() returns a generated value until the generator returns Optional.empty()"() {
        given:
            final finalSize = 10
        when:
            def unfolded = StreamUtils.unfold(1, {i -> 
                    (i < finalSize) ? Optional.of(i + 1) : Optional.empty()
                })
        then:
            unfolded.collect(Collectors.toList()).size() == finalSize
    }
    
    def "zip() combines two streams into one"() {
        given:
            final streamLimit = 10
        when:
            def left = IntStream.iterate(0, {i -> i + 1}).limit(streamLimit).boxed()
            def right = IntStream.iterate(10, {i-> i - 1}).limit(streamLimit).boxed()
        then:
            def collection = StreamUtils.zip(left, right, { l, r -> l + r }).collect(Collectors.toList())
    }
    
    def "takeWhile takes elements from a Stream while its condition is true"() {
        given:
            final size = 10
            final initialValue = 0
        when:
            def infinite = Stream.iterate(initialValue, { i -> i + 1 })
            def finite = StreamUtils.takeWhile(infinite, { s -> s < size })
	then:	
            finite.collect(Collectors.toList()).size() == size;
    }
    
    def "takeUntil takes elements from a Stream until its condition is true"() {
        given:
            final size = 10
            final initialValue = 1
        when:
            def infinite = Stream.iterate(initialValue, { i -> i + 1 })
            def finite = StreamUtils.takeUntil(infinite, { s -> s > size })
	then:	
            finite.collect(Collectors.toList()).size() == size;
    }
   
    def "iterate works like a for statement"() {
        given:
            def expected = [1, 2, 3, 4]
        when:
            def results = StreamUtils.iterate( { -> 1}, { i -> i < 5 }, { i -> i + 1 }).collect(Collectors.toList())
        then:
            results == expected
    }
    
    def "iterateInt works like a for statement but for a IntStream"() {
        given:
            def expected = [1, 2, 3, 4]
        when:
            def results = StreamUtils.iterateInts(1, { i -> i < 5 }, { i -> i + 1 }).toArray()
        then:
            results == expected
    }
   
    def "stringCollector collects a single string from a Stream<String>"() {
        given:
            def list = ["Test", "me", "up"]
	when:	
            def collected = list.stream().collect(StreamUtils.stringCollector())
        then:
            collected == "Testmeup"
    }
}

