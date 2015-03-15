package ca.ualberta.cs.team1travelexpenseapp.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import junit.framework.TestCase;
import ca.ualberta.cs.team1travelexpenseapp.Claim;
import ca.ualberta.cs.team1travelexpenseapp.ClaimList;
import ca.ualberta.cs.team1travelexpenseapp.ClaimantClaimsListActivity;
import ca.ualberta.cs.team1travelexpenseapp.ClaimantCommentActivity;
import ca.ualberta.cs.team1travelexpenseapp.ClaimantExpenseListActivity;
import ca.ualberta.cs.team1travelexpenseapp.ClaimListController;
import ca.ualberta.cs.team1travelexpenseapp.EditClaimActivity;
import ca.ualberta.cs.team1travelexpenseapp.Expense;
import ca.ualberta.cs.team1travelexpenseapp.ExpenseListController;
import ca.ualberta.cs.team1travelexpenseapp.R;
import ca.ualberta.cs.team1travelexpenseapp.User;
import ca.ualberta.cs.team1travelexpenseapp.Claim.Status;


public class ClaimantExpenseListTest extends ActivityInstrumentationTestCase2<ClaimantExpenseListActivity> {
	Activity activity;
	ListView expenseListView;
	Context context;
	
	public ClaimantExpenseListTest() {
		super(ClaimantExpenseListActivity.class);
	}

	private Claim DummyClaim(){
		
		Claim claim = new Claim();
		//by default their status is submitted
		claim.setStartDate(new Date(100));
		claim.setEndDate(new Date(101));
		claim.setStatus(Status.submitted);
		claim.setClaimantName("approver test");
		claim.addDestination("test dest", null);
		//claim.setTotal(100,"EUR");
		
		return claim;
	}
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		//add a claim to test on

		Claim claim1 = new Claim("name",new Date(2000,11,11), new Date(2015,12,12));
		ClaimListController.addClaim(claim1);	
		ClaimListController.setCurrentClaim(claim1);
		
		Intent intent = new Intent();
		intent.putExtra("Index", 0);
		setActivityIntent(intent);
		activity = getActivity();
		expenseListView = (ListView) (activity.findViewById(ca.ualberta.cs.team1travelexpenseapp.R.id.claimantExpensesList));
		
		//add some expense to the claim to test on
		Expense expense1=new Expense("Expense1",new Date(2000,11,11),"category1",
				new BigDecimal(10.00), "CURRENCY1");
		expense1.setComplete(false);//this will default to false
		Expense expense2=new Expense("Expense2",new Date(200,11,13),"category2",
				new BigDecimal(20.00), "CURRENCY2");
		Expense expense3=new Expense("Expense3",new Date(200,11,12),"category3",
				new BigDecimal(25.00), "CURRENCY3");
		
//		claim1.addExpense(expense1);
//		claim1.addExpense(expense2);
//		claim1.addExpense(expense3);
		
	}
	
	/*
//	 *  US 7.01.01
//	 *  As a claimant, I want to submit an expense claim for approval, denoting 
//	 *  the claim status as submitted, with no further changes allowed by me to the 
//	 *  claim information (except the tags).
//	 */
//	
//<<<<<<< HEAD
//	public void testSubmitButton(){
//=======
//
//	public void testSubmit() {
//		//preconditions - User has a claim made that they are viewing 
//		User user = new User("user","Joe");
//		Claim claim = DummyClaim();
//		ClaimListController.setCurrentClaim(claim);
//		Log.i("Help","After the start");
//		
//		
//		
//		ActivityMonitor receiverActivityMonitor = 
//		getInstrumentation().addMonitor(EditClaimActivity.class.getName(),
//				null, false);
//		
//>>>>>>> 87c51863cd19661b2540dad11adbf3fc103a7850
//		
//		Claim claim = DummyClaim();
//		claim.setStatus(Status.inProgress);
//		
//<<<<<<< HEAD
//		ClaimListController.setCurrentClaim(claim);
//=======
//		final Button saveBT = (Button) activity.findViewById(R.id.saveClaimButton);
//		
//		EditClaimActivity receiverActivity = (EditClaimActivity) 
//		receiverActivityMonitor.waitForActivityWithTimeout(720);
//		
//
////		
////		final Button button = (Button) activity.findViewById(R.id.submitClaimButton);
////		activity.runOnUiThread(new Runnable() {
////		    @Override
////		    public void run() {
////		      // click button and open next activity.
////		      button.performClick();
////		    }
////		});
//		
////		assertEquals("Status submitted", "Submitted", claim.getStatus());
//		
//		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(EditClaimActivity.class.getName(), null, false);
//		EditClaimActivity  editClaimActivity = new EditClaimActivity();
//		Log.i("Help","Activity Monitor");
//		
//		
//		activity = receiverActivity;
//		
//		final EditText claimNameET  = (EditText) editClaimActivity.findViewById(R.id.claimNameBody);
//		//final EditText DestinationET  = (EditText) editClaimActivity.findViewById(R.id.destination);
//		final EditText   reason   = (EditText) activity.findViewById(R.id.claimReasonBody);
//		final DatePicker fromDate = (DatePicker) activity.findViewById(R.id.claimFromDate);
//		final DatePicker endDate  = (DatePicker) activity.findViewById(R.id.claimEndDate);
//>>>>>>> 87c51863cd19661b2540dad11adbf3fc103a7850
//		
//		final Button submitButton = (Button) activity.findViewById(R.id.submitButton);
//		activity.runOnUiThread(new Runnable(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				submitButton.performClick();
//			}
//		});
//		getInstrumentation().waitForIdleSync();
//		
//		assertTrue("claim submittied success?", 
//				Status.submitted == ClaimListController.getCurrentClaim().getStatus());
//		
//		
//		
//	}
	
	
	
