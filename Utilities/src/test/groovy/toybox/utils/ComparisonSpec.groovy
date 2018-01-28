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
class ComparisonSpec extends Specification {
    def "comparison returns an enumeration when comparing two values instead of an int"() {
        expect:
            Comparison.compare(5, 5) == Comparison.EQUAL
            Comparison.compare(3, 10) == Comparison.LESSER
            Comparison.compare(13, 10) == Comparison.GREATER
    }
}

