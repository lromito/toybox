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
class TrySpec extends Specification {
   
    def "non throwing function returns a Success element"() {
        when:
            def success = Try.of{ -> "Success"}
        then:
            success instanceof Try.Success
            success.isSuccess()
            !success.isFailure()
    }
    
    def "throwing function returns a Failure element"() {
        when:
            def fail = Try.of{ -> throw new IllegalArgumentException("testing")}
        then:
            fail instanceof Try.Failure
            fail.isFailure()
            !fail.isSuccess()
            
    }
    
    def "map applies the mapper function only if its a success"() {
        when:
            def success = Try.of{ -> "ess"}
            def mapped = success.map{s -> s + s}
        then:
            mapped.orElse("failure").equals("essess")
            !mapped.orElse("failure").equals("failure")
            
        when:
            def fail = Try.of{ -> throw new IllegalArgumentException("testing")}
            mapped = fail.map{s -> s + s}
        then:
            mapped.orElse("failure").equals("failure")
            !mapped.orElse("failure").equals("essess")
            
    }
}

