/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package verkocht;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import verkocht.handlers.CancelandStopIntentHandler;
import verkocht.handlers.FallbackIntentHandler;
import verkocht.handlers.HelpIntentHandler;
import verkocht.handlers.LaunchRequestHandler;
import verkocht.handlers.ModifyRecipeIntentHandler;
import verkocht.handlers.SelectRecipeByCategoryIntentHandler;
import verkocht.handlers.SelectRecipeByFavoritsIntentHandler;
import verkocht.handlers.SelectRecipeByNameIntentHandler;
import verkocht.handlers.SelectRecipeIntentHandler;
import verkocht.handlers.SessionEndedRequestHandler;
import verkocht.handlers.SetFavoriteIntentHandler;
import verkocht.handlers.TellMeCategoriesIntentHandler;
import verkocht.handlers.TellRecipeStepsIntentHandler;

public class VerkochtStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
//                        new WhatsMyColorIntentHandler(),
//                        new MyColorIsIntentHandler(),
                		new TellMeCategoriesIntentHandler(),
                        new ModifyRecipeIntentHandler(),
                        new SelectRecipeByCategoryIntentHandler(),
                        new SelectRecipeByFavoritsIntentHandler(),
                        new SelectRecipeByNameIntentHandler(),
                        new SelectRecipeIntentHandler(),
                        new SetFavoriteIntentHandler(),
                        new TellRecipeStepsIntentHandler(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public VerkochtStreamHandler() {
        super(getSkill());
    }

}
