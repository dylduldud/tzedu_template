package other.activity.src

fun mvvmViewModel(
    packageName: String,
    activityClass: String,
    isCreateRepository: Boolean
) = if (isCreateRepository)
"""
package $packageName.viewModel

import com.tz.tzbaselib.TzViewModel
import ${packageName}.repository.${activityClass}Repository

class ${activityClass}ViewModel : TzViewModel() {
  
    val ${activityClass.first().toLowerCase()}${activityClass.substring(1,activityClass.length)}Repository by lazy { ${activityClass}Repository() }
    
    fun initData(){
        complete()
    }
    
}
"""
else
"""
package $packageName.viewModel

import com.tz.tzbaselib.TzViewModel

class ${activityClass}ViewModel : TzViewModel() {
    
    fun initData(){
        complete()
    }
    
}
"""