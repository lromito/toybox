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
class LazyLongSpec extends Specification {
    
    @Shared def VALUE = 42L  
    
    def "get() always retuns a value"() {
        given:
            def lazy = LazyLong.of({return VALUE})
        
        expect:
            lazy != null
            lazy.get() != null
            lazy.get() == VALUE
          
    }
    
    def "a lazy is initialized only after calling get"() {
        given:
            def lazy = LazyLong.of({return VALUE})
        
        expect:
            !lazy.isInitialized()
            
        when:
            lazy.get()
            
        then:
            lazy.isInitialized()
    }
    
    def "lazy reports its initialization status in its toString message"() {
        given:
            def lazy = LazyLong.of({return VALUE})
        
        expect:
            lazy.toString() == "LazyInitializationLong [not initialized]"
            
        when:
            lazy.get()
            
        then:
            lazy.toString() == "LazyInitializationLong [42]"
    }

}

