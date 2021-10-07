package other.activity.res

fun mvvmActivityXml(
    packageName: String,
    activityClass: String,
    isCreateViewModel: Boolean,
    isUseDataBinding:Boolean
) = if (isCreateViewModel&&isUseDataBinding)
"""
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable name="model" type="${packageName}.viewModel.${activityClass}ViewModel"/>
    </data>
    
    <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">
        
    </LinearLayout>
    
</layout>
"""
else
"""
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
        
</LinearLayout>
"""