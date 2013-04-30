package sh.calaba.instrumentationbackend.actions.we7;

import java.util.ArrayList;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.TestHelpers;
import sh.calaba.instrumentationbackend.actions.Action;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

public class We7ActionBarSearch extends We7Action implements Action {

	@Override
	public Result execute(String... args) {
		
		View actionLogoView = TestHelpers.getViewById("action_logo");
		
		if (actionLogoView == null) {
			
			InstrumentationBackend.log("Unable to find action_logo so assuming true ActionBar");	
			
			
			Window window = InstrumentationBackend.solo.getCurrentActivity().getWindow();
			
			View decorView = window.getDecorView();
			
			ArrayList<ImageButton> imageButtonViews = InstrumentationBackend.solo.getCurrentImageButtons(decorView);
			
			for (ImageButton ib : imageButtonViews) {
				
				InstrumentationBackend.log(ib.toString());

			}
			
			
			InstrumentationBackend.log("Clicking on Search");
			InstrumentationBackend.solo.clickOnMenuItem("Search");
			InstrumentationBackend.log("Entering search text " + args[0]);
			InstrumentationBackend.solo.enterText(0, args[0]); 
			InstrumentationBackend.log("Clicking on Search");
			InstrumentationBackend.solo.clickOnMenuItem("Search");
			InstrumentationBackend.log("Done");
			
		} else {
			
			InstrumentationBackend.log("Found action_logo calling clickOnVew so assuming ActionBarCompat");
			
			InstrumentationBackend.log("Clicking on Search");
			if (!pressView("search_icon")) {
				return new Result(false, "Unable to find view with id search_icon");
			}
			InstrumentationBackend.log("search_icon clicked");

			
			InstrumentationBackend.log("Entering search text " + args[0]);
			EditText searchText = (EditText) TestHelpers.getViewById("search_text");
			if (searchText == null) {
				return new Result(false, "Unable to find view with id search_text");
			}
			InstrumentationBackend.log("search_text view found");
			InstrumentationBackend.solo.enterText(searchText, args[0]);

			
			InstrumentationBackend.log("Clicking on Search");
			if (!pressView("search_icon")) {
				return new Result(false, "Unable to find view with id search_icon");
			}
			InstrumentationBackend.log("search_icon clicked");

		}
		
		return new Result(true);
		
	}
	
	@Override
	public String key() {
		return "action_bar_search";
	}

}
