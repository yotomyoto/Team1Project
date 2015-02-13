
package ca.ualberta.cs.team1travelexpenseapp.test;

import java.util.ArrayList;
import java.util.Date;

import ca.ualberta.cs.team1travelexpenseapp.Claim;
import ca.ualberta.cs.team1travelexpenseapp.ClaimsListController;

import android.R;
import android.nfc.Tag;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import junit.framework.TestCase;

public class ClaimTest extends ActivityInstrumentationTestCase2<ClaimActivity> {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	//US01.01.01
	public void testAddClaim() {
		// Creating a claim and adding test values
		Claim claim = new Claim();
		claim.setName("name");
		claim.setStartDate(new Date(2000,11,11));
		claim.setEndDate(new Date(2015,12,12));
		final String expected = "name";
		final String actual = claim.getName();
		// Asserting that the values match
		assertEquals("name?",expected,actual);
		assertEquals("start date?",new Date(2000,11,11),claim.getStartDate());
		assertEquals("end date?",new Date(2015,12,12),claim.getEndDate());
	}

	

	//US01.02.01
	public void testEnterDestination() {
		// Creating a claim and adding test destination values
		Claim claim = new Claim();
		claim.addDestination("dest 1");
		claim.addDestination("dest 2");
		// Assert values match
		assertEquals("Destination","dest 1",claim.getDestination(0));
		// Edit first value and assert they match
		claim.editDestination(0,"dest 3");
		assertEquals("Destination","dest 3",claim.getDestination(0));
		assertTrue("contains claim", claim.contains("dest 3"));
		assertEquals("length of destination doesn't match",3,claim.destinationCount());
		
	}
	
	//US01.03.01
	public void testViewClaim() {
		// Get the activity and assert that it's on the screen
		ClaimActivity activity = getActivity();
		TextView view = (TextView) activity.findViewByID(R.id.claimtext);
		assertNotNull("activity",activity);
		assertNotNull("textview",view);
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}
	
	//US01.04.01
	public void testEditClaim() {
		// Create a claim with test values
		Claim claim = new Claim();
		claim.setName("name");
		claim.setStartDate(new Date(2000,11,11));
		claim.setEndDate(new Date(2015,12,12));
		final String expected = "test";
		final String actual = claim.getName();
		// Edit values
		claim.setName("name");
		claim.setStartDate(new Date(2100,11,11));
		claim.setEndDate(new Date(2115,12,12));
		// Assert the new values match
		assertEquals("name does not match",expected,actual);
		assertEquals("start date does not match",new Date(2100,11,11),claim.getStartDate());
		assertEquals("end date does not match",new Date(2115,12,12),claim.getEndDate());
		// Attempt to edit non editable claim
		claim.setStatus("submitted");
		claim.setName("NURBS");
		// Assert that the edit failed
		AssertEquals("edit made to non editable claim",claim.getName(),expected);
	}
	//US01.05.01
	public void testDeleteClaim() {
		// Create a claim and add it to the controller
		final Button deleteButton =
        		  (Button) getActivity()
        		 .findViewById(R.id.deleteclaimbutton);
		Claim claim = new Claim();
		ClaimsListController list = new ClaimsListController();
		// Add the claim and assert it's not empty
		list.add(claim);
		assertTrue("list is empty",list.length()==1);
		// Remove the claim and assert it's empty
		list.remove(claim);
		assertTrue("empty list",list.length()==0);
		// Add claim and assert button deletes claim
		list.add(claim);
		TouchUtils.clickView(this, deleteButton);
		assertTrue("button didn't delete, list not empty",list.length()==0);
		
	}
	//US01.06.01
	public void testSaveClaims() {
		// Start the main activity of the application under test
		ClaimActivity activity = getActivity();
		// Create and fill the claim with values
		ClaimsListController list = new ClaimsListController();
		Claim claim = new Claim();
		final String expected = "name";
		claim.setName(expected);
		claim.setStartDate(new Date(2000,11,11));
		claim.setEndDate(new Date(2015,12,12));
		list.add(claim);
	    // Stop the activity - The onDestroy() method should save the state of the claim
		activity.finish();
	    // Re-start the Activity - the onResume() method should restore the state of the Spinner
		ClaimActivity activity = getActivity();
		// Get current claim from the controller
		claim = list.get(0);
		final String actual = claim.getName();
	    // Assert that the current claim is the same as the starting claim
		assertEquals("name?",expected,actual);
		assertEquals("start date?",new Date(2000,11,11),claim.getStartDate());
		assertEquals("end date?",new Date(2015,12,12),claim.getEndDate());
		
	}
	
	//US03.01.01:As a claimant, I want to give an expense claim one or more alphanumeric 
	//tags, so that claims can be organized by me into groups.
	public void addTags(){
		Tag tag1=new Tag("buisness");
		Tag tag2=new Tag("pleasure");
		
		Claim claim= new Claim();
		claim.addTag(tag1);
		ArrayList<Tag> tags=claim.getTags();
		ArrayList<String> tagStrings=new ArrayList<String>();
		assertEquals("Tags were not added properly, length of list incorrect",tags.size(),2);
		for(int i=0; i<tags.size(); i++){
			tagStrings.add(tags.get(i).toString());
		}
		assertTrue("Tags were not added properly, strings do not match those added",tags.contains("buisness") && tags.contains("pleasure"));
		
	}
	
	

}

