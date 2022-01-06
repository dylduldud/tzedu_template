package other.item.res

fun itemXmlTemplate(applicationPackage: String, activityClass: String, isCreateDataBinding: Boolean): String {
    if (isCreateDataBinding) {
        return """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools">

  <data>

    <variable
      name="model"
      type="$applicationPackage.item.${activityClass}Model" />
     
  </data>

  <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

  </LinearLayout>

</layout>

"""
    }
    return """
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:orientation="vertical">

</LinearLayout>      
"""
}