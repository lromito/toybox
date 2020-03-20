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
class ConvertersSpec extends Specification {
	
    def "stringToInt returns an Optional with an int if the value can be converted or an empty Optional otherwise"() {
        when:
            def string = "42"
            def converted = Converters.stringToInt(string)
        then:
            converted.isPresent()
            converted.getAsInt() == 42
            
        when:
            string = "NotAnInt"
            converted = Converters.stringToInt(string)
        then: 
            !converted.isPresent()
    }
    

}

