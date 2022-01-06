package other.activity.src

fun mvvmFragmentKt(
    applicationPackage: String?,
    activityClass: String,
    layoutName: String,
    packageName: String,
    isCreateFragment:Boolean,
    isUseDataBinding:Boolean
) = if(isCreateFragment)
    """
package $packageName.view

import android.view.View
import com.tz.tzbaselib.${if(isUseDataBinding) "TzDBFragment" else "TzFragment"}
import com.tz.tzbaselib.default.viewModels
import ${packageName}.viewModel.${activityClass}ViewModel
import ${applicationPackage}.R
${
    if(isUseDataBinding)
        "import ${applicationPackage}.databinding.Fragment${activityClass}Binding"
    else
        ""
}

class ${activityClass}Fragment : ${if(isUseDataBinding) "TzDBFragment" else "TzFragment"}<${activityClass}ViewModel${if(isUseDataBinding) ",Fragment${activityClass}Binding" else ""}>() {
    
    override val mainViewModel: ${activityClass}ViewModel by viewModels()
 
    override fun bindLayoutId(): Int = R.layout.${layoutName}
    
    override fun initData() {
        mainViewModel.initData()
    }
    
    override val topBarView: View? = null
    
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
import com.tz.tzbaselib.TzBaseFragment
import com.tz.tzbaselib.default.NoneActivityProvider
import ${applicationPackage}.R

class ${activityClass}Fragment : TzBaseFragment(), NoneActivityProvider {
 
    override fun bindLayoutId(): Int = R.layout.${layoutName}
    
    override fun initData() {
        TODO("Not yet implemented")
    }
    
    override val topBarView: View? = null
    
    override fun initView(view: View) {
        TODO("Not yet implemented")
    }
    
}
"""