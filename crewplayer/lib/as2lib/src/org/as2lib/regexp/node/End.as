﻿/*
 * Copyright the original author or authors.
 * 
 * Licensed under the MOZILLA PUBLIC LICENSE, Version 1.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
import org.as2lib.regexp.node.Node;  

/**
 * {@code End} is a node to anchor at the end of input. This is the absolute 
 * end, so this should not match at the last newline before the end as $ will.
 * 
 * @author Igor Sadovskiy
 */
 
class org.as2lib.regexp.node.End extends Node {
	
    public function match(matcher:Object, i:Number, seq:String):Boolean {
        return (i == matcher.to && next.match(matcher, i, seq));
    }
    
}
