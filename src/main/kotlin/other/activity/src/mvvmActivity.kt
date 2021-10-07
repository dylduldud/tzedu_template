package other.activity.src
//activityClass.first().toUpperCase()}${activityClass.substring(1,activityClass.length).toLowerCase()
fun mvvmActivityKt(
    applicationPackage: String?,
    activityClass: String,
    layoutName: String,
    packageName: String,
    isCreateViewModel:Boolean,
    isUseDataBinding:Boolean
) = if(isCreateViewModel)
"""
package $packageName.view

import android.view.View
import com.tz.tzbaselib.${if(isUseDataBinding) "TzDBActivity" else "TzActivity"}
import com.tz.tzbaselib.default.viewModels
import ${packageName}.viewModel.${activityClass}ViewModel
import ${applicationPackage}.R
${
    if(isUseDataBinding) 
        "import ${applicationPackage}.databinding.Activity${activityClass}Binding"
    else
        ""
}

class ${activityClass}Activity : ${if(isUseDataBinding) "TzDBActivity" else "TzActivity"}<${activityClass}ViewModel${if(isUseDataBinding) ",Activity${activityClass}Binding" else ""}>() {
    
    override val mainViewModel: ${activityClass}ViewModel by viewModels()
 
    override fun bindLayoutId(): Int = R.layout.${layoutName}
    
    override fun initData() {
        mainViewModel.initData()
    }
    
    override fun initTopBar(): View? = null
    
    override fun initView(view: View) {
        ${if(isUseDataBinding) "mainDataBinding?.model=mainViewModel" else ""}
        
    }
    
}
"""
else
"""
package $packageName.view

import android.view.View
import com.tz.tzbaselib.TzBaseActivity
import com.tz.tzbaselib.default.NoneActivityProvider
import ${applicationPackage}.R

class ${activityClass}Activity : TzBaseActivity(), NoneActivityProvider {
 
    override fun bindLayoutId(): Int = R.layout.${layoutName}
    
    override fun initData() {
        TODO("Not yet implemented")
    }
    
    override fun initTopBar(): View? = null
    
    override fun initView(view: View) {
        TODO("Not yet implemented")
    }
    
}
"""