/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package verkocht.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

/**
 * Intent handler that greets the user. If nothing is said, the skill will repromt
 * with a random pick of available repromts.
 */
public class LaunchRequestHandler implements RequestHandler {
	SecureRandom rnd = new SecureRandom();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String[] repromts = {"Sage zum Beispiel: Welche Kategorien gibt es?",
    			"Sage zum Beispiel: Zur Rezeptauswahl",
    			"Sage zum Beispiel: Ich habe einen Favoriten",
    			"Ich kann dir helfen, sage zum Beispiel: Wie waehle ich ein Rezept aus?"};
    	
        String speechText = "Hallo. Ich bin dein interaktives Kochbuch \"Verkocht\"! Was willst du tun?";
        String repromptText = repromts[rnd.nextInt(repromts.length)];
        return input.getResponseBuilder()
                .withSimpleCard("Verkocht!", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .build();
    }
}
