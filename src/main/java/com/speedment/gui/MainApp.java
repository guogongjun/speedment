/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.gui;

import com.speedment.util.Settings;
import com.speedment.gui.controllers.MailPromptController;
import com.speedment.gui.controllers.ProjectPromptController;
import com.speedment.gui.icons.Icons;
import com.speedment.util.analytics.AnalyticsUtil;
import static com.speedment.util.analytics.FocusPoint.GUI_STARTED;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Emil Forslund
 */
public class MainApp extends Application {
    
    private static MainApp app;

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        
        stage.getIcons().add(Icons.SPEEDMENT_LOGO.load());
		
		AnalyticsUtil.notify(GUI_STARTED);

        if (Settings.inst().has("user_mail")) {
            ProjectPromptController.showIn(stage);
        } else {
            MailPromptController.showIn(stage);
        }

        stage.show();
        
        
    }
    
    public static void showWebsite(String url) {
        app.getHostServices().showDocument(url);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
