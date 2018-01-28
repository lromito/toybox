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

import static toybox.utils.CollectionUtils.entry;
import static toybox.utils.CollectionUtils.mapOf;
import static toybox.utils.CollectionUtils.mapOfEntries;

/**
 *
 * @author Luca Romito
 */
class CollectionUtilsSpec extends Specification {
    
    def "mapOfEntries correctly puts key and value in the map"() {
        given:
            def key = "key"
            def value = "value"
            
        when:
            def map = mapOfEntries(entry(key, value))
            
        then:
            map.containsKey(key)
            map.get(key) == value
            
    }
    
    def "mapOf correctly puts key and value in the map"() {
        given:
            def key = "key"
            def value = "value"
            
        when:
            def map = mapOf(key, value)
            
        then:
            map.containsKey(key)
            map.get(key) == value
            
    }
}

