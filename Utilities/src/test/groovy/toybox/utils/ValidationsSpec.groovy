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
class ValidationsSpec extends Specification {
    /*
        
    	@Test(expected=IllegalArgumentException.class)
	public void testRequires() {
		final Integer value = Integer.valueOf(-5);
		Validations.requires("Value must be greater than 0", () -> value > 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRequiresInt() {
		final int value = -5;
		Validations.requiresInt(value, (v) -> v > 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRequiresValue() {
		final Integer value = Integer.valueOf(-5);
		Validations.requiresValue(value, (v) -> v > 0);
	}

    
    */
   
    def "if validation condition is not satisfied, an IllegalArgumentException is thrown" () {
        given:
            def value = Integer.valueOf(-5)
        when:
            Validations.requires("Value must be greater than 0", {it > 0})
        then:
           IllegalArgumentException e = thrown() 
    }
    
    def "validation condition with zero-arg function" () {
        given:
            def value = Integer.valueOf(-5)
        when:
            Validations.requires("Value must be greater than 0", {value > 0})
        then:
           IllegalArgumentException e = thrown() 
    }
    
        def "validation condition tested with an int" () {
        given:
            def value = -5
        when:
            Validations.requiresInt(value, {it > 0})
        then:
           IllegalArgumentException e = thrown() 
    }
}

