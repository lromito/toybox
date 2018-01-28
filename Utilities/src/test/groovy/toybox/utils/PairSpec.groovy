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
class PairSpec extends Specification {
     
    @Shared def first = "first"
    @Shared def second = "second"
    
    def "makePair creates  a valid pair"() {
        when:
            def pair = Pair.of(first, second)
        
        then:
            pair.getFirst() == first
            pair.getSecond() == second
    }
    
    def "pair is consistent with equals"() {
        when:
            def pair1 = Pair.of(first, second)
            def pair2 = Pair.of(first, second)

        then:
            pair1 == pair1
            pair1 == pair2
            pair2 == pair1
    }
        
    def "withFirst creates a new pair"() {
        given:
            def third = "Third"
            def pair1 = Pair.of(first, second)
        
        when:
            def pair2 = pair1.withFirst(third)

        then:
            pair2 != pair1
            pair2.getFirst() == third
            pair2.getFirst() != pair1.getFirst()
            pair2.getSecond() == pair1.getSecond()
    }
    
    def "withSecond creates a new pair"() {
        given:
            def third = "Third"
            def pair1 = Pair.of(first, second)
            
        when:
            def pair2 = pair1.withSecond(third)

        then:
            pair2 != pair1
            pair2.getSecond() == third
            pair2.getFirst() == pair1.getFirst()
            pair2.getSecond() != pair1.getSecond()
    }

    
    def "map applies to both elements"() {
        given:
            def pair = Pair.of(first, second);
	
        when:
            def mapped = pair.map( {it.length()}, {it.length()})
            
        then:
            pair.getFirst().length() == mapped.getFirst()
            pair.getSecond().length() == mapped.getSecond()
    }
    
    def "flatMap takes a biFunction that returns a Pair"() {
        given:
            def pair = Pair.of(1, 2);
	
        when:
            def mapped = pair.flatMap{p1, p2 -> Pair.of(Integer.toString(p1), Integer.toString(p2))};
            
        then:
            mapped.getFirst() == "1"
            mapped.getSecond() == "2"
    }
    
    
    def "mapFirst maps the first element and leaves the second unchanged"() {
        given:
            def pair = Pair.of(first, second);
	
        when:
            def mapped = pair.mapFirst{it.length()};
            
        then:
            pair.getFirst().length() == mapped.getFirst()
            pair.getSecond() == mapped.getSecond()
    }
    
   
    def "mapSecond maps the second element and leaves the first unchanged"() {
        given:
            def pair = Pair.of(first, second);
	
        when:
            def mapped = pair.mapSecond{it.length()};
            
        then:
            pair.getFirst() == mapped.getFirst()
            pair.getSecond().length() == mapped.getSecond()
    }
 }