//	public void testSubmit() {
//		//preconditions - User has a claim made that they are viewing 
//		User user = new User("user","Joe");
//		Claim claim = DummyClaim();
//		ClaimListController.setCurrentClaim(claim);
//		Log.i("Help","After the start");
//		
//		
//		
//		ActivityMonitor receiverActivityMonitor = 
//		getInstrumentation().addMonitor(EditClaimActivity.class.getName(),
//				null, false);
//		
//		
//		
//		final Button saveBT = (Button) activity.findViewById(R.id.saveClaimButton);
//		
//		EditClaimActivity receiverActivity = (EditClaimActivity) 
//		receiverActivityMonitor.waitForActivityWithTimeout(720);
//		
////		// from http://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html#DetermineConnection
////		ConnectivityManager cm =
////		        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
////		 
////	NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
////		boolean isConnected = activeNetwork != null &&
////		                      activeNetwork.isConnectedOrConnecting();
////
////		
////		assertTrue("Connected", isConnected);
//	
////		//Trigger
////		// from http://stackoverflow.com/questions/9405561/test-if-a-button-starts-a-new-activity-in-android-junit-pref-without-robotium
//		
//		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(EditClaimActivity.class.getName(), null, false);
//		EditClaimActivity  editClaimActivity = new EditClaimActivity();
//		Log.i("Help","Activity Monitor");
//		
//		
//		activity = receiverActivity;
//		
//		final EditText claimNameET  = (EditText) editClaimActivity.findViewById(R.id.claimNameBody);
//		//final EditText DestinationET  = (EditText) editClaimActivity.findViewById(R.id.destination);
//		final EditText   reason   = (EditText) activity.findViewById(R.id.claimReasonBody);
//		final DatePicker fromDate = (DatePicker) activity.findViewById(R.id.claimFromDate);
//		final DatePicker endDate  = (DatePicker) activity.findViewById(R.id.claimEndDate);
//		
//		claimNameET.setText("TEST NAME");
//		//DestinationET.setText("TESTDEST");
//		//final Button saveBT = (Button) editClaimActivity.findViewById(R.id.saveClaimButton);
//		editClaimActivity.runOnUiThread(new Runnable(){
//			
//			public void run(){
//				Log.i("Help","right before button click");
//				saveBT.performClick();// approver user type is selected
//				//User type selected : precondition
//			}
//			
//		});
//		
//		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
//		Log.i("Help","nextActivity");
//		assertNotNull(nextActivity);
//		
//		ViewAsserts.assertOnScreen(nextActivity.getWindow().getDecorView(),null);
//		Log.i("Help","Assert on screen");
//	
//		Set<String> setDestTest = null;
//		setDestTest.add("TESTDEST");
//			
//		assertEquals("Status submitted", Status.submitted, claim.getStatus());
//		assertFalse("Claim name not editable",ClaimListController.getCurrentClaim().getClaimantName()=="TEST NAME");
//		assertFalse("Claim destination not editable", ClaimListController.getCurrentClaim().getDestinations()==setDestTest);
//
//		
//		
//	}
	// old test
//	public void testSubmitClaim(){
//		final Claim claim = ClaimListController.getClaim(0);
//		Button button = (Button) activity.findViewById(R.id.submitClaimButton);
//		button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				claim.submit();
//				
//			}
//		});
//		Claim claimSubmitted = ClaimListController.getSubmittedClaim(0);
//		assertEquals("Claim Submitted", claim, claimSubmitted);
//		assertEquals("Claim status submitted", "Submitted", claim.getStatus());

//	}

	/*
	 * US 7.02.01
	 * As a claimant, I want a visual warning when trying to 
	 * submit an expense claim when there are fields with missing values or 
	 * there are any incompleteness indicators on the expense items.
	 */
	
	public void testSubmitWarning() {
		Claim claim = new Claim();
		claim.setComplete(false);
		ClaimListController.setCurrentClaim(claim);
		final Button submitButton = (Button) activity.findViewById(R.id.submitButton);
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				submitButton.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		AlertDialog dia = getActivity().submitWarningDialog;
		assertTrue("Not null", dia != null);

		assertTrue("Dialog shows1", dia.isShowing());
		
		claim.setComplete(true);
		Expense expense = new Expense();
		expense.setFlagged(true);
		ExpenseListController.addExpense(expense);
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				submitButton.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();

		dia = getActivity().submitWarningDialog;
		assertTrue("Dialog shows2", dia.isShowing());
	}
