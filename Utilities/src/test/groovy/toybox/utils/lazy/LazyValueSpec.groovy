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

package toybox.utils.lazy

import spock.lang.*
/**
 *
 * @author Luca Romito
 */
class LazyValueSpec extends Specification {

    @Shared def VALUE = 42 
    
    def "get() always retuns a value"() {
        given:
            def lazy = LazyValue.of({return VALUE})
        
        expect:
            lazy != null
            lazy.get() != null
            lazy.get() == VALUE
          
    }
    
    def "a lazy is initialized only after calling get"() {
        given:
            def lazy = LazyValue.of({return VALUE})
        
        expect:
            !lazy.isInitialized()
            
        when:
            lazy.get()
            
        then:
            lazy.isInitialized()
    }
    
    def "lazy reports its initialization status in its toString message"() {
        given:
            def lazy = LazyValue.of({return VALUE})
        
        expect:
            lazy.toString() == "LazyValue [not initialized]"
            
        when:
            lazy.get()
            
        then:
            lazy.toString() == "LazyValue [42]"
    }
    
    def "Two Lazies with the same value are equal"() {
        given:
            def lazy1 = LazyValue.of({return VALUE})
            def lazy2 = LazyValue.of({return VALUE})
        expect:
            lazy1.get() == lazy2.get()
        and:
            lazy1 == lazy2
    }
}

