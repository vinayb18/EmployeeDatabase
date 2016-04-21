package commrhardman23.httpsgithub.employeedatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeDatabaseInterface extends AppCompatActivity {

    private EditText edtxtName;
    private EditText edtxtPosition;
    private EditText edtxtEmployeeNum;
    private EditText edtxtWage;
    private TextView txtvwResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_database_interface);

        edtxtName = (EditText) findViewById(R.id.edtxtName);
        edtxtPosition = (EditText) findViewById(R.id.edtxtPosition);
        edtxtEmployeeNum = (EditText) findViewById(R.id.edtxtEmployeeNum);
        edtxtWage = (EditText) findViewById(R.id.edtxtWage);
        txtvwResult = (TextView) findViewById(R.id.txtvwResult);

    }

    /**
     * insertData adds elements to the Employee database using information given by the user
     * @param vw is the button the method is associated with
     */
    public void insertData(View vw){

        /**
         * 1. Create a new EmployeeDatabaseHelper variable. You will need to use the following call:
         *    new EmployeeDatabaseHelper(this, null, null, 0);
         * 2. Create variables for all information stored in the Employee database
         * 3. Create a ContentValues variable
         * 4. Get a Writable Database reference using the variable name db (Remember your
         *    try-catch block. The if-else statement that follows should also go in your try block).
         */

        EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this,null,null,0);
        SQLiteDatabase db;
        String name;
        String position;
        String employeeNumber;
        String wage;
        ContentValues employeeValues = new ContentValues();

        try {
            db = employeeDatabaseHelper.getWritableDatabase();

            //insert code from if-else statement here
            if(edtxtName.getText().length() == 0 || edtxtPosition.getText().length() == 0 ||
                    edtxtEmployeeNum.getText().length() == 0 || edtxtWage.getText().length() == 0){

                txtvwResult.setText("You must enter all values to add an element!");

            } else {

                /**
                 * 1. Set each variable equal to the values from the EditTexts
                 * 2. put each value into the ContentValues variable
                 * 3. Call the EmployeeDatabaseHelper's insertElement method
                 * 4. Display that the element has been added successfully
                 */

                name = edtxtName.getText().toString();
                position= edtxtPosition.getText().toString();
                employeeNumber= edtxtEmployeeNum.getText().toString();
                wage= edtxtWage.getText().toString();

                employeeValues.put("NAME", name);
                employeeValues.put("POSITION", position);
                employeeValues.put("EMPLOYEE_NUM", employeeNumber);
                employeeValues.put("WAGE", wage);

                employeeDatabaseHelper.insertElement(db, employeeValues);
                txtvwResult.setText("Successfully Added");

            }

        } catch (SQLiteException e) {
            //display that the database was not found
            txtvwResult.setText("Database Not Found");
        }

    }

    /**
     * searchOrDelete opens the new activity where the user will be able to search or delete entries
     * in the Employee database
     * @param vw is the button that is associated with this method
     */
    public void searchOrDelete(View vw){

        Intent goToSearchDelete = new Intent(this, SearchDatabase.class);

        startActivityForResult(goToSearchDelete, 0);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        edtxtName.setText("");
        edtxtPosition.setText("");
        edtxtEmployeeNum.setText("");
        edtxtWage.setText("");
        txtvwResult.setText("");

    }
}
