package verkocht.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class TellRecipeStepsIntentHandler implements RequestHandler {
    static int counter;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TellRecipeStepsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Recipe recipeToRead = Recipe.getRecipeToRead();
        if (counter >= Recipe.getRecipeToRead().getSteps().size()) {
            speechText = PhrasesForAlexa.END_READ_RECIPE_STEPS;
            counter = 0;
        } else {          
            String recipeStep = recipeToRead.getSteps().get(counter);
            speechText = String.format("(%d), %s ", counter + 1, recipeStep);
            counter++;
             }
        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Rezeptschritte", speechText)
                .withReprompt("Wie kann ich dir helfen?").withShouldEndSession(false).build();
    }
    public static Recipe getRecipeToRead() {
        return Recipe.getRecipeToRead();
    }

    public static void setRecipeToRead(Recipe recipeToRead) {
        Recipe.setRecipeToRead(recipeToRead);
    }

    public static void resetCnt() {
        counter = 0;
    }

}