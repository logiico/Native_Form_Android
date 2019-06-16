
# Native_Form_Android

- what is this repository :

   this is a sample project in which native form library (app_native_lib) is used
   to demonstrate the useability of this module

- what does the library do exactly :

   Form in json format is sent to native form library (app_native_lib), it creates
   form with android views then user can fill the form and submit it.

   The library (specificly ActivitynativeForm.java) returns a list of filled data in json format along with attached files to your activity

- how to use library (app_native_lib) :

    > Create a simple project and add the module (app_native_lib).
    > Then add an activity and its xml which has only a button, the
      activity is responsible to send required data ( form in json and other requirements )
      by intent, after all Implement onActivityResult to receive result of filled data.
    > Form in json format should be sent to ActivityNativeForm.java by Intent.you can do 
      it your way but I put the String in raw folder as file and open it to get the json.

- This is the Form in json format :
  [here](https://github.com/tahadev/Native_Form_Android/blob/master/app_sample/src/main/res/raw/sample_form_format.docx)

  
- This is same form created by above link :
 
 
<img src="https://raw.githubusercontent.com/tahadev/Native_Form_Android_Sample/master/app_native_lib/src/main/java/com/example/nativeformmakerandroid/1_1.jpg" />


- how to use it :

  you need to add thes code to your project to send required data to ActivityNativeform.java 


       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent;
                  intent = new Intent(getApplicationContext(), ActivityFormNative.class);
                  intent.putExtra(ConstString.JOB_TEMPLATE_ID, 1 /*mission.Templates.get(position).Id*/); // todo : make pop up
                  long uid = ...  ; // A uniq ID for form
                  intent.putExtra(ConstString.JOB_DATA_U_ID, uid); //
                  intent.putExtra(ConstString.FORM_EDITABLE, true); //
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstHelper.Simple_Date_Format);
                  String currDate = ... ; // A datetime in string format
                  intent.putExtra(ConstString.FORM_ENTERING_TIME, currDate); 
                  String templateJson = ... ; // A string which is a Form (Template) in json format
                  intent.putExtra(ConstString.FORM_TEMPLATE_IN_JSON_FORMAT, templateJson); //
                  intent.putExtra(ConstString.STATUS_IN_APP, EnumDataStatusInApp.STATUS_NOT_SET_YET.getIntValue()); 
                  
                  startActivityForResult(intent, FORM_REQUEST_CODE);
              }
          });

how to get result of filled form: 
 
     @Override
      protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       
        if (requestCode == FORM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            /* filled data in json format */
            data_json = data.getStringExtra(ConstString.JOB_TEMPLATE_SAVED_DATA);
            /* file attachments added to form */
           List<FileModelJavaScript> fileModelJavaScripts = data.getParcelableArrayListExtra(ConstString.JOB_TEMPLATE_SAVED_FILE_LIST);
        } else {
            Log.e(TAG , "no result");
        }
        Log.e(TAG, "11");
    }


    
   
