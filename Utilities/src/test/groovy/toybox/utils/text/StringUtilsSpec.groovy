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

/**
 *
 * @author Luca Romito
 */
class StringUtilsSpec extends Specification {
   
    @Shared String nullString = null
    @Shared def emptyString = ""
    
    def "isNullOrEmpty is true if a string is null or empty"() {
        expect:
            StringUtils.isNullOrEmpty(nullString)
            StringUtils.isNullOrEmpty(emptyString)
            !StringUtils.isNullOrEmpty("null")
    }
    
    def "nullToEmpty returns an empty string on null or the same string otherwise" () {
        given:
            def nonEmptyString = "a non empty string"
        when:
            def converted = StringUtils.nullToEmpty(nonEmptyString)          
        then:
            converted == "a non empty string"
        
        when:
            converted = StringUtils.nullToEmpty(emptyString)          
        then:
            converted == emptyString
        
        when:
            converted = StringUtils.nullToEmpty(nullString)          
        then:
            converted == emptyString
    }
    
    def "normalize normalizes a string and leaves null or empty as they are"() {
        given:
            def test = "  non normalized  string "
            def normalized = "non normalized string"
        expect:
            StringUtils.normalize(test) == normalized
            StringUtils.normalize("  ").isEmpty()
            StringUtils.normalize(null).isEmpty()
    }
    
    def "makeIterable test with forEach and a builder"() {
        given:
            def test = "test"
            def builder = new StringBuilder()
        when:
            StringUtils.makeIterable(test).forEach{ builder << it}
        then:
            builder.toString() == test
    }
    
    def "makeIterable allows a string to be iterated in order"() {
        given:
            def test = "test"
        expect:
            StringUtils.makeIterable(test).eachWithIndex { element, position ->
                assert element == test[position]
            }
    }
    
    def "makeIterable does not iterate on an empty string"() {
        expect:
            StringUtils.makeIterable(emptyString).each { 
                assert !"must fail if it tries to iterate on an empty string"
            }
    }
    
        def "makeIterable does not iterate on a null string"() {
        expect:
            StringUtils.makeIterable(nullString).each { 
                assert !"must fail if it tries to iterate on a null string"
            }
    }

}

