package handlers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import verkocht.handlers.ModifyRecipeByUnitsIntentHandler;
import verkocht.model.Category;
import verkocht.model.PhrasesForAlexa;
import verkocht.model.Recipe;

public class ModifyRecipeByUnitsIntentHandlerTest {
	private ModifyRecipeByUnitsIntentHandler handler;
	private Recipe testRecipe = new Recipe("test", Category.MEAT, 2, 20);

    @Before
    public void setup() {
        handler = new ModifyRecipeByUnitsIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandleWithoutSavedRecipe() {
        Recipe.saveRecipe(null);
    	final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

    	final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_SELECT_RECIPE_FIRST));
    }
    
    @Test
    public void testHandleWithSavedRecipeFirstOpen() {
        Recipe.saveRecipe(testRecipe);
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        final Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        final Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_WELCOME));
    }
    
    @Test
    public void testHandleModificationNotPassed() {
        Recipe.saveRecipe(testRecipe);
        HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_WELCOME));
        
        returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_NOT_DONE));
    }
    
    /*
    @Test
    public void testHandleModificationPassed() {
        Recipe.saveRecipe(testRecipe);
        testRecipe.addIngredient(new Ingredient("Ei", Unit.STUECK), 1);
        HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        Response response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_WELCOME));
        
        final List<Slot> slotValues = new ArrayList<>();
        Slot ingredient = Slot.builder().withName("Ingredient")
                .withResolutions(Resolutions.builder()
                        .addResolutionsPerAuthorityItem(Resolution.builder()
                                .addValuesItem(ValueWrapper.builder()
                                        .withValue(Value.builder()
                                                .withName("Ei")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        Slot ingredientValue = Slot.builder().withName("Ingredient_Value")
                .withValue("3")
                .build();
        slotValues.add(ingredient);
        slotValues.add(ingredientValue);
        final Intent.Builder intentBuilder = Intent.builder();
        slotValues.forEach((slotItem) ->
                intentBuilder.putSlotsItem(slotItem.getName(), slotItem)
        );

        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(intentBuilder.build())
                        .build())
                .build();


        HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

        returnResponse = handler.handle(inputMock);        

        assertTrue(returnResponse.isPresent());
        response = returnResponse.get();;

        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString()
                .contains(PhrasesForAlexa.MODIFY_UNIT_DONE));
    }
    */
}
