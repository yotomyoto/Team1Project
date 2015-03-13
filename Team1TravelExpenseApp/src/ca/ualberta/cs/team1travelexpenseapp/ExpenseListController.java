package ca.ualberta.cs.team1travelexpenseapp;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


public class ExpenseListController {
	protected static Expense currentExpense = null;
	private static ExpenseList currentExpenseList= null;

	public static ExpenseList getCurrentExpenseList() { 
		if (currentExpenseList == null) {
			currentExpenseList = ClaimListController.getCurrentClaim().getExpenseList();
		}
		
		return currentExpenseList;
	}
	
	public static void setCurrentExpense(Expense expense){
		currentExpense = expense; 
	}
	
	public static Expense getCurrentExpense(){
		return currentExpense;
	}
	
	public static void setCurrentExpenseList(ExpenseList expenseList){
		currentExpenseList = expenseList; 
	}
	
	public static void addExpense(Expense expense){
		ArrayList<Expense> expenseArray=ExpenseListController.getCurrentExpenseList().getExpenses();
		expenseArray.add(expense);
		setCurrentExpense(expense);
		//setCurrentExpenseList(ClaimListController.getCurrentClaim().getExpenseList());
		
		getCurrentExpenseList().setExpenseList(expenseArray);
	}
	
	public static void removeExpense(Expense expense){
		ArrayList<Expense> expenseArray=ExpenseListController.getCurrentExpenseList().getExpenses();
		expenseArray.remove(expense);
		getCurrentExpenseList().setExpenseList(expenseArray);
	}
	
	public static void updateExpense(Expense expense, Expense newExpense){
		if (ClaimListController.getCurrentClaim().status != Claim.Status.submitted && 
				ClaimListController.getCurrentClaim().status != Claim.Status.approved){
			ArrayList<Expense> expenseArray=ExpenseListController.getCurrentExpenseList().getExpenses();
			expenseArray.set(expenseArray.indexOf(expense), newExpense);
			setCurrentExpense(newExpense);
			getCurrentExpenseList().setExpenseList(expenseArray);
		}
	}
	
	public static void onExpenseSaveClick(EditExpenseActivity activity) {
			
		Spinner categorySpinner = (Spinner) activity.findViewById(R.id.categorySelector);
		String categoryText = String.valueOf(categorySpinner.getSelectedItem());
		
		EditText descriptionView = (EditText) activity.findViewById(R.id.descriptionBody);
		String descriptionText = descriptionView.getText().toString();
		
		DatePicker dateView = (DatePicker) activity.findViewById( R.id.expenseDate);
		Calendar   calendar   = Calendar.getInstance();
		calendar.set(dateView.getYear(), dateView.getMonth(), dateView.getDayOfMonth());
		Date date = calendar.getTime();
		
		EditText amountView = (EditText) activity.findViewById(R.id.currencyBody);
		BigDecimal amountValue = new BigDecimal(amountView.getText().toString());
		
		Spinner currencySpinner = (Spinner) activity.findViewById(R.id.currencySelector);
		String currencyText = String.valueOf(currencySpinner.getSelectedItem());
		
		updateExpense(getCurrentExpense(), new Expense(descriptionText, date, categoryText, amountValue, currencyText));
		
		
		activity.finish();	
	}
	
	public static void onAddExpenseClick(ClaimantExpenseListActivity activity) {
		ExpenseListController.addExpense(new Expense());
		Intent intent = new Intent(activity, EditExpenseActivity.class);
		activity.startActivity(intent);
		
	}

	public static File compressPhoto(EditExpenseActivity activity,
			File photoFile) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void onRemoveExpenseClick() {
		ArrayList<Expense> expenses = getCurrentExpenseList().getExpenses();
		expenses.remove(currentExpense);
		currentExpenseList.setExpenseList(expenses);
		
	}
}