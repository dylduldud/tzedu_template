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
import android.view.ViewGroup
import com.tz.tzbaselib.${if(isUseDataBinding) "TzDBActivity" else "TzActivity"}
import com.tz.tzbaselib.impl.viewModels
import com.tz.tzbaselib.impl.DefaultAppBar
import ${packageName}.viewModel.${activityClass}ViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import ${applicationPackage}.R
${
    if(isUseDataBinding) 
        "import ${applicationPackage}.databinding.Activity${activityClass}Binding"
    else
        ""
}

@Route(path = "")
class ${activityClass}Activity : ${if(isUseDataBinding) "TzDBActivity" else "TzActivity"}<${activityClass}ViewModel${if(isUseDataBinding) ",Activity${activityClass}Binding" else ""}>() {
    
    override val mainViewModel: ${activityClass}ViewModel by viewModels()
 
    override fun bindLayoutId(): Int = R.layout.${layoutName}
    
    override fun initData() {
        mainViewModel.initData()
    }
    
     override val topBarView: View? by lazy {
        DefaultAppBar(this).createAppBar(contentView as ViewGroup).apply {
            //不需要AppBar可以移除
        }.build()
     }
    
    override fun initView(view: View) {
        ${if(isUseDataBinding) """
            mainDataBinding?.apply{
                model = mainViewModel
            }
        """.trimIndent() else ""}
        
    }
    
}
"""
else
"""
package $packageName.view

import android.view.View
import android.view.ViewGroup
import com.tz.tzbaselib.TzBaseActivity
import com.tz.tzbaselib.impl.NoneActivityProvider
import com.tz.tzbaselib.impl.DefaultAppBar
import ${applicationPackage}.R
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "")
class ${activityClass}Activity : TzBaseActivity(), NoneActivityProvider {
 
    override fun bindLayoutId(): Int = R.layout.${layoutName}
    
    override fun initData() {
        TODO("Not yet implemented")
    }
    
    override val topBarView: View? by lazy {
        DefaultAppBar(this).createAppBar(contentView as ViewGroup).apply {
            //不需要AppBar可以移除
        }.build()
     }
    
    override fun initView(view: View) {
        TODO("Not yet implemented")
    }
    
}
"""