//

//	
	/*
	 *  US 7.04.01
	 *  As a claimant, I want a submitted expense claim that was 
	 *  approved to be denoted by a claim status of approved, with no
	 *   further changes allowed by me to the claim information (except the tags).
	 */
	
	public void testApproved(){
		// precondition - claimant has an approved claim
		Claim claim = DummyClaim();
		claim.setStatus(Status.approved);
		claim.setClaimantName("Dummy");
		ClaimListController.setCurrentClaim(claim);
		
		
		final EditText editName = (EditText) activity.findViewById(R.id.claimNameBody);
		final EditText editDestination = (EditText) activity.findViewById(R.id.claimDestinationBody);
		final EditText editReason = (EditText) activity.findViewById(R.id.claimReasonBody);

		editName.setText("Joe");
		editDestination.setText("Hawaii");
		editReason.setText("Business");
//		claim.addTag("Holiday");
//
		final Button button = (Button) activity.findViewById(R.id.saveClaimButton);
		activity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		});
//		assertEquals("Claim status Approved", "Approved", claim.getStatus());
//		assertEquals("Claim tags editable", claim.getTag(0), "Holiday");
		assertNotSame("Claim name not editable", ClaimListController.getCurrentClaim().getClaimantName(), "Joe");
		assertNotSame("Claim destination not editable", ClaimListController.getCurrentClaim().getDestinations(), "Hawaii");
		assertNotSame("Claim reason not editable", ClaimListController.getCurrentClaim().getReason("Hawaii"), "Business");
//
	}
//	
//	//US05.01.01: As a claimant, I want to list all the expense items for a claim, 
//	//in order of entry, showing for each expense item: the date the expense was 
//	//incurred, the category, the textual description, amount spent, unit of currency, 
//	//whether there is a photographic receipt, and incompleteness indicator.
//	public void testListExpense(){
//		//precondition
//		Claim claim = ClaimListController.getClaim(0);
//		
//		int claimCount = expenseListView.getCount();
//		for(int i=0; i < claimCount; i++){
//			//get text info from a claim at position of i of expenseListView 
//			TextView expenseInfo = (TextView) expenseListView.getItemAtPosition(i);
//			
//			//toString() method should be checked manually to verify it contains the correct info
//			String viewText = expenseInfo.getText().toString();
//
//			//get expense at position i of expenseList of claim for the activity
//			Expense expense=((ClaimantExpenseListActivity) activity).claim.getExpense(i);
//			String expenseText=expense.toString();
//			
//			String expectedText =((ClaimantExpenseListActivity) activity).claim.toString();
//			assertEquals("Expense summary at list item "+i+" does not match expected value",expectedText, viewtext);	
//		}
//		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(),expenseListView);
//	}
	
	/* US07.05.01
	* As a claimant, I want to see the name of the approver and any comment(s) 
	* from the approver on a returned or approved claim.
	*/

	public void testApproverNameComments(){
	
		ClaimList list = new ClaimList();
		final Claim claim =  new Claim();
		list.addClaim(claim);
		ClaimListController.addClaim(claim);
		ClaimListController.setCurrentClaim(claim);
		
		Expense expense = new Expense();
		//ClaimListController.getCurrentClaim().addExpense(expense);
		ExpenseListController.addExpense(expense);
					
		User checkUser = new User("approver","John");
		ClaimListController.setUser(checkUser);
		
		ClaimListController.getCurrentClaim().addComment("HI it looks good");
		
		// get approve button
		final Button button = (Button) activity.findViewById(R.id.viewCommentsButton);
		//from http://stackoverflow.com/a/9406087 (March 15, 2015)
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantCommentActivity.class.getName(), null, false);
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// click approve button
				button.performClick(); 	
				
			}
			
		});
		getInstrumentation().waitForIdleSync();

		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
		// next activity is opened and captured.
		TextView text = (TextView) nextActivity.findViewById(R.id.claimantCommentString);
		assertEquals("Can View Comments","John\nHI it looks good", text.getText().toString());
		assertNotNull(nextActivity);
		nextActivity.finish();
	}
	
	//US08.06.01
	/*
	*Tests if an approver comment
	*was successfully added to 
	*a claim
	*/
	public void testCommentAddable(){
		Claim claim = new Claim();
		ClaimListController.setCurrentClaim(claim);
		User user = new User("Approver", "Geoff");
		ClaimListController.setUser(user);
		
		claim.addComment("comment");
		
		final Button commentButton = (Button) activity.findViewById(R.id.viewCommentsButton);
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantCommentActivity.class.getName(), null, false);
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				commentButton.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();

		
		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50);
		TextView text = (TextView) nextActivity.findViewById(R.id.claimantCommentString);
		assertEquals("Can View Comments","comment", text.getText().toString());
		assertNotNull(nextActivity);
		nextActivity.finish();

		
	}
}
