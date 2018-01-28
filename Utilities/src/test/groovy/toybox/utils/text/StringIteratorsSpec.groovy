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

package toybox.utils.text

import spock.lang.*
import java.util.stream.Collectors;

/**
 *
 * @author Luca Romito
 */
class StringIteratorsSpec extends Specification {
    
    def "splitOnCharacter splits on a single character everthing else is preserved"() {
        given:
            def test = "Split,me, in three"
            def char delimiter = ','
        when:
            def results = StringIterators.splitOnCharacter(test, delimiter).toList()            
        then:
            results.size() == 3
            results[0] == "Split"
            results[1] == "me"
            results[2] == " in three"
    }
    
    def "splitOnCharacter does not iterate on empty and null strings"() {
        given:
            def empty = ""
            def String nullString = null
            def char delimiter = ','
        expect:
            StringIterators.splitOnCharacter(empty, delimiter).forEach {
                assert !"Must never iterate"
            }            
            StringIterators.splitOnCharacter(nullString, delimiter).forEach {
                assert !"Must never iterate"
            } 
    }
    
    def "splitStringStream works like splitOnCharacter but return a Stream of Strings"() {
        given:
            def test = "Split,me, in three"
            def char delimiter = ','
        when:
            def results = StringIterators.splitStringStream(test, delimiter).collect(Collectors.toList())          
        then:
            results.size() == 3
            results[0] == "Split"
            results[1] == "me"
            results[2] == " in three"
        when:    
            results = StringIterators.splitStringStream("No split me", delimiter).collect(Collectors.toList())
        then:
            results.size() == 1
            results[0] == "No split me"
        when:
            results = StringIterators.splitStringStream("Split,,me", delimiter).collect(Collectors.toList())
        then:
            results.size() == 3
            results[0] == "Split"
            results[1] == ""
            results[2] == "me"
    }
    
    def "splitStringStream returns an empty Stream on empty or null string"() {
        given:
            def empty = ""
            def String nullString = null
            def char delimiter = ','
        when:
            def stream = StringIterators.splitStringStream(empty, delimiter)
        then:
            stream.forEach { assert !"Stream should be empty" }
            
        when:
            stream = StringIterators.splitStringStream(nullString, delimiter)
        then:
            stream.forEach { assert !"Stream should be empty" }
    }
    
    def "splitOnFixedLength returns an Iterator that yields string that are at most of the specified length"() {
        given:
            def fixedLengthTest = "12345" + "12345" + "12345" + "123"
            def fixedLength = 5
        when:
            def results = StringIterators.splitOnFixedLength(fixedLengthTest, fixedLength).toList()
        then:
            results.size() == 4
            results.any { it.length() <= fixedLength }
            
    }
    
    def "splitOnFixedLength length must be greater than zero"() {
        given:
            def fixedLengthTest = "12345" + "12345" + "12345" + "123"
            def invalidLength = -5
        when:
            results = StringIterators.splitOnFixedLength(fixedLengthTest, invalidLength).toList()
        then:
            IllegalArgumentException e = thrown()
    }
}